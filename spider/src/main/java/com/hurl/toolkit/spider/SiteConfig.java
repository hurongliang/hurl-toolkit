package com.hurl.toolkit.spider;

/**
 * Created by hurongliang on 16/7/16.
 */
public class SiteConfig {
    private String encoding = "UTF-8";
    private int retry = 0;
    private int interval = 0;
    private int connectionTimeout = 10;

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public static SiteConfig getDefault(){
        return new SiteConfig();
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public int getInterval() {
        return interval;
    }

    /**
     * 设置两次抓取页面的时间间隔（毫秒）
     * @param interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }
}
