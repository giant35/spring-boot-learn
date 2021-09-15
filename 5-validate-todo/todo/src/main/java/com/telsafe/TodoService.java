package com.telsafe;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author tangfh
 * @date 2021/9/14
 */
@Validated
public interface TodoService {
    List<Todo> list(int pageNo, int pageSize);

    int add(@NotNull @Valid Todo todo);

    void update(final int id, @NotNull @Valid Todo todo);

    Todo get(int id);

    void delete(int id);
}
