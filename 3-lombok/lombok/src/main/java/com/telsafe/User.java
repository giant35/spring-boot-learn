package com.telsafe;

import lombok.*;

/**
 * @author tangfh
 * @date 2021/9/14
 */
///@Data
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private int age;
}
