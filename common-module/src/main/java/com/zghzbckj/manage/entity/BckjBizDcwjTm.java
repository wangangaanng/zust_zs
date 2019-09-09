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
public class BckjBizDcwjTm extends DataWithExpEntity<BckjBizDcwjTm> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private String dcwjRefOwid;		// dcwj_ref_owid
	private String tmmc;		// tmmc
	private Integer tmlx;		// tmlx
	private String tmckda;		// tmckda
	private Custom tmfz;		// tmfz
	private String tmsm;		// tmsm
	private Custom tmsx;		// tmsx
	private String tmxxa;		// tmxxa
	private String tmxxb;		// tmxxb
	private String tmxxc;		// tmxxc
	private String tmxxd;		// tmxxd
	private String tmxxe;		// tmxxe
	private String tmxxf;		// tmxxf
	private String tmxxg;		// tmxxg
	private String tmxxh;		// tmxxh
	private String tmxxi;		// tmxxi
	private String tmxxj;		// tmxxj
	private String tmxxk;		// tmxxk
	private String tmxxl;		// tmxxl
	private String tmxxm;		// tmxxm
	private String tmxxn;		// tmxxn
	private String tmxxo;		// tmxxo
	private String tmxxp;		// tmxxp
	private String tmxxq;		// tmxxq
	private String tmxxr;		// tmxxr
	private String tmxxs;		// tmxxs
	private String tmxxt;		// tmxxt
	private String tmxxu;		// tmxxu
	private String tmxxv;		// tmxxv
	private String tmxxw;		// tmxxw
	private String tmxxx;		// tmxxx
	private String tmxxy;		// tmxxy
	private String tmxxz;		// tmxxz
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
	
	public BckjBizDcwjTm() {
		super();
	}

	public BckjBizDcwjTm(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
	}
	
	@Length(min=0, max=64, message="dcwj_ref_owid长度必须介于 0 和 64 之间")
	public String getDcwjRefOwid() {
		return dcwjRefOwid;
	}

	public void setDcwjRefOwid(String dcwjRefOwid) {
		this.dcwjRefOwid = dcwjRefOwid;
	}
	
	@Length(min=0, max=200, message="tmmc长度必须介于 0 和 200 之间")
	public String getTmmc() {
		return tmmc;
	}

	public void setTmmc(String tmmc) {
		this.tmmc = tmmc;
	}
	
	public Integer getTmlx() {
		return tmlx;
	}

	public void setTmlx(Integer tmlx) {
		this.tmlx = tmlx;
	}
	
	@Length(min=0, max=200, message="tmckda长度必须介于 0 和 200 之间")
	public String getTmckda() {
		return tmckda;
	}

	public void setTmckda(String tmckda) {
		this.tmckda = tmckda;
	}
	
	public Custom getTmfz() {
		return tmfz;
	}

	public void setTmfz(Custom tmfz) {
		this.tmfz = tmfz;
	}
	
	@Length(min=0, max=500, message="tmsm长度必须介于 0 和 500 之间")
	public String getTmsm() {
		return tmsm;
	}

	public void setTmsm(String tmsm) {
		this.tmsm = tmsm;
	}
	
	public Custom getTmsx() {
		return tmsx;
	}

	public void setTmsx(Custom tmsx) {
		this.tmsx = tmsx;
	}
	
	@Length(min=0, max=200, message="tmxxa长度必须介于 0 和 200 之间")
	public String getTmxxa() {
		return tmxxa;
	}

	public void setTmxxa(String tmxxa) {
		this.tmxxa = tmxxa;
	}
	
	@Length(min=0, max=200, message="tmxxb长度必须介于 0 和 200 之间")
	public String getTmxxb() {
		return tmxxb;
	}

	public void setTmxxb(String tmxxb) {
		this.tmxxb = tmxxb;
	}
	
	@Length(min=0, max=200, message="tmxxc长度必须介于 0 和 200 之间")
	public String getTmxxc() {
		return tmxxc;
	}

	public void setTmxxc(String tmxxc) {
		this.tmxxc = tmxxc;
	}
	
	@Length(min=0, max=200, message="tmxxd长度必须介于 0 和 200 之间")
	public String getTmxxd() {
		return tmxxd;
	}

	public void setTmxxd(String tmxxd) {
		this.tmxxd = tmxxd;
	}
	
	@Length(min=0, max=200, message="tmxxe长度必须介于 0 和 200 之间")
	public String getTmxxe() {
		return tmxxe;
	}

	public void setTmxxe(String tmxxe) {
		this.tmxxe = tmxxe;
	}
	
	@Length(min=0, max=200, message="tmxxf长度必须介于 0 和 200 之间")
	public String getTmxxf() {
		return tmxxf;
	}

	public void setTmxxf(String tmxxf) {
		this.tmxxf = tmxxf;
	}
	
	@Length(min=0, max=200, message="tmxxg长度必须介于 0 和 200 之间")
	public String getTmxxg() {
		return tmxxg;
	}

	public void setTmxxg(String tmxxg) {
		this.tmxxg = tmxxg;
	}
	
	@Length(min=0, max=200, message="tmxxh长度必须介于 0 和 200 之间")
	public String getTmxxh() {
		return tmxxh;
	}

	public void setTmxxh(String tmxxh) {
		this.tmxxh = tmxxh;
	}
	
	@Length(min=0, max=200, message="tmxxi长度必须介于 0 和 200 之间")
	public String getTmxxi() {
		return tmxxi;
	}

	public void setTmxxi(String tmxxi) {
		this.tmxxi = tmxxi;
	}
	
	@Length(min=0, max=200, message="tmxxj长度必须介于 0 和 200 之间")
	public String getTmxxj() {
		return tmxxj;
	}

	public void setTmxxj(String tmxxj) {
		this.tmxxj = tmxxj;
	}
	
	@Length(min=0, max=200, message="tmxxk长度必须介于 0 和 200 之间")
	public String getTmxxk() {
		return tmxxk;
	}

	public void setTmxxk(String tmxxk) {
		this.tmxxk = tmxxk;
	}
	
	@Length(min=0, max=200, message="tmxxl长度必须介于 0 和 200 之间")
	public String getTmxxl() {
		return tmxxl;
	}

	public void setTmxxl(String tmxxl) {
		this.tmxxl = tmxxl;
	}
	
	@Length(min=0, max=200, message="tmxxm长度必须介于 0 和 200 之间")
	public String getTmxxm() {
		return tmxxm;
	}

	public void setTmxxm(String tmxxm) {
		this.tmxxm = tmxxm;
	}
	
	@Length(min=0, max=200, message="tmxxn长度必须介于 0 和 200 之间")
	public String getTmxxn() {
		return tmxxn;
	}

	public void setTmxxn(String tmxxn) {
		this.tmxxn = tmxxn;
	}
	
	@Length(min=0, max=200, message="tmxxo长度必须介于 0 和 200 之间")
	public String getTmxxo() {
		return tmxxo;
	}

	public void setTmxxo(String tmxxo) {
		this.tmxxo = tmxxo;
	}
	
	@Length(min=0, max=200, message="tmxxp长度必须介于 0 和 200 之间")
	public String getTmxxp() {
		return tmxxp;
	}

	public void setTmxxp(String tmxxp) {
		this.tmxxp = tmxxp;
	}
	
	@Length(min=0, max=200, message="tmxxq长度必须介于 0 和 200 之间")
	public String getTmxxq() {
		return tmxxq;
	}

	public void setTmxxq(String tmxxq) {
		this.tmxxq = tmxxq;
	}
	
	@Length(min=0, max=200, message="tmxxr长度必须介于 0 和 200 之间")
	public String getTmxxr() {
		return tmxxr;
	}

	public void setTmxxr(String tmxxr) {
		this.tmxxr = tmxxr;
	}
	
	@Length(min=0, max=200, message="tmxxs长度必须介于 0 和 200 之间")
	public String getTmxxs() {
		return tmxxs;
	}

	public void setTmxxs(String tmxxs) {
		this.tmxxs = tmxxs;
	}
	
	@Length(min=0, max=200, message="tmxxt长度必须介于 0 和 200 之间")
	public String getTmxxt() {
		return tmxxt;
	}

	public void setTmxxt(String tmxxt) {
		this.tmxxt = tmxxt;
	}
	
	@Length(min=0, max=200, message="tmxxu长度必须介于 0 和 200 之间")
	public String getTmxxu() {
		return tmxxu;
	}

	public void setTmxxu(String tmxxu) {
		this.tmxxu = tmxxu;
	}
	
	@Length(min=0, max=200, message="tmxxv长度必须介于 0 和 200 之间")
	public String getTmxxv() {
		return tmxxv;
	}

	public void setTmxxv(String tmxxv) {
		this.tmxxv = tmxxv;
	}
	
	@Length(min=0, max=200, message="tmxxw长度必须介于 0 和 200 之间")
	public String getTmxxw() {
		return tmxxw;
	}

	public void setTmxxw(String tmxxw) {
		this.tmxxw = tmxxw;
	}
	
	@Length(min=0, max=200, message="tmxxx长度必须介于 0 和 200 之间")
	public String getTmxxx() {
		return tmxxx;
	}

	public void setTmxxx(String tmxxx) {
		this.tmxxx = tmxxx;
	}
	
	@Length(min=0, max=200, message="tmxxy长度必须介于 0 和 200 之间")
	public String getTmxxy() {
		return tmxxy;
	}

	public void setTmxxy(String tmxxy) {
		this.tmxxy = tmxxy;
	}
	
	@Length(min=0, max=200, message="tmxxz长度必须介于 0 和 200 之间")
	public String getTmxxz() {
		return tmxxz;
	}

	public void setTmxxz(String tmxxz) {
		this.tmxxz = tmxxz;
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