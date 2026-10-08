package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = GuardianChildRestMapperImpl.class)
public class GuardianChildRestMapperTest {

    @Autowired
    private GuardianChildRestMapper mapper;

    @Test
    void testContextLoads() {
        assertNotNull(mapper);
    }
}
