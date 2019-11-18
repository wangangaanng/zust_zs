/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.config.Global;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.common.SwytConstant;
import com.zghzbckj.manage.dao.BckjBizBmDao;
import com.zghzbckj.manage.dao.BckjBizBmmxDao;
import com.zghzbckj.manage.dao.BckjBizJbxxDao;
import com.zghzbckj.manage.entity.*;
import com.zghzbckj.manage.utils.Html2PdfUtil;
import com.zghzbckj.manage.utils.MailUtils;
import com.zghzbckj.manage.utils.TemplateUtils;
import com.zghzbckj.manage.web.MessageUtil;
import com.zghzbckj.util.ExcelUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    BckjBizXxpzService bckjBizXxpzService;
    @Autowired
    BckjBizJbxxService bckjBizJbxxService;
    @Autowired
    BckjBizBmmxDao bmmxDao;
    @Autowired
    BckjBizBmDao bmDao;
    @Autowired
    BckjBizJbxxDao jbxxDao;

    @Override
    public BckjBizBm get(String owid) {
        BckjBizBm bm = super.get(owid);
        Map params = Maps.newHashMap();
        params.put("fileClassId", bm.getUserRefOwid());
        Integer number = this.dao.queryFileNumber(params);
        bm.setJsfj(number.toString() + "个文件");
        return bm;
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
    public PageInfo<BckjBizBm> findPageBckjBizBm(List<FilterModel> filters, Integer state, Integer pageNo, Integer pageSize) {
        Integer codtionState = null;


        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!TextUtils.isEmpty(dataMap.get("state"))) {
            codtionState = Integer.parseInt(dataMap.get("state").toString());
        }
        if (!TextUtils.isEmpty(state) && 9 != state) {
            dataMap.put("state", state);
        }
        else {
            dataMap.put("cj", 1);
        }

        if (!TextUtils.isEmpty(codtionState)) {
            dataMap.put("state", codtionState);
        }
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("sqsj2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("sqsj2").toString(), 1);
            dataMap.put("sqsj2", date);
        }
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("jfsj2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("jfsj2").toString(), 1);
            dataMap.put("jfsj2", date);
        }
        String nf = DateUtil.getDateStr("yyyy");
        dataMap.put("bmnd", nf);
        PageInfo<BckjBizBm> page = findPageWithGrade(dataMap, pageNo, pageSize, " a.sqsj desc ");

        List<BckjBizBm> records = page.getRecords();
        BckjBizBm bm = new BckjBizBm();
        bm.setXm("共有：" + page.getTotalCount() + "条报名信息");
        bm.setReadOnly(true);
        records.add(0, bm);

        return page;
    }


    public PageInfo<BckjBizBm> getHistoryList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Integer codtionState = null;
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("sqsj2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("sqsj2").toString(), 1);
            dataMap.put("sqsj2", date);
        }
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("jfsj2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("jfsj2").toString(), 1);
            dataMap.put("jfsj2", date);
        }
        PageInfo<BckjBizBm> page = findPageWithGrade(dataMap, pageNo, pageSize, " a.sqsj desc ");
        List<BckjBizBm> records = page.getRecords();
        BckjBizBm bm = new BckjBizBm();
        bm.setXm("共有：" + page.getTotalCount() + "条报名信息");
        bm.setState(null);
        bm.setReadOnly(true);
        records.add(0, bm);

        return page;
    }


    private PageInfo<BckjBizBm> findPageWithGrade(Map<String, Object> paramsMap, Integer pageNo, Integer pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }

        List<BckjBizBm> bmList = this.dao.findListByMap(paramsMap);
        List<Map> mapList = new ArrayList<>();
        if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
            for (BckjBizBm bm : bmList) {
                Map bmMap = JackSonJsonUtils.objectToMap(bm);
                Map params = Maps.newHashMap();
                params.put("bmRefOwid", bm.getOwid());
                params.put("orderBy", "a.mxsx");
                params.put("lx", "0");
                List<BckjBizBmmx> mxList = bmmxDao.findListByMap(params);
                if (mxList != null && mxList.size() > 0) {
                    for (BckjBizBmmx mx : mxList) {
                        String mxmc = mx.getMxmc();
                        bmMap.put(mxmc, mx.getMxnr());
                    }
                }
                params.put("lx", SwytConstant.BMMX_LX_ZH);
                mxList = bmmxDao.findListByMap(params);
                if (mxList != null && mxList.size() > 0) {
                    for (BckjBizBmmx mx : mxList) {
                        String mxmc = mx.getMxmc();
                        bmMap.put(mxmc, mx.getMxnr());
                    }
                }
                bmMap.put("owid", bm.getOwid());
                bmMap.put("state", bm.getState());
                mapList.add(bmMap);
                params.put("lx", SwytConstant.BMMX_LX_XK);
                mxList = bmmxDao.findListByMap(params);
                int i = 1;
                if (mxList != null && mxList.size() > 0) {
                    for (BckjBizBmmx mx : mxList) {
                        String mxmc = mx.getMxmc();
                        String mxnr = mx.getMxnr();
                        if (1 == i) {
                            bmMap.put("xkcj1", mxmc + ":" + mxnr);
                        }
                        if (2 == i) {
                            bmMap.put("xkcj2", mxmc + ":" + mxnr);
                        }
                        if (3 == i) {
                            bmMap.put("xkcj3", mxmc + ":" + mxnr);
                        }
                        i++;
                    }
                }
            }
        }

        page.setList(mapList);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;

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
        doCheckSubTime(MapUtils.getString(mapData, "xxbh"));
        String bmnd = DateUtil.getCurrentDate(CommonConstant.DATE_FROMART).substring(0, 4);
        mapData.put("bmnd", bmnd);
        BckjBizBm bmParam = JsonUtil.map2Bean(mapData, BckjBizBm.class);
        BckjBizBm bm = this.dao.getOneByMap(mapData);
        if (null == bm) {
            bm = bmParam;
            bm.setRzbd(0);
            bm.setSqsj(new Date());
            bm.setState(0);
            bm.setBmnd(bmnd);
            bm.setXybnr(SwytConstant.BMXZQZ);
            saveOrUpdate(bm);
        } else {
            if (bm.getState() > 0) {
                throw CustomerException.newInstances("此报名已提交，不能修改");
            }
            bmParam.setState(bm.getState());
            BeanUtil.copyPropertiesIgnoreNull(bmParam, bm);
        }
        BckjBizBkzy zy = bckjBizBkzyService.get(Long.valueOf(MapUtils.getInt(mapData, "zyOwid")));
        bm.setBkzyRefOwid(Long.valueOf(zy.getOwid()));
        bm.setXzzylj(zy.getPath());
        bm.setXzzymc(zy.getName());
        Map param = Maps.newHashMap();
        param.put("yhRefOwid", bm.getUserRefOwid());
        BckjBizJbxx jbxx = bckjBizJbxxService.getInfo(param);
        BeanUtil.copyBean(jbxx, bm, "xm", "sfzh", "xb", "tcah", "qq", "yx", "mz", "wyyz",
                "wycj", "lxdh", "jtzz", "zxlb", "jssm", "qtqk", "yzmc");
        applyCjxx(bm.getOwid(), param);
        saveOrUpdate(bm);
        return bm.getOwid();
    }

    /**
     * 查找报名状态
     *
     * @param xxbh
     * @throws CustomerException
     */
    private void doCheckSubTime(String xxbh) throws CustomerException {
        int bmzt = bckjBizXxpzService.getBmState(xxbh);
        if (bmzt != 1) {
            throw CustomerException.newInstances("当前时间未开放报名");
        }
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
            if (one.getLx() == 1) {
                bmmx.setMxnr(String.valueOf(one.getKmcj()));
            } else {
                bmmx.setMxnr(String.valueOf(one.getKmdj()));
            }
            bmmx.setMxsx(one.getXssx());
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
        saveOrUpdate(bm);
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
        BckjBizBm bm = this.dao.getOneByMap(mapData);
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
            throw CustomerException.newInstances("报名信息为空");
        }
        doCheckSubTime(bm.getXxbh());
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
        bm.setJfsj(DateUtil.getDate(MapUtils.getString(mapData, "jfsj"), CommonConstant.DATE_FROMART));
        bm.setState(6);
        bm.setJjly(null);
        bm.setXybnr(SwytConstant.BMDDQR);
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
    public String getApply(Map<String, Object> mapData) throws IOException, CustomerException {
        BckjBizBm bm = getBmxx(mapData);
        if (null == bm) {
            throw CustomerException.newInstances("报名表不存在");
        }
        String fileName = bm.getOwid() + File.separator + SwytConstant.SWTYSQB;

        if (bm.getState() > 0) {
            return SwytConstant.SWTYFILEPATH + File.separator + fileName;
        }
        String[] bmStrs = {"xklb", "wyyz", "bklb", "xzzymc",
                "xm", "xbStr", "qq", "mz", "jtzz", "yx", "sfzh", "lxdh",
                "wycj", "zxlb", "jssm", "qtqk", "tcah", "yzmc", "bmnd"};
        Map datas = BeanUtil.obj2Map(bm, bmStrs);
        Map paramCjxx = Maps.newConcurrentMap();
        paramCjxx.put("yhRefOwid", bm.getUserRefOwid());
        paramCjxx.put("lx", "0");
        List<BckjBizCjxx> hkList = bckjBizCjxxService.findListByParams(paramCjxx, SwytConstant.ORDERBY_NAME);
        paramCjxx.put("lx", 1);
        List<BckjBizCjxx> xkList = bckjBizCjxxService.findListByParams(paramCjxx, SwytConstant.ORDERBY_NAME);
        paramCjxx.put("lx", 2);
        List<BckjBizCjxx> zcList = bckjBizCjxxService.findListByParams(paramCjxx, SwytConstant.ORDERBY_NAME);
        datas.put("hkList", hkList);
        datas.put("xkList", xkList);
        datas.put("zcList", zcList);
        String saveFilePath = Global.getConfig(SwytConstant.SWTYFILEPATH) + fileName;
        String htmlData = TemplateUtils.freeMarkerContent(datas, "apcationForm");
        if (TextUtils.isEmpty(htmlData)) {
            throw CustomerException.newInstances("生成报名表失败");
        }
        Html2PdfUtil.createPdf(htmlData, saveFilePath);
        return SwytConstant.SWTYFILEPATH + File.separator + fileName;
    }


    /**
     * <p>方法:sendApplyEmail TODO发送面试单</p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/27 12:22  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public boolean sendApplyEmail(Map<String, Object> mapData) throws CustomerException {
        String owid = MapUtils.getString(mapData, "applyOwid");
        BckjBizBm bm = get(owid);
        if (null == bm) {
            throw CustomerException.newInstances("报名表不存在");
        }
        doCheckSubTime(bm.getXxbh());
        if (bm.getState() == 1) {
            bm.setState(2);
            bm.setXybnr(SwytConstant.BMPZSC);
            saveOrUpdate(bm);
        }
        String saveFilePath = Global.getConfig(SwytConstant.SWTYFILEPATH) + owid + File.separator + SwytConstant.SWTYSQB;
        String cns = Global.getConfig(SwytConstant.SWTYFILEPATH) + SwytConstant.SWTYCNS;
        String email = MapUtils.getString(mapData, "yx");
        Map value = Maps.newHashMap();
        value.put("to", email);
        value.put("subject", "浙江科技学院三位一体综合评价招生申请表");
        Map mapsDic = CacheUtil.getVal("swyt10025", Map.class);
        String mmss = MapUtils.getString(mapsDic, "dicVal6");
        value.put("content", mmss);
        List<String> fileList = Lists.newArrayList();
        fileList.add(cns);
        fileList.add(saveFilePath);
        MailUtils.sendMails(fileList, value);
        return Boolean.TRUE;
    }

    /**
     * <p>方法:sendView TODO </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return boolean  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/27 14:17  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public boolean sendView(Map<String, Object> mapData) throws CustomerException {
        String owid = MapUtils.getString(mapData, "applyOwid");
        BckjBizBm bm = get(owid);
        if (null == bm) {
            throw CustomerException.newInstances("报名表不存在");
        }

        doCheckSubTime(bm.getXxbh());
        if (bm.getState() == 8) {
            bm.setState(9);
            bm.setXybnr(SwytConstant.BMCJCX);
            saveOrUpdate(bm);
        }
        String view = Global.getConfig(SwytConstant.SWTYFILEPATH) + owid + File.separator + SwytConstant.SWTYMSTZD;
        if (!MailUtils.fileIsExist(view)) {
            throw CustomerException.newInstances("面试通知单文件尚未生成");
        }
        String email = MapUtils.getString(mapData, "yx");
        Map value = Maps.newHashMap();
        value.put("to", email);
        value.put("subject", "浙江科技学院三位一体综合测试通知单");
        Map mapsDic = CacheUtil.getVal("swyt10025", Map.class);
        String mmss = MapUtils.getString(mapsDic, "dicVal7");
        value.put("content", mmss);
        List<String> fileList = Lists.newArrayList();
        fileList.add(view);
        MailUtils.sendMails(fileList, value);
        return Boolean.TRUE;
    }

    /**
     * <p>方法:getNotice TODO </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.lang.String  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/27 21:03  </li>
     * </ul>
     */
    public String getNotice(Map<String, Object> mapData) throws IOException {
        BckjBizBm bm = getBmxx(mapData);
        if (null == bm) {
            throw CustomerException.newInstances("报名表不存在");
        }
//        String mmss="4月21日上午9:00-17:00到闻理院A4-122报到，国际交流类（包括设计学类中德联合培养）考生\n" +
//                "18:00进行外语能力测试， 4月22日上午考生8:00之前，下午场考生12:00之前在A4-122报到，统一\n" +
//                "到候考区抽签分组，进行面试。";//
        if (TextUtils.isEmpty(bm.getZkzh())) {
            throw CustomerException.newInstances("准考证号生成中，请稍后再试");
        }
        Map mapsDic = CacheUtil.getVal("swyt10025", Map.class);
        String mmss = MapUtils.getString(mapsDic, "memo");
        bm.setMssm(mmss);
        String view = MapUtils.getString(mapData, "applyOwid") + File.separator + SwytConstant.SWTYMSTZD;

        String htmlData = TemplateUtils.freeMarkerContent(bm, "ap");
        if (TextUtils.isEmpty(htmlData)) {
            throw CustomerException.newInstances("生成面试单失败");
        }
        Html2PdfUtil.createPdf(htmlData, Global.getConfig(SwytConstant.SWTYFILEPATH) + view);
        return SwytConstant.SWTYFILEPATH + File.separator + view;
    }

    @Transactional(readOnly = false)
    public Map submitPurchaseBack(List<String> codes, Integer state, Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizBm bm = get(codes.get(0));
        if (TextUtils.isEmpty(bm)) {
            resultMap.put("result", "false");
            resultMap.put("msg", "无报名表");
            return resultMap;
        }
        String lxdh = bm.getLxdh();
        if (4 == state) {
            bm.setXybnr(SwytConstant.BMJJ);
        } else if (5 == state) {
            bm.setXybnr(SwytConstant.BMDJF);
            String content = SwytConstant.PASS_MESS;
            try {
                MessageUtil.sendMessage(lxdh, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (-1 == state) {
            bm.setXybnr(SwytConstant.CXTJBMSQ);
        } else if (7 == state) {
            bm.setXybnr(SwytConstant.BMDMSFP);
            String content = SwytConstant.MONEY_PASS_MESS;
            lxdh = bm.getLxdh();
            try {
                MessageUtil.sendMessage(lxdh, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bm.setState(state);
        saveOrUpdate(bm);
        resultMap = new HashMap<>(2);
        resultMap.put("result", "true");
        List<Object> _list = new ArrayList();
        _list.add(bm);
        resultMap.put("bean", _list);
        return resultMap;
    }

    public List<Map> listDicByType(Integer hkcj) {
        Map params = Maps.newHashMap();
        params.put("type", hkcj);
        params.put("orderBy", " CAST(a.dic_val4 AS SIGNED)");
        List<Map> results = this.dao.listDicByType(params);
        return results;

    }


    @Transactional(readOnly = false)
    public void genZkz() {
        Map params = Maps.newHashMap();
        params.put("state", 8);
        params.put("orderBy", " a.bklb_owid,a.mssj");
        List<BckjBizBm> bmList = this.dao.findListByMap(params);
        int zybh;//专业编号
        String _zybh = "";
        int xh = 1;
        String _xh = "";
        String nf = DateUtil.getDateStr("yyyy");
        nf = nf.substring(2, 4);
        if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
            for (BckjBizBm bm : bmList) {
                _xh = getFixLen(xh, 4);
                _xh = nf + getFixLen(bm.getBklbOwid(),3) + _xh;
                xh++;
                bm.setZkzh(_xh);
                saveOrUpdate(bm);

                String content = SwytConstant.ZKZH_MESS;
                String lxdh = bm.getLxdh();
                try {
                    MessageUtil.sendMessage(lxdh, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private String getFixLen(int xh, int len) {
        String _xh = xh + "";
        int _len = len - _xh.length();
        for (int index = 0; index < _len; index++) {
            _xh = "0" + _xh;
        }
        return _xh;

    }

    /**
     * 读取excel
     *
     * @param filename
     * @return
     */
    public static List<List<String>> getExcelLists(String filename) {
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        return list;
    }

    @Transactional(readOnly = false)
    public ResponseMessage recordCjInfo(String path) {
        Map params = Maps.newHashMap();
        //文件路径
        String filename = path;
        List<List<String>> list = getExcelLists(filename);
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //成绩信息录入
                List<String> cellList = list.get(i);//行循环
                String xm = cellList.get(0); //姓名
                String zkzh = cellList.get(1); //准考证号
                String mscj = cellList.get(2); //面试成绩
                String bscj = cellList.get(3); //笔试成绩
                String zzcj = cellList.get(4); //最终成绩
                if (TextUtils.isEmpty(xm) || TextUtils.isEmpty(zkzh)) {
                    continue;
                }
                params.clear();
                params.put("xm", xm);
                params.put("zkzh", zkzh);
                BckjBizBm bm = bmDao.getOneByMap(params);
                if (!TextUtils.isEmpty(bm)) {
                    bm.setMscj(mscj);
                    bm.setBscj(bscj);
                    bm.setZzcj(zzcj);
                    bm.setState(10);
                    bm.setXybnr(SwytConstant.BMCJCX);
                    bm.setLrsj(new Date());
                    saveOrUpdate(bm);
                }

            }

        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public Integer todoMan(Map<String, Object> dataMap) {
        Integer state = Integer.parseInt(dataMap.get("state").toString());
        Integer toManNumber = 0;
        if (3 == state || 7 == state) {
            toManNumber = this.dao.todoMan(dataMap);
        } else {
            dataMap.clear();
            toManNumber = jbxxDao.countNumber(dataMap);
        }
        return toManNumber;
    }

    public Map<String, Object> bmPie(Map<String, Object> dataMap) {
        Integer type = Integer.parseInt(dataMap.get("type").toString());
        if (1 == type) {
            dataMap.put("groupBy", "xklb");
        }
        if (2 == type) {
            dataMap.put("groupBy", "bklb");
        }
        if (3 == type) {
            dataMap.put("groupBy", "xzzymc");
        }
        List<Map<String, Object>> bmList = bmDao.getBmNumber(dataMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> objectMap = Maps.newHashMap();
        Map<String, Object> params = Maps.newHashMap();
        int total = 0;
        for (Map<String, Object> bm : bmList) {
            objectMap = Maps.newHashMap();
            if (1 == type) {
                objectMap.put("name", MapUtils.getString(bm, "xklb"));
            }
            if (2 == type) {
                objectMap.put("name", MapUtils.getString(bm, "bklb"));
            }
            if (3 == type) {
                objectMap.put("name", MapUtils.getString(bm, "xzzymc"));
            }
            objectMap.put("value", MapUtils.getInt(bm, "value"));
            total += MapUtils.getInt(bm, "value");
            resultList.add(objectMap);
        }
        //饼图数据
        result.put("pieData", resultList);
        //总数
        result.put("total", total);
        //涉及行业
        result.put("lb", bmList.size());
        return result;
    }
}