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
public class BckjBizJbxx extends DataWithExpEntity<BckjBizJbxx> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String sfzzp;		// sfzzp
	private String xm;		// xm
	private String sfzh;		// sfzh
	private Date csrq;		// csrq
	private String zzmm;		// zzmm
	private String jdxy;		// jdxy
	private String mz;		// mz
	private String xjdq;		// xjdq
	private Integer xb;		// xb
	private String jtzz;		// jtzz
	private String yb;		// yb
	private String lxdh;		// lxdh
	private String area;		// area
	private String city;		// city
	private String prov;		// prov
	private String grsg;		// grsg
	private String grtz;		// grtz
	private String grsl;		// grsl
	private String tcah;		// tcah
	private String grzp;		// grzp
	private String kslb;		// kslb

	
	public BckjBizJbxx() {
		super();
	}

	public BckjBizJbxx(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	@Length(min=0, max=200, message="sfzzp长度必须介于 0 和 200 之间")
	public String getSfzzp() {
		return sfzzp;
	}

	public void setSfzzp(String sfzzp) {
		this.sfzzp = sfzzp;
	}
	
	@Length(min=0, max=20, message="xm长度必须介于 0 和 20 之间")
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Length(min=0, max=20, message="sfzh长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=20, message="zzmm长度必须介于 0 和 20 之间")
	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	
	@Length(min=0, max=200, message="jdxy长度必须介于 0 和 200 之间")
	public String getJdxy() {
		return jdxy;
	}

	public void setJdxy(String jdxy) {
		this.jdxy = jdxy;
	}
	
	@Length(min=0, max=20, message="mz长度必须介于 0 和 20 之间")
	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}
	
	@Length(min=0, max=200, message="xjdq长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=200, message="jtzz长度必须介于 0 和 200 之间")
	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	
	@Length(min=0, max=6, message="yb长度必须介于 0 和 6 之间")
	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}
	
	@Length(min=0, max=20, message="lxdh长度必须介于 0 和 20 之间")
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Length(min=0, max=20, message="area长度必须介于 0 和 20 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=20, message="city长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=20, message="prov长度必须介于 0 和 20 之间")
	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
	
	@Length(min=0, max=20, message="grsg长度必须介于 0 和 20 之间")
	public String getGrsg() {
		return grsg;
	}

	public void setGrsg(String grsg) {
		this.grsg = grsg;
	}
	
	@Length(min=0, max=20, message="grtz长度必须介于 0 和 20 之间")
	public String getGrtz() {
		return grtz;
	}

	public void setGrtz(String grtz) {
		this.grtz = grtz;
	}
	
	@Length(min=0, max=50, message="grsl长度必须介于 0 和 50 之间")
	public String getGrsl() {
		return grsl;
	}

	public void setGrsl(String grsl) {
		this.grsl = grsl;
	}
	
	@Length(min=0, max=200, message="tcah长度必须介于 0 和 200 之间")
	public String getTcah() {
		return tcah;
	}

	public void setTcah(String tcah) {
		this.tcah = tcah;
	}
	
	@Length(min=0, max=200, message="grzp长度必须介于 0 和 200 之间")
	public String getGrzp() {
		return grzp;
	}

	public void setGrzp(String grzp) {
		this.grzp = grzp;
	}
	
	@Length(min=0, max=20, message="kslb长度必须介于 0 和 20 之间")
	public String getKslb() {
		return kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
	}

	
}