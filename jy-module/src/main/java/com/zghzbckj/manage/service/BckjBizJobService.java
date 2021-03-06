/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.feign.BckjBizYhkzSer;
import com.zghzbckj.manage.dao.BckjBizJobDao;
import com.zghzbckj.manage.dao.BckjBizJybmDao;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;
import com.zghzbckj.manage.dao.BckjBizXsgzDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.entity.BckjBizXsgz;
import com.zghzbckj.util.PageUtils;
import com.zghzbckj.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJobService extends CrudService<BckjBizJobDao, BckjBizJob> {

    private static final Logger log = Logger.getLogger(BckjBizJobService.class);
    @Autowired
    BckjBizXsgzDao xsgzDao;
    @Autowired
    BckjBizQyxxDao qyxxDao;
    @Autowired
    BckjBizJybmService bmService;
    @Autowired
    BckjBizJybmDao bmDao;
    @Autowired
    BckjBizQyxxService qyxxService;
    @Autowired
    BckjBizXsgzService xsgzService;
    @Autowired
    BckjBizYhkzSer keyFilter;


    @Override
    public BckjBizJob get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizJob> findList(BckjBizJob bckjBizJob) {
        return super.findList(bckjBizJob);
    }

    @Override
    public PageInfo<BckjBizJob> findPage(Page<BckjBizJob> page, BckjBizJob bckjBizJob) {
        return super.findPage(page, bckjBizJob);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizJob bckjBizJob) {
        super.saveOrUpdate(bckjBizJob);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizJob bckjBizJob) {
        super.delete(bckjBizJob);
    }

    @Autowired
    BckjBizXsgzService bckjBizXsgzService;


    /**
     * <p>方法:findPagebckjBizJob TODO后台BckjBizJob分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizJob(List<FilterModel> filters, Integer zwlx, Integer pageNo, Integer pageSize) {
        PageInfo<BckjBizJob> page = new PageInfo<>();
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("createtime2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("createtime2").toString(), 1);
            dataMap.put("createtime2", date);
        }
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("zphKsrq2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("zphKsrq2").toString(), 1);
            dataMap.put("zphKsrq2", date);
        }

        //职位类型 0 职位 1职来职往 2社会招聘会 3 企业招聘会 4 宣讲会
        dataMap.put("zwlx", zwlx.toString());
        if ("5".equals(zwlx.toString())) {
            dataMap.put("zwlx", "3");
            dataMap.put("ddw", "1");
        }
        if ("6".equals(zwlx.toString())) {
            dataMap.put("zwlx", "3");
            dataMap.put("wait", "1");
        } else if ("3".equals(zwlx.toString())) {
            dataMap.put("over", 1);
            dataMap.put("zwlx", "3");
        } else if ("7".equals(zwlx.toString())) {
            dataMap.put("zwlx", "3");
        }
        if (JyContant.ZWLB_ZW == zwlx) {
            page = findPageWithCompany(dataMap, pageNo, pageSize, " a.createtime desc ");
        } else {
            page = findPageWithDay(dataMap, pageNo, pageSize, " a.createtime desc ");
        }


        List<BckjBizJob> records = page.getRecords();
        BckjBizJob job = new BckjBizJob();
        job.setZwbt("共有：" + page.getTotalCount() + "条信息");
        job.setReadOnly(true);
        job.setState(null);
        records.add(0, job);
        return ResponseMessage.sendOK(page);
    }


    public PageInfo<BckjBizJob> findPageWithDay(Map<String, Object> paramsMap, int pageNo, int pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!com.ourway.base.utils.TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }
        List<BckjBizJob> jobList = this.dao.findListByMap(paramsMap);
        if (!TextUtils.isEmpty(jobList) && jobList.size() > 0) {
            for (BckjBizJob job : jobList) {
                try {
                    if (!TextUtils.isEmpty(job.getZphKsrq())) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date todayDate = DateUtil.getDate(formatter.format(new Date()), "yyyy-MM-dd");
                        Date ksrq = DateUtil.getDate(formatter.format(job.getZphKsrq()), "yyyy-MM-dd");
                        int rq = dateCompare(todayDate, ksrq);
                        if (rq <= 0) {
                            job.setRqState(1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        page.setList(jobList);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;
    }


    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }

    Integer qdAllNumber = 0;
    Integer bmAllNumber = 0;
    Integer gzAllNumber = 0;
    Integer successNumber = 0;

    public ResponseMessage findPageBckjBizJobXjh(List<FilterModel> filters, Integer state, Integer pageNo, Integer pageSize) {
        PageInfo<BckjBizJob> page = new PageInfo<>();
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        //职位类型 0 职位 1职来职往 2社会招聘会 3 企业招聘会 4 宣讲会
        dataMap.put("zwlx", JyContant.ZWLB_XJH);
        //王显弘改          -------》该开始
        if (state == 7) {
            dataMap.put("zwlx", JyContant.ZWLB_ZPH);
        }
        if (state == 8) {
            dataMap.put("zwlx", JyContant.ZWLB_JZ);
        }
        //王显弘改          -------》该结束
        //1待举办 2已举办  3报名中
        if (1 == state) {
            dataMap.put("wait", 1);
        } else if (2 == state) {
            dataMap.put("over", 1);
        } else if (3 == state) {
            dataMap.put("bm", 1);
        }
        if (4 == state) {
            dataMap.put("ddw", 1);
        }
        //      --------->>  王显弘改  开始
        if (state == 5 || state == 7 || state == 8) {
            /*dataMap.put("wait", 1);
            page = findPageWithNumber(dataMap, pageNo, pageSize, " a.exp5,a.createtime  desc ");
            List<BckjBizJob> records = page.getRecords();
            for (BckjBizJob bckjBizJob : records) {
                //设置待举办
                bckjBizJob.setState(6);
            }
            dataMap.remove("wait");
            dataMap.put("over", 1);
            PageInfo<BckjBizJob> page2 = findPageWithNumber(dataMap, pageNo, pageSize, " a.exp5,a.createtime  desc ");
            List<BckjBizJob> records2 = page2.getRecords();
            for (BckjBizJob bckjBizJob : records2) {
                //设置已举办
                bckjBizJob.setState(2);
            }
            page.setTotalCount(page.getTotalCount() + page2.getTotalCount());
            records.addAll(records2);
            page.setRecords(records);*/

            if (state == 7 || state == 5) {
                page = findPageWithNumber(dataMap, pageNo, pageSize, " a.zph_ksrq  desc ");
            } else {
                page = findPageWithNumber(dataMap, pageNo, pageSize, " a.exp5,a.createtime  desc ");
            }
            List<BckjBizJob> records = page.getRecords();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(new Date());
            for (BckjBizJob bckjBizJob : records) {
                if (bckjBizJob.getZphKsrq().equals(DateUtil.getDate(format, "yyyy-MM-dd")) || bckjBizJob.getZphKsrq().after(DateUtil.getDate(format, "yyyy-MM-dd"))) {
                    bckjBizJob.setState(2);
                } else {
                    bckjBizJob.setState(1);
                }
            }
        } else {
            //《--------------------    结束
            page = findPageWithNumber(dataMap, pageNo, pageSize, " a.exp5,a.createtime  desc ");
            //      --------->>   王显弘改  开始
        }
        //《--------------------   结束
        List<BckjBizJob> records = page.getRecords();
        BckjBizJob job = new BckjBizJob();
        job.setZwbt("共有：" + page.getTotalCount());
        job.setReadOnly(true);
        job.setState(null);
        job.setBmNumber(bmAllNumber);
        job.setQdNumber(qdAllNumber);
        job.setGzNumber(gzAllNumber);
        job.setQdSuccess(successNumber);
        //王显弘改          -------》该开始
        bmAllNumber = 0;
        qdAllNumber = 0;
        gzAllNumber = 0;
        successNumber = 0;
        //王显弘改
        records.add(0, job);
        return ResponseMessage.sendOK(page);
    }

    public PageInfo<BckjBizJob> findPageWithNumber(Map<String, Object> paramsMap, int pageNo, int pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!com.ourway.base.utils.TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }
        List<BckjBizJob> resultList = this.dao.findListByMap(paramsMap);
        if (!TextUtils.isEmpty(resultList) && resultList.size() > 0) {
            for (BckjBizJob job : resultList) {
                Map params = Maps.newHashMap();
                params.put("jobRefOwid", job.getOwid());
                params.put("bmlx", JyContant.BMLX_XS);
                Integer number = bmDao.countNumber(params);
                job.setBmNumber(number);
                bmAllNumber += number;
                //关注
                params.put("gzlx", 1);
                params.put("xxlb", 0);
                number = xsgzDao.countGzNumber(params);
                job.setGzNumber(number);
                gzAllNumber += number;
                //签到
                params.put("xxlb", 1);
                number = xsgzDao.countNumber(params);
                job.setQdNumber(number);
                qdAllNumber += number;
                //签到成功数
                params.put("state", 1);
                number = xsgzDao.countNumber(params);
                job.setQdSuccess(number);
                successNumber += number;
            }
        }
        page.setList(resultList);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());

        return pageInfo;
    }


    private PageInfo<BckjBizJob> findPageWithCompany(Map<String, Object> paramsMap, Integer pageNo, Integer pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }
        List<BckjBizJob> jobList = this.dao.findListByMap(paramsMap);
        if (!TextUtils.isEmpty(jobList) && jobList.size() > 0) {
            for (BckjBizJob job : jobList) {
                try {
                    if (3 == job.getState()) {
                        job.setRqState(3);

                    } else {
                        if (TextUtils.isEmpty(job.getZwSxsj())) {
                            job.setRqState(2);

                        } else if (!TextUtils.isEmpty(job.getZwSxsj())) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date todayDate = DateUtil.getDate(formatter.format(new Date()), "yyyy-MM-dd");
                            Date zwSxsj = DateUtil.getDate(formatter.format(job.getZwSxsj()), "yyyy-MM-dd");
                            int rq = dateCompare(todayDate, zwSxsj);
                            if (rq <= 0) {
                                job.setRqState(2);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        page.setList(jobList);
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
     * <p>方法:savebckjBizJob TODO保存BckjBizJob信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */

    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizJob(Map<String, Object> mapData, Integer zwlx) {
        for (int i = 1; i <= 5; i++) {
            if (!TextUtils.isEmpty(mapData.get("sdtj" + i))) {
                mapData.put("sdtj" + i, mapData.get("sdtj" + i).toString().replace(",", "，"));
            }
        }
        clearCompany(mapData);
        BckjBizJob bckjBizJob = JsonUtil.map2Bean(mapData, BckjBizJob.class);

        String zwbt = bckjBizJob.getZwbt();
        Map params = new HashMap<>();
        params.put("content", zwbt);
        ResponseMessage responseYhxx = keyFilter.keyFilterQuery(params);
        if (!TextUtils.isEmpty(responseYhxx.getBean())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, responseYhxx.getBean().toString());
        }

        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJob bckjBizJobIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJob, bckjBizJobIndata);
            bckjBizJob = bckjBizJobIndata;
        } else {
            bckjBizJob.setZwlx(zwlx);
            bckjBizJob.setState(JyContant.JOB_ZT_TG);
        }
        if (!TextUtils.isEmpty(mapData.get("zphKsrq"))) {
//            Date date = DateUtil.getDate(mapData.get("zphKsrq").toString(), "yyyy-MM-dd HH:mm:ss");
//            if (TextUtils.isEmpty(date)) {
//                date = DateUtil.getDate(mapData.get("zphKsrq").toString(), "yyyy-MM-dd");
//            }
            Date date = DateUtil.getDate(mapData.get("zphKsrq").toString(), "yyyy-MM-dd");
            bckjBizJob.setZphKsrq(date);
        }
        if (JyContant.ZWLB_ZW == zwlx) {
            bckjBizJob.setSfbm(1);
            if (!TextUtils.isEmpty(mapData.get("exp1"))) {
                Map temp = new HashMap<>(1);
                temp.put("qymc", mapData.get("exp1").toString());
                BckjBizQyxx qyxx = qyxxDao.getOne(temp);
                if (TextUtils.isEmpty(qyxx)) {
                    qyxx = new BckjBizQyxx();
                    qyxx.setQymc(mapData.get("exp1").toString());
                    qyxx.setState(JyContant.QY_ZT_TG);
                    qyxxService.saveOrUpdate(qyxx);
                }
                bckjBizJob.setQyxxRefOwid(qyxx.getOwid());
            }
        }
        if (JyContant.ZWLB_XJH == zwlx) {
            params = Maps.newHashMap();
            params.put("jobRefOwid", bckjBizJob.getOwid());
            params.put("bmlx", 0);
            List<BckjBizJybm> jybmList = bmDao.findListByMap(params);
            if (!TextUtils.isEmpty(jybmList) && jybmList.size() > 0) {
                for (BckjBizJybm jybm : jybmList) {
                    jybm.setXjsj(DateUtil.getDateString(bckjBizJob.getZphKsrq(), "yyyy-MM-dd"));
                    bmService.saveOrUpdate(jybm);
                }
            }
        }
        //待定位
        if (!TextUtils.isEmpty(mapData.get("zphSfqd"))) {
            if ("1".equals(mapData.get("zphSfqd")) && TextUtils.isEmpty(bckjBizJob.getZphGpsjd())) {
                bckjBizJob.setExp5("1");
            }
        }
        saveOrUpdate(bckjBizJob);
        if (!com.ourway.base.utils.TextUtils.isEmpty(mapData.get("fileExtId"))) {
            mapData.put("articleOwid", bckjBizJob.getOwid());
            qyxxDao.updateFile(mapData);
        }

        return ResponseMessage.sendOK(bckjBizJob);
    }

    private void clearCompany(Map<String, Object> mapData) {
        mapData.remove("qyxx.qymc");
        mapData.remove("qyxx.qydz");
        mapData.remove("qyxx.qyYyzzzp");
        mapData.remove("qyxx.qyTysh");
        mapData.remove("qyxx.qyZczj");
        mapData.remove("qyxx.qyLxr");
        mapData.remove("qyxx.qyLxrdh");
        mapData.remove("qyxx.qyProv");
        mapData.remove("qyxx.qyCity");
        mapData.remove("qyxx.qyArea");
        mapData.remove("qyxx.qyGsjs");
        mapData.remove("qyHylb");
        mapData.remove("qyGsxz");
        mapData.remove("qyGsgm");
        mapData.remove("qyxx.qyZczj");

    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizJob </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : codes) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            BckjBizJob bckjBizJob = new BckjBizJob();
            bckjBizJob.setOwid(owid);
            this.dao.delete(bckjBizJob);
            params.put("owid", owid);
            objs.add(params);

            Map temp = new HashMap<>();
            temp.put("jobRefOwid", owid);
            List<BckjBizJybm> bmList = bmService.findListByParams(temp, "");
            if (bmList != null && bmList.size() > 0) {
                for (BckjBizJybm bm : bmList) {
                    bmService.delete(bm);
                }
            }
            List<BckjBizXsgz> gzList = xsgzService.findListByParams(temp, "");
            if (gzList != null && gzList.size() > 0) {
                for (BckjBizXsgz gz : gzList) {
                    xsgzService.delete(gz);
                }
            }
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public Map addOneJob(Map<String, Object> mapData) {

        Map resultMap = new HashMap<>(2);
        //判断关键字
        String zwbt = mapData.get("zwbt").toString();
        Map params = new HashMap<>();
        params.put("content", zwbt);
        ResponseMessage responseYhxx = keyFilter.keyFilterQuery(params);
        if (!TextUtils.isEmpty(responseYhxx.getBean())) {
            resultMap.put("result", "false");
//            resultMap.put("msg", "包含不可用关键字:" + responseYhxx.getBean().toString().substring(0, responseYhxx.getBean().toString().length() - 1));
            if (!TextUtils.isEmpty(responseYhxx.getBean())) {
                resultMap.put("msg", responseYhxx.getBean().toString());
            }
            return resultMap;
        }
        BckjBizJob job = new BckjBizJob();
        BckjBizQyxx qyxx = qyxxDao.get(mapData.get("qyxxRefOwid").toString());
        if (3 == qyxx.getState()) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.HMD_ERROR_MESSAGE);
            return resultMap;
        }

        try {
            job = MapUtils.map2Bean(mapData, BckjBizJob.class);
            //状态通过
            job.setState(JyContant.JOB_ZT_TG);
            //职位类型，需要判断自动审核 0表示关 1表示开
            if (JyContant.ZWLB_ZW == job.getZwlx()) {
                job.setSfbm(1);
                String flag = CacheUtil.getVal(JyContant.KG + JyContant.ZWSH);
                if (!TextUtils.isEmpty(flag) && "0".equals(flag)) {
                    job.setState(JyContant.JOB_ZT_DSH);
                }
            }
            job.setZwYds(0);
            job.setZwGzs(0);
            //公司名称
            if (!TextUtils.isEmpty(qyxx)) {
                job.setExp1(qyxx.getQymc());
                job.setExp6(qyxx.getQyLxr());
                job.setExp7(qyxx.getQyLxrdh());
            }
            //职位失效时间
            if (!TextUtils.isEmpty(mapData.get("zwSxsj"))) {
                job.setZwSxsj(DateUtil.getDate(mapData.get("zwSxsj").toString()));
            }
            saveOrUpdate(job);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", job);
        return resultMap;
    }

    public PageInfo<BckjBizJob> myJobList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        if (!TextUtils.isEmpty(dataMap.get("zwlx"))) {
            dataMap.put("zwlx", dataMap.get("zwlx").toString());
        }
        if (!TextUtils.isEmpty(dataMap.get("zphSfbm"))) {
            dataMap.put("zphSfbm", dataMap.get("zphSfbm").toString());
        }
        dataMap.put("orderBy", " a.createtime desc ");
        Page<BckjBizJob> page = new Page<>(pageNo, pageSize);
        //如果是外面列表，查看所有状态为通过记录，如果是我的个人中心，查看所有状态
        if (TextUtils.isEmpty(dataMap.get("qyxxRefOwid")) && TextUtils.isEmpty(dataMap.get("yhRefOwid"))) {
            dataMap.put("state", JyContant.JOB_ZT_TG);
        }
        dataMap.put("page", page);
        List<BckjBizJob> jobList = this.dao.findListByMap(dataMap);
        if (!TextUtils.isEmpty(jobList)) {
            for (BckjBizJob job : jobList) {
                Map params = new HashMap<>();
                if (!TextUtils.isEmpty(job.getZwGzzn())) {
                    params.put("type", JyContant.GZZN);
                    params.put("dicVal1", job.getZwGzzn());
                    String str = qyxxDao.queryDic(params);
                    job.setZwGzznStr(str);
                }
                if (!TextUtils.isEmpty(job.getZwGzzn())) {
                    params.put("type", JyContant.GZZN);
                    params.put("dicVal1", job.getZwGzzn());
                    String str = qyxxDao.queryDic(params);
                    job.setZwGzznStr(str);
                }
                if (!TextUtils.isEmpty(job.getZwGzxz())) {
                    params.put("type", JyContant.GZXZ);
                    params.put("dicVal1", job.getZwGzxz());
                    String str = qyxxDao.queryDic(params);
                    job.setZwGzxzStr(str);
                }
                if (!TextUtils.isEmpty(job.getZwNlyq())) {
                    params.put("type", JyContant.NLYQ);
                    params.put("dicVal1", job.getZwNlyq());
                    String str = qyxxDao.queryDic(params);
                    job.setZwNlyqStr(str);
                }
                if (!TextUtils.isEmpty(job.getZwXlyq())) {
                    params.put("type", JyContant.XLYQ);
                    params.put("dicVal1", job.getZwXlyq());
                    String str = qyxxDao.queryDic(params);
                    job.setZwXlyqStr(str);
                }
                if (!TextUtils.isEmpty(job.getZwYyyq())) {
                    params.put("type", JyContant.YYYQ);
                    params.put("dicVal1", job.getZwYyyq());
                    String str = qyxxDao.queryDic(params);
                    job.setZwYyyqStr(str);
                }
                if (!TextUtils.isEmpty(job.getZwGznx())) {
                    params.put("type", JyContant.GZNX);
                    params.put("dicVal1", job.getZwGznx());
                    String str = qyxxDao.queryDic(params);
                    job.setZwGznxStr(str);
                }
                params.clear();
                params.put("jobRefOwid", job.getOwid());
                //0 职位 1 企业
                if (!TextUtils.isEmpty(dataMap.get("gzlx"))) {
                    params.put("gzlx", dataMap.get("gzlx").toString());
                }
                if (!TextUtils.isEmpty(dataMap.get("xxlb"))) {
                    params.put("xxlb", dataMap.get("xxlb").toString());
                }
                List<BckjBizXsgz> xsgzList = xsgzDao.findListByMapInfo(params);
                job.setXsgzList(xsgzList);
                job.setNumber(xsgzList.size());
                params.clear();
                params.put("jobRefOwid", job.getOwid());
                if (!TextUtils.isEmpty(dataMap.get("yhRefOwid"))) {
                    params.put("yhRefOwid", dataMap.get("yhRefOwid").toString());
                }
                List<BckjBizJybm> bmList = bmDao.findListByMapInfo(params);
                if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
                    job.setSfbm(2);
                } else {
                    job.setSfbm(1);
                }
            }

        }

        page.setList(jobList);
        PageInfo<BckjBizJob> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;
    }

    public Map jobByMonth(Map<String, Object> dataMap) {
        Map<String, List> map = new HashMap<>();
        List<BckjBizJob> jobList = this.dao.findListByMap(dataMap);
        if (jobList != null && jobList.size() > 0) {
            for (BckjBizJob job : jobList) {
                String dateKey = DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd");
                if (map.containsKey(dateKey)) {
                    map.get(dateKey).add(job);
                } else {
                    List<BckjBizJob> newList = new ArrayList<>();
                    newList.add(job);
                    map.put(dateKey, newList);
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println("703c695ce6dd4c".length());
    }


    @Transactional(readOnly = false)
    public BckjBizJob getMiniJob(Map<String, Object> mapData) throws Exception {
        BckjBizJob job = new BckjBizJob();
        String owid = mapData.get("owid").toString();
        if (owid.length() == 14) {
            job = this.dao.queryByOwid(owid);
        } else {
            job = get(owid);
        }
        Map params = new HashMap<>(2);
        if (!TextUtils.isEmpty(job.getZwGzzn())) {
            params.put("type", JyContant.GZZN);
            params.put("dicVal1", job.getZwGzzn());
            String str = qyxxDao.queryDic(params);
            job.setZwGzznStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwGzzn())) {
            params.put("type", JyContant.GZZN);
            params.put("dicVal1", job.getZwGzzn());
            String str = qyxxDao.queryDic(params);
            job.setZwGzznStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwGzxz())) {
            params.put("type", JyContant.GZXZ);
            params.put("dicVal1", job.getZwGzxz());
            String str = qyxxDao.queryDic(params);
            job.setZwGzxzStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwNlyq())) {
            params.put("type", JyContant.NLYQ);
            params.put("dicVal1", job.getZwNlyq());
            String str = qyxxDao.queryDic(params);
            job.setZwNlyqStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwXlyq())) {
            params.put("type", JyContant.XLYQ);
            params.put("dicVal1", job.getZwXlyq());
            String str = qyxxDao.queryDic(params);
            job.setZwXlyqStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwYyyq())) {
            params.put("type", JyContant.YYYQ);
            params.put("dicVal1", job.getZwYyyq());
            String str = qyxxDao.queryDic(params);
            job.setZwYyyqStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwGznx())) {
            params.put("type", JyContant.GZNX);
            params.put("dicVal1", job.getZwGznx());
            String str = qyxxDao.queryDic(params);
            job.setZwGznxStr(str);
        }


        if (!TextUtils.isEmpty(job.getQyxxRefOwid())) {
            BckjBizQyxx qyxx = qyxxDao.getQyxxInfo(job.getQyxxRefOwid());
            //BASE64加密
            qyxx.setQyLxrdh(TextUtils.base64Code(qyxx.getQyLxrdh()));
            qyxx.setQylxfs(TextUtils.base64Code(qyxx.getQylxfs()));
            qyxx.setQyYx(TextUtils.base64Code(qyxx.getQyYx()));


            if (!TextUtils.isEmpty(qyxx)) {
                if (!TextUtils.isEmpty(qyxx.getQyGsgm())) {
                    params.put("type", JyContant.GSGM);
                    params.put("dicVal1", qyxx.getQyGsgm());
                    String gsgmStr = qyxxDao.queryDic(params);
                    qyxx.setQyGsgmStr(gsgmStr);
                }
                if (!TextUtils.isEmpty(qyxx.getQyHylb())) {
                    params.put("type", JyContant.HYLB);
                    params.put("dicVal1", qyxx.getQyHylb());
                    String hylbStr = qyxxDao.queryDic(params);
                    qyxx.setQyHylbStr(hylbStr);
                }
                if (!TextUtils.isEmpty(qyxx.getQyGsxz())) {
                    params.put("type", JyContant.GSXZ);
                    params.put("dicVal1", qyxx.getQyGsxz());
                    String gsxzStr = qyxxDao.queryDic(params);
                    qyxx.setQyGsxzStr(gsxzStr);
                }
                job.setQyxx(qyxx);
            }
        }
