package com.hurl.toolkit.http;

import org.junit.Test;

import com.hurl.toolkit.http.HttpUtil;
import com.hurl.toolkit.http.Options.Method;

public class HttpUtilTest {
//	@Test
	public void get(){
		System.out.println(HttpUtil.request("localhost:9200", "/", null, Options.getDefault().setMethod(Method.GET)));
	}
	@Test
	public void put(){
		System.out.println(HttpUtil.request("localhost:9200", "/cars", null, Options.getDefault().setMethod(Method.PUT)));
	}
}
