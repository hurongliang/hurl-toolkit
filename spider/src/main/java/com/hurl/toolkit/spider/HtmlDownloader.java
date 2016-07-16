package com.hurl.toolkit.spider;

import com.hurl.toolkit.http.HttpUtil;

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
