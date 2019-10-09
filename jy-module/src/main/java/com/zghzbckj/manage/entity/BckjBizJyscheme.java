/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * 就业方案Entity
 * @author wangangaanng
 * @version 2019-09-30
 */
public class BckjBizJyscheme extends DataWithExpEntity<BckjBizJyscheme> {
	
	private static final long serialVersionUID = 1L;

	private String xsxh;		// xsxh_ref_owid
	private String xm;		// xm
	private String sfz;
	private String xsxy;
	private String xszy;
	private Integer xb;
	private String syd;
	private String pyfs;
	private String zyfx;
	private String sjh;
	private String xxmc;		// xxmc
	private String byxl;
	private String byqx;		// byqx
	private Integer sfzydk;		// sfzydk
	private String yrdwmc;		// yrdwmc
	private String yrdwdm;		// yrdwdm
	private String yrdwxz;		// yrdwxz
	private String dwlbmc;		// dwlbmc
	private String dwszdmc;		// dwszdmc
	private String dwlxr;		// dwlxr
	private String dwdh;		// dwdh
	private String gzzwlbmc;		// gzzwlbmc



	private String bdzqflbmc;       //bdzqflbmc
	private String bdzdwmc;		// bdzdwmc
	private String bdzszdmc;		// bdzszdmc
	private Date bdkssj;		// bdkssj
	private Date bdjssj;		// bdjssj
	private Integer sfdydwbdz;		// sfdydwbdz
	private String datddw;		// datddw
	private String bdzbz;		// bdzbz
	private String datdxxdz;		// datdxxdz
	private  String bdzbh;
	private String bdzlsh;
	private String datddh;		// datddh
	private String hkqydz;		// hkqydz
	private String bzone;		// bzone
	private String bztwo;		// bztwo
	private String bzthree;		// bzthree



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

	public String getPyfs() {
		return pyfs;
	}

	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}

	public String getZyfx() {
		return zyfx;
	}

	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
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

	public String getByxl() {
		return byxl;
	}

	public void setByxl(String byxl) {
		this.byxl = byxl;
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
	
	@Length(min=0, max=100, message="bdzdwmc长度必须介于 0 和 100 之间")
	public String getBdzdwmc() {
		return bdzdwmc;
	}

	public void setBdzdwmc(String bdzdwmc) {
		this.bdzdwmc = bdzdwmc;
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
	
	public Integer getSfdydwbdz() {
		return sfdydwbdz;
	}

	public void setSfdydwbdz(Integer sfdydwbdz) {
		this.sfdydwbdz = sfdydwbdz;
	}
	
	@Length(min=0, max=100, message="datddw长度必须介于 0 和 100 之间")
	public String getDatddw() {
		return datddw;
	}

	public void setDatddw(String datddw) {
		this.datddw = datddw;
	}
	
	@Length(min=0, max=100, message="bdzbz长度必须介于 0 和 100 之间")
	public String getBdzbz() {
		return bdzbz;
	}

	public void setBdzbz(String bdzbz) {
		this.bdzbz = bdzbz;
	}
	
	@Length(min=0, max=100, message="datdxxdz长度必须介于 0 和 100 之间")
	public String getDatdxxdz() {
		return datdxxdz;
	}

	public void setDatdxxdz(String datdxxdz) {
		this.datdxxdz = datdxxdz;
	}
	
	@Length(min=0, max=100, message="datddh长度必须介于 0 和 100 之间")
	public String getDatddh() {
		return datddh;
	}

	public void setDatddh(String datddh) {
		this.datddh = datddh;
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