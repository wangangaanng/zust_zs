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
public class BckjBizArticle extends DataWithExpEntity<BckjBizArticle> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private String tpjj;		// tpjj
	private String jjnr;		// jjnr
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
	private String attaMm;		// atta_mm
	private Integer istop;		// istop
	private Integer sxh;		// sxh
	private String gjz;		// gjz
	private String memo;		// memo
	private Date createtime;		// createtime
	private String creator;		// creator
	private String creatorName;		// creator_name
	private Date lastupdate;		// lastupdate
	private String updator;		// updator
	private String updatorName;		// updator_name
	private Integer ver;		// ver
	private Date vertime;		// vertime
	private Integer deptId;		// dept_id
	private String deptPath;		// dept_path
	private Integer delflg;		// delflg
	private Integer state;		// state
	private String xgfj;		// xgfj
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
	
	public BckjBizArticle() {
		super();
	}

	public BckjBizArticle(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=64, message="creator长度必须介于 0 和 64 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Length(min=0, max=64, message="creator_name长度必须介于 0 和 64 之间")
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}
	
	@Length(min=0, max=64, message="updator长度必须介于 0 和 64 之间")
	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
	@Length(min=0, max=64, message="updator_name长度必须介于 0 和 64 之间")
	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}
	
	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVertime() {
		return vertime;
	}

	public void setVertime(Date vertime) {
		this.vertime = vertime;
	}
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=240, message="dept_path长度必须介于 0 和 240 之间")
	public String getDeptPath() {
		return deptPath;
	}

	public void setDeptPath(String deptPath) {
		this.deptPath = deptPath;
	}
	
	public Integer getDelflg() {
		return delflg;
	}

	public void setDelflg(Integer delflg) {
		this.delflg = delflg;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Length(min=0, max=20, message="xgfj长度必须介于 0 和 20 之间")
	public String getXgfj() {
		return xgfj;
	}

	public void setXgfj(String xgfj) {
		this.xgfj = xgfj;
	}
	
	@Length(min=0, max=160, message="exp1长度必须介于 0 和 160 之间")
	public String getExp1() {
		return exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}
	
	@Length(min=0, max=200, message="exp2长度必须介于 0 和 200 之间")
	public String getExp2() {
		return exp2;
	}

	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}
	
	@Length(min=0, max=200, message="exp3长度必须介于 0 和 200 之间")
	public String getExp3() {
		return exp3;
	}

	public void setExp3(String exp3) {
		this.exp3 = exp3;
	}
	
	@Length(min=0, max=200, message="exp4长度必须介于 0 和 200 之间")
	public String getExp4() {
		return exp4;
	}

	public void setExp4(String exp4) {
		this.exp4 = exp4;
	}
	
	@Length(min=0, max=200, message="exp5长度必须介于 0 和 200 之间")
	public String getExp5() {
		return exp5;
	}

	public void setExp5(String exp5) {
		this.exp5 = exp5;
	}
	
	@Length(min=0, max=200, message="exp6长度必须介于 0 和 200 之间")
	public String getExp6() {
		return exp6;
	}

	public void setExp6(String exp6) {
		this.exp6 = exp6;
	}
	
	@Length(min=0, max=200, message="exp7长度必须介于 0 和 200 之间")
	public String getExp7() {
		return exp7;
	}

	public void setExp7(String exp7) {
		this.exp7 = exp7;
	}
	
	@Length(min=0, max=200, message="exp8长度必须介于 0 和 200 之间")
	public String getExp8() {
		return exp8;
	}

	public void setExp8(String exp8) {
		this.exp8 = exp8;
	}
	
	@Length(min=0, max=200, message="exp9长度必须介于 0 和 200 之间")
	public String getExp9() {
		return exp9;
	}

	public void setExp9(String exp9) {
		this.exp9 = exp9;
	}
	
	@Length(min=0, max=200, message="exp10长度必须介于 0 和 200 之间")
	public String getExp10() {
		return exp10;
	}

	public void setExp10(String exp10) {
		this.exp10 = exp10;
	}
	
}