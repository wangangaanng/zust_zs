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
public class BckjBizDcwjJg extends DataWithExpEntity<BckjBizDcwjJg> {

	private static final long serialVersionUID = 1L;
	private String dtrid;		// dtrid
	private String dtrxm;		// dtrxm
	private Date ksdt;		// ksdt
	private Date jsdt;		// jsdt
	private Integer dtsc;		// dtsc
	private BigDecimal zdf;		// zdf
	private Boolean readOnly;

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public BckjBizDcwjJg() {
		super();
	}



	@Length(min=0, max=64, message="dtrid长度必须介于 0 和 64 之间")
	public String getDtrid() {
		return dtrid;
	}

	public void setDtrid(String dtrid) {
		this.dtrid = dtrid;
	}

	@Length(min=0, max=100, message="dtrxm长度必须介于 0 和 100 之间")
	public String getDtrxm() {
		return dtrxm;
	}

	public void setDtrxm(String dtrxm) {
		this.dtrxm = dtrxm;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getKsdt() {
		return ksdt;
	}

	public void setKsdt(Date ksdt) {
		this.ksdt = ksdt;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJsdt() {
		return jsdt;
	}

	public void setJsdt(Date jsdt) {
		this.jsdt = jsdt;
	}

	public Integer getDtsc() {
		return dtsc;
	}

	public void setDtsc(Integer dtsc) {
		this.dtsc = dtsc;
	}

	public BigDecimal getZdf() {
		return zdf;
	}

	public void setZdf(BigDecimal zdf) {
		this.zdf = zdf;
	}


}