package com.hurl.toolkit.spider.impl;

import com.hurl.toolkit.http.HttpUtil;
import com.hurl.toolkit.spider.Downloader;

import java.net.URI;

/**
 * Created by hurongliang on 16/7/16.
 */
public class HtmlDownloader implements Downloader {
    @Override
    public String download(URI uri) {
        return HttpUtil.get(uri, null);
    }
}
