package com.hurl.toolkit.elk;

import com.hurl.toolkit.elk.data.ESDocument;
import com.hurl.toolkit.elk.data.ESDocumentWithId;
import com.hurl.toolkit.http.HttpUtil;
import com.hurl.toolkit.http.UrlParams;
import com.hurl.toolkit.json.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hurongliang on 16/6/25.
 */
public class ES {
    public static String setRefreshInterval(String host, String index, String interval){
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> indexMap = new HashMap<>();
        param.put("index", indexMap);
        indexMap.put("refresh_interval", interval);
        return HttpUtil.put(host, "/" + index + "/_settings", UrlParams.empty(), JsonUtil.toString(param));
    }
    public static String bulk(String host, String index, String type, List< ? extends ESDocument> docs) {
        StringBuffer sb = new StringBuffer();
        for(ESDocument doc : docs){
            if(doc instanceof ESDocumentWithId) {
                sb.append("{\"index\" : {\"_id\" : \"" + ((ESDocumentWithId)doc).id() + "\"}}\n");
            }
            sb.append(JsonUtil.toString(doc) + "\n");
        }
        return HttpUtil.post(host, "/" + index + "/" + type + "/_bulk", UrlParams.empty(), sb.toString());
    }
    public static String setFieldMapping(String host, String index, String type, List<FieldMapping> mappings){
        Map<String, Object> typeMap = new HashMap<>();
        Map<String, Object> propertiesMap = new HashMap<>();
        typeMap.put("properties", propertiesMap);
        for(FieldMapping mapping : mappings){
            Map<String, Object> fieldMap = new HashMap<>();
            propertiesMap.put(mapping.getName(), fieldMap);
            if(mapping.getType() != null) fieldMap.put("type", mapping.getType());
            if(mapping.getIndex() != null) fieldMap.put("index", mapping.getIndex());
            if(mapping.isAddRaw()){
                Map<String, Object> fieldInFieldMap = new HashMap<>();
                fieldMap.put("fields", fieldInFieldMap);
                Map<String, Object> rawMap = new HashMap<>();
                fieldInFieldMap.put("raw", rawMap);
                rawMap.put("type", "string");
                rawMap.put("index", FieldMapping.INDEX_NOT_ANALYZED);
            }
            if(mapping.getFormat() != null) {
                fieldMap.put("format", mapping.getFormat());
            }
        }
        return HttpUtil.post(host, "/" + index + "/_mapping" + "/" + type , UrlParams.empty(), JsonUtil.toString(typeMap));
    }
    public static String deleteIndex(String host, String index){
        return HttpUtil.delete(host, "/" + index, UrlParams.empty());
    }
    public static String createIndex(String host, String index){
        return HttpUtil.post(host, "/" + index, UrlParams.empty(), null);
    }
    public static String clusterHealth(String host){
        return HttpUtil.get(host, "/_cluster/health", UrlParams.empty());
    }
    public static String status(String host){
        return HttpUtil.get(host, "/_cat/indices", UrlParams.empty().setParam("v","v"));
    }
    public static String openIndex(String host, String index){
        return HttpUtil.post(host, "/" + index + "/_open", UrlParams.empty(), null);
    }
    public static String closeIndex(String host, String index){
        return HttpUtil.post(host, "/" + index + "/_close", UrlParams.empty(), null);
    }
}
