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
	
	public BckjBizDcwjTm() {
		super();
	}

	public BckjBizDcwjTm(String id){
		super(id);
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

	
}