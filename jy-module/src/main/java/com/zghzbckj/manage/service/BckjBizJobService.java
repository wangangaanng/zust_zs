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
import com.zghzbckj.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //职位类型 0 职位 1职来职往 2社会招聘会 3 企业招聘会 4 宣讲会
        dataMap.put("zwlx", zwlx.toString());
        if (JyContant.ZWLB_ZW == zwlx) {
            page = findPageWithCompany(dataMap, pageNo, pageSize, " a.createtime desc ");
        } else {
            page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");
        }

        return ResponseMessage.sendOK(page);
    }


    public ResponseMessage findPageBckjBizJobXjh(List<FilterModel> filters, Integer state, Integer pageNo, Integer pageSize) {
        PageInfo<BckjBizJob> page = new PageInfo<>();
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        //职位类型 0 职位 1职来职往 2社会招聘会 3 企业招聘会 4 宣讲会
        dataMap.put("zwlx", JyContant.ZWLB_XJH);
        if (1 == state) {
            dataMap.put("wait", 1);
        } else if (2 == state) {
            dataMap.put("over", 1);
        }
        page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");
        return ResponseMessage.sendOK(page);
    }


    private PageInfo<BckjBizJob> findPageWithCompany(Map<String, Object> paramsMap, Integer pageNo, Integer pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }

        page.setList(this.dao.findListByMapWithCompany(paramsMap));
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
        BckjBizJob bckjBizJob = JsonUtil.map2Bean(mapData, BckjBizJob.class);

        String zwbt = bckjBizJob.getZwbt();
        Map params = new HashMap<>();
        params.put("content", zwbt);
        ResponseMessage responseYhxx = keyFilter.keyFilterQuery(params);
        if (!TextUtils.isEmpty(responseYhxx.getBean())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "包含不可用关键字:" + responseYhxx.getBean().toString().substring(0, responseYhxx.getBean().toString().length() - 1));
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
            Date date = DateUtil.getDate(mapData.get("zphKsrq").toString(), "yyyy-MM-dd HH:mm:ss");
            if (TextUtils.isEmpty(date)) {
                date = DateUtil.getDate(mapData.get("zphKsrq").toString(), "yyyy-MM-dd");
            }
            bckjBizJob.setZphKsrq(date);
        }
        if (JyContant.ZWLB_ZW == zwlx) {
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

        saveOrUpdate(bckjBizJob);
        return ResponseMessage.sendOK(bckjBizJob);
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
            resultMap.put("msg", "包含不可用关键字:" + responseYhxx.getBean().toString().substring(0, responseYhxx.getBean().toString().length() - 1));
            return resultMap;
        }
        BckjBizJob job = new BckjBizJob();
        BckjBizQyxx qyxx = qyxxDao.get(mapData.get("qyxxRefOwid").toString());

        try {
            job = MapUtils.map2Bean(mapData, BckjBizJob.class);
            //状态通过
            job.setState(JyContant.JOB_ZT_TG);
            //职位类型，需要判断自动审核 0表示关 1表示开
            if (JyContant.ZWLB_ZW == job.getZwlx()) {
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

    @Transactional(readOnly = false)
    public BckjBizJob getOneJob(Map<String, Object> mapData) {
        String owid = mapData.get("owid").toString();
        BckjBizJob job = get(owid);
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
            BckjBizQyxx qyxx = qyxxService.get(job.getQyxxRefOwid());
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
//阅读数+1
        BckjBizJob newJob = get(owid);
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
            List<BckjBizXsgz> bckjBizXsgzs = bckjBizXsgzService.findListByMap(sendMap);
            if (!TextUtils.isEmpty(bckjBizXsgzs) && bckjBizXsgzs.size() > 0) {
                job.setExp2(bckjBizXsgzs.get(0).getOwid());
            } else {
                job.setExp2("0");
            }
        }
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
}