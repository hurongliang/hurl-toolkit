package com.hurl.toolkit.spider;

import com.hurl.toolkit.http.HttpUtil;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by hurongliang on 16/7/16.
 */
public class Spider {
    private IURLIterator urlIterator;
    private PageProcessor pageProcessor;
    private SiteConfig siteConfig;
    private Downloader downloader;
    private int thread = 1;
    public Spider(IURLIterator urlIterator){
        this.urlIterator = urlIterator;
    }
    public static Spider create(IURLIterator urlIterator){
        return new Spider(urlIterator);
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

    private void craw(String url) throws URISyntaxException {
        Page page = new Page(url);
        String raw = downloader.download(new URI(url));
        page.setRaw(raw);
        pageProcessor.process(page);
    }

    private void init() {
        if(pageProcessor == null) pageProcessor = new EmptyPageProcessor();
        if(siteConfig == null) siteConfig = SiteConfig.getDefault();
        if(downloader == null) downloader = new HtmlDownloader();
    }

    public void startAsync(){

    }
}
