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
public class BckjBizXxpz extends DataWithExpEntity<BckjBizXxpz> {
	
	private static final long serialVersionUID = 1L;
	private String xxbh;		// xxbh
	private String xxmc;		// xxmc
	private String xcjs;		// xcjs
	private String lxr;		// lxr
	private String lxdz;		// lxdz
	private String lxdh;		// lxdh
	private String bmnd;		// bmnd
	private Date kssj;		// kssj
	private Date jzsj;		// jzsj
	private Integer sfks;		// sfks
	private String xxlj;		// xxlj
	private String bmbMb;		// bmb_mb
	private String zkzMb;		// zkz_mb

	
	public BckjBizXxpz() {
		super();
	}

	public BckjBizXxpz(String id){
		super(id);
	}
	
	@Length(min=0, max=20, message="xxbh长度必须介于 0 和 20 之间")
	public String getXxbh() {
		return xxbh;
	}

	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	
	@Length(min=0, max=200, message="xxmc长度必须介于 0 和 200 之间")
	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	
	@Length(min=0, max=1000, message="xcjs长度必须介于 0 和 1000 之间")
	public String getXcjs() {
		return xcjs;
	}

	public void setXcjs(String xcjs) {
		this.xcjs = xcjs;
	}
	
	@Length(min=0, max=20, message="lxr长度必须介于 0 和 20 之间")
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	
	@Length(min=0, max=200, message="lxdz长度必须介于 0 和 200 之间")
	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	
	@Length(min=0, max=20, message="lxdh长度必须介于 0 和 20 之间")
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Length(min=0, max=4, message="bmnd长度必须介于 0 和 4 之间")
	public String getBmnd() {
		return bmnd;
	}

	public void setBmnd(String bmnd) {
		this.bmnd = bmnd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJzsj() {
		return jzsj;
	}

	public void setJzsj(Date jzsj) {
		this.jzsj = jzsj;
	}
	
	public Integer getSfks() {
		return sfks;
	}

	public void setSfks(Integer sfks) {
		this.sfks = sfks;
	}
	
	@Length(min=0, max=100, message="xxlj长度必须介于 0 和 100 之间")
	public String getXxlj() {
		return xxlj;
	}

	public void setXxlj(String xxlj) {
		this.xxlj = xxlj;
	}
	
	@Length(min=0, max=200, message="bmb_mb长度必须介于 0 和 200 之间")
	public String getBmbMb() {
		return bmbMb;
	}

	public void setBmbMb(String bmbMb) {
		this.bmbMb = bmbMb;
	}
	
	@Length(min=0, max=200, message="zkz_mb长度必须介于 0 和 200 之间")
	public String getZkzMb() {
		return zkzMb;
	}

	public void setZkzMb(String zkzMb) {
		this.zkzMb = zkzMb;
	}

	
}