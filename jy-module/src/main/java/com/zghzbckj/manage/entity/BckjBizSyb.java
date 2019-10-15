/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-20
 */
public class BckjBizSyb extends DataWithExpEntity<BckjBizSyb> {
	
	private static final long serialVersionUID = 1L;
	private String yhRefOwid;		// yh_ref_owid
	private String xh;		// xh
	private String ksh;		// ksh
	private String xm;		// xm
	private Integer xb;		// 0女生；11男生
	private String sfzh;		// sfzh
	private String csrq;		// csrq
	private String syd;		// syd
	private String mz;		// mz
	private String zzmm;		// zzmm
	private String rxnf;		// rxnf
	private String bynf;		// bynf
	private String byrq;		// byrq
	private String cxsy;		// cxsy
	private String xqda;		// xqda
	private Integer sfrx;		// sfrx
	private String hkpcs;		// hkpcs
	private Integer hkrx;		// hkrx
	private String xlcc;		// xlcc
	private String xz;		// xz
	private String ssxx;		// ssxx
	private String ssxy;		// ssxy
	private String xxzy;		// xxzy
	private String zyfx;		// zyfx
	private String szbj;		// szbj
	private String pyfs;		// pyfs
	private String wpdw;		// wpdw
	private String knslb;		// knslb
	private String sfslb;		// sfslb
	private String sjhm;		// sjhm
	private String dzyx;		// dzyx
	private String qqhm;		// qqhm
	private String jtdh;		// jtdh
	private String jtyb;		// jtyb
	private String jtdz;		// jtdz

	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	public BckjBizSyb() {
		super();
	}


	
	@Length(min=0, max=30, message="xh长度必须介于 0 和 30 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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
	
	@Length(min=0, max=30, message="sfzh长度必须介于 0 和 30 之间")
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	@Length(min=0, max=30, message="csrq长度必须介于 0 和 30 之间")
	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
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
	public String getRxnf() {
		return rxnf;
	}

	public void setRxnf(String rxnf) {
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
	public String getByrq() {
		return byrq;
	}

	public void setByrq(String byrq) {
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
	
	@Length(min=0, max=100, message="ssxx长度必须介于 0 和 100 之间")
	public String getSsxx() {
		return ssxx;
	}

	public void setSsxx(String ssxx) {
		this.ssxx = ssxx;
	}
	
	@Length(min=0, max=100, message="ssxy长度必须介于 0 和 100 之间")
	public String getSsxy() {
		return ssxy;
	}

	public void setSsxy(String ssxy) {
		this.ssxy = ssxy;
	}
	
	@Length(min=0, max=100, message="xxzy长度必须介于 0 和 100 之间")
	public String getXxzy() {
		return xxzy;
	}

	public void setXxzy(String xxzy) {
		this.xxzy = xxzy;
	}
	
	@Length(min=0, max=100, message="zyfx长度必须介于 0 和 100 之间")
	public String getZyfx() {
		return zyfx;
	}

	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	
	@Length(min=0, max=100, message="szbj长度必须介于 0 和 100 之间")
	public String getSzbj() {
		return szbj;
	}

	public void setSzbj(String szbj) {
		this.szbj = szbj;
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
	
	@Length(min=0, max=30, message="sjhm长度必须介于 0 和 30 之间")
	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	
	@Length(min=0, max=30, message="dzyx长度必须介于 0 和 30 之间")
	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
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