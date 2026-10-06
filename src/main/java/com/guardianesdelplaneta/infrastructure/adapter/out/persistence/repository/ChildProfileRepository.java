package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildProfileRepository extends JpaRepository<ChildProfileEntity, Long> {

    Optional<ChildProfileEntity> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
