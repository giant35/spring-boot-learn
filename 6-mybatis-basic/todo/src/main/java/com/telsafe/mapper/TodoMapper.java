package com.telsafe.mapper;

import com.telsafe.TodoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    void insert(TodoEntity todo);

    void update(TodoEntity todo);

    TodoEntity get(int id);

    void delete(int id);

    List<TodoEntity> select(Boolean done, int offset, int limit);
}
