package com.hurl.toolkit.spider.impl;

import com.hurl.toolkit.spider.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hurongliang on 16/7/16.
 */
public class BasicRequest implements Request {
    private Map<String,Object> fields = new HashMap<>();
    private String uri;
    public BasicRequest(String uri){
        this.uri = uri;
    }

    public Object getField(String key){
        return fields.get(key);
    }
    public void setField(String key, Object value){
        fields.put(key, value);
    }

    @Override
    public String getUrl() {
        return this.uri;
    }
    
    public String toString(){
    	return "BasicRequest[" + this.uri + "]";
    }
}
