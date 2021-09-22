package com.telsafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemTodoServiceImplTest {
    @Autowired
    MemTodoServiceImpl s;

    @Test
    void s() {
        var list = s.list(1, 10);
        assertTrue(!list.isEmpty());
    }

}