package com.telsafe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangfh
 * @date 2021/9/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniResp<T> {
    int code;
    String msg;
    T data;
}
