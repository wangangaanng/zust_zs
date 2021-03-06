/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.zghzbckj.base.entity.DataWithExpEntity;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizLqxs extends DataWithExpEntity<BckjBizLqxs> {
	
	private static final long serialVersionUID = 1L;
	private String xybh;		// xybh
	private String nf;		// nf
	private String lqlx;		// lqlx
	private String sfzh;		// sfzh
	private String ksh;		// ksh
	private String xm;		// xm
	private String xbdm;		// xbdm
	private String lqzy;		// lqzy
	private String lqzyok;		// lqzyok
	private String ntzzy;		// ntzzy
	private String lxdh;		// lxdh
	private String csny;		// csny
	private String zzmmdm;		// zzmmdm
	private String mzdm;		// mzdm
	private String kslbdm;		// kslbdm
	private String bylbdm;		// bylbdm
	private String zxdm;		// zxdm
	private String zxmc;		// zxmc
	private String dqdm;		// dqdm
	private String jtdz;		// jtdz
	private String yzbm;		// yzbm
	private String kstc;		// kstc
	private String ksjlhcf;		// ksjlhcf
	private String sjr;		// sjr
	private String tdcj;		// tdcj
	private String tdzy;		// tdzy
	private String yw;		// yw
	private String sx;		// sx
	private String wy;		// wy
	private String zhw;		// zhw
	private String zhl;		// zhl
	private String mk;		// mk
	private String mscj;		// mscj
	private String mszhcj;		// mszhcj
	private String xy;		// xy
	private String xz;		// xz
	private String lp;		// lp
	private String pc;		// pc
	private String sf;		// sf
	private String memo;		// memo
	private Date jcsj;		// jcsj
	private String ems;		// ems

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	private boolean readOnly;
	
	public BckjBizLqxs() {
		super();
	}

	
	@Length(min=0, max=20, message="xybh长度必须介于 0 和 20 之间")
	public String getXybh() {
		return xybh;
	}

	public void setXybh(String xybh) {
		this.xybh = xybh;
	}
	
	@Length(min=0, max=10, message="nf长度必须介于 0 和 10 之间")
	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}
	
	@Length(min=0, max=20, message="lqlx长度必须介于 0 和 20 之间")
	public String getLqlx() {
		return lqlx;
	}

	public void setLqlx(String lqlx) {
		this.lqlx = lqlx;
	}
	
	@Length(min=0, max=20, message="sfzh长度必须介于 0 和 20 之间")
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	@Length(min=0, max=36, message="ksh长度必须介于 0 和 36 之间")
	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	
	@Length(min=0, max=36, message="xm长度必须介于 0 和 36 之间")
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}
	
	@Length(min=0, max=4, message="xbdm长度必须介于 0 和 4 之间")
	public String getXbdm() {
		return xbdm;
	}

	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	
	@Length(min=0, max=200, message="lqzy长度必须介于 0 和 200 之间")
	public String getLqzy() {
		return lqzy;
	}

	public void setLqzy(String lqzy) {
		this.lqzy = lqzy;
	}
	
	@Length(min=0, max=200, message="lqzyok长度必须介于 0 和 200 之间")
	public String getLqzyok() {
		return lqzyok;
	}

	public void setLqzyok(String lqzyok) {
		this.lqzyok = lqzyok;
	}
	
	@Length(min=0, max=200, message="ntzzy长度必须介于 0 和 200 之间")
	public String getNtzzy() {
		return ntzzy;
	}

	public void setNtzzy(String ntzzy) {
		this.ntzzy = ntzzy;
	}
	
	@Length(min=0, max=36, message="lxdh长度必须介于 0 和 36 之间")
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	@Length(min=0, max=50, message="csny长度必须介于 0 和 50 之间")
	public String getCsny() {
		return csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
	}
	
	@Length(min=0, max=20, message="zzmmdm长度必须介于 0 和 20 之间")
	public String getZzmmdm() {
		return zzmmdm;
	}

	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}
	
	@Length(min=0, max=20, message="mzdm长度必须介于 0 和 20 之间")
	public String getMzdm() {
		return mzdm;
	}

	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}
	
	@Length(min=0, max=20, message="kslbdm长度必须介于 0 和 20 之间")
	public String getKslbdm() {
		return kslbdm;
	}

	public void setKslbdm(String kslbdm) {
		this.kslbdm = kslbdm;
	}
	
	@Length(min=0, max=200, message="bylbdm长度必须介于 0 和 200 之间")
	public String getBylbdm() {
		return bylbdm;
	}

	public void setBylbdm(String bylbdm) {
		this.bylbdm = bylbdm;
	}
	
	@Length(min=0, max=20, message="zxdm长度必须介于 0 和 20 之间")
	public String getZxdm() {
		return zxdm;
	}

	public void setZxdm(String zxdm) {
		this.zxdm = zxdm;
	}
	
	@Length(min=0, max=200, message="zxmc长度必须介于 0 和 200 之间")
	public String getZxmc() {
		return zxmc;
	}

	public void setZxmc(String zxmc) {
		this.zxmc = zxmc;
	}
	
	@Length(min=0, max=20, message="dqdm长度必须介于 0 和 20 之间")
	public String getDqdm() {
		return dqdm;
	}

	public void setDqdm(String dqdm) {
		this.dqdm = dqdm;
	}
	
	@Length(min=0, max=200, message="jtdz长度必须介于 0 和 200 之间")
	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	
	@Length(min=0, max=10, message="yzbm长度必须介于 0 和 10 之间")
	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	
	@Length(min=0, max=500, message="kstc长度必须介于 0 和 500 之间")
	public String getKstc() {
		return kstc;
	}

	public void setKstc(String kstc) {
		this.kstc = kstc;
	}
	
	@Length(min=0, max=500, message="ksjlhcf长度必须介于 0 和 500 之间")
	public String getKsjlhcf() {
		return ksjlhcf;
	}

	public void setKsjlhcf(String ksjlhcf) {
		this.ksjlhcf = ksjlhcf;
	}
	
	@Length(min=0, max=10, message="sjr长度必须介于 0 和 10 之间")
	public String getSjr() {
		return sjr;
	}

	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	
	public String getTdcj() {
		return tdcj;
	}

	public void setTdcj(String tdcj) {
		this.tdcj = tdcj;
	}
	
	@Length(min=0, max=20, message="tdzy长度必须介于 0 和 20 之间")
	public String getTdzy() {
		return tdzy;
	}

	public void setTdzy(String tdzy) {
		this.tdzy = tdzy;
	}
	
	public String getYw() {
		return yw;
	}

	public void setYw(String yw) {
		this.yw = yw;
	}
	
	public String getSx() {
		return sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}
	
	public String getWy() {
		return wy;
	}

	public void setWy(String wy) {
		this.wy = wy;
	}
	
	public String getZhw() {
		return zhw;
	}

	public void setZhw(String zhw) {
		this.zhw = zhw;
	}
	
	public String getZhl() {
		return zhl;
	}

	public void setZhl(String zhl) {
		this.zhl = zhl;
	}
	
	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}
	
	public String getMscj() {
		return mscj;
	}

	public void setMscj(String mscj) {
		this.mscj = mscj;
	}
	
	public String getMszhcj() {
		return mszhcj;
	}

	public void setMszhcj(String mszhcj) {
		this.mszhcj = mszhcj;
	}
	
	@Length(min=0, max=100, message="xy长度必须介于 0 和 100 之间")
	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}
	
	@Length(min=0, max=100, message="xz长度必须介于 0 和 100 之间")
	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}
	
	@Length(min=0, max=100, message="lp长度必须介于 0 和 100 之间")
	public String getLp() {
		return lp;
	}

	public void setLp(String lp) {
		this.lp = lp;
	}
	
	@Length(min=0, max=100, message="pc长度必须介于 0 和 100 之间")
	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}
	
	@Length(min=0, max=100, message="sf长度必须介于 0 和 100 之间")
	public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJcsj() {
		return jcsj;
	}

	public void setJcsj(Date jcsj) {
		this.jcsj = jcsj;
	}
	
	@Length(min=0, max=20, message="ems长度必须介于 0 和 20 之间")
	public String getEms() {
		return ems;
	}

	public void setEms(String ems) {
		this.ems = ems;
	}

	
}