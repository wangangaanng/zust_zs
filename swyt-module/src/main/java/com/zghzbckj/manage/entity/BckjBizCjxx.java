/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * ccEntity
 * @author cc
 * @version 2019-10-21
 */
public class BckjBizCjxx extends DataWithExpEntity<BckjBizCjxx> {
	
	private static final long serialVersionUID = 1L;
	private Integer lx;		// 0会考；1选考；2；综合测评
	private String kmbh;		// kmbh
	private String kmsj;		// kmsj
	private String kmdj;		// kmdj
	private Double kmcj;		// kmcj
	private Integer xssx;		// xssx
	private String exp1;		// exp1
	private String exp2;		// exp2
	private String exp3;		// exp3
	private String exp4;		// exp4
	private String exp5;		// exp5
	private String exp6;		// exp6
	private String exp7;		// exp7
	private String exp8;		// exp8
	private String exp9;		// exp9
	private String exp10;		// exp10
	private String creator;		// creator
	private Date createtime;		// createtime
	private String creatorName;		// creator_name
	private Date lastupdate;		// lastupdate
	private String updator;		// updator
	private String updatorName;		// updator_name
	private String ver;		// ver
	private Date vertime;		// vertime
	private String deptId;		// dept_id
	private String deptPath;		// dept_path
	private String delflg;		// delflg
	private String state;		// state
	
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

	
}