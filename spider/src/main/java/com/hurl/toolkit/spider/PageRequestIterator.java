package com.hurl.toolkit.spider;

public interface PageRequestIterator {
    boolean hasNext();
    PageRequest next();
}
