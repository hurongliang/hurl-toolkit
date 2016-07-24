package com.hurl.toolkit.spider;

import com.hurl.toolkit.spider.impl.BasicRequestIterator;
import org.junit.Test;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SpiderTest {
    @Test
    public void start() throws SpiderException {
        Spider.ofType(Object.class).requestIterator(new BasicRequestIterator("http://www.baidu.com"))
                .pageProcessor(page -> {
                    PageResult result = new PageResult();
                    result.setUrl(page.getRequest().getUrl());
                    result.setContent(page.raw());
                    return result;
                }).resultProcessor(result -> {
            if (result instanceof PageResult) {
                System.out.println("get " + ((PageResult) result).getUrl());
                System.out.println("content " + ((PageResult) result).getContent());
            }
        }).start();
    }
}
