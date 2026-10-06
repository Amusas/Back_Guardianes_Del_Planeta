package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import java.util.List;

public interface GetEcosystemsUseCase {
    List<Ecosystem> getActiveEcosystems();
    Ecosystem getEcosystemById(Long id);
    Ecosystem getEcosystemByCode(String code);
}
