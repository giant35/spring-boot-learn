package com.telsafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tangfh
 * @date 2021/9/15
 */
@SpringBootTest
class MemTodoServiceImplTest {
    @Autowired
    TodoService s;

    @Test
    void ss() {
        s.add(null);
    }
}