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
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.manage.dao.BckjBizJybmDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.vo.BckjBizYhxxVo;
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
public class BckjBizJybmService extends CrudService<BckjBizJybmDao, BckjBizJybm> {

    private static final Logger log = Logger.getLogger(BckjBizJybmService.class);
    @Autowired
    BckjBizQyxxService qyxxService;
    @Autowired
    BckjBizJobService jobService;
    @Autowired
    BckjBizYhxxSer bckjbizyhxxSer;

    @Override
    public BckjBizJybm get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizJybm> findList(BckjBizJybm bckjBizJybm) {
        return super.findList(bckjBizJybm);
    }

    @Override
    public PageInfo<BckjBizJybm> findPage(Page<BckjBizJybm> page, BckjBizJybm bckjBizJybm) {
        return super.findPage(page, bckjBizJybm);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizJybm bckjBizJybm) {
        super.saveOrUpdate(bckjBizJybm);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizJybm bckjBizJybm) {
        super.delete(bckjBizJybm);
    }


    /**
     * <p>方法:findPagebckjBizJybm TODO后台BckjBizJybm分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizJybm(List<FilterModel> filters, Integer pageNo, Integer pageSize, Map map) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!TextUtils.isEmpty(map.get("jobRefOwid"))) {
            dataMap.put("jobRefOwid", map.get("jobRefOwid").toString());
        }
        if (!TextUtils.isEmpty(map.get("bmlx"))) {
            dataMap.put("bmlx", map.get("bmlx").toString());
        }
        PageInfo<BckjBizJybm> page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");
        return ResponseMessage.sendOK(page);
    }


    public ResponseMessage findPageBckjBizJybmXjh(List<FilterModel> filters, Integer pageNo, Integer pageSize, Map map) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!TextUtils.isEmpty(map.get("jobRefOwid"))) {
            dataMap.put("jobRefOwid", map.get("jobRefOwid").toString());
        }
        if (!TextUtils.isEmpty(map.get("bmdx"))) {
            dataMap.put("bmdx", map.get("bmdx").toString());
        }
        if (!TextUtils.isEmpty(map.get("bmlx"))) {
            dataMap.put("bmlx", map.get("bmlx").toString());
        }
        PageInfo<BckjBizJybm> page = findPageXjh(dataMap, pageNo, pageSize, " a.createtime desc ");
        return ResponseMessage.sendOK(page);
    }

    private PageInfo<BckjBizJybm> findPageXjh(Map<String, Object> paramsMap, Integer pageNo, Integer pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }

        page.setList(this.dao.findListByMapXjh(paramsMap));
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
     * <p>方法:savebckjBizJybm TODO保存BckjBizJybm信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizJybm(Map<String, Object> mapData) {
        BckjBizJybm bckjBizJybm = JsonUtil.map2Bean(mapData, BckjBizJybm.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJybm bckjBizJybmIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJybm, bckjBizJybmIndata);
            bckjBizJybm = bckjBizJybmIndata;
        }
        saveOrUpdate(bckjBizJybm);
        return ResponseMessage.sendOK(bckjBizJybm);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizJybm </p>
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
            BckjBizJybm bckjBizJybm = new BckjBizJybm();
            bckjBizJybm.setOwid(owid);
            this.dao.delete(bckjBizJybm);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public Map applyJob(Map<String, Object> mapData) {
        //0报名 1宣讲 2职位
        Integer bmdx = Integer.parseInt(mapData.get("bmdx").toString());
        Map resultMap = new HashMap<>(2);
        BckjBizJybm jybm = new BckjBizJybm();

        Integer bmlx = Integer.parseInt(mapData.get("bmlx").toString());
        try {
            jybm = MapUtils.map2Bean(mapData, BckjBizJybm.class);
            jybm.setBmsj(new Date());
            //报名类型企业
            if (bmlx == JyContant.BMLX_QY) {
                BckjBizQyxx qyxx = qyxxService.get(mapData.get("qyxxRefOwid").toString());
                //企业名称，税号
                jybm.setQymc(qyxx.getQymc());
                jybm.setQysh(qyxx.getQyTysh());
                Map params = new HashMap<>();
                params.put("qyxxRefOwid", mapData.get("qyxxRefOwid").toString());
//                params.put("jobRefOwid", mapData.get("jobRefOwid").toString());
                BckjBizJybm existBm = this.dao.getOneByParam(params);
                if (!TextUtils.isEmpty(existBm)) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "已存在报名信息");
                    return resultMap;
                }
            } else if (JyContant.BMLX_XS == bmlx) {
                Map params = new HashMap<>();
                params.put("yhRefOwid", mapData.get("yhRefOwid").toString());
                params.put("jobRefOwid", mapData.get("jobRefOwid").toString());
                BckjBizJybm existBm = this.dao.getOneByParam(params);
                if (!TextUtils.isEmpty(existBm)) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "已存在报名信息");
                    return resultMap;
                }
                ResponseMessage responseYhxx = bckjbizyhxxSer.getOneByOwid(mapData.get("yhRefOwid").toString());
                if (TextUtils.isEmpty(responseYhxx) || responseYhxx.getBackCode() != 0 || TextUtils.isEmpty(responseYhxx.getBean())) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "不存在该用户");
                    return resultMap;
                }
                BckjBizYhxxVo yhxxVo = JsonUtil.map2Bean((Map) responseYhxx.getBean(), BckjBizYhxxVo.class);
                jybm.setLxdh(yhxxVo.getXm());
                jybm.setLxr(yhxxVo.getSjh());
                jybm.setBmlx(JyContant.BMDX_ZW);
                jybm.setYhRefOwid(yhxxVo.getOwid());
            }
            //报名对象宣讲会
            if (JyContant.BMDX_XJH == bmdx) {
                if (TextUtils.isEmpty(mapData.get("xjsj"))) {
                    jybm.setXjsj(mapData.get("xjsj").toString());
                }
            } else if (JyContant.BMDX_ZPH == bmdx) {
                BckjBizJob job = jobService.get(mapData.get("jobRefOwid").toString());
                if (TextUtils.isEmpty(job)) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "招聘信息不存在");
                    return resultMap;
                }
                if (TextUtils.isEmpty(job.getZphSfbm()) || 0 == job.getZphSfbm()) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "无需报名");
                    return resultMap;
                }
                if (!TextUtils.isEmpty(job.getZphKsrq())) {
                    jybm.setXjsj(DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd HH:mm:ss"));
                }
            }
            //待审核
            jybm.setState(JyContant.JOB_ZT_DSH);
            saveOrUpdate(jybm);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", jybm);
        return resultMap;
    }

    public PageInfo<BckjBizJybm> myBmList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        dataMap.put("orderBy", " a.createtime desc ");
        Page<BckjBizJybm> page = new Page<>(pageNo, pageSize);
        dataMap.put("page", page);
        dataMap.put("bmlx", dataMap.get("bmlx").toString());
        dataMap.put("bmdx", dataMap.get("bmdx").toString());
        List<BckjBizJybm> bmList = this.dao.findListByMap(dataMap);
        page.setList(bmList);
        PageInfo<BckjBizJybm> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;
    }

    public BckjBizJybm getOneByJobHy(Map<String, Object> datamap) {
        return this.dao.getOneByJobHy(datamap);
    }


    @Transactional(readOnly = false)
    public Map submitPurchaseBack(List<String> codes, Integer state, Map mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizJybm bm = get(codes.get(0));
        //报名对象  0招聘会 1宣讲会
        Integer bmdx = Integer.parseInt(mapData.get("bmdx").toString());
        if (1 == state) {
            if (bmdx == JyContant.BMDX_ZPH) {
                if (TextUtils.isEmpty(mapData.get("zwbh"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "审核通过时请填写分配的展位编号");
                    return resultMap;
                }
                bm.setZwbh(mapData.get("zwbh").toString());
            } else if (bmdx == JyContant.BMDX_XJH) {
                //如果是宣讲会，在职位表生成数据
                BckjBizJob job = new BckjBizJob();
                job.setQyxxRefOwid(bm.getQyxxRefOwid());
                job.setZwlx(JyContant.ZWLB_XJH);
                job.setZwPro(JyContant.ZW_PRO);
                job.setZwCity(JyContant.ZW_CITY);
                job.setZwArea(JyContant.ZW_AREA);
                job.setZphJbdd(JyContant.ZW_DD);
                job.setZphJbf(bm.getQymc());
                job.setZwbt(bm.getQymc() + "宣讲会");
                job.setZphCbf(JyContant.ZW_DD);
                job.setZphKsrq(DateUtil.getDate(bm.getXjsj(), "yyyy-MM-dd HH:mm:ss"));
                job.setState(JyContant.JOB_ZT_TG);
                jobService.saveOrUpdate(job);
                bm.setJobRefOwid(job.getOwid());
            }
            // TODO: 2019/9/18 通过短信

        } else if (2 == state) {
            // TODO: 2019/9/18 拒绝短信
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
}