/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ccEntity
 *
 * @author cc
 * @version 2019-09-09
 */
public class BckjBizJybm extends DataWithExpEntity<BckjBizJybm> {

    private static final long serialVersionUID = 1L;
    private String qyxxRefOwid;        // qyxx_ref_owid
    private String yqRefOwid;
    private String yqmc;
    private Integer bmqygs;
    private String jobRefOwid;        // job_ref_owid
    private String yhRefOwid;        // yh_ref_owid
    private Integer bmlx;        // bmlx
    private Integer bmdx;        // bmdx
    private Date bmsj;        // bmsj
    private String zwbh;        // zwbh
    private String lxdh;        // lxdh
    private String lxr;        // lxr
    private String qymc;        // qymc
    private String qysh;        // qysh
    private String xjhsqly;        // xjhsqly
    private Integer sfxz;        // sfxz
    private String xjsj;        // xjsj
    private Integer sftz;        // sftz
    private Date tzsj;        // tzsj
    private String jkr;        // jkr
    private String jkrjs;        // jkrjs
    private String memo;        // memo
    private String xgfj;

    private String zwbt;
    private String zwPro;
    private String zwCity;
    private String zwArea;
    private String zphJbdd;
    private Date zphKsrq;
    private String zphJtsj;
    private String gsqymc;


    private String zdytj1;
    private String zdytj2;
    private String zdytj3;
    private String zdytj4;
    private String zdytj5;
    private String tjsd1;
    private String tjsd2;
    private String tjsd3;
    private String tjsd4;
    private String tjsd5;
    private String xxlxr;
    private String xxlxrdh;

    private Integer zphSfbm;        // zph_sfbm
    private Date zphBmjzsj;        // zph_bmjzsj
    private Integer zphSfqd;        // zph_sfqd
    private String qyLxr;
    private String qyLxrdh;
    private boolean readOnly;

    private String zw1;
    private String zw2;
    private String zw3;
    private String zw4;
    private String zw5;
    private String rs1;
    private String rs2;
    private String rs3;
    private String rs4;
    private String rs5;
    private Integer showUpload;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getShowUpload() {
        return showUpload;
    }

    public void setShowUpload(Integer showUpload) {
        this.showUpload = showUpload;
    }

    public String getYqRefOwid() {
        return yqRefOwid;
    }

    public void setYqRefOwid(String yqRefOwid) {
        this.yqRefOwid = yqRefOwid;
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }

    public Integer getBmqygs() {
        return bmqygs;
    }

    public void setBmqygs(Integer bmqygs) {
        this.bmqygs = bmqygs;
    }

    public String getZw1() {
        return zw1;
    }

    public void setZw1(String zw1) {
        this.zw1 = zw1;
    }

    public String getZw2() {
        return zw2;
    }

    public void setZw2(String zw2) {
        this.zw2 = zw2;
    }

    public String getZw3() {
        return zw3;
    }

    public void setZw3(String zw3) {
        this.zw3 = zw3;
    }

    public String getZw4() {
        return zw4;
    }

    public void setZw4(String zw4) {
        this.zw4 = zw4;
    }

    public String getZw5() {
        return zw5;
    }

    public void setZw5(String zw5) {
        this.zw5 = zw5;
    }

    public String getRs1() {
        return rs1;
    }

    public void setRs1(String rs1) {
        this.rs1 = rs1;
    }

    public String getRs2() {
        return rs2;
    }

    public void setRs2(String rs2) {
        this.rs2 = rs2;
    }

    public String getRs3() {
        return rs3;
    }

    public void setRs3(String rs3) {
        this.rs3 = rs3;
    }

    public String getRs4() {
        return rs4;
    }

    public void setRs4(String rs4) {
        this.rs4 = rs4;
    }

    public String getRs5() {
        return rs5;
    }

