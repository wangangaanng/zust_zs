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
public class BckjBizZjzx extends DataWithExpEntity<BckjBizZjzx> {
	
	private static final long serialVersionUID = 1L;

	private String yhid;		// yhid
	private String zjxm;		// zjxm
	private String zjtx;		// zjtx
	private String zjjj;		// zjjj
	private String zjlxfs;		// zjlxfs
	private String zjzw;		// zjzw
	private String zjbgs;		// zjbgs
	private String szxy;		// szxy
	private String szzy;		// szzy
	private String zjxx;		// zjxx
	private String memo;		// memo
	private String zxsj;		// zxsj
	private Integer zjsfkyy;		// zjsfkyy
	private String lmbh;		// lmbh

	
	public BckjBizZjzx() {
		super();
	}


	
	@Length(min=0, max=64, message="yhid长度必须介于 0 和 64 之间")
	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	
	@Length(min=0, max=20, message="zjxm长度必须介于 0 和 20 之间")
	public String getZjxm() {
		return zjxm;
	}

	public void setZjxm(String zjxm) {
		this.zjxm = zjxm;
	}
	
	@Length(min=0, max=200, message="zjtx长度必须介于 0 和 200 之间")
	public String getZjtx() {
		return zjtx;
	}

	public void setZjtx(String zjtx) {
		this.zjtx = zjtx;
	}
	
	@Length(min=0, max=1000, message="zjjj长度必须介于 0 和 1000 之间")
	public String getZjjj() {
		return zjjj;
	}

	public void setZjjj(String zjjj) {
		this.zjjj = zjjj;
	}
	
	@Length(min=0, max=64, message="zjlxfs长度必须介于 0 和 64 之间")
	public String getZjlxfs() {
		return zjlxfs;
	}

	public void setZjlxfs(String zjlxfs) {
		this.zjlxfs = zjlxfs;
	}
	
	@Length(min=0, max=64, message="zjzw长度必须介于 0 和 64 之间")
	public String getZjzw() {
		return zjzw;
	}

	public void setZjzw(String zjzw) {
		this.zjzw = zjzw;
	}
	
	@Length(min=0, max=100, message="zjbgs长度必须介于 0 和 100 之间")
	public String getZjbgs() {
		return zjbgs;
	}

	public void setZjbgs(String zjbgs) {
		this.zjbgs = zjbgs;
	}
	
	@Length(min=0, max=100, message="szxy长度必须介于 0 和 100 之间")
	public String getSzxy() {
		return szxy;
	}

	public void setSzxy(String szxy) {
		this.szxy = szxy;
	}
	
	@Length(min=0, max=100, message="szzy长度必须介于 0 和 100 之间")
	public String getSzzy() {
		return szzy;
	}

	public void setSzzy(String szzy) {
		this.szzy = szzy;
	}
	
	public String getZjxx() {
		return zjxx;
	}

	public void setZjxx(String zjxx) {
		this.zjxx = zjxx;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=500, message="zxsj长度必须介于 0 和 500 之间")
	public String getZxsj() {
		return zxsj;
	}

	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	
	public Integer getZjsfkyy() {
		return zjsfkyy;
	}

	public void setZjsfkyy(Integer zjsfkyy) {
		this.zjsfkyy = zjsfkyy;
	}
	
	@Length(min=0, max=200, message="lmbh长度必须介于 0 和 200 之间")
	public String getLmbh() {
		return lmbh;
	}

	public void setLmbh(String lmbh) {
		this.lmbh = lmbh;
	}
	

	
}