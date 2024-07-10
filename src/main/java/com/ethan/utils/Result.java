package com.ethan.utils;

import java.io.Serializable;

/**
 * 返回数据
 * @author Johnson
 */
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public Result() {
        this.code = "200";
        this.message = "success";
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this.code = "200";
        this.message = "success";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
