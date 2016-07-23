package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
public class Page {
    private Request request;
    private String raw;
    public Page(Request request){
        this.request = request;
    }
    public void setRaw(String raw){
        this.raw = raw;
    }
    public Request getRequest(){
        return this.request;
    }
    public String raw(){
        return this.raw;
    }
}
