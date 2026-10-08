package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LevelRestMapperImpl.class)
public class LevelRestMapperTest {

    @Autowired
    private LevelRestMapper mapper;

    @Test
    void testContextLoads() {
        assertNotNull(mapper);
    }
}
