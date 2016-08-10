package com.hurl.toolkit.spider.impl;

import java.net.URI;

import com.hurl.toolkit.http.HttpUtil;
import com.hurl.toolkit.http.Options;
import com.hurl.toolkit.spider.Crawer;
import com.hurl.toolkit.spider.SiteConfig;

/**
 * Created by hurongliang on 16/7/16.
 */
public class HtmlDownloader implements Crawer {
	private SiteConfig siteConfig;
	public HtmlDownloader(SiteConfig siteConfig){
		this.siteConfig = siteConfig;
	}
    @Override
    public String craw(URI uri) {
        return HttpUtil.request(uri, Options.getDefault().setCharset(siteConfig.getEncoding()));
    }
}
