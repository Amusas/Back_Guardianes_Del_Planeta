package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = XPTransactionPersistenceMapperImpl.class)
public class XPTransactionPersistenceMapperTest {

    @Autowired
    private XPTransactionPersistenceMapper mapper;

    @Test
    void testContextLoads() {
        assertNotNull(mapper);
    }
}
