/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjDicMenuApply extends DataWithExpEntity<BckjDicMenuApply> {
	
	private static final long serialVersionUID = 1L;
	private Integer menuRefOwid;		// menu_ref_owid
	private String wzbh;		// wzbh
	private Integer px;		// px
	private Integer type;		// type
	private String name;		// name
	private String memo;		// memo
	private String ggt;		// ggt
	private String jsy;		// jsy
	
	public BckjDicMenuApply() {
		super();
	}


	public Integer getMenuRefOwid() {
		return menuRefOwid;
	}

	public void setMenuRefOwid(Integer menuRefOwid) {
		this.menuRefOwid = menuRefOwid;
	}

	
	@Length(min=0, max=10, message="wzbh长度必须介于 0 和 10 之间")
	public String getWzbh() {
		return wzbh;
	}

	public void setWzbh(String wzbh) {
		this.wzbh = wzbh;
	}
	
	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="name长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=200, message="ggt长度必须介于 0 和 200 之间")
	public String getGgt() {
		return ggt;
	}

	public void setGgt(String ggt) {
		this.ggt = ggt;
	}
	
	@Length(min=0, max=200, message="jsy长度必须介于 0 和 200 之间")
	public String getJsy() {
		return jsy;
	}

	public void setJsy(String jsy) {
		this.jsy = jsy;
	}
	
}