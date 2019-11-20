/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * ccEntity
 *
 * @author cc
 * @version 2019-10-21
 */
public class BckjBizBm extends DataWithExpEntity<BckjBizBm> {

    private static final long serialVersionUID = 1L;
    private Long bkzyRefOwid;        // bkzy_ref_owid
    private String fzszRefOwid;
    private String xxbh;        // xxbh
    private String userRefOwid;        // user_ref_owid
    private String xm;        // xm
    private Integer xb;        // xb
    private String xbStr;        // xb
    private String bmnd;        // bmnd
    private String mz;        // mz
    private String sfzh;        // sfzh
    private String yx;        // yx
    private String qq;        // qq
    private String jtzz;        // qq
    private String lxdh;        // qq

    private String tcah;        // tcah
    private String xklb;        // 0普通类    1艺术类；
    private String wyyz;        // wyyz
    private Double wycj;        // wycj
    private String zxlb;        // zxlb
    private String jssm;        // jssm
    private String jsfj;        // jsfj
    private String qtqk;        // qtqk
    private String xzzylj;        // xzzylj
    private String xzzymc;        // xzzymc
    private String bklb;        // bklb
    private Date sqsj;        // sqsj
    private String bmbZp;        // bmb_zp
    private String cnszp;        // cnszp
    private Date cssj;        // cssj
    private String jjly;        // jjly
    private Date jfsj;        // jfsj
    private String jfpzh;        // jfpzh
    private String jfpzZp;        // jfpz_zp
    private String mssj;        // mssj
    private String msfzh;        // msfzh
    private String msfzZnxh;        // msfz_znxh
    private Integer msfzSxh;        // msfz_sxh
    private String zkzh;        // zkzh
    private Integer rzbd;        // rzbd
    private String grzp;        // grzp
    private Date cqsj;        // cqsj
    private Date bdsj;        // bdsj
    private String zkzMb;        // zkz_mb
    private String bmbMb;        // bmb_mb
    private Date dySj;        // dy_sj
    private String xybnr;        // xybnr
    private String bscj;        // bscj
    private String mscj;        // mscj
    private String zzcj;        // zzcj
    private String memo;        // zzcj
    private String yzmc;        // zzcj
    private Integer xklbOwid;        // rzbd
    private Integer bklbOwid;        // rzbd

    private String mssm;        // ms
    private Date lrsj;
    private String xkcj1;
    private String xkcj2;
    private String xkcj3;
    private boolean readOnly;

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }


    public String getXkcj1() {
        return xkcj1;
    }

    public void setXkcj1(String xkcj1) {
        this.xkcj1 = xkcj1;
    }

    public String getXkcj2() {
        return xkcj2;
    }

    public void setXkcj2(String xkcj2) {
        this.xkcj2 = xkcj2;
    }

    public String getXkcj3() {
        return xkcj3;
    }

    public void setXkcj3(String xkcj3) {
        this.xkcj3 = xkcj3;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getMssm() {
        return mssm;
    }

    public void setMssm(String mssm) {
        this.mssm = mssm;
    }

    public String getMssj() {
        return mssj;
    }

    public void setMssj(String mssj) {
        this.mssj = mssj;
    }

    public String getFzszRefOwid() {
        return fzszRefOwid;
    }

    public void setFzszRefOwid(String fzszRefOwid) {
        this.fzszRefOwid = fzszRefOwid;
    }

    public String getYzmc() {
        return yzmc;
    }

    public void setYzmc(String yzmc) {
        this.yzmc = yzmc;
    }

    public Integer getXklbOwid() {
        return xklbOwid;
    }

    public void setXklbOwid(Integer xklbOwid) {
        this.xklbOwid = xklbOwid;
    }

    public BckjBizBm() {
        super();
    }


    public Long getBkzyRefOwid() {
        return bkzyRefOwid;
    }

    public void setBkzyRefOwid(Long bkzyRefOwid) {
        this.bkzyRefOwid = bkzyRefOwid;
    }

    @Length(min = 0, max = 20, message = "xxbh长度必须介于 0 和 20 之间")
    public String getXxbh() {
        return xxbh;
    }

    public void setXxbh(String xxbh) {
        this.xxbh = xxbh;
    }

    @Length(min = 0, max = 64, message = "user_ref_owid长度必须介于 0 和 64 之间")
    public String getUserRefOwid() {
        return userRefOwid;
    }

    public void setUserRefOwid(String userRefOwid) {
        this.userRefOwid = userRefOwid;
    }

    @Length(min = 0, max = 20, message = "xm长度必须介于 0 和 20 之间")
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public Integer getXb() {
        return xb;
    }

    public void setXb(Integer xb) {
        this.xb = xb;
    }

    @Length(min = 0, max = 4, message = "bmnd长度必须介于 0 和 4 之间")
    public String getBmnd() {
        return bmnd;
    }

    public void setBmnd(String bmnd) {
        this.bmnd = bmnd;
    }

    @Length(min = 0, max = 20, message = "mz长度必须介于 0 和 20 之间")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @Length(min = 0, max = 20, message = "sfzh长度必须介于 0 和 20 之间")
    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    @Length(min = 0, max = 50, message = "yx长度必须介于 0 和 50 之间")
    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    @Length(min = 0, max = 20, message = "qq长度必须介于 0 和 20 之间")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Length(min = 0, max = 200, message = "tcah长度必须介于 0 和 200 之间")
    public String getTcah() {
        return tcah;
    }

    public void setTcah(String tcah) {
        this.tcah = tcah;
    }

    public String getXklb() {
        return xklb;
    }

    public void setXklb(String xklb) {
        this.xklb = xklb;
    }

    @Length(min = 0, max = 20, message = "wyyz长度必须介于 0 和 20 之间")
    public String getWyyz() {
        return wyyz;
    }

    public void setWyyz(String wyyz) {
        this.wyyz = wyyz;
    }

    public Double getWycj() {
        return wycj;
    }

    public void setWycj(Double wycj) {
        this.wycj = wycj;
    }

    @Length(min = 0, max = 200, message = "zxlb长度必须介于 0 和 200 之间")
    public String getZxlb() {
        return zxlb;
    }

    public void setZxlb(String zxlb) {
        this.zxlb = zxlb;
    }

    @Length(min = 0, max = 500, message = "jssm长度必须介于 0 和 500 之间")
    public String getJssm() {
        return jssm;
    }

    public void setJssm(String jssm) {
        this.jssm = jssm;
    }

    @Length(min = 0, max = 500, message = "jsfj长度必须介于 0 和 500 之间")
    public String getJsfj() {
        return jsfj;
    }

    public void setJsfj(String jsfj) {
        this.jsfj = jsfj;
    }

    @Length(min = 0, max = 500, message = "qtqk长度必须介于 0 和 500 之间")
    public String getQtqk() {
        return qtqk;
    }

    public void setQtqk(String qtqk) {
        this.qtqk = qtqk;
    }

    @Length(min = 0, max = 200, message = "xzzylj长度必须介于 0 和 200 之间")
    public String getXzzylj() {
        return xzzylj;
    }

    public void setXzzylj(String xzzylj) {
        this.xzzylj = xzzylj;
    }

    @Length(min = 0, max = 100, message = "xzzymc长度必须介于 0 和 100 之间")
    public String getXzzymc() {
        return xzzymc;
    }

    public void setXzzymc(String xzzymc) {
        this.xzzymc = xzzymc;
    }

    @Length(min = 0, max = 36, message = "bklb长度必须介于 0 和 36 之间")
    public String getBklb() {
        return bklb;
    }

    public void setBklb(String bklb) {
        this.bklb = bklb;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getSqsj() {
        return sqsj;
    }

    public void setSqsj(Date sqsj) {
        this.sqsj = sqsj;
    }

    @Length(min = 0, max = 100, message = "bmb_zp长度必须介于 0 和 100 之间")
    public String getBmbZp() {
        return bmbZp;
    }

    public void setBmbZp(String bmbZp) {
        this.bmbZp = bmbZp;
    }

    @Length(min = 0, max = 100, message = "cnszp长度必须介于 0 和 100 之间")
    public String getCnszp() {
        return cnszp;
    }

    public void setCnszp(String cnszp) {
        this.cnszp = cnszp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCssj() {
        return cssj;
    }

    public void setCssj(Date cssj) {
        this.cssj = cssj;
    }

    @Length(min = 0, max = 200, message = "jjly长度必须介于 0 和 200 之间")
    public String getJjly() {
        return jjly;
    }

    public void setJjly(String jjly) {
        this.jjly = jjly;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getJfsj() {
        return jfsj;
    }

    public void setJfsj(Date jfsj) {
        this.jfsj = jfsj;
    }

    @Length(min = 0, max = 50, message = "jfpzh长度必须介于 0 和 50 之间")
    public String getJfpzh() {
        return jfpzh;
    }

    public void setJfpzh(String jfpzh) {
        this.jfpzh = jfpzh;
    }

    @Length(min = 0, max = 100, message = "jfpz_zp长度必须介于 0 和 100 之间")
    public String getJfpzZp() {
        return jfpzZp;
    }

    public void setJfpzZp(String jfpzZp) {
        this.jfpzZp = jfpzZp;
    }


    @Length(min = 0, max = 50, message = "msfzh长度必须介于 0 和 50 之间")
    public String getMsfzh() {
        return msfzh;
    }

    public void setMsfzh(String msfzh) {
        this.msfzh = msfzh;
    }

    @Length(min = 0, max = 50, message = "msfz_znxh长度必须介于 0 和 50 之间")
    public String getMsfzZnxh() {
        return msfzZnxh;
    }

    public void setMsfzZnxh(String msfzZnxh) {
        this.msfzZnxh = msfzZnxh;
    }

    public Integer getMsfzSxh() {
        return msfzSxh;
    }

    public void setMsfzSxh(Integer msfzSxh) {
        this.msfzSxh = msfzSxh;
    }

    @Length(min = 0, max = 50, message = "zkzh长度必须介于 0 和 50 之间")
    public String getZkzh() {
        if(null==this.zkzh){
            return CommonConstants.EMPTY_STR;
        }
        return zkzh;
    }

    public void setZkzh(String zkzh) {
        this.zkzh = zkzh;
    }

    public Integer getRzbd() {
        return rzbd;
    }

    public void setRzbd(Integer rzbd) {
        this.rzbd = rzbd;
    }

    @Length(min = 0, max = 200, message = "grzp长度必须介于 0 和 200 之间")
    public String getGrzp() {
        return grzp;
    }

    public void setGrzp(String grzp) {
        this.grzp = grzp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCqsj() {
        return cqsj;
    }

    public void setCqsj(Date cqsj) {
        this.cqsj = cqsj;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }

    @Length(min = 0, max = 200, message = "zkz_mb长度必须介于 0 和 200 之间")
    public String getZkzMb() {
        return zkzMb;
    }

    public void setZkzMb(String zkzMb) {
        this.zkzMb = zkzMb;
    }

    @Length(min = 0, max = 200, message = "bmb_mb长度必须介于 0 和 200 之间")
    public String getBmbMb() {
        return bmbMb;
    }

    public void setBmbMb(String bmbMb) {
        this.bmbMb = bmbMb;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDySj() {
        return dySj;
    }

    public void setDySj(Date dySj) {
        this.dySj = dySj;
    }

    @Length(min = 0, max = 200, message = "xybnr长度必须介于 0 和 200 之间")
    public String getXybnr() {
        return xybnr;
    }

    public void setXybnr(String xybnr) {
        this.xybnr = xybnr;
    }

    @Length(min = 0, max = 20, message = "bscj长度必须介于 0 和 20 之间")
    public String getBscj() {
        return bscj;
    }

    public void setBscj(String bscj) {
        this.bscj = bscj;
    }

    @Length(min = 0, max = 20, message = "mscj长度必须介于 0 和 20 之间")
    public String getMscj() {
        return mscj;
    }

    public void setMscj(String mscj) {
        this.mscj = mscj;
    }

    @Length(min = 0, max = 20, message = "zzcj长度必须介于 0 和 20 之间")
    public String getZzcj() {
        return zzcj;
    }

    public void setZzcj(String zzcj) {
        this.zzcj = zzcj;
    }


    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getJtzz() {
        return jtzz;
    }

    public void setJtzz(String jtzz) {
        this.jtzz = jtzz;
    }

    public String getXbStr() {
        if (null != xb) {
            if (xb == 1) {
                return "男";
            }
            if (xb == 2) {
                return "女";
            }
        }
        return xbStr;
    }

    public void setXbStr(String xbStr) {
        this.xbStr = xbStr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getBklbOwid() {
        return bklbOwid;
    }

    public void setBklbOwid(Integer bklbOwid) {
        this.bklbOwid = bklbOwid;
    }
}