package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;

import java.util.List;

public class ParamoActivitySeeder {

    public static List<ActivityEntity> buildActivities(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Ubicación del Páramo")
                                .configurationJson("{\"question\":\"¿Dónde encontramos los páramos en Colombia?\",\"options\":[\"En las cálidas playas de arena\",\"En las altas cumbres de las cordilleras andinas por encima de 3.000 metros\",\"Bajo el agua profunda del océano\"],\"correctAnswer\":\"En las altas cumbres de las cordilleras andinas por encima de 3.000 metros\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Páramo de Sumapaz")
                                .configurationJson("{\"statement\":\"El páramo de Sumapaz en Colombia es el páramo más grande de todo el planeta Tierra.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Naturaleza vs Basuras en el Páramo")
                                .configurationJson("{\"instructions\":\"Clasifica los elementos según pertenezcan al páramo o sean basuras contaminantes:\",\"categories\":[{\"id\":\"naturaleza\",\"name\":\"Naturaleza del páramo\"},{\"id\":\"basura\",\"name\":\"Basura contaminante\"}],\"items\":[{\"id\":\"frailejon\",\"name\":\"Frailejón silvestre\"},{\"id\":\"pajonal\",\"name\":\"Pajonales dorados\"},{\"id\":\"lata\",\"name\":\"Lata de refresco oxidada\"},{\"id\":\"bolsa\",\"name\":\"Bolsa de plástico rota\"}],\"correctAnswers\":{\"frailejon\":\"naturaleza\",\"pajonal\":\"naturaleza\",\"lata\":\"basura\",\"bolsa\":\"basura\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Amanecer en las Cumbres")
                                .configurationJson("{\"instructions\":\"Ordena cómo despierta un día en el páramo colombiano:\",\"steps\":[\"Noche helada con escarcha y temperaturas bajo cero\",\"Salen los primeros rayos de sol calentando la vegetación\",\"La densa niebla se disipa revelando lagunas relucientes\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Paisaje del Páramo")
                                .configurationJson("{\"instructions\":\"Ubica cada ser en el rincón del páramo donde habita:\",\"targets\":[{\"zone\":\"cielo\",\"name\":\"Cielo frío\"},{\"zone\":\"ladera\",\"name\":\"Ladera con niebla\"},{\"zone\":\"laguna\",\"name\":\"Laguna glaciar\"}],\"elements\":[{\"id\":\"condor\",\"name\":\"Cóndor de los Andes\"},{\"id\":\"frailejon\",\"name\":\"Frailejón Guane\"},{\"id\":\"pato\",\"name\":\"Pato de los torrentes\"}],\"correctAnswers\":{\"condor\":\"cielo\",\"frailejon\":\"ladera\",\"pato\":\"laguna\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Fábricas de Agua")
                                .configurationJson("{\"question\":\"¿Por qué a los páramos se les conoce como 'fábricas naturales de agua'?\",\"options\":[\"Porque tienen máquinas mecánicas gigantes instaladas\",\"Porque atrapan la humedad de la niebla y la liberan lentamente alimentando ríos\",\"Porque calientan el agua para que hierva rápidamente\"],\"correctAnswer\":\"Porque atrapan la humedad de la niebla y la liberan lentamente alimentando ríos\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Los Pelos de la Niebla")
                                .configurationJson("{\"statement\":\"Las diminutas gotas de niebla son atrapadas por la vellosidad blanca de las hojas de las plantas de páramo.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Fuentes de Agua Limpias vs Contaminadas")
                                .configurationJson("{\"instructions\":\"Separa las aguas limpias de páramo de las fuentes hídricas contaminadas:\",\"categories\":[{\"id\":\"limpias\",\"name\":\"Fuentes puras de páramo\"},{\"id\":\"contaminadas\",\"name\":\"Aguas contaminadas\"}],\"items\":[{\"id\":\"glaciar\",\"name\":\"Laguna glaciar de Chingaza\"},{\"id\":\"nacedero\",\"name\":\"Nacedero entre musgos\"},{\"id\":\"detergente\",\"name\":\"Agua con detergentes tóxicos\"},{\"id\":\"petroleo\",\"name\":\"Canal con restos de combustible\"}],\"correctAnswers\":{\"glaciar\":\"limpias\",\"nacedero\":\"limpias\",\"detergente\":\"contaminadas\",\"petroleo\":\"contaminadas\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Viaje de la Gota de Niebla")
                                .configurationJson("{\"instructions\":\"Organiza el recorrido de una gota de agua en el páramo:\",\"steps\":[\"La niebla choca con los pelos protectores del frailejón\",\"La gota escurre y es absorbida por el suelo esponjoso de turba\",\"El agua filtrada brota pura dando origen a quebradas y ríos\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Estados del Agua en la Cumbre")
                                .configurationJson("{\"instructions\":\"Coloca cada forma del agua en su entorno del páramo:\",\"targets\":[{\"zone\":\"aire\",\"name\":\"En el aire\"},{\"zone\":\"suelo\",\"name\":\"En el suelo\"},{\"zone\":\"laguna\",\"name\":\"En las lagunas\"}],\"elements\":[{\"id\":\"niebla\",\"name\":\"Niebla flotante espesa\"},{\"id\":\"turba\",\"name\":\"Musgo empapado como esponja\"},{\"id\":\"trucha\",\"name\":\"Agua cristalina con peces nativos\"}],\"correctAnswers\":{\"niebla\":\"aire\",\"turba\":\"suelo\",\"trucha\":\"laguna\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Crecimiento del Frailejón")
                                .configurationJson("{\"question\":\"¿Cuánto tiempo le toma a un frailejón crecer apenas 1 metro de altura?\",\"options\":[\"Un solo fin de semana\",\"Entre 50 y 100 años porque crece 1 cm al año\",\"Menos de un mes durante el verano\"],\"correctAnswer\":\"Entre 50 y 100 años porque crece 1 cm al año\",\"correctOption\":1,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Cuidado del Frailejón")
                                .configurationJson("{\"statement\":\"Arrancar o pisar un frailejón destruye décadas de crecimiento y deteriora la capacidad del páramo de regular el agua.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Anatomía del Frailejón vs Amenazas")
                                .configurationJson("{\"instructions\":\"Distingue las partes protectoras del frailejón de las agresiones humanas:\",\"categories\":[{\"id\":\"anatomia\",\"name\":\"Estructura del frailejón\"},{\"id\":\"amenaza\",\"name\":\"Amenaza destructiva\"}],\"items\":[{\"id\":\"hojas_vellosas\",\"name\":\"Hojas con vellosidades abrigadas\"},{\"id\":\"tallo_muerto\",\"name\":\"Abrigo de hojas secas en el tallo\"},{\"id\":\"quema\",\"name\":\"Quema intencional de pastizales\"},{\"id\":\"pisoteo\",\"name\":\"Pisoteo fuera de senderos demarcados\"}],\"correctAnswers\":{\"hojas_vellosas\":\"anatomia\",\"tallo_muerto\":\"anatomia\",\"quema\":\"amenaza\",\"pisoteo\":\"amenaza\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Vida Centenaria del Frailejón")
                                .configurationJson("{\"instructions\":\"Ordena la larga vida de un frailejón colombiano:\",\"steps\":[\"Germina una diminuta semilla resguardada entre el musgo húmedo\",\"Crece pacientemente a razón de un centímetro por año\",\"Se convierte en un centinela centenario de más de 2 metros de altura\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Secretos del Frailejón Guane")
                                .configurationJson("{\"instructions\":\"Asigna cada adaptación del frailejón a su función ecológica:\",\"targets\":[{\"zone\":\"hojas\",\"name\":\"Hojas vellosas\"},{\"zone\":\"tallo\",\"name\":\"Tallo con hojas secas\"},{\"zone\":\"raiz\",\"name\":\"Raíces esponjosas\"}],\"elements\":[{\"id\":\"frio\",\"name\":\"Aislarse de las heladas y radiación solar\"},{\"id\":\"humedad\",\"name\":\"Retener calor y conservar humedad en el tronco\"},{\"id\":\"anclaje\",\"name\":\"Absorber agua y sujetarse al suelo pedregoso\"}],\"correctAnswers\":{\"frio\":\"hojas\",\"humedad\":\"tallo\",\"anclaje\":\"raiz\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Los Colchones de Agua")
                                .configurationJson("{\"question\":\"¿Cuánto peso en agua puede retener el musgo Sphagnum de las turberas de páramo?\",\"options\":[\"Cero, el musgo repele el agua por completo\",\"Hasta 20 veces su propio peso en agua pura\",\"Solo el peso de una pequeña moneda\"],\"correctAnswer\":\"Hasta 20 veces su propio peso en agua pura\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Origen de los Ríos")
                                .configurationJson("{\"statement\":\"Grandes ríos colombianos como el río Magdalena, Cauca y Bogotá nacen en lagunas y humedales de páramo.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Protectores vs Destructores de Nacederos")
                                .configurationJson("{\"instructions\":\"Clasifica los elementos según protejan o destruyan los nacimientos de agua:\",\"categories\":[{\"id\":\"protector\",\"name\":\"Protectores del nacedero\"},{\"id\":\"destructor\",\"name\":\"Destructores de fuentes hídricas\"}],\"items\":[{\"id\":\"musgo_vivo\",\"name\":\"Colchón de musgo Sphagnum vivo\"},{\"id\":\"bosque_coloradito\",\"name\":\"Bosque nativo de Polylepis (coloradito)\"},{\"id\":\"drenaje\",\"name\":\"Zanjas de drenaje para secar el humedal\"},{\"id\":\"vacas_nacedero\",\"name\":\"Ganado pisoteando el nacedero\"}],\"correctAnswers\":{\"musgo_vivo\":\"protector\",\"bosque_coloradito\":\"protector\",\"drenaje\":\"destructor\",\"vacas_nacedero\":\"destructor\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Del Nacedero a la Ciudad")
                                .configurationJson("{\"instructions\":\"Organiza el camino del agua desde la cumbre hasta los hogares colombianos:\",\"steps\":[\"El agua gotea constantemente desde las turberas y frailejones de alta montaña\",\"Se reúnen pequeños arroyos que forman cascadas y ríos cristalinos\",\"Llega a las bocatomas de acueductos abasteciendo a millones de personas\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Protección de la Cuenca Alta")
                                .configurationJson("{\"instructions\":\"Ubica cada medida de protección en la zona de la cuenca correspondiente:\",\"targets\":[{\"zone\":\"paramo\",\"name\":\"Nacedero en páramo intocable\"},{\"zone\":\"ronda\",\"name\":\"Ronda hídrica del arroyo\"},{\"zone\":\"poblado\",\"name\":\"Bocatoma municipal\"}],\"elements\":[{\"id\":\"cerca\",\"name\":\"Cerca protectora sin acceso a ganado\"},{\"id\":\"arboles_nativos\",\"name\":\"Siembra de alisos y arbustos de ribera\"},{\"id\":\"control_agua\",\"name\":\"Tratamiento y medición de agua potable\"}],\"correctAnswers\":{\"cerca\":\"paramo\",\"arboles_nativos\":\"ronda\",\"control_agua\":\"poblado\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Radiación Solar y Frío")
                                .configurationJson("{\"question\":\"¿Por qué la radiación ultravioleta del sol es tan intensa en el páramo?\",\"options\":[\"Porque la luna refleja luz verde en el páramo\",\"Porque a mayor altitud la atmósfera es más delgada y filtra menos rayos solares\",\"Porque las montañas tienen espejos artificiales en las rocas\"],\"correctAnswer\":\"Porque a mayor altitud la atmósfera es más delgada y filtra menos rayos solares\",\"correctOption\":1,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Sobrevivir a la Noche Helada")
                                .configurationJson("{\"statement\":\"Muchos animales del páramo buscan refugio en madrigueras subterráneas o bajo pajonales densos para no congelarse.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Adaptaciones de Fauna vs Flora")
                                .configurationJson("{\"instructions\":\"Clasifica las adaptaciones al clima extremo según correspondan a animales o plantas:\",\"categories\":[{\"id\":\"fauna\",\"name\":\"Adaptaciones de animales\"},{\"id\":\"flora\",\"name\":\"Adaptaciones de plantas\"}],\"items\":[{\"id\":\"plumas_condor\",\"name\":\"Plumaje denso y aislante del cóndor\"},{\"id\":\"pelaje_venado\",\"name\":\"Pelaje grueso del venado soche\"},{\"id\":\"hojas_achaparradas\",\"name\":\"Crecimiento rastrero pegado al suelo cálido\"},{\"id\":\"pelusa_blanca\",\"name\":\"Pelusa que refleja el exceso de rayos UV\"}],\"correctAnswers\":{\"plumas_condor\":\"fauna\",\"pelaje_venado\":\"fauna\",\"hojas_achaparradas\":\"flora\",\"pelusa_blanca\":\"flora\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Supervivencia Diaria en la Altura")
                                .configurationJson("{\"instructions\":\"Organiza la rutina diaria de supervivencia de la fauna de alta montaña:\",\"steps\":[\"Resguardarse en cuevas y pajonales durante la helada nocturna\",\"Salir con el sol de la mañana para calentar el cuerpo sobre rocas oscuras\",\"Alimentarse activamente antes de que caiga nuevamente la densa niebla fría\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Especialistas del Frío Andino")
                                .configurationJson("{\"instructions\":\"Asigna cada especie a su microhábitat de refugio en el páramo:\",\"targets\":[{\"zone\":\"paredon\",\"name\":\"Paredones rocosos inaccesibles\"},{\"zone\":\"pajonal\",\"name\":\"Pajonales y frailejonales densos\"},{\"zone\":\"pedregal\",\"name\":\"Rocas calentadas por el sol\"}],\"elements\":[{\"id\":\"nido_condor\",\"name\":\"Nido del Cóndor de los Andes\"},{\"id\":\"curi\",\"name\":\"Madriguera del curí silvestre\"},{\"id\":\"lagartija\",\"name\":\"Lagartija de páramo (Anolis)\"}],\"correctAnswers\":{\"nido_condor\":\"paredon\",\"curi\":\"pajonal\",\"lagartija\":\"pedregal\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Polinizadores de las Alturas")
                                .configurationJson("{\"question\":\"¿Qué ave diminuta y resistente poliniza las flores de frailejón en el páramo?\",\"options\":[\"El colibrí chivito de los páramos (Oxypogon)\",\"El flamenco rosado de la costa marina\",\"El avestruz gigante de las llanuras africanas\"],\"correctAnswer\":\"El colibrí chivito de los páramos (Oxypogon)\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Interdependencia Ecológica")
                                .configurationJson("{\"statement\":\"Si desaparecen los frailejones, muchas especies de abejorros y aves de altura se extinguirían por falta de polen y néctar.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Roles en la Red del Páramo")
                                .configurationJson("{\"instructions\":\"Clasifica a los habitantes del páramo según su función en la red ecológica:\",\"categories\":[{\"id\":\"productor\",\"name\":\"Productores y Polinizadores\"},{\"id\":\"consumidor\",\"name\":\"Herbívoros y Depredadores\"}],\"items\":[{\"id\":\"frailejon_pajonal\",\"name\":\"Frailejones y pastos Calamagrostis\"},{\"id\":\"abejorro_paramo\",\"name\":\"Abejorro de páramo (Bombus)\"},{\"id\":\"venado_soche\",\"name\":\"Venado soche de montaña\"},{\"id\":\"aguila_paramo\",\"name\":\"Águila de páramo (Geranoaetus)\"}],\"correctAnswers\":{\"frailejon_pajonal\":\"productor\",\"abejorro_paramo\":\"productor\",\"venado_soche\":\"consumidor\",\"aguila_paramo\":\"consumidor\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Ciclo de Nutrientes en Suelos Fríos")
                                .configurationJson("{\"instructions\":\"Organiza el reciclaje natural en el suelo negro del páramo:\",\"steps\":[\"Mueren hojas de frailejón y pajonales cayendo al suelo\",\"Hongos de páramo descomponen la materia vegetal lentamente por el frío\",\"Se forma un suelo orgánico oscuro y fértil que alimenta a la vegetación\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Servicios Ecosistémicos de Altura")
                                .configurationJson("{\"instructions\":\"Relaciona cada especie de alta montaña con el servicio vital que brinda:\",\"targets\":[{\"zone\":\"polinizacion\",\"name\":\"Polinización de flores\"},{\"zone\":\"limpieza\",\"name\":\"Limpieza de restos naturales\"},{\"zone\":\"esponja\",\"name\":\"Regulación de caudales\"}],\"elements\":[{\"id\":\"chivito\",\"name\":\"Colibrí chivito de páramo\"},{\"id\":\"condor_limpiador\",\"name\":\"Cóndor carroñero sagrado\"},{\"id\":\"colchon_turbera\",\"name\":\"Colchones de musgo Sphagnum\"}],\"correctAnswers\":{\"chivito\":\"polinizacion\",\"condor_limpiador\":\"limpieza\",\"colchon_turbera\":\"esponja\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Amenazas a Santurbán y Pisba")
                                .configurationJson("{\"question\":\"¿Por qué la minería en páramos como Santurbán es una grave amenaza ambiental en Colombia?\",\"options\":[\"Porque hace que las piedras brillen de color dorado\",\"Porque destruye las fuentes hídricas y puede envenenar el agua de millones de personas con químicos\",\"Porque los mineros plantan demasiados frailejones nuevos\"],\"correctAnswer\":\"Porque destruye las fuentes hídricas y puede envenenar el agua de millones de personas con químicos\",\"correctOption\":1,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Impacto de la Ganadería en Altura")
                                .configurationJson("{\"statement\":\"Las pezuñas pesadas del ganado compactan el suelo esponjoso del páramo, impidiendo que absorba y guarde agua de lluvia.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Impactos Negativos vs Conservación del Páramo")
                                .configurationJson("{\"instructions\":\"Separa las prácticas destructivas de las acciones de conservación legal:\",\"categories\":[{\"id\":\"destructiva\",\"name\":\"Amenazas destructivas\"},{\"id\":\"conservacion\",\"name\":\"Conservación del páramo\"}],\"items\":[{\"id\":\"mineria_oro\",\"name\":\"Minería en zonas de nacedero\"},{\"id\":\"papas_quimicos\",\"name\":\"Cultivos de papa con agroquímicos\"},{\"id\":\"delimitacion\",\"name\":\"Delimitación estricta de páramos por ley\"},{\"id\":\"parques_nacionales\",\"name\":\"Parques Nacionales Naturales protegidos\"}],\"correctAnswers\":{\"mineria_oro\":\"destructiva\",\"papas_quimicos\":\"destructiva\",\"delimitacion\":\"conservacion\",\"parques_nacionales\":\"conservacion\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Consecuencias del Fuego en el Páramo")
                                .configurationJson("{\"instructions\":\"Ordena la cadena destructiva provocada por las quemas no controladas:\",\"steps\":[\"Quemas ilegales destruyen frailejones centenarios y musgos de turbera\",\"El suelo desprotegido se quema y pierde su capacidad de retener humedad\",\"Durante el verano los ríos y acueductos sufren desabastecimiento severo\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Escudos de Protección")
                                .configurationJson("{\"instructions\":\"Asigna cada amenaza a su medida legal o técnica de mitigación:\",\"targets\":[{\"zone\":\"mineria\",\"name\":\"Frente a la minería\"},{\"zone\":\"ganaderia\",\"name\":\"Frente al sobrepastoreo\"},{\"zone\":\"fuego\",\"name\":\"Frente a incendios forestales\"}],\"elements\":[{\"id\":\"prohibicion\",\"name\":\"Prohibición legal de títulos mineros en páramos\"},{\"id\":\"retiro_ganado\",\"name\":\"Retiro concertado de ganado de las cuencas altas\"},{\"id\":\"brigadas\",\"name\":\"Brigadas de guardaparques y campesinos vigías\"}],\"correctAnswers\":{\"prohibicion\":\"mineria\",\"retiro_ganado\":\"ganaderia\",\"brigadas\":\"fuego\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Agua que Bebemos")
                                .configurationJson("{\"question\":\"¿Aproximadamente qué porcentaje del agua potable de Colombia se produce en los páramos?\",\"options\":[\"Menos del 1% del total nacional\",\"Más del 70% del agua potable que llega a ciudades como Bogotá, Bucaramanga y Medellín\",\"El 0%, porque el agua de páramo no se puede consumir\"],\"correctAnswer\":\"Más del 70% del agua potable que llega a ciudades como Bogotá, Bucaramanga y Medellín\",\"correctOption\":1,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Senderismo Responsable")
                                .configurationJson("{\"statement\":\"Al visitar un páramo está prohibido hacer fogatas, extraer plantas o llevar mascotas que puedan perturbar la fauna silvestre.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acciones del Guardián vs Irresponsabilidad")
                                .configurationJson("{\"instructions\":\"Clasifica los comportamientos éticos de un Guardián del Páramo frente a las faltas graves:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián del Páramo\"},{\"id\":\"irresponsable\",\"name\":\"Conducta dañina e irresponsable\"}],\"items\":[{\"id\":\"senderos_autorizados\",\"name\":\"Caminar únicamente por pasarelas autorizadas\"},{\"id\":\"educar_comunidad\",\"name\":\"Promover el ahorro de agua en colegios y hogares\"},{\"id\":\"arrancar_frailejon\",\"name\":\"Arrancar flores de frailejón para llevar de adorno\"},{\"id\":\"dejar_basuras\",\"name\":\"Dejar botellas de vidrio que actúan como lupa y causan fuego\"}],\"correctAnswers\":{\"senderos_autorizados\":\"guardian\",\"educar_comunidad\":\"guardian\",\"arrancar_frailejon\":\"irresponsable\",\"dejar_basuras\":\"irresponsable\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Plan Maestro de Restauración de Páramo")
                                .configurationJson("{\"instructions\":\"Ordena los pasos técnicos para sanar una zona de páramo degradada:\",\"steps\":[\"Cercar el área para evitar el ingreso de ganado y vehículos todoterreno\",\"Propagar plántulas de frailejón en viveros comunitarios de alta montaña\",\"Sembrar plántulas nativas y monitorear el regreso paulatino del agua pura\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Pacto por las Fábricas de Agua")
                                .configurationJson("{\"instructions\":\"Asigna cada compromiso ciudadano al ámbito donde tiene su mayor impacto:\",\"targets\":[{\"zone\":\"montana\",\"name\":\"En el páramo\"},{\"zone\":\"ciudad\",\"name\":\"En la ciudad\"},{\"zone\":\"ley\",\"name\":\"En la participación ciudadana\"}],\"elements\":[{\"id\":\"no_huella\",\"name\":\"Cero basuras y respeto sagrado por el frailejón\"},{\"id\":\"ahorro_ducha\",\"name\":\"Cerrar la llave y duchas cortas para cuidar el caudal\"},{\"id\":\"defensa_ley\",\"name\":\"Apoyar consultas y leyes de protección de páramos\"}],\"correctAnswers\":{\"no_huella\":\"montana\",\"ahorro_ducha\":\"ciudad\",\"defensa_ley\":\"ley\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }
}
