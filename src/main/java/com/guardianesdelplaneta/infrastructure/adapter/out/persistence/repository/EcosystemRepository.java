package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.EcosystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EcosystemRepository extends JpaRepository<EcosystemEntity, Long> {

    Optional<EcosystemEntity> findByCode(String code);

    List<EcosystemEntity> findByIsActiveTrue();

    boolean existsByCode(String code);
}
