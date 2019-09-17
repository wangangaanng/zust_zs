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
public class BckjBizYhxx extends DataWithExpEntity<BckjBizYhxx> {
	
	private static final long serialVersionUID = 1L;

	private String sjh;		// sjh
	private Integer yhlx;   //yhlx
	private String unionid;		// unionid
	private String yhtx;		// yhtx
	private String sfz;		// sfz
	private String xm;		// xm
	private Integer xb;		// xb
	private String mz;		// mz
	private Date csrq;		// csrq
	private String yx;		// yx
	private Date zcsj;		// zcsj
	private Date fssj;		// fssj
	private String yzm;		// yzm
	private String prov;		// prov
	private String city;		// city
	private String area;		// area
	private String dlzh;		// dlzh
	private Date dlzhsj;		// dlzhsj
	private String yhdlzh;
	private String yhdlmm;


	public String getYhdlzh() {
		return yhdlzh;
	}

	public void setYhdlzh(String yhdlzh) {
		this.yhdlzh = yhdlzh;
	}

	public String getYhdlmm() {
		return yhdlmm;
	}

	public void setYhdlmm(String yhdlmm) {
		this.yhdlmm = yhdlmm;
	}

	public Integer getYhlx() {
		return yhlx;
	}

	public void setYhlx(Integer yhlx) {
		this.yhlx = yhlx;
	}
	
	public BckjBizYhxx() {
		super();
	}

	
	@Length(min=0, max=20, message="sjh长度必须介于 0 和 20 之间")
	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}
	
	@Length(min=0, max=100, message="unionid长度必须介于 0 和 100 之间")
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	@Length(min=0, max=200, message="yhtx长度必须介于 0 和 200 之间")
	public String getYhtx() {
		return yhtx;
	}

	public void setYhtx(String yhtx) {
		this.yhtx = yhtx;
	}
	
	@Length(min=0, max=20, message="sfz长度必须介于 0 和 20 之间")
	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	
	@Length(min=0, max=50, message="xm长度必须介于 0 和 50 之间")
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
	
	@Length(min=0, max=200, message="mz长度必须介于 0 和 200 之间")
	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	
	@Length(min=0, max=200, message="yx长度必须介于 0 和 200 之间")
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZcsj() {
		return zcsj;
	}

	public void setZcsj(Date zcsj) {
		this.zcsj = zcsj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFssj() {
		return fssj;
	}

	public void setFssj(Date fssj) {
		this.fssj = fssj;
	}
	
	@Length(min=0, max=36, message="yzm长度必须介于 0 和 36 之间")
	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	
	@Length(min=0, max=20, message="prov长度必须介于 0 和 20 之间")
	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
	
	@Length(min=0, max=20, message="city长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=20, message="area长度必须介于 0 和 20 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=20, message="dlzh长度必须介于 0 和 20 之间")
	public String getDlzh() {
		return dlzh;
	}

	public void setDlzh(String dlzh) {
		this.dlzh = dlzh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDlzhsj() {
		return dlzhsj;
	}

	public void setDlzhsj(Date dlzhsj) {
		this.dlzhsj = dlzhsj;
	}

}