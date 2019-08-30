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
public class AppBizPicvid extends DataWithExpEntity<AppBizPicvid> {
	
	private static final long serialVersionUID = 1L;
	private Integer lx;		// 0
	private Integer lmbh;		// lmbh
	private String lmbt;		// lmbt
	private Integer lmbh2;		// lmbh2
	private String lmbt2;		// lmbt
	private String bt;		// bt
	private String url;		// url
	private Integer djs;		// djs
	private String memo;		// memo
	private String imagePath;		// memo
	public AppBizPicvid() {
		super();
	}
	
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}
	
	public Integer getLmbh() {
		return lmbh;
	}

	public void setLmbh(Integer lmbh) {
		this.lmbh = lmbh;
	}
	
	@Length(min=0, max=200, message="lmbt长度必须介于 0 和 200 之间")
	public String getLmbt() {
		return lmbt;
	}

	public void setLmbt(String lmbt) {
		this.lmbt = lmbt;
	}
	
	public Integer getLmbh2() {
		return lmbh2;
	}

	public void setLmbh2(Integer lmbh2) {
		this.lmbh2 = lmbh2;
	}
	
	@Length(min=0, max=200, message="lmbt2长度必须介于 0 和 200 之间")
	public String getLmbt2() {
		return lmbt2;
	}

	public void setLmbt2(String lmbt2) {
		this.lmbt2 = lmbt2;
	}
	
	@Length(min=0, max=200, message="bt长度必须介于 0 和 200 之间")
	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
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
	
	@Length(min=0, max=240, message="memo长度必须介于 0 和 240 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}