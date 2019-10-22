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
public class BckjBizDcwj extends DataWithExpEntity<BckjBizDcwj> {
	
	private static final long serialVersionUID = 1L;
	private String wzbh;		// wzbh
	private Integer wjlx;		// wjlx
	private String wjmc;		// wjmc
	private String wjjjtp;		// wjjjtp
	private String wjjj;		// wjjj
	private String wjsm;		// wjsm
	private Date kssj;		// kssj
	private Date jssj;		// jssj
	private Integer dcrs;		// dcrs
	private Integer sfdl;		// sfdl
	private Integer mxdx;		// mxdx
	private String wjnd;		// wjnd
	private Integer sfyx;		// sfyx

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	private Boolean readOnly;


	
	public BckjBizDcwj() {
		super();
	}

	
	@Length(min=0, max=20, message="wzbh长度必须介于 0 和 20 之间")
	public String getWzbh() {
		return wzbh;
	}

	public void setWzbh(String wzbh) {
		this.wzbh = wzbh;
	}
	
	public Integer getWjlx() {
		return wjlx;
	}

	public void setWjlx(Integer wjlx) {
		this.wjlx = wjlx;
	}
	
	@Length(min=0, max=100, message="wjmc长度必须介于 0 和 100 之间")
	public String getWjmc() {
		return wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	
	@Length(min=0, max=200, message="wjjjtp长度必须介于 0 和 200 之间")
	public String getWjjjtp() {
		return wjjjtp;
	}

	public void setWjjjtp(String wjjjtp) {
		this.wjjjtp = wjjjtp;
	}
	
	@Length(min=0, max=500, message="wjjj长度必须介于 0 和 500 之间")
	public String getWjjj() {
		return wjjj;
	}

	public void setWjjj(String wjjj) {
		this.wjjj = wjjj;
	}
	
	@Length(min=0, max=2000, message="wjsm长度必须介于 0 和 2000 之间")
	public String getWjsm() {
		return wjsm;
	}

	public void setWjsm(String wjsm) {
		this.wjsm = wjsm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}
	
	public Integer getDcrs() {
		return dcrs;
	}

	public void setDcrs(Integer dcrs) {
		this.dcrs = dcrs;
	}
	
	public Integer getSfdl() {
		return sfdl;
	}

	public void setSfdl(Integer sfdl) {
		this.sfdl = sfdl;
	}
	
	public Integer getMxdx() {
		return mxdx;
	}

	public void setMxdx(Integer mxdx) {
		this.mxdx = mxdx;
	}
	
	@Length(min=0, max=4, message="wjnd长度必须介于 0 和 4 之间")
	public String getWjnd() {
		return wjnd;
	}

	public void setWjnd(String wjnd) {
		this.wjnd = wjnd;
	}
	
	public Integer getSfyx() {
		return sfyx;
	}

	public void setSfyx(Integer sfyx) {
		this.sfyx = sfyx;
	}

	
}