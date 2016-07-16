package com.hurl.toolkit.spider;

import java.io.Serializable;

/**
 * Created by hurongliang on 16/7/16.
 */
public class PageResult implements Serializable{
    private String url;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
