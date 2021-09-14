package com.telsafe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@Service
public class MemTodoServiceImpl implements TodoService {
    List<Todo> list = Collections.synchronizedList(new ArrayList<>());
    int lastId = 0;

    @Override
    public List<Todo> list(final int pageNo, final int pageSize) {
        int offset = (pageNo - 1) * pageSize;
        if (offset > list.size()) {
            return List.of();
        }
        return list.subList(offset, Math.min(offset + pageSize, list.size()));
    }

    @Override
    public int add(final Todo todo) {
        Objects.requireNonNull(todo);
        todo.setId(++lastId);
        list.add(todo);
        return todo.getId();
    }

    @Override
    public void update(final int id, final Todo todo) {
        Objects.requireNonNull(todo);
        var oldTodo = get(id);
        var index = list.indexOf(oldTodo);
        todo.setId(id);
        list.set(index, todo);
    }

    @Override
    public Todo get(final int id) {
        var ret = list.stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().orElseThrow();
        return ret;
    }

    @Override
    public void delete(final int id) {
        list.removeIf(e -> Objects.equals(id, e.getId()));
    }
}
