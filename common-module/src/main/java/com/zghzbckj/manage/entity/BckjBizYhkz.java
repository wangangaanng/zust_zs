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
public class BckjBizYhkz extends DataWithExpEntity<BckjBizYhkz> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private Integer 0lx;		// 0lx
	private String xsxh;		// xsxh
	private String xsxy;		// xsxy
	private String xszy;		// xszy
	private String xsbj;		// xsbj
	private String xsnj;		// xsnj
	private String xsxm;		// xsxm
	private String lxr;		// lxr
	private String memo;		// memo
	
	public BckjBizYhkz() {
		super();
	}

	public BckjBizYhkz(String id){
		super(id);
	}

	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	public Integer get0lx() {
		return 0lx;
	}

	public void set0lx(Integer 0lx) {
		this.0lx = 0lx;
	}
	
	@Length(min=0, max=40, message="xsxh长度必须介于 0 和 40 之间")
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	@Length(min=0, max=60, message="xsxy长度必须介于 0 和 60 之间")
	public String getXsxy() {
		return xsxy;
	}

	public void setXsxy(String xsxy) {
		this.xsxy = xsxy;
	}
	
	@Length(min=0, max=60, message="xszy长度必须介于 0 和 60 之间")
	public String getXszy() {
		return xszy;
	}

	public void setXszy(String xszy) {
		this.xszy = xszy;
	}
	
	@Length(min=0, max=60, message="xsbj长度必须介于 0 和 60 之间")
	public String getXsbj() {
		return xsbj;
	}

	public void setXsbj(String xsbj) {
		this.xsbj = xsbj;
	}
	
	@Length(min=0, max=60, message="xsnj长度必须介于 0 和 60 之间")
	public String getXsnj() {
		return xsnj;
	}

	public void setXsnj(String xsnj) {
		this.xsnj = xsnj;
	}
	
	@Length(min=0, max=20, message="xsxm长度必须介于 0 和 20 之间")
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	
	@Length(min=0, max=20, message="lxr长度必须介于 0 和 20 之间")
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}