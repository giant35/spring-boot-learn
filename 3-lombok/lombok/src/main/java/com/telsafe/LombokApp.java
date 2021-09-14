package com.telsafe;

/**
 * @author tangfh
 * @date 2021/9/14
 */
public class LombokApp {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(11);
        System.out.println("user.age:" + user.getAge());
    }
}
