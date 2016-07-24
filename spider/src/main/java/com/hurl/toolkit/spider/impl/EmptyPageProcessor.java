package com.hurl.toolkit.spider.impl;

import com.hurl.toolkit.spider.Page;
import com.hurl.toolkit.spider.PageProcessor;

/**
 * Created by hurongliang on 16/7/16.
 */
public class EmptyPageProcessor<T> implements PageProcessor<T> {
    @Override
    public T process(Page page) {
        return null;
    }
}
