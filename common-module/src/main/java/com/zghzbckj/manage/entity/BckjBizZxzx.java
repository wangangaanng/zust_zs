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
public class BckjBizZxzx extends DataWithExpEntity<BckjBizZxzx> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private Integer zxlx;		// zxlx
	private String wtnr;		// wtnr
	private String danr;		// danr
	private Date twrq;		// twrq
	private Date hdrq;		// hdrq
	private String yx;		// yx
	private String dh;		// dh
	private Integer sfxs;		// sfxs
	private String lyip;		// lyip
	private String twOwid;		// tw_owid
	private String twName;		// tw_name
	private String hfOwid;		// hf_owid
	private String hfName;		// hf_name
	private String zxzyid;		// zxzyid
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
	
	public BckjBizZxzx() {
		super();
	}

	public BckjBizZxzx(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
	}
	
	public Integer getZxlx() {
		return zxlx;
	}

	public void setZxlx(Integer zxlx) {
		this.zxlx = zxlx;
	}
	
	@Length(min=0, max=1000, message="wtnr长度必须介于 0 和 1000 之间")
	public String getWtnr() {
		return wtnr;
	}

	public void setWtnr(String wtnr) {
		this.wtnr = wtnr;
	}
	
	@Length(min=0, max=1000, message="danr长度必须介于 0 和 1000 之间")
	public String getDanr() {
		return danr;
	}

	public void setDanr(String danr) {
		this.danr = danr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTwrq() {
		return twrq;
	}

	public void setTwrq(Date twrq) {
		this.twrq = twrq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHdrq() {
		return hdrq;
	}

	public void setHdrq(Date hdrq) {
		this.hdrq = hdrq;
	}
	
	@Length(min=0, max=200, message="yx长度必须介于 0 和 200 之间")
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}
	
	@Length(min=0, max=36, message="dh长度必须介于 0 和 36 之间")
	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}
	
	public Integer getSfxs() {
		return sfxs;
	}

	public void setSfxs(Integer sfxs) {
		this.sfxs = sfxs;
	}
	
	@Length(min=0, max=20, message="lyip长度必须介于 0 和 20 之间")
	public String getLyip() {
		return lyip;
	}

	public void setLyip(String lyip) {
		this.lyip = lyip;
	}
	
	@Length(min=0, max=64, message="tw_owid长度必须介于 0 和 64 之间")
	public String getTwOwid() {
		return twOwid;
	}

	public void setTwOwid(String twOwid) {
		this.twOwid = twOwid;
	}
	
	@Length(min=0, max=100, message="tw_name长度必须介于 0 和 100 之间")
	public String getTwName() {
		return twName;
	}

	public void setTwName(String twName) {
		this.twName = twName;
	}
	
	@Length(min=0, max=64, message="hf_owid长度必须介于 0 和 64 之间")
	public String getHfOwid() {
		return hfOwid;
	}

	public void setHfOwid(String hfOwid) {
		this.hfOwid = hfOwid;
	}
	
	@Length(min=0, max=100, message="hf_name长度必须介于 0 和 100 之间")
	public String getHfName() {
		return hfName;
	}

	public void setHfName(String hfName) {
		this.hfName = hfName;
	}
	
	@Length(min=0, max=64, message="zxzyid长度必须介于 0 和 64 之间")
	public String getZxzyid() {
		return zxzyid;
	}

	public void setZxzyid(String zxzyid) {
		this.zxzyid = zxzyid;
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