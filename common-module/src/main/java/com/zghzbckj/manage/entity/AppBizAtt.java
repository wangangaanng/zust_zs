/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-04-13
 */
public class  AppBizAtt extends DataWithExpEntity<AppBizAtt> {
	
	private static final long serialVersionUID = 1L;
	private String wzRefOwid;		// wz_ref_owid
	private String fjmc;		// fjmc
	private Integer fjlx;		// fjlx
	private String fjlj;		// fjlj

	public AppBizAtt() {
		super();
	}


	@Length(min=0, max=64, message="wz_ref_owid长度必须介于 0 和 64 之间")
	public String getWzRefOwid() {
		return wzRefOwid;
	}

	public void setWzRefOwid(String wzRefOwid) {
		this.wzRefOwid = wzRefOwid;
	}

	
	@Length(min=0, max=200, message="fjmc长度必须介于 0 和 200 之间")
	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	
	public Integer getFjlx() {
		return fjlx;
	}

	public void setFjlx(Integer fjlx) {
		this.fjlx = fjlx;
	}
	
	@Length(min=0, max=200, message="fjlj长度必须介于 0 和 200 之间")
	public String getFjlj() {
		return fjlj;
	}

	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}


	
}