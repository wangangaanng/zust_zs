/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 就业方案Entity
 * @author wangangaanng
 * @version 2019-09-30
 */
public class BckjBizJyscheme extends DataWithExpEntity<BckjBizJyscheme> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String xsxh;		// xsxh_ref_owid
	private String xm;		// xm
	private String sfz;
	private Integer xb;
	private String mz;
	private String xxmc;
	private String xsxy;
	private String xszy;
	private String xsbj;
	private String syd;
	private String bynf;
	private String xlcc;
	private String xz;
	private int sfsf;        //sfsf
	private Integer sfzz;			// sfzz
	private Integer sfdlxy;		// sfdlxy
	private String syddm;
	private String sjh;
	private String jtdh;
	private String qqhm;
	private String yx;
	private String byqx;
	private String jyqdbz;		// jyqdbz
	private String yrdwxz;		// yrdwxz
	private String gzzwlbmc;		// gzzwlbmc
	private String yrdwdm;		// yrdwdm
	private String yrdwmc;		// yrdwmc
	private String zgdwdm;		// zgdwdm
	private String zgdwmc;		// zgdwmc
	private String yrdwlsmc;		// yrdwlsmc
	private String dwszdmc;		// dwszdmc
	private String dwszddm;
	private String bdzszdmc;		// bdzszdmc
	private String bdzqflbmc;       //bdzqflbmc
	private Date bdkssj;		// bdkssj
	private Date bdjssj;		// bdjssj
	private  String bdzbh;
	private String xjcqk;		// xjcqk
	private String datdxxdz;		// datdxxdz
	private String datddw;		// datddw
	private String hkqydz;		// hkqydz
	private String dwlxr;		// dwlxr
	private String dwdh;		// dwdh
	private Integer sfzydk;		// sfzydk
	private String dwlbmc;		// dwlbmc
	private String bdzlsh;
	private String bzone;		// bzone
	private String bztwo;		// bztwo
	private String bzthree;		// bzthree

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getXsbj() {
		return xsbj;
	}

	public void setXsbj(String xsbj) {
		this.xsbj = xsbj;
	}

	public String getBynf() {
		return bynf;
	}

	public void setBynf(String bynf) {
		this.bynf = bynf;
	}

	public String getXlcc() {
		return xlcc;
	}

	public void setXlcc(String xlcc) {
		this.xlcc = xlcc;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public int getSfsf() {
		return sfsf;
	}

	public void setSfsf(int sfsf) {
		this.sfsf = sfsf;
	}

	public Integer getSfzz() {
		return sfzz;
	}

	public void setSfzz(Integer sfzz) {
		this.sfzz = sfzz;
	}

	public Integer getSfdlxy() {
		return sfdlxy;
	}

	public void setSfdlxy(Integer sfdlxy) {
		this.sfdlxy = sfdlxy;
	}

	public String getSyddm() {
		return syddm;
	}

	public void setSyddm(String syddm) {
		this.syddm = syddm;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getQqhm() {
		return qqhm;
	}

	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}

	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	public String getJyqdbz() {
		return jyqdbz;
	}

	public void setJyqdbz(String jyqdbz) {
		this.jyqdbz = jyqdbz;
	}

	public String getZgdwdm() {
		return zgdwdm;
	}

	public void setZgdwdm(String zgdwdm) {
		this.zgdwdm = zgdwdm;
	}

	public String getZgdwmc() {
		return zgdwmc;
	}

	public void setZgdwmc(String zgdwmc) {
		this.zgdwmc = zgdwmc;
	}

	public String getYrdwlsmc() {
		return yrdwlsmc;
	}

	public void setYrdwlsmc(String yrdwlsmc) {
		this.yrdwlsmc = yrdwlsmc;
	}

	public String getDwszddm() {
		return dwszddm;
	}

	public void setDwszddm(String dwszddm) {
		this.dwszddm = dwszddm;
	}

	public String getXjcqk() {
		return xjcqk;
	}

	public void setXjcqk(String xjcqk) {
		this.xjcqk = xjcqk;
	}



	public BckjBizJyscheme() {
	}


	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	public Integer getXb() {
		return xb;
	}

	public void setXb(Integer xb) {
		this.xb = xb;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getXsxy() {
		return xsxy;
	}

	public void setXsxy(String xsxy) {
		this.xsxy = xsxy;
	}

	public String getXszy() {
		return xszy;
	}

	public void setXszy(String xszy) {
		this.xszy = xszy;
	}



	public String getSyd() {
		return syd;
	}

	public void setSyd(String syd) {
		this.syd = syd;
	}



	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getBdzbh() {
		return bdzbh;
	}

	public void setBdzbh(String bdzbh) {
		this.bdzbh = bdzbh;
	}

	public String getBdzlsh() {
		return bdzlsh;
	}

	public void setBdzlsh(String bdzlsh) {
		this.bdzlsh = bdzlsh;
	}
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	public String getBdzqflbmc() {
		return bdzqflbmc;
	}

	public void setBdzqflbmc(String bdzqflbmc) {
		this.bdzqflbmc = bdzqflbmc;
	}


	
	@Length(min=0, max=100, message="xm长度必须介于 0 和 100 之间")
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Length(min=0, max=100, message="xxmc长度必须介于 0 和 100 之间")
	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	
	@Length(min=0, max=100, message="byqx长度必须介于 0 和 100 之间")
	public String getByqx() {
		return byqx;
	}

	public void setByqx(String byqx) {
		this.byqx = byqx;
	}
	
	public Integer getSfzydk() {
		return sfzydk;
	}

	public void setSfzydk(Integer sfzydk) {
		this.sfzydk = sfzydk;
	}
	
	@Length(min=0, max=100, message="yrdwmc长度必须介于 0 和 100 之间")
	public String getYrdwmc() {
		return yrdwmc;
	}

	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	
	@Length(min=0, max=100, message="yrdwdm长度必须介于 0 和 100 之间")
	public String getYrdwdm() {
		return yrdwdm;
	}

	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	
	@Length(min=0, max=100, message="yrdwxz长度必须介于 0 和 100 之间")
	public String getYrdwxz() {
		return yrdwxz;
	}

	public void setYrdwxz(String yrdwxz) {
		this.yrdwxz = yrdwxz;
	}
	
	@Length(min=0, max=100, message="dwlbmc长度必须介于 0 和 100 之间")
	public String getDwlbmc() {
		return dwlbmc;
	}

	public void setDwlbmc(String dwlbmc) {
		this.dwlbmc = dwlbmc;
	}
	
	@Length(min=0, max=100, message="dwszdmc长度必须介于 0 和 100 之间")
	public String getDwszdmc() {
		return dwszdmc;
	}

	public void setDwszdmc(String dwszdmc) {
		this.dwszdmc = dwszdmc;
	}
	
	@Length(min=0, max=50, message="dwlxr长度必须介于 0 和 50 之间")
	public String getDwlxr() {
		return dwlxr;
	}

	public void setDwlxr(String dwlxr) {
		this.dwlxr = dwlxr;
	}
	
	@Length(min=0, max=64, message="dwdh长度必须介于 0 和 64 之间")
	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	
	@Length(min=0, max=100, message="gzzwlbmc长度必须介于 0 和 100 之间")
	public String getGzzwlbmc() {
		return gzzwlbmc;
	}

	public void setGzzwlbmc(String gzzwlbmc) {
		this.gzzwlbmc = gzzwlbmc;
	}


	
	@Length(min=0, max=100, message="bdzszdmc长度必须介于 0 和 100 之间")
	public String getBdzszdmc() {
		return bdzszdmc;
	}

	public void setBdzszdmc(String bdzszdmc) {
		this.bdzszdmc = bdzszdmc;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBdkssj() {
		return bdkssj;
	}

	public void setBdkssj(Date bdkssj) {
		this.bdkssj = bdkssj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBdjssj() {
		return bdjssj;
	}

	public void setBdjssj(Date bdjssj) {
		this.bdjssj = bdjssj;
	}
	

	
	@Length(min=0, max=100, message="datddw长度必须介于 0 和 100 之间")
	public String getDatddw() {
		return datddw;
	}

	public void setDatddw(String datddw) {
		this.datddw = datddw;
	}
	

	
	@Length(min=0, max=100, message="datdxxdz长度必须介于 0 和 100 之间")
	public String getDatdxxdz() {
		return datdxxdz;
	}

	public void setDatdxxdz(String datdxxdz) {
		this.datdxxdz = datdxxdz;
	}
	

	
	@Length(min=0, max=100, message="hkqydz长度必须介于 0 和 100 之间")
	public String getHkqydz() {
		return hkqydz;
	}

	public void setHkqydz(String hkqydz) {
		this.hkqydz = hkqydz;
	}
	
	@Length(min=0, max=200, message="bzone长度必须介于 0 和 200 之间")
	public String getBzone() {
		return bzone;
	}

	public void setBzone(String bzone) {
		this.bzone = bzone;
	}
	
	@Length(min=0, max=200, message="bztwo长度必须介于 0 和 200 之间")
	public String getBztwo() {
		return bztwo;
	}

	public void setBztwo(String bztwo) {
		this.bztwo = bztwo;
	}
	
	@Length(min=0, max=200, message="bzthree长度必须介于 0 和 200 之间")
	public String getBzthree() {
		return bzthree;
	}

	public void setBzthree(String bzthree) {
		this.bzthree = bzthree;
	}



}