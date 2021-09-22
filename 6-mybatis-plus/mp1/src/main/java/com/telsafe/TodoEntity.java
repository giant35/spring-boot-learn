package com.telsafe;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("todo")
public class TodoEntity {
    private Integer id;
    private String note;
    private Boolean done;
    private LocalDateTime createAt;
}
