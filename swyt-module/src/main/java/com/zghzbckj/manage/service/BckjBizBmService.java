/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.itextpdf.text.DocumentException;
import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.config.Global;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.common.SwytConstant;
import com.zghzbckj.manage.dao.BckjBizBmDao;
import com.zghzbckj.manage.entity.*;
import com.zghzbckj.manage.utils.JavaToPdfHtmlFreeMarker;
import com.zghzbckj.manage.utils.MailUtils;
import com.zghzbckj.manage.utils.TemplateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class BckjBizBmService extends CrudService<BckjBizBmDao, BckjBizBm> {

    private static final Logger log = Logger.getLogger(BckjBizBmService.class);

    @Autowired
    BckjBizBkzyService bckjBizBkzyService;
    @Autowired
    BckjBizBmmxService bckjBizBmmxService;
    @Autowired
    BckjBizCjxxService bckjBizCjxxService;

    @Autowired
    BckjBizJbxxService bckjBizJbxxService;

    @Override
    public BckjBizBm get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizBm> findList(BckjBizBm bckjBizBm) {
        return super.findList(bckjBizBm);
    }

    @Override
    public PageInfo<BckjBizBm> findPage(Page<BckjBizBm> page, BckjBizBm bckjBizBm) {
        return super.findPage(page, bckjBizBm);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizBm bckjBizBm) {
        super.saveOrUpdate(bckjBizBm);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizBm bckjBizBm) {
        super.delete(bckjBizBm);
    }


    /**
     * <p>方法:findPagebckjBizBm TODO后台BckjBizBm分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.PageInfo  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public PageInfo<BckjBizBm> findPageBckjBizBm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizBm> page = findPage(dataMap, pageNo, pageSize, null);
        return page;
    }

    /**
     * <p>方法:savebckjBizBm TODO保存BckjBizBm信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizBm  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public BckjBizBm saveBckjBizBm(Map<String, Object> mapData) {
        BckjBizBm bckjBizBm = JsonUtil.map2Bean(mapData, BckjBizBm.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizBm bckjBizBmIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizBm, bckjBizBmIndata);
            bckjBizBm = bckjBizBmIndata;
        }
        saveOrUpdate(bckjBizBm);
        return bckjBizBm;
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizBm </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return java.util.List  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = Lists.newArrayList();
        for (String owid : codes) {
            Map<String, Object> params = Maps.newHashMap();
            BckjBizBm bckjBizBm = new BckjBizBm();
            bckjBizBm.setOwid(owid);
            this.dao.delete(bckjBizBm);
            params.put("owid", owid);
            objs.add(params);
        }
        return objs;
    }

    /**
     * <p>方法:submit TODO提交专业报名 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return boolean  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 10:11  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public String submit(Map<String, Object> mapData) throws Exception {
        String bmnd = DateUtil.getCurrentDate(CommonConstant.DATE_FROMART).substring(0, 4);
        mapData.put("bmnd", bmnd);
        BckjBizBm bmParam = JsonUtil.map2Bean(mapData, BckjBizBm.class);
        BckjBizBm bm = this.dao.getOneByMap(mapData);
        if (null == bm) {
            bm = bmParam;
            bm.setSqsj(new Date());
            bm.setState(0);
            bm.setBmnd(bmnd);
            bm.setXybnr(SwytConstant.BMXZQZ);
        } else {
            BeanUtil.copyPropertiesIgnoreNull(bmParam, bm);
        }
        BckjBizBkzy zy = bckjBizBkzyService.get(Long.valueOf(MapUtils.getInt(mapData, "zyOwid")));
        bm.setBkzyRefOwid(zy.getOwid());
        bm.setXzzylj(zy.getPath());
        bm.setXzzymc(zy.getName());
        Map param = Maps.newHashMap();
        param.put("yhRefOwid", bm.getUserRefOwid());
        BckjBizJbxx jbxx = bckjBizJbxxService.getInfo(param);
        BeanUtil.copyBean(jbxx, bm, "xm", "sfzh", "xb", "tcah", "qq", "yx", "mz", "wyyz",
                "wycj", "lxdh", "jtzz", "zxlb", "jssm", "qtqk");
        applyCjxx(bm.getOwid(), param);
        saveOrUpdate(bm);
        return bm.getOwid();
    }

    /**
     * @param bmOwid
     * @param param  带有参数为yhRefOwid
     */
    private void applyCjxx(String bmOwid, Map param) {
        List<BckjBizCjxx> cjxx = bckjBizCjxxService.findListByParams(param, CommonConstants.EMPTY_STR);
        param.put("bmRefOwid", bmOwid);
        bckjBizBmmxService.deleteByMap(param);
        for (BckjBizCjxx one : cjxx) {
            BckjBizBmmx bmmx = new BckjBizBmmx();
            bmmx.setBmRefOwid(bmOwid);
            bmmx.setLx(one.getLx());
            bmmx.setMxsx(one.getXssx());
            bmmx.setMxnr(String.valueOf(one.getKmcj()));
            bmmx.setMxmc(one.getKmmc());
            bckjBizBmmxService.saveOrUpdate(bmmx);
        }
    }

    /**
     * <p>方法:confirmApply TODO失败 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return boolean  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 13:51  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public boolean confirmApply(Map<String, Object> mapData) throws Exception {
        BckjBizBm bm = getBmxx(mapData);
        //报名表确认
        bm.setState(1);
        bm.setXybnr(SwytConstant.BMXZ);
        return Boolean.TRUE;
    }


    /**
     * <p>方法:getResult TODO查报名表所有信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizBm  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 15:45  </li>
     * </ul>
     */
    public BckjBizBm getResult(Map<String, Object> mapData) throws CustomerException {
        BckjBizBm bm = getBmxx(mapData);
        return bm;
    }

    /**
     * 查申请表
     *
     * @param mapData applyOwid
     * @return
     * @throws CustomerException
     */
    private BckjBizBm getBmxx(Map<String, Object> mapData) throws CustomerException {
        BckjBizBm bm = this.dao.getOneByMap(mapData);
        if (null == bm) {
            throw CustomerException.newInstances("无报名信息");
        }
        return bm;
    }

    /**
     * <p>方法:promise TODO承诺书和报名表提交</p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return boolean  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 15:48  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public boolean promise(Map<String, Object> mapData) throws CustomerException {
        BckjBizBm bm = getBmxx(mapData);
        bm.setCnszp(MapUtils.getString(mapData, "cnszp"));
        bm.setBmbZp(MapUtils.getString(mapData, "bmbZp"));
        bm.setState(3);
        bm.setXybnr(SwytConstant.BMDSH);
        saveOrUpdate(bm);
        return Boolean.TRUE;
    }

    /**
     * <p>方法:submitJft TODO缴费凭证提交 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 16:17  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public Object submitJft(Map<String, Object> mapData) {
        BckjBizBm bm = getBmxx(mapData);
        bm.setJfpzZp(MapUtils.getString(mapData, "jfpzZp"));
        saveOrUpdate(bm);
        return Boolean.TRUE;
    }

    /**
     * <p>方法:getApply TODO </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/26 15:53  </li>
     * </ul>
     */
    public String getApply(Map<String, Object> mapData) throws IOException, DocumentException {
        BckjBizBm bm = getBmxx(mapData);
        String[] bmStrs = {"xklb", "wyyz", "bklb", "xzzymc",
                "xm", "xbStr", "qq", "mz", "jtzz", "yx", "sfzh", "lxdh",
                "wycj", "zxlb", "jssm", "qtqk", "tcah"};
        Map datas = BeanUtil.obj2Map(bm, bmStrs);
        Map paramCjxx = Maps.newConcurrentMap();
        paramCjxx.put("yhRefOwid", bm.getUserRefOwid());
        paramCjxx.put("lx","0");
        List<BckjBizCjxx> hkList = bckjBizCjxxService.findListByParams(paramCjxx, SwytConstant.ORDERBY_NAME);
        paramCjxx.put("lx",1);
        List<BckjBizCjxx> xkList = bckjBizCjxxService.findListByParams(paramCjxx,SwytConstant.ORDERBY_NAME);
        paramCjxx.put("lx",2);
        List<BckjBizCjxx> zcList = bckjBizCjxxService.findListByParams(paramCjxx,SwytConstant.ORDERBY_NAME);
        datas.put("hkList",hkList);
        datas.put("xkList",xkList);
        datas.put("zcList",zcList);
        String fileName = bm.getOwid() + SwytConstant.GENAL_PDF_FILE;
        String saveFilePath = Global.getConfig(SwytConstant.SWTYFILEPATH) + fileName;
        String htmlData = TemplateUtils.freeMarkerContent(datas, "applicationForm");
        JavaToPdfHtmlFreeMarker.createPdf(htmlData, saveFilePath);
        return SwytConstant.SWTYFILEPATH + File.separator + fileName;
    }


    /**
    *<p>方法:sendApplyEmail TODO发送面试单</p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return java.lang.Object  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/27 12:22  </li>
    *</ul>
    */
    public boolean sendApplyEmail(Map<String, Object> mapData) {

        String saveFilePath = Global.getConfig(SwytConstant.SWTYFILEPATH) +  MapUtils.getString(mapData,"applyOwid")+File.separator+SwytConstant.SWTYSQB;
        String cns = Global.getConfig(SwytConstant.SWTYFILEPATH) +SwytConstant.SWTYCNS;
        String email=MapUtils.getString(mapData,"yx");
        Map value= Maps.newHashMap();
        value.put("to",email);
        value.put("subject","浙江科技学院三位一体综合评价招生申请表");
        value.put("content","");
        List<String> fileList= Lists.newArrayList();
        fileList.add(cns);
        fileList.add(saveFilePath);
        MailUtils.sendMails(fileList,value);
        return Boolean.TRUE;
    }

    /**
    *<p>方法:sendView TODO </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return boolean  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/27 14:17  </li>
    *</ul>
    */
    public boolean sendView(Map<String, Object> mapData) {
        String view = Global.getConfig(SwytConstant.SWTYFILEPATH) +SwytConstant.SWTYMSTZD;
        String email=MapUtils.getString(mapData,"yx");
        Map value= Maps.newHashMap();
        value.put("to",email);
        value.put("subject","浙江科技学院三位一体综合评价招生综合测试通知单");
        value.put("content","");
        List<String> fileList= Lists.newArrayList();
        fileList.add(view);
        MailUtils.sendMails(fileList,value);
        return Boolean.TRUE;
    }
}