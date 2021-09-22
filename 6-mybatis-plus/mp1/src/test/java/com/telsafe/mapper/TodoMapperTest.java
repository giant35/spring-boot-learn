package com.telsafe.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.telsafe.TodoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tangfh
 * @date 2021/9/22
 */
@SpringBootTest
class TodoMapperTest {
    @Autowired
    TodoMapper mapper;

    @Test
    void select() {
        var list = mapper.select(null, 0, 10);
        list.forEach(System.out::println);
        Assertions.assertTrue(!list.isEmpty());
    }

    @Test
    void list() {
        final var pg = new Page<TodoEntity>(1, 10);
        final var query = Wrappers.<TodoEntity>query().gt("id", 0);
        var page = mapper.selectPage(pg, query);
        page.getRecords().forEach(System.out::println);
        Assertions.assertTrue(page.getTotal() > 0);
        Assertions.assertTrue(!page.getRecords().isEmpty());
    }
}