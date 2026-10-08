package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = EcosystemPersistenceMapperImpl.class)
public class EcosystemPersistenceMapperTest {

    @Autowired
    private EcosystemPersistenceMapper mapper;

    @Test
    void testContextLoads() {
        assertNotNull(mapper);
    }
}
