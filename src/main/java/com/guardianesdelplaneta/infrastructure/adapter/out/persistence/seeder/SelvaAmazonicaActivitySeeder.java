package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;

import java.util.List;

public class SelvaAmazonicaActivitySeeder {

    public static List<ActivityEntity> buildActivities(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Corazón Verde del Planeta")
                                .configurationJson("{\"question\":\"¿Por qué la Amazonía colombiana es tan vital para el planeta?\",\"options\":[\"Porque es una fábrica de cemento gigante\",\"Porque regula el clima, genera oxígeno, absorbe dióxido de carbono y produce ríos voladores\",\"Porque no tiene ningún ser vivo viviendo en ella\"],\"correctAnswer\":\"Porque regula el clima, genera oxígeno, absorbe dióxido de carbono y produce ríos voladores\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Delfín Rosado")
                                .configurationJson("{\"statement\":\"En los ríos de la Amazonía colombiana habita el delfín rosado, un delfín amistoso que vive en agua dulce.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Selva Amazónica vs Ciudad")
                                .configurationJson("{\"instructions\":\"Clasifica los elementos según sean parte de la Amazonía o del entorno urbano:\",\"categories\":[{\"id\":\"amazonia\",\"name\":\"Elementos de la Selva Amazónica\"},{\"id\":\"ciudad\",\"name\":\"Elementos urbanos\"}],\"items\":[{\"id\":\"delfin\",\"name\":\"Delfín rosado nadando en el río\"},{\"id\":\"victoria_regia\",\"name\":\"Lirio flotante gigante Victoria Regia\"},{\"id\":\"semaforo\",\"name\":\"Semáforo de avenida\"},{\"id\":\"rascacielos\",\"name\":\"Edificio rascacielos de concreto\"}],\"correctAnswers\":{\"delfin\":\"amazonia\",\"victoria_regia\":\"amazonia\",\"semaforo\":\"ciudad\",\"rascacielos\":\"ciudad\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Amanecer en la Gran Selva")
                                .configurationJson("{\"instructions\":\"Ordena cómo despierta la selva amazónica:\",\"steps\":[\"La bruma matutina flota sobre las copas de los árboles gigantes\",\"El coro potente de los monos aulladores resuena por los ríos\",\"Los primeros rayos solares iluminan a las garzas pescando en las orillas\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Habitantes de la Amazonía")
                                .configurationJson("{\"instructions\":\"Ubica cada especie en el piso ecológico donde vive:\",\"targets\":[{\"zone\":\"rio\",\"name\":\"Río Amazonas y caños\"},{\"zone\":\"copas\",\"name\":\"Copas de los árboles (Dosel)\"},{\"zone\":\"suelo\",\"name\":\"Suelo selvático húmedo\"}],\"elements\":[{\"id\":\"delfin_rio\",\"name\":\"Delfín rosado nadador\"},{\"id\":\"guacamaya\",\"name\":\"Guacamaya bandera en vuelo\"},{\"id\":\"jaguar_suelo\",\"name\":\"Jaguar acechando con sigilo\"}],\"correctAnswers\":{\"delfin_rio\":\"rio\",\"guacamaya\":\"copas\",\"jaguar_suelo\":\"suelo\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Los Gigantes del Dosel")
                                .configurationJson("{\"question\":\"¿Qué altura pueden alcanzar árboles colosales como la Ceiba en la selva amazónica?\",\"options\":[\"Apenas un metro de altura\",\"Más de 40 a 50 metros de altura, sobrepasando a los demás árboles\",\"La misma altura que una hierba de jardín\"],\"correctAnswer\":\"Más de 40 a 50 metros de altura, sobrepasando a los demás árboles\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Raíces como Contrafuertes")
                                .configurationJson("{\"statement\":\"Los árboles amazónicos tienen raíces tabulares gigantes llamadas 'bambas' que actúan como contrafuertes para no caerse.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Pisos de la Selva: Dosel vs Sotobosque")
                                .configurationJson("{\"instructions\":\"Clasifica a los seres según vivan en el dosel iluminado o en la sombra del sotobosque:\",\"categories\":[{\"id\":\"dosel\",\"name\":\"Dosel y árboles emergentes\"},{\"id\":\"sotobosque\",\"name\":\"Sotobosque y suelo sombreado\"}],\"items\":[{\"id\":\"harpia\",\"name\":\"Águila harpía anidando en la copa\"},{\"id\":\"bromelia_aerea\",\"name\":\"Bromelias epífitas colgantes\"},{\"id\":\"rana_dardo\",\"name\":\"Rana venenosa dardo dorada en hojarasca\"},{\"id\":\"hongo_luz\",\"name\":\"Hongos bioluminiscentes en troncos caídos\"}],\"correctAnswers\":{\"harpia\":\"dosel\",\"bromelia_aerea\":\"dosel\",\"rana_dardo\":\"sotobosque\",\"hongo_luz\":\"sotobosque\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Crecimiento de una Ceiba Milenaria")
                                .configurationJson("{\"instructions\":\"Organiza la vida de una ceiba gigante en la Amazonía:\",\"steps\":[\"Una semilla diminuta cae en un claro de luz del bosque\",\"Crece a gran velocidad buscando los rayos del sol\",\"Desarrolla una copa colosal que alberga a cientos de especies epífitas y animales\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Estratos de la Selva Húmeda")
                                .configurationJson("{\"instructions\":\"Coloca a cada habitante en su estrato de la selva amazónica:\",\"targets\":[{\"zone\":\"emergente\",\"name\":\"Copa emergente soleada\"},{\"zone\":\"medio\",\"name\":\"Dosel medio frondoso\"},{\"zone\":\"tierra\",\"name\":\"Piso de tierra y raíces\"}],\"elements\":[{\"id\":\"aguila\",\"name\":\"Águila harpía oteando la selva\"},{\"id\":\"mono_churuco\",\"name\":\"Mono churuco saltador\"},{\"id\":\"armadillo_gigante\",\"name\":\"Armadillo gigante excavador de madrigueras\"}],\"correctAnswers\":{\"aguila\":\"emergente\",\"mono_churuco\":\"medio\",\"armadillo_gigante\":\"tierra\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Secreto de la Anaconda")
                                .configurationJson("{\"question\":\"¿Por qué la anaconda verde tiene los ojos y la nariz en la parte superior de la cabeza?\",\"options\":[\"Para mirar las estrellas mientras duerme\",\"Para poder respirar y vigilar presas manteniendo su pesado cuerpo completamente sumergido\",\"Para nadar hacia atrás sin tropezar\"],\"correctAnswer\":\"Para poder respirar y vigilar presas manteniendo su pesado cuerpo completamente sumergido\",\"correctOption\":1,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Caza y Respiración de la Anaconda")
                                .configurationJson("{\"statement\":\"La anaconda verde no tiene veneno; es una constrictora musculosa y una nadadora extraordinaria en caños y pantanos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Reptiles Amazónicos vs Peces de Río")
                                .configurationJson("{\"instructions\":\"Distingue los grandes reptiles de los peces nativos de la Amazonía:\",\"categories\":[{\"id\":\"reptiles\",\"name\":\"Grandes reptiles amazónicos\"},{\"id\":\"peces\",\"name\":\"Peces de la cuenca amazónica\"}],\"items\":[{\"id\":\"anaconda\",\"name\":\"Anaconda verde gigante\"},{\"id\":\"caiman_negro\",\"name\":\"Caimán negro del río Caquetá\"},{\"id\":\"pirarucu\",\"name\":\"Pirarucú con escamas rojizas\"},{\"id\":\"pirana\",\"name\":\"Piraña de vientre rojo\"}],\"correctAnswers\":{\"anaconda\":\"reptiles\",\"caiman_negro\":\"reptiles\",\"pirarucu\":\"peces\",\"pirana\":\"peces\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Acecho en el Río")
                                .configurationJson("{\"instructions\":\"Ordena cómo caza la anaconda verde en el agua turbia:\",\"steps\":[\"Permanece inmóvil bajo plantas flotantes camuflada con su piel verde oscura\",\"Asoma únicamente sus orificios nasales para respirar sin ser descubierta\",\"Se impulsa con su potente cuerpo ondulante atrapando a su presa en el agua\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Adaptaciones de la Anaconda Verde")
                                .configurationJson("{\"instructions\":\"Asigna cada rasgo anatómico de la anaconda a su ventaja adaptativa:\",\"targets\":[{\"zone\":\"fosas_superiores\",\"name\":\"Ojos y fosas arriba\"},{\"zone\":\"piel_moteada\",\"name\":\"Piel moteada de verde y negro\"},{\"zone\":\"mandibula\",\"name\":\"Mandíbula elástica y flexible\"}],\"elements\":[{\"id\":\"acecho_sumergido\",\"name\":\"Vigilancia y respiración discreta bajo el agua\"},{\"id\":\"camuflaje_pantano\",\"name\":\"Ocultamiento perfecto entre lodo y hojas sumergidas\"},{\"id\":\"tragar_presas\",\"name\":\"Poder tragar presas enteras más anchas que su cabeza\"}],\"correctAnswers\":{\"acecho_sumergido\":\"fosas_superiores\",\"camuflaje_pantano\":\"piel_moteada\",\"tragar_presas\":\"mandibula\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Los Ríos Voladores")
                                .configurationJson("{\"question\":\"¿Qué son los 'ríos voladores' de la selva amazónica?\",\"options\":[\"Ríos llenos de peces con alas que vuelan\",\"Gigantescas corrientes de vapor de agua bombeadas por los árboles que viajan por el cielo llevando lluvias a Colombia\",\"Túneles de viento seco que no tienen agua\"],\"correctAnswer\":\"Gigantescas corrientes de vapor de agua bombeadas por los árboles que viajan por el cielo llevando lluvias a Colombia\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Bomba de Agua del Árbol")
                                .configurationJson("{\"statement\":\"Un solo árbol grande de la selva amazónica puede transpirar más de 1.000 litros de agua en un solo día a la atmósfera.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Selva Inundada vs Selva de Tierra Firme")
                                .configurationJson("{\"instructions\":\"Clasifica los ecosistemas según sean selvas inundables o selvas de tierra firme:\",\"categories\":[{\"id\":\"inundada\",\"name\":\"Selva inundable (Várzea e Igapó)\"},{\"id\":\"firme\",\"name\":\"Selva de tierra firme\"}],\"items\":[{\"id\":\"victoria_flotante\",\"name\":\"Victoria amazónica de grandes hojas flotantes\"},{\"id\":\"tambaqui\",\"name\":\"Pez gamitana comiendo frutos bajo el agua\"},{\"id\":\"castano\",\"name\":\"Árbol de castaña del Brasil en colinas secas\"},{\"id\":\"paujil\",\"name\":\"Paujil tuberoso caminando en tierra firme\"}],\"correctAnswers\":{\"victoria_flotante\":\"inundada\",\"tambaqui\":\"inundada\",\"castano\":\"firme\",\"paujil\":\"firme\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Ciclo de los Ríos Voladores")
                                .configurationJson("{\"instructions\":\"Organiza cómo la selva amazónica crea lluvias para toda Colombia:\",\"steps\":[\"Millones de árboles absorben agua del suelo amazónico con sus raíces profundas\",\"Las hojas transpiran vapor de agua formando nubes colosales sobre la copa de la selva\",\"Los vientos empujan los ríos voladores hacia la cordillera de los Andes donde llueve alimentando los páramos\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Cuerpos de Agua Amazónicos")
                                .configurationJson("{\"instructions\":\"Ubica cada especie en su hábitat acuático amazónico:\",\"targets\":[{\"zone\":\"rio_blanco\",\"name\":\"Río Amazonas (Aguas blancas con sedimento)\"},{\"zone\":\"rio_negro\",\"name\":\"Caños de aguas negras (Igapó)\"},{\"zone\":\"banco_playa\",\"name\":\"Playas y bancos arenosos estacionales\"}],\"elements\":[{\"id\":\"pirarucu_rio\",\"name\":\"Pirarucú de hasta 200 kg respirando en la superficie\"},{\"id\":\"manati_negro\",\"name\":\"Manatí amazónico pastando en aguas oscuras y quietas\"},{\"id\":\"charapas\",\"name\":\"Tortugas charapas desovando en bancos de arena\"}],\"correctAnswers\":{\"pirarucu_rio\":\"rio_blanco\",\"manati_negro\":\"rio_negro\",\"charapas\":\"banco_playa\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Rey de la Selva Americana")
                                .configurationJson("{\"question\":\"¿Quién es el depredador terrestre más grande y respetado de la selva amazónica colombiana?\",\"options\":[\"El Jaguar (Panthera onca)\",\"El oso polar blanco\",\"El canguro saltador\"],\"correctAnswer\":\"El Jaguar (Panthera onca)\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Pirarucú y el Aire")
                                .configurationJson("{\"statement\":\"El pez pirarucú tiene una vejiga natatoria modificada como pulmón que le permite respirar aire en aguas con poco oxígeno.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Cadena Trófica Amazónica")
                                .configurationJson("{\"instructions\":\"Clasifica los organismos según sean herbívoros-frugívoros o carnívoros de cúspide:\",\"categories\":[{\"id\":\"herbivoros\",\"name\":\"Herbívoros y Frugívoros\"},{\"id\":\"carnivoros\",\"name\":\"Carnívoros y Superdepredadores\"}],\"items\":[{\"id\":\"tapir_danta\",\"name\":\"Danta o tapir amazónico\"},{\"id\":\"mono_aullador\",\"name\":\"Mono aullador comedor de hojas\"},{\"id\":\"jaguar_fresco\",\"name\":\"Jaguar acechador de riberas\"},{\"id\":\"aguila_harpia\",\"name\":\"Águila harpía cazadora de monos\"}],\"correctAnswers\":{\"tapir_danta\":\"herbivoros\",\"mono_aullador\":\"herbivoros\",\"jaguar_fresco\":\"carnivoros\",\"aguila_harpia\":\"carnivoros\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Vida en la Selva Inundada")
                                .configurationJson("{\"instructions\":\"Organiza el flujo alimenticio en la selva inundada amazónica:\",\"steps\":[\"Árboles cargados de frutas dejan caer sus semillas sobre el agua\",\"Peces tambaquíes comen las semillas y las dispersan nadando por el bosque inundado\",\"El caimán negro regula las poblaciones de peces en las lagunas interiores\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Simbiosis en la Selva")
                                .configurationJson("{\"instructions\":\"Relaciona cada organismo con la alianza ecológica que mantiene viva a la selva:\",\"targets\":[{\"zone\":\"vara_santa\",\"name\":\"Árbol de vara santa\"},{\"zone\":\"hormigas\",\"name\":\"Hormigas aztecas\"},{\"zone\":\"hongos\",\"name\":\"Hongos micorrízicos\"}],\"elements\":[{\"id\":\"casa_arbol\",\"name\":\"Brinda tallos huecos y néctar dulce para anidar\"},{\"id\":\"defensa_arbol\",\"name\":\"Defienden ferozmente al árbol contra orugas y trepadoras\"},{\"id\":\"red_nutrientes\",\"name\":\"Conectan raíces bajo tierra distribuyendo minerales y agua\"}],\"correctAnswers\":{\"casa_arbol\":\"vara_santa\",\"defensa_arbol\":\"hormigas\",\"red_nutrientes\":\"hongos\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Sembradores de la Selva")
                                .configurationJson("{\"question\":\"¿Qué ocurriría en la selva amazónica si desaparecieran los monos churucos y los tapires?\",\"options\":[\"Los árboles crecerían automáticamente de plástico\",\"Muchos árboles de semillas grandes no podrían propagarse y la selva perdería su densidad y diversidad\",\"No pasa nada porque los árboles no necesitan semillas\"],\"correctAnswer\":\"Muchos árboles de semillas grandes no podrían propagarse y la selva perdería su densidad y diversidad\",\"correctOption\":1,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Los Murciélagos Sembradores")
                                .configurationJson("{\"statement\":\"Los murciélagos frugívoros de la selva dispersan millones de semillas cada noche regenerando claros deforestados.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Dispersores de Vida vs Motores de Destrucción")
                                .configurationJson("{\"instructions\":\"Separa a los sembradores naturales de los agentes que destruyen el bosque:\",\"categories\":[{\"id\":\"sembradores\",\"name\":\"Sembradores naturales\"},{\"id\":\"destructores\",\"name\":\"Causantes de deforestación\"}],\"items\":[{\"id\":\"tucan_toco\",\"name\":\"Tucán comelón y dispersor de frutas\"},{\"id\":\"tapir_viajero\",\"name\":\"Tapir amazónico que camina kilómetros\"},{\"id\":\"motosierra\",\"name\":\"Motosierras de tala ilegal de cedro\"},{\"id\":\"buldozer\",\"name\":\"Retroexcavadoras de minería que contaminan ríos\"}],\"correctAnswers\":{\"tucan_toco\":\"sembradores\",\"tapir_viajero\":\"sembradores\",\"motosierra\":\"destructores\",\"buldozer\":\"destructores\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Milagro de la Dispersión")
                                .configurationJson("{\"instructions\":\"Ordena cómo nace un nuevo árbol amazónico gracias a la fauna:\",\"steps\":[\"El mono churuco disfruta de frutos maduros en la copa más alta\",\"Se desplaza por el dosel y deposita las semillas ilesas en el suelo fértil\",\"La semilla germina bajo el calor y humedad formando un nuevo gigante selvático\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Medios de Dispersión en la Selva")
                                .configurationJson("{\"instructions\":\"Asigna cada especie a su vía principal de dispersión de semillas:\",\"targets\":[{\"zone\":\"aire\",\"name\":\"Dispersión aérea en el vuelo\"},{\"zone\":\"tierra\",\"name\":\"Dispersión terrestre a larga distancia\"},{\"zone\":\"agua\",\"name\":\"Dispersión acuática en temporada de creciente\"}],\"elements\":[{\"id\":\"guacamaya_semillas\",\"name\":\"Guacamaya roja volando entre copas\"},{\"id\":\"tapir_semillas\",\"name\":\"Tapir o danta amazónica en senderos selváticos\"},{\"id\":\"gamitana_semillas\",\"name\":\"Pez gamitana comiendo frutos bajo el agua\"}],\"correctAnswers\":{\"guacamaya_semillas\":\"aire\",\"tapir_semillas\":\"tierra\",\"gamitana_semillas\":\"agua\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Arco de la Deforestación")
                                .configurationJson("{\"question\":\"¿Cuál es la principal causa de deforestación en la Amazonía colombiana en departamentos como Caquetá y Guaviare?\",\"options\":[\"Los animales que comen demasiado pasto silvestre\",\"La tala ilegal y quema para acaparamiento de tierras y ganadería extensiva\",\"La caída natural de hojas en otoño\"],\"correctAnswer\":\"La tala ilegal y quema para acaparamiento de tierras y ganadería extensiva\",\"correctOption\":1,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Veneno del Mercurio")
                                .configurationJson("{\"statement\":\"El mercurio vertido por la minería ilegal en ríos como el Caquetá o Putumayo contamina los peces y envenena a comunidades indígenas.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Amenazas a la Selva vs Prácticas Indígenas Sostenibles")
                                .configurationJson("{\"instructions\":\"Clasifica los impactos destructivos frente a los saberes y prácticas ancestrales:\",\"categories\":[{\"id\":\"amenaza\",\"name\":\"Amenaza destructiva\"},{\"id\":\"ancestral\",\"name\":\"Saberes y prácticas sostenibles\"}],\"items\":[{\"id\":\"quemas_selva\",\"name\":\"Quema intencional de miles de hectáreas de bosque\"},{\"id\":\"carreteras_ilegales\",\"name\":\"Trochas y carreteras ilegales en parques nacionales\"},{\"id\":\"chagra\",\"name\":\"Chagra indígena tradicional con rotación de cultivos\"},{\"id\":\"guardia_indigena\",\"name\":\"Monitoreo y protección territorial por guardias indígenas\"}],\"correctAnswers\":{\"quemas_selva\":\"amenaza\",\"carreteras_ilegales\":\"amenaza\",\"chagra\":\"ancestral\",\"guardia_indigena\":\"ancestral\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Cadena de Impacto de la Deforestación")
                                .configurationJson("{\"instructions\":\"Ordena las graves consecuencias de talar y quemar la selva amazónica:\",\"steps\":[\"Se talan árboles centenarios para sembrar pasto para ganado\",\"El suelo selvático pierde su humedad y se desertifica rápidamente bajo el sol ardiente\",\"Se debilitan los ríos voladores y se reducen las lluvias en toda Colombia\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Escudos de la Amazonía Colombiana")
                                .configurationJson("{\"instructions\":\"Ubica cada estrategia de protección en su territorio correspondiente:\",\"targets\":[{\"zone\":\"chiribiquete\",\"name\":\"Parque Nacional Serranía de Chiribiquete\"},{\"zone\":\"resguardos\",\"name\":\"Resguardos Indígenas de la Amazonía\"},{\"zone\":\"frontera_bosque\",\"name\":\"Cinturón de contención de deforestación\"}],\"elements\":[{\"id\":\"tepuyes_protegidos\",\"name\":\"Patrimonio mundial con tepuyes y arte rupestre milenario\"},{\"id\":\"gobierno_propio\",\"name\":\"Conservación comunitaria basada en saberes ancestrales\"},{\"id\":\"satelite_bosques\",\"name\":\"Vigilancia satelital en tiempo real para detener talas ilegales\"}],\"correctAnswers\":{\"tepuyes_protegidos\":\"chiribiquete\",\"gobierno_propio\":\"resguardos\",\"satelite_bosques\":\"frontera_bosque\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Parque Chiribiquete")
                                .configurationJson("{\"question\":\"¿Por qué el Parque Nacional Natural Serranía de Chiribiquete es un tesoro de la humanidad?\",\"options\":[\"Porque tiene montañas rusas mecánicas para turistas\",\"Porque es el parque de selva húmeda tropical más grande del mundo y alberga especies únicas y pueblos en aislamiento\",\"Porque está construido completamente de acero inoxidable\"],\"correctAnswer\":\"Porque es el parque de selva húmeda tropical más grande del mundo y alberga especies únicas y pueblos en aislamiento\",\"correctOption\":1,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("No a las Mascotas Silvestres")
                                .configurationJson("{\"statement\":\"Comprar monos, loros o perezosos de la selva para tenerlos en casa es tráfico ilegal de fauna y destruye el ecosistema.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acciones del Guardián Amazónico vs Destrucción")
                                .configurationJson("{\"instructions\":\"Clasifica los compromisos de un Guardián frente a delitos ecológicos:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián Amazónico\"},{\"id\":\"delito\",\"name\":\"Delito o daño ecológico\"}],\"items\":[{\"id\":\"restaurar_semillas\",\"name\":\"Apoyar viveros comunitarios de ceiba, caucho y acai nativo\"},{\"id\":\"respetar_aislamiento\",\"name\":\"Respetar el territorio de pueblos en aislamiento voluntario\"},{\"id\":\"comprar_pieles\",\"name\":\"Comprar pieles de jaguar o plumas de guacamaya\"},{\"id\":\"apoyar_tala\",\"name\":\"Consumir madera fina sin certificación ambiental legal\"}],\"correctAnswers\":{\"restaurar_semillas\":\"guardian\",\"respetar_aislamiento\":\"guardian\",\"comprar_pieles\":\"delito\",\"apoyar_tala\":\"delito\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Plan de Restauración del Bosque Amazónico")
                                .configurationJson("{\"instructions\":\"Ordena los pasos de la restauración ecológica comunitaria:\",\"steps\":[\"Frenar por completo las quemas y el ingreso de ganado a la zona degradada\",\"Sembrar especies nativas pioneras de rápido crecimiento que generen sombra\",\"Las aves y monos regresan dispersando semillas de árboles centenarios regenerando la selva\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Pilares del Guardián Amazónico")
                                .configurationJson("{\"instructions\":\"Asigna cada compromiso ecológico a su ámbito de acción:\",\"targets\":[{\"zone\":\"cultura\",\"name\":\"Respeto y saberes ancestrales\"},{\"zone\":\"consumo\",\"name\":\"Consumo ético ciudadano\"},{\"zone\":\"territorio\",\"name\":\"Defensa activa del territorio\"}],\"elements\":[{\"id\":\"valorar_indigenas\",\"name\":\"Aprender y valorar la sabiduría botánica de pueblos originarios\"},{\"id\":\"cero_carne_selva\",\"name\":\"Rechazar productos que provengan de zonas deforestadas\"},{\"id\":\"denuncia_talas\",\"name\":\"Respaldar denuncias comunitarias contra la tala y minería ilegal\"}],\"correctAnswers\":{\"valorar_indigenas\":\"cultura\",\"cero_carne_selva\":\"consumo\",\"denuncia_talas\":\"territorio\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }
}
