package com.hurl.toolkit.spider;

import java.net.URI;

/**
 * Created by hurongliang on 16/7/16.
 */
public interface Request {
    String getUrl();
    Object getField(String key);
}
