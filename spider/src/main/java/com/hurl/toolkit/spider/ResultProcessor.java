package com.hurl.toolkit.spider;
/**
 * Created by hurongliang on 16/7/16.
 */
@FunctionalInterface
public interface ResultProcessor<T>{
    void process(T t);
}
