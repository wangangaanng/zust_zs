/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizBm extends DataWithExpEntity<BckjBizBm> {
	
	private static final long serialVersionUID = 1L;
	private Integer bkzyRefOwid;		// bkzy_ref_owid
	private String xxbh;		// xxbh
	private String bmnd;		// bmnd
	private Integer xzzy;		// xzzy
	private String xzzylj;		// xzzylj
	private String xzzymc;		// xzzymc
	private String wyyz;		// wyyz
	private Date sqsj;		// sqsj
	private Date dysj;		// dysj
	private Integer dycs;		// dycs
	private Date yjsj;		// yjsj
	private String ems;		// ems
	private String bmbZp;		// bmb_zp
	private Date sdsj;		// sdsj
	private Date cssj;		// cssj
	private String jjly;		// jjly
	private Date jfsj;		// jfsj
	private String jfpzh;		// jfpzh
	private String jfpzZp;		// jfpz_zp
	private Date mssj;		// mssj
	private String msfzh;		// msfzh
	private String msfzZnxh;		// msfz_znxh
	private Integer msfzSxh;		// msfz_sxh
	private String zkzh;		// zkzh
	private Integer rzbd;		// rzbd
	private String grzp;		// grzp
	private Date cqsj;		// cqsj
	private Date bdsj;		// bdsj
	private String zkzMb;		// zkz_mb
	private String bmbMb;		// bmb_mb
	private Date dySj;		// dy_sj
	private String xybnr;		// xybnr
	private String bscj;		// bscj
	private String mscj;		// mscj
	private String zzcj;		// zzcj

	
	public BckjBizBm() {
		super();
	}



	
	public Integer getBkzyRefOwid() {
		return bkzyRefOwid;
	}

	public void setBkzyRefOwid(Integer bkzyRefOwid) {
		this.bkzyRefOwid = bkzyRefOwid;
	}
	
	@Length(min=0, max=20, message="xxbh长度必须介于 0 和 20 之间")
	public String getXxbh() {
		return xxbh;
	}

	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	
	@Length(min=0, max=4, message="bmnd长度必须介于 0 和 4 之间")
	public String getBmnd() {
		return bmnd;
	}

	public void setBmnd(String bmnd) {
		this.bmnd = bmnd;
	}
	
	public Integer getXzzy() {
		return xzzy;
	}

	public void setXzzy(Integer xzzy) {
		this.xzzy = xzzy;
	}
	
	@Length(min=0, max=200, message="xzzylj长度必须介于 0 和 200 之间")
	public String getXzzylj() {
		return xzzylj;
	}

	public void setXzzylj(String xzzylj) {
		this.xzzylj = xzzylj;
	}
	
	@Length(min=0, max=100, message="xzzymc长度必须介于 0 和 100 之间")
	public String getXzzymc() {
		return xzzymc;
	}

	public void setXzzymc(String xzzymc) {
		this.xzzymc = xzzymc;
	}
	
	@Length(min=0, max=20, message="wyyz长度必须介于 0 和 20 之间")
	public String getWyyz() {
		return wyyz;
	}

	public void setWyyz(String wyyz) {
		this.wyyz = wyyz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSqsj() {
		return sqsj;
	}

	public void setSqsj(Date sqsj) {
		this.sqsj = sqsj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDysj() {
		return dysj;
	}

	public void setDysj(Date dysj) {
		this.dysj = dysj;
	}
	
	public Integer getDycs() {
		return dycs;
	}

	public void setDycs(Integer dycs) {
		this.dycs = dycs;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getYjsj() {
		return yjsj;
	}

	public void setYjsj(Date yjsj) {
		this.yjsj = yjsj;
	}
	
	@Length(min=0, max=20, message="ems长度必须介于 0 和 20 之间")
	public String getEms() {
		return ems;
	}

	public void setEms(String ems) {
		this.ems = ems;
	}
	
	@Length(min=0, max=200, message="bmb_zp长度必须介于 0 和 200 之间")
	public String getBmbZp() {
		return bmbZp;
	}

	public void setBmbZp(String bmbZp) {
		this.bmbZp = bmbZp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSdsj() {
		return sdsj;
	}

	public void setSdsj(Date sdsj) {
		this.sdsj = sdsj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCssj() {
		return cssj;
	}

	public void setCssj(Date cssj) {
		this.cssj = cssj;
	}
	
	@Length(min=0, max=200, message="jjly长度必须介于 0 和 200 之间")
	public String getJjly() {
		return jjly;
	}

	public void setJjly(String jjly) {
		this.jjly = jjly;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJfsj() {
		return jfsj;
	}

	public void setJfsj(Date jfsj) {
		this.jfsj = jfsj;
	}
	
	@Length(min=0, max=50, message="jfpzh长度必须介于 0 和 50 之间")
	public String getJfpzh() {
		return jfpzh;
	}

	public void setJfpzh(String jfpzh) {
		this.jfpzh = jfpzh;
	}
	
	@Length(min=0, max=200, message="jfpz_zp长度必须介于 0 和 200 之间")
	public String getJfpzZp() {
		return jfpzZp;
	}

	public void setJfpzZp(String jfpzZp) {
		this.jfpzZp = jfpzZp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMssj() {
		return mssj;
	}

	public void setMssj(Date mssj) {
		this.mssj = mssj;
	}
	
	@Length(min=0, max=50, message="msfzh长度必须介于 0 和 50 之间")
	public String getMsfzh() {
		return msfzh;
	}

	public void setMsfzh(String msfzh) {
		this.msfzh = msfzh;
	}
	
	@Length(min=0, max=50, message="msfz_znxh长度必须介于 0 和 50 之间")
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
	
	@Length(min=0, max=50, message="zkzh长度必须介于 0 和 50 之间")
	public String getZkzh() {
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
	
	@Length(min=0, max=200, message="grzp长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="zkz_mb长度必须介于 0 和 200 之间")
	public String getZkzMb() {
		return zkzMb;
	}

	public void setZkzMb(String zkzMb) {
		this.zkzMb = zkzMb;
	}
	
	@Length(min=0, max=200, message="bmb_mb长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="xybnr长度必须介于 0 和 200 之间")
	public String getXybnr() {
		return xybnr;
	}

	public void setXybnr(String xybnr) {
		this.xybnr = xybnr;
	}
	
	@Length(min=0, max=20, message="bscj长度必须介于 0 和 20 之间")
	public String getBscj() {
		return bscj;
	}

	public void setBscj(String bscj) {
		this.bscj = bscj;
	}
	
	@Length(min=0, max=20, message="mscj长度必须介于 0 和 20 之间")
	public String getMscj() {
		return mscj;
	}

	public void setMscj(String mscj) {
		this.mscj = mscj;
	}
	
	@Length(min=0, max=20, message="zzcj长度必须介于 0 和 20 之间")
	public String getZzcj() {
		return zzcj;
	}

	public void setZzcj(String zzcj) {
		this.zzcj = zzcj;
	}

	
}