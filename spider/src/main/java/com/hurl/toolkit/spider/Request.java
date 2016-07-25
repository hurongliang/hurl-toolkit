package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
public interface Request {
    String getUrl();
    Object getField(String key);
}
