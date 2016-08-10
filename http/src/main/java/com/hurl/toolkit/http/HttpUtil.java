package com.hurl.toolkit.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
	public static String request(String uristr, Options options){
		URI uri = null;
		try{
			uri = new URI(uristr);
		}catch(URISyntaxException e){
			throw new RuntimeException("invalid url " + uristr, e);
		}
		return request(uri, options);
	}
	public static String request(String host, String path, UrlParams urlParams, Options options){
		return request(buildURI(host, path, urlParams), options);
	}

	public static String request(URI uri, Options options) {
		HttpUriRequest request = null;
		switch(options.getMethod()){
		case GET:
			request = new HttpGet(uri);
			break;
		case PUT:
			request = new HttpPut(uri);
			break;
		case POST:
			request = new HttpPost(uri);
			break;
		case DELETE:
			request = new HttpDelete(uri);
			break;
		default:
			throw new RuntimeException("invalid method " + options.getMethod());
		}
		if(StringUtils.isNotEmpty(options.getBody())) {
			StringEntity entity = new StringEntity(options.getBody(), ContentType.create("text/plain", "utf-8"));
			if (request instanceof HttpPut) {
				((HttpPut)request).setEntity(entity);
			} else if (request instanceof HttpPost){
				((HttpPost)request).setEntity(entity);
			}
		}
		if(LOG.isDebugEnabled()) {
			LOG.debug("HTTP REQUEST: " + request.getURI());
		}
		String res = request(request, options);
		if(LOG.isDebugEnabled()){
			if(res != null && res.length() > 200){
				LOG.debug("HTTP RESPONSE: " + StringUtils.replace(res.substring(0, 200) + "...", "\n", ""));
			} else {
				LOG.debug("HTTP RESPONSE: " + StringUtils.replace(res, "\n", ""));
			}
		}
		return res;
	}

	private static String request(HttpUriRequest request, Options options) {
        List<Header> defaultHeaders = new ArrayList<>();
        defaultHeaders.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2"));
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultHeaders(defaultHeaders).build();
		try {
			String res = client.execute(request, new StringResponseHandler(options.getCharset()));
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
		private String charset;
		public StringResponseHandler(String charset){
			this.charset = charset;
		}
		@Override
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			String body = "";
			HttpEntity entity = response.getEntity();
			if(entity != null){
				body = EntityUtils.toString(entity, charset);
			}
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new RuntimeException(response.getStatusLine() + body);
			} else {
				return body;
			}
		}
		
	}
}
