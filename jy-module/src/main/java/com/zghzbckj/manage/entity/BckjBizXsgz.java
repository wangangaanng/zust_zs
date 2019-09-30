/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ccEntity
 *
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizXsgz extends DataWithExpEntity<BckjBizXsgz> {

    private static final long serialVersionUID = 1L;

    private String jobRefOwid;        // job_ref_owid
    private String yhRefOwid;        // yh_ref_owid
    private Integer gzlx;        // gzlx
    private Integer xxlb;        // xxlb
    private String yynr;        // yynr
    private Date gzsj;        // gzsj
    private String lxdh;        // lxdh
    private String lxr;        // lxr
    private BigDecimal gpsMbjd;        // gps_mbjd
    private BigDecimal gpsMbwd;        // gps_mbwd
    private BigDecimal gpsJd;        // gps_jd
    private BigDecimal gpsWd;        // gps_wd
    private Integer gpsJl;        // gps_jl
    private String memo;        // memo
    private BckjBizJob job;
    private String qymc;
    private String zwbt;
    private String gsqymc;


    public String getGsqymc() {
        return gsqymc;
    }

    public void setGsqymc(String gsqymc) {
        this.gsqymc = gsqymc;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getZwbt() {
        return zwbt;
    }

    public void setZwbt(String zwbt) {
        this.zwbt = zwbt;
    }

    public BckjBizJob getJob() {
        return job;
    }

    public void setJob(BckjBizJob job) {
        this.job = job;
    }

    public BckjBizXsgz() {
        super();
    }

    @Length(min = 0, max = 64, message = "job_ref_owid长度必须介于 0 和 64 之间")
    public String getJobRefOwid() {
        return jobRefOwid;
    }

    public void setJobRefOwid(String jobRefOwid) {
        this.jobRefOwid = jobRefOwid;
    }

    @Length(min = 0, max = 64, message = "yh_ref_owid长度必须介于 0 和 64 之间")
    public String getYhRefOwid() {
        return yhRefOwid;
    }

    public void setYhRefOwid(String yhRefOwid) {
        this.yhRefOwid = yhRefOwid;
    }

    public Integer getGzlx() {
        return gzlx;
    }

    public void setGzlx(Integer gzlx) {
        this.gzlx = gzlx;
    }

    public Integer getXxlb() {
        return xxlb;
    }

    public void setXxlb(Integer xxlb) {
        this.xxlb = xxlb;
    }

    @Length(min = 0, max = 50, message = "yynr长度必须介于 0 和 50 之间")
    public String getYynr() {
        return yynr;
    }

    public void setYynr(String yynr) {
        this.yynr = yynr;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getGzsj() {
        return gzsj;
    }

    public void setGzsj(Date gzsj) {
        this.gzsj = gzsj;
    }

    @Length(min = 0, max = 20, message = "lxdh长度必须介于 0 和 20 之间")
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    @Length(min = 0, max = 20, message = "lxr长度必须介于 0 和 20 之间")
    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public BigDecimal getGpsMbjd() {
        return gpsMbjd;
    }

    public void setGpsMbjd(BigDecimal gpsMbjd) {
        this.gpsMbjd = gpsMbjd;
    }

    public BigDecimal getGpsMbwd() {
        return gpsMbwd;
    }

    public void setGpsMbwd(BigDecimal gpsMbwd) {
        this.gpsMbwd = gpsMbwd;
    }

    public BigDecimal getGpsJd() {
        return gpsJd;
    }

    public void setGpsJd(BigDecimal gpsJd) {
        this.gpsJd = gpsJd;
    }

    public BigDecimal getGpsWd() {
        return gpsWd;
    }

    public void setGpsWd(BigDecimal gpsWd) {
        this.gpsWd = gpsWd;
    }

    public Integer getGpsJl() {
        return gpsJl;
    }

    public void setGpsJl(Integer gpsJl) {
        this.gpsJl = gpsJl;
    }

    @Length(min = 0, max = 1000, message = "memo长度必须介于 0 和 1000 之间")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}