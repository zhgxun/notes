package com.github.zhgxun.learn.common.errors;

public class ParamException extends RuntimeException {

    private static final long serialVersionUID = -2325841603600440314L;

    public ParamException() {
    }

    public ParamException(String message) {
        super(message);
    }
}
