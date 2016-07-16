package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
public class Page {
    private String url;
    private String raw;
    public Page(String url){
        this.url = url;
    }
    public void setRaw(String raw){
        this.raw = raw;
    }
    public String url(){
        return this.url;
    }
    public String raw(){
        return this.raw;
    }

}
