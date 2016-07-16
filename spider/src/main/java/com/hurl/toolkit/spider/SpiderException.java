package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SpiderException extends Exception {
    public SpiderException(String message){
        super(message);
    }
    public SpiderException(String message, Throwable cause){
        super(message, cause);
    }
}
