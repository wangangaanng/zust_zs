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
public class BckjBizQyxx extends DataWithExpEntity<BckjBizQyxx> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private String qyTysh;		// qy_tysh
	private String qymc;		// qymc
	private String qydz;		// qydz
	private String qylxfs;		// qylxfs
	private String qyYx;		// qy_yx
	private String qyLxr;		// qy_lxr
	private String qyLxrdh;		// qy_lxrdh
	private String qyProv;		// qy_prov
	private String qyCity;		// qy_city
	private String qyArea;		// qy_area
	private String qyGsxz;		// qy_gsxz
	private String qyHylb;		// qy_hylb
	private String qyGsgm;		// qy_gsgm
	private String qyGswz;		// qy_gswz
	private String qyFrdbxm;		// qy_frdbxm
	private String qyZczj;		// qy_zczj
	private String qyGsjs;		// qy_gsjs
	private Date qyZcsj;		// qy_zcsj
	private String qyFrsfz;		// qy_frsfz
	private String qyYyzzzp;		// qy_yyzzzp
	private String qyFrsfzzp;		// qy_frsfzzp
	private String qybq;		// qybq
	private String memo;		// memo
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
	
	public BckjBizQyxx() {
		super();
	}

	public BckjBizQyxx(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
	}
	
	@Length(min=0, max=20, message="qy_tysh长度必须介于 0 和 20 之间")
	public String getQyTysh() {
		return qyTysh;
	}

	public void setQyTysh(String qyTysh) {
		this.qyTysh = qyTysh;
	}
	
	@Length(min=0, max=100, message="qymc长度必须介于 0 和 100 之间")
	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
	@Length(min=0, max=200, message="qydz长度必须介于 0 和 200 之间")
	public String getQydz() {
		return qydz;
	}

	public void setQydz(String qydz) {
		this.qydz = qydz;
	}
	
	@Length(min=0, max=20, message="qylxfs长度必须介于 0 和 20 之间")
	public String getQylxfs() {
		return qylxfs;
	}

	public void setQylxfs(String qylxfs) {
		this.qylxfs = qylxfs;
	}
	
	@Length(min=0, max=50, message="qy_yx长度必须介于 0 和 50 之间")
	public String getQyYx() {
		return qyYx;
	}

	public void setQyYx(String qyYx) {
		this.qyYx = qyYx;
	}
	
	@Length(min=0, max=20, message="qy_lxr长度必须介于 0 和 20 之间")
	public String getQyLxr() {
		return qyLxr;
	}

	public void setQyLxr(String qyLxr) {
		this.qyLxr = qyLxr;
	}
	
	@Length(min=0, max=100, message="qy_lxrdh长度必须介于 0 和 100 之间")
	public String getQyLxrdh() {
		return qyLxrdh;
	}

	public void setQyLxrdh(String qyLxrdh) {
		this.qyLxrdh = qyLxrdh;
	}
	
	@Length(min=0, max=20, message="qy_prov长度必须介于 0 和 20 之间")
	public String getQyProv() {
		return qyProv;
	}

	public void setQyProv(String qyProv) {
		this.qyProv = qyProv;
	}
	
	@Length(min=0, max=20, message="qy_city长度必须介于 0 和 20 之间")
	public String getQyCity() {
		return qyCity;
	}

	public void setQyCity(String qyCity) {
		this.qyCity = qyCity;
	}
	
	@Length(min=0, max=20, message="qy_area长度必须介于 0 和 20 之间")
	public String getQyArea() {
		return qyArea;
	}

	public void setQyArea(String qyArea) {
		this.qyArea = qyArea;
	}
	
	@Length(min=0, max=20, message="qy_gsxz长度必须介于 0 和 20 之间")
	public String getQyGsxz() {
		return qyGsxz;
	}

	public void setQyGsxz(String qyGsxz) {
		this.qyGsxz = qyGsxz;
	}
	
	@Length(min=0, max=20, message="qy_hylb长度必须介于 0 和 20 之间")
	public String getQyHylb() {
		return qyHylb;
	}

	public void setQyHylb(String qyHylb) {
		this.qyHylb = qyHylb;
	}
	
	@Length(min=0, max=20, message="qy_gsgm长度必须介于 0 和 20 之间")
	public String getQyGsgm() {
		return qyGsgm;
	}

	public void setQyGsgm(String qyGsgm) {
		this.qyGsgm = qyGsgm;
	}
	
	@Length(min=0, max=200, message="qy_gswz长度必须介于 0 和 200 之间")
	public String getQyGswz() {
		return qyGswz;
	}

	public void setQyGswz(String qyGswz) {
		this.qyGswz = qyGswz;
	}
	
	@Length(min=0, max=20, message="qy_frdbxm长度必须介于 0 和 20 之间")
	public String getQyFrdbxm() {
		return qyFrdbxm;
	}

	public void setQyFrdbxm(String qyFrdbxm) {
		this.qyFrdbxm = qyFrdbxm;
	}
	
	public String getQyZczj() {
		return qyZczj;
	}

	public void setQyZczj(String qyZczj) {
		this.qyZczj = qyZczj;
	}
	
	public String getQyGsjs() {
		return qyGsjs;
	}

	public void setQyGsjs(String qyGsjs) {
		this.qyGsjs = qyGsjs;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQyZcsj() {
		return qyZcsj;
	}

	public void setQyZcsj(Date qyZcsj) {
		this.qyZcsj = qyZcsj;
	}
	
	@Length(min=0, max=20, message="qy_frsfz长度必须介于 0 和 20 之间")
	public String getQyFrsfz() {
		return qyFrsfz;
	}

	public void setQyFrsfz(String qyFrsfz) {
		this.qyFrsfz = qyFrsfz;
	}
	
	@Length(min=0, max=100, message="qy_yyzzzp长度必须介于 0 和 100 之间")
	public String getQyYyzzzp() {
		return qyYyzzzp;
	}

	public void setQyYyzzzp(String qyYyzzzp) {
		this.qyYyzzzp = qyYyzzzp;
	}
	
	@Length(min=0, max=100, message="qy_frsfzzp长度必须介于 0 和 100 之间")
	public String getQyFrsfzzp() {
		return qyFrsfzzp;
	}

	public void setQyFrsfzzp(String qyFrsfzzp) {
		this.qyFrsfzzp = qyFrsfzzp;
	}
	
	@Length(min=0, max=1000, message="qybq长度必须介于 0 和 1000 之间")
	public String getQybq() {
		return qybq;
	}

	public void setQybq(String qybq) {
		this.qybq = qybq;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=36, message="creator长度必须介于 0 和 36 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Length(min=0, max=100, message="creator_name长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=36, message="updator长度必须介于 0 和 36 之间")
	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
	@Length(min=0, max=100, message="updator_name长度必须介于 0 和 100 之间")
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
	
}