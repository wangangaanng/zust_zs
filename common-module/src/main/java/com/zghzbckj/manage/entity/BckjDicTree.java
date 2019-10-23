/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithLongExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 图文Entity
 * @author cg
 * @version 2019-03-15
 */
public class BckjDicTree extends DataWithLongExpEntity<BckjDicTree> {
	
	private static final long serialVersionUID = 1L;
	private Integer fid;		// fid
	private String path;		// path
	private Integer cc;		// cc
	private Double px;		// px
	private Integer dicType;		// dic_type
	private String dicCode;		// dic_code
	private String dicName;		// dic_name
	private String alias;		// alias
	private String dicDesc;		// dic_desc
	private String attachFile;		// attach_file
	private String memo;		// memo
	private List<BckjDicTree> subChidren;
	
	public BckjDicTree() {
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
	
	public Integer getDicType() {
		return dicType;
	}

	public void setDicType(Integer dicType) {
		this.dicType = dicType;
	}
	
	@Length(min=0, max=100, message="dic_code长度必须介于 0 和 100 之间")
	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	
	@Length(min=0, max=360, message="dic_name长度必须介于 0 和 360 之间")
	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	
	@Length(min=0, max=360, message="alias长度必须介于 0 和 360 之间")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@Length(min=0, max=2400, message="dic_desc长度必须介于 0 和 2400 之间")
	public String getDicDesc() {
		return dicDesc;
	}

	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}
	
	@Length(min=0, max=240, message="attach_file长度必须介于 0 和 240 之间")
	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	
	@Length(min=0, max=240, message="memo长度必须介于 0 和 240 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public List<BckjDicTree> getSubChidren() {
		return subChidren;
	}

	public void setSubChidren(List<BckjDicTree> subChidren) {
		this.subChidren = subChidren;
	}
}