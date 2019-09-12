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
public class BckjBizXsyy extends DataWithExpEntity<BckjBizXsyy> {
	
	private static final long serialVersionUID = 1L;
	private String zjzxRefOwid;		// zjzx_ref_owid
	private String yhid;		// yhid
	private String xsxh;		// xsxh
	private String xsxm;		// xsxm
	private Date yysj;			// yysj
	private String yybz;		// yybz
	private String memo;		// memo
	
	public BckjBizXsyy() {
		super();
	}




	
	@Length(min=0, max=64, message="zjzx_ref_owid长度必须介于 0 和 64 之间")
	public String getZjzxRefOwid() {
		return zjzxRefOwid;
	}

	public void setZjzxRefOwid(String zjzxRefOwid) {
		this.zjzxRefOwid = zjzxRefOwid;
	}
	
	@Length(min=0, max=64, message="yhid长度必须介于 0 和 64 之间")
	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	
	@Length(min=0, max=20, message="xsxh长度必须介于 0 和 20 之间")
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	@Length(min=0, max=20, message="xsxm长度必须介于 0 和 20 之间")
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getYysj() {
		return yysj;
	}

	public void setYysj(Date yysj) {
		this.yysj = yysj;
	}
	
	@Length(min=0, max=300, message="yybz长度必须介于 0 和 300 之间")
	public String getYybz() {
		return yybz;
	}

	public void setYybz(String yybz) {
		this.yybz = yybz;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	
}