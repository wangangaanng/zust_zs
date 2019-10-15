package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;


/**
 * 就业方案补充类Entity
 * @author wangangaanng
 * @version 2019-10-10
 */
public class BckjBizJypuchong extends DataWithExpEntity<BckjBizJypuchong> {
	
	private static final long serialVersionUID = 1L;


	private String yhRefOwid;		// yh_ref_owid
	private String xsxh;		// xsxh
	private int sfsf;        //sfsf
	private Integer sfzz;		// sfzz
	private Integer sfdlxy;		// sfdlxy
	private String jyqdbz;		// jyqdbz
	private String zgdwdm;		// zgdwdm
	private String zgdwmc;		// zgdwmc
	private String yrdwlsmc;		// yrdwlsmc
	private String bddddm;		// bddddm
	private String xjcqk;		// xjcqk

	public int getSfsf() {
		return sfsf;
	}

	public void setSfsf(int sfsf) {
		this.sfsf = sfsf;
	}

	public String getDwszddm() {
		return dwszddm;
	}

	public void setDwszddm(String dwszddm) {
		this.dwszddm = dwszddm;
	}

	private String dwszddm;


	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}

	public String getJyqdbz() {
		return jyqdbz;
	}

	public void setJyqdbz(String jyqdbz) {
		this.jyqdbz = jyqdbz;
	}

	@Length(min=0, max=100, message="xsxh长度必须介于 0 和 100 之间")
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
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
	

	
	@Length(min=0, max=100, message="zgdwdm长度必须介于 0 和 100 之间")
	public String getZgdwdm() {
		return zgdwdm;
	}

	public void setZgdwdm(String zgdwdm) {
		this.zgdwdm = zgdwdm;
	}
	
	@Length(min=0, max=100, message="zgdwmc长度必须介于 0 和 100 之间")
	public String getZgdwmc() {
		return zgdwmc;
	}

	public void setZgdwmc(String zgdwmc) {
		this.zgdwmc = zgdwmc;
	}
	
	@Length(min=0, max=100, message="yrdwlsmc长度必须介于 0 和 100 之间")
	public String getYrdwlsmc() {
		return yrdwlsmc;
	}

	public void setYrdwlsmc(String yrdwlsmc) {
		this.yrdwlsmc = yrdwlsmc;
	}
	
	@Length(min=0, max=100, message="bddddm长度必须介于 0 和 100 之间")
	public String getBddddm() {
		return bddddm;
	}

	public void setBddddm(String bddddm) {
		this.bddddm = bddddm;
	}
	
	@Length(min=0, max=100, message="xjcqk长度必须介于 0 和 100 之间")
	public String getXjcqk() {
		return xjcqk;
	}

	public void setXjcqk(String xjcqk) {
		this.xjcqk = xjcqk;
	}



}