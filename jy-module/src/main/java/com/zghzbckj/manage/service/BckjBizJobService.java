/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.BckjBizJobDao;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;
import com.zghzbckj.manage.dao.BckjBizXsgzDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizXsgz;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseMessage findPageBckjBizJob(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizJob> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
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
    public ResponseMessage saveBckjBizJob(Map<String, Object> mapData) {
        BckjBizJob bckjBizJob = JsonUtil.map2Bean(mapData, BckjBizJob.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJob bckjBizJobIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJob, bckjBizJobIndata);
            bckjBizJob = bckjBizJobIndata;
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
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public Map addOneJob(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizJob job = new BckjBizJob();
        try {
            job = MapUtils.map2Bean(mapData, BckjBizJob.class);
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
        dataMap.put("orderBy", " a.createtime desc ");
        Page<BckjBizJob> page = new Page<>(pageNo, pageSize);
        List<BckjBizJob> jobList = this.dao.findListByMap(dataMap);

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

    public List<BckjBizJob> jobByMonth(Map<String, Object> dataMap) {
        List<BckjBizJob> jobList = this.dao.findListByMap(dataMap);
        return jobList;
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
        return job;
    }

    public PageInfo<BckjBizJob> firstJobList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        dataMap.put("orderBy", " a.createtime desc ");
        Page<BckjBizJob> page = new Page<>(pageNo, pageSize);
        List<BckjBizJob> jobList = new ArrayList<>();
        if ("1".equals(dataMap.get("zwlx").toString())) {
            jobList = this.dao.findListByMapFirst(dataMap);
        }else{
            jobList = this.dao.findListByMap(dataMap);
        }
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