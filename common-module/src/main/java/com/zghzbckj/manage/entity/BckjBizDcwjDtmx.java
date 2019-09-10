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
public class BckjBizDcwjDtmx extends DataWithExpEntity<BckjBizDcwjDtmx> {
	
	private static final long serialVersionUID = 1L;

	private String dcwjjgRefOwid;		// dcwjjg_ref_owid
	private String dcwjtmRefOwid;		// dcwjtm_ref_owid
	private String wjda;		// wjda
	private BigDecimal tmdf;		// tmdf
	private String memo;		// memo

	
	public BckjBizDcwjDtmx() {
		super();
	}



	
	@Length(min=0, max=64, message="dcwjjg_ref_owid长度必须介于 0 和 64 之间")
	public String getDcwjjgRefOwid() {
		return dcwjjgRefOwid;
	}

	public void setDcwjjgRefOwid(String dcwjjgRefOwid) {
		this.dcwjjgRefOwid = dcwjjgRefOwid;
	}
	
	@Length(min=0, max=64, message="dcwjtm_ref_owid长度必须介于 0 和 64 之间")
	public String getDcwjtmRefOwid() {
		return dcwjtmRefOwid;
	}

	public void setDcwjtmRefOwid(String dcwjtmRefOwid) {
		this.dcwjtmRefOwid = dcwjtmRefOwid;
	}
	
	@Length(min=1, max=20, message="wjda长度必须介于 1 和 20 之间")
	public String getWjda() {
		return wjda;
	}

	public void setWjda(String wjda) {
		this.wjda = wjda;
	}
	
	public BigDecimal getTmdf() {
		return tmdf;
	}

	public void setTmdf(BigDecimal tmdf) {
		this.tmdf = tmdf;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}