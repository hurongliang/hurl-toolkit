package com.hurl.toolkit.spider;

import java.util.HashSet;
import java.util.Iterator;

public class URLCollection extends HashSet<String> implements IURLIterator{
	private static final long serialVersionUID = 1L;
	private Iterator<String> it;
	@Override
	public boolean hasNext() {
		if(it == null) it = iterator();
		return it.hasNext();
	}

	@Override
	public String next() {
		if(it == null) it = iterator();
		return it.next();
	}
}
