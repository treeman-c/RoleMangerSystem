package com.treeman.unit;


import java.io.Serializable;

/**
 * @author .
 * @param <T>
 */

public class ResultCode<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public ResultCode() {
    }

    public ResultCode(Type type, String message) {
        this.code = type.value;
        this.message = message;
    }

    public ResultCode(Type type, String message, T data) {
        this.code = type.value;
        this.message = message;
        if (data != null) {
            this.data = data;
        }
    }

    public static <T> ResultCode<T> ok() {
        return ok("操作成功", (T) null);
    }

    public static <T> ResultCode<T> ok(T data) {
        return ok("操作成功", data);
    }

    public static <T> ResultCode<T> ok(String message, T data) {
        return new ResultCode<T>(Type.SUCCESS, message, data);
    }

    public static <T> ResultCode<T> warn(String message) {
        return warn(message, (T) null);
    }

    public static <T> ResultCode<T> warn(String message, T data) {
        return new ResultCode<T>(Type.WARN, message, data);
    }

    public static <T> ResultCode<T> unAuth() {
        return new ResultCode<T>(Type.UNAUTH, "未登陆", (T) null);
    }

    public static <T> ResultCode<T> failed() {
        return failed("操作失败");
    }

    public static <T> ResultCode<T> failed(String message) {
        return failed(message, (T) null);
    }

    public static <T> ResultCode<T> failed(String message, T data) {
        return new ResultCode<T>(Type.ERROR, message, data);
    }

    public static enum Type {
        //成功
        SUCCESS(200),
        //警告
        WARN(402),
        //未知
        UNAUTH(401),
        //错误
        ERROR(500);

        private final int value;

        private Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
