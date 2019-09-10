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
public class BckjBizLntj extends DataWithExpEntity<BckjBizLntj> {
	
	private static final long serialVersionUID = 1L;
	private String xxbh;		// xxbh
	private String nf;		// 2014，2015
	private String sf;		// sf
	private String kl;		// kl
	private String pc;		// pc
	private String zy;		// zy
	private String xz;		// 一年，四年，三年
	private Integer lqs;		// lqs
	private BigDecimal zgf;		// zgf
	private BigDecimal zdf;		// zdf
	private BigDecimal pjf;		// pjf
	private String szxy;		// szxy

	public BckjBizLntj() {
		super();
	}

	
	@Length(min=0, max=10, message="xxbh长度必须介于 0 和 10 之间")
	public String getXxbh() {
		return xxbh;
	}

	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
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
	
	public Integer getLqs() {
		return lqs;
	}

	public void setLqs(Integer lqs) {
		this.lqs = lqs;
	}
	
	public BigDecimal getZgf() {
		return zgf;
	}

	public void setZgf(BigDecimal zgf) {
		this.zgf = zgf;
	}
	
	public BigDecimal getZdf() {
		return zdf;
	}

	public void setZdf(BigDecimal zdf) {
		this.zdf = zdf;
	}
	
	public BigDecimal getPjf() {
		return pjf;
	}

	public void setPjf(BigDecimal pjf) {
		this.pjf = pjf;
	}
	
	@Length(min=0, max=100, message="szxy长度必须介于 0 和 100 之间")
	public String getSzxy() {
		return szxy;
	}

	public void setSzxy(String szxy) {
		this.szxy = szxy;
	}
	

	
}