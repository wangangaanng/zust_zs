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
public class BckjBizFzsz extends DataWithExpEntity<BckjBizFzsz> {
	
	private static final long serialVersionUID = 1L;
	private String zh;		// zh
	private String xxbh;		// xxbh
	private Integer fzlb;		// fzlb
	private String zhbh;		// zhbh
	private Integer zhrs;		// zhrs
	private Integer ksxh;		// ksxh
	private Integer jsxh;		// jsxh
	private String dl;		// dl
	private String dlid;		// dlid
	private String memo;		// memo

	
	public BckjBizFzsz() {
		super();
	}

	
	@Length(min=0, max=100, message="zh长度必须介于 0 和 100 之间")
	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}
	
	@Length(min=0, max=20, message="xxbh长度必须介于 0 和 20 之间")
	public String getXxbh() {
		return xxbh;
	}

	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	
	public Integer getFzlb() {
		return fzlb;
	}

	public void setFzlb(Integer fzlb) {
		this.fzlb = fzlb;
	}
	
	@Length(min=0, max=20, message="zhbh长度必须介于 0 和 20 之间")
	public String getZhbh() {
		return zhbh;
	}

	public void setZhbh(String zhbh) {
		this.zhbh = zhbh;
	}
	
	public Integer getZhrs() {
		return zhrs;
	}

	public void setZhrs(Integer zhrs) {
		this.zhrs = zhrs;
	}
	
	public Integer getKsxh() {
		return ksxh;
	}

	public void setKsxh(Integer ksxh) {
		this.ksxh = ksxh;
	}
	
	public Integer getJsxh() {
		return jsxh;
	}

	public void setJsxh(Integer jsxh) {
		this.jsxh = jsxh;
	}
	
	@Length(min=0, max=20, message="dl长度必须介于 0 和 20 之间")
	public String getDl() {
		return dl;
	}

	public void setDl(String dl) {
		this.dl = dl;
	}
	
	@Length(min=0, max=20, message="dlid长度必须介于 0 和 20 之间")
	public String getDlid() {
		return dlid;
	}

	public void setDlid(String dlid) {
		this.dlid = dlid;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}