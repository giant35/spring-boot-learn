package com.telsafe;

import java.util.List;

/**
 * @author tangfh
 * @date 2021/9/14
 */
public interface TodoService {
    List<Todo> list(int pageNo, int pageSize);

    int add(Todo todo);

    void update(final int id, Todo todo);

    Todo get(int id);

    void delete(int id);
}
