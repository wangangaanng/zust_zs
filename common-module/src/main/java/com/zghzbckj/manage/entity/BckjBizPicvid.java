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
public class BckjBizPicvid extends DataWithExpEntity<BckjBizPicvid> {
	
	private static final long serialVersionUID = 1L;
	private Integer lx;		// 0：图片 1视频
	private Integer zszd;		// zszd
	private String lmbh;		// lmbh
	private String bt;		// bt
	private String xsbt;		// xsbt
	private String url;		// url
	private Integer djs;		// djs
	private String memo;		// memo
	private String tzlj;		// tzlj
	
	public BckjBizPicvid() {
		super();
	}



	
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}
	
	public Integer getZszd() {
		return zszd;
	}

	public void setZszd(Integer zszd) {
		this.zszd = zszd;
	}
	
	@Length(min=0, max=100, message="lmbh长度必须介于 0 和 100 之间")
	public String getLmbh() {
		return lmbh;
	}

	public void setLmbh(String lmbh) {
		this.lmbh = lmbh;
	}
	
	@Length(min=0, max=200, message="bt长度必须介于 0 和 200 之间")
	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}
	
	@Length(min=0, max=200, message="xsbt长度必须介于 0 和 200 之间")
	public String getXsbt() {
		return xsbt;
	}

	public void setXsbt(String xsbt) {
		this.xsbt = xsbt;
	}
	
	@Length(min=0, max=200, message="url长度必须介于 0 和 200 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getDjs() {
		return djs;
	}

	public void setDjs(Integer djs) {
		this.djs = djs;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=200, message="tzlj长度必须介于 0 和 200 之间")
	public String getTzlj() {
		return tzlj;
	}

	public void setTzlj(String tzlj) {
		this.tzlj = tzlj;
	}
	

	
}