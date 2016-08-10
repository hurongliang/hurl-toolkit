package com.hurl.toolkit.http;

public class Options {
	public static enum Method{
		GET,PUT,POST,DELETE
	}
	private String charset;
	private Method method;
	private String body;
	public String getBody() {
		return body;
	}
	public Options setBody(String body) {
		this.body = body;
		return this;
	}
	private Options(){}
	public String getCharset() {
		return charset;
	}
	public Options setCharset(String charset) {
		this.charset = charset;
		return this;
	}
	public Method getMethod() {
		return method;
	}
	public Options setMethod(Method method) {
		this.method = method;
		return this;
	}
	public static Options getDefault(){
		Options o = new Options();
		o.charset = "utf-8";
		o.method = Method.GET;
		return o;
	}
}
