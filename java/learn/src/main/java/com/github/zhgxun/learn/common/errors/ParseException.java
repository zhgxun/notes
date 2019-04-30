package com.github.zhgxun.learn.common.errors;

public class ParseException extends RuntimeException {

    private static final long serialVersionUID = 7024922981724604702L;

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }
}
