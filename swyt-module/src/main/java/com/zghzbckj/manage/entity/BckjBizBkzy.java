/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizBkzy extends DataWithExpEntity<BckjBizBkzy> {
	
	private static final long serialVersionUID = 1L;

	private Integer fid;		// fid
	private String path;		// path
	private Integer cc;		// cc
	private BigDecimal px;		// px
	private String xxbh;		// xxbh
	private String bknd;		// bknd
	private Integer type;		// type
	private String code;		// code
	private String name;		// name
	private String content;		// content
	private String attachFile;		// attach_file
	private String xgyy;		// xgyy
	private String memo;		// memo

	
	public BckjBizBkzy() {
		super();
	}

	
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	@Length(min=0, max=360, message="path长度必须介于 0 和 360 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}
	
	public BigDecimal getPx() {
		return px;
	}

	public void setPx(BigDecimal px) {
		this.px = px;
	}
	
	@Length(min=0, max=20, message="xxbh长度必须介于 0 和 20 之间")
	public String getXxbh() {
		return xxbh;
	}

	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	
	@Length(min=0, max=4, message="bknd长度必须介于 0 和 4 之间")
	public String getBknd() {
		return bknd;
	}

	public void setBknd(String bknd) {
		this.bknd = bknd;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="code长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=360, message="name长度必须介于 0 和 360 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2400, message="content长度必须介于 0 和 2400 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=240, message="attach_file长度必须介于 0 和 240 之间")
	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	
	@Length(min=0, max=20, message="xgyy长度必须介于 0 和 20 之间")
	public String getXgyy() {
		return xgyy;
	}

	public void setXgyy(String xgyy) {
		this.xgyy = xgyy;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}