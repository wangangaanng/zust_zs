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
public class BckjBizJtcyxx extends DataWithExpEntity<BckjBizJtcyxx> {
	
	private static final long serialVersionUID = 1L;

	private String yhRefOwid;		// yh_ref_owid
	private Integer cylb;		// cylb
	private String xm;		// xm
	private Integer xb;		// xb
	private String whcd;		// whcd
	private String gzdw;		// gzdw
	private String gzzw;		// gzzw
	private String lxsj;		// lxsj
	private String dwdh;		// dwdh
	private Integer xssx;		// xssx

	
	public BckjBizJtcyxx() {
		super();
	}




	
	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	public Integer getCylb() {
		return cylb;
	}

	public void setCylb(Integer cylb) {
		this.cylb = cylb;
	}
	
	@Length(min=0, max=20, message="xm长度必须介于 0 和 20 之间")
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	public Integer getXb() {
		return xb;
	}

	public void setXb(Integer xb) {
		this.xb = xb;
	}
	
	@Length(min=0, max=20, message="whcd长度必须介于 0 和 20 之间")
	public String getWhcd() {
		return whcd;
	}

	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	
	@Length(min=0, max=200, message="gzdw长度必须介于 0 和 200 之间")
	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}
	
	@Length(min=0, max=50, message="gzzw长度必须介于 0 和 50 之间")
	public String getGzzw() {
		return gzzw;
	}

	public void setGzzw(String gzzw) {
		this.gzzw = gzzw;
	}
	
	@Length(min=0, max=20, message="lxsj长度必须介于 0 和 20 之间")
	public String getLxsj() {
		return lxsj;
	}

	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	
	@Length(min=0, max=20, message="dwdh长度必须介于 0 和 20 之间")
	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	
	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	
}