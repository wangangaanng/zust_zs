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
public class BckjBizCjxx extends DataWithExpEntity<BckjBizCjxx> {
	
	private static final long serialVersionUID = 1L;

	private String yhRefOwid;		// yh_ref_owid
	private String kmbh;		// kmbh
	private String kmsj;		// kmsj
	private String kmdj;		// kmdj
	private Custom kmcj;		// kmcj
	private Integer xssx;		// xssx

	
	public BckjBizCjxx() {
		super();
	}

	public BckjBizCjxx(String id){
		super(id);
	}

	
	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
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
	
	public Custom getKmcj() {
		return kmcj;
	}

	public void setKmcj(Custom kmcj) {
		this.kmcj = kmcj;
	}
	
	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	
}