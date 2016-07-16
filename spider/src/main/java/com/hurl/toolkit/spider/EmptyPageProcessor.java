package com.hurl.toolkit.spider;

import java.io.Serializable;

/**
 * Created by hurongliang on 16/7/16.
 */
public class EmptyPageProcessor<T extends Serializable> implements PageProcessor<T>{
    @Override
    public T process(Page page) {
        return null;
    }
}
