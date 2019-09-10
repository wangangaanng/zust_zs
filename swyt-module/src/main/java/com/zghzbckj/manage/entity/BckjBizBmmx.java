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
public class BckjBizBmmx extends DataWithExpEntity<BckjBizBmmx> {
	
	private static final long serialVersionUID = 1L;

	private String bmRefOwid;		// bm_ref_owid
	private String mxmc;		// mxmc
	private String mxnr;		// mxnr
	private Integer mxsx;		// mxsx
	private String memo;		// memo

	
	public BckjBizBmmx() {
		super();
	}




	
	@Length(min=0, max=64, message="bm_ref_owid长度必须介于 0 和 64 之间")
	public String getBmRefOwid() {
		return bmRefOwid;
	}

	public void setBmRefOwid(String bmRefOwid) {
		this.bmRefOwid = bmRefOwid;
	}
	
	@Length(min=0, max=20, message="mxmc长度必须介于 0 和 20 之间")
	public String getMxmc() {
		return mxmc;
	}

	public void setMxmc(String mxmc) {
		this.mxmc = mxmc;
	}
	
	@Length(min=0, max=500, message="mxnr长度必须介于 0 和 500 之间")
	public String getMxnr() {
		return mxnr;
	}

	public void setMxnr(String mxnr) {
		this.mxnr = mxnr;
	}
	
	public Integer getMxsx() {
		return mxsx;
	}

	public void setMxsx(Integer mxsx) {
		this.mxsx = mxsx;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	
}