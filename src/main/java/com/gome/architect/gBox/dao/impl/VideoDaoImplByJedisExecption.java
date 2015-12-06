package com.gome.architect.gBox.dao.impl;

/**
 * Created by lqx on 2015/11/30.
 */
public class VideoDaoImplByJedisExecption extends Exception{
    protected Throwable cause = null;

    public VideoDaoImplByJedisExecption(String message) {
        super(message);
    }

    public VideoDaoImplByJedisExecption(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public VideoDaoImplByJedisExecption(Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
