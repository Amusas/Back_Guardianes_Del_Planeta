package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RewardPersistenceMapperImpl.class)
public class RewardPersistenceMapperTest {

    @Autowired
    private RewardPersistenceMapper mapper;

    @Test
    void testContextLoads() {
        assertNotNull(mapper);
    }
}
