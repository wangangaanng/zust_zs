/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithIntegerExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjDicMenu extends DataWithIntegerExpEntity<BckjDicMenu> {
	
	private static final long serialVersionUID = 1L;
	private Integer fid;		// fid
	private String path;		// path
	private Integer cc;		// cc
	private Double px;		// px
	private Integer type;		// type
	private String code;		// code
	private String name;		// name
	private String alias;		// alias
	private String content;		// content
	private String attachFile;		// attach_file
	private Integer bxlx;		// bxlx
	private Integer sjhqdx;		// sjhqdx
	private String tzlj;		// tzlj
	private String memo;		// memo
	
	public BckjDicMenu() {
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
	
	public Double getPx() {
		return px;
	}

	public void setPx(Double px) {
		this.px = px;
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
	
	@Length(min=0, max=64, message="name长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=360, message="alias长度必须介于 0 和 360 之间")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
	
	public Integer getBxlx() {
		return bxlx;
	}

	public void setBxlx(Integer bxlx) {
		this.bxlx = bxlx;
	}
	
	public Integer getSjhqdx() {
		return sjhqdx;
	}

	public void setSjhqdx(Integer sjhqdx) {
		this.sjhqdx = sjhqdx;
	}
	
	@Length(min=0, max=200, message="tzlj长度必须介于 0 和 200 之间")
	public String getTzlj() {
		return tzlj;
	}

	public void setTzlj(String tzlj) {
		this.tzlj = tzlj;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}