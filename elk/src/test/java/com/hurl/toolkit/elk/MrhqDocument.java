package com.hurl.toolkit.elk;

import com.hurl.toolkit.elk.data.ESDocumentWithId;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hurongliang on 16/6/26.
 */
public class MrhqDocument implements ESDocumentWithId{
    private String rq;
    private String gpdm;
    private String gpdmJ;
    private String gplx;
    private String gpmc;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal closePreDay;
    private BigDecimal volume;
    private BigDecimal turnover;
    private BigDecimal amplitude;

    public String id(){
        return rq + "-" + gpdm;
    }
    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getGpdm() {
        return gpdm;
    }

    public void setGpdm(String gpdm) {
        this.gpdm = gpdm;
    }

    public String getGpdmJ() {
        return gpdmJ;
    }

    public void setGpdmJ(String gpdmJ) {
        this.gpdmJ = gpdmJ;
    }

    public String getGplx() {
        return gplx;
    }

    public void setGplx(String gplx) {
        this.gplx = gplx;
    }

    public String getGpmc() {
        return gpmc;
    }

    public void setGpmc(String gpmc) {
        this.gpmc = gpmc;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClosePreDay() {
        return closePreDay;
    }

    public void setClosePreDay(BigDecimal closePreDay) {
        this.closePreDay = closePreDay;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
    }
}
