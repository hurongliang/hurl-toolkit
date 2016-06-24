package com.hurl.toolkit.json;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static String toString(Object object){
		if(object instanceof List) {
			return JSONArray.fromObject(object).toString();
		} else {
			return JSONObject.fromObject(object).toString();
		}
	}
}
