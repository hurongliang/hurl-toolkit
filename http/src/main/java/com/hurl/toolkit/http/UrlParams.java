package com.hurl.toolkit.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hurongliang on 16/7/2.
 */
public class UrlParams {
    Map<String, String> params = new HashMap<>();

    private UrlParams() {
    }

    public static UrlParams empty() {
        return new UrlParams();
    }

    public UrlParams setParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public List<NameValuePair> toNameValuePair() {
        List<NameValuePair> pairs = new ArrayList<>();
        if(!params.isEmpty()){
            params.keySet().forEach(key -> pairs.add(new BasicNameValuePair(key, params.get(key))));
        }
        return pairs;
    }
    public String toQueryString(){
        if(!params.isEmpty()){
            List<String> items = new ArrayList<>();
            params.keySet().forEach(key -> items.add(key + "=" + params.get(key)));
            return "?" + StringUtils.join(items, "&");
        }
        return "";
    }
}