    public void setRs5(String rs5) {
        this.rs5 = rs5;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getQyLxr() {
        return qyLxr;
    }

    public void setQyLxr(String qyLxr) {
        this.qyLxr = qyLxr;
    }

    public String getQyLxrdh() {
        return qyLxrdh;
    }

    public void setQyLxrdh(String qyLxrdh) {
        this.qyLxrdh = qyLxrdh;
    }

    public Integer getZphSfbm() {
        return zphSfbm;
    }

    public void setZphSfbm(Integer zphSfbm) {
        this.zphSfbm = zphSfbm;
    }

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

    public String getXxlxr() {
        return xxlxr;
    }

    public void setXxlxr(String xxlxr) {
        this.xxlxr = xxlxr;
    }

    public String getXxlxrdh() {
        return xxlxrdh;
    }

    public void setXxlxrdh(String xxlxrdh) {
        this.xxlxrdh = xxlxrdh;
    }

    public String getZdytj1() {
        return zdytj1;
    }

    public void setZdytj1(String zdytj1) {
        this.zdytj1 = zdytj1;
    }

    public String getZdytj2() {
        return zdytj2;
    }

    public void setZdytj2(String zdytj2) {
        this.zdytj2 = zdytj2;
    }

    public String getZdytj3() {
        return zdytj3;
    }

    public void setZdytj3(String zdytj3) {
        this.zdytj3 = zdytj3;
    }

    public String getZdytj4() {
        return zdytj4;
    }

    public void setZdytj4(String zdytj4) {
        this.zdytj4 = zdytj4;
    }

    public String getZdytj5() {
        return zdytj5;
    }

    public void setZdytj5(String zdytj5) {
        this.zdytj5 = zdytj5;
    }

    public String getTjsd1() {
        return tjsd1;
    }

    public void setTjsd1(String tjsd1) {
        this.tjsd1 = tjsd1;
    }

    public String getTjsd2() {
        return tjsd2;
    }

    public void setTjsd2(String tjsd2) {
        this.tjsd2 = tjsd2;
    }

    public String getTjsd3() {
        return tjsd3;
    }

    public void setTjsd3(String tjsd3) {
        this.tjsd3 = tjsd3;
    }

    public String getTjsd4() {
        return tjsd4;
    }

    public void setTjsd4(String tjsd4) {
        this.tjsd4 = tjsd4;
    }

    public String getTjsd5() {
        return tjsd5;
    }

    public void setTjsd5(String tjsd5) {
        this.tjsd5 = tjsd5;
    }

    public String getGsqymc() {
        return gsqymc;
    }

    public void setGsqymc(String gsqymc) {
        this.gsqymc = gsqymc;
    }

    public String getZphJtsj() {
        return zphJtsj;
    }

    public void setZphJtsj(String zphJtsj) {
        this.zphJtsj = zphJtsj;
    }

    public String getZwbt() {
        return zwbt;
    }

    public void setZwbt(String zwbt) {
        this.zwbt = zwbt;
    }

    public String getZwPro() {
        return zwPro;
    }

    public void setZwPro(String zwPro) {
        this.zwPro = zwPro;
    }

    public String getZwCity() {
        return zwCity;
    }

    public void setZwCity(String zwCity) {
        this.zwCity = zwCity;
    }

    public String getZwArea() {
        return zwArea;
    }

    public void setZwArea(String zwArea) {
        this.zwArea = zwArea;
    }

    public String getZphJbdd() {
        return zphJbdd;
    }

    public void setZphJbdd(String zphJbdd) {
        this.zphJbdd = zphJbdd;
    }

    public Date getZphKsrq() {
        return zphKsrq;
    }

    public void setZphKsrq(Date zphKsrq) {
        this.zphKsrq = zphKsrq;
    }

    public String getXgfj() {
        return xgfj;
    }

    public void setXgfj(String xgfj) {
        this.xgfj = xgfj;
    }

    public BckjBizJybm() {
        super();
    }


    @Length(min = 0, max = 64, message = "qyxx_ref_owid长度必须介于 0 和 64 之间")
    public String getQyxxRefOwid() {
        return qyxxRefOwid;
    }

    public void setQyxxRefOwid(String qyxxRefOwid) {
        this.qyxxRefOwid = qyxxRefOwid;
    }

    @Length(min = 0, max = 64, message = "job_ref_owid长度必须介于 0 和 64 之间")
    public String getJobRefOwid() {
        return jobRefOwid;
    }

    public void setJobRefOwid(String jobRefOwid) {
        this.jobRefOwid = jobRefOwid;
    }

    @Length(min = 0, max = 64, message = "yh_ref_owid长度必须介于 0 和 64 之间")
    public String getYhRefOwid() {
        return yhRefOwid;
    }

    public void setYhRefOwid(String yhRefOwid) {
        this.yhRefOwid = yhRefOwid;
    }

    @NotNull(message = "bmlx不能为空")
    public Integer getBmlx() {
        return bmlx;
    }

    public void setBmlx(Integer bmlx) {
        this.bmlx = bmlx;
    }

    @NotNull(message = "bmdx不能为空")
    public Integer getBmdx() {
        return bmdx;
    }

    public void setBmdx(Integer bmdx) {
        this.bmdx = bmdx;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBmsj() {
        return bmsj;
    }

    public void setBmsj(Date bmsj) {
        this.bmsj = bmsj;
    }

    @Length(min = 0, max = 50, message = "zwbh长度必须介于 0 和 50 之间")
    public String getZwbh() {
        return zwbh;
    }

    public void setZwbh(String zwbh) {
        this.zwbh = zwbh;
    }

    @Length(min = 0, max = 50, message = "lxdh长度必须介于 0 和 50 之间")
    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    @Length(min = 0, max = 50, message = "lxr长度必须介于 0 和 50 之间")
    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    @Length(min = 0, max = 100, message = "qymc长度必须介于 0 和 100 之间")
    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    @Length(min = 0, max = 50, message = "qysh长度必须介于 0 和 50 之间")
    public String getQysh() {
        return qysh;
    }

    public void setQysh(String qysh) {
        this.qysh = qysh;
    }

    @Length(min = 0, max = 200, message = "xjhsqly长度必须介于 0 和 200 之间")
    public String getXjhsqly() {
        return xjhsqly;
    }

    public void setXjhsqly(String xjhsqly) {
        this.xjhsqly = xjhsqly;
    }

    public Integer getSfxz() {
        return sfxz;
    }

    public void setSfxz(Integer sfxz) {
        this.sfxz = sfxz;
    }

    @Length(min = 0, max = 100, message = "xjsj长度必须介于 0 和 100 之间")
    public String getXjsj() {
        return xjsj;
    }

    public void setXjsj(String xjsj) {
        this.xjsj = xjsj;
    }

    public Integer getSftz() {
        return sftz;
    }

    public void setSftz(Integer sftz) {
        this.sftz = sftz;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTzsj() {
        return tzsj;
    }

    public void setTzsj(Date tzsj) {
        this.tzsj = tzsj;
    }

    @Length(min = 0, max = 20, message = "jkr长度必须介于 0 和 20 之间")
    public String getJkr() {
        return jkr;
    }

    public void setJkr(String jkr) {
        this.jkr = jkr;
    }

    @Length(min = 0, max = 1000, message = "jkrjs长度必须介于 0 和 1000 之间")
    public String getJkrjs() {
        return jkrjs;
    }

    public void setJkrjs(String jkrjs) {
        this.jkrjs = jkrjs;
    }

    @Length(min = 0, max = 1000, message = "memo长度必须介于 0 和 1000 之间")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private BckjBizQyxx qyxx;

    public BckjBizQyxx getQyxx() {
        return qyxx;
    }

    public void setQyxx(BckjBizQyxx qyxx) {
        this.qyxx = qyxx;
    }

    private List<Map> zwList;

    public void setZwList(List<Map> zwList) {
        this.zwList = zwList;
    }

    public List<Map> getZwList() {
        return zwList;
    }
}