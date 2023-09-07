package com.el.authorization.domain.rsp;

public class Response<T> {
    private Integer status = 100;

    private String message = "SUCCESS";

    private T result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
        		"status=" + status +
        		", message=" + message +
        		", result=" + result +
        '}';
    }
}