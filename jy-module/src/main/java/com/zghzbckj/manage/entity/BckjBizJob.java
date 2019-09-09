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
public class BckjBizJob extends DataWithExpEntity<BckjBizJob> {
	
	private static final long serialVersionUID = 1L;
	private String owid;		// owid
	private String qyxxRefOwid;		// qyxx_ref_owid
	private String zwbt;		// zwbt
	private String zwgjz;		// zwgjz
	private Integer zwlx;		// zwlx
	private String lmlj;		// lmlj
	private Integer lmbh;		// lmbh
	private String zwPro;		// zw_pro
	private String zwCity;		// zw_city
	private String zwArea;		// zw_area
	private String zwGzzn;		// zw_gzzn
	private String zwGzxz;		// zw_gzxz
	private Custom zwXs;		// zw_xs
	private String zwLxyx;		// zw_lxyx
	private Integer zwZprs;		// zw_zprs
	private String zwNlyq;		// zw_nlyq
	private String zwXlyq;		// zw_xlyq
	private String zwYyyq;		// zw_yyyq
	private String zwGznx;		// zw_gznx
	private String zwGwzz;		// zw_gwzz
	private Integer zwGzs;		// zw_gzs
	private Integer zwYds;		// zw_yds
	private Date zwSxsj;		// zw_sxsj
	private String zwMxxy;		// zw_mxxy
	private String zwMxzy;		// zw_mxzy
	private String zphJbf;		// zph_jbf
	private String zphCbf;		// zph_cbf
	private String zphJbdd;		// zph_jbdd
	private Date zphKsrq;		// zph_ksrq
	private String zphJtsj;		// zph_jtsj
	private Integer zphSfbm;		// zph_sfbm
	private Date zphBmjzsj;		// zph_bmjzsj
	private Integer zphSfqd;		// zph_sfqd
	private Custom zphGpsjd;		// zph_gpsjd
	private Custom zphGpswd;		// zph_gpswd
	private Integer zphGpsbj;		// zph_gpsbj
	private String memo;		// memo
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
	private Integer delflg;		// 0：正常 -1：删除 -100模板另存
	private Integer state;		// -1 :作废 0：正常可修改  100：工作流完成 1-99是流程
	private String xgfj;		// xgfj
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
	
	public BckjBizJob() {
		super();
	}

	public BckjBizJob(String id){
		super(id);
	}

	@Length(min=1, max=64, message="owid长度必须介于 1 和 64 之间")
	public String getOwid() {
		return owid;
	}

	public void setOwid(String owid) {
		this.owid = owid;
	}
	
	@Length(min=0, max=64, message="qyxx_ref_owid长度必须介于 0 和 64 之间")
	public String getQyxxRefOwid() {
		return qyxxRefOwid;
	}

	public void setQyxxRefOwid(String qyxxRefOwid) {
		this.qyxxRefOwid = qyxxRefOwid;
	}
	
	@Length(min=0, max=200, message="zwbt长度必须介于 0 和 200 之间")
	public String getZwbt() {
		return zwbt;
	}

	public void setZwbt(String zwbt) {
		this.zwbt = zwbt;
	}
	
	@Length(min=0, max=200, message="zwgjz长度必须介于 0 和 200 之间")
	public String getZwgjz() {
		return zwgjz;
	}

	public void setZwgjz(String zwgjz) {
		this.zwgjz = zwgjz;
	}
	
	public Integer getZwlx() {
		return zwlx;
	}

	public void setZwlx(Integer zwlx) {
		this.zwlx = zwlx;
	}
	
	@Length(min=0, max=200, message="lmlj长度必须介于 0 和 200 之间")
	public String getLmlj() {
		return lmlj;
	}

	public void setLmlj(String lmlj) {
		this.lmlj = lmlj;
	}
	
	public Integer getLmbh() {
		return lmbh;
	}

	public void setLmbh(Integer lmbh) {
		this.lmbh = lmbh;
	}
	
	@Length(min=0, max=20, message="zw_pro长度必须介于 0 和 20 之间")
	public String getZwPro() {
		return zwPro;
	}

	public void setZwPro(String zwPro) {
		this.zwPro = zwPro;
	}
	
	@Length(min=0, max=20, message="zw_city长度必须介于 0 和 20 之间")
	public String getZwCity() {
		return zwCity;
	}

	public void setZwCity(String zwCity) {
		this.zwCity = zwCity;
	}
	
	@Length(min=0, max=20, message="zw_area长度必须介于 0 和 20 之间")
	public String getZwArea() {
		return zwArea;
	}

	public void setZwArea(String zwArea) {
		this.zwArea = zwArea;
	}
	
	@Length(min=0, max=20, message="zw_gzzn长度必须介于 0 和 20 之间")
	public String getZwGzzn() {
		return zwGzzn;
	}

	public void setZwGzzn(String zwGzzn) {
		this.zwGzzn = zwGzzn;
	}
	
	@Length(min=0, max=20, message="zw_gzxz长度必须介于 0 和 20 之间")
	public String getZwGzxz() {
		return zwGzxz;
	}

	public void setZwGzxz(String zwGzxz) {
		this.zwGzxz = zwGzxz;
	}
	
	public Custom getZwXs() {
		return zwXs;
	}

	public void setZwXs(Custom zwXs) {
		this.zwXs = zwXs;
	}
	
