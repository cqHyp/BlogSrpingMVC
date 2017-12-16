package com.cqhpoldi.util;

public class Response {
    // Response 构造器
    private int code;
    private Object data;
    private String message;

    public void Response(int code, Object data, String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
