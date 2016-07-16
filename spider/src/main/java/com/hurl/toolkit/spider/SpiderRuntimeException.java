package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SpiderRuntimeException extends RuntimeException{
    public SpiderRuntimeException(String message){ super(message); }
    public SpiderRuntimeException(String message, Throwable cause){ super(message, cause); }
}
