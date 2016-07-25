package com.hurl.toolkit.http;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
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
		if(LOG.isDebugEnabled()) {
			LOG.debug("HTTP REQUEST: " + request.getURI());
		}
		String res = request(request);
		if(LOG.isDebugEnabled()){
			if(res != null && res.length() > 200){
				LOG.debug("HTTP RESPONSE: " + StringUtils.replace(res.substring(0, 200) + "...", "\n", ""));
			} else {
				LOG.debug("HTTP RESPONSE: " + StringUtils.replace(res, "\n", ""));
			}
		}
		return res;
	}
    public static String post(URI uri, String body) {
        return httpOption(new HttpPost(uri), body);
    }
    public static String get(URI uri, String body) {
        return httpOption(new HttpGet(uri), body);
    }
    public static String delete(URI uri, String body) {
        return httpOption(new HttpDelete(uri), body);
    }
    public static String put(URI uri, String body) {
        return httpOption(new HttpPut(uri), body);
    }
	public static String post(String host, String path, UrlParams urlParams, String body) {
		return httpOption(new HttpPost(buildURI(host, path, urlParams)), body);
	}
	public static String put(String host, String path, UrlParams urlParams, String body) {
		return httpOption(new HttpPut(buildURI(host, path, urlParams)), body);
	}
	public static String get(String host, String path, UrlParams urlParams) {
		return httpOption(new HttpGet(buildURI(host, path, urlParams)), null);
	}
	public static String delete(String host, String path, UrlParams urlParams) {
		return httpOption(new HttpDelete(buildURI(host, path, urlParams)), null);
	}

	private static String request(HttpUriRequest request) {
        List<Header> defaultHeaders = new ArrayList<>();
        defaultHeaders.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2"));
		CloseableHttpClient client = HttpClients.custom()
                .setDefaultHeaders(defaultHeaders).build();
		try {
			String res = client.execute(request, new StringResponseHandler());
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static URI buildURI(String host, String path, UrlParams params) {
		URIBuilder builder = new URIBuilder()
				.setScheme("http")
				.setHost(host)
				.setPath(path)
				.setParameters(params.toNameValuePair());
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
