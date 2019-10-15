package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 学生信息录入类Entity
 * @author wangangaanng
 * @version 2019-10-10
 */
public class BckjBizStudentinfo extends DataWithExpEntity<BckjBizStudentinfo> {
	
	private static final long serialVersionUID = 1L;

	private String xsxh;		// xsxh
	private String yhRefOwid;		// yh_ref_owid
	private String zydhOne;		// zydh_one
	private String zydhTwo;		// zydh_two
	private String zydhThree;		// zydh_three
	private String zydhFour;		// zydh_four
	private String zydhFive;		// zydh_five
	private String zydhSix;		// zydh_six
	private String kslb;		// kslb
	private String bylb;		// bylb
	private Integer zxdm;		// zxdm
	private String zxmc;		// zxmc
	private String syddm;
	private String kstc;		// kstc
	private String hjqk;		// hjqk
	private String sjr;		// sjr
	private Integer tdcj;		// tdcj
	private String tdzy;		// tdzy
	private String xkkm;		// xkkm
	private Integer yw;		// yw
	private Integer sx;		// sx
	private Integer wy;		// wy
	private Integer wl;		// wl
	private Integer hx;		// hx
	private Integer sw;		// sw
	private Integer zz;		// zz
	private Integer ls;		// ls
	private Integer dl;		// dl
	private Integer js;		// js
	private Integer zhcj;		// zhcj
	private Integer tkmscj;		// tkmscj
	private Integer tkbycj;		// tkbycj
	private Integer tkszcj;		// tkszcj
	private String km;		// km
	private String pc;		// pc
	private String updator;		// updator
	private String sg;		// sg
	private String tz;		// tz
	private Integer jf;		// jf
	private String memo;		// memo

	public String getYhRefOwid() {
		return yhRefOwid;
	}

