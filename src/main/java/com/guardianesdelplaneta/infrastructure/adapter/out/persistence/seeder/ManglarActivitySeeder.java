package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;

import java.util.List;

public class ManglarActivitySeeder {

    public static List<ActivityEntity> buildActivities(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Bosque Anfibio")
                                .configurationJson("{\"question\":\"¿Qué es el manglar y dónde se encuentra en Colombia?\",\"options\":[\"Un bosque de pinos en lo alto de una montaña nevada\",\"Un bosque de árboles adaptados al agua salada en las costas del Caribe y Pacífico donde la tierra se une con el mar\",\"Un desierto de dunas de arena sin ninguna planta\"],\"correctAnswer\":\"Un bosque de árboles adaptados al agua salada en las costas del Caribe y Pacífico donde la tierra se une con el mar\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Gran Sala Cuna del Mar")
                                .configurationJson("{\"statement\":\"El manglar es conocido como la 'sala cuna del mar' porque miles de peces, camarones y cangrejos nacen y crecen protegidos entre sus raíces.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Vida del Manglar vs Basuras Plásticas")
                                .configurationJson("{\"instructions\":\"Separa los seres vivos nativos del manglar de los residuos que no pertenecen al ecosistema:\",\"categories\":[{\"id\":\"vida\",\"name\":\"Seres vivos del manglar\"},{\"id\":\"basura\",\"name\":\"Basuras contaminantes\"}],\"items\":[{\"id\":\"mangle_rojo\",\"name\":\"Árbol de mangle rojo con zancos\"},{\"id\":\"cangrejo_azul\",\"name\":\"Cangrejo azul patirrojo\"},{\"id\":\"bolsa_fango\",\"name\":\"Bolsa plástica atrapada en las raíces\"},{\"id\":\"vaso_icopor\",\"name\":\"Vaso desechable flotando en el estero\"}],\"correctAnswers\":{\"mangle_rojo\":\"vida\",\"cangrejo_azul\":\"vida\",\"bolsa_fango\":\"basura\",\"vaso_icopor\":\"basura\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Ritmo de las Mareas")
                                .configurationJson("{\"instructions\":\"Ordena cómo cambian las mareas en el bosque de manglar:\",\"steps\":[\"La marea sube e inunda el manglar con agua marina tibia\",\"Peces y alevines nadan y se alimentan entre las raíces zancudas sumergidas\",\"La marea baja dejando al descubierto el fango fértil donde caminan los cangrejos\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Pisos del Bosque de Manglar")
                                .configurationJson("{\"instructions\":\"Ubica a cada habitante en su estrato del ecosistema de manglar:\",\"targets\":[{\"zone\":\"copas\",\"name\":\"Copa de las ramas verdes\"},{\"zone\":\"raices\",\"name\":\"Raíces zancudas sumergidas\"},{\"zone\":\"fango\",\"name\":\"Fango de la orilla durante la bajamar\"}],\"elements\":[{\"id\":\"garza_blanca\",\"name\":\"Garza real blanca descansando\"},{\"id\":\"alevin_robalo\",\"name\":\"Alevines de róbalo nadando seguros\"},{\"id\":\"cangrejo_violinista\",\"name\":\"Cangrejo violinista en su madriguera\"}],\"correctAnswers\":{\"garza_blanca\":\"copas\",\"alevin_robalo\":\"raices\",\"cangrejo_violinista\":\"fango\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Esnórquel Natural de los Árboles")
                                .configurationJson("{\"question\":\"¿Cómo logran respirar los árboles de mangle negro en un fango lodoso sin oxígeno?\",\"options\":[\"Compran bombonas de oxígeno en la ferretería\",\"Desarrollan raíces verticales llamadas 'pneumatóforos' que salen hacia arriba como tubos de esnórquel\",\"No respiran nunca durante toda su vida\"],\"correctAnswer\":\"Desarrollan raíces verticales llamadas 'pneumatóforos' que salen hacia arriba como tubos de esnórquel\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Raíces Zancudas del Mangle Rojo")
                                .configurationJson("{\"statement\":\"El mangle rojo tiene raíces curvas en forma de zancos que sostienen firmemente al árbol contra el fuerte oleaje y las corrientes.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Mangles Nativos vs Plantas Terrestres")
                                .configurationJson("{\"instructions\":\"Clasifica los árboles según sean especies de manglar adaptadas al agua salada o plantas terrestres:\",\"categories\":[{\"id\":\"mangle\",\"name\":\"Árboles de manglar costero\"},{\"id\":\"terrestre\",\"name\":\"Plantas terrestres de tierra firme\"}],\"items\":[{\"id\":\"mangle_rojo_sp\",\"name\":\"Mangle rojo con raíces en arco (Rhizophora)\"},{\"id\":\"mangle_negro_sp\",\"name\":\"Mangle negro con pneumatóforos (Avicennia)\"},{\"id\":\"rosa_jardin\",\"name\":\"Rosal de jardín con espinas\"},{\"id\":\"naranjo_frutal\",\"name\":\"Árbol de naranja dulce\"}],\"correctAnswers\":{\"mangle_rojo_sp\":\"mangle\",\"mangle_negro_sp\":\"mangle\",\"rosa_jardin\":\"terrestre\",\"naranjo_frutal\":\"terrestre\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("La Jabalina Verde: El Propágulo")
                                .configurationJson("{\"instructions\":\"Ordena cómo nace un nuevo árbol de mangle:\",\"steps\":[\"El propágulo o semilla germina aún colgado de la rama del árbol madre\",\"Cae como una jabalina puntiaguda y se clava verticalmente en el fango blando\",\"Echa raíces a gran velocidad antes de que la marea alta lo desplace\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Anatomía Milagrosa del Mangle")
                                .configurationJson("{\"instructions\":\"Asigna cada parte del mangle a su función biológica extraordinaria:\",\"targets\":[{\"zone\":\"hojas\",\"name\":\"Hojas cerosas gruesas\"},{\"zone\":\"zancos\",\"name\":\"Raíces zancudas arqueadas\"},{\"zone\":\"propagulo\",\"name\":\"Propágulo puntiagudo\"}],\"elements\":[{\"id\":\"filtrar_sal\",\"name\":\"Glándulas que expulsan cristales de sal marina\"},{\"id\":\"estabilidad\",\"name\":\"Anclaje firme en el fango y trampa de sedimentos\"},{\"id\":\"semilla_viajera\",\"name\":\"Semilla flotante lista para colonizar nuevas costas\"}],\"correctAnswers\":{\"filtrar_sal\":\"hojas\",\"estabilidad\":\"zancos\",\"semilla_viajera\":\"propagulo\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Jardinero del Fango")
                                .configurationJson("{\"question\":\"¿Por qué los cangrejos son considerados los ingenieros ecológicos del manglar?\",\"options\":[\"Porque construyen puentes de cemento\",\"Porque al cavar madrigueras oxigenan el lodo y al comer hojas caídas reciclan los nutrientes\",\"Porque ahuyentan a todos los pájaros con sus pinzas\"],\"correctAnswer\":\"Porque al cavar madrigueras oxigenan el lodo y al comer hojas caídas reciclan los nutrientes\",\"correctOption\":1,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Bioindicadores de Salud Costera")
                                .configurationJson("{\"statement\":\"La presencia abundante de cangrejos violinistas y azules indica que el manglar tiene buena calidad de agua y suelo oxigenado.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Crustáceos y Moluscos vs Peces de Canal")
                                .configurationJson("{\"instructions\":\"Separa a los habitantes del fango de los peces que nadan en los canales:\",\"categories\":[{\"id\":\"fango\",\"name\":\"Crustáceos y moluscos del suelo\"},{\"id\":\"canal\",\"name\":\"Peces de canales de agua\"}],\"items\":[{\"id\":\"violinista\",\"name\":\"Cangrejo violinista con gran tenaza\"},{\"id\":\"piangua\",\"name\":\"Concha piangua recolectada en raíces\"},{\"id\":\"robalo\",\"name\":\"Róbalo plateado nadador\"},{\"id\":\"sabalo\",\"name\":\"Sábalo saltador de estuario\"}],\"correctAnswers\":{\"violinista\":\"fango\",\"piangua\":\"fango\",\"robalo\":\"canal\",\"sabalo\":\"canal\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Un Día de Trabajo del Cangrejo")
                                .configurationJson("{\"instructions\":\"Organiza la labor diaria de un cangrejo del manglar:\",\"steps\":[\"Sale de su galería subterránea cuando desciende la marea\",\"Recoge hojas de mangle caídas y las introduce en su túnel fresco\",\"Al digerirlas produce abono orgánico fértil que nutre a todo el estuario marino\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Superpoderes del Cangrejo del Manglar")
                                .configurationJson("{\"instructions\":\"Asigna cada parte del cangrejo a su utilidad biológica:\",\"targets\":[{\"zone\":\"gran_tenaza\",\"name\":\"Gran tenaza colorida\"},{\"zone\":\"ojos_pedunculo\",\"name\":\"Ojos en pedúnculos verticales\"},{\"zone\":\"patas_marchadoras\",\"name\":\"Patas excavadoras ágiles\"}],\"elements\":[{\"id\":\"cortejo_defensa\",\"name\":\"Emitir señales visuales para defender madriguera y cortejar\"},{\"id\":\"vision_panoramica\",\"name\":\"Vigilar depredadores mientras el cuerpo sigue bajo el lodo\"},{\"id\":\"galerias_aire\",\"name\":\"Abrir túneles profundos que permiten al suelo fangoso respirar\"}],\"correctAnswers\":{\"cortejo_defensa\":\"gran_tenaza\",\"vision_panoramica\":\"ojos_pedunculo\",\"galerias_aire\":\"patas_marchadoras\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("¿Qué es el Agua Salobre?")
                                .configurationJson("{\"question\":\"¿Qué es el agua salobre que define a los estuarios y manglares colombianos?\",\"options\":[\"Agua congelada con pedazos de hielo flotante\",\"Una mezcla perfecta entre el agua dulce de los ríos y el agua salada que entra con la marea marina\",\"Agua de piscina tratada con cloro artificial\"],\"correctAnswer\":\"Una mezcla perfecta entre el agua dulce de los ríos y el agua salada que entra con la marea marina\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Peligro de Tapar los Caños")
                                .configurationJson("{\"statement\":\"Si se taponan los caños que traen agua dulce de los ríos, el manglar se sobresaliniza y puede secarse y morir por completo.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Nacimiento de Montaña vs Estuario Costero")
                                .configurationJson("{\"instructions\":\"Distingue las características de una quebrada de montaña frente a un estuario de manglar:\",\"categories\":[{\"id\":\"montana\",\"name\":\"Quebrada de montaña alta\"},{\"id\":\"estuario\",\"name\":\"Estuario de manglar costero\"}],\"items\":[{\"id\":\"agua_fria_dulce\",\"name\":\"Agua dulce fría que baja entre rocas limpias\"},{\"id\":\"corriente_rapida\",\"name\":\"Corriente rápida con cascadas oxigenadas\"},{\"id\":\"agua_salobre_tibia\",\"name\":\"Agua salobre tibia afectada por las mareas\"},{\"id\":\"sedimento_rico\",\"name\":\"Fondo de limo y fango muy rico en nutrientes orgánicos\"}],\"correctAnswers\":{\"agua_fria_dulce\":\"montana\",\"corriente_rapida\":\"montana\",\"agua_salobre_tibia\":\"estuario\",\"sedimento_rico\":\"estuario\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Viaje del Agua hasta el Océano")
                                .configurationJson("{\"instructions\":\"Ordena cómo se forma el agua fértil del manglar:\",\"steps\":[\"Los ríos San Juan o Sinú transportan sedimentos y agua dulce desde la cordillera\",\"En la costa el río se abre y choca contra la marea salada del océano\",\"El laberinto de raíces de mangle frena los sedimentos entregando agua limpia al mar abierto\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Zonas del Estuario Colombiano")
                                .configurationJson("{\"instructions\":\"Coloca cada especie en la sección del estuario donde habita:\",\"targets\":[{\"zone\":\"boca_marina\",\"name\":\"Boca marina exterior\"},{\"zone\":\"canales_interiores\",\"name\":\"Canales y ciénagas interiores\"},{\"zone\":\"pantano_salobre\",\"name\":\"Pantano salobre poco profundo\"}],\"elements\":[{\"id\":\"delfin_tucuxi\",\"name\":\"Delfín costero tucuxi cazando en la boca\"},{\"id\":\"nutria_manglar\",\"name\":\"Nutria neotropical nadando en los canales\"},{\"id\":\"mangle_blanco\",\"name\":\"Bosque denso de mangle blanco y botón\"}],\"correctAnswers\":{\"delfin_tucuxi\":\"boca_marina\",\"nutria_manglar\":\"canales_interiores\",\"mangle_blanco\":\"pantano_salobre\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Tesoro del Detrito")
                                .configurationJson("{\"question\":\"¿Qué es el 'detrito' y por qué sostiene toda la red de vida en el manglar?\",\"options\":[\"Basura plástica arrojada por turistas\",\"Materia orgánica de hojas de mangle descompuestas por hongos y bacterias, llena de nutrientes para camarones y peces juveniles\",\"Piedras calizas del fondo marino\"],\"correctAnswer\":\"Materia orgánica de hojas de mangle descompuestas por hongos y bacterias, llena de nutrientes para camarones y peces juveniles\",\"correctOption\":1,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Dependencia de las Aves Marinas")
                                .configurationJson("{\"statement\":\"Garzas, pelícanos y espátulas rosadas dependen directamente de los peces criados en el manglar para alimentar a sus pichones en los nidos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Red Trófica del Manglar")
                                .configurationJson("{\"instructions\":\"Clasifica las especies según sean detritívoros-filtradores o depredadores cazadores:\",\"categories\":[{\"id\":\"detritivoros\",\"name\":\"Detritívoros y Filtradores\"},{\"id\":\"depredadores\",\"name\":\"Cazadores y Depredadores\"}],\"items\":[{\"id\":\"camaron_manglar\",\"name\":\"Camarón blanco comelón de detrito\"},{\"id\":\"ostra_raiz\",\"name\":\"Ostras de mangle filtradoras de agua\"},{\"id\":\"pelicano_pescador\",\"name\":\"Pelícano café zambullidor pescador\"},{\"id\":\"cocodrilo_aguja\",\"name\":\"Caimán aguja (Crocodylus acutus)\"}],\"correctAnswers\":{\"camaron_manglar\":\"detritivoros\",\"ostra_raiz\":\"detritivoros\",\"pelicano_pescador\":\"depredadores\",\"cocodrilo_aguja\":\"depredadores\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Flujo de Energía en las Raíces")
                                .configurationJson("{\"instructions\":\"Organiza la cadena alimentaria en el manglar:\",\"steps\":[\"Cae una hoja amarilla de mangle rojo a las aguas tranquilas del estero\",\"Bacterias y microorganismos la descomponen creando un rico festín de detrito\",\"Larvas de camarón comen el detrito y luego son alimento de pargos y garzas\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Eslabones del Ecosistema Manglar")
                                .configurationJson("{\"instructions\":\"Ubica a cada organismo en su eslabón trófico correspondiente:\",\"targets\":[{\"zone\":\"productor\",\"name\":\"Productor vegetal primario\"},{\"zone\":\"consumidor_primario\",\"name\":\"Consumidor primario herbívoro/filtrador\"},{\"zone\":\"superdepredador\",\"name\":\"Superdepredador tope\"}],\"elements\":[{\"id\":\"mangle_arbol\",\"name\":\"Hojas verdes del árbol de mangle\"},{\"id\":\"caracol_manglar\",\"name\":\"Caracol de mangle raspador de algas\"},{\"id\":\"caiman_costero\",\"name\":\"Caimán o cocodrilo aguja costero\"}],\"correctAnswers\":{\"mangle_arbol\":\"productor\",\"caracol_manglar\":\"consumidor_primario\",\"caiman_costero\":\"superdepredador\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Muro Natural contra Tormentas")
                                .configurationJson("{\"question\":\"¿Cuánto de la fuerza del oleaje y tormentas pueden absorber los bosques de manglar intactos?\",\"options\":[\"Cero, el manglar no frena las olas en absoluto\",\"Hasta un 66% de la energía de las olas y marejadas ciclónicas, protegiendo las casas de las costas\",\"Solo la fuerza de una pequeña llovizna suave\"],\"correctAnswer\":\"Hasta un 66% de la energía de las olas y marejadas ciclónicas, protegiendo las casas de las costas\",\"correctOption\":1,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Deforestación y Vulnerabilidad Costera")
                                .configurationJson("{\"statement\":\"Talar manglares para construir proyectos urbanos o granjas camaroneras deja a las poblaciones expuestas a marejadas e inundaciones devastadoras.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Costas Protegidas vs Costas Deforestadas")
                                .configurationJson("{\"instructions\":\"Clasifica los efectos según correspondan a una costa con manglar sano o a una costa deforestada:\",\"categories\":[{\"id\":\"protegida\",\"name\":\"Beneficios de costa con manglar sano\"},{\"id\":\"deforestada\",\"name\":\"Consecuencias de costa sin manglar\"}],\"items\":[{\"id\":\"barrera_viva\",\"name\":\"Muralla viva contra huracanes y marejadas\"},{\"id\":\"frenar_erosion\",\"name\":\"Retención firme de arena frenando la erosión\"},{\"id\":\"derrumbes_costa\",\"name\":\"Pérdida acelerada de playas y viviendas por oleaje\"},{\"id\":\"salinizacion_pozos\",\"name\":\"Salinización y daño de los pozos de agua dulce del pueblo\"}],\"correctAnswers\":{\"barrera_viva\":\"protegida\",\"frenar_erosion\":\"protegida\",\"derrumbes_costa\":\"deforestada\",\"salinizacion_pozos\":\"deforestada\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Cómo Frena el Manglar una Marejada")
                                .configurationJson("{\"instructions\":\"Ordena cómo funciona el escudo vivo del manglar:\",\"steps\":[\"Olas de gran altura avanzan con fuerza destructiva desde altamar hacia la orilla\",\"Chocan con el denso laberinto elástico de miles de raíces zancudas entrelazadas\",\"La velocidad del agua se disipa y llega suavemente sin causar estragos a las casas costeras\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Servicios Invaluables del Manglar")
                                .configurationJson("{\"instructions\":\"Relaciona cada función ecológica con el gran beneficio para la sociedad colombiana:\",\"targets\":[{\"zone\":\"carbono_azul\",\"name\":\"Almacén de carbono azul\"},{\"zone\":\"defensa_fisica\",\"name\":\"Defensa de la línea de costa\"},{\"zone\":\"seguridad_pesquera\",\"name\":\"Seguridad y sustento alimentario\"}],\"elements\":[{\"id\":\"clima_global\",\"name\":\"Capturar hasta 10 veces más carbono que un bosque terrestre mitigando el cambio climático\"},{\"id\":\"amortiguar_olas\",\"name\":\"Evitar que tempestades tropicales destruyan las poblaciones del Pacífico y Caribe\"},{\"id\":\"peces_comunidad\",\"name\":\"Asegurar la pesca artesanal de pargo, jaiba y piangua para familias campesinas\"}],\"correctAnswers\":{\"clima_global\":\"carbono_azul\",\"amortiguar_olas\":\"defensa_fisica\",\"peces_comunidad\":\"seguridad_pesquera\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La Lección de la Ciénaga Grande")
                                .configurationJson("{\"question\":\"¿Qué grave impacto causó en la Ciénaga Grande de Santa Marta la construcción de una vía sin pasos de agua?\",\"options\":[\"El agua se llenó de dulces de colores\",\"Se interrumpió el intercambio de agua dulce del río Magdalena, matando miles de hectáreas de manglar\",\"Aparecieron arrecifes de coral en la carretera\"],\"correctAnswer\":\"Se interrumpió el intercambio de agua dulce del río Magdalena, matando miles de hectáreas de manglar\",\"correctOption\":1,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Asfixia por Plásticos")
                                .configurationJson("{\"statement\":\"Bolsas y telas plásticas atrapadas en las raíces de los manglares asfixian sus lenticelas y pneumatóforos impidiendo su respiración.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Destrucción Costera vs Restauración Comunitaria")
                                .configurationJson("{\"instructions\":\"Clasifica los actos según sean amenazas destructivas o acciones de restauración:\",\"categories\":[{\"id\":\"amenaza\",\"name\":\"Amenaza destructiva\"},{\"id\":\"restauracion\",\"name\":\"Acción de restauración ecológica\"}],\"items\":[{\"id\":\"carbon_mangle\",\"name\":\"Tala clandestina para producir carbón vegetal\"},{\"id\":\"relleno_urbano\",\"name\":\"Relleno de manglares con escombros para lotes ilegales\"},{\"id\":\"reapertura_canos\",\"name\":\"Reapertura y dragado de caños para que ingrese agua dulce\"},{\"id\":\"siembra_piangueras\",\"name\":\"Siembra comunitaria de propágulos por mujeres piangüeras\"}],\"correctAnswers\":{\"carbon_mangle\":\"amenaza\",\"relleno_urbano\":\"amenaza\",\"reapertura_canos\":\"restauracion\",\"siembra_piangueras\":\"restauracion\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Rehabilitación de un Manglar Dañado")
                                .configurationJson("{\"instructions\":\"Ordena cómo los científicos y comunidades salvan un manglar afectado:\",\"steps\":[\"Se abren y limpian los canales bloqueados permitiendo el reingreso de agua dulce\",\"La salinidad del agua disminuye a niveles saludables para la vegetación nativa\",\"Los árboles retoñan y los cangrejos y peces vuelven a colonizar el bosque recuperado\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Frentes de Conservación en Colombia")
                                .configurationJson("{\"instructions\":\"Asigna cada reto de protección costera a su región en Colombia:\",\"targets\":[{\"zone\":\"cienaga\",\"name\":\"Caribe (Ciénaga Grande de Santa Marta)\"},{\"zone\":\"bahia_malaga\",\"name\":\"Pacífico (Parque Nacional Uramba Bahía Málaga)\"},{\"zone\":\"san_andres\",\"name\":\"Caribe insular (Old Point en San Andrés)\"}],\"elements\":[{\"id\":\"caños_salud\",\"name\":\"Recuperación hidrológica de caños del río Magdalena\"},{\"id\":\"cero_tala_pacific\",\"name\":\"Vigilancia comunitaria ancestral contra tala de mangle rojo\"},{\"id\":\"proteccion_isla\",\"name\":\"Parque regional de manglar protegiendo la laguna arrecifal\"}],\"correctAnswers\":{\"caños_salud\":\"cienaga\",\"cero_tala_pacific\":\"bahia_malaga\",\"proteccion_isla\":\"san_andres\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Las Guardianas Piangüeras")
                                .configurationJson("{\"question\":\"¿Quiénes son las piangüeras del Pacífico colombiano y cómo cuidan el manglar?\",\"options\":[\"Máquinas robóticas que barren el fango\",\"Mujeres tradicionales que recolectan pianguas respetando tallas mínimas y sembrando propágulos\",\"Turistas que dejan basura en los esteros\"],\"correctAnswer\":\"Mujeres tradicionales que recolectan pianguas respetando tallas mínimas y sembrando propágulos\",\"correctOption\":1,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Carbono Azul para el Clima")
                                .configurationJson("{\"statement\":\"Proteger los manglares en Colombia es una de las estrategias más poderosas del país para combatir la crisis climática global.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acciones del Guardián del Manglar vs Conductas Destructivas")
                                .configurationJson("{\"instructions\":\"Distingue el compromiso ecológico frente a las prácticas nocivas en el manglar:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián del Manglar\"},{\"id\":\"nociva\",\"name\":\"Práctica nociva y dañina\"}],\"items\":[{\"id\":\"no_carbón_mangle\",\"name\":\"Rechazar carbón de leña o muebles de mangle talado ilegalmente\"},{\"id\":\"respetar_tallas\",\"name\":\"Respetar tamaños mínimos en conchas, peces y cangrejos\"},{\"id\":\"extraer_pianguita\",\"name\":\"Recolectar pianguas microscópicas antes de que desoven\"},{\"id\":\"verter_aceites\",\"name\":\"Verter aceites o gasolina de lanchas motoras en el manglar\"}],\"correctAnswers\":{\"no_carbón_mangle\":\"guardian\",\"respetar_tallas\":\"guardian\",\"extraer_pianguita\":\"nociva\",\"verter_aceites\":\"nociva\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Jornada Comunitaria de Siembra de Mangles")
                                .configurationJson("{\"instructions\":\"Ordena cómo una comunidad escolar restaura su bosque de manglar:\",\"steps\":[\"Recolectar propágulos maduros y sanos que han caído en el agua limpia\",\"Clavarlos verticalmente en el banco de fango fértil durante las horas de bajamar\",\"Monitorear el crecimiento de las plántulas y protegerlas de basuras flotantes\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Compromisos del Guardián Costero")
                                .configurationJson("{\"instructions\":\"Ubica cada compromiso en el ámbito de la vida costera donde se aplica:\",\"targets\":[{\"zone\":\"escuela\",\"name\":\"En el colegio o comunidad\"},{\"zone\":\"faena\",\"name\":\"En la faena de pesca y concha\"},{\"zone\":\"turismo\",\"name\":\"En el ecoturismo costero\"}],\"elements\":[{\"id\":\"viveros_manglar\",\"name\":\"Crear semilleros escolares de mangle rojo y negro\"},{\"id\":\"liberar_hembras\",\"name\":\"Devolver al agua cangrejas con huevos para que se multipliquen\"},{\"id\":\"paseo_silencioso\",\"name\":\"Navegar en kayak o canoa sin ruido respetando el nido de las aves\"}],\"correctAnswers\":{\"viveros_manglar\":\"escuela\",\"liberar_hembras\":\"faena\",\"paseo_silencioso\":\"turismo\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }
}
