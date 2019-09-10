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
public class BckjBizDacx extends DataWithExpEntity<BckjBizDacx> {
	
	private static final long serialVersionUID = 1L;

	private String xsxh;		// xsxh
	private String xsxm;		// xsxm
	private String sfzh;		// sfzh
	private Integer xb;		// xb
	private Date bysj;		// bysj
	private String szxy;		// szxy
	private String szbj;		// szbj
	private String dwmc;		// dwmc
	private String bdzDwmc;		// bdz_dwmc
	private String dazjDwmc;		// dazj_dwmc
	private String dazjDwdz;		// dazj_dwdz
	private String bdzBh;		// bdz_bh
	private String memo;		// memo
	
	public BckjBizDacx() {
		super();
	}


	
	@Length(min=0, max=20, message="xsxh长度必须介于 0 和 20 之间")
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	@Length(min=0, max=36, message="xsxm长度必须介于 0 和 36 之间")
	public String getXsxm() {
		return xsxm;
	}

	public void setXsxm(String xsxm) {
		this.xsxm = xsxm;
	}
	
	@Length(min=0, max=36, message="sfzh长度必须介于 0 和 36 之间")
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	public Integer getXb() {
		return xb;
	}

	public void setXb(Integer xb) {
		this.xb = xb;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBysj() {
		return bysj;
	}

	public void setBysj(Date bysj) {
		this.bysj = bysj;
	}
	
	@Length(min=0, max=100, message="szxy长度必须介于 0 和 100 之间")
	public String getSzxy() {
		return szxy;
	}

	public void setSzxy(String szxy) {
		this.szxy = szxy;
	}
	
	@Length(min=0, max=100, message="szbj长度必须介于 0 和 100 之间")
	public String getSzbj() {
		return szbj;
	}

	public void setSzbj(String szbj) {
		this.szbj = szbj;
	}
	
	@Length(min=0, max=100, message="dwmc长度必须介于 0 和 100 之间")
	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	
	@Length(min=0, max=100, message="bdz_dwmc长度必须介于 0 和 100 之间")
	public String getBdzDwmc() {
		return bdzDwmc;
	}

	public void setBdzDwmc(String bdzDwmc) {
		this.bdzDwmc = bdzDwmc;
	}
	
	@Length(min=0, max=100, message="dazj_dwmc长度必须介于 0 和 100 之间")
	public String getDazjDwmc() {
		return dazjDwmc;
	}

	public void setDazjDwmc(String dazjDwmc) {
		this.dazjDwmc = dazjDwmc;
	}
	
	@Length(min=0, max=200, message="dazj_dwdz长度必须介于 0 和 200 之间")
	public String getDazjDwdz() {
		return dazjDwdz;
	}

	public void setDazjDwdz(String dazjDwdz) {
		this.dazjDwdz = dazjDwdz;
	}
	
	@Length(min=0, max=50, message="bdz_bh长度必须介于 0 和 50 之间")
	public String getBdzBh() {
		return bdzBh;
	}

	public void setBdzBh(String bdzBh) {
		this.bdzBh = bdzBh;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	
}