package com.telsafe;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoEntity {
    private Integer id;
    private String note;
    private Boolean done;
    private LocalDateTime createAt;
}
