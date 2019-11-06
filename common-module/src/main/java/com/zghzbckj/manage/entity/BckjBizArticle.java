/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class  BckjBizArticle extends DataWithExpEntity<BckjBizArticle> {
	
	private static final long serialVersionUID = 1L;
	private String tpjj;		// tpjj
	private String jjnr;		// jjnr
	private String qtbhname;		// jjnr
	private String lmbh;		// lmbh
	private String fbr;		// fbr
	private Date fbsj;		// fbsj
	private Date sxsj;		// sxsj
	private String wzbt;		// wzbt
	private String wzly;		// wzly
	private String wznr;		// wznr
	private Integer wzzt;		// wzzt
	private Integer ydcs;		// ydcs
	private String htmlPath;		// html_path
	private String nf;		// html_path
	private String jznf;		// html_path
	private String qtbh;		// html_path
	private String attaMm;		// atta_mm
	private Integer istop;		// istop
	private Integer sxh;		// sxh
	private String gjz;		// gjz
	private String memo;		// memo
	private String xgfj;
    private List<Map> fileList;
    private Boolean readOnly;
	
	public BckjBizArticle() {
		super();
	}

	
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
	
	@Length(min=0, max=100, message="lmbh长度必须介于 0 和 100 之间")
	public String getLmbh() {
		return lmbh;
	}

	public void setLmbh(String lmbh) {
		this.lmbh = lmbh;
	}
	
	@Length(min=0, max=30, message="fbr长度必须介于 0 和 30 之间")
	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
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
		if(null==ydcs){
			return 0;
		}
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
	
	public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}
	
	public Integer getSxh() {
		return sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}
	
	@Length(min=0, max=100, message="gjz长度必须介于 0 和 100 之间")
	public String getGjz() {
		return gjz;
	}

	public void setGjz(String gjz) {
		this.gjz = gjz;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


    public List<Map> getFileList() {
        return fileList;
    }

    public void setFileList(List<Map> fileList) {
        this.fileList = fileList;
    }

	public String getXgfj() {
		return xgfj;
	}

	public void setXgfj(String xgfj) {
		this.xgfj = xgfj;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getQtbh() {
		return qtbh;
	}

	public void setQtbh(String qtbh) {
		this.qtbh = qtbh;
	}

	public String getQtbhname() {
		return qtbhname;
	}

	public void setQtbhname(String qtbhname) {
		this.qtbhname = qtbhname;
	}

	public String getJznf() {
		return jznf;
	}

	public void setJznf(String jznf) {
		this.jznf = jznf;
	}
}