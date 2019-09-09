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
public class BckjBizZxzx extends DataWithExpEntity<BckjBizZxzx> {
	
	private static final long serialVersionUID = 1L;

	private Integer zxlx;		// zxlx
	private String wtnr;		// wtnr
	private String danr;		// danr
	private Date twrq;		// twrq
	private Date hdrq;		// hdrq
	private String yx;		// yx
	private String dh;		// dh
	private Integer sfxs;		// sfxs
	private String lyip;		// lyip
	private String twOwid;		// tw_owid
	private String twName;		// tw_name
	private String hfOwid;		// hf_owid
	private String hfName;		// hf_name
	private String zxzyid;		// zxzyid

	
	public BckjBizZxzx() {
		super();
	}

	public BckjBizZxzx(String id){
		super(id);
	}


	
	public Integer getZxlx() {
		return zxlx;
	}

	public void setZxlx(Integer zxlx) {
		this.zxlx = zxlx;
	}
	
	@Length(min=0, max=1000, message="wtnr长度必须介于 0 和 1000 之间")
	public String getWtnr() {
		return wtnr;
	}

	public void setWtnr(String wtnr) {
		this.wtnr = wtnr;
	}
	
	@Length(min=0, max=1000, message="danr长度必须介于 0 和 1000 之间")
	public String getDanr() {
		return danr;
	}

	public void setDanr(String danr) {
		this.danr = danr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTwrq() {
		return twrq;
	}

	public void setTwrq(Date twrq) {
		this.twrq = twrq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHdrq() {
		return hdrq;
	}

	public void setHdrq(Date hdrq) {
		this.hdrq = hdrq;
	}
	
	@Length(min=0, max=200, message="yx长度必须介于 0 和 200 之间")
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}
	
	@Length(min=0, max=36, message="dh长度必须介于 0 和 36 之间")
	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}
	
	public Integer getSfxs() {
		return sfxs;
	}

	public void setSfxs(Integer sfxs) {
		this.sfxs = sfxs;
	}
	
	@Length(min=0, max=20, message="lyip长度必须介于 0 和 20 之间")
	public String getLyip() {
		return lyip;
	}

	public void setLyip(String lyip) {
		this.lyip = lyip;
	}
	
	@Length(min=0, max=64, message="tw_owid长度必须介于 0 和 64 之间")
	public String getTwOwid() {
		return twOwid;
	}

	public void setTwOwid(String twOwid) {
		this.twOwid = twOwid;
	}
	
	@Length(min=0, max=100, message="tw_name长度必须介于 0 和 100 之间")
	public String getTwName() {
		return twName;
	}

	public void setTwName(String twName) {
		this.twName = twName;
	}
	
	@Length(min=0, max=64, message="hf_owid长度必须介于 0 和 64 之间")
	public String getHfOwid() {
		return hfOwid;
	}

	public void setHfOwid(String hfOwid) {
		this.hfOwid = hfOwid;
	}
	
	@Length(min=0, max=100, message="hf_name长度必须介于 0 和 100 之间")
	public String getHfName() {
		return hfName;
	}

	public void setHfName(String hfName) {
		this.hfName = hfName;
	}
	
	@Length(min=0, max=64, message="zxzyid长度必须介于 0 和 64 之间")
	public String getZxzyid() {
		return zxzyid;
	}

	public void setZxzyid(String zxzyid) {
		this.zxzyid = zxzyid;
	}

}