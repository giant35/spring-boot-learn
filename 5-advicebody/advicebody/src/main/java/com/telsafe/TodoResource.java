package com.telsafe;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@RestController
@RequestMapping("todo")
@Api(tags = "待办事项")//TODO:!!!
public class TodoResource {

    @ApiOperation(value = "创建", notes = "详情补充")//TODO:!!!
    @PostMapping
    public Todo add(
            @RequestBody Todo todo
    ) {
        return todo;
    }

    @PutMapping("{id}")
    public Todo update(
            @PathVariable(name = "id") int id,
            @RequestBody Todo todo
    ) {
        return todo;
    }

    @GetMapping("{id}")
    public Todo get(
            @PathVariable(name = "id") Integer id
    ) {
        return new Todo(1, "测试", false);
    }

    @GetMapping
    public List<Todo> list(
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        var todo = new Todo(1, "测试", false);
        return List.of(todo);
    }


    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") Integer id
    ) {
    }
}
