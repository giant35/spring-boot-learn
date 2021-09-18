package com.telsafe;

import com.telsafe.mapper.TodoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TodoMapperTest {
    @Autowired
    TodoMapper mapper;

    @Test
    void test() {
        var t = todo();
        mapper.insert(t);
        Assertions.assertNotNull(t.getId());
        System.out.println("id:" + t.getId());

        var t2 = mapper.get(t.getId());
        System.out.println("t2:" + t2);


    }

    TodoEntity todo() {
        TodoEntity ret = new TodoEntity();
        ret.setDone(false);
        ret.setNote("买菠菜");
        ret.setCreateAt(LocalDateTime.now());
        return ret;
    }
}