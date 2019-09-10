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
public class BckjBizYhgl extends DataWithExpEntity<BckjBizYhgl> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String wxbh;		// wxbh
	private Integer sfgz;		// sfgz
	private String openid;		// openid
	private Date gzsj;		// gzsj
	private String memo;		// memo


	
	public BckjBizYhgl() {
		super();
	}



	
	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	@Length(min=0, max=200, message="wxbh长度必须介于 0 和 200 之间")
	public String getWxbh() {
		return wxbh;
	}

	public void setWxbh(String wxbh) {
		this.wxbh = wxbh;
	}
	
	public Integer getSfgz() {
		return sfgz;
	}

	public void setSfgz(Integer sfgz) {
		this.sfgz = sfgz;
	}
	
	@Length(min=0, max=100, message="openid长度必须介于 0 和 100 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGzsj() {
		return gzsj;
	}

	public void setGzsj(Date gzsj) {
		this.gzsj = gzsj;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	
}