/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * ccEntity
 * @author cc
 * @version 2019-04-13
 */
public class AppBizArticle extends DataWithExpEntity<AppBizArticle> {
	
	private static final long serialVersionUID = 1L;
	private String tpjj;		// tpjj
	private String jjnr;		// jjnr
	private String path;		// path
	private String pathName;		// path_name
	private Integer lmbh;		// lmbh
	private String lmbt;		// lmbt
	private Integer lmbh2;		// lmbh2
	private String lmbt2;		// lmbt2
	private String fbr;		// fbr
	private Date fbsj;		// fbsj
	private Date sxsj;		// sxsj
	private String wzbt;		// wzbt
	private String wzly;		// wzly
	private String wznr;		// wznr
	private Integer wzzt;		// wzzt
	private Integer ydcs;		// ydcs
	private String htmlPath;		// html_path
	private String attaMm;		// atta_mm
	private String memo;		// memo
	private Integer sxh;
	private Integer istop;
	private List<AppBizAtt> fileList;

	
	@Length(min=0, max=100, message="tpjj长度必须介于 0 和 100 之间")
	public String getTpjj() {
		return tpjj;
	}

	public void setTpjj(String tpjj) {
		this.tpjj = tpjj;
	}
	
	@Length(min=0, max=1000, message="jjnr长度必须介于 0 和 1000 之间")
	public String getJjnr() {
		return jjnr;
	}

	public void setJjnr(String jjnr) {
		this.jjnr = jjnr;
	}
	
	@Length(min=0, max=36, message="path长度必须介于 0 和 36 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=200, message="path_name长度必须介于 0 和 200 之间")
	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
	public Integer getLmbh() {
		return lmbh;
	}

	public void setLmbh(Integer lmbh) {
		this.lmbh = lmbh;
	}
	
	@Length(min=0, max=200, message="lmbt长度必须介于 0 和 200 之间")
	public String getLmbt() {
		return lmbt;
	}

	public void setLmbt(String lmbt) {
		this.lmbt = lmbt;
	}
	
	public Integer getLmbh2() {
		return lmbh2;
	}

	public void setLmbh2(Integer lmbh2) {
		this.lmbh2 = lmbh2;
	}
	
	@Length(min=0, max=200, message="lmbt2长度必须介于 0 和 200 之间")
	public String getLmbt2() {
		return lmbt2;
	}

	public void setLmbt2(String lmbt2) {
		this.lmbt2 = lmbt2;
	}
	
	@Length(min=0, max=30, message="fbr长度必须介于 0 和 30 之间")
	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFbsj() {
		return fbsj;
	}

	public void setFbsj(Date fbsj) {
		this.fbsj = fbsj;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSxsj() {
		return sxsj;
	}

	public void setSxsj(Date sxsj) {
		this.sxsj = sxsj;
	}
	
	@Length(min=0, max=200, message="wzbt长度必须介于 0 和 200 之间")
	public String getWzbt() {
		return wzbt;
	}

	public void setWzbt(String wzbt) {
		this.wzbt = wzbt;
	}
	
	@Length(min=0, max=200, message="wzly长度必须介于 0 和 200 之间")
	public String getWzly() {
		return wzly;
	}

	public void setWzly(String wzly) {
		this.wzly = wzly;
	}
	
	public String getWznr() {
		return wznr;
	}

	public void setWznr(String wznr) {
		this.wznr = wznr;
	}
	
	public Integer getWzzt() {
		return wzzt;
	}

	public void setWzzt(Integer wzzt) {
		this.wzzt = wzzt;
	}
	
	public Integer getYdcs() {
		return ydcs;
	}

	public void setYdcs(Integer ydcs) {
		this.ydcs = ydcs;
	}
	
	@Length(min=0, max=200, message="html_path长度必须介于 0 和 200 之间")
	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	
	@Length(min=0, max=200, message="atta_mm长度必须介于 0 和 200 之间")
	public String getAttaMm() {
		return attaMm;
	}

	public void setAttaMm(String attaMm) {
		this.attaMm = attaMm;
	}
	
	@Length(min=0, max=240, message="memo长度必须介于 0 和 240 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<AppBizAtt> getFileList() {
		return fileList;
	}

	public void setFileList(List<AppBizAtt> fileList) {
		this.fileList = fileList;
	}

	public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}
}