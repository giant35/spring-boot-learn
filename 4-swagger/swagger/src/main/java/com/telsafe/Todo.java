package com.telsafe;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author tangfh
 * @date 2021/9/14
 */
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "待办事项")
public class Todo {
    @ApiModelProperty("id 标识")//TODO:!!!
    private Integer id;
    private String note;
    private Boolean done;
    //需要配置 private LocalDateTime date;
}
