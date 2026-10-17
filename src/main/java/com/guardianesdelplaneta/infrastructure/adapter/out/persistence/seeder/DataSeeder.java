package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;
import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.domain.model.enums.RewardType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.AchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.EcosystemEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.RewardEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.AchievementRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.EcosystemRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LessonRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LevelRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.RewardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final LevelRepository levelRepository;
    private final EcosystemRepository ecosystemRepository;
    private final LessonRepository lessonRepository;
    private final ActivityRepository activityRepository;
    private final AchievementRepository achievementRepository;
    private final RewardRepository rewardRepository;

    public DataSeeder(LevelRepository levelRepository,
                      EcosystemRepository ecosystemRepository,
                      LessonRepository lessonRepository,
                      ActivityRepository activityRepository,
                      AchievementRepository achievementRepository,
                      RewardRepository rewardRepository) {
        this.levelRepository = levelRepository;
        this.ecosystemRepository = ecosystemRepository;
        this.lessonRepository = lessonRepository;
        this.activityRepository = activityRepository;
        this.achievementRepository = achievementRepository;
        this.rewardRepository = rewardRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Verificando e inicializando datos semilla...");
        Map<Integer, LevelEntity> levels = seedLevels();
        seedEcosystemsAndLessons(levels);
        seedBosqueAndinoActivities();
        seedEcosystemActivities("PARAMO", ParamoActivitySeeder::buildActivities);
        seedEcosystemActivities("OCEANO", OceanoActivitySeeder::buildActivities);
        seedEcosystemActivities("SELVA_AMAZONICA", SelvaAmazonicaActivitySeeder::buildActivities);
        seedEcosystemActivities("MANGLAR", ManglarActivitySeeder::buildActivities);
        seedEcosystemActivities("BOSQUE_SECO_TROPICAL", BosqueSecoActivitySeeder::buildActivities);
        seedEcosystemActivities("LLANOS_SABANA", LlanosSabanaActivitySeeder::buildActivities);
        seedAchievements();
        seedRewards(levels);
        log.info("Inicialización de datos semilla completada.");
    }

    private Map<Integer, LevelEntity> seedLevels() {
        Map<Integer, LevelEntity> map = new HashMap<>();

        record LevelData(int number, String name, int requiredXp, String description, String visualKey) {}

        List<LevelData> levelDataList = List.of(
                new LevelData(1, "Semilla Guardiana", 0, "Nivel inicial del explorador ambiental", "LEVEL_1_SEED"),
                new LevelData(2, "Explorador del Agua", 500, "Protector de cuencas y fuentes hídricas", "LEVEL_2_WATER"),
                new LevelData(3, "Guardián de los Mares", 1000, "Defensor de costas, arrecifes y fauna marina", "LEVEL_3_OCEAN"),
                new LevelData(4, "Protector de la Selva", 1500, "Custodio de bosques y biodiversidad profunda", "LEVEL_4_JUNGLE"),
                new LevelData(5, "Defensor del Planeta", 2000, "Líder en mitigación y cuidado integral", "LEVEL_5_DEFENDER"),
                new LevelData(6, "Maestro Guardián", 2500, "Máximo rango de sabiduría ambiental", "LEVEL_6_MASTER")
        );

        for (LevelData data : levelDataList) {
            LevelEntity entity = levelRepository.findByLevelNumber(data.number())
                    .orElseGet(() -> levelRepository.save(LevelEntity.builder()
                            .levelNumber(data.number())
                            .name(data.name())
                            .requiredXp(data.requiredXp())
                            .description(data.description())
                            .visualKey(data.visualKey())
                            .build()));
            entity.setName(data.name());
            entity.setDescription(data.description());
            entity.setVisualKey(data.visualKey());
            levelRepository.save(entity);
            map.put(data.number(), entity);
        }

        return map;
    }

    private void seedEcosystemsAndLessons(Map<Integer, LevelEntity> levels) {
        record LessonInfo(String title, String content, int orderIndex, int xpReward) {}
        record EcosystemData(String code, String name, String description, int requiredLevelNumber, String mascotKey, List<LessonInfo> lessons) {}

        List<EcosystemData> ecosystemList = List.of(
                new EcosystemData(
                        "BOSQUE_ANDINO",
                        "Bosque Andino",
                        "Biodiversidad de alta montaña, niebla y flora nativa.",
                        1,
                        "OSO_ANDINO",
                        List.of(
                                new LessonInfo(
                                        "Bienvenidos al Bosque Andino",
                                        "El Bosque Andino es un ecosistema que se encuentra en las montañas de los Andes. Allí hay árboles, plantas, animales y fuentes de agua que necesitan condiciones especiales para vivir. En este lugar, la neblina, la humedad y las temperaturas frescas ayudan a mantener la vida. ¡Hoy comienza tu aventura para descubrirlo y protegerlo!",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "Exploradores de la Biodiversidad",
                                        "La biodiversidad es la variedad de seres vivos que habitan un lugar. En el Bosque Andino encontramos árboles, musgos, helechos, insectos, aves y muchos otros organismos. Cada uno cumple una función importante: algunas plantas producen alimento, ciertos animales transportan semillas y otros ayudan a descomponer los restos naturales. Cuanta más biodiversidad saludable exista, más conexiones tendrá este ecosistema.",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "Los Habitantes del Bosque",
                                        "Los animales del Bosque Andino tienen adaptaciones que les permiten encontrar alimento, refugio y pareja. El oso andino, por ejemplo, consume frutos y plantas, y puede ayudar a dispersar semillas. Las aves pueden transportar semillas a otros lugares, mientras que los insectos polinizadores ayudan a muchas plantas a reproducirse. Proteger a los animales también significa conservar los lugares donde encuentran lo que necesitan para vivir.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "El Viaje del Agua",
                                        "El agua viaja constantemente por la naturaleza mediante el ciclo del agua. Se evapora, forma nubes, cae como lluvia y puede infiltrarse en el suelo o alimentar quebradas y ríos. Los bosques ayudan a regular el agua porque sus hojas, raíces y suelos favorecen su retención y circulación. Cuando destruimos la vegetación o contaminamos las fuentes hídricas, ponemos en riesgo tanto a los animales como a las comunidades humanas.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "La Red de la Vida",
                                        "En el bosque, los seres vivos están conectados mediante relaciones de alimentación, refugio y reproducción. Las plantas producen su propio alimento usando la luz solar; los herbívoros comen plantas y algunos depredadores se alimentan de otros animales. Los hongos y muchos microorganismos descomponen restos de seres vivos y devuelven nutrientes al suelo. Si una especie desaparece, otras pueden verse afectadas porque forman parte de una misma red ecológica.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "Los Guardianes del Suelo",
                                        "Aunque no siempre lo vemos, el suelo está lleno de vida. Las lombrices, los hongos, las bacterias y otros organismos ayudan a descomponer hojas y ramas, reciclar nutrientes y mantener la fertilidad. Las raíces sujetan la tierra y la vegetación reduce el impacto directo de la lluvia. La tala, las quemas y la erosión pueden degradar el suelo, dificultando que nuevas plantas crezcan y que el bosque se regenere.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Amenazas al Bosque Andino",
                                        "El Bosque Andino enfrenta amenazas como la deforestación, los incendios, la contaminación y la expansión de las ciudades y los cultivos. Cuando un bosque se divide en pequeños fragmentos, muchos animales tienen dificultades para encontrar alimento, reproducirse o desplazarse. Estas alteraciones también pueden afectar el agua y el clima local. Para proteger el ecosistema debemos identificar las causas de los problemas y comprender cómo se relacionan entre sí.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Salvar el Bosque Andino",
                                        "¡Llegó el desafío final, Guardián del Planeta! Imagina que una zona del Bosque Andino está perdiendo árboles, sus quebradas tienen residuos y los animales están perdiendo sus refugios. Para ayudarla, debes proponer un plan que incluya restaurar la vegetación con especies nativas, evitar la contaminación del agua, proteger los hábitats y trabajar con la comunidad. Recuerda que las soluciones deben considerar las conexiones entre plantas, animales, suelo y agua. ¡Ahora tienes el conocimiento para convertirte en un verdadero protector del bosque!",
                                        8,
                                        60
                                )
                        )
                ),
                new EcosystemData(
                        "PARAMO",
                        "Páramo",
                        "Conservación del agua y fábrica natural de ríos.",
                        1,
                        "FRAILEJON_GUANE",
                        List.of(
                                new LessonInfo(
                                        "Exploradores del Páramo Colombiano",
                                        "El páramo es un ecosistema de alta montaña que encontramos en Colombia, especialmente en las cordilleras de los Andes. Sus temperaturas son bajas, hay neblina frecuente y pueden alternarse momentos de sol, lluvia y frío. Allí viven frailejones, pajonales, pequeños animales y muchas otras especies adaptadas a estas condiciones. Los páramos son fundamentales para el país porque sus suelos y humedales almacenan agua y ayudan a regular su circulación.",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "El Ciclo del Agua en la Niebla",
                                        "En los páramos colombianos, la neblina y las nubes bajas son parte importante del ambiente. Las gotas de agua pueden quedar atrapadas en la vegetación y después caer al suelo, mientras la lluvia también alimenta las quebradas y los humedales. Parte del agua se infiltra y queda almacenada en los suelos, desde donde puede desplazarse lentamente. Así, el agua sigue su recorrido por el ecosistema y contribuye al abastecimiento de ríos y comunidades. ¡La niebla también forma parte de esta aventura!",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "La Misión del Frailejón",
                                        "El frailejón es una de las plantas más representativas de los páramos colombianos. Sus hojas, cubiertas de pequeños pelos, ayudan a protegerlo del frío y pueden retener gotas de agua. Sus hojas muertas también se acumulan alrededor del tallo y contribuyen a conservar la humedad. Los frailejones forman parte de un ecosistema que regula el agua, pero necesitan años para crecer. Por eso, arrancarlos o pisotearlos puede causar daños difíciles de reparar.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "Guardianes de los Nacederos",
                                        "Los nacederos son lugares donde el agua subterránea llega a la superficie y comienza a alimentar pequeños cursos de agua. En los páramos, los suelos húmedos, las turberas y la vegetación ayudan a regular el movimiento del agua que puede llegar a quebradas y ríos. Si contaminamos estos lugares, removemos la vegetación o alteramos el suelo, podemos perjudicar la calidad y la disponibilidad del agua. Proteger los nacederos significa conservar su entorno, evitar los residuos y respetar las zonas sensibles.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "La Vida en un Clima Extremo",
                                        "En el páramo, las plantas y los animales deben soportar temperaturas cambiantes, vientos fuertes y alta exposición al sol. Algunas plantas crecen cerca del suelo o tienen hojas especiales que ayudan a protegerlas del frío. Los animales buscan refugio, alimento y lugares adecuados para reproducirse. Estas adaptaciones les permiten sobrevivir en condiciones difíciles. Cuando el hábitat cambia demasiado rápido, las especies pueden perder las condiciones que necesitan para vivir.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "Una Red de Vida en las Alturas",
                                        "En el páramo, los seres vivos dependen unos de otros y de las condiciones del ambiente. Los insectos pueden polinizar flores, las aves pueden transportar semillas y los organismos del suelo ayudan a descomponer la materia orgánica. El agua y los nutrientes también conectan los distintos componentes del ecosistema. Si desaparecen ciertas plantas o se deterioran los humedales, pueden cambiar los recursos disponibles para otras especies. Comprender estas relaciones nos ayuda a reconocer por qué proteger una sola parte del páramo no siempre es suficiente.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Amenazas al Páramo Colombiano",
                                        "Los páramos colombianos enfrentan amenazas como las quemas, la expansión agrícola y ganadera, algunas actividades mineras, la contaminación y el cambio climático. Estas presiones pueden degradar los suelos, modificar los humedales y afectar las especies que viven allí. Cuando el suelo pierde su estructura y su capacidad para retener agua, el ecosistema puede tardar mucho tiempo en recuperarse. Para conservarlo debemos identificar las causas del daño y buscar alternativas que permitan proteger la naturaleza y el bienestar de las comunidades.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Proteger la Fábrica de Agua",
                                        "¡El páramo necesita tu ayuda! Imagina que una zona tiene basura cerca de un nacedero, vegetación dañada y señales de erosión. Debes diseñar un plan de protección: identificar las causas del problema, evitar nuevas alteraciones, conservar la vegetación nativa y solicitar apoyo a las autoridades ambientales y a la comunidad. También debes explicar cómo la neblina, los frailejones, los humedales y los suelos participan en la regulación del agua. Tu misión es proponer soluciones que protejan todo el ecosistema, no solamente una planta o una fuente de agua.",
                                        8,
                                        60
                                )
                        )
                ),
                new EcosystemData(
                        "OCEANO",
                        "Océano",
                        "Mares colombianos, arrecifes de coral y fauna marina.",
                        2,
                        "TORTUGA_MARINA",
                        List.of(
                                new LessonInfo(
                                        "Bienvenidos a los Dos Océanos de Colombia",
                                        "Colombia es un país privilegiado al contar con costas en el Mar Caribe y el Océano Pacífico. En estas aguas cálidas y profundas habitan miles de especies marinas. Aprenderemos sobre las diferencias entre ambos litorales, sus corrientes marinas y por qué son vitales para el equilibrio climático del planeta.",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "El Reino de los Arrecifes de Coral",
                                        "Los arrecifes de coral son conocidos como las selvas del mar por su inmensa biodiversidad. Aunque parecen rocas o plantas, los corales son pequeños animales que forman estructuras coloniales donde peces, moluscos y crustáceos encuentran refugio, alimento y zonas de cría.",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "Algas Ondulantes y Praderas Marinas",
                                        "Las praderas de pastos marinos y las algas son los productores primarios del océano. Capturan carbono de la atmósfera, producen oxígeno y sirven de alimento para tortugas, manatíes y peces juveniles, manteniendo el agua limpia y reduciendo la fuerza del oleaje costero.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "La Gran Aventura de la Tortuga Marina",
                                        "Las tortugas marinas recorren miles de kilómetros a lo largo de su vida, pero siempre regresan a las playas donde nacieron para desovar. Descubre sus adaptaciones anatómicas para nadar en mar abierto, su respiración pulmonar y los desafíos que enfrentan los neonatos al llegar al océano.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "El Viaje de la Energía Marina",
                                        "La energía en el océano viaja a través de cadenas tróficas marinas: desde el fitoplancton microscópico y el zooplancton, pasando por peces herbívoros y bancos de sardinas, hasta grandes depredadores tope como tiburones, delfines y ballenas jorobadas.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "Misterios de las Aguas Profundas",
                                        "Bajo la superficie marina, donde la luz del sol no puede penetrar, se encuentra la zona abisal. Los seres vivos aquí han desarrollado adaptaciones sorprendentes como la bioluminiscencia, cuerpos gelatinosos y tolerancia a presiones aplastantes y bajas temperaturas.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Amenazas Bajo las Olas",
                                        "Nuestros océanos enfrentan graves problemas como la acumulación de plásticos de un solo uso, los microplásticos ingeridos por la fauna, el calentamiento del agua que blanquea los corales, la acidificación marina y la sobrepesca de especies vulnerables.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Guardianes de los Océanos Colombianos",
                                        "¡Llegó la hora de actuar, Guardián del Mar! Diseña un plan de rescate marino para proteger las costas colombianas: reducir el uso de plásticos en las ciudades, apoyar áreas marinas protegidas, participar en jornadas de limpieza de playas y promover el consumo responsable de recursos acuáticos.",
                                        8,
                                        60
                                )
                        )
                ),
                new EcosystemData(
                        "SELVA_AMAZONICA",
                        "Selva Amazónica",
                        "El gran pulmón verde del mundo y ríos voladores.",
                        3,
                        "ANACONDA_VERDE",
                        List.of(
                                new LessonInfo(
                                        "Bienvenidos a la Amazonía Colombiana",
                                        "La Amazonía colombiana es una vasta región de selva tropical cruzada por caudalosos ríos. En este territorio verde conviven pueblos indígenas ancestrales y la mayor variedad de fauna y flora del mundo, desempeñando un papel clave en la regulación del clima y las lluvias de todo el continente.",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "El Reino de los Árboles Gigantes",
                                        "En la selva, gigantescos árboles como la ceiba y el caucho pueden superar los 40 metros de altura. Sus copas forman un dosel continuo donde habitan monos, tucanes, ranas arborícolas y orquídeas, creando múltiples pisos de vida y refugio según la altura.",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "La Anaconda Verde: Gigante de los Ríos",
                                        "La anaconda verde es la serpiente más pesada del mundo y una nadadora excepcional de caños, pantanos y ríos amazónicos. Descubre sus adaptaciones anatómicas: ojos y fosas nasales en la parte superior de la cabeza para acechar sumergida y su rol como regulador de poblaciones en el agua.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "El Viaje del Agua por la Selva",
                                        "Los árboles de la selva liberan millones de litros de vapor de agua a la atmósfera mediante evapotranspiración, creando los llamados 'ríos voladores': enormes masas de nubes que llevan lluvias vitales a otras regiones de Colombia y Sudamérica.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "La Red Secreta de la Selva",
                                        "En el Amazonas las relaciones tróficas son fascinantes e intrincadas: desde hongos e insectos que reciclan materia orgánica en el suelo selvático, peces como el pirarucú y pirañas en los ríos, hasta jaguares y aves rapaces como el águila harpía en lo más alto de la cadena.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "Los Guardianes de las Semillas",
                                        "Muchos animales amazónicos son dispersores esenciales: monos churucos, tapires, murciélagos frugívoros e incluso peces que comen frutos caídos al agua. Sin ellos, los árboles nativos no podrían propagarse ni la selva regenerarse tras una perturbación.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Cuando la Selva Está en Peligro",
                                        "La selva amazónica sufre por la deforestación provocada por la ganadería extensiva, los incendios provocados, la tala ilegal y la contaminación de ríos por minería. La pérdida de masa boscosa fragmenta hábitats y altera el ciclo del agua global.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Salvar la Amazonía Colombiana",
                                        "¡Conviértete en guardián de la selva! Diseña un plan comunitario y ambiental para proteger la Amazonía: apoyar la reforestación con semillas nativas, frenar el consumo de productos que causan tala ilegal, promover reservas forestales y valorar los saberes de los pueblos originarios.",
                                        8,
                                        60
                                )
                        )
                ),
                new EcosystemData(
                        "MANGLAR",
                        "Manglar",
                        "Bosques anfibios de protección costera y cunas de vida acuática.",
                        3,
                        "CANGREJO",
                        List.of(
                                new LessonInfo(
                                        "Bienvenidos al Reino del Manglar",
                                        "El manglar es un ecosistema costero único donde la tierra firme se encuentra con el mar. En Colombia los encontramos tanto en el litoral Caribe como en el Pacífico. Este ambiente anfibio sirve de refugio y sala cuna para innumerables especies marinas y terrestres.",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "Árboles con Raíces Extraordinarias",
                                        "Los mangles, como el mangle rojo y el mangle negro, tienen raíces zancudas y pneumatóforos que respiran fuera del fango y filtran el exceso de sal del agua marina. Sus intrincadas ramas submarinas crean laberintos protectores para peces pequeños.",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "El Cangrejo: Pequeño Guardián del Manglar",
                                        "Los cangrejos violinistas y azules son obreros incansables del manglar: excavan galerías que oxigenan el suelo lodoso y se alimentan de hojas caídas, acelerando la descomposición de nutrientes que alimentan a toda la comunidad acuática.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "El Encuentro del Río con el Mar",
                                        "En los estuarios, el agua dulce que baja de las montañas se mezcla con el agua salada que entra con las mareas, formando agua salobre. Esta mezcla rica en sedimentos y minerales genera uno de los entornos más productivos del planeta.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "La Gran Red Alimentaria del Manglar",
                                        "La vida en el manglar está interconectada: desde bacterias y hongos que procesan la hojarasca, moluscos y camarones que se alimentan del detrito, hasta peces juveniles, garzas, pelícanos y nutrias que cazan en sus aguas tranquilas.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "El Escudo Natural de la Costa",
                                        "Los densos bosques de manglar actúan como amortiguadores naturales contra tormentas, huracanes y marejadas ciclónicas, reteniendo los sedimentos para evitar la erosión de las costas y protegiendo a las comunidades humanas costeras.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Cuando el Manglar Está en Peligro",
                                        "La tala indiscriminada de madera, el desarrollo urbano desmedido en las playas, la contaminación por aguas residuales y la instalación de granjas acuícolas sin regulación amenazan con destruir estos valiosos bosques salados.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Salvar el Manglar Colombiano",
                                        "¡Conviértete en protector costero! Elabora una propuesta para la restauración comunitaria de los manglares: siembra de propágulos de mangle, limpieza de residuos plásticos atrapados en las raíces y campañas de sensibilización en las poblaciones pesqueras.",
                                        8,
                                        60
                                )
                        )
                ),
                new EcosystemData(
                        "BOSQUE_SECO_TROPICAL",
                        "Bosque Seco Tropical",
                        "Adaptación a la sequía y especies endémicas del Caribe.",
                        4,
                        "IGUANA_VERDE",
                        List.of(
                                new LessonInfo(
                                        "Bienvenidos al Bosque Seco Tropical",
                                        "El Bosque Seco Tropical es uno de los ecosistemas más amenazados y fascinantes de Colombia. Presente en valles interandinos y en la región Caribe, se caracteriza por experimentar largos meses sin lluvia seguidos de temporadas de aguaceros intensos.",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "Un Bosque que Aprende a Ahorrar Agua",
                                        "Para sobrevivir a meses de intensa sequía, muchos árboles pierden sus hojas (flora decidua) para evitar la transpiración, mientras que otros acumulan agua en sus gruesos troncos y desarrollan espinas protectoras.",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "La Iguana Verde: Experta en el Sol",
                                        "La iguana verde es un reptil maestro de la supervivencia térmica: regula su temperatura corporal tomando el sol en las ramas más altas y aprovecha la vegetación seca y flores para alimentarse con gran eficiencia.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "El Viaje del Agua entre Dos Estaciones",
                                        "El ciclo del agua en el bosque seco es un baile estacional de contrastes: durante la sequía los arroyos se reducen a charcas aisladas, pero con las primeras lluvias el bosque reverdece en cuestión de pocos días.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "La Red de la Vida en el Bosque Seco",
                                        "Las cadenas tróficas del bosque seco incluyen insectos polinizadores, aves como el tití cabeciblanco que se alimenta de néctar y frutos, serpientes de cascabel, armadillos y felinos como el ocelote.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "Semillas Viajeras y Bosques que Renacen",
                                        "Muchas semillas de plantas del bosque seco tienen alas o espinas que les permiten viajar con el viento o adherirse al pelaje de los animales, esperando dormidas en el suelo hasta que caigan las lluvias para germinar.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Cuando el Bosque Pierde su Hogar",
                                        "La tala para agricultura, ganadería y urbanización ha reducido el bosque seco en Colombia a menos del 8% de su cobertura original, dejando a especies únicas sin corredores biológicos para desplazarse.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Recuperar el Bosque Seco",
                                        "¡Súmate al rescate del bosque seco! Diseña un plan de restauración ecológica: siembra de árboles nativos como el guayacán y el ceibo, creación de cercas vivas en fincas ganaderas y concientización comunitaria sobre la prevención de incendios.",
                                        8,
                                        60
                                )
                        )
                ),
                new EcosystemData(
                        "LLANOS_SABANA",
                        "Llanos y Sabana",
                        "Inmensas sabanas de pastizales, humedales y esteros.",
                        5,
                        "CHIGUIRO",
                        List.of(
                                new LessonInfo(
                                        "Bienvenidos a los Llanos Colombianos",
                                        "Los Llanos Orientales y la cuenca de la Orinoquía colombiana forman inmensas sabanas de pastizales naturales que se extienden hasta el horizonte, salpicadas de bosques de galería a orillas de los ríos.",
                                        1,
                                        20
                                ),
                                new LessonInfo(
                                        "Un Mar de Pastos bajo el Sol",
                                        "La vegetación de la sabana está dominada por pastos resistentes y palmas de moriche que crecen en suelos ácidos, adaptadas tanto al sol abrasador de la época seca como a meses de inundación.",
                                        2,
                                        25
                                ),
                                new LessonInfo(
                                        "El Chigüiro: Gigante Tranquilo de los Llanos",
                                        "El chigüiro o capibara es el roedor más grande del mundo y un nadador experto con membranas interdigitales. Vive en manadas familiares y es fundamental para la dinámica de pastoreo y los esteros llaneros.",
                                        3,
                                        30
                                ),
                                new LessonInfo(
                                        "Cuando la Sabana se Convierte en Laguna",
                                        "Durante el invierno llanero, las lluvias desbordan caños y esteros, transformando miles de hectáreas de tierra en gigantescos humedales donde peces, reptiles y mamíferos acuáticos se dispersan libremente.",
                                        4,
                                        35
                                ),
                                new LessonInfo(
                                        "Vecinos de la Sabana: Depredadores y Presas",
                                        "En los Llanos conviven caimanes del Orinoco, anacondas, corocoras rojas, garzas blancas, osos hormigueros gigantes, venados de cola blanca y el imponente jaguar que caza cerca de los morichales.",
                                        5,
                                        40
                                ),
                                new LessonInfo(
                                        "Los Secretos del Suelo y los Humedales",
                                        "Los morichales y humedales llaneros funcionan como gigantescos filtros naturales que almacenan agua limpia y liberan humedad en la temporada seca, siendo oasis vitales para la supervivencia de toda la fauna.",
                                        6,
                                        45
                                ),
                                new LessonInfo(
                                        "Amenazas en la Orinoquía Colombiana",
                                        "La quema descontrolada de pastos, la desecación de esteros para monocultivos extensivos y la contaminación de fuentes de agua ponen en peligro el delicado ciclo de sequías e inundaciones de la sabana.",
                                        7,
                                        50
                                ),
                                new LessonInfo(
                                        "Misión: Proteger los Llanos y sus Aguas",
                                        "¡Sé un verdadero guardián de la sabana! Propón soluciones para la conservación de la Orinoquía: proteger los nacimientos y morichales, evitar fuegos en verano, promover reservas naturales de la sociedad civil y convivir en armonía con la fauna nativa.",
                                        8,
                                        60
                                )
                        )
                )
        );

        for (EcosystemData ecoData : ecosystemList) {
            LevelEntity requiredLevel = levels.getOrDefault(ecoData.requiredLevelNumber(), levels.get(1));
            EcosystemEntity ecoEntity = ecosystemRepository.findByCode(ecoData.code())
                    .orElseGet(() -> ecosystemRepository.save(EcosystemEntity.builder()
                            .code(ecoData.code())
                            .name(ecoData.name())
                            .description(ecoData.description())
                            .requiredLevel(requiredLevel)
                            .mascotKey(ecoData.mascotKey())
                            .isActive(true)
                            .build()));

            ecoEntity.setName(ecoData.name());
            ecoEntity.setDescription(ecoData.description());
            ecoEntity.setRequiredLevel(requiredLevel);
            ecoEntity.setMascotKey(ecoData.mascotKey());
            ecosystemRepository.save(ecoEntity);

            if (!ecoData.lessons().isEmpty()) {
                List<LessonEntity> currentLessons = lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(ecoEntity.getId());
                Map<Integer, LessonEntity> currentByOrder = currentLessons.stream()
                        .collect(Collectors.toMap(LessonEntity::getOrderIndex, l -> l, (l1, l2) -> l1));

                for (LessonInfo lessonInfo : ecoData.lessons()) {
                    LessonEntity lesson = currentByOrder.get(lessonInfo.orderIndex());
                    if (lesson != null) {
                        lesson.setTitle(lessonInfo.title());
                        lesson.setContent(lessonInfo.content());
                        lesson.setXpReward(lessonInfo.xpReward());
                        lessonRepository.save(lesson);
                    } else {
                        lesson = lessonRepository.save(LessonEntity.builder()
                                .ecosystem(ecoEntity)
                                .title(lessonInfo.title())
                                .content(lessonInfo.content())
                                .orderIndex(lessonInfo.orderIndex())
                                .xpReward(lessonInfo.xpReward())
                                .isActive(true)
                                .build());

                        activityRepository.save(ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Actividad: " + lessonInfo.title())
                                .configurationJson("{\"question\":\"¿Qué acción protege este elemento del bosque?\",\"options\":[\"Cuidar y no arrojar basura\",\"Destruir la vegetación\",\"Contaminar el agua\"],\"correctOption\":0}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build());
                    }
                }
            }
        }
    }

    private void seedBosqueAndinoActivities() {
        seedEcosystemActivities("BOSQUE_ANDINO", this::buildActivitiesForBosqueAndinoLesson);
    }

    private void seedEcosystemActivities(String ecosystemCode, BiFunction<LessonEntity, Integer, List<ActivityEntity>> activityBuilder) {
        try {
            activityRepository.deleteIdentificationActivities();
        } catch (Exception e) {
            log.warn("No se pudieron purgar actividades IDENTIFICATION de forma nativa: {}", e.getMessage());
        }

        ecosystemRepository.findByCode(ecosystemCode).ifPresent(ecosystem -> {
            List<LessonEntity> lessons = lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(ecosystem.getId());
            for (LessonEntity lesson : lessons) {
                int lessonOrder = lesson.getOrderIndex();
                List<ActivityEntity> currentActivities = activityRepository.findByLessonIdOrderByOrderIndexAsc(lesson.getId());
                List<ActivityEntity> definedActivities = activityBuilder.apply(lesson, lessonOrder);

                if (currentActivities.isEmpty()) {
                    activityRepository.saveAll(definedActivities);
                    log.info("Sembradas exitosamente 5 actividades para {} lección {} ({})", ecosystemCode, lessonOrder, lesson.getTitle());
                } else {
                    Map<Integer, ActivityEntity> existingByOrder = currentActivities.stream()
                            .collect(Collectors.toMap(ActivityEntity::getOrderIndex, a -> a, (a1, a2) -> a1));

                    for (ActivityEntity def : definedActivities) {
                        ActivityEntity existing = existingByOrder.get(def.getOrderIndex());
                        if (existing != null) {
                            existing.setType(def.getType());
                            existing.setTitle(def.getTitle());
                            existing.setConfigurationJson(def.getConfigurationJson());
                            existing.setXpReward(def.getXpReward());
                            activityRepository.save(existing);
                        } else {
                            activityRepository.save(def);
                        }
                    }

                    List<ActivityEntity> extras = currentActivities.stream()
                            .filter(a -> a.getOrderIndex() > 5)
                            .toList();
                    if (!extras.isEmpty()) {
                        try {
                            activityRepository.deleteAll(extras);
                        } catch (Exception e) {
                            log.warn("No se pudieron eliminar actividades sobrantes en {}: {}", ecosystemCode, e.getMessage());
                        }
                    }

                    log.info("Actualizadas/sembradas exitosamente 5 actividades para {} lección {} ({})", ecosystemCode, lessonOrder, lesson.getTitle());
                }
            }
        });
    }

    private List<ActivityEntity> buildActivitiesForBosqueAndinoLesson(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("¿Dónde vive el oso de anteojos?")
                                .configurationJson("{\"question\":\"¿Dónde vive el oso de anteojos?\",\"options\":[\"En el desierto caliente\",\"En el bosque andino y de niebla\",\"En el fondo del mar\"],\"correctAnswer\":\"En el bosque andino y de niebla\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El bosque andino es hogar de muchos animales")
                                .configurationJson("{\"statement\":\"El bosque andino es hogar de muchos animales, aves e insectos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("¿Quiénes viven en el bosque?")
                                .configurationJson("{\"instructions\":\"Separa los seres vivos del bosque de los objetos que no son vivos:\",\"categories\":[{\"id\":\"vivos\",\"name\":\"Seres vivos del bosque\"},{\"id\":\"no_vivos\",\"name\":\"Objetos no vivos\"}],\"items\":[{\"id\":\"oso\",\"name\":\"Oso de anteojos\"},{\"id\":\"palma\",\"name\":\"Palma de cera\"},{\"id\":\"botella\",\"name\":\"Botella plástica\"},{\"id\":\"llanta\",\"name\":\"Llanta vieja\"}],\"correctAnswers\":{\"oso\":\"vivos\",\"palma\":\"vivos\",\"botella\":\"no_vivos\",\"llanta\":\"no_vivos\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El camino del agua")
                                .configurationJson("{\"instructions\":\"Organiza los tres pasos sobre cómo el agua de la lluvia llega a una quebrada:\",\"steps\":[\"La lluvia cae sobre las hojas y el musgo\",\"El agua se infiltra y viaja por el suelo\",\"El agua brota limpia en la quebrada\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Construye tu bosque andino")
                                .configurationJson("{\"instructions\":\"Ubica cada elemento en su lugar correcto:\",\"targets\":[{\"zone\":\"bosque\",\"name\":\"El Bosque\"},{\"zone\":\"agua\",\"name\":\"La Quebrada\"},{\"zone\":\"rama\",\"name\":\"Las Ramas\"}],\"elements\":[{\"id\":\"arbol\",\"name\":\"Árbol nativo\"},{\"id\":\"pez\",\"name\":\"Pez de montaña\"},{\"id\":\"ave\",\"name\":\"Pájaro andino\"}],\"correctAnswers\":{\"arbol\":\"bosque\",\"pez\":\"agua\",\"ave\":\"rama\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("¿Qué es la biodiversidad?")
                                .configurationJson("{\"question\":\"¿Qué significa la palabra 'biodiversidad' en el bosque andino?\",\"options\":[\"Solo los animales que pueden volar\",\"La gran variedad de seres vivos que habitan un lugar\",\"Las rocas y piedras de las montañas\"],\"correctAnswer\":\"La gran variedad de seres vivos que habitan un lugar\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El musgo retiene agua como esponja")
                                .configurationJson("{\"statement\":\"Los musgos del bosque andino actúan como esponjas naturales absorbiendo el agua de la niebla.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Flora y Fauna Andina")
                                .configurationJson("{\"instructions\":\"Clasifica cada ser vivo según sea una Planta (Flora) o un Animal (Fauna):\",\"categories\":[{\"id\":\"flora\",\"name\":\"Flora (Plantas)\"},{\"id\":\"fauna\",\"name\":\"Fauna (Animales)\"}],\"items\":[{\"id\":\"orquidea\",\"name\":\"Orquídea silvestre\"},{\"id\":\"colibri\",\"name\":\"Colibrí picoespada\"},{\"id\":\"roble\",\"name\":\"Roble andino\"},{\"id\":\"venado\",\"name\":\"Venado de páramo\"}],\"correctAnswers\":{\"orquidea\":\"flora\",\"colibri\":\"fauna\",\"roble\":\"flora\",\"venado\":\"fauna\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("De semilla a árbol andino")
                                .configurationJson("{\"instructions\":\"Ordena las etapas de crecimiento de un roble andino:\",\"steps\":[\"La semilla cae en tierra fértil húmeda\",\"Brotan las primeras hojas verdes\",\"Crece hasta ser un imponente árbol del dosel\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Estratos de la montaña")
                                .configurationJson("{\"instructions\":\"Ubica a cada habitante en su estrato correspondiente del bosque:\",\"targets\":[{\"zone\":\"suelo\",\"name\":\"Suelo húmedo\"},{\"zone\":\"copa\",\"name\":\"Copa de los árboles\"},{\"zone\":\"aire\",\"name\":\"Cielo andino\"}],\"elements\":[{\"id\":\"champi\",\"name\":\"Hongo del suelo\"},{\"id\":\"mono\",\"name\":\"Mono nocturno\"},{\"id\":\"aguila\",\"name\":\"Águila crestada\"}],\"correctAnswers\":{\"champi\":\"suelo\",\"mono\":\"copa\",\"aguila\":\"aire\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El jardinero de los Andes")
                                .configurationJson("{\"question\":\"¿Por qué al oso de anteojos se le llama el 'jardinero de los Andes'?\",\"options\":[\"Porque riega las flores con una regadera\",\"Porque al comer frutos dispersa sus semillas por la montaña\",\"Porque poda los arbustos con sus garras\"],\"correctAnswer\":\"Porque al comer frutos dispersa sus semillas por la montaña\",\"correctOption\":1,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La pava andina dispersora")
                                .configurationJson("{\"statement\":\"La pava andina come frutos silvestres y ayuda a reforestar naturalmente las laderas de la montaña.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Aves y Mamíferos Andinos")
                                .configurationJson("{\"instructions\":\"Distingue entre las Aves del bosque andino y los Mamíferos:\",\"categories\":[{\"id\":\"aves\",\"name\":\"Aves andinas\"},{\"id\":\"mamiferos\",\"name\":\"Mamíferos andinos\"}],\"items\":[{\"id\":\"tucan\",\"name\":\"Tucán piquiplano\"},{\"id\":\"cusumbo\",\"name\":\"Cusumbo de montaña\"},{\"id\":\"gallito\",\"name\":\"Gallito de las rocas\"},{\"id\":\"danta\",\"name\":\"Danta de montaña\"}],\"correctAnswers\":{\"tucan\":\"aves\",\"cusumbo\":\"mamiferos\",\"gallito\":\"aves\",\"danta\":\"mamiferos\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El viaje de las semillas")
                                .configurationJson("{\"instructions\":\"Ordena el ciclo de dispersión de semillas del oso andino:\",\"steps\":[\"El oso come deliciosos frutos silvestres\",\"El oso camina largas distancias por la cordillera\",\"Deposita semillas en el suelo donde nacen nuevos árboles\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Alimentación en el bosque")
                                .configurationJson("{\"instructions\":\"Asigna cada especie a su tipo principal de alimento:\",\"targets\":[{\"zone\":\"frutos\",\"name\":\"Comedor de frutos\"},{\"zone\":\"nectar\",\"name\":\"Bebedor de néctar\"},{\"zone\":\"insectos\",\"name\":\"Cazador de insectos\"}],\"elements\":[{\"id\":\"oso\",\"name\":\"Oso andino\"},{\"id\":\"colibri\",\"name\":\"Colibrí andino\"},{\"id\":\"rana\",\"name\":\"Rana de lluvia\"}],\"correctAnswers\":{\"oso\":\"frutos\",\"colibri\":\"nectar\",\"rana\":\"insectos\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La fábrica de agua")
                                .configurationJson("{\"question\":\"¿Qué función ecológica cumple la densa niebla en el bosque andino?\",\"options\":[\"Enfriar las rocas sin ningún efecto en el agua\",\"Las plantas atrapan sus microgotas alimentando manantiales y ríos\",\"Impedir que crezcan las plantas\"],\"correctAnswer\":\"Las plantas atrapan sus microgotas alimentando manantiales y ríos\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Regulación hídrica")
                                .configurationJson("{\"statement\":\"El bosque andino amortigua las lluvias fuertes y previene avalanchas e inundaciones en las partes bajas.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Cuidado vs Daño a las fuentes de agua")
                                .configurationJson("{\"instructions\":\"Clasifica las acciones según protejan o dañen las fuentes hídricas:\",\"categories\":[{\"id\":\"cuidado\",\"name\":\"Protección del agua\"},{\"id\":\"dano\",\"name\":\"Contaminación y daño\"}],\"items\":[{\"id\":\"alisos\",\"name\":\"Sembrar alisos en las riberas\"},{\"id\":\"plastico\",\"name\":\"Arrojar plásticos al arroyo\"},{\"id\":\"pesticida\",\"name\":\"Verter pesticidas en la quebrada\"},{\"id\":\"limpieza\",\"name\":\"Jornadas comunitarias de limpieza\"}],\"correctAnswers\":{\"alisos\":\"cuidado\",\"plastico\":\"dano\",\"pesticida\":\"dano\",\"limpieza\":\"cuidado\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El ciclo del agua de montaña")
                                .configurationJson("{\"instructions\":\"Ordena el recorrido del agua desde la atmósfera hasta las comunidades:\",\"steps\":[\"La niebla choca contra la vegetación de la cordillera\",\"Los musgos y bromelias filtran y entregan agua pura al suelo\",\"Nacen arroyos limpios que abastecen los acueductos\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Zonas de la cuenca hídrica")
                                .configurationJson("{\"instructions\":\"Ubica cada elemento en la parte de la cuenca que le corresponde:\",\"targets\":[{\"zone\":\"alta\",\"name\":\"Nacimiento (Zona alta)\"},{\"zone\":\"media\",\"name\":\"Caudal medio (Zona media)\"},{\"zone\":\"baja\",\"name\":\"Comunidades (Zona baja)\"}],\"elements\":[{\"id\":\"manantial\",\"name\":\"Manantial de niebla\"},{\"id\":\"cascada\",\"name\":\"Cascada y bosque ripario\"},{\"id\":\"acueducto\",\"name\":\"Bocatoma de acueducto\"}],\"correctAnswers\":{\"manantial\":\"alta\",\"cascada\":\"media\",\"acueducto\":\"baja\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El rol de los polinizadores")
                                .configurationJson("{\"question\":\"¿Qué ocurriría en el bosque andino si desaparecen las abejas y los colibríes?\",\"options\":[\"Muchas especies de plantas no podrán reproducirse ni dar frutos\",\"Los árboles crecerán más rápido al no tener visitantes\",\"El viento sustituirá de inmediato toda la polinización sin problema\"],\"correctAnswer\":\"Muchas especies de plantas no podrán reproducirse ni dar frutos\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Orquídeas y epífitas")
                                .configurationJson("{\"statement\":\"Las orquídeas epífitas viven sobre los troncos para recibir luz sin quitarle nutrientes al árbol hospedero.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Productores y Consumidores")
                                .configurationJson("{\"instructions\":\"Clasifica los organismos según su posición en la red trófica:\",\"categories\":[{\"id\":\"productor\",\"name\":\"Productores autótrofos\"},{\"id\":\"consumidor\",\"name\":\"Consumidores heterótrofos\"}],\"items\":[{\"id\":\"helecho\",\"name\":\"Helecho arbóreo gigante\"},{\"id\":\"puma\",\"name\":\"Puma andino\"},{\"id\":\"palma\",\"name\":\"Palma de cera del Quindío\"},{\"id\":\"ardilla\",\"name\":\"Ardilla andina de cola roja\"}],\"correctAnswers\":{\"helecho\":\"productor\",\"puma\":\"consumidor\",\"palma\":\"productor\",\"ardilla\":\"consumidor\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Cadena alimenticia andina")
                                .configurationJson("{\"instructions\":\"Organiza el flujo de energía en la cadena trófica de la montaña:\",\"steps\":[\"La planta silvestre capta luz solar y produce frutos\",\"El venado soche consume las hojas y frutos de la planta\",\"El puma andino como gran carnívoro regula la población de herbívoros\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Interacciones ecológicas")
                                .configurationJson("{\"instructions\":\"Relaciona a cada organismo con el servicio ecológico clave que brinda:\",\"targets\":[{\"zone\":\"polinizar\",\"name\":\"Polinización\"},{\"zone\":\"dispersar\",\"name\":\"Dispersión de semillas\"},{\"zone\":\"reciclar\",\"name\":\"Descomposición y reciclaje\"}],\"elements\":[{\"id\":\"murcielago\",\"name\":\"Murciélago nectarívoro\"},{\"id\":\"mono\",\"name\":\"Mono nocturno\"},{\"id\":\"hongo\",\"name\":\"Hongo de repisa\"}],\"correctAnswers\":{\"murcielago\":\"polinizar\",\"mono\":\"dispersar\",\"hongo\":\"reciclar\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La capa protectora del suelo")
                                .configurationJson("{\"question\":\"¿Cuál es la función vital de la hojarasca y musgos que cubren el suelo andino?\",\"options\":[\"Impedir que las raíces de los árboles respiren\",\"Nutrir la tierra, retener humedad y evitar que la lluvia arrastre el suelo\",\"Hacer que el bosque sea resbaloso y peligroso\"],\"correctAnswer\":\"Nutrir la tierra, retener humedad y evitar que la lluvia arrastre el suelo\",\"correctOption\":1,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Tala y erosión")
                                .configurationJson("{\"statement\":\"Al talar los bosques de ladera, las raíces mueren y las lluvias provocan deslaves y pérdida de suelo fértil.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Guardianes del suelo vs Habitantes del dosel")
                                .configurationJson("{\"instructions\":\"Clasifica a los seres según habiten el suelo o la copa de los árboles:\",\"categories\":[{\"id\":\"suelo\",\"name\":\"Habitantes del suelo\"},{\"id\":\"dosel\",\"name\":\"Habitantes del dosel (Copa)\"}],\"items\":[{\"id\":\"lombriz\",\"name\":\"Lombriz andina\"},{\"id\":\"bromelia\",\"name\":\"Bromelia epífita\"},{\"id\":\"escarabajo\",\"name\":\"Escarabajo estercolero\"},{\"id\":\"tangara\",\"name\":\"Tángara multicolor\"}],\"correctAnswers\":{\"lombriz\":\"suelo\",\"bromelia\":\"dosel\",\"escarabajo\":\"suelo\",\"tangara\":\"dosel\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Formación de suelo fértil")
                                .configurationJson("{\"instructions\":\"Ordena los pasos de la formación del humus fértil en el bosque:\",\"steps\":[\"Caen hojas, ramas secas y frutos al piso forestal\",\"Hongos, bacterias e insectos descomponen la materia orgánica\",\"Se genera humus rico que nutre a los gigantes del bosque\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Capas del suelo andino")
                                .configurationJson("{\"instructions\":\"Ubica cada componente en la capa del suelo que le corresponde:\",\"targets\":[{\"zone\":\"superficie\",\"name\":\"Capa superficial viva\"},{\"zone\":\"humus\",\"name\":\"Suelo fértil (Humus)\"},{\"zone\":\"roca\",\"name\":\"Roca madre profunda\"}],\"elements\":[{\"id\":\"hojarasca\",\"name\":\"Capa de hojas secas\"},{\"id\":\"raices\",\"name\":\"Red de raíces y micorrizas\"},{\"id\":\"piedras\",\"name\":\"Piedras y base mineral\"}],\"correctAnswers\":{\"hojarasca\":\"superficie\",\"raices\":\"humus\",\"piedras\":\"roca\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Fragmentación de hábitats")
                                .configurationJson("{\"question\":\"¿Por qué la tala y construcción de carreteras no planificadas amenazan a la fauna andina?\",\"options\":[\"Porque fragmentan los bosques y aíslan a las poblaciones impidiendo su reproducción\",\"Porque a los animales les gustan los caminos pavimentados\",\"Porque aumenta la comida disponible para todos\"],\"correctAnswer\":\"Porque fragmentan los bosques y aíslan a las poblaciones impidiendo su reproducción\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Especies exóticas invasoras")
                                .configurationJson("{\"statement\":\"Monocultivos de pino y eucalipto en laderas andinas secan las fuentes hídricas y acidifican el suelo nativo.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Amenazas vs Prácticas Sostenibles")
                                .configurationJson("{\"instructions\":\"Clasifica cada actividad humana según sea una amenaza o una práctica de conservación:\",\"categories\":[{\"id\":\"amenaza\",\"name\":\"Amenaza ecológica\"},{\"id\":\"sostenible\",\"name\":\"Práctica sostenible\"}],\"items\":[{\"id\":\"ganaderia\",\"name\":\"Ganadería extensiva en pendientes\"},{\"id\":\"cafe\",\"name\":\"Café bajo sombra agroforestal\"},{\"id\":\"caza\",\"name\":\"Caza furtiva de fauna silvestre\"},{\"id\":\"corredores\",\"name\":\"Creación de corredores biológicos\"}],\"correctAnswers\":{\"ganaderia\":\"amenaza\",\"cafe\":\"sostenible\",\"caza\":\"amenaza\",\"corredores\":\"sostenible\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Cadena del impacto ambiental")
                                .configurationJson("{\"instructions\":\"Ordena la secuencia destructiva provocada por la tala ilegal:\",\"steps\":[\"Tala y quema de vegetación nativa en la montaña\",\"Lluvias intensas lavan el suelo desprovisto de cobertura vegetal\",\"Ocurren derrumbes que colmatan ríos y afectan comunidades abajo\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Estrategias de restauración")
                                .configurationJson("{\"instructions\":\"Empareja cada amenaza con la solución técnica ecológica adecuada:\",\"targets\":[{\"zone\":\"tala\",\"name\":\"Reforestación con nativas\"},{\"zone\":\"aislamiento\",\"name\":\"Corredores biológicos\"},{\"zone\":\"caza\",\"name\":\"Vigilancia comunitaria\"}],\"elements\":[{\"id\":\"suelo_pelado\",\"name\":\"Laderas deforestadas\"},{\"id\":\"fauna_encerrada\",\"name\":\"Poblaciones aisladas\"},{\"id\":\"trafico\",\"name\":\"Tráfico de fauna\"}],\"correctAnswers\":{\"suelo_pelado\":\"tala\",\"fauna_encerrada\":\"aislamiento\",\"trafico\":\"caza\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        // 1. MULTIPLE_CHOICE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Misión Guardián Andino")
                                .configurationJson("{\"question\":\"¿Cuál es la estrategia comunitaria más integral para asegurar el futuro del bosque andino?\",\"options\":[\"Restaurar cuencas con plantas nativas, proteger corredores y educar a la comunidad\",\"Reemplazar el bosque nativo con jardines decorativos de plástico\",\"Ignorar las alertas ambientales para no preocupar a la gente\"],\"correctAnswer\":\"Restaurar cuencas con plantas nativas, proteger corredores y educar a la comunidad\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        // 2. TRUE_FALSE
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Palma de Cera y Loro Orejiamarillo")
                                .configurationJson("{\"statement\":\"La protección de la Palma de Cera en Colombia es indispensable para salvar de la extinción al Loro Orejiamarillo.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        // 3. CLASSIFICATION
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acción Guardiana vs Indiferencia")
                                .configurationJson("{\"instructions\":\"Clasifica las actitudes frente al compromiso ambiental:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián\"},{\"id\":\"indiferencia\",\"name\":\"Indiferencia o daño\"}],\"items\":[{\"id\":\"vivero\",\"name\":\"Crear un vivero de robles andinos\"},{\"id\":\"fogata\",\"name\":\"Dejar fogatas encendidas en el bosque\"},{\"id\":\"denuncia\",\"name\":\"Denunciar talas ilegales ante autoridades\"},{\"id\":\"basura\",\"name\":\"Arrojar pilas y plásticos al monte\"}],\"correctAnswers\":{\"vivero\":\"guardian\",\"fogata\":\"indiferencia\",\"denuncia\":\"guardian\",\"basura\":\"indiferencia\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        // 4. ORDERING
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Plan Maestro de Restauración")
                                .configurationJson("{\"instructions\":\"Ordena los pasos del plan técnico para recuperar un área degradada:\",\"steps\":[\"Diagnosticar el suelo y controlar especies invasoras dañinas\",\"Plantar especies nativas pioneras que enriquezcan la tierra\",\"Monitorear el regreso espontáneo de aves polinizadoras y fauna\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        // 5. DRAG_AND_DROP
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Compromisos del Guardián")
                                .configurationJson("{\"instructions\":\"Asigna cada compromiso ecológico al espacio donde tiene su mayor impacto:\",\"targets\":[{\"zone\":\"hogar\",\"name\":\"En el hogar\"},{\"zone\":\"colegio\",\"name\":\"En el colegio o comunidad\"},{\"zone\":\"montana\",\"name\":\"En las reservas naturales\"}],\"elements\":[{\"id\":\"ahorro\",\"name\":\"Cerrar la llave y no verter aceites\"},{\"id\":\"reciclaje\",\"name\":\"Organizar brigadas de separación de residuos\"},{\"id\":\"sendero\",\"name\":\"No dejar huella ni extraer fauna y flora\"}],\"correctAnswers\":{\"ahorro\":\"hogar\",\"reciclaje\":\"colegio\",\"sendero\":\"montana\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }

    private void seedAchievements() {
        record AchievementData(String code, String title, String description, AchievementConditionType conditionType, int conditionValue, String visualKey) {}

        List<AchievementData> list = List.of(
                new AchievementData("FIRST_STEP", "Primer Paso", "Completar la primera actividad educativa.", AchievementConditionType.FIRST_ACTIVITY, 1, "BADGE_FIRST_STEP"),
                new AchievementData("FIRST_LESSON", "Primera Lección", "Completar con éxito tu primera lección ambiental.", AchievementConditionType.FIRST_LESSON, 1, "BADGE_FIRST_LESSON"),
                new AchievementData("WATER_PROTECTOR", "Protector del Agua", "Alcanzar 5 lecciones completadas.", AchievementConditionType.LESSON_COUNT, 5, "BADGE_WATER_PROTECTOR"),
                new AchievementData("SEVEN_DAY_STREAK", "Racha Imparable", "Mantener una racha activa de 7 días consecutivos.", AchievementConditionType.STREAK_REACHED, 7, "BADGE_SEVEN_STREAK"),
                new AchievementData("ECOSYSTEM_EXPLORER", "Explorador de Ecosistemas", "Completar al menos 3 ecosistemas.", AchievementConditionType.ECOSYSTEM_COUNT, 3, "BADGE_ECOSYSTEM_EXPLORER"),
                new AchievementData("XP_MASTER", "Maestro del Conocimiento", "Alcanzar 1000 puntos de XP.", AchievementConditionType.XP_REACHED, 1000, "BADGE_XP_MASTER"),
                new AchievementData("LEVEL_UP_3", "Guardián de Mares", "Alcanzar el Nivel 3 en tu aventura.", AchievementConditionType.LEVEL_REACHED, 3, "BADGE_LEVEL_3")
        );

        for (AchievementData a : list) {
            achievementRepository.findByCode(a.code()).ifPresentOrElse(
                    existing -> {
                        existing.setTitle(a.title());
                        existing.setDescription(a.description());
                        existing.setVisualKey(a.visualKey());
                        achievementRepository.save(existing);
                    },
                    () -> achievementRepository.save(AchievementEntity.builder()
                            .code(a.code())
                            .title(a.title())
                            .description(a.description())
                            .conditionType(a.conditionType())
                            .conditionValue(a.conditionValue())
                            .visualKey(a.visualKey())
                            .isActive(true)
                            .build())
            );
        }
    }

    private void seedRewards(Map<Integer, LevelEntity> levels) {
        record RewardData(String name, String description, RewardType type, String visualKey, int levelNumber) {}

        List<RewardData> rewards = List.of(
                new RewardData("Gorra de Explorador", "Accesorio para tu avatar de campo", RewardType.ACCESSORY, "ITEM_EXPLORER_CAP", 1),
                new RewardData("Marco de Agua Pura", "Marco conmemorativo de corrientes hídricas", RewardType.FRAME, "ITEM_WATER_FRAME", 2),
                new RewardData("Avatar Guardián del Mar", "Avatar protector oceánico", RewardType.AVATAR, "ITEM_REEF_AVATAR", 3),
                new RewardData("Fondo de la Selva Profunda", "Fondo de perfil del Amazonas", RewardType.BACKGROUND, "ITEM_JUNGLE_BG", 4),
                new RewardData("Corona Solar", "Accesorio exclusivo para el defensor del planeta", RewardType.ACCESSORY, "ITEM_SUN_CROWN", 5)
        );

        for (RewardData r : rewards) {
            LevelEntity lvl = levels.getOrDefault(r.levelNumber(), levels.get(1));
            rewardRepository.findByVisualKey(r.visualKey()).ifPresentOrElse(
                    existing -> {
                        existing.setName(r.name());
                        existing.setDescription(r.description());
                        existing.setRequiredLevel(lvl);
                        rewardRepository.save(existing);
                    },
                    () -> rewardRepository.save(RewardEntity.builder()
                            .name(r.name())
                            .description(r.description())
                            .type(r.type())
                            .visualKey(r.visualKey())
                            .requiredLevel(lvl)
                            .isActive(true)
                            .build())
            );
        }
    }
}
