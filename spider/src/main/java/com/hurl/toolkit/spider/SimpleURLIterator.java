package com.hurl.toolkit.spider;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleURLIterator implements IURLIterator{
    private Iterator<String> it;
	
	public SimpleURLIterator(String url){
        List<String> urls = new ArrayList<>();
        urls.add(url);
        it = urls.iterator();
	}
	
	public SimpleURLIterator(List<String> urls) {
        List<String> list = new ArrayList<>();
        if(urls != null) list.addAll(urls);
        it = urls.iterator();
	}
	@Override
	public boolean hasNext() {
        return it.hasNext();
	}

	@Override
	public String next() {
        return it.next();
	}

}
