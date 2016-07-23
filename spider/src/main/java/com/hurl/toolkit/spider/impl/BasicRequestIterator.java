package com.hurl.toolkit.spider.impl;


import com.hurl.toolkit.spider.Request;
import com.hurl.toolkit.spider.RequestIterator;
import com.hurl.toolkit.spider.SpiderRuntimeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasicRequestIterator implements RequestIterator {
    private Iterator<BasicRequest> it;
	
	public BasicRequestIterator(String url){
        List<BasicRequest> urls = new ArrayList<>();
        try {
            urls.add(new BasicRequest(url));
        } catch (Exception e) {
            throw new SpiderRuntimeException("unable to parse url " + url);
        }
        it = urls.iterator();
	}
	
	public BasicRequestIterator(List<BasicRequest> urls) {
        List<BasicRequest> list = new ArrayList<>();
        if(urls != null) list.addAll(urls);
        it = urls.iterator();
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
