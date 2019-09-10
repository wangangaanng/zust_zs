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
public class BckjBizBkyx extends DataWithExpEntity<BckjBizBkyx> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String bkxy;		// bkxy
	private String bkzy;		// bkzy
	private String bkly;		// bkly
	private String lxdh;		// lxdh
	private Date yxsj;		// yxsj
	private String memo;		// memo
	
	public BckjBizBkyx() {
		super();
	}

	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	@Length(min=0, max=20, message="bkxy长度必须介于 0 和 20 之间")
	public String getBkxy() {
		return bkxy;
	}

	public void setBkxy(String bkxy) {
		this.bkxy = bkxy;
	}
	
	@Length(min=0, max=20, message="bkzy长度必须介于 0 和 20 之间")
	public String getBkzy() {
		return bkzy;
	}

	public void setBkzy(String bkzy) {
		this.bkzy = bkzy;
	}
	
	@Length(min=0, max=100, message="bkly长度必须介于 0 和 100 之间")
	public String getBkly() {
		return bkly;
	}

	public void setBkly(String bkly) {
		this.bkly = bkly;
	}
	
	@Length(min=0, max=20, message="lxdh长度必须介于 0 和 20 之间")
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getYxsj() {
		return yxsj;
	}

	public void setYxsj(Date yxsj) {
		this.yxsj = yxsj;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	
}