package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;

import java.util.List;

public class OceanoActivitySeeder {

    public static List<ActivityEntity> buildActivities(LessonEntity lesson, int lessonOrder) {
        switch (lessonOrder) {
            case 1:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Los Dos Mares de Colombia")
                                .configurationJson("{\"question\":\"¿Cuáles son los dos océanos que bañan las costas de Colombia?\",\"options\":[\"El Océano Glacial Ártico y el Mar Muerto\",\"El Mar Caribe (Océano Atlántico) y el Océano Pacífico\",\"Solo un gran lago interior sin salida al mar\"],\"correctAnswer\":\"El Mar Caribe (Océano Atlántico) y el Océano Pacífico\",\"correctOption\":1,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Privilegio Marino de Colombia")
                                .configurationJson("{\"statement\":\"Colombia es el único país de América del Sur que cuenta con extensas costas sobre el Mar Caribe y el Océano Pacífico.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Caribe vs Pacífico Colombiano")
                                .configurationJson("{\"instructions\":\"Clasifica las características según correspondan al litoral Caribe o al litoral Pacífico:\",\"categories\":[{\"id\":\"caribe\",\"name\":\"Caribe Colombiano\"},{\"id\":\"pacifico\",\"name\":\"Pacífico Colombiano\"}],\"items\":[{\"id\":\"arrecifes_rosario\",\"name\":\"Arrecifes de coral de San Andrés y Rosario\"},{\"id\":\"arena_blanca\",\"name\":\"Playas de arena blanca y aguas turquesas\"},{\"id\":\"ballenas_utria\",\"name\":\"Visita de ballenas jorobadas en Gorgona y Utría\"},{\"id\":\"selva_mar\",\"name\":\"Selva tropical húmeda que se funde con el mar\"}],\"correctAnswers\":{\"arrecifes_rosario\":\"caribe\",\"arena_blanca\":\"caribe\",\"ballenas_utria\":\"pacifico\",\"selva_mar\":\"pacifico\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Movimiento de las Aguas")
                                .configurationJson("{\"instructions\":\"Ordena cómo influyen las corrientes marinas en los océanos colombianos:\",\"steps\":[\"Los vientos alisios empujan aguas cálidas en el mar Caribe\",\"Corrientes ricas en nutrientes emergen en el Pacífico colombiano\",\"Se genera una gigantesca explosión de peces, moluscos y vida marina\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Habitantes de Nuestros Mares")
                                .configurationJson("{\"instructions\":\"Ubica cada especie emblemática en su entorno marino colombiano:\",\"targets\":[{\"zone\":\"arrecife_caribe\",\"name\":\"Arrecifes del Caribe\"},{\"zone\":\"costa_pacifica\",\"name\":\"Ensenadas del Pacífico\"},{\"zone\":\"altamar\",\"name\":\"Altamar de Malpelo\"}],\"elements\":[{\"id\":\"pez_loro\",\"name\":\"Pez loro multicolor\"},{\"id\":\"ballena\",\"name\":\"Ballena jorobada con cría\"},{\"id\":\"tiburon_martillo\",\"name\":\"Cardumen de tiburón martillo\"}],\"correctAnswers\":{\"pez_loro\":\"arrecife_caribe\",\"ballena\":\"costa_pacifica\",\"tiburon_martillo\":\"altamar\"},\"difficulty\":\"Muy fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 2:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("¿Qué es un Coral?")
                                .configurationJson("{\"question\":\"¿Qué son en realidad los corales que construyen los arrecifes submarinos?\",\"options\":[\"Rocas inanimadas arrastradas por los ríos\",\"Pequeños animales marinos llamados pólipos que viven en colonias\",\"Plantas terrestres que cayeron al agua\"],\"correctAnswer\":\"Pequeños animales marinos llamados pólipos que viven en colonias\",\"correctOption\":1,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Gran Barrera de Seaflower")
                                .configurationJson("{\"statement\":\"La Reserva de Biosfera Seaflower en San Andrés y Providencia alberga una de las barreras de arrecifes más grandes del planeta.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Vida en el Arrecife vs Amenazas")
                                .configurationJson("{\"instructions\":\"Distingue a los habitantes del arrecife de las amenazas que lo destruyen:\",\"categories\":[{\"id\":\"vida\",\"name\":\"Habitantes del arrecife\"},{\"id\":\"amenaza\",\"name\":\"Amenaza al arrecife\"}],\"items\":[{\"id\":\"coral_cerebro\",\"name\":\"Coral cerebro vivo\"},{\"id\":\"anemona\",\"name\":\"Anémonas y pez payaso\"},{\"id\":\"blanqueamiento\",\"name\":\"Blanqueamiento por agua muy caliente\"},{\"id\":\"ancla_bote\",\"name\":\"Anclas de embarcaciones que fracturan corales\"}],\"correctAnswers\":{\"coral_cerebro\":\"vida\",\"anemona\":\"vida\",\"blanqueamiento\":\"amenaza\",\"ancla_bote\":\"amenaza\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Construcción de una Selva Submarina")
                                .configurationJson("{\"instructions\":\"Ordena cómo se forma un arrecife de coral milenario:\",\"steps\":[\"Pólipos microscópicos se fijan en el lecho marino rocoso\",\"Absorben minerales del agua y construyen duros esqueletos de carbonato de calcio\",\"Durante siglos crecen formando inmensas murallas submarinas llenas de peces\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Estructura del Arrecife Coralino")
                                .configurationJson("{\"instructions\":\"Ubica cada componente en la zona del arrecife que le corresponde:\",\"targets\":[{\"zone\":\"cresta\",\"name\":\"Cresta coralina superficial\"},{\"zone\":\"laguna\",\"name\":\"Laguna arenosa protegida\"},{\"zone\":\"pared\",\"name\":\"Pared exterior profunda\"}],\"elements\":[{\"id\":\"cuerno_alce\",\"name\":\"Coral cuerno de alce robusto\"},{\"id\":\"caballito\",\"name\":\"Caballito de mar en la arena\"},{\"id\":\"pez_angel\",\"name\":\"Pez ángel francés nadando en la pared\"}],\"correctAnswers\":{\"cuerno_alce\":\"cresta\",\"caballito\":\"laguna\",\"pez_angel\":\"pared\"},\"difficulty\":\"Fácil\"}")
                                .xpReward(10)
                                .orderIndex(5)
                                .build()
                );
            case 3:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Pulmón del Océano")
                                .configurationJson("{\"question\":\"¿Por qué las praderas de pastos marinos en el Caribe colombiano son tan valiosas?\",\"options\":[\"Porque convierten el agua salada en leche dulce\",\"Porque capturan carbono de la atmósfera, limpian el agua y producen oxígeno para el mar\",\"Porque impiden que los barcos puedan navegar\"],\"correctAnswer\":\"Porque capturan carbono de la atmósfera, limpian el agua y producen oxígeno para el mar\",\"correctOption\":1,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Manatí y los Pastos")
                                .configurationJson("{\"statement\":\"El manatí del Caribe se alimenta pacíficamente de hojas de pastos marinos en ensenadas y bahías colombianas.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Pastos Marinos vs Macroalgas")
                                .configurationJson("{\"instructions\":\"Clasifica los organismos vegetales marinos según sean pastos marinos o algas:\",\"categories\":[{\"id\":\"pastos\",\"name\":\"Pastos marinos (Plantas con flores)\"},{\"id\":\"algas\",\"name\":\"Macroalgas marinas\"}],\"items\":[{\"id\":\"thalassia\",\"name\":\"Pasto de tortuga (Thalassia testudinum)\"},{\"id\":\"syringodium\",\"name\":\"Pasto manatí (Syringodium)\"},{\"id\":\"sargazo\",\"name\":\"Alga sargazo flotante\"},{\"id\":\"alga_roja\",\"name\":\"Alga roja calcárea de fondo\"}],\"correctAnswers\":{\"thalassia\":\"pastos\",\"syringodium\":\"pastos\",\"sargazo\":\"algas\",\"alga_roja\":\"algas\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("El Escudo de Pastos Marinos")
                                .configurationJson("{\"instructions\":\"Organiza los beneficios protectores de una pradera marina:\",\"steps\":[\"Sus densas raíces sujetan la arena del fondo evitando que sea arrastrada\",\"Sus hojas ondulantes disipan la fuerza del oleaje antes de llegar a la playa\",\"El agua queda limpia y transparente, protegiendo a los alevines de peces\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Sala Cuna de la Pradera")
                                .configurationJson("{\"instructions\":\"Ubica cada especie en su nivel de la pradera de pastos marinos:\",\"targets\":[{\"zone\":\"hojas\",\"name\":\"Entre las hojas ondulantes\"},{\"zone\":\"fondo\",\"name\":\"En el fondo arenoso\"},{\"zone\":\"columna\",\"name\":\"En el agua abierta sobre la pradera\"}],\"elements\":[{\"id\":\"caracol_pala\",\"name\":\"Caracol pala (Lobatus gigas)\"},{\"id\":\"estrella\",\"name\":\"Estrella de mar cojín dorada\"},{\"id\":\"pargo_juvenil\",\"name\":\"Juvenil de pargo rojo en crecimiento\"}],\"correctAnswers\":{\"caracol_pala\":\"hojas\",\"estrella\":\"fondo\",\"pargo_juvenil\":\"columna\"},\"difficulty\":\"Fácil - Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 4:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La Memoria de la Tortuga")
                                .configurationJson("{\"question\":\"¿Cómo logran las tortugas marinas regresar a desovar a la misma playa donde nacieron?\",\"options\":[\"Usan mapas dibujados en sus aletas\",\"Tienen una brújula magnética interna y memorizan los olores y campos magnéticos de su playa natal\",\"Siguen el rastro de barcos pesqueros turísticos\"],\"correctAnswer\":\"Tienen una brújula magnética interna y memorizan los olores y campos magnéticos de su playa natal\",\"correctOption\":1,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("Respiración de las Tortugas")
                                .configurationJson("{\"statement\":\"Las tortugas marinas respiran por pulmones y deben salir periódicamente a la superficie del agua a tomar aire.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Tortugas Marinas Colombianas vs Fauna Dulceacuícola")
                                .configurationJson("{\"instructions\":\"Separa las especies de tortugas marinas de las especies de río o selva:\",\"categories\":[{\"id\":\"marina\",\"name\":\"Tortugas marinas de Colombia\"},{\"id\":\"dulceacuicola\",\"name\":\"Fauna de agua dulce\"}],\"items\":[{\"id\":\"carey\",\"name\":\"Tortuga Carey del Caribe (Eretmochelys)\"},{\"id\":\"laud\",\"name\":\"Tortuga Laúd gigante (Dermochelys coriacea)\"},{\"id\":\"charapa\",\"name\":\"Tortuga charapa de río amazónico\"},{\"id\":\"delfin_rosado\",\"name\":\"Delfín rosado del río Orinoco\"}],\"correctAnswers\":{\"carey\":\"marina\",\"laud\":\"marina\",\"charapa\":\"dulceacuicola\",\"delfin_rosado\":\"dulceacuicola\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Ciclo de Vida de la Tortuga Marina")
                                .configurationJson("{\"instructions\":\"Ordena las etapas del viaje de una tortuga marina:\",\"steps\":[\"La madre cava un nido en la arena tibia de la playa durante la noche\",\"Los neonatos eclosionan y corren velozmente hacia el mar guiados por el brillo de las olas\",\"Crecen en altamar y décadas después regresan a la misma playa a desovar\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Peligros en la Travesía Marina")
                                .configurationJson("{\"instructions\":\"Asigna cada amenaza al espacio del viaje de la tortuga donde ocurre:\",\"targets\":[{\"zone\":\"playa\",\"name\":\"En la playa de anidación\"},{\"zone\":\"costa\",\"name\":\"En aguas costeras\"},{\"zone\":\"altamar\",\"name\":\"En mar abierto\"}],\"elements\":[{\"id\":\"luces\",\"name\":\"Luces artificiales que desorientan a las crías\"},{\"id\":\"redes_fantasma\",\"name\":\"Redes de pesca abandonadas que atrapan tortugas\"},{\"id\":\"bolsas_plasticas\",\"name\":\"Bolsas plásticas flotantes que confunden con medusas\"}],\"correctAnswers\":{\"luces\":\"playa\",\"redes_fantasma\":\"costa\",\"bolsas_plasticas\":\"altamar\"},\"difficulty\":\"Intermedio\"}")
                                .xpReward(15)
                                .orderIndex(5)
                                .build()
                );
            case 5:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La Base de la Vida Marina")
                                .configurationJson("{\"question\":\"¿Cuál es la base microscópica que sostiene toda la cadena alimentaria en los océanos?\",\"options\":[\"El fitoplancton microscópico que fotosintetiza con la luz solar\",\"Las piedras redondas arrastradas por las olas\",\"Los granos de sal marina disueltos\"],\"correctAnswer\":\"El fitoplancton microscópico que fotosintetiza con la luz solar\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Gran Viaje de las Jorobadas")
                                .configurationJson("{\"statement\":\"Las ballenas jorobadas viajan más de 8.000 kilómetros desde la Antártida hasta las aguas cálidas del Pacífico colombiano para parir a sus ballenatos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Niveles Tróficos del Océano")
                                .configurationJson("{\"instructions\":\"Clasifica las especies marinas según su posición en la red trófica:\",\"categories\":[{\"id\":\"productores\",\"name\":\"Productores y Consumidores primarios\"},{\"id\":\"carnivoros\",\"name\":\"Carnívoros y Depredadores tope\"}],\"items\":[{\"id\":\"fitoplancton\",\"name\":\"Microalgas y fitoplancton unicelular\"},{\"id\":\"krill\",\"name\":\"Zooplancton y krill diminuto\"},{\"id\":\"tiburon_sedoso\",\"name\":\"Tiburón sedoso de aguas profundas\"},{\"id\":\"atun_aleta\",\"name\":\"Atún aleta amarilla veloz\"}],\"correctAnswers\":{\"fitoplancton\":\"productores\",\"krill\":\"productores\",\"tiburon_sedoso\":\"carnivoros\",\"atun_aleta\":\"carnivoros\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Flujo de Energía en el Mar")
                                .configurationJson("{\"instructions\":\"Organiza el traspaso de energía en una cadena trófica marina:\",\"steps\":[\"El fitoplancton capta la luz solar en las capas superficiales del mar\",\"Bancos de sardinas se alimentan del plancton marino\",\"Un grupo de delfines coopera para alimentarse del banco de sardinas\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Eslabones Tróficos Marinos")
                                .configurationJson("{\"instructions\":\"Ubica cada ser vivo en su rol alimenticio dentro del ecosistema oceánico:\",\"targets\":[{\"zone\":\"productor\",\"name\":\"Productor fotosintético\"},{\"zone\":\"herbivoro\",\"name\":\"Consumidor herbívoro\"},{\"zone\":\"depredador\",\"name\":\"Depredador tope de cúspide\"}],\"elements\":[{\"id\":\"alga_verde\",\"name\":\"Microalgas del plancton\"},{\"id\":\"tortuga_verde\",\"name\":\"Tortuga verde marina comelona de pastos\"},{\"id\":\"tiburon_tigre\",\"name\":\"Tiburón tigre de Malpelo\"}],\"correctAnswers\":{\"alga_verde\":\"productor\",\"tortuga_verde\":\"herbivoro\",\"tiburon_tigre\":\"depredador\"},\"difficulty\":\"Intermedio - Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 6:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("Luces en la Oscuridad Abisal")
                                .configurationJson("{\"question\":\"¿Cómo se llama la asombrosa capacidad de los seres de las profundidades marinas de producir su propia luz?\",\"options\":[\"Bioluminiscencia producida por reacciones químicas naturales\",\"Fosforescencia artificial con pilas sumergibles\",\"Electricidad de tormentas que queda guardada en la piel\"],\"correctAnswer\":\"Bioluminiscencia producida por reacciones químicas naturales\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("La Gran Fosa del Pacífico")
                                .configurationJson("{\"statement\":\"Frente a las costas del Pacífico colombiano existen fosas submarinas que superan los 4.000 metros de profundidad sin luz solar.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Superficie Iluminada vs Zona Abisal")
                                .configurationJson("{\"instructions\":\"Clasifica a las criaturas marinas según habiten la superficie iluminada o la profundidad abisal:\",\"categories\":[{\"id\":\"superficie\",\"name\":\"Zona iluminada (0 a 200 m)\"},{\"id\":\"abisal\",\"name\":\"Zona profunda y oscura (> 1.000 m)\"}],\"items\":[{\"id\":\"pez_mariposa\",\"name\":\"Pez mariposa de arrecife\"},{\"id\":\"langosta_espinosa\",\"name\":\"Langosta espinosa de aguas someras\"},{\"id\":\"pez_rape\",\"name\":\"Pez rape abisal con linterna en la frente\"},{\"id\":\"calamar_gigante\",\"name\":\"Calamar gigante de ojos deslumbrantes\"}],\"correctAnswers\":{\"pez_mariposa\":\"superficie\",\"langosta_espinosa\":\"superficie\",\"pez_rape\":\"abisal\",\"calamar_gigante\":\"abisal\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Descenso a las Profundidades")
                                .configurationJson("{\"instructions\":\"Ordena los estratos marinos desde la superficie soleada hasta el cañón oscuro:\",\"steps\":[\"Zona iluminada con arrecifes de coral y tortugas nadando libremente\",\"Zona de penumbra crepuscular donde la luz solar comienza a extinguirse\",\"Llanura abisal helada con presiones titánicas y criaturas bioluminiscentes\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Adaptaciones al Abismo Marino")
                                .configurationJson("{\"instructions\":\"Asigna cada adaptación anatómica extrema a su función biológica:\",\"targets\":[{\"zone\":\"linterna\",\"name\":\"Iluminación biológica\"},{\"zone\":\"ojos\",\"name\":\"Detección de presas\"},{\"zone\":\"cuerpo\",\"name\":\"Resistencia a presiones extremas\"}],\"elements\":[{\"id\":\"fotoforos\",\"name\":\"Fotóforos que emiten destellos luminosos para cazar\"},{\"id\":\"ojos_gigantes\",\"name\":\"Ojos enormes adaptados a captar la mínima sombra\"},{\"id\":\"cuerpo_gelatinoso\",\"name\":\"Cuerpo blando y gelatinoso sin huesos quebradizos\"}],\"correctAnswers\":{\"fotoforos\":\"linterna\",\"ojos_gigantes\":\"ojos\",\"cuerpo_gelatinoso\":\"cuerpo\"},\"difficulty\":\"Avanzado\"}")
                                .xpReward(20)
                                .orderIndex(5)
                                .build()
                );
            case 7:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("El Peligro de los Microplásticos")
                                .configurationJson("{\"question\":\"¿Por qué los microplásticos son una amenaza invisible pero mortal para los mares colombianos?\",\"options\":[\"Porque convierten el agua salada en agua con gas\",\"Porque los peces pequeños y moluscos los confunden con comida, acumulando tóxicos que llegan a nuestro plato\",\"Porque hacen que las olas sean demasiado suaves\"],\"correctAnswer\":\"Porque los peces pequeños y moluscos los confunden con comida, acumulando tóxicos que llegan a nuestro plato\",\"correctOption\":1,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Pez León Invasor")
                                .configurationJson("{\"statement\":\"El pez león es una especie invasora en el Caribe colombiano que depreda alevines nativos alterando el equilibrio de los arrecifes.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Amenazas Oceánicas vs Soluciones de Conservación")
                                .configurationJson("{\"instructions\":\"Clasifica los impactos destructivos frente a las acciones de protección marina:\",\"categories\":[{\"id\":\"amenaza\",\"name\":\"Amenaza marina\"},{\"id\":\"conservacion\",\"name\":\"Solución de conservación\"}],\"items\":[{\"id\":\"derrame_crudo\",\"name\":\"Derrame de hidrocarburos y petróleo\"},{\"id\":\"pesca_arrastre\",\"name\":\"Pesca de arrastre que arrasa el fondo marino\"},{\"id\":\"santuario_malpelo\",\"name\":\"Santuario de Fauna y Flora Malpelo protegido\"},{\"id\":\"vedas_pesca\",\"name\":\"Respeto a vedas de reproducción y tallas mínimas\"}],\"correctAnswers\":{\"derrame_crudo\":\"amenaza\",\"pesca_arrastre\":\"amenaza\",\"santuario_malpelo\":\"conservacion\",\"vedas_pesca\":\"conservacion\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Ruta del Plástico hasta el Océano")
                                .configurationJson("{\"instructions\":\"Ordena cómo una basura de ciudad llega a dañar la fauna marina:\",\"steps\":[\"Una botella o bolsa plástica es arrojada a un arroyo o calle de la ciudad\",\"Las corrientes fluviales la transportan al mar donde el sol la fragmenta en microplásticos\",\"Peces y tortugas ingieren los fragmentos plásticos sufriendo desnutrición y muerte\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Defensa de las Aguas Colombianas")
                                .configurationJson("{\"instructions\":\"Asigna cada amenaza con su respuesta de protección ambiental:\",\"targets\":[{\"zone\":\"rios_ciudad\",\"name\":\"En las desembocaduras de ríos\"},{\"zone\":\"playas\",\"name\":\"En playas y puertos costeros\"},{\"zone\":\"altamar\",\"name\":\"En áreas marinas protegidas\"}],\"elements\":[{\"id\":\"trampas_basura\",\"name\":\"Barreras y trampas retenedoras de residuos plásticos\"},{\"id\":\"limpiezas_playa\",\"name\":\"Brigadas ciudadanas de limpieza comunitaria de costas\"},{\"id\":\"patrullas_guardacostas\",\"name\":\"Monitoreo de guardacostas contra pesca ilegal extranjera\"}],\"correctAnswers\":{\"trampas_basura\":\"rios_ciudad\",\"limpiezas_playa\":\"playas\",\"patrullas_guardacostas\":\"altamar\"},\"difficulty\":\"Avanzado - Difícil\"}")
                                .xpReward(25)
                                .orderIndex(5)
                                .build()
                );
            case 8:
                return List.of(
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.MULTIPLE_CHOICE)
                                .title("La Meta de Protección Marina")
                                .configurationJson("{\"question\":\"¿Qué importante logro ambiental alcanzó Colombia protegiendo sus mares?\",\"options\":[\"Pavimentar el fondo del mar Caribe con cemento\",\"Declarar más del 30% de sus áreas marinas protegidas en santuarios y reservas\",\"Prohibir que los peces naden cerca de las playas\"],\"correctAnswer\":\"Declarar más del 30% de sus áreas marinas protegidas en santuarios y reservas\",\"correctOption\":1,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(1)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.TRUE_FALSE)
                                .title("El Compromiso del Consumidor")
                                .configurationJson("{\"statement\":\"Rechazar plásticos de un solo uso en cualquier ciudad de Colombia evita que toneladas de basura lleguen a los dos océanos.\",\"options\":[\"Verdadero\",\"Falso\"],\"correctAnswer\":\"Verdadero\",\"correctOption\":0,\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(2)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.CLASSIFICATION)
                                .title("Acciones del Guardián del Mar vs Destrucción Marina")
                                .configurationJson("{\"instructions\":\"Distingue las acciones de un Guardián Oceánico frente a conductas destructivas:\",\"categories\":[{\"id\":\"guardian\",\"name\":\"Acción de Guardián Oceánico\"},{\"id\":\"destructiva\",\"name\":\"Conducta dañina\"}],\"items\":[{\"id\":\"rechazar_pitillos\",\"name\":\"Rechazar pitillos, cubiertos y envases plásticos desechables\"},{\"id\":\"consumo_responsable\",\"name\":\"Consumir pescado capturado de manera artesanal y legal\"},{\"id\":\"llevar_coral\",\"name\":\"Arrancar pedazos de coral o caracoles vivos de adorno\"},{\"id\":\"acosar_tortugas\",\"name\":\"Perseguir y tocar tortugas o tiburones al bucear\"}],\"correctAnswers\":{\"rechazar_pitillos\":\"guardian\",\"consumo_responsable\":\"guardian\",\"llevar_coral\":\"destructiva\",\"acosar_tortugas\":\"destructiva\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(3)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.ORDERING)
                                .title("Restauración de Arrecifes de Coral")
                                .configurationJson("{\"instructions\":\"Ordena los pasos del proyecto colombiano 'Un Millón de Corales':\",\"steps\":[\"Cultivar fragmentos saludables de coral en guarderías submarinas flotantes\",\"Cuidar que los fragmentos crezcan fuertes y sanos libres de algas invasoras\",\"Trasplantar los corales maduros a arrecifes degradados devolviendo la vida marina\"],\"correctSequence\":[0,1,2],\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(4)
                                .build(),
                        ActivityEntity.builder()
                                .lesson(lesson)
                                .type(ActivityType.DRAG_AND_DROP)
                                .title("Santuarios Marinos de Colombia")
                                .configurationJson("{\"instructions\":\"Ubica cada gran joya marina protegida en su litoral correspondiente:\",\"targets\":[{\"zone\":\"pacifico\",\"name\":\"Pacífico profundo (Santuario Malpelo)\"},{\"zone\":\"caribe_insular\",\"name\":\"Caribe insular (Reserva Seaflower)\"},{\"zone\":\"costa_pacifica\",\"name\":\"Ensenada chocoana (Parque Utría)\"}],\"elements\":[{\"id\":\"tiburones_malpelo\",\"name\":\"Capital mundial de tiburones martillo y sedosos\"},{\"id\":\"tercera_barrera\",\"name\":\"Gran barrera arrecifal y aguas de siete colores\"},{\"id\":\"ballenatos_utria\",\"name\":\"Guardería y cuna de nacimiento de ballenas jorobadas\"}],\"correctAnswers\":{\"tiburones_malpelo\":\"pacifico\",\"tercera_barrera\":\"caribe_insular\",\"ballenatos_utria\":\"costa_pacifica\"},\"difficulty\":\"Experto (Desafío Guardián)\"}")
                                .xpReward(30)
                                .orderIndex(5)
                                .build()
                );
            default:
                return List.of();
        }
    }
}
