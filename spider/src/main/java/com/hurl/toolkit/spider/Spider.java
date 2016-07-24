package com.hurl.toolkit.spider;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.hurl.toolkit.spider.impl.EmptyPageProcessor;
import com.hurl.toolkit.spider.impl.HtmlDownloader;

/**
 * Created by hurongliang on 16/7/16.
 */
public class Spider<T> {
    private RequestIterator urlIterator;
    private PageProcessor<T> pageProcessor;
    private ResultProcessor<T> resultProcessor;
    private SiteConfig siteConfig;
    private Downloader downloader;
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    private ThreadPoolExecutor jobService;
    private int thread = 1;

    public Spider(){
    	
    }
    public Spider(RequestIterator requestIterator){
        this.urlIterator = requestIterator;
    }
    public static <T> Spider<T> ofType(Class<T> cls){
    	return new Spider<T>();
    }
    public static <T> Spider<List<T>> ofList(Class<T> cls){
    	return new Spider<List<T>>();
    }
    public Spider<T> siteConfig(SiteConfig siteConfig){
        this.siteConfig = siteConfig;
        return this;
    }
    public Spider<T> requestIterator(RequestIterator ri){
    	this.urlIterator = ri;
    	return this;
    }
    public Spider<T> pageProcessor(PageProcessor<T> pageProcessor){
        this.pageProcessor = pageProcessor;
        return this;
    }
    public Spider<T> downloader(Downloader downloader){
        this.downloader = downloader;
        return this;
    }
    public Spider<T> thread(int thread){
        this.thread = thread;
        return this;
    }

    public Spider<T> resultProcessor(ResultProcessor<T> resultProcessor){
        this.resultProcessor = resultProcessor;
        return this;
    }

    public void start()throws SpiderException{
        init();
        try{
            while(urlIterator.hasNext()){
            		try {
						craw(urlIterator.next());
					} catch (Exception e) {
						e.printStackTrace();
					}
            }
        }catch(Exception e){
            throw new SpiderException("spider failure", e);
        }
    }

    private void craw(Request pageRequest) throws SpiderException {
        ListenableFuture<Page> f1 = service.submit(() -> {
            Page page = new Page(pageRequest);
            String raw = downloader.download(new URI(pageRequest.getUrl()));
            page.setRaw(raw);
            return page;
        });

        Page page = null;
        try {
			page = f1.get(siteConfig.getConnectionTimeout(), TimeUnit.SECONDS);
		} catch (TimeoutException e1) {
			throw new SpiderException("抓取超时", e1);
		} catch (Exception e2) {
			throw new SpiderException("抓取出错", e2);
		}
        
        T result = pageProcessor.process(page);
        
        resultProcessor.process(result);
        
        /*
        ListenableFuture<T> f2 = Futures.transform(f1, new AsyncFunction<Page, T>() {
            @Override
            public ListenableFuture<T> apply(Page o) throws Exception {
                return service.submit(new Callable<T>() {
                    @Override
                    public T call() throws Exception {
                        return pageProcessor.process(o);
                    }
                });
            }
        });

        Futures.addCallback(f2, new FutureCallback<T>() {
            @Override
            public void onSuccess(T o) {
                resultProcessor.process(o);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        
        try {
			f2.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		*/
    }

    private void init() throws SpiderException {
        if(urlIterator == null) throw new SpiderException("url interator is undefined.");
        if(pageProcessor == null) pageProcessor = new EmptyPageProcessor<T>();
        if(siteConfig == null) siteConfig = SiteConfig.getDefault();
        if(downloader == null) downloader = new HtmlDownloader();
        if(jobService == null) {
        	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                    thread,
                    60,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        	jobService = threadPoolExecutor;//MoreExecutors.listeningDecorator(threadPoolExecutor);
        }
    }

    public void startAsync() throws SpiderException{
        init();
        try{
            while(urlIterator.hasNext()){
            	jobService.submit(() -> {
            		try {
						craw(urlIterator.next());
					} catch (Exception e) {
						e.printStackTrace();
					}
            	});
            }
        }catch(Exception e){
            throw new SpiderException("spider failure", e);
        }
    }
}
