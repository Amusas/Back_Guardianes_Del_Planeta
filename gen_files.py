import os

files = {
    'src/main/java/com/guardianesdelplaneta/domain/port/out/AchievementPersistencePort.java': '''package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Achievement;
import java.util.List;
import java.util.Optional;

public interface AchievementPersistencePort {
    List<Achievement> findAllActive();
    Optional<Achievement> findById(Long id);
    Optional<Achievement> findByCode(String code);
}
''',
    'src/main/java/com/guardianesdelplaneta/domain/port/out/ChildAchievementPersistencePort.java': '''package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.ChildAchievement;
import java.util.List;
import java.util.Optional;

public interface ChildAchievementPersistencePort {
    List<ChildAchievement> findByChildId(Long childId);
    Optional<ChildAchievement> findByChildIdAndAchievementId(Long childId, Long achievementId);
    boolean existsByChildIdAndAchievementId(Long childId, Long achievementId);
    ChildAchievement save(ChildAchievement childAchievement);
}
''',
    'src/main/java/com/guardianesdelplaneta/infrastructure/adapter/out/persistence/AchievementPersistenceAdapter.java': '''package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.AchievementPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.AchievementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AchievementPersistenceAdapter implements AchievementPersistencePort {

    private final AchievementRepository achievementRepository;
    private final AchievementPersistenceMapper achievementPersistenceMapper;

    public AchievementPersistenceAdapter(AchievementRepository achievementRepository, AchievementPersistenceMapper achievementPersistenceMapper) {
        this.achievementRepository = achievementRepository;
        this.achievementPersistenceMapper = achievementPersistenceMapper;
    }

    @Override
    public List<Achievement> findAllActive() {
        return achievementRepository.findByIsActiveTrue().stream()
                .map(achievementPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Achievement> findById(Long id) {
        return achievementRepository.findById(id).map(achievementPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Achievement> findByCode(String code) {
        return achievementRepository.findByCode(code).map(achievementPersistenceMapper::toDomain);
    }
}
''',
    'src/main/java/com/guardianesdelplaneta/infrastructure/adapter/out/persistence/ChildAchievementPersistenceAdapter.java': '''package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildAchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildAchievementPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildAchievementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChildAchievementPersistenceAdapter implements ChildAchievementPersistencePort {

    private final ChildAchievementRepository childAchievementRepository;
    private final ChildAchievementPersistenceMapper childAchievementPersistenceMapper;

    public ChildAchievementPersistenceAdapter(ChildAchievementRepository childAchievementRepository, ChildAchievementPersistenceMapper childAchievementPersistenceMapper) {
        this.childAchievementRepository = childAchievementRepository;
        this.childAchievementPersistenceMapper = childAchievementPersistenceMapper;
    }

    @Override
    public List<ChildAchievement> findByChildId(Long childId) {
        return childAchievementRepository.findByChildId(childId).stream()
                .map(childAchievementPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChildAchievement> findByChildIdAndAchievementId(Long childId, Long achievementId) {
        return childAchievementRepository.findByChildIdAndAchievementId(childId, achievementId)
                .map(childAchievementPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByChildIdAndAchievementId(Long childId, Long achievementId) {
        return childAchievementRepository.existsByChildIdAndAchievementId(childId, achievementId);
    }

    @Override
    public ChildAchievement save(ChildAchievement childAchievement) {
        ChildAchievementEntity entity = childAchievementPersistenceMapper.toEntity(childAchievement);
        return childAchievementPersistenceMapper.toDomain(childAchievementRepository.save(entity));
    }
}
''',
    'src/main/java/com/guardianesdelplaneta/domain/port/in/GetAchievementsUseCase.java': '''package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import java.util.List;

public interface GetAchievementsUseCase {
    List<Achievement> getAllActiveAchievements();
    List<Achievement> getUnlockedAchievements(Long userId);
    ChildAchievement getAchievementStatus(Long userId, Long achievementId);
}
''',
    'src/main/java/com/guardianesdelplaneta/domain/port/in/GetStreakUseCase.java': '''package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Streak;
import java.util.List;
import java.util.Optional;

public interface GetStreakUseCase {
    Optional<Streak> getCurrentStreak(Long userId);
    List<Streak> getStreakHistory(Long userId);
}
''',
    'src/main/java/com/guardianesdelplaneta/domain/exception/AchievementNotFoundException.java': '''package com.guardianesdelplaneta.domain.exception;

public class AchievementNotFoundException extends DomainException {
    public AchievementNotFoundException(String message) {
        super(message);
    }
}
''',
}

for path, content in files.items():
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
print("Files generated successfully.")
