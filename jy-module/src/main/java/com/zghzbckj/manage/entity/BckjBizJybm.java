/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ccEntity
 *
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizJybm extends DataWithExpEntity<BckjBizJybm> {

    private static final long serialVersionUID = 1L;
    private String qyxxRefOwid;        // qyxx_ref_owid
    private String jobRefOwid;        // job_ref_owid
    private String yhRefOwid;        // yh_ref_owid
    private Integer bmlx;        // bmlx
    private Integer bmdx;        // bmdx
    private Date bmsj;        // bmsj
    private String zwbh;        // zwbh
    private String lxdh;        // lxdh
    private String lxr;        // lxr
    private String qymc;        // qymc
    private String qysh;        // qysh
    private String xjhsqly;        // xjhsqly
    private Integer sfxz;        // sfxz
    private String xjsj;        // xjsj
    private Integer sftz;        // sftz
    private Date tzsj;        // tzsj
    private String jkr;        // jkr
    private String jkrjs;        // jkrjs
    private String memo;        // memo
    private String xgfj;

    private String zwbt;
    private String zwPro;
    private String zwCity;
    private String zwArea;
    private String zphJbdd;
    private String zphKsrq;


    public String getZwbt() {
        return zwbt;
    }

    public void setZwbt(String zwbt) {
        this.zwbt = zwbt;
    }

    public String getZwPro() {
        return zwPro;
    }

    public void setZwPro(String zwPro) {
        this.zwPro = zwPro;
    }

    public String getZwCity() {
        return zwCity;
    }

    public void setZwCity(String zwCity) {
        this.zwCity = zwCity;
    }

    public String getZwArea() {
        return zwArea;
    }

    public void setZwArea(String zwArea) {
        this.zwArea = zwArea;
    }

    public String getZphJbdd() {
        return zphJbdd;
    }

    public void setZphJbdd(String zphJbdd) {
        this.zphJbdd = zphJbdd;
    }

    public String getZphKsrq() {
        return zphKsrq;
    }

    public void setZphKsrq(String zphKsrq) {
        this.zphKsrq = zphKsrq;
    }

    public String getXgfj() {
        return xgfj;
    }

    public void setXgfj(String xgfj) {
        this.xgfj = xgfj;
    }

    public BckjBizJybm() {
        super();
    }


    @Length(min = 0, max = 64, message = "qyxx_ref_owid长度必须介于 0 和 64 之间")
    public String getQyxxRefOwid() {
        return qyxxRefOwid;
    }

    public void setQyxxRefOwid(String qyxxRefOwid) {
        this.qyxxRefOwid = qyxxRefOwid;
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

    @NotNull(message = "bmlx不能为空")
    public Integer getBmlx() {
        return bmlx;
    }

    public void setBmlx(Integer bmlx) {
        this.bmlx = bmlx;
    }

    @NotNull(message = "bmdx不能为空")
    public Integer getBmdx() {
        return bmdx;
    }

    public void setBmdx(Integer bmdx) {
        this.bmdx = bmdx;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBmsj() {
        return bmsj;
    }

    public void setBmsj(Date bmsj) {
        this.bmsj = bmsj;
    }

    @Length(min = 0, max = 50, message = "zwbh长度必须介于 0 和 50 之间")
    public String getZwbh() {
        return zwbh;
    }

    public void setZwbh(String zwbh) {
        this.zwbh = zwbh;
    }

    @Length(min = 0, max = 50, message = "lxdh长度必须介于 0 和 50 之间")
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    @Length(min = 0, max = 50, message = "lxr长度必须介于 0 和 50 之间")
    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    @Length(min = 0, max = 100, message = "qymc长度必须介于 0 和 100 之间")
    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    @Length(min = 0, max = 50, message = "qysh长度必须介于 0 和 50 之间")
    public String getQysh() {
        return qysh;
    }

    public void setQysh(String qysh) {
        this.qysh = qysh;
    }

    @Length(min = 0, max = 200, message = "xjhsqly长度必须介于 0 和 200 之间")
    public String getXjhsqly() {
        return xjhsqly;
    }

    public void setXjhsqly(String xjhsqly) {
        this.xjhsqly = xjhsqly;
    }

    public Integer getSfxz() {
        return sfxz;
    }

    public void setSfxz(Integer sfxz) {
        this.sfxz = sfxz;
    }

    @Length(min = 0, max = 100, message = "xjsj长度必须介于 0 和 100 之间")
    public String getXjsj() {
        return xjsj;
    }

    public void setXjsj(String xjsj) {
        this.xjsj = xjsj;
    }

    public Integer getSftz() {
        return sftz;
    }

    public void setSftz(Integer sftz) {
        this.sftz = sftz;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTzsj() {
        return tzsj;
    }

    public void setTzsj(Date tzsj) {
        this.tzsj = tzsj;
    }

    @Length(min = 0, max = 20, message = "jkr长度必须介于 0 和 20 之间")
    public String getJkr() {
        return jkr;
    }

    public void setJkr(String jkr) {
        this.jkr = jkr;
    }

    @Length(min = 0, max = 1000, message = "jkrjs长度必须介于 0 和 1000 之间")
    public String getJkrjs() {
        return jkrjs;
    }

    public void setJkrjs(String jkrjs) {
        this.jkrjs = jkrjs;
    }

    @Length(min = 0, max = 1000, message = "memo长度必须介于 0 和 1000 之间")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}