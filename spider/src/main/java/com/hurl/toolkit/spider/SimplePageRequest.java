package com.hurl.toolkit.spider;

import java.net.URI;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SimplePageRequest implements PageRequest{
    private URI uri;
    public SimplePageRequest(URI uri){
        this.uri = uri;
    }

    @Override
    public URI getURI() {
        return this.uri;
    }
}
