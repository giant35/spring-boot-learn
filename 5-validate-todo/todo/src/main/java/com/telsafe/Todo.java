package com.telsafe;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@Data
public class Todo {
    private Integer id;
    @NotBlank(message = "说明不能为空")
    private String note;
    @NotNull
    private Boolean done;
}
