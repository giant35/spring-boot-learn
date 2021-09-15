package com.telsafe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
@RequestMapping("todo")
@Slf4j
public class TodoResource {
    @Autowired
    TodoService todoService;

    @PostMapping
    public Todo add(
            @RequestBody @Valid Todo todo
    ) {
        int id = todoService.add(todo);
        log.info("添加 todo: ", todo.getNote());
        return todoService.get(id);
    }

    @PutMapping("{id}")
    public Todo update(
            @PathVariable(name = "id") int id,
            @RequestBody @Valid Todo todo
    ) {
        todoService.update(id, todo);
        return todoService.get(id);
    }

    @GetMapping("{id}")
    public Todo get(
            @PathVariable(name = "id") Integer id
    ) {
        var todo = todoService.get(id);
        return todo;
    }

    @GetMapping
    public List<Todo> list(
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return todoService.list(pageNo, pageSize);
    }


    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") Integer id
    ) {
        todoService.delete(id);
    }
}
