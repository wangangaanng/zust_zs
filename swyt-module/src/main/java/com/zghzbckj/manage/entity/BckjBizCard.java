/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * ccEntity
 * @author cc
 * @version 2019-11-05
 */
public class BckjBizCard extends DataWithExpEntity<BckjBizCard> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private String headImage;		// head_image
	private String name;		// name
	private Integer xb;		// xb
	private String folk;		// folk
	private Date birthday;		// birthday
	private String address;		// address
	private Date validterm;		// validterm
	private Integer isPass;		// is_pass
	private String number;		// number
	private Double faceValue;		// face_value
	private String faceImage;		// face_image
	private String source;		// source
	private String deviceid;		// deviceid
	private Date createtime;		// createtime
	private String creator;		// creator
	private String creatorName;		// creator_name
	private Date lastupdate;		// lastupdate
	private String updator;		// updator
	private String updatorName;		// updator_name
	private String ver;		// ver
	private Date vertime;		// vertime
	private String deptId;		// dept_id
	private String deptPath;		// dept_path
	private String delflg;		// 0：正常 -1：删除 -100模板另存
	private String state;		// -1 :作废 0：正常可修改  100：工作流完成 1-99是流程
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


	public BckjBizCard() {
		super();
	}

	
	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	@Length(min=0, max=50, message="name长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getXb() {
		return xb;
	}

	public void setXb(Integer xb) {
		this.xb = xb;
	}
	
	@Length(min=0, max=50, message="folk长度必须介于 0 和 50 之间")
	public String getFolk() {
		return folk;
	}

	public void setFolk(String folk) {
		this.folk = folk;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=100, message="address长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidterm() {
		return validterm;
	}

	public void setValidterm(Date validterm) {
		this.validterm = validterm;
	}
	
	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	
	@Length(min=0, max=36, message="number长度必须介于 0 和 36 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Double getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}
	
	public String getFaceImage() {
		return faceImage;
	}

	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}
	
	@Length(min=0, max=100, message="source长度必须介于 0 和 100 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=64, message="deviceid长度必须介于 0 和 64 之间")
	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	
}