	@Length(min=0, max=50, message="zw_lxyx长度必须介于 0 和 50 之间")
	public String getZwLxyx() {
		return zwLxyx;
	}

	public void setZwLxyx(String zwLxyx) {
		this.zwLxyx = zwLxyx;
	}
	
	public Integer getZwZprs() {
		return zwZprs;
	}

	public void setZwZprs(Integer zwZprs) {
		this.zwZprs = zwZprs;
	}
	
	@Length(min=0, max=20, message="zw_nlyq长度必须介于 0 和 20 之间")
	public String getZwNlyq() {
		return zwNlyq;
	}

	public void setZwNlyq(String zwNlyq) {
		this.zwNlyq = zwNlyq;
	}
	
	@Length(min=0, max=20, message="zw_xlyq长度必须介于 0 和 20 之间")
	public String getZwXlyq() {
		return zwXlyq;
	}

	public void setZwXlyq(String zwXlyq) {
		this.zwXlyq = zwXlyq;
	}
	
	@Length(min=0, max=20, message="zw_yyyq长度必须介于 0 和 20 之间")
	public String getZwYyyq() {
		return zwYyyq;
	}

	public void setZwYyyq(String zwYyyq) {
		this.zwYyyq = zwYyyq;
	}
	
	@Length(min=0, max=20, message="zw_gznx长度必须介于 0 和 20 之间")
	public String getZwGznx() {
		return zwGznx;
	}

	public void setZwGznx(String zwGznx) {
		this.zwGznx = zwGznx;
	}
	
	@Length(min=0, max=500, message="zw_gwzz长度必须介于 0 和 500 之间")
	public String getZwGwzz() {
		return zwGwzz;
	}

	public void setZwGwzz(String zwGwzz) {
		this.zwGwzz = zwGwzz;
	}
	
	public Integer getZwGzs() {
		return zwGzs;
	}

	public void setZwGzs(Integer zwGzs) {
		this.zwGzs = zwGzs;
	}
	
	public Integer getZwYds() {
		return zwYds;
	}

	public void setZwYds(Integer zwYds) {
		this.zwYds = zwYds;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZwSxsj() {
		return zwSxsj;
	}

	public void setZwSxsj(Date zwSxsj) {
		this.zwSxsj = zwSxsj;
	}
	
	@Length(min=0, max=50, message="zw_mxxy长度必须介于 0 和 50 之间")
	public String getZwMxxy() {
		return zwMxxy;
	}

	public void setZwMxxy(String zwMxxy) {
		this.zwMxxy = zwMxxy;
	}
	
	@Length(min=0, max=50, message="zw_mxzy长度必须介于 0 和 50 之间")
	public String getZwMxzy() {
		return zwMxzy;
	}

	public void setZwMxzy(String zwMxzy) {
		this.zwMxzy = zwMxzy;
	}
	
	@Length(min=0, max=50, message="zph_jbf长度必须介于 0 和 50 之间")
	public String getZphJbf() {
		return zphJbf;
	}

	public void setZphJbf(String zphJbf) {
		this.zphJbf = zphJbf;
	}
	
	@Length(min=0, max=50, message="zph_cbf长度必须介于 0 和 50 之间")
	public String getZphCbf() {
		return zphCbf;
	}

	public void setZphCbf(String zphCbf) {
		this.zphCbf = zphCbf;
	}
	
	@Length(min=0, max=150, message="zph_jbdd长度必须介于 0 和 150 之间")
	public String getZphJbdd() {
		return zphJbdd;
	}

	public void setZphJbdd(String zphJbdd) {
		this.zphJbdd = zphJbdd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZphKsrq() {
		return zphKsrq;
	}

	public void setZphKsrq(Date zphKsrq) {
		this.zphKsrq = zphKsrq;
	}
	
	@Length(min=0, max=20, message="zph_jtsj长度必须介于 0 和 20 之间")
	public String getZphJtsj() {
		return zphJtsj;
	}

	public void setZphJtsj(String zphJtsj) {
		this.zphJtsj = zphJtsj;
	}
	
	public Integer getZphSfbm() {
		return zphSfbm;
	}

	public void setZphSfbm(Integer zphSfbm) {
		this.zphSfbm = zphSfbm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZphBmjzsj() {
		return zphBmjzsj;
	}

	public void setZphBmjzsj(Date zphBmjzsj) {
		this.zphBmjzsj = zphBmjzsj;
	}
	
	public Integer getZphSfqd() {
		return zphSfqd;
	}

	public void setZphSfqd(Integer zphSfqd) {
		this.zphSfqd = zphSfqd;
	}
	
	public Custom getZphGpsjd() {
		return zphGpsjd;
	}

	public void setZphGpsjd(Custom zphGpsjd) {
		this.zphGpsjd = zphGpsjd;
	}
	
	public Custom getZphGpswd() {
		return zphGpswd;
	}

	public void setZphGpswd(Custom zphGpswd) {
		this.zphGpswd = zphGpswd;
	}
	
	public Integer getZphGpsbj() {
		return zphGpsbj;
	}

	public void setZphGpsbj(Integer zphGpsbj) {
		this.zphGpsbj = zphGpsbj;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
	@Length(min=0, max=20, message="xgfj长度必须介于 0 和 20 之间")
	public String getXgfj() {
		return xgfj;
	}

	public void setXgfj(String xgfj) {
		this.xgfj = xgfj;
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