	public void setYhRefOwid(String yhRefOwid) {
		this.yhRefOwid = yhRefOwid;
	}
	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}


	public String getKslb() {
		return kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
	}

	public String getBylb() {
		return bylb;
	}

	public void setBylb(String bylb) {
		this.bylb = bylb;
	}

	public String getSyddm() {
		return syddm;
	}

	public void setSyddm(String syddm) {
		this.syddm = syddm;
	}
	
	@Length(min=0, max=40, message="xsxh长度必须介于 0 和 40 之间")
	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}
	
	@Length(min=0, max=60, message="zydh_one长度必须介于 0 和 60 之间")
	public String getZydhOne() {
		return zydhOne;
	}

	public void setZydhOne(String zydhOne) {
		this.zydhOne = zydhOne;
	}
	
	@Length(min=0, max=60, message="zydh_two长度必须介于 0 和 60 之间")
	public String getZydhTwo() {
		return zydhTwo;
	}

	public void setZydhTwo(String zydhTwo) {
		this.zydhTwo = zydhTwo;
	}
	
	@Length(min=0, max=60, message="zydh_three长度必须介于 0 和 60 之间")
	public String getZydhThree() {
		return zydhThree;
	}

	public void setZydhThree(String zydhThree) {
		this.zydhThree = zydhThree;
	}
	
	@Length(min=0, max=60, message="zydh_four长度必须介于 0 和 60 之间")
	public String getZydhFour() {
		return zydhFour;
	}

	public void setZydhFour(String zydhFour) {
		this.zydhFour = zydhFour;
	}
	
	@Length(min=0, max=60, message="zydh_five长度必须介于 0 和 60 之间")
	public String getZydhFive() {
		return zydhFive;
	}

	public void setZydhFive(String zydhFive) {
		this.zydhFive = zydhFive;
	}
	
	@Length(min=0, max=60, message="zydh_six长度必须介于 0 和 60 之间")
	public String getZydhSix() {
		return zydhSix;
	}

	public void setZydhSix(String zydhSix) {
		this.zydhSix = zydhSix;
	}
	

	
	public Integer getZxdm() {
		return zxdm;
	}

	public void setZxdm(Integer zxdm) {
		this.zxdm = zxdm;
	}
	
	@Length(min=0, max=100, message="zxmc长度必须介于 0 和 100 之间")
	public String getZxmc() {
		return zxmc;
	}

	public void setZxmc(String zxmc) {
		this.zxmc = zxmc;
	}
	
	@Length(min=0, max=100, message="kstc长度必须介于 0 和 100 之间")
	public String getKstc() {
		return kstc;
	}

	public void setKstc(String kstc) {
		this.kstc = kstc;
	}
	
	@Length(min=0, max=100, message="hjqk长度必须介于 0 和 100 之间")
	public String getHjqk() {
		return hjqk;
	}

	public void setHjqk(String hjqk) {
		this.hjqk = hjqk;
	}
	
	@Length(min=0, max=1024, message="sjr长度必须介于 0 和 1024 之间")
	public String getSjr() {
		return sjr;
	}

	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	
	public Integer getTdcj() {
		return tdcj;
	}

	public void setTdcj(Integer tdcj) {
		this.tdcj = tdcj;
	}
	
	@Length(min=0, max=60, message="tdzy长度必须介于 0 和 60 之间")
	public String getTdzy() {
		return tdzy;
	}

	public void setTdzy(String tdzy) {
		this.tdzy = tdzy;
	}
	
	@Length(min=0, max=60, message="xkkm长度必须介于 0 和 60 之间")
	public String getXkkm() {
		return xkkm;
	}

	public void setXkkm(String xkkm) {
		this.xkkm = xkkm;
	}
	
	public Integer getYw() {
		return yw;
	}

	public void setYw(Integer yw) {
		this.yw = yw;
	}
	
	public Integer getSx() {
		return sx;
	}

	public void setSx(Integer sx) {
		this.sx = sx;
	}
	
	public Integer getWy() {
		return wy;
	}

	public void setWy(Integer wy) {
		this.wy = wy;
	}
	
	public Integer getWl() {
		return wl;
	}

	public void setWl(Integer wl) {
		this.wl = wl;
	}
	
	public Integer getHx() {
		return hx;
	}

	public void setHx(Integer hx) {
		this.hx = hx;
	}
	
	public Integer getSw() {
		return sw;
	}

	public void setSw(Integer sw) {
		this.sw = sw;
	}
	
	public Integer getZz() {
		return zz;
	}

	public void setZz(Integer zz) {
		this.zz = zz;
	}
	
	public Integer getLs() {
		return ls;
	}

	public void setLs(Integer ls) {
		this.ls = ls;
	}
	
	public Integer getDl() {
		return dl;
	}

	public void setDl(Integer dl) {
		this.dl = dl;
	}
	
	public Integer getJs() {
		return js;
	}

	public void setJs(Integer js) {
		this.js = js;
	}
	
	public Integer getZhcj() {
		return zhcj;
	}

	public void setZhcj(Integer zhcj) {
		this.zhcj = zhcj;
	}
	
	public Integer getTkmscj() {
		return tkmscj;
	}

	public void setTkmscj(Integer tkmscj) {
		this.tkmscj = tkmscj;
	}
	
	public Integer getTkbycj() {
		return tkbycj;
	}

	public void setTkbycj(Integer tkbycj) {
		this.tkbycj = tkbycj;
	}
	
	public Integer getTkszcj() {
		return tkszcj;
	}

	public void setTkszcj(Integer tkszcj) {
		this.tkszcj = tkszcj;
	}
	

	
	@Length(min=0, max=64, message="updator长度必须介于 0 和 64 之间")
	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
	@Length(min=0, max=60, message="sg长度必须介于 0 和 60 之间")
	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}
	
	@Length(min=0, max=60, message="tz长度必须介于 0 和 60 之间")
	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}
	
	public Integer getJf() {
		return jf;
	}

	public void setJf(Integer jf) {
		this.jf = jf;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}



}