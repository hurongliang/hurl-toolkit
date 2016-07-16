package com.hurl.toolkit.spider;

public interface PageRequestIterator {
	public boolean hasNext();
	public PageRequest next();
}
