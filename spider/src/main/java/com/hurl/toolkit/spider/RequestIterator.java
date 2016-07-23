package com.hurl.toolkit.spider;

public interface RequestIterator {
    boolean hasNext();
    Request next();
}
