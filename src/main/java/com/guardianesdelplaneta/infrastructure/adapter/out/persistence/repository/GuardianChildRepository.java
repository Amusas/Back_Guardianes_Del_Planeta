package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.GuardianChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuardianChildRepository extends JpaRepository<GuardianChildEntity, Long> {

    List<GuardianChildEntity> findByGuardianId(Long guardianId);

    List<GuardianChildEntity> findByChildId(Long childId);

    Optional<GuardianChildEntity> findByGuardianIdAndChildId(Long guardianId, Long childId);

    boolean existsByGuardianIdAndChildId(Long guardianId, Long childId);
}
