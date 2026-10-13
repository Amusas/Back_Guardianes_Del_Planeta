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
                                new LessonInfo("Árboles nativos", "Aprende sobre especies emblemáticas como el roble andino y la palma de cera.", 1, 50),
                                new LessonInfo("Mamíferos andinos", "Conoce la fauna de montaña, como el oso de anteojos y la danta de páramo.", 2, 50),
                                new LessonInfo("Cuidado del sotobosque", "Descubre la importancia del musgo, helechos y microhábitats de suelo.", 3, 50)
                        )
                ),
                new EcosystemData(
                        "PARAMO",
                        "Páramo",
                        "Conservación del agua y fábrica natural de ríos. Criterio de desbloqueo: Completar Bosque Andino (Lección 2) + Nivel 1.",
                        1,
                        "FRAILEJON_GUANE",
                        List.of(
                                new LessonInfo("El ciclo del agua en la niebla", "Cómo la niebla paramera se transforma en agua potable.", 1, 50),
                                new LessonInfo("Función del frailejón", "Estructura esponjosa del frailejón y retención de humedad.", 2, 50),
                                new LessonInfo("Protección de nacederos", "Cuidado de lagunas glaciares y ríos nacientes.", 3, 50)
                        )
                ),
                new EcosystemData(
                        "OCEANO",
                        "Océano",
                        "Contaminación marina y microplásticos. Criterio de desbloqueo: Completar Páramo + Nivel 2.",
                        2,
                        "TORTUGA_MARINA",
                        List.of(
                                new LessonInfo("Arrecifes de coral", "Estructuras vivas marinas y su rol protegiendo costas.", 1, 50),
                                new LessonInfo("Impacto del plástico", "Peligro de redes fantasma y degradación a microplásticos.", 2, 50),
                                new LessonInfo("Vida marina protegida", "Tortugas, cetáceos y manglares como refugio pelágico.", 3, 50)
                        )
                ),
                new EcosystemData(
                        "SELVA_AMAZONICA",
                        "Selva Amazónica",
                        "Deforestación y pulmón del planeta. Criterio de desbloqueo: Completar Océano + Nivel 3.",
                        3,
                        "ANACONDA_VERDE",
                        List.of(
                                new LessonInfo("La masa forestal", "Dosel arbóreo, regulación climática y lluvias voladoras.", 1, 50),
                                new LessonInfo("Cadenas tróficas selváticas", "Depredadores tope y equilibrio de presas.", 2, 50),
                                new LessonInfo("Amenazas de la tala", "Impacto de la ganadería extensiva e incendios forestales.", 3, 50)
                        )
                ),
                new EcosystemData(
                        "MANGLAR",
                        "Manglar",
                        "Protección costera y cunas de vida acuática. Criterio de desbloqueo: Completar Selva Amazónica + Nivel 3.",
                        3,
                        "CANGREJO",
                        List.of(
                                new LessonInfo("Raíces en agua salada", "Mangle rojo, raíces zancudas y filtración de sedimentos.", 1, 50),
                                new LessonInfo("Barreras contra tormentas", "Disipación de oleaje extremo y huracanes costeros.", 2, 50),
                                new LessonInfo("Fauna de estuario", "Moluscos, peces juveniles y aves migratorias marinas.", 3, 50)
                        )
                ),
                new EcosystemData(
                        "BOSQUE_SECO_TROPICAL",
                        "Bosque Seco Tropical",
                        "Adaptación a la sequía y especies endémicas. Criterio de desbloqueo: Completar Manglar + Nivel 4.",
                        4,
                        "IGUANA_VERDE",
                        List.of(
                                new LessonInfo("Flora decidua", "Caída de hojas para evitar pérdida de humedad en temporada seca.", 1, 50),
                                new LessonInfo("Conservación del suelo seco", "Suelos calcáreos y retención de nutrientes escasos.", 2, 50),
                                new LessonInfo("Estrategias de adaptación", "Fauna con hábitos crepusculares y tolerancia térmica.", 3, 50)
                        )
                ),
                new EcosystemData(
                        "LLANOS_SABANA",
                        "Llanos y Sabana",
                        "Humedales estacionales y fauna de pradera. Criterio de desbloqueo: Completar Bosque Seco + Nivel 5.",
                        5,
                        "CHIGUIRO",
                        List.of(
                                new LessonInfo("Dinámica de sequía e inundación", "Régimen bimodal de esteros y sabanas inundables.", 1, 50),
                                new LessonInfo("Aves migratorias", "Corredor aéreo de garzas, corocoras y patos silvestres.", 2, 50),
                                new LessonInfo("Coexistencia con la fauna", "Conservación de chigüiros, osos hormigueros y venados.", 3, 50)
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

            // Actualizar si ya existía sin nivel o atributos
            if (ecoEntity.getRequiredLevel() == null) {
                ecoEntity.setRequiredLevel(requiredLevel);
                ecoEntity.setMascotKey(ecoData.mascotKey());
                ecosystemRepository.save(ecoEntity);
            }

            // Sembrar lecciones si no existen
            List<LessonEntity> existingLessons = lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(ecoEntity.getId());
            if (existingLessons.isEmpty()) {
                for (LessonInfo lessonInfo : ecoData.lessons()) {
                    LessonEntity lesson = lessonRepository.save(LessonEntity.builder()
                            .ecosystem(ecoEntity)
                            .title(lessonInfo.title())
                            .content(lessonInfo.content())
                            .orderIndex(lessonInfo.orderIndex())
                            .xpReward(lessonInfo.xpReward())
                            .isActive(true)
                            .build());

                    // Actividad interactiva por defecto para la lección
                    activityRepository.save(ActivityEntity.builder()
                            .lesson(lesson)
                            .type(ActivityType.MULTIPLE_CHOICE)
                            .title("Pregunta clave: " + lessonInfo.title())
                            .configurationJson("{\"question\":\"¿Cuál es la importancia ambiental de este tema?\",\"options\":[\"Opción A: Destrucción sin control\",\"Opción B: Protección y equilibrio de la vida\",\"Opción C: Ninguna relevancia\"],\"correctOption\":1}")
                            .xpReward(10)
                            .orderIndex(1)
                            .build());
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
