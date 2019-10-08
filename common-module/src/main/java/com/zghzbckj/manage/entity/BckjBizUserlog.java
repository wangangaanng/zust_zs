/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * ccEntity
 * @author cc
 * @version 2019-10-08
 */
public class BckjBizUserlog extends DataWithExpEntity<BckjBizUserlog> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String xsxh;		// xsxh
	private String name;		// name
	private Date doTime;		// do_time
	private String doContent;		// do_content
	private Integer yhlx;		// yhlx
	
	public BckjBizUserlog() {
		super();
	}

	
	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	@Length(min=0, max=30, message="xsxh长度必须介于 0 和 30 之间")
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	@Length(min=0, max=30, message="name长度必须介于 0 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDoTime() {
		return doTime;
	}

	public void setDoTime(Date doTime) {
		this.doTime = doTime;
	}
	
	@Length(min=0, max=2000, message="do_content长度必须介于 0 和 2000 之间")
	public String getDoContent() {
		return doContent;
	}

	public void setDoContent(String doContent) {
		this.doContent = doContent;
	}
	
	public Integer getYhlx() {
		return yhlx;
	}

	public void setYhlx(Integer yhlx) {
		this.yhlx = yhlx;
	}

	
}