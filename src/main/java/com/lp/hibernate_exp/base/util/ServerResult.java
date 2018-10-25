package com.lp.hibernate_exp.base.util;

import lombok.Data;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:42
 */
@Data
public class ServerResult {


    // 返回状态，true,返回结果正确，false，返回结果异常
    private boolean status;
    // 返回的状态码
    private Integer code;
    // 返回的状态信息
    private String message;
    // 返回的数据
    private Object data;

    public ServerResult() {
        this.status = false;
        this.code = -1;
        this.message = "操作失败";
        this.data = null;
    }

    public static ServerResult success(String message, Object data) {
        ServerResult sr = new ServerResult();
        sr.status = true;
        sr.code = 1;
        sr.message = message;
        sr.data = data;
        return sr;
    }

    public static ServerResult error(Integer code,String message){
        ServerResult sr = new ServerResult();
        sr.status = false;
        sr.code = code;
        sr.message = message;
        return sr;
    }
}
