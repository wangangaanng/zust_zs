/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-10-21
 */
public class BckjBizCjxx extends DataWithExpEntity<BckjBizCjxx> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;
	private Integer lx;		// 0会考；1选考；2；综合测评
	private String kmbh;		// kmbh
	private String kmmc;		// kmbh
	private String kmsj;		// kmsj
	private String kmdj;		// kmdj
	private Double kmcj;		// kmcj
	private Integer xssx;		// xssx
	public BckjBizCjxx() {
		super();
	}


	
	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}
	
	@Length(min=0, max=20, message="kmbh长度必须介于 0 和 20 之间")
	public String getKmbh() {
		return kmbh;
	}

	public void setKmbh(String kmbh) {
		this.kmbh = kmbh;
	}
	
	@Length(min=0, max=20, message="kmsj长度必须介于 0 和 20 之间")
	public String getKmsj() {
		return kmsj;
	}

	public void setKmsj(String kmsj) {
		this.kmsj = kmsj;
	}
	
	@Length(min=0, max=100, message="kmdj长度必须介于 0 和 100 之间")
	public String getKmdj() {
		return kmdj;
	}

	public void setKmdj(String kmdj) {
		this.kmdj = kmdj;
	}
	
	public Double getKmcj() {
		return kmcj;
	}

	public void setKmcj(Double kmcj) {
		this.kmcj = kmcj;
	}
	
	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}


	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}

	public String getKmmc() {
		return kmmc;
	}

	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}
}