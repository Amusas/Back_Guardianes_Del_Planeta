package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;

import java.util.List;

public class LlanosSabanaActivitySeeder {

    public static List<ActivityEntity> buildActivities(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Mar Verde de la Orinoquía")
                                .configurationJson("{\"question\":\"¿Cómo es el paisaje tradicional de los Llanos Orientales de Colombia?\",\"options\":[\"Una cordillera empinada cubierta de hielo\",\"Inmensas llanuras verdes de pastizales que parecen un mar hasta el horizonte salpicadas de palmas de moriche\",\"Un bosque submarino de corales oscuros\"],\"correctAnswer\":\"Inmensas llanuras verdes de pastizales que parecen un mar hasta el horizonte salpicadas de palmas de moriche\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Bosques de Galería")
                                .configurationJson("{\"statement\":\"En los Llanos colombianos las sabanas abiertas están cruzadas por frondosos bosques de galería a lo largo de los ríos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Llanos vs Páramo Andino")
                                .configurationJson("{\"instructions\":\"Clasifica los paisajes según pertenezcan a los Llanos Orientales o al páramo andino:\",\"categories\":[{\"id\":\"llanos\",\"name\":\"Paisaje de los Llanos\"},{\"id\":\"paramo\",\"name\":\"Paisaje de alta montaña\"}],\"items\":[{\"id\":\"sabana_plana\",\"name\":\"Sabana interminable bajo el sol ardiente\"},{\"id\":\"morichal_agua\",\"name\":\"Morichal de palmas rodeado de esteros\"},{\"id\":\"laguna_glaciar\",\"name\":\"Laguna glaciar a 4.000 metros de altura\"},{\"id\":\"nieve_cumbre\",\"name\":\"Cumbre nevada con escarcha helada\"}],\"correctAnswers\":{\"sabana_plana\":\"llanos\",\"morichal_agua\":\"llanos\",\"laguna_glaciar\":\"paramo\",\"nieve_cumbre\":\"paramo\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Atardecer en la Sabana Llanera")
                                .configurationJson("{\"instructions\":\"Ordena cómo cae la tarde en el llano colombiano:\",\"steps\":[\"El sol rojo tiñe de fuego el horizonte de la sabana\",\"Bandadas de corocoras rojas vuelan en formación hacia los dormideros\",\"Se escuchan los silbidos tranquilos de los chigüiros en el estero al refrescar\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Habitantes de la Sabana")
                                .configurationJson("{\"instructions\":\"Ubica a cada habitante emblemático en su hábitat predilecto:\",\"targets\":[{\"zone\":\"sabana_abierta\",\"name\":\"Pastizal abierto soleado\"},{\"zone\":\"estero\",\"name\":\"Estero de agua fresca\"},{\"zone\":\"cielo_llanero\",\"name\":\"Cielo abierto de la llanura\"}],\"elements\":[{\"id\":\"oso_hormiguero\",\"name\":\"Oso hormiguero con su cola plumosa\"},{\"id\":\"chiguiro_nadador\",\"name\":\"Chigüiro nadando pacíficamente\"},{\"id\":\"corocora_roja\",\"name\":\"Corocora de plumas rojas brillantes\"}],\"correctAnswers\":{\"oso_hormiguero\":\"sabana_abierta\",\"chiguiro_nadador\":\"estero\",\"corocora_roja\":\"cielo_llanero\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Árbol de la Vida")
                                .configurationJson("{\"question\":\"¿Por qué la palma de moriche (Mauritia flexuosa) es llamada el 'árbol de la vida' en los Llanos?\",\"options\":[\"Porque camina por las noches buscando comida\",\"Porque crea oasis donde brota agua pura todo el año y alimenta a casi toda la fauna llanera con sus frutos\",\"Porque está hecha de oro macizo brillante\"],\"correctAnswer\":\"Porque crea oasis donde brota agua pura todo el año y alimenta a casi toda la fauna llanera con sus frutos\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Pastos Resistentes de la Sabana")
                                .configurationJson("{\"statement\":\"Los pastos nativos de la sabana tienen raíces profundas que sobreviven tanto a meses de inundación como a temporadas secas con fuego.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Flora de la Sabana vs Plantas de Clima Frío")
                                .configurationJson("{\"instructions\":\"Distingue las plantas de la sabana llanera de las plantas de alta montaña:\",\"categories\":[{\"id\":\"sabana\",\"name\":\"Flora de la sabana llanera\"},{\"id\":\"fria\",\"name\":\"Flora de clima frío andino\"}],\"items\":[{\"id\":\"pasto_sabanero\",\"name\":\"Pasto sabanero resistente al sol ardiente\"},{\"id\":\"palma_moriche\",\"name\":\"Palma de moriche con frutos carnosos anaranjados\"},{\"id\":\"frailejon_monte\",\"name\":\"Frailejón peludo de alta cumbre\"},{\"id\":\"roble_andino_sp\",\"name\":\"Roble andino de bosque de niebla\"}],\"correctAnswers\":{\"pasto_sabanero\":\"sabana\",\"palma_moriche\":\"sabana\",\"frailejon_monte\":\"fria\",\"roble_andino_sp\":\"fria\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Milagro del Morichal")
                                .configurationJson("{\"instructions\":\"Organiza cómo nace y protege la vida un morichal llanero:\",\"steps\":[\"Brotan manantiales subterráneos de agua cristalina en medio de la llanura\",\"Crecen las palmas de moriche formando un oasis protegido con sombra y humedad\",\"Cientos de animales como dantas, aves y peces acuden a alimentarse y beber agua pura\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Hábitats de los Llanos Orientales")
                                .configurationJson("{\"instructions\":\"Asigna cada componente a su zona ecológica llanera:\",\"targets\":[{\"zone\":\"morichal_permanente\",\"name\":\"Morichal permanente de agua\"},{\"zone\":\"sabana_abierta\",\"name\":\"Sabana de pastizal abierto\"},{\"zone\":\"bosque_galeria\",\"name\":\"Bosque de galería del río\"}],\"elements\":[{\"id\":\"cuna_cristalina\",\"name\":\"Nacedero de agua cristalina y frutos de palma de moriche\"},{\"id\":\"termiteros_sabana\",\"name\":\"Llanura con termiteros gigantes y pastos resistentes\"},{\"id\":\"corredor_monos\",\"name\":\"Franja tupida de árboles donde habitan monos aulladores y pumas\"}],\"correctAnswers\":{\"cuna_cristalina\":\"morichal_permanente\",\"termiteros_sabana\":\"sabana_abierta\",\"corredor_monos\":\"bosque_galeria\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Roedor Más Grande del Mundo")
                                .configurationJson("{\"question\":\"¿Qué récord asombroso tiene el chigüiro o capibara en el planeta?\",\"options\":[\"Es el roedor viviente más grande del planeta y un nadador experto\",\"Es el felino con colmillos más afilados\",\"Es el ave que vuela más alto en las tormentas\"],\"correctAnswer\":\"Es el roedor viviente más grande del planeta y un nadador experto\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Manadas Solidarias")
                                .configurationJson("{\"statement\":\"Los chigüiros viven en manadas sociales muy unidas y cuidan colectivamente a las crías en 'guarderías' comunales.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Comportamiento del Chigüiro vs Hábitos del Jaguar")
                                .configurationJson("{\"instructions\":\"Distingue las conductas del chigüiro herbívoro frente al jaguar cazador:\",\"categories\":[{\"id\":\"chiguiro\",\"name\":\"Conducta del chigüiro llanero\"},{\"id\":\"jaguar\",\"name\":\"Conducta del jaguar de sabana\"}],\"items\":[{\"id\":\"pastar_hierba\",\"name\":\"Pastar hierbas tiernas y plantas acuáticas en grupo\"},{\"id\":\"zambullida_escape\",\"name\":\"Zambullirse y bucear en el agua para escapar del peligro\"},{\"id\":\"acecho_sigilo\",\"name\":\"Acechar con sigilo en el borde del bosque de galería\"},{\"id\":\"mordida_potente\",\"name\":\"Cazar con mandíbulas poderosas manteniendo el balance ecológico\"}],\"correctAnswers\":{\"pastar_hierba\":\"chiguiro\",\"zambullida_escape\":\"chiguiro\",\"acecho_sigilo\":\"jaguar\",\"mordida_potente\":\"jaguar\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("La Rutina del Chigüiro en el Agua")
                                .configurationJson("{\"instructions\":\"Organiza el día de una manada de chigüiros:\",\"steps\":[\"Pastan tranquilamente hierba fresca durante las primeras horas de la mañana\",\"Al subir el calor del mediodía se sumergen en el estero dejando solo ojos y nariz afuera\",\"Se revuelcan en el lodo fresco para hidratar su piel y protegerse de los insectos\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Anatomía Marina del Gran Roedor")
                                .configurationJson("{\"instructions\":\"Asigna cada adaptación del chigüiro a su ventaja física:\",\"targets\":[{\"zone\":\"patas_membranas\",\"name\":\"Patas con membranas interdigitales\"},{\"zone\":\"cabeza_alineada\",\"name\":\"Ojos, orejas y nariz en una sola línea\"},{\"zone\":\"dientes_incisivos\",\"name\":\"Dientes incisivos de crecimiento continuo\"}],\"elements\":[{\"id\":\"remos_acuaticos\",\"name\":\"Remos eficientes para nadar y cruzar esteros y ríos caudalosos\"},{\"id\":\"periscopio_natural\",\"name\":\"Poder vigilar depredadores y respirar mientras el cuerpo sigue sumergido\"},{\"id\":\"cortar_pastos\",\"name\":\"Cortar pastos fibrosos y vegetación dura de la sabana sin desgastarse\"}],\"correctAnswers\":{\"remos_acuaticos\":\"patas_membranas\",\"periscopio_natural\":\"cabeza_alineada\",\"cortar_pastos\":\"dientes_incisivos\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La Gran Inundación de la Sabana")
                                .configurationJson("{\"question\":\"¿Qué ocurre en los Llanos colombianos durante la temporada de invierno con las lluvias?\",\"options\":[\"El suelo se congela como un témpano de hielo\",\"Los ríos se desbordan y convierten millones de hectáreas en un gigantesco mar interior de agua dulce\",\"La sabana se queda sin una sola gota de agua\"],\"correctAnswer\":\"Los ríos se desbordan y convierten millones de hectáreas en un gigantesco mar interior de agua dulce\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Rigor de la Sequía")
                                .configurationJson("{\"statement\":\"Durante la temporada seca de verano en el llano, los esteros se encogen y toda la fauna se agrupa en los últimos pozos de agua.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Invierno vs Verano Llanero")
                                .configurationJson("{\"instructions\":\"Clasifica los eventos según ocurran en el invierno inundado o en el verano seco de la sabana:\",\"categories\":[{\"id\":\"invierno\",\"name\":\"Invierno (Inundación)\"},{\"id\":\"verano\",\"name\":\"Verano (Sequía severa)\"}],\"items\":[{\"id\":\"canoas_estero\",\"name\":\"Esteros gigantes donde navegan canoas sobre pastizales\"},{\"id\":\"dispersion_peces\",\"name\":\"Peces dispersos comiendo por toda la llanura inundada\"},{\"id\":\"polvo_sol\",\"name\":\"Polvo, sol ardiente y charcas de agua reducidas\"},{\"id\":\"amontonamiento\",\"name\":\"Cientos de chigüiros amontonados buscando el último pozo\"}],\"correctAnswers\":{\"canoas_estero\":\"invierno\",\"dispersion_peces\":\"invierno\",\"polvo_sol\":\"verano\",\"amontonamiento\":\"verano\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Latido Estacional de la Orinoquía")
                                .configurationJson("{\"instructions\":\"Ordena cómo funciona el ciclo estacional de los llanos:\",\"steps\":[\"Llegan las lluvias y los ríos inundan las sabanas creando un gigantesco humedal fértil\",\"Los peces y chigüiros nadan libres dispersándose por millones de hectáreas verdes\",\"Llega el verano, las aguas bajan y las aves pescadoras se alimentan en las pozas concentradas\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Refugios en las Dos Estaciones")
                                .configurationJson("{\"instructions\":\"Ubica cada refugio natural en su función de salvamento llanero:\",\"targets\":[{\"zone\":\"banco_alto\",\"name\":\"Banco de tierra alta no inundable\"},{\"zone\":\"estero_profundo\",\"name\":\"Estero hondo en la sequía\"},{\"zone\":\"cano_galeria\",\"name\":\"Caño con bosque de galería\"}],\"elements\":[{\"id\":\"isla_seca\",\"name\":\"Isla seca para descanso del ganado y fauna durante la gran inundación\"},{\"id\":\"reserva_verano\",\"name\":\"Última trinchera de agua fresca para caimanes, chigüiros y peces en verano\"},{\"id\":\"sombra_eterna\",\"name\":\"Sombra perenne y microclima fresco contra el sol calcinante\"}],\"correctAnswers\":{\"isla_seca\":\"banco_alto\",\"reserva_verano\":\"estero_profundo\",\"sombra_eterna\":\"cano_galeria\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Titán del Río Orinoco")
                                .configurationJson("{\"question\":\"¿Quién es el Caimán del Orinoco (Crocodylus intermedius) y por qué necesita protección urgente en Colombia?\",\"options\":[\"Es uno de los cocodrilos más grandes del mundo (hasta 6 metros) y está en peligro crítico de extinción\",\"Es un pequeño lagarto inofensivo que come frutas de jardín\",\"Es un pez volador con escamas plateadas\"],\"correctAnswer\":\"Es uno de los cocodrilos más grandes del mundo (hasta 6 metros) y está en peligro crítico de extinción\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Lengua Gigante del Oso Palmero")
                                .configurationJson("{\"statement\":\"El oso hormiguero gigante no tiene dientes y utiliza su lengua pegajosa de 60 cm para comer miles de hormigas y termitas al día.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Depredadores vs Presas Herbívoras")
                                .configurationJson("{\"instructions\":\"Clasifica a la fauna de la Orinoquía según su rol en la cadena alimentaria:\",\"categories\":[{\"id\":\"depredador\",\"name\":\"Depredadores de la sabana\"},{\"id\":\"herbivoro\",\"name\":\"Herbívoros pacíficos\"}],\"items\":[{\"id\":\"caiman_orinoco\",\"name\":\"Caimán del Orinoco en ríos caudalosos\"},{\"id\":\"jaguar_llanero\",\"name\":\"Jaguar cazador de estero\"},{\"id\":\"chiguiro_manada\",\"name\":\"Chigüiro llanero comelón de pasto tierno\"},{\"id\":\"venado_cola_blanca\",\"name\":\"Venado coliblanco de la pradera abierta\"}],\"correctAnswers\":{\"caiman_orinoco\":\"depredador\",\"jaguar_llanero\":\"depredador\",\"chiguiro_manada\":\"herbivoro\",\"venado_cola_blanca\":\"herbivoro\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Flujo de Vida en el Estero")
                                .configurationJson("{\"instructions\":\"Organiza la cadena trófica en el humedal de la sabana:\",\"steps\":[\"Plantas acuáticas y pastos tiernos crecen con el sol y agua del estero\",\"Los chigüiros se alimentan de la vegetación verde manteniendo podado el humedal\",\"El caimán del Orinoco y la anaconda cazan manteniendo equilibrada a la manada\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Especialistas de la Orinoquía")
                                .configurationJson("{\"instructions\":\"Ubica cada especie extraordinaria en su destreza ecológica:\",\"targets\":[{\"zone\":\"excavador\",\"name\":\"Cazador de termiteros en tierra\"},{\"zone\":\"pescador_aire\",\"name\":\"Pescador de aguas someras\"},{\"zone\":\"delfin_rio\",\"name\":\"Acróbata acuático de río\"}],\"elements\":[{\"id\":\"oso_garras\",\"name\":\"Oso hormiguero con garras curvas poderosas\"},{\"id\":\"garzon_soldado\",\"name\":\"Garzón soldado con su enorme pico arpón\"},{\"id\":\"tonina_orinoco\",\"name\":\"Tonina o delfín rosado cazando en el río Meta\"}],\"correctAnswers\":{\"oso_garras\":\"excavador\",\"garzon_soldado\":\"pescador_aire\",\"tonina_orinoco\":\"delfin_rio\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Los Filtros de la Sabana")
                                .configurationJson("{\"question\":\"¿Qué papel ecológico fundamental cumplen los esteros y morichales en el suelo de la Orinoquía?\",\"options\":[\"Convierten la arena en polvo de hornear\",\"Filtran sedimentos, recargan aguas subterráneas y guardan millones de litros de agua dulce para el verano\",\"Hacen que el suelo sea imposible de caminar\"],\"correctAnswer\":\"Filtran sedimentos, recargan aguas subterráneas y guardan millones de litros de agua dulce para el verano\",\"correctOption\":1,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Suelos Ácidos y Fuego Continuo")
                                .configurationJson("{\"statement\":\"Los suelos de la sabana son naturalmente ácidos y frágiles; las quemas anuales continuas destruyen los nutrientes y desertifican la tierra.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Microhábitats: Sabana Alta vs Humedal Bajo")
                                .configurationJson("{\"instructions\":\"Clasifica los componentes según correspondan a la sabana alta no inundable o al estero bajo:\",\"categories\":[{\"id\":\"sabana_alta\",\"name\":\"Sabana alta y bancos\"},{\"id\":\"estero_bajo\",\"name\":\"Humedal y estero bajo\"}],\"items\":[{\"id\":\"termiteros_altos\",\"name\":\"Castillos gigantes de termitas duras\"},{\"id\":\"pastos_resiste\",\"name\":\"Pastos con raíces profundas en tierra seca\"},{\"id\":\"lentejas_agua\",\"name\":\"Manto flotante de lentejas y buchón de agua\"},{\"id\":\"fango_anaconda\",\"name\":\"Fondo fangoso y húmedo donde reposan las anacondas\"}],\"correctAnswers\":{\"termiteros_altos\":\"sabana_alta\",\"pastos_resiste\":\"sabana_alta\",\"lentejas_agua\":\"estero_bajo\",\"fango_anaconda\":\"estero_bajo\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Reciclaje de Nutrientes en la Sabana")
                                .configurationJson("{\"instructions\":\"Organiza el enriquecimiento del suelo de los llanos:\",\"steps\":[\"La manada de chigüiros pasta y fertiliza el pastizal con su estiércol\",\"Escarabajos peloteros y lombrices entierran la materia orgánica en la tierra\",\"El suelo se nutre permitiendo un rebrote vigoroso de pastos nativos tras las lluvias\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Ecosistema del Estero Llanero")
                                .configurationJson("{\"instructions\":\"Asigna cada componente al estrato del humedal donde se encuentra:\",\"targets\":[{\"zone\":\"superficie_vegetal\",\"name\":\"Superficie de plantas acuáticas\"},{\"zone\":\"orilla_fangosa\",\"name\":\"Orilla fangosa de transición\"},{\"zone\":\"profundo\",\"name\":\"Fondo profundo del estero\"}],\"elements\":[{\"id\":\"tarulla_flor\",\"name\":\"Tarulla o lirio de agua en floración\"},{\"id\":\"huellas_danta\",\"name\":\"Huellas frescas de venados y chigüiros bebiendo\"},{\"id\":\"cachamas_fondo\",\"name\":\"Cardumen de cachamas y bagres rayados nadando\"}],\"correctAnswers\":{\"tarulla_flor\":\"superficie_vegetal\",\"huellas_danta\":\"orilla_fangosa\",\"cachamas_fondo\":\"profundo\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Drama de la Gran Sequía")
                                .configurationJson("{\"question\":\"¿Por qué la combinación de sequía extrema con quemas descontroladas ha provocado tragedias en Casanare?\",\"options\":[\"Porque la lluvia cae hacia arriba\",\"Porque seca los esteros antes de tiempo, asfixia a miles de chigüiros y tortugas y destruye los morichales protectores de agua\",\"Porque hace que los ríos se congelen de golpe\"],\"correctAnswer\":\"Porque seca los esteros antes de tiempo, asfixia a miles de chigüiros y tortugas y destruye los morichales protectores de agua\",\"correctOption\":1,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Desecación de Humedales")
                                .configurationJson("{\"statement\":\"Drenar artificialmente esteros y caños para sembrar monocultivos de arroz o palma aceitera sin control destruye los bebederos de la fauna silvestre.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Amenazas a los Llanos vs Ganadería Sostenible")
                                .configurationJson("{\"instructions\":\"Clasifica las prácticas según sean destructivas o sostenibles con el llano:\",\"categories\":[{\"id\":\"amenaza\",\"name\":\"Amenaza ecológica destructiva\"},{\"id\":\"sostenible\",\"name\":\"Práctica de manejo sostenible\"}],\"items\":[{\"id\":\"quema_masiva\",\"name\":\"Quema masiva con vientos en pleno mediodía seco\"},{\"id\":\"desvio_rios\",\"name\":\"Desvío de ríos enteros dejando sin agua a comunidades río abajo\"},{\"id\":\"reservorios_pozos\",\"name\":\"Creación de reservorios de agua y bebederos para la fauna en verano\"},{\"id\":\"respeto_morichal\",\"name\":\"Cercar morichales y nacederos para que el ganado no los dañe\"}],\"correctAnswers\":{\"quema_masiva\":\"amenaza\",\"desvio_rios\":\"amenaza\",\"reservorios_pozos\":\"sostenible\",\"respeto_morichal\":\"sostenible\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Secuencia de un Fuego Descontrolado")
                                .configurationJson("{\"instructions\":\"Ordena cómo una quema irresponsable devasta la sabana:\",\"steps\":[\"Se inicia una quema ilegal de pasto con vientos fuertes de verano\",\"El fuego penetra en el morichal quemando el corazón húmedo de las palmas\",\"El nacedero se seca y los animales del estero quedan desamparados sin agua\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Medidas de Mitigación en la Sabana")
                                .configurationJson("{\"instructions\":\"Asigna cada solución a la emergencia llanera correspondiente:\",\"targets\":[{\"zone\":\"sequia_extrema\",\"name\":\"Frente a sequías extremas\"},{\"zone\":\"agroindustria\",\"name\":\"Frente a la agroindustria\"},{\"zone\":\"fauna_peligro\",\"name\":\"Frente a la fauna amenazada\"}],\"elements\":[{\"id\":\"pozos_solares\",\"name\":\"Pozos profundos con bombas solares para recargar esteros en verano\"},{\"id\":\"corredores_sabana\",\"name\":\"Mantenimiento obligatorio de franjas de morichal y bosques intactos\"},{\"id\":\"cero_caza_chiguiro\",\"name\":\"Prohibición de cacería comercial furtiva de chigüiro y tortuga sabanera\"}],\"correctAnswers\":{\"pozos_solares\":\"sequia_extrema\",\"corredores_sabana\":\"agroindustria\",\"cero_caza_chiguiro\":\"fauna_peligro\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Reservas de la Sociedad Civil en Casanare")
                                .configurationJson("{\"question\":\"¿Por qué reservas de la sociedad civil como el Hato La Aurora en Casanare son ejemplos mundiales de conservación?\",\"options\":[\"Porque encerraron a los animales en jaulas de zoológico\",\"Porque demuestran que la ganadería tradicional puede convivir con miles de chigüiros, venados y jaguares libres protegidos por los llaneros\",\"Porque talaron todos los árboles para sembrar cemento\"],\"correctAnswer\":\"Porque demuestran que la ganadería tradicional puede convivir con miles de chigüiros, venados y jaguares libres protegidos por los llaneros\",\"correctOption\":1,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Tradición del Cuidado Llanero")
                                .configurationJson("{\"statement\":\"Los cantos de trabajo de llano, declarados Patrimonio de la Humanidad, expresan el profundo respeto y amor del vaquero por la sabana y sus animales.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acciones del Guardián Llanero vs Destrucción de la Sabana")
                                .configurationJson("{\"instructions\":\"Distingue las acciones de un Guardián de la Sabana frente a conductas perjudiciales:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián de la Sabana\"},{\"id\":\"destructiva\",\"name\":\"Conducta perjudicial\"}],\"items\":[{\"id\":\"cuidar_morichal\",\"name\":\"Cercar nacederos de morichal para proteger el agua cristalina\"},{\"id\":\"safari_fotografico\",\"name\":\"Fomentar el ecoturismo y safaris que valoran a los animales vivos\"},{\"id\":\"cazar_ilegal_chiguiro\",\"name\":\"Cazar chigüiros ilegalmente para traficar su carne\"},{\"id\":\"arrojar_quimicos\",\"name\":\"Arrojar recipientes de agroquímicos vacíos en los caños y esteros\"}],\"correctAnswers\":{\"cuidar_morichal\":\"guardian\",\"safari_fotografico\":\"guardian\",\"cazar_ilegal_chiguiro\":\"destructiva\",\"arrojar_quimicos\":\"destructiva\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Plan Maestro de Protección del Estero")
                                .configurationJson("{\"instructions\":\"Ordena cómo un hato llanero protege su estero comunal:\",\"steps\":[\"Instalar molinos de viento o paneles solares que recargan pozas profundas durante el verano\",\"Acordar con los vecinos la prohibición de quemas y de cacería furtiva de fauna silvestre\",\"Monitorear con alegría el nacimiento de crías de chigüiro, caimanes y corocoras en el estero seguro\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("El Legado del Guardián de los Llanos")
                                .configurationJson("{\"instructions\":\"Ubica cada compromiso en el pilar de conservación correspondiente:\",\"targets\":[{\"zone\":\"agua_morichal\",\"name\":\"El agua sagrada (Morichales)\"},{\"zone\":\"fauna_libre\",\"name\":\"La fauna libre (Sabana y esteros)\"},{\"zone\":\"cultura_llanera\",\"name\":\"Cultura y orgullo ambiental\"}],\"elements\":[{\"id\":\"morichal_eterno\",\"name\":\"Cuidado permanente de los nacederos como fábricas de vida\"},{\"id\":\"convivencia_jaguar\",\"name\":\"Convivencia pacífica con chigüiros, osos palmeros y jaguares libres\"},{\"id\":\"cantos_naturaleza\",\"name\":\"Difusión de los cantos de vaquería que enseñan el amor por la naturaleza\"}],\"correctAnswers\":{\"morichal_eterno\":\"agua_morichal\",\"convivencia_jaguar\":\"fauna_libre\",\"cantos_naturaleza\":\"cultura_llanera\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }
}
