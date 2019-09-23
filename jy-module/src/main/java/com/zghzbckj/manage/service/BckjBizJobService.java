/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.*;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.BckjBizJobDao;
import com.zghzbckj.manage.dao.BckjBizJybmDao;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;
import com.zghzbckj.manage.dao.BckjBizXsgzDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.entity.BckjBizXsgz;
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
        BckjBizJob bckjBizJob = JsonUtil.map2Bean(mapData, BckjBizJob.class);

        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJob bckjBizJobIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJob, bckjBizJobIndata);
            bckjBizJob = bckjBizJobIndata;
        } else {
            bckjBizJob.setZwlx(zwlx);
            bckjBizJob.setState(JyContant.JOB_ZT_TG);
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


            params.clear();
            params.put("jobRefOwid", owid);
            List<BckjBizJybm> bmList = bmService.findListByParams(params, "");
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
        BckjBizJob job = new BckjBizJob();
        BckjBizQyxx qyxx = qyxxDao.get(mapData.get("qyxxRefOwid").toString());

        try {
            job = MapUtils.map2Bean(mapData, BckjBizJob.class);
            job.setZwGzs(0);
            job.setZwYds(0);
            if (!TextUtils.isEmpty(qyxx)) {
                job.setExp1(qyxx.getQymc());
            }
            //状态通过
            job.setState(JyContant.JOB_ZT_TG);
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
        //状态为通过
        dataMap.put("state", JyContant.JOB_ZT_TG);
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

    public BckjBizJob getOneJob(String owid) {
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
        if (!TextUtils.isEmpty(job.getZwYds())) {
            job.setZwYds(job.getZwYds() + 1);
        } else {
            job.setZwYds(0);
        }

        if (!TextUtils.isEmpty(job.getZwGzs())) {
            job.setZwGzs(job.getZwGzs() + 1);
        } else {
            job.setZwGzs(0);
        }

        return job;
    }

    public PageInfo<BckjBizJob> firstJobList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        dataMap.put("orderBy", " a.createtime desc ");
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
}