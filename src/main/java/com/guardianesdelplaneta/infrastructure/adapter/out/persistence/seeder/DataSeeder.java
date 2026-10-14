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
                        "Biodiversidad de alta montaña y flora nativa. Criterio de desbloqueo: Nivel 1 (Ecosistema inicial).",
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
                        "Contaminación marina y microplásticos. Criterio de desbloqueo: Nivel 2.",
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
                        "Deforestación y pulmón del planeta. Criterio de desbloqueo: Nivel 3.",
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
                new EcosystemData("MANGLAR", "Manglar", "Protección costera y cunas de vida acuática.", 3, "CANGREJO", List.of()),
                new EcosystemData("BOSQUE_SECO_TROPICAL", "Bosque Seco Tropical", "Adaptación a la sequía y especies endémicas.", 4, "IGUANA_VERDE", List.of()),
                new EcosystemData("LLANOS_SABANA", "Llanos y Sabana", "Humedales estacionales y fauna de pradera.", 5, "CHIGUIRO", List.of())
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

            if (ecoEntity.getRequiredLevel() == null) {
                ecoEntity.setRequiredLevel(requiredLevel);
                ecoEntity.setMascotKey(ecoData.mascotKey());
                ecosystemRepository.save(ecoEntity);
            }

            // Solo sembrar lecciones si existen en la definición (por ahora Bosque Andino)
            if (!ecoData.lessons().isEmpty()) {
                List<LessonEntity> currentLessons = lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(ecoEntity.getId());
                if (currentLessons.size() != ecoData.lessons().size()) {
                    for (LessonInfo lessonInfo : ecoData.lessons()) {
                        boolean exists = currentLessons.stream().anyMatch(l -> l.getOrderIndex().equals(lessonInfo.orderIndex()));
                        if (!exists) {
                            LessonEntity lesson = lessonRepository.save(LessonEntity.builder()
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
            if (!achievementRepository.existsByCode(a.code())) {
                achievementRepository.save(AchievementEntity.builder()
                        .code(a.code())
                        .title(a.title())
                        .description(a.description())
                        .conditionType(a.conditionType())
                        .conditionValue(a.conditionValue())
                        .visualKey(a.visualKey())
                        .isActive(true)
                        .build());
            }
        }
    }

    private void seedRewards(Map<Integer, LevelEntity> levels) {
        if (rewardRepository.count() == 0) {
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
                rewardRepository.save(RewardEntity.builder()
                        .name(r.name())
                        .description(r.description())
                        .type(r.type())
                        .visualKey(r.visualKey())
                        .requiredLevel(lvl)
                        .isActive(true)
                        .build());
            }
        }
    }
}
