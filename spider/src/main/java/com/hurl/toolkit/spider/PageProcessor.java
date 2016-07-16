package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
@FunctionalInterface
public interface PageProcessor {
    void process(Page page);
}