//        //报名企业
//        params.clear();
//        params.put("jobRefOwid", job.getOwid());
//        params.put("state", 1);
//        List<BckjBizJybm> bmList = bmService.findListByParams(params, " a.qymc desc ");
//        List<Map> zwList = new ArrayList<>();
//        Map map = Maps.newHashMap();
//        if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
//            for (BckjBizJybm jybm : bmList) {
//                zwList = new ArrayList<>();
//                if (!TextUtils.isEmpty(jybm.getZw1())) {
//                    map = Maps.newHashMap();
//                    map.put("zw", jybm.getZw1());
//                    map.put("rs", jybm.getRs1());
//                    zwList.add(map);
//                }
//                if (!TextUtils.isEmpty(jybm.getZw2())) {
//                    map = Maps.newHashMap();
//                    map.put("zw", jybm.getZw2());
//                    map.put("rs", jybm.getRs2());
//                    zwList.add(map);
//                }
//                if (!TextUtils.isEmpty(jybm.getZw3())) {
//                    map = Maps.newHashMap();
//                    map.put("zw", jybm.getZw3());
//                    map.put("rs", jybm.getRs3());
//                    zwList.add(map);
//                }
//                if (!TextUtils.isEmpty(jybm.getZw4())) {
//                    map = Maps.newHashMap();
//                    map.put("zw", jybm.getZw4());
//                    map.put("rs", jybm.getRs4());
//                    zwList.add(map);
//                }
//                if (!TextUtils.isEmpty(jybm.getZw5())) {
//                    map = Maps.newHashMap();
//                    map.put("zw", jybm.getZw5());
//                    map.put("rs", jybm.getRs5());
//                    zwList.add(map);
//                }
//                jybm.setZwList(zwList);
//            }
//        }
//        job.setBmList(bmList);
        //阅读数+1
        BckjBizJob newJob = get(job.getOwid());
        if (!TextUtils.isEmpty(newJob.getZwYds())) {
            newJob.setZwYds(newJob.getZwYds() + 1);
        } else {
            newJob.setZwYds(1);
        }
        saveOrUpdate(newJob);
        job.setZwYds(newJob.getZwYds());
        //查看是否被关注
        if (!TextUtils.isEmpty(mapData.get("yhOwid"))) {
            HashMap<String, Object> sendMap = Maps.newHashMap();
            sendMap.put("jobRefOwid", owid);
            sendMap.put("yhRefOwid", mapData.get("yhOwid"));
            List<BckjBizXsgz> bckjBizXsgzs = xsgzDao.findListByMapInfo(sendMap);
            if (!TextUtils.isEmpty(bckjBizXsgzs) && bckjBizXsgzs.size() > 0) {
                job.setExp2(bckjBizXsgzs.get(0).getOwid());
            } else {
                job.setExp2("0");
            }
        }

        Map mapParam = Maps.newHashMap();
        mapParam.put("wzRefOwid", owid);
        List<Map> files = qyxxDao.getSysFiles(mapParam);
        job.setFileList(files);


        return job;
    }


    @Transactional(readOnly = false)
    public BckjBizJob getOneJob(Map<String, Object> mapData) throws Exception {
        BckjBizJob job = new BckjBizJob();
        String owid = mapData.get("owid").toString();
        if (owid.length() == 14) {
            job = this.dao.queryByOwid(owid);
        } else {
            job = get(owid);
        }
        Map params = new HashMap<>(2);
        if (!TextUtils.isEmpty(job.getZwGzzn())) {
            params.put("type", JyContant.GZZN);
            params.put("dicVal1", job.getZwGzzn());
            String str = qyxxDao.queryDic(params);
            job.setZwGzznStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwGzzn())) {
            params.put("type", JyContant.GZZN);
            params.put("dicVal1", job.getZwGzzn());
            String str = qyxxDao.queryDic(params);
            job.setZwGzznStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwGzxz())) {
            params.put("type", JyContant.GZXZ);
            params.put("dicVal1", job.getZwGzxz());
            String str = qyxxDao.queryDic(params);
            job.setZwGzxzStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwNlyq())) {
            params.put("type", JyContant.NLYQ);
            params.put("dicVal1", job.getZwNlyq());
            String str = qyxxDao.queryDic(params);
            job.setZwNlyqStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwXlyq())) {
            params.put("type", JyContant.XLYQ);
            params.put("dicVal1", job.getZwXlyq());
            String str = qyxxDao.queryDic(params);
            job.setZwXlyqStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwYyyq())) {
            params.put("type", JyContant.YYYQ);
            params.put("dicVal1", job.getZwYyyq());
            String str = qyxxDao.queryDic(params);
            job.setZwYyyqStr(str);
        }
        if (!TextUtils.isEmpty(job.getZwGznx())) {
            params.put("type", JyContant.GZNX);
            params.put("dicVal1", job.getZwGznx());
            String str = qyxxDao.queryDic(params);
            job.setZwGznxStr(str);
        }


        if (!TextUtils.isEmpty(job.getQyxxRefOwid())) {
            BckjBizQyxx qyxx = qyxxDao.getQyxxInfo(job.getQyxxRefOwid());
            //BASE64加密
            qyxx.setQyLxrdh(TextUtils.base64Code(qyxx.getQyLxrdh()));
            qyxx.setQylxfs(TextUtils.base64Code(qyxx.getQylxfs()));
            qyxx.setQyYx(TextUtils.base64Code(qyxx.getQyYx()));

            if (!TextUtils.isEmpty(qyxx)) {
                if (!TextUtils.isEmpty(qyxx.getQyGsgm())) {
                    params.put("type", JyContant.GSGM);
                    params.put("dicVal1", qyxx.getQyGsgm());
                    String gsgmStr = qyxxDao.queryDic(params);
                    qyxx.setQyGsgmStr(gsgmStr);
                }
                if (!TextUtils.isEmpty(qyxx.getQyHylb())) {
                    params.put("type", JyContant.HYLB);
                    params.put("dicVal1", qyxx.getQyHylb());
                    String hylbStr = qyxxDao.queryDic(params);
                    qyxx.setQyHylbStr(hylbStr);
                }
                if (!TextUtils.isEmpty(qyxx.getQyGsxz())) {
                    params.put("type", JyContant.GSXZ);
                    params.put("dicVal1", qyxx.getQyGsxz());
                    String gsxzStr = qyxxDao.queryDic(params);
                    qyxx.setQyGsxzStr(gsxzStr);
                }
                job.setQyxx(qyxx);
            }
        }
        //报名企业
        params.clear();
        params.put("jobRefOwid", job.getOwid());
        params.put("state", 1);
        List<BckjBizJybm> bmList = bmService.findListByParams(params, " a.qymc desc ");
        List<Map> zwList = new ArrayList<>();
        Map map = Maps.newHashMap();
        if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
            for (BckjBizJybm jybm : bmList) {
                zwList = new ArrayList<>();
                if (!TextUtils.isEmpty(jybm.getZw1())) {
                    map = Maps.newHashMap();
                    map.put("zw", jybm.getZw1());
                    map.put("rs", jybm.getRs1());
                    zwList.add(map);
                }
                if (!TextUtils.isEmpty(jybm.getZw2())) {
                    map = Maps.newHashMap();
                    map.put("zw", jybm.getZw2());
                    map.put("rs", jybm.getRs2());
                    zwList.add(map);
                }
                if (!TextUtils.isEmpty(jybm.getZw3())) {
                    map = Maps.newHashMap();
                    map.put("zw", jybm.getZw3());
                    map.put("rs", jybm.getRs3());
                    zwList.add(map);
                }
                if (!TextUtils.isEmpty(jybm.getZw4())) {
                    map = Maps.newHashMap();
                    map.put("zw", jybm.getZw4());
                    map.put("rs", jybm.getRs4());
                    zwList.add(map);
                }
                if (!TextUtils.isEmpty(jybm.getZw5())) {
                    map = Maps.newHashMap();
                    map.put("zw", jybm.getZw5());
                    map.put("rs", jybm.getRs5());
                    zwList.add(map);
                }
                jybm.setZwList(zwList);
            }
        }
        job.setBmList(bmList);
        //阅读数+1
        BckjBizJob newJob = get(job.getOwid());
        if (!TextUtils.isEmpty(newJob.getZwYds())) {
            newJob.setZwYds(newJob.getZwYds() + 1);
        } else {
            newJob.setZwYds(1);
        }
        saveOrUpdate(newJob);
        job.setZwYds(newJob.getZwYds());
        //查看是否被关注
        if (!TextUtils.isEmpty(mapData.get("yhOwid"))) {
            HashMap<String, Object> sendMap = Maps.newHashMap();
            sendMap.put("jobRefOwid", owid);
            sendMap.put("yhRefOwid", mapData.get("yhOwid"));
            List<BckjBizXsgz> bckjBizXsgzs = xsgzDao.findListByMapInfo(sendMap);
            if (!TextUtils.isEmpty(bckjBizXsgzs) && bckjBizXsgzs.size() > 0) {
                job.setExp2(bckjBizXsgzs.get(0).getOwid());
            } else {
                job.setExp2("0");
            }
        }

        Map mapParam = Maps.newHashMap();
        mapParam.put("wzRefOwid", owid);
        List<Map> files = qyxxDao.getSysFiles(mapParam);
        job.setFileList(files);


        return job;
    }

    public PageInfo<BckjBizJob> firstJobList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        dataMap.put("orderBy", " a.createtime desc ");
        dataMap.put("state", JyContant.JOB_ZT_TG);
        Page<BckjBizJob> page = new Page<>(pageNo, pageSize);
        List<BckjBizJob> jobList = new ArrayList<>();
        dataMap.put("page", page);
        //如果是职来职往，包括职来职往，招聘会，宣讲会 zwlx 1,2,4
