package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;

public interface DatabaseHealthPort {

    DatabaseHealth pingDatabase();
}
