package com.hurl.toolkit.spider;

import com.hurl.toolkit.spider.impl.EmptyPageProcessor;
import com.hurl.toolkit.spider.impl.HtmlDownloader;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by hurongliang on 16/7/16.
 */
public class Spider {
    private RequestIterator urlIterator;
    private PageProcessor pageProcessor;
    private Pipeline pipeline;
    private SiteConfig siteConfig;
    private Downloader downloader;
    private int thread = 1;

    public Spider(RequestIterator requestIterator){
        this.urlIterator = requestIterator;
    }
    public static Spider create(RequestIterator requestIterator){
        return new Spider(requestIterator);
    }
    public Spider siteConfig(SiteConfig siteConfig){
        this.siteConfig = siteConfig;
        return this;
    }
    public Spider pageProcessor(PageProcessor pageProcessor){
        this.pageProcessor = pageProcessor;
        return this;
    }
    public Spider downloader(Downloader downloader){
        this.downloader = downloader;
        return this;
    }
    public Spider thread(int thread){
        this.thread = thread;
        return this;
    }

    public Spider pipeline(Pipeline pipeline){
        this.pipeline = pipeline;
        return this;
    }

    public void start()throws SpiderException{
        init();
        try{
            while(urlIterator.hasNext()){
               craw(urlIterator.next());
            }
        }catch(Exception e){
            throw new SpiderException("spider failure", e);
        }
    }

    private void craw(Request pageRequest) throws URISyntaxException, MalformedURLException {
        Page page = new Page(pageRequest);
        String raw = downloader.download(new URI(pageRequest.getUrl()));
        page.setRaw(raw);
        Serializable s = pageProcessor.process(page);
        if(this.pipeline != null){
            this.pipeline.process(s);
        }
    }

    private void init() throws SpiderException {
        if(urlIterator == null) throw new SpiderException("url interator is undefined.");
        if(pageProcessor == null) pageProcessor = new EmptyPageProcessor();
        if(siteConfig == null) siteConfig = SiteConfig.getDefault();
        if(downloader == null) downloader = new HtmlDownloader();
    }

    public void startAsync(){

    }
}
