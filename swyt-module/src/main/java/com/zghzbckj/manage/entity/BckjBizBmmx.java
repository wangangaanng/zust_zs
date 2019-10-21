/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-10-21
 */
public class BckjBizBmmx extends DataWithExpEntity<BckjBizBmmx> {
	
	private static final long serialVersionUID = 1L;
	private String bmRefOwid;		// bm_ref_owid
	private Integer lx;		// 0会考（加综合测评4项）；1选考；2综合测评
	private String mxmc;		// mxmc
	private String mxnr;		// mxnr
	private Integer mxsx;		// mxsx

	
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
	
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}
	
	@Length(min=0, max=20, message="mxmc长度必须介于 0 和 20 之间")
	public String getMxmc() {
		return mxmc;
	}

	public void setMxmc(String mxmc) {
		this.mxmc = mxmc;
	}
	
	@Length(min=0, max=1000, message="mxnr长度必须介于 0 和 1000 之间")
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

}