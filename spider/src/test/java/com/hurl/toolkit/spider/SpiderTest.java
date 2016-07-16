package com.hurl.toolkit.spider;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SpiderTest {
    @Test
    public void start() throws SpiderException {
        Spider.create(new SimplePageIterator("http://www.baidu.com"))
                .pageProcessor(page -> {
                    PageResult result = new PageResult();
                    result.setUrl(page.getRequest().getURI().toASCIIString());
                    result.setContent(page.raw());
                    return result;
                }).pageResultProcessor(result -> {
            if (result instanceof PageResult) {
                System.out.println("get " + ((PageResult) result).getUrl());
                System.out.println("content " + ((PageResult)result).getContent());
            }
        }).start();
    }
}
