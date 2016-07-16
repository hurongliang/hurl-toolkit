package com.hurl.toolkit.spider;


import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimplePageIterator implements PageRequestIterator {
    private Iterator<SimplePageRequest> it;
	
	public SimplePageIterator(String url){
        List<SimplePageRequest> urls = new ArrayList<>();
        try {
            urls.add(new SimplePageRequest(new URI(url)));
        } catch (Exception e) {
            throw new SpiderRuntimeException("unable to parse url " + url);
        }
        it = urls.iterator();
	}
	
	public SimplePageIterator(List<SimplePageRequest> urls) {
        List<SimplePageRequest> list = new ArrayList<>();
        if(urls != null) list.addAll(urls);
        it = urls.iterator();
	}
	@Override
	public boolean hasNext() {
        return it.hasNext();
	}

	@Override
	public PageRequest next() {
        return it.next();
	}

}
