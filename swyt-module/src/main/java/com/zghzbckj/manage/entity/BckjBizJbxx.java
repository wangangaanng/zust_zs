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
public class BckjBizJbxx extends DataWithExpEntity<BckjBizJbxx> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private String yhRefOwid;		// yh_ref_owid
	private String sfzzp;		// sfzzp
	private String xm;		// xm
	private String sfzh;		// sfzh
	private Date csrq;		// csrq
	private String zzmm;		// zzmm
	private String jdxy;		// jdxy
	private String mz;		// mz
	private String xjdq;		// xjdq
	private Integer xb;		// xb
	private String jtzz;		// jtzz
	private String yb;		// yb
	private String lxdh;		// lxdh
	private String area;		// area
	private String city;		// city
	private String prov;		// prov
	private String grsg;		// grsg
	private String grtz;		// grtz
	private String grsl;		// grsl
	private String tcah;		// tcah
	private String grzp;		// grzp
	private String kslb;		// kslb
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
	private Integer ver;		// ver
	private Date vertime;		// vertime
	private Integer deptId;		// dept_id
	private String deptPath;		// dept_path
	private Integer delflg;		// delflg
	private Integer state;		// state
	
	public BckjBizJbxx() {
		super();
	}

	public BckjBizJbxx(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
	}
	
	@Length(min=0, max=64, message="yh_ref_owid长度必须介于 0 和 64 之间")
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	
	@Length(min=0, max=200, message="sfzzp长度必须介于 0 和 200 之间")
	public String getSfzzp() {
		return sfzzp;
	}

	public void setSfzzp(String sfzzp) {
		this.sfzzp = sfzzp;
	}
	
	@Length(min=0, max=20, message="xm长度必须介于 0 和 20 之间")
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Length(min=0, max=20, message="sfzh长度必须介于 0 和 20 之间")
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	
	@Length(min=0, max=20, message="zzmm长度必须介于 0 和 20 之间")
	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	
	@Length(min=0, max=200, message="jdxy长度必须介于 0 和 200 之间")
	public String getJdxy() {
		return jdxy;
	}

	public void setJdxy(String jdxy) {
		this.jdxy = jdxy;
	}
	
	@Length(min=0, max=20, message="mz长度必须介于 0 和 20 之间")
	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}
	
	@Length(min=0, max=200, message="xjdq长度必须介于 0 和 200 之间")
	public String getXjdq() {
		return xjdq;
	}

	public void setXjdq(String xjdq) {
		this.xjdq = xjdq;
	}
	
	public Integer getXb() {
		return xb;
	}

	public void setXb(Integer xb) {
		this.xb = xb;
	}
	
	@Length(min=0, max=200, message="jtzz长度必须介于 0 和 200 之间")
	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	
	@Length(min=0, max=6, message="yb长度必须介于 0 和 6 之间")
	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}
	
	@Length(min=0, max=20, message="lxdh长度必须介于 0 和 20 之间")
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Length(min=0, max=20, message="area长度必须介于 0 和 20 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=20, message="city长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=20, message="prov长度必须介于 0 和 20 之间")
	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
	
	@Length(min=0, max=20, message="grsg长度必须介于 0 和 20 之间")
	public String getGrsg() {
		return grsg;
	}

	public void setGrsg(String grsg) {
		this.grsg = grsg;
	}
	
	@Length(min=0, max=20, message="grtz长度必须介于 0 和 20 之间")
	public String getGrtz() {
		return grtz;
	}

	public void setGrtz(String grtz) {
		this.grtz = grtz;
	}
	
	@Length(min=0, max=50, message="grsl长度必须介于 0 和 50 之间")
	public String getGrsl() {
		return grsl;
	}

	public void setGrsl(String grsl) {
		this.grsl = grsl;
	}
	
	@Length(min=0, max=200, message="tcah长度必须介于 0 和 200 之间")
	public String getTcah() {
		return tcah;
	}

	public void setTcah(String tcah) {
		this.tcah = tcah;
	}
	
	@Length(min=0, max=200, message="grzp长度必须介于 0 和 200 之间")
	public String getGrzp() {
		return grzp;
	}

	public void setGrzp(String grzp) {
		this.grzp = grzp;
	}
	
	@Length(min=0, max=20, message="kslb长度必须介于 0 和 20 之间")
	public String getKslb() {
		return kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
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
	
	@Length(min=0, max=36, message="creator长度必须介于 0 和 36 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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