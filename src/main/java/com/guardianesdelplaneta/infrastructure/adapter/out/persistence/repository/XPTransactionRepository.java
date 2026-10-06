package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository;

import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.XPTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XPTransactionRepository extends JpaRepository<XPTransactionEntity, Long> {

    List<XPTransactionEntity> findByChildIdOrderByCreatedAtDesc(Long childId);
}
