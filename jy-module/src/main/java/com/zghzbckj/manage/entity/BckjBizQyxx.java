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
public class BckjBizQyxx extends DataWithExpEntity<BckjBizQyxx> {
	
	private static final long serialVersionUID = 1L;

	private String qyTysh;		// qy_tysh
	private String qymc;		// qymc
	private String qydz;		// qydz
	private String qylxfs;		// qylxfs
	private String qyYx;		// qy_yx
	private String qyLxr;		// qy_lxr
	private String qyLxrdh;		// qy_lxrdh
	private String qyProv;		// qy_prov
	private String qyCity;		// qy_city
	private String qyArea;		// qy_area
	private String qyGsxz;		// qy_gsxz
	private String qyHylb;		// qy_hylb
	private String qyGsgm;		// qy_gsgm
	private String qyGswz;		// qy_gswz
	private String qyFrdbxm;		// qy_frdbxm
	private String qyZczj;		// qy_zczj
	private String qyGsjs;		// qy_gsjs
	private Date qyZcsj;		// qy_zcsj
	private String qyFrsfz;		// qy_frsfz
	private String qyYyzzzp;		// qy_yyzzzp
	private String qyFrsfzzp;		// qy_frsfzzp
	private String qybq;		// qybq
	private String memo;		// memo

	
	public BckjBizQyxx() {
		super();
	}

	
	@Length(min=0, max=20, message="qy_tysh长度必须介于 0 和 20 之间")
	public String getQyTysh() {
		return qyTysh;
	}

	public void setQyTysh(String qyTysh) {
		this.qyTysh = qyTysh;
	}
	
	@Length(min=0, max=100, message="qymc长度必须介于 0 和 100 之间")
	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
	@Length(min=0, max=200, message="qydz长度必须介于 0 和 200 之间")
	public String getQydz() {
		return qydz;
	}

	public void setQydz(String qydz) {
		this.qydz = qydz;
	}
	
	@Length(min=0, max=20, message="qylxfs长度必须介于 0 和 20 之间")
	public String getQylxfs() {
		return qylxfs;
	}

	public void setQylxfs(String qylxfs) {
		this.qylxfs = qylxfs;
	}
	
	@Length(min=0, max=50, message="qy_yx长度必须介于 0 和 50 之间")
	public String getQyYx() {
		return qyYx;
	}

	public void setQyYx(String qyYx) {
		this.qyYx = qyYx;
	}
	
	@Length(min=0, max=20, message="qy_lxr长度必须介于 0 和 20 之间")
	public String getQyLxr() {
		return qyLxr;
	}

	public void setQyLxr(String qyLxr) {
		this.qyLxr = qyLxr;
	}
	
	@Length(min=0, max=100, message="qy_lxrdh长度必须介于 0 和 100 之间")
	public String getQyLxrdh() {
		return qyLxrdh;
	}

	public void setQyLxrdh(String qyLxrdh) {
		this.qyLxrdh = qyLxrdh;
	}
	
	@Length(min=0, max=20, message="qy_prov长度必须介于 0 和 20 之间")
	public String getQyProv() {
		return qyProv;
	}

	public void setQyProv(String qyProv) {
		this.qyProv = qyProv;
	}
	
	@Length(min=0, max=20, message="qy_city长度必须介于 0 和 20 之间")
	public String getQyCity() {
		return qyCity;
	}

	public void setQyCity(String qyCity) {
		this.qyCity = qyCity;
	}
	
	@Length(min=0, max=20, message="qy_area长度必须介于 0 和 20 之间")
	public String getQyArea() {
		return qyArea;
	}

	public void setQyArea(String qyArea) {
		this.qyArea = qyArea;
	}
	
	@Length(min=0, max=20, message="qy_gsxz长度必须介于 0 和 20 之间")
	public String getQyGsxz() {
		return qyGsxz;
	}

	public void setQyGsxz(String qyGsxz) {
		this.qyGsxz = qyGsxz;
	}
	
	@Length(min=0, max=20, message="qy_hylb长度必须介于 0 和 20 之间")
	public String getQyHylb() {
		return qyHylb;
	}

	public void setQyHylb(String qyHylb) {
		this.qyHylb = qyHylb;
	}
	
	@Length(min=0, max=20, message="qy_gsgm长度必须介于 0 和 20 之间")
	public String getQyGsgm() {
		return qyGsgm;
	}

	public void setQyGsgm(String qyGsgm) {
		this.qyGsgm = qyGsgm;
	}
	
	@Length(min=0, max=200, message="qy_gswz长度必须介于 0 和 200 之间")
	public String getQyGswz() {
		return qyGswz;
	}

	public void setQyGswz(String qyGswz) {
		this.qyGswz = qyGswz;
	}
	
	@Length(min=0, max=20, message="qy_frdbxm长度必须介于 0 和 20 之间")
	public String getQyFrdbxm() {
		return qyFrdbxm;
	}

	public void setQyFrdbxm(String qyFrdbxm) {
		this.qyFrdbxm = qyFrdbxm;
	}
	
	public String getQyZczj() {
		return qyZczj;
	}

	public void setQyZczj(String qyZczj) {
		this.qyZczj = qyZczj;
	}
	
	public String getQyGsjs() {
		return qyGsjs;
	}

	public void setQyGsjs(String qyGsjs) {
		this.qyGsjs = qyGsjs;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQyZcsj() {
		return qyZcsj;
	}

	public void setQyZcsj(Date qyZcsj) {
		this.qyZcsj = qyZcsj;
	}
	
	@Length(min=0, max=20, message="qy_frsfz长度必须介于 0 和 20 之间")
	public String getQyFrsfz() {
		return qyFrsfz;
	}

	public void setQyFrsfz(String qyFrsfz) {
		this.qyFrsfz = qyFrsfz;
	}
	
	@Length(min=0, max=100, message="qy_yyzzzp长度必须介于 0 和 100 之间")
	public String getQyYyzzzp() {
		return qyYyzzzp;
	}

	public void setQyYyzzzp(String qyYyzzzp) {
		this.qyYyzzzp = qyYyzzzp;
	}
	
	@Length(min=0, max=100, message="qy_frsfzzp长度必须介于 0 和 100 之间")
	public String getQyFrsfzzp() {
		return qyFrsfzzp;
	}

	public void setQyFrsfzzp(String qyFrsfzzp) {
		this.qyFrsfzzp = qyFrsfzzp;
	}
	
	@Length(min=0, max=1000, message="qybq长度必须介于 0 和 1000 之间")
	public String getQybq() {
		return qybq;
	}

	public void setQybq(String qybq) {
		this.qybq = qybq;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	
}