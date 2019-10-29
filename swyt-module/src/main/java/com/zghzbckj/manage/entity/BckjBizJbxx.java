/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * ccEntity
 *
 * @author cc
 * @version 2019-10-21
 */
public class BckjBizJbxx extends DataWithExpEntity<BckjBizJbxx> {

    private static final long serialVersionUID = 1L;
    private String yhRefOwid;
    private String sfzzm;        // sfzzm
    private String sfzfm;        // sfzfm
    private String hjzm;        // hjzm
    private String xm;        // xm
    private String sfzh;        // sfzh
    private Date csrq;        // csrq
    private String zzmm;        // zzmm
    private String jdxy;        // jdxy
    private String mz;        // mz
    private String xjdq;        // xjdq
    private Integer xb;        // xb
    private String jtzz;        // jtzz
    private String yb;        // yb
    private String lxdh;        // lxdh
    private String area;        // area
    private String city;        // city
    private String prov;        // prov
    private String grsg;        // grsg
    private String grtz;        // grtz
    private String grsl;        // grsl
    private String yx;        // yx
    private String qq;        // qq
    private String tcah;        // tcah
    private String grzp;        // grzp
    private String kslb;        // kslb
    private String wyyz;        // wyyz
    private Double wycj;        // wycj
    private String zxlb;        // zxlb
    private String jssm;        // jssm
    private String jsfj;        // jsfj
    private String qtqk;        // qtqk
    private Integer xkState;
    private Integer hkState;
    private Integer jtcyState;
    private String swytDlzh;
    private String swytDlmm;

    public String getSwytDlzh() {
        return swytDlzh;
    }

    public void setSwytDlzh(String swytDlzh) {
        this.swytDlzh = swytDlzh;
    }

    public String getSwytDlmm() {
        return swytDlmm;
    }

    public void setSwytDlmm(String swytDlmm) {
        this.swytDlmm = swytDlmm;
    }

    public Integer getHkState() {
        return hkState;
    }

    public void setHkState(Integer hkState) {
        this.hkState = hkState;
    }

    public Integer getJtcyState() {
        return jtcyState;
    }

    public void setJtcyState(Integer jtcyState) {
        this.jtcyState = jtcyState;
    }

    public BckjBizJbxx() {
        super();
    }


    @Length(min = 0, max = 100, message = "sfzzm长度必须介于 0 和 100 之间")
    public String getSfzzm() {
        return sfzzm;
    }

    public void setSfzzm(String sfzzm) {
        this.sfzzm = sfzzm;
    }

    @Length(min = 0, max = 100, message = "sfzfm长度必须介于 0 和 100 之间")
    public String getSfzfm() {
        return sfzfm;
    }

    public void setSfzfm(String sfzfm) {
        this.sfzfm = sfzfm;
    }

    @Length(min = 0, max = 100, message = "hjzm长度必须介于 0 和 100 之间")
    public String getHjzm() {
        return hjzm;
    }

    public void setHjzm(String hjzm) {
        this.hjzm = hjzm;
    }

    @Length(min = 0, max = 20, message = "xm长度必须介于 0 和 20 之间")
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Length(min = 0, max = 20, message = "sfzh长度必须介于 0 和 20 之间")
    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    @Length(min = 0, max = 20, message = "zzmm长度必须介于 0 和 20 之间")
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    @Length(min = 0, max = 200, message = "jdxy长度必须介于 0 和 200 之间")
    public String getJdxy() {
        return jdxy;
    }

    public void setJdxy(String jdxy) {
        this.jdxy = jdxy;
    }

    @Length(min = 0, max = 20, message = "mz长度必须介于 0 和 20 之间")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @Length(min = 0, max = 200, message = "xjdq长度必须介于 0 和 200 之间")
    public String getXjdq() {
        return xjdq;
    }

    public void setXjdq(String xjdq) {
        this.xjdq = xjdq;
    }

    public Integer getXb() {
        return xb;
    }

    public void setXb(Integer xb) {
        this.xb = xb;
    }

    @Length(min = 0, max = 200, message = "jtzz长度必须介于 0 和 200 之间")
    public String getJtzz() {
        return jtzz;
    }

    public void setJtzz(String jtzz) {
        this.jtzz = jtzz;
    }

    @Length(min = 0, max = 6, message = "yb长度必须介于 0 和 6 之间")
    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    @Length(min = 0, max = 20, message = "lxdh长度必须介于 0 和 20 之间")
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    @Length(min = 0, max = 20, message = "area长度必须介于 0 和 20 之间")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Length(min = 0, max = 20, message = "city长度必须介于 0 和 20 之间")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Length(min = 0, max = 20, message = "prov长度必须介于 0 和 20 之间")
    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    @Length(min = 0, max = 20, message = "grsg长度必须介于 0 和 20 之间")
    public String getGrsg() {
        return grsg;
    }

    public void setGrsg(String grsg) {
        this.grsg = grsg;
    }

    @Length(min = 0, max = 20, message = "grtz长度必须介于 0 和 20 之间")
    public String getGrtz() {
        return grtz;
    }

    public void setGrtz(String grtz) {
        this.grtz = grtz;
    }

    @Length(min = 0, max = 50, message = "grsl长度必须介于 0 和 50 之间")
    public String getGrsl() {
        return grsl;
    }

    public void setGrsl(String grsl) {
        this.grsl = grsl;
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

    @Length(min = 0, max = 200, message = "grzp长度必须介于 0 和 200 之间")
    public String getGrzp() {
        return grzp;
    }

    public void setGrzp(String grzp) {
        this.grzp = grzp;
    }

    @Length(min = 0, max = 20, message = "kslb长度必须介于 0 和 20 之间")
    public String getKslb() {
        return kslb;
    }

    public void setKslb(String kslb) {
        this.kslb = kslb;
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

    public Integer getXkState() {
        return xkState;
    }

    public void setXkState(Integer xkState) {
        this.xkState = xkState;
    }

    public String getYhRefOwid() {
        return yhRefOwid;
    }

    public void setYhRefOwid(String yhRefOwid) {
        this.yhRefOwid = yhRefOwid;
    }
}