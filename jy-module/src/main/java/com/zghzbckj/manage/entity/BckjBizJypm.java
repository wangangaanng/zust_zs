/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizJypm extends DataWithExpEntity<BckjBizJypm> {
	
	private static final long serialVersionUID = 1L;

	private String pmnf;		// pmnf
	private String pmyf;		// pmyf
	private String szxy;		// szxy
	private String pmzy;		// pmzy
	private String pmbj;		// pmbj
	private Integer pmbyrs;		// pmbyrs
	private Integer pmyprs;		// pmyprs
	private Integer pmqyrs;		// pmqyrs
	private BigDecimal pmqyl;		// pmqyl
	private BigDecimal pmjyl;		// pmjyl
	private Integer pmmc;		// pmmc
	private String memo;		// memo

	
	public BckjBizJypm() {
		super();
	}

	
	@Length(min=0, max=4, message="pmnf长度必须介于 0 和 4 之间")
	public String getPmnf() {
		return pmnf;
	}

	public void setPmnf(String pmnf) {
		this.pmnf = pmnf;
	}
	
	@Length(min=0, max=2, message="pmyf长度必须介于 0 和 2 之间")
	public String getPmyf() {
		return pmyf;
	}

	public void setPmyf(String pmyf) {
		this.pmyf = pmyf;
	}
	
	@Length(min=0, max=100, message="szxy长度必须介于 0 和 100 之间")
	public String getSzxy() {
		return szxy;
	}

	public void setSzxy(String szxy) {
		this.szxy = szxy;
	}
	
	@Length(min=0, max=20, message="pmzy长度必须介于 0 和 20 之间")
	public String getPmzy() {
		return pmzy;
	}

	public void setPmzy(String pmzy) {
		this.pmzy = pmzy;
	}
	
	@Length(min=0, max=20, message="pmbj长度必须介于 0 和 20 之间")
	public String getPmbj() {
		return pmbj;
	}

	public void setPmbj(String pmbj) {
		this.pmbj = pmbj;
	}
	
	public Integer getPmbyrs() {
		return pmbyrs;
	}

	public void setPmbyrs(Integer pmbyrs) {
		this.pmbyrs = pmbyrs;
	}
	
	public Integer getPmyprs() {
		return pmyprs;
	}

	public void setPmyprs(Integer pmyprs) {
		this.pmyprs = pmyprs;
	}
	
	public Integer getPmqyrs() {
		return pmqyrs;
	}

	public void setPmqyrs(Integer pmqyrs) {
		this.pmqyrs = pmqyrs;
	}
	
	public BigDecimal getPmqyl() {
		return pmqyl;
	}

	public void setPmqyl(BigDecimal pmqyl) {
		this.pmqyl = pmqyl;
	}
	
	public BigDecimal getPmjyl() {
		return pmjyl;
	}

	public void setPmjyl(BigDecimal pmjyl) {
		this.pmjyl = pmjyl;
	}
	
	public Integer getPmmc() {
		return pmmc;
	}

	public void setPmmc(Integer pmmc) {
		this.pmmc = pmmc;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	
}