package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;

public interface CheckHealthUseCase {

    DatabaseHealth checkHealth();
}
