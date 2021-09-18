package com.telsafe;

import com.telsafe.mapper.TodoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MybatisTodoServiceImpl implements TodoService {
    @Autowired
    TodoMapper mapper;

    @Override
    public List<Todo> list(int pageNo, int pageSize) {
        var list = mapper.select(null, (pageNo - 1) * pageSize, pageSize);
        var ret = list.stream().map(this::vo).collect(Collectors.toList());
        return ret;
    }

    @Override
    public int add(Todo todo) {
        TodoEntity e = new TodoEntity();
        BeanUtils.copyProperties(todo, e);
        e.setCreateAt(LocalDateTime.now());
        mapper.insert(e);
        return e.getId();
    }

    @Override
    public void update(int id, Todo todo) {
        var e = mapper.get(id);
        Objects.requireNonNull(e);
        BeanUtils.copyProperties(todo, e);
        mapper.update(e);
    }

    @Override
    public Todo get(int id) {
        var e = mapper.get(id);
        Todo ret = vo(e);
        return ret;
    }

    private Todo vo(TodoEntity e) {
        Objects.requireNonNull(e);
        var ret = new Todo();
        BeanUtils.copyProperties(e, ret);
        return ret;
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }
}
