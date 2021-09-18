package com.telsafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceIT {
    @Autowired
    TodoService s;

    @Transactional
    @Test
    void test() {
        final int id;
        {
            var t = new Todo();
            t.setDone(false);
            t.setNote("买白菜");
            id = s.add(t);
            assertTrue(id > 0);
            System.out.println("id:" + id);
        }
        {
            var t = s.get(id);
            assertNotNull(t);
            System.out.println("t:" + t);
        }
        {
            var list = s.list(1, 10);
            assertNotNull(list);
            assertTrue(!list.isEmpty());
            list.forEach(System.out::println);
        }
        {
            s.delete(id);
        }

    }
}