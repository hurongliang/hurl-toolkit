package com.hurl.toolkit.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	static{
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	public static String toString(Object object){
		if(object == null) return null;
		if(object instanceof String) return object.toString();
		try{
			return mapper.writeValueAsString(object);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static <T> T toBean(String value, Class<T> cls){
		if(value == null) return null;
		try{
			return mapper.readValue(value, cls);
		} catch(Exception e){
			e.printStackTrace();;
			return null;
		}
	}
}
