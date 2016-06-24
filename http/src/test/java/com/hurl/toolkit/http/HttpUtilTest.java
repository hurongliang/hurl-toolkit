package com.hurl.toolkit.http;

import org.junit.Test;

import com.hurl.toolkit.http.HttpUtil;

public class HttpUtilTest {
//	@Test
	public void get(){
		System.out.println(HttpUtil.get("localhost:9200", "/", null));
	}
	@Test
	public void put(){
		System.out.println(HttpUtil.put("localhost:9200", "/cars", null, null));
	}
}
