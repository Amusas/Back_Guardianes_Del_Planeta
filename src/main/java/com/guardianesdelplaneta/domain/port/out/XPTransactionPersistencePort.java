package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.XPTransaction;
import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;

public interface XPTransactionPersistencePort {
    XPTransaction save(XPTransaction transaction);
    boolean existsByChildIdAndTypeAndReferenceId(Long childId, XPTransactionType type, Long referenceId);
}
