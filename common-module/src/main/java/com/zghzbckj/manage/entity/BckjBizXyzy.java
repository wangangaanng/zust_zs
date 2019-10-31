/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithLongExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizXyzy extends DataWithLongExpEntity<BckjBizXyzy> {
	
	private static final long serialVersionUID = 1L;
	private Long parentId;		// parent_id
	private String path;		// path
	private String bh;		// bh
	private String mz;		// mz
	private String jj;		// jj
	private String tp;		// tp
	private String articleUrl;		// article_url

	
	public BckjBizXyzy() {
		super();
	}



	

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=36, message="path长度必须介于 0 和 36 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=36, message="bh长度必须介于 0 和 36 之间")
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}
	
	@Length(min=0, max=20, message="mz长度必须介于 0 和 20 之间")
	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}
	
	@Length(min=0, max=1000, message="jj长度必须介于 0 和 1000 之间")
	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}
	
	@Length(min=0, max=200, message="tp长度必须介于 0 和 200 之间")
	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}
	
	@Length(min=0, max=200, message="article_url长度必须介于 0 和 200 之间")
	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}
	
}