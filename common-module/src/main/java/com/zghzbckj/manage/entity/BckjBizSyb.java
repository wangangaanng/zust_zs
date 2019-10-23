/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-20
 */
public class BckjBizSyb extends DataWithExpEntity<BckjBizSyb> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String xsxh;		// xh
	private String ksh;		// ksh
	private String xm;		// xm
	private Integer xb;		// 2女生；1男生
	private String sfz;		// sfzh
	private Date csrq;		// csrq
	private String syd;		// syd
	private String mz;		// mz
	private String zzmm;		// zzmm
	private Date rxnf;		// rxnf
	private String bynf;		// bynf
	private Date byrq;		// byrq
	private String cxsy;		// cxsy
	private String xqda;		// xqda
	private Integer sfrx;		// sfrx
	private String hkpcs;		// hkpcs
	private Integer hkrx;		// hkrx
	private String xlcc;		// xlcc
	private String xz;		// xz
	private String xxmc;		// ssxx
	private String xsxy;		// ssxy
	private String xszy;		// xxzy
	private String zyfx;		// zyfx
	private String xsbj;		// szbj
	private String pyfs;		// pyfs
	private String wpdw;		// wpdw
	private String knslb;		// knslb
	private String sfslb;		// sfslb
	private String sjh;		// sjhm
	private String yx;		// dzyx
	private String qqhm;		// qqhm
	private String jtdh;		// jtdh
	private String jtyb;		// jtyb
	private String jtdz;		// jtdz

	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}


	public String getXxmc() {
		return xxmc;
	}

	public String getXsxy() {
		return xsxy;
	}

	public String getXszy() {
		return xszy;
	}

	public String getXsbj() {
		return xsbj;
	}
	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	public BckjBizSyb() {
		super();
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public void setXsxy(String xsxy) {
		this.xsxy = xsxy;
	}

	public void setXszy(String xszy) {
		this.xszy = xszy;
	}

	public void setXsbj(String xsbj) {
		this.xsbj = xsbj;
	}

	

	
	@Length(min=0, max=30, message="ksh长度必须介于 0 和 30 之间")
	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	
	@Length(min=0, max=30, message="xm长度必须介于 0 和 30 之间")
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
	

	
	@Length(min=0, max=30, message="csrq长度必须介于 0 和 30 之间")
	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	
	@Length(min=0, max=100, message="syd长度必须介于 0 和 100 之间")
	public String getSyd() {
		return syd;
	}

	public void setSyd(String syd) {
		this.syd = syd;
	}
	
	@Length(min=0, max=30, message="mz长度必须介于 0 和 30 之间")
	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}
	
	@Length(min=0, max=30, message="zzmm长度必须介于 0 和 30 之间")
	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	
	@Length(min=0, max=30, message="rxnf长度必须介于 0 和 30 之间")
	public Date getRxnf() {
		return rxnf;
	}

	public void setRxnf(Date rxnf) {
		this.rxnf = rxnf;
	}
	
	@Length(min=0, max=30, message="bynf长度必须介于 0 和 30 之间")
	public String getBynf() {
		return bynf;
	}

	public void setBynf(String bynf) {
		this.bynf = bynf;
	}
	
	@Length(min=0, max=30, message="byrq长度必须介于 0 和 30 之间")
	public Date getByrq() {
		return byrq;
	}

	public void setByrq(Date byrq) {
		this.byrq = byrq;
	}
	
	@Length(min=0, max=100, message="cxsy长度必须介于 0 和 100 之间")
	public String getCxsy() {
		return cxsy;
	}

	public void setCxsy(String cxsy) {
		this.cxsy = cxsy;
	}
	
	@Length(min=0, max=100, message="xqda长度必须介于 0 和 100 之间")
	public String getXqda() {
		return xqda;
	}

	public void setXqda(String xqda) {
		this.xqda = xqda;
	}
	
	public Integer getSfrx() {
		return sfrx;
	}

	public void setSfrx(Integer sfrx) {
		this.sfrx = sfrx;
	}
	
	@Length(min=0, max=100, message="hkpcs长度必须介于 0 和 100 之间")
	public String getHkpcs() {
		return hkpcs;
	}

	public void setHkpcs(String hkpcs) {
		this.hkpcs = hkpcs;
	}
	
	public Integer getHkrx() {
		return hkrx;
	}

	public void setHkrx(Integer hkrx) {
		this.hkrx = hkrx;
	}
	
	@Length(min=0, max=100, message="xlcc长度必须介于 0 和 100 之间")
	public String getXlcc() {
		return xlcc;
	}

	public void setXlcc(String xlcc) {
		this.xlcc = xlcc;
	}
	
	@Length(min=0, max=100, message="xz长度必须介于 0 和 100 之间")
	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}
	


	
	@Length(min=0, max=100, message="zyfx长度必须介于 0 和 100 之间")
	public String getZyfx() {
		return zyfx;
	}

	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	

	
	@Length(min=0, max=100, message="pyfs长度必须介于 0 和 100 之间")
	public String getPyfs() {
		return pyfs;
	}

	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}
	
	@Length(min=0, max=100, message="wpdw长度必须介于 0 和 100 之间")
	public String getWpdw() {
		return wpdw;
	}

	public void setWpdw(String wpdw) {
		this.wpdw = wpdw;
	}
	
	@Length(min=0, max=100, message="knslb长度必须介于 0 和 100 之间")
	public String getKnslb() {
		return knslb;
	}

	public void setKnslb(String knslb) {
		this.knslb = knslb;
	}
	
	@Length(min=0, max=100, message="sfslb长度必须介于 0 和 100 之间")
	public String getSfslb() {
		return sfslb;
	}

	public void setSfslb(String sfslb) {
		this.sfslb = sfslb;
	}
	

	
	@Length(min=0, max=30, message="qqhm长度必须介于 0 和 30 之间")
	public String getQqhm() {
		return qqhm;
	}

	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	
	@Length(min=0, max=30, message="jtdh长度必须介于 0 和 30 之间")
	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	
	@Length(min=0, max=30, message="jtyb长度必须介于 0 和 30 之间")
	public String getJtyb() {
		return jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	
	@Length(min=0, max=100, message="jtdz长度必须介于 0 和 100 之间")
	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}


}