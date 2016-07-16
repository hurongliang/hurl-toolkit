package com.hurl.toolkit.spider;

import org.junit.Test;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SpiderTest {
    @Test
    public void start() throws SpiderException {
        Spider.create(new SimplePageIterator("http://www.baidu.com"))
                .pageProcessor(page -> {
                    System.out.println("get " + page.getRequest());
                    System.out.println("content " + page.raw());
                }).start();
    }
}
