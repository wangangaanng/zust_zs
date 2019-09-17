/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataEntity;
import org.hibernate.validator.constraints.Length;


/**
 * 单表生成Entity
 * @author D.chen.g
 * @version 2018-07-13
 */
public class SysWxconfig extends DataEntity<SysWxconfig> {
	
	private static final long serialVersionUID = 1L;
	private String weCode;		// we_code
	private String weName;		// we_name
	private String weDesc;		// we_desc
	private String weParent;		// we_parent
	private Integer weType;		// we_type
	private String weAppid;		// we_appid
	private String weSecrect;		// we_secrect
	private String weMacid;		// we_macid
	private String weKey;		// we_key
	private String weNotifyurl;		// we_notifyurl
	private String weWebNotifyurl;		// we_web_notifyurl
	private String weRedirecturl;		// we_redirecturl
	private String weCertPath;		// we_cert_path
	private Integer weDevelop;		// we_develop
	private String weDevelopAddr;		// we_develop_addr
	private String weServiceName;		// we_service_name
	private String language;		// language
	private String constactsSecrect;
	private String compAppId;
	private String compAppSecrect;
	private String compId;

	private String weGh;		// we_gh
	
	public SysWxconfig() {
		super();
	}


	@Length(min=0, max=64, message="we_code长度必须介于 0 和 64 之间")
	public String getWeCode() {
		return weCode;
	}

	public void setWeCode(String weCode) {
		this.weCode = weCode;
	}
	
	@Length(min=0, max=128, message="we_name长度必须介于 0 和 128 之间")
	public String getWeName() {
		return weName;
	}

	public void setWeName(String weName) {
		this.weName = weName;
	}
	
	@Length(min=0, max=640, message="we_desc长度必须介于 0 和 640 之间")
	public String getWeDesc() {
		return weDesc;
	}

	public void setWeDesc(String weDesc) {
		this.weDesc = weDesc;
	}
	
	@Length(min=0, max=64, message="we_parent长度必须介于 0 和 64 之间")
	public String getWeParent() {
		return weParent;
	}

	public void setWeParent(String weParent) {
		this.weParent = weParent;
	}

	
	@Length(min=0, max=128, message="we_appid长度必须介于 0 和 128 之间")
	public String getWeAppid() {
		return weAppid;
	}

	public void setWeAppid(String weAppid) {
		this.weAppid = weAppid;
	}
	
	@Length(min=0, max=128, message="we_secrect长度必须介于 0 和 128 之间")
	public String getWeSecrect() {
		return weSecrect;
	}

	public void setWeSecrect(String weSecrect) {
		this.weSecrect = weSecrect;
	}
	
	@Length(min=0, max=128, message="we_macid长度必须介于 0 和 128 之间")
	public String getWeMacid() {
		return weMacid;
	}

	public void setWeMacid(String weMacid) {
		this.weMacid = weMacid;
	}
	
	@Length(min=0, max=128, message="we_key长度必须介于 0 和 128 之间")
	public String getWeKey() {
		return weKey;
	}

	public void setWeKey(String weKey) {
		this.weKey = weKey;
	}
	
	@Length(min=0, max=640, message="we_notifyurl长度必须介于 0 和 640 之间")
	public String getWeNotifyurl() {
		return weNotifyurl;
	}

	public void setWeNotifyurl(String weNotifyurl) {
		this.weNotifyurl = weNotifyurl;
	}
	
	@Length(min=0, max=640, message="we_web_notifyurl长度必须介于 0 和 640 之间")
	public String getWeWebNotifyurl() {
		return weWebNotifyurl;
	}

	public void setWeWebNotifyurl(String weWebNotifyurl) {
		this.weWebNotifyurl = weWebNotifyurl;
	}
	
	@Length(min=0, max=640, message="we_redirecturl长度必须介于 0 和 640 之间")
	public String getWeRedirecturl() {
		return weRedirecturl;
	}

	public void setWeRedirecturl(String weRedirecturl) {
		this.weRedirecturl = weRedirecturl;
	}
	
	@Length(min=0, max=640, message="we_cert_path长度必须介于 0 和 640 之间")
	public String getWeCertPath() {
		return weCertPath;
	}

	public void setWeCertPath(String weCertPath) {
		this.weCertPath = weCertPath;
	}
	

	
	@Length(min=0, max=640, message="we_develop_addr长度必须介于 0 和 640 之间")
	public String getWeDevelopAddr() {
		return weDevelopAddr;
	}

	public void setWeDevelopAddr(String weDevelopAddr) {
		this.weDevelopAddr = weDevelopAddr;
	}
	
	@Length(min=0, max=64, message="we_service_name长度必须介于 0 和 64 之间")
	public String getWeServiceName() {
		return weServiceName;
	}

	public void setWeServiceName(String weServiceName) {
		this.weServiceName = weServiceName;
	}
	
	@Length(min=0, max=16, message="language长度必须介于 0 和 16 之间")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	

	
	@Length(min=0, max=100, message="we_gh长度必须介于 0 和 100 之间")
	public String getWeGh() {
		return weGh;
	}

	public void setWeGh(String weGh) {
		this.weGh = weGh;
	}

	public Integer getWeType() {
		return weType;
	}

	public void setWeType(Integer weType) {
		this.weType = weType;
	}

	public Integer getWeDevelop() {
		return weDevelop;
	}

	public void setWeDevelop(Integer weDevelop) {
		this.weDevelop = weDevelop;
	}

	public String getConstactsSecrect() {
		return constactsSecrect;
	}

	public void setConstactsSecrect(String constactsSecrect) {
		this.constactsSecrect = constactsSecrect;
	}

	public String getCompAppSecrect() {
		return compAppSecrect;
	}

	public void setCompAppSecrect(String compAppSecrect) {
		this.compAppSecrect = compAppSecrect;
	}

	public String getCompAppId() {
		return compAppId;
	}

	public void setCompAppId(String compAppId) {
		this.compAppId = compAppId;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}
}