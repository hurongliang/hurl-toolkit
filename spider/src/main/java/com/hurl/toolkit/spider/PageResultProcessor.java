package com.hurl.toolkit.spider;

import java.io.Serializable;

/**
 * Created by hurongliang on 16/7/16.
 */
@FunctionalInterface
public interface PageResultProcessor<T extends Serializable>{
    void process(T t);
}