//        if ("1".equals(dataMap.get("zwlx").toString())) {
//            dataMap.put("state", JyContant.QY_ZT_TG);
//            jobList = this.dao.findListByMapFirst(dataMap);
//        } else {
//            jobList = this.dao.findListByMap(dataMap);
//        }
        jobList = this.dao.findListByMap(dataMap);
        if (!TextUtils.isEmpty(jobList)) {
            for (BckjBizJob job : jobList) {
                Map params = new HashMap<>();
                params.put("jobRefOwid", job.getOwid());
                //0 职位 1 企业
                params.put("gzlx", dataMap.get("gzlx"));
                List<BckjBizXsgz> xsgzList = xsgzDao.findListByMap(params);
                job.setXsgzList(xsgzList);
                job.setNumber(xsgzList.size());
            }

        }

        page.setList(jobList);
        PageInfo<BckjBizJob> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;
    }

    @Transactional(readOnly = false)
    public Map fixJob(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizJob newJob = new BckjBizJob();
        BckjBizJob oldJob = get(mapData.get("owid").toString());
        try {
            newJob = MapUtils.map2Bean(mapData, BckjBizJob.class);
            BeanUtil.copyPropertiesIgnoreNull(newJob, oldJob);
            oldJob.setState(JyContant.JOB_ZT_TG);
            //职位失效时间

            if (!TextUtils.isEmpty(mapData.get("zwSxsj"))) {
                oldJob.setZwSxsj(DateUtil.getDate(mapData.get("zwSxsj").toString()));
            }
            saveOrUpdate(oldJob);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", oldJob);
        return resultMap;
    }


    @Transactional(readOnly = false)
    public Map submitPurchaseBack(List<String> codes, Integer state) {
        Map resultMap = new HashMap<>(2);
        BckjBizJob job = get(codes.get(0));
        if (JyContant.JOB_ZT_TG.equals(state)) {

            // TODO: 2019/9/18 通过短信

        } else if (JyContant.JOB_ZT_JJ.equals(state)) {
            // TODO: 2019/9/18 拒绝短信
        }
        job.setState(state);
        saveOrUpdate(job);
        resultMap = new HashMap<>(2);
        resultMap.put("result", "true");
        List<Object> _list = new ArrayList();
        _list.add(job);
        resultMap.put("bean", _list);
        return resultMap;
    }

    public List<Map<String, String[]>> xjhtjList() {
        List<Map<String, String[]>> resultList = new ArrayList<>();
        //宣讲会自定义条件
        Map params = Maps.newHashMap();
        params.put("type", JyContant.ZDYTJ);
        List<Map> dicList = new ArrayList<>();
        dicList = qyxxDao.queryDicList(params);
        if (!TextUtils.isEmpty(dicList) && dicList.size() > 0) {
            for (Map map : dicList) {
                if (!TextUtils.isEmpty(map.get("dic_val2")) && !TextUtils.isEmpty(map.get("dic_val3"))) {
                    String zdytj = map.get("dic_val2").toString();
                    String zdyjg = map.get("dic_val3").toString();
                    zdyjg.replace(",", "，");
                    String[] jgArray = zdyjg.split("，");
                    Map resultMap = new HashMap<>();
                    resultMap.put(zdytj, jgArray);
                    resultList.add(resultMap);
                }
            }
        }
        return resultList;
    }

    public List<Map<String, String[]>> zphtjList(Map<String, Object> mapData) {
        List<Map<String, String[]>> resultList = new ArrayList<>();
        String owid = mapData.get("owid").toString();
        BckjBizJob job = get(owid);
        if (JyContant.ZWLB_ZPH == job.getZwlx()) {
            String zdytj = "";
            String zdyjg = "";
            String[] jgArray = new String[10];
            Map resultMap = new HashMap<>();
            if (!TextUtils.isEmpty(job.getZdytj1()) && !TextUtils.isEmpty(job.getTjsd1())) {
                zdytj = job.getZdytj1();
                zdyjg = job.getTjsd1();
                zdyjg.replace(",", "，");
                jgArray = zdyjg.split("，");
                resultMap = new HashMap<>();
                resultMap.put(zdytj, jgArray);
                resultList.add(resultMap);
            }

            if (!TextUtils.isEmpty(job.getZdytj2()) && !TextUtils.isEmpty(job.getTjsd2())) {
                zdytj = job.getZdytj2();
                zdyjg = job.getTjsd2();
                zdyjg.replace(",", "，");
                jgArray = zdyjg.split("，");
                resultMap = new HashMap<>();
                resultMap.put(zdytj, jgArray);
                resultList.add(resultMap);
            }
            if (!TextUtils.isEmpty(job.getZdytj3()) && !TextUtils.isEmpty(job.getTjsd3())) {
                zdytj = job.getZdytj3();
                zdyjg = job.getTjsd3();
                zdyjg.replace(",", "，");
                jgArray = zdyjg.split("，");
                resultMap = new HashMap<>();
                resultMap.put(zdytj, jgArray);
                resultList.add(resultMap);
            }
            if (!TextUtils.isEmpty(job.getZdytj4()) && !TextUtils.isEmpty(job.getTjsd4())) {
                zdytj = job.getZdytj4();
                zdyjg = job.getTjsd4();
                zdyjg.replace(",", "，");
                jgArray = zdyjg.split("，");
                resultMap = new HashMap<>();
                resultMap.put(zdytj, jgArray);
                resultList.add(resultMap);
            }
            if (!TextUtils.isEmpty(job.getZdytj5()) && !TextUtils.isEmpty(job.getTjsd5())) {
                zdytj = job.getZdytj5();
                zdyjg = job.getTjsd5();
                zdyjg.replace(",", "，");
                jgArray = zdyjg.split("，");
                resultMap = new HashMap<>();
                resultMap.put(zdytj, jgArray);
                resultList.add(resultMap);
            }
        }
        return resultList;
    }

    public BckjBizJob getJob(String owid) {
        BckjBizJob job = get(owid);
        if (job.getZwlx() == JyContant.ZWLB_ZW && !TextUtils.isEmpty(job.getQyxxRefOwid())) {
            BckjBizQyxx qyxx = qyxxService.get(job.getQyxxRefOwid());
            job.setQyxx(qyxx);
        }
        return job;
    }

    /**
     * 前台获得当日需要签到的信息列表
     *
     * @param filterModels
     * @param pageNo
     * @param pageSize
     * @return ResponseMessage
     */
    public PageInfo<BckjBizJob> getQdList(List<FilterModel> filterModels, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
        Page<BckjBizJob> page = new Page<>(pageNo, pageSize);
        dataMap.put("page", page);
        List<BckjBizJob> listByMap = this.dao.findQdList(dataMap);
        page.setList(listByMap);
        return PageUtils.assimblePageInfo(page);
    }

    /**
     * 获得需要采点的list
     *
     * @return PageInfo<BckjBizJob>
     */
    public PageInfo<BckjBizJob> getCdList(List<FilterModel> filterModels, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
        Page<BckjBizJob> page = new Page<>(pageNo, pageSize);
        dataMap.put("page", page);
        List<BckjBizJob> cdList = this.dao.getCdList(dataMap);
        page.setList(cdList);
        return PageUtils.assimblePageInfo(page);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage setCdPoint(Map<String, Object> dataMap) {
        this.dao.setCdPoint(dataMap);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

//    public ResponseMessage sendMess() {
//        Map params = new HashMap<>(1);
//        params.put("lastupdate", "1");
//        List<BckjBizQyxx> qyxx = qyxxDao.findListByMap(params);
//        if (!TextUtils.isEmpty(qyxx) && qyxx.size() > 0) {
//            for (BckjBizQyxx qy : qyxx) {
//                String content = qy.getQymc() + "注册人您好，" + "您的登录账号：" + qy.getQyTysh() + ",您的登录密码(法人身份证后六位)：" + qy.getQyFrsfz().substring(qy.getQyFrsfz().length() - 6, qy.getQyFrsfz().length()) + "，欢迎您登录浙江科技学院就业网进行招聘会报名！";
//                String mobile = qy.getQyLxrdh();
//                try {
//                    MessageUtil.sendMessage(mobile, content);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return ResponseMessage.sendOK(qyxx.size());
//    }
}