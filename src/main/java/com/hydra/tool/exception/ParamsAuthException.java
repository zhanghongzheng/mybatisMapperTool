package com.hydra.tool.exception;

/**
 * Created by GongZheng on 15/6/4 下午11:32.
 * Describe:
 */
public class ParamsAuthException extends Exception {
    public ParamsAuthException() {
    }

    public ParamsAuthException(String message) {
        super(message);
    }

    public ParamsAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsAuthException(Throwable cause) {
        super(cause);
    }

    public ParamsAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
