package com.telsafe;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author tangfh
 * @date 2021/9/14
 */
public class Todo {
    private Integer id;
    private String note;
    private Boolean done;
    //需要配置 private LocalDateTime date;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Todo(final Integer index, final String note, final Boolean done) {
        this.id = index;
        this.note = note;
        this.done = done;
    }

    public String getNote() {
        return note;
    }

    public Boolean getDone() {
        return done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public void setDone(final Boolean done) {
        this.done = done;
    }

}
