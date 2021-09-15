package com.telsafe;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel(description = "待办事项")
/**
 * @author tangfh
 * @date 2021/9/14
 */
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @ApiModelProperty("idddd")
    private Integer id;
    private String note;
    private Boolean done;
    //需要配置 private LocalDateTime date;
}
