/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizZsjh extends DataWithExpEntity<BckjBizZsjh> {
	
	private static final long serialVersionUID = 1L;
	private String xybh;		// xybh
	private String nf;		// 2014，2015
	private String sf;		// sf
	private String kl;		// kl
	private String pc;		// pc
	private String zy;		// zy
	private String xz;		// 一年，四年，三年
	private Integer zss;		// zss
	private BigDecimal xf;		// xf
	private String syxw;		// syxw

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	private boolean readOnly;

	
	public BckjBizZsjh() {
		super();
	}

	
	@Length(min=0, max=20, message="xybh长度必须介于 0 和 20 之间")
	public String getXybh() {
		return xybh;
	}

	public void setXybh(String xybh) {
		this.xybh = xybh;
	}
	
	@Length(min=0, max=10, message="2014，2015长度必须介于 0 和 10 之间")
	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}
	
	@Length(min=0, max=100, message="sf长度必须介于 0 和 100 之间")
	public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}
	
	@Length(min=0, max=100, message="kl长度必须介于 0 和 100 之间")
	public String getKl() {
		return kl;
	}

	public void setKl(String kl) {
		this.kl = kl;
	}
	
	@Length(min=0, max=100, message="pc长度必须介于 0 和 100 之间")
	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}
	
	@Length(min=0, max=100, message="zy长度必须介于 0 和 100 之间")
	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}
	
	@Length(min=0, max=10, message="一年，四年，三年长度必须介于 0 和 10 之间")
	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}
	
	public Integer getZss() {
		return zss;
	}

	public void setZss(Integer zss) {
		this.zss = zss;
	}
	
	public BigDecimal getXf() {
		return xf;
	}

	public void setXf(BigDecimal xf) {
		this.xf = xf;
	}
	
	@Length(min=0, max=100, message="syxw长度必须介于 0 和 100 之间")
	public String getSyxw() {
		return syxw;
	}

	public void setSyxw(String syxw) {
		this.syxw = syxw;
	}

	
}