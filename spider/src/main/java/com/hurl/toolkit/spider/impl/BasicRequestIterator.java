package com.hurl.toolkit.spider.impl;


import com.hurl.toolkit.spider.Request;
import com.hurl.toolkit.spider.RequestIterator;
import com.hurl.toolkit.spider.SpiderRuntimeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BasicRequestIterator implements RequestIterator {
    private Iterator<BasicRequest> it;
	
	public BasicRequestIterator(String url){
        this(Arrays.asList(url));
	}
	
	public BasicRequestIterator(List<String> urls) {
        List<BasicRequest> list = new ArrayList<>();
        urls.forEach(url -> list.add(new BasicRequest(url)));
        it = list.iterator();
	}
	@Override
	public boolean hasNext() {
        return it.hasNext();
	}

	@Override
	public Request next() {
        return it.next();
	}

}
