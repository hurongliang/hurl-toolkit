package com.hurl.toolkit.elk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hurongliang on 16/6/25.
 */
public class ESParams {
    private Map<String, String> map = new HashMap<>();
    public ESParams put(String key, String value){
        map.put(key, value);
        return this;
    }
    public Map<String,String> toMap(){
        map.put("pretty", "pretty");
        return map;
    }

}
