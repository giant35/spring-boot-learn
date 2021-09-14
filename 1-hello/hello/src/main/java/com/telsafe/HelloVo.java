package com.telsafe;

/**
 * @author tangfh
 * @date 2021/9/14
 */
public class HelloVo {
    private String msg;
    private String user;

    public HelloVo(final String msg, final String user) {
        this.msg = msg;
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public String getUser() {
        return user;
    }

}
