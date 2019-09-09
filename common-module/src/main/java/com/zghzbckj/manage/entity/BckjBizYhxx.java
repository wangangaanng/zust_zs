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
	private String owid;		// owid
	private String sjh;		// sjh
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
	private Date createtime;		// createtime
	private String creator;		// creator
	private String creatorName;		// creator_name
	private Date lastupdate;		// lastupdate
	private String updator;		// updator
	private String updatorName;		// updator_name
	private String ver;		// ver
	private Date vertime;		// vertime
	private Integer deptId;		// dept_id
	private String deptPath;		// dept_path
	private Integer delflg;		// 0：正常 -1：删除 -100模板另存
	private Integer state;		// -1 :作废 0：正常可修改  100：工作流完成 1-99是流程
	private String yhDlzh;		// yh_dlzh
	private String yhDlmm;		// yh_dlmm
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
	
	public BckjBizYhxx() {
		super();
	}

	public BckjBizYhxx(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
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
	
	@Length(min=0, max=11, message="ver长度必须介于 0 和 11 之间")
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
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
	
	@Length(min=0, max=30, message="yh_dlzh长度必须介于 0 和 30 之间")
	public String getYhDlzh() {
		return yhDlzh;
	}

	public void setYhDlzh(String yhDlzh) {
		this.yhDlzh = yhDlzh;
	}
	
	@Length(min=0, max=30, message="yh_dlmm长度必须介于 0 和 30 之间")
	public String getYhDlmm() {
		return yhDlmm;
	}

	public void setYhDlmm(String yhDlmm) {
		this.yhDlmm = yhDlmm;
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