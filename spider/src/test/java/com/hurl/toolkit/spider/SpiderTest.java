package com.hurl.toolkit.spider;

import org.junit.Test;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SpiderTest {
    @Test
    public void start() throws SpiderException {
        Spider.create(new SimpleURLIterator("http://www.baidu.com"))
                .pageProcessor(page -> System.out.println(page.url())).start();
    }
}
