package com.hurl.toolkit.http;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

	private static String httpOption(HttpUriRequest request, String body) {
		if(StringUtils.isNotEmpty(body)) {
			StringEntity entity = new StringEntity(body, ContentType.create("text/plain", "utf-8"));
			if (request instanceof HttpPut) {
				((HttpPut)request).setEntity(entity);
			} else if (request instanceof HttpPost){
				((HttpPost)request).setEntity(entity);
			}
		}
		LOG.debug("HTTP " + request.getMethod() + " " + request.getURI() + " " + (body == null ? "" : StringUtils.replace(body, "\n", "")));
		String res = request(request);
		LOG.debug("HTTP RESPONSE " + request.getURI() + " " + StringUtils.replace(res, "\n", ""));
		return res;
	}
	public static String post(String host, String path, Map<String, String> params, String body) {
		return httpOption(new HttpPost(buildURI(host, path, params)), body);
	}
	public static String put(String host, String path, Map<String, String> params, String body) {
		return httpOption(new HttpPut(buildURI(host, path, params)), body);
	}
	public static String get(String host, String path, Map<String, String> params) {
		return httpOption(new HttpGet(buildURI(host, path, params)), null);
	}
	public static String delete(String host, String path, Map<String, String> params) {
		return httpOption(new HttpDelete(buildURI(host, path, params)), null);
	}

	private static String request(HttpUriRequest request) {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			String res = client.execute(request, new StringResponseHandler());
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static URI buildURI(String host, String path, Map<String, String> params) {
		URIBuilder builder = new URIBuilder().setScheme("http").setHost(host).setPath(path);
		if(params != null && params.size() > 0){
			for(String key : params.keySet()){
				builder.addParameter(key, params.get(key));
			}
		}
		try{
			return builder.build();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private static class StringResponseHandler implements ResponseHandler<String>{
		@Override
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			String body = "";
			HttpEntity entity = response.getEntity();
			if(entity != null){
				body = EntityUtils.toString(entity);
			}
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new RuntimeException(response.getStatusLine() + body);
			} else {
				return body;
			}
		}
		
	}
}
