package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;

import java.util.List;

public class BosqueSecoActivitySeeder {

    public static List<ActivityEntity> buildActivities(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Bosque de los Contrastes")
                                .configurationJson("{\"question\":\"¿Cuál es el rasgo climático más marcado del Bosque Seco Tropical en Colombia?\",\"options\":[\"Cae nieve todos los días del año\",\"Pasa meses enteros de sequía sin lluvia y luego tiene temporadas de aguaceros torrenciales\",\"Siempre llueve a la misma hora sin ningún cambio\"],\"correctAnswer\":\"Pasa meses enteros de sequía sin lluvia y luego tiene temporadas de aguaceros torrenciales\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Un Ecosistema en Peligro Crítico")
                                .configurationJson("{\"statement\":\"El Bosque Seco Tropical es uno de los ecosistemas más amenazados de Colombia: queda menos del 8% de su cobertura original.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Fauna del Bosque Seco vs Fauna Polar")
                                .configurationJson("{\"instructions\":\"Clasifica a los animales según habiten el Bosque Seco Tropical o zonas polares heladas:\",\"categories\":[{\"id\":\"bosque_seco\",\"name\":\"Fauna del Bosque Seco Tropical\"},{\"id\":\"polar\",\"name\":\"Fauna de regiones polares\"}],\"items\":[{\"id\":\"iguana_verde\",\"name\":\"Iguana verde tomando el sol en ramas\"},{\"id\":\"titi_cabeciblanco\",\"name\":\"Tití cabeciblanco saltando entre lianas\"},{\"id\":\"pinguino\",\"name\":\"Pingüino emperador del hielo\"},{\"id\":\"oso_polar\",\"name\":\"Oso polar de nieve ártica\"}],\"correctAnswers\":{\"iguana_verde\":\"bosque_seco\",\"titi_cabeciblanco\":\"bosque_seco\",\"pinguino\":\"polar\",\"oso_polar\":\"polar\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Gran Cambio Estacional")
                                .configurationJson("{\"instructions\":\"Ordena la asombrosa transformación del bosque seco con las lluvias:\",\"steps\":[\"Durante la sequía los árboles pierden sus hojas y el bosque luce gris y dormido\",\"Caen los primeros aguaceros torrenciales empapando la tierra sedienta\",\"En pocos días todo el bosque estalla en un verde esmeralda y millones de flores\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Rincones del Bosque Seco")
                                .configurationJson("{\"instructions\":\"Ubica cada especie en su lugar predilecto del bosque seco:\",\"targets\":[{\"zone\":\"copas\",\"name\":\"Copa soleada del dosel\"},{\"zone\":\"ramas_altas\",\"name\":\"Ramas altas y soleadas\"},{\"zone\":\"suelo_pedregoso\",\"name\":\"Suelo cálido pedregoso\"}],\"elements\":[{\"id\":\"titi_copas\",\"name\":\"Tití cabeciblanco buscando frutos dulces\"},{\"id\":\"iguana_ramas\",\"name\":\"Iguana verde calentando su cuerpo al sol\"},{\"id\":\"lagartija_suelo\",\"name\":\"Lagartija veloz buscando insectos\"}],\"correctAnswers\":{\"titi_copas\":\"copas\",\"iguana_ramas\":\"ramas_altas\",\"lagartija_suelo\":\"suelo_pedregoso\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Secreto de Botar las Hojas")
                                .configurationJson("{\"question\":\"¿Por qué muchos árboles del bosque seco dejan caer todas sus hojas durante el verano seco?\",\"options\":[\"Porque les da pereza mantenerlas\",\"Para evitar perder agua mediante la evaporación y sellar su humedad interna\",\"Porque les cae nieve pesada encima\"],\"correctAnswer\":\"Para evitar perder agua mediante la evaporación y sellar su humedad interna\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Troncos Almacenadores")
                                .configurationJson("{\"statement\":\"Árboles del bosque seco como la ceiba bonga o el barrigón acumulan litros de agua dentro de sus gruesos troncos esponjosos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Adaptaciones a la Sequía vs Plantas Acuáticas")
                                .configurationJson("{\"instructions\":\"Distingue las adaptaciones vegetales para ahorrar agua de las plantas que viven en agua abundante:\",\"categories\":[{\"id\":\"ahorro\",\"name\":\"Adaptaciones al bosque seco\"},{\"id\":\"humedad\",\"name\":\"Plantas de agua abundante\"}],\"items\":[{\"id\":\"espinas_cactus\",\"name\":\"Espinas en lugar de hojas para no transpirar\"},{\"id\":\"corteza_verde\",\"name\":\"Tronco verde que hace fotosíntesis sin hojas\"},{\"id\":\"lirio_agua\",\"name\":\"Hojas gigantes de lirio acuático flotante\"},{\"id\":\"musgo_charca\",\"name\":\"Musgo que solo vive empapado de agua\"}],\"correctAnswers\":{\"espinas_cactus\":\"ahorro\",\"corteza_verde\":\"ahorro\",\"lirio_agua\":\"humedad\",\"musgo_charca\":\"humedad\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("La Estrategia del Árbol Caducifolio")
                                .configurationJson("{\"instructions\":\"Organiza la respuesta de un árbol del bosque seco ante la sequía:\",\"steps\":[\"El sol del verano calienta intensamente y el suelo pierde toda humedad\",\"El árbol bota su follaje protegiendo la savia en su tronco grueso\",\"Al caer las primeras lluvias brotan miles de hojas y flores en tiempo récord\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Especialistas en Ahorrar Agua")
                                .configurationJson("{\"instructions\":\"Asigna cada especie vegetal a su ingeniosa estrategia de supervivencia:\",\"targets\":[{\"zone\":\"cactus\",\"name\":\"Cactus candelabro\"},{\"zone\":\"guayacan\",\"name\":\"Guayacán amarillo\"},{\"zone\":\"barrigon\",\"name\":\"Árbol barrigón\"}],\"elements\":[{\"id\":\"hojas_espinosas\",\"name\":\"Tallos carnosos con espinas y cutícula cerosa impenetrable\"},{\"id\":\"floracion_masiva\",\"name\":\"Pérdida total de hojas y floración dorada sincrónica\"},{\"id\":\"tanque_agua\",\"name\":\"Tronco hinchado como un tanque gigante de almacenamiento de agua\"}],\"correctAnswers\":{\"hojas_espinosas\":\"cactus\",\"floracion_masiva\":\"guayacan\",\"tanque_agua\":\"barrigon\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Batería Solar Viviente")
                                .configurationJson("{\"question\":\"¿Por qué las iguanas verdes pasan horas inmóviles tomando el sol sobre las ramas altas?\",\"options\":[\"Porque están castigadas sin moverse\",\"Porque son reptiles ectotermos y necesitan el calor solar para regular su temperatura y digerir las hojas\",\"Porque no tienen ojos para ver\"],\"correctAnswer\":\"Porque son reptiles ectotermos y necesitan el calor solar para regular su temperatura y digerir las hojas\",\"correctOption\":1,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Cola Defensiva")
                                .configurationJson("{\"statement\":\"La larga cola de la iguana verde le sirve como timón potente para nadar y como un látigo defensivo frente a depredadores.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Alimento Natural vs Peligros para la Iguana")
                                .configurationJson("{\"instructions\":\"Clasifica los alimentos sanos de la iguana verde frente a las amenazas humanas que sufre:\",\"categories\":[{\"id\":\"alimento\",\"name\":\"Alimentación natural de la iguana\"},{\"id\":\"peligro\",\"name\":\"Amenaza humana destructiva\"}],\"items\":[{\"id\":\"hojas_tiernas\",\"name\":\"Hojas tiernas y brotes de árboles nativos\"},{\"id\":\"flores_amarillas\",\"name\":\"Flores dulces y frutos caídos de temporada\"},{\"id\":\"caza_huevos\",\"name\":\"Caza cruel y extracción ilegal de huevos en cuaresma\"},{\"id\":\"tala_dormideros\",\"name\":\"Tala de los árboles altos donde duerme y toma el sol\"}],\"correctAnswers\":{\"hojas_tiernas\":\"alimento\",\"flores_amarillas\":\"alimento\",\"caza_huevos\":\"peligro\",\"tala_dormideros\":\"peligro\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Día de una Iguana Verde")
                                .configurationJson("{\"instructions\":\"Organiza la rutina diaria de la iguana en el bosque seco:\",\"steps\":[\"Despierta con el cuerpo frío y trepa a la rama más alta para tomar el sol matutino\",\"Una vez caliente su sangre se alimenta con entusiasmo de hojas y flores frescas\",\"Al detectar peligro salta intrépidamente a un río o al follaje para escapar\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Herramientas Biológicas de la Iguana")
                                .configurationJson("{\"instructions\":\"Asigna cada rasgo anatómico de la iguana a su función:\",\"targets\":[{\"zone\":\"cresta_espinas\",\"name\":\"Cresta dorsal de espinas\"},{\"zone\":\"papada_gular\",\"name\":\"Papada gular colgante\"},{\"zone\":\"ojo_parietal\",\"name\":\"Tercer ojo parietal en la cabeza\"}],\"elements\":[{\"id\":\"termo_defensa\",\"name\":\"Ayuda a regular calor corporal y aparentar mayor tamaño ante rivales\"},{\"id\":\"comunicacion_cortejo\",\"name\":\"Señal visual para comunicarse, defender territorio y cortejar parejas\"},{\"id\":\"sensor_sombras\",\"name\":\"Sensor de luz que detecta sombras de aves rapaces sobrevolando en el cielo\"}],\"correctAnswers\":{\"termo_defensa\":\"cresta_espinas\",\"comunicacion_cortejo\":\"papada_gular\",\"sensor_sombras\":\"ojo_parietal\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Los Pozos de la Vida")
                                .configurationJson("{\"question\":\"¿Qué ocurre con los arroyos del bosque seco durante los meses de sequía más intensa?\",\"options\":[\"Se convierten en ríos de chocolate caliente\",\"Se reducen a pozas o charcas aisladas donde toda la fauna acude a beber\",\"Se evaporan al espacio y nunca más regresan\"],\"correctAnswer\":\"Se reducen a pozas o charcas aisladas donde toda la fauna acude a beber\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Suelos Fértiles pero Delicados")
                                .configurationJson("{\"statement\":\"Los suelos del bosque seco son ricos en minerales pero sumamente vulnerables a la erosión si se talan los árboles protectores.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Temporada Seca vs Temporada de Lluvias")
                                .configurationJson("{\"instructions\":\"Clasifica los eventos según ocurran en la sequía o en la temporada de lluvias del bosque seco:\",\"categories\":[{\"id\":\"sequia\",\"name\":\"Temporada de sequía (Verano)\"},{\"id\":\"lluvias\",\"name\":\"Temporada de lluvias (Invierno)\"}],\"items\":[{\"id\":\"suelo_agrietado\",\"name\":\"Suelo reseco y arroyos reducidos a pozas\"},{\"id\":\"hojarasca_crujiente\",\"name\":\"Hojarasca seca crujiente y árboles sin follaje\"},{\"id\":\"reverdecimiento\",\"name\":\"Reverdecimiento masivo y flores en una semana\"},{\"id\":\"renacuajos_charcas\",\"name\":\"Nacimiento de renacuajos e insectos por millones\"}],\"correctAnswers\":{\"suelo_agrietado\":\"sequia\",\"hojarasca_crujiente\":\"sequia\",\"reverdecimiento\":\"lluvias\",\"renacuajos_charcas\":\"lluvias\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Retorno del Agua")
                                .configurationJson("{\"instructions\":\"Organiza el milagro del regreso del agua al bosque seco:\",\"steps\":[\"El bosque resiste meses de calor extremo protegiendo sus semillas en la tierra\",\"Los truenos anuncian aguaceros que empapan el suelo y llenan los arroyos secos\",\"En pocos días la vida explota con insectos, ranas y aves celebrando el verdor\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Guardianes del Agua en el Bosque Seco")
                                .configurationJson("{\"instructions\":\"Ubica cada elemento protector del agua en su rol ecosistémico:\",\"targets\":[{\"zone\":\"poza_residual\",\"name\":\"Poza residual en la sequía\"},{\"zone\":\"ronda_arboles\",\"name\":\"Ronda de árboles de galería\"},{\"zone\":\"colchon_hojarasca\",\"name\":\"Capa de hojarasca sobre la tierra\"}],\"elements\":[{\"id\":\"bebedero_vital\",\"name\":\"Oasis donde beben venados, tigrillos y aves sedientas\"},{\"id\":\"sombra_fresca\",\"name\":\"Sombra continua que evita la evaporación rápida del cauce del arroyo\"},{\"id\":\"esponja_humedad\",\"name\":\"Colchón orgánico que absorbe la lluvia violenta evitando que arrastre el suelo\"}],\"correctAnswers\":{\"bebedero_vital\":\"poza_residual\",\"sombra_fresca\":\"ronda_arboles\",\"esponja_humedad\":\"colchon_hojarasca\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Tití Endémico de Colombia")
                                .configurationJson("{\"question\":\"¿Por qué el Tití Cabeciblanco (Saguinus oedipus) es un tesoro natural único de Colombia?\",\"options\":[\"Porque vive en todos los continentes del mundo\",\"Porque es endémico de Colombia (solo vive en el Caribe colombiano) y dispersa semillas de más de 60 árboles nativos\",\"Porque sabe hablar tres idiomas diferentes\"],\"correctAnswer\":\"Porque es endémico de Colombia (solo vive en el Caribe colombiano) y dispersa semillas de más de 60 árboles nativos\",\"correctOption\":1,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Ocelote Cazador")
                                .configurationJson("{\"statement\":\"El ocelote o tigrillo es un felino sigiloso que regula las poblaciones de roedores y reptiles en el bosque seco manteniendo el equilibrio.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Red Trófica del Bosque Seco")
                                .configurationJson("{\"instructions\":\"Clasifica a los seres según sean herbívoros-frugívoros o depredadores carnívoros:\",\"categories\":[{\"id\":\"herbi_frugi\",\"name\":\"Herbívoros y Frugívoros\"},{\"id\":\"carnivoros\",\"name\":\"Depredadores del bosque seco\"}],\"items\":[{\"id\":\"titi_cresta\",\"name\":\"Tití cabeciblanco de cresta blanca algodonosa\"},{\"id\":\"venado_caribe\",\"name\":\"Venado coliblanco comelón de brotes\"},{\"id\":\"ocelote_manchas\",\"name\":\"Ocelote o tigrillo manchado\"},{\"id\":\"cascabel_tropical\",\"name\":\"Serpiente cascabel neotropical cazadora de roedores\"}],\"correctAnswers\":{\"titi_cresta\":\"herbi_frugi\",\"venado_caribe\":\"herbi_frugi\",\"ocelote_manchas\":\"carnivoros\",\"cascabel_tropical\":\"carnivoros\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Cadena de Vida en el Bosque Seco")
                                .configurationJson("{\"instructions\":\"Organiza el flujo de alimento en el bosque seco:\",\"steps\":[\"Árboles nativos de guayacán y ceiba producen abundantes flores y frutos dulces\",\"El tití cabeciblanco se alimenta de las frutas y dispersa las semillas al saltar\",\"El ocelote acecha en ramas bajas manteniendo el equilibrio natural de la fauna\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Especialistas del Bosque Seco")
                                .configurationJson("{\"instructions\":\"Ubica cada especie en su rol ecológico fundamental:\",\"targets\":[{\"zone\":\"polinizador_noche\",\"name\":\"Polinizador nocturno\"},{\"zone\":\"sembrador_dia\",\"name\":\"Sembrador diurno del dosel\"},{\"zone\":\"controlador\",\"name\":\"Controlador biológico de plagas\"}],\"elements\":[{\"id\":\"murcielago_trompa\",\"name\":\"Murciélago trompudo que poliniza cactus y flores nocturnas\"},{\"id\":\"titi_sembrador\",\"name\":\"Tití cabeciblanco dispersor de semillas de árboles maderables\"},{\"id\":\"serpiente_coral\",\"name\":\"Serpiente coral que controla roedores en la hojarasca\"}],\"correctAnswers\":{\"murcielago_trompa\":\"polinizador_noche\",\"titi_sembrador\":\"sembrador_dia\",\"serpiente_coral\":\"controlador\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Semillas que Vuelan con el Viento")
                                .configurationJson("{\"question\":\"¿Qué genial adaptación tienen semillas de árboles como el Guayacán o la Ceiba para viajar con el viento seco?\",\"options\":[\"Tienen alas membranosas o plumas de algodón que flotan con las brisas cálidas\",\"Compran boletos de avión en el aeropuerto\",\"Caminan con pequeñas patas peludas\"],\"correctAnswer\":\"Tienen alas membranosas o plumas de algodón que flotan con las brisas cálidas\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Sueño de la Semilla")
                                .configurationJson("{\"statement\":\"Muchas semillas del bosque seco tienen cáscaras tan duras que pueden dormir años en la tierra esperando que llegue una buena lluvia para germinar.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Semillas Aladas vs Semillas con Gancho")
                                .configurationJson("{\"instructions\":\"Clasifica los mecanismos de dispersión de semillas del bosque seco:\",\"categories\":[{\"id\":\"viento\",\"name\":\"Dispersión por viento (Anemocoria)\"},{\"id\":\"animales\",\"name\":\"Dispersión por animales (Zoocoria)\"}],\"items\":[{\"id\":\"semilla_guayacan\",\"name\":\"Semilla con alas helicóptero del guayacán\"},{\"id\":\"semilla_ceiba\",\"name\":\"Semillas envueltas en algodón volador de ceiba\"},{\"id\":\"semilla_fruta_titi\",\"name\":\"Semillas de frutos carnosos comidas por titís\"},{\"id\":\"semilla_espinas\",\"name\":\"Semillas con espinas pegajosas que se adhieren al pelaje\"}],\"correctAnswers\":{\"semilla_guayacan\":\"viento\",\"semilla_ceiba\":\"viento\",\"semilla_fruta_titi\":\"animales\",\"semilla_espinas\":\"animales\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("La Aventura de una Semilla Alada")
                                .configurationJson("{\"instructions\":\"Ordena cómo nace un nuevo guayacán amarillo:\",\"steps\":[\"El fruto seco estalla al mediodía liberando cientos de semillas aladas\",\"El viento caliente las hace girar en espiral llevándolas lejos del árbol padre\",\"Al caer la lluvia la semilla despierta y echa su primera raíz firme en el suelo fértil\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Aliados de la Regeneración")
                                .configurationJson("{\"instructions\":\"Asigna cada fuerza de la naturaleza a su aporte en la siembra del bosque:\",\"targets\":[{\"zone\":\"viento_verano\",\"name\":\"El viento del verano\"},{\"zone\":\"aves_frugivoras\",\"name\":\"Aves y monos frugívoros\"},{\"zone\":\"hormigas_arrieras\",\"name\":\"Hormigas arrieras trabajadoras\"}],\"elements\":[{\"id\":\"impulso_aereo\",\"name\":\"Transportar a cientos de metros semillas livianas con alas de papel\"},{\"id\":\"abono_semillas\",\"name\":\"Depositar semillas con abono natural en claros de bosque\"},{\"id\":\"siembra_subterranea\",\"name\":\"Llevar semillas a nidos subterráneos fértiles y húmedos\"}],\"correctAnswers\":{\"impulso_aereo\":\"viento_verano\",\"abono_semillas\":\"aves_frugivoras\",\"siembra_subterranea\":\"hormigas_arrieras\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La Pérdida del Bosque Seco")
                                .configurationJson("{\"question\":\"¿Por qué el bosque seco tropical en Colombia fue destruido en más del 92% a lo largo de los años?\",\"options\":[\"Porque los extraterrestres se llevaron los árboles\",\"Porque sus tierras planas y fértiles fueron taladas masivamente para ganadería y agricultura sin dejar parches de bosque\",\"Porque a los animales no les gustaba vivir allí\"],\"correctAnswer\":\"Porque sus tierras planas y fértiles fueron taladas masivamente para ganadería y agricultura sin dejar parches de bosque\",\"correctOption\":1,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Tráfico Ilegal del Tití")
                                .configurationJson("{\"statement\":\"Capturar y vender titís cabeciblancos como mascotas es un delito ambiental grave que pone a la especie al borde de la extinción.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Degradación del Bosque Seco vs Restauración")
                                .configurationJson("{\"instructions\":\"Clasifica los impactos destructivos frente a las estrategias de restauración ecológica:\",\"categories\":[{\"id\":\"degradacion\",\"name\":\"Causas de degradación\"},{\"id\":\"restauracion\",\"name\":\"Estrategias de recuperación\"}],\"items\":[{\"id\":\"quemas_pastos\",\"name\":\"Incendios provocados para limpiar rastrojos de potrero\"},{\"id\":\"fragmentacion\",\"name\":\"Carreteras que dividen el bosque en parches aislados\"},{\"id\":\"cercas_vivas\",\"name\":\"Cercas vivas con árboles nativos conectando fincas\"},{\"id\":\"reservas_sociedad\",\"name\":\"Reservas naturales de la sociedad civil con pactos comunitarios\"}],\"correctAnswers\":{\"quemas_pastos\":\"degradacion\",\"fragmentacion\":\"degradacion\",\"cercas_vivas\":\"restauracion\",\"reservas_sociedad\":\"restauracion\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Efecto de la Fragmentación del Bosque")
                                .configurationJson("{\"instructions\":\"Ordena cómo la fragmentación afecta a la fauna del bosque seco:\",\"steps\":[\"Una carretera o potrero divide el bosque nativo en pequeños parches aislados\",\"Los titís y perezosos no pueden cruzar y quedan encerrados sin suficiente comida\",\"Se instalan pasos aéreos de fauna con cuerdas permitiendo que vuelvan a conectarse sin peligro\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Alianzas por el Bosque Seco")
                                .configurationJson("{\"instructions\":\"Ubica cada solución en el espacio rural donde tiene su mayor efectividad:\",\"targets\":[{\"zone\":\"potreros\",\"name\":\"En los potreros ganaderos\"},{\"zone\":\"vias_rurales\",\"name\":\"En las vías y carreteras\"},{\"zone\":\"comunidad_local\",\"name\":\"En las comunidades del Caribe\"}],\"elements\":[{\"id\":\"silvopastoreo\",\"name\":\"Sistemas silvopastoriles con árboles de guayacán dando sombra al ganado\"},{\"id\":\"puentes_fauna\",\"name\":\"Pasos aéreos de fauna con cuerdas entre copas de árboles\"},{\"id\":\"orgullo_titi\",\"name\":\"Festivales escolares y muñecos de tití hechos con plástico reciclado\"}],\"correctAnswers\":{\"silvopastoreo\":\"potreros\",\"puentes_fauna\":\"vias_rurales\",\"orgullo_titi\":\"comunidad_local\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Proyecto Tití de Colombia")
                                .configurationJson("{\"question\":\"¿Cómo trabaja la iniciativa colombiana 'Proyecto Tití' para salvar el bosque seco?\",\"options\":[\"Tala más árboles para hacer juguetes de plástico\",\"Trabaja con comunidades creando artesanías ecológicas, sembrando bosques y educando a los niños para proteger al tití\",\"Encierra a todos los animales en jaulas metálicas\"],\"correctAnswer\":\"Trabaja con comunidades creando artesanías ecológicas, sembrando bosques y educando a los niños para proteger al tití\",\"correctOption\":1,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Los Árboles Nativos son la Clave")
                                .configurationJson("{\"statement\":\"Sembrar árboles nativos como el guayacán, la ceiba y el caracolí es indispensable para recuperar el bosque seco en Colombia.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acciones del Guardián del Bosque Seco vs Conductas Nocivas")
                                .configurationJson("{\"instructions\":\"Distingue las actitudes de un auténtico Guardián frente a conductas destructivas:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián del Bosque Seco\"},{\"id\":\"destructiva\",\"name\":\"Conducta destructiva\"}],\"items\":[{\"id\":\"no_comprar_titi\",\"name\":\"Rechazar la compra de titís, loros o iguanas como mascotas\"},{\"id\":\"sembrar_guayacan\",\"name\":\"Sembrar árboles nativos adaptados al clima local en rondas de arroyos\"},{\"id\":\"comprar_huevos\",\"name\":\"Comprar huevos de iguana silvestre en carreteras\"},{\"id\":\"prender_fuego\",\"name\":\"Hacer quemas de rastrojo en pleno mediodía caluroso\"}],\"correctAnswers\":{\"no_comprar_titi\":\"guardian\",\"sembrar_guayacan\":\"guardian\",\"comprar_huevos\":\"destructiva\",\"prender_fuego\":\"destructiva\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Plan Maestro de Siembra de Bosque Seco")
                                .configurationJson("{\"instructions\":\"Ordena los pasos para reforestar exitosamente una finca en el bosque seco:\",\"steps\":[\"Recolectar semillas maduras de árboles nativos locales adaptados a la sequía\",\"Criar las plántulas en vivero hasta que tengan tallos fuertes y raíces profundas\",\"Sembrarlas exactamente al inicio de la temporada de lluvias para que enraícen con fuerza\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("El Legado del Guardián del Bosque Seco")
                                .configurationJson("{\"instructions\":\"Ubica cada compromiso en el objetivo de conservación correspondiente:\",\"targets\":[{\"zone\":\"fauna_endemica\",\"name\":\"Protección de fauna única\"},{\"zone\":\"conectividad\",\"name\":\"Conectividad de corredores\"},{\"zone\":\"educacion\",\"name\":\"Educación y orgullo ambiental\"}],\"elements\":[{\"id\":\"cero_trafico_titi\",\"name\":\"Cero tráfico ilegal de titís cabeciblancos e iguanas verdes\"},{\"id\":\"conectar_parches\",\"name\":\"Unir parches de bosque aislados mediante cercas vivas y bosques de galería\"},{\"id\":\"ninos_lideres\",\"name\":\"Niños guardianes que enseñan en sus familias a no provocar incendios forestales\"}],\"correctAnswers\":{\"cero_trafico_titi\":\"fauna_endemica\",\"conectar_parches\":\"conectividad\",\"ninos_lideres\":\"educacion\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }
}
