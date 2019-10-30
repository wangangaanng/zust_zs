/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.manage.dao.BckjBizJybmDao;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.web.MessageUtil;
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
    @Autowired
    BckjBizQyxxDao qyxxDao;

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
        if (!TextUtils.isEmpty(map.get("bmdx"))) {
            dataMap.put("bmdx", map.get("bmdx").toString());
        }
        PageInfo<BckjBizJybm> page = findPage(dataMap, pageNo, pageSize, "a.state, a.createtime desc ");

        List<BckjBizJybm> records = page.getRecords();
        BckjBizJybm job = new BckjBizJybm();
        job.setZwbt("共有：" + page.getTotalCount() + "条信息");
        job.setReadOnly(true);
        job.setState(null);
        records.add(0, job);
        return ResponseMessage.sendOK(page);
    }

    public ResponseMessage findPageBckjBizJybmZw(List<FilterModel> filters, Integer pageNo, Integer pageSize, Map map) {
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
        if (!TextUtils.isEmpty(map.get("state"))) {
            dataMap.put("state", map.get("state").toString());
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
        if (!TextUtils.isEmpty(map.get("state"))) {
            dataMap.put("state", map.get("state").toString());
        }
        if (!TextUtils.isEmpty(dataMap.get("xjsj2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("xjsj2").toString(), 1);
            dataMap.put("xjsj2", date);
        }
        if (!TextUtils.isEmpty(map.get("wait"))) {
            dataMap.put("wait", map.get("wait").toString());
        }
        PageInfo<BckjBizJybm> page = findPageXjh(dataMap, pageNo, pageSize, " a.state,a.createtime desc ");

        List<BckjBizJybm> records = page.getRecords();
        BckjBizJybm job = new BckjBizJybm();
        job.setQymc("共有：" + page.getTotalCount() + "条宣讲会信息");
        job.setReadOnly(true);
        records.add(0, job);
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
        BckjBizJob job = new BckjBizJob();
        //0 招聘会  1 宣讲会2职位
        Integer bmdx = Integer.parseInt(mapData.get("bmdx").toString());
        Map resultMap = new HashMap<>(2);
        BckjBizJybm jybm = new BckjBizJybm();

        Integer bmlx = Integer.parseInt(mapData.get("bmlx").toString());
        try {
            jybm = MapUtils.map2Bean(mapData, BckjBizJybm.class);
            //自定义条件
            if (!TextUtils.isEmpty(jybm.getZdytj1()) && !TextUtils.isEmpty(jybm.getTjsd1())) {
                jybm.setZdytj1(jybm.getZdytj1() + "：" + jybm.getTjsd1());
            }
            if (!TextUtils.isEmpty(jybm.getZdytj2()) && !TextUtils.isEmpty(jybm.getTjsd2())) {
                jybm.setZdytj2(jybm.getZdytj2() + "：" + jybm.getTjsd2());
            }
            if (!TextUtils.isEmpty(jybm.getZdytj3()) && !TextUtils.isEmpty(jybm.getTjsd3())) {
                jybm.setZdytj3(jybm.getZdytj3() + "：" + jybm.getTjsd3());
            }
            if (!TextUtils.isEmpty(jybm.getZdytj4()) && !TextUtils.isEmpty(jybm.getTjsd4())) {
                jybm.setZdytj4(jybm.getZdytj4() + "：" + jybm.getTjsd4());
            }
            if (!TextUtils.isEmpty(jybm.getZdytj5()) && !TextUtils.isEmpty(jybm.getTjsd5())) {
                jybm.setZdytj5(jybm.getZdytj5() + "：" + jybm.getTjsd5());
            }

            jybm.setBmsj(new Date());
            //报名类型企业
            if (bmlx == JyContant.BMLX_QY) {
                BckjBizQyxx qyxx = qyxxService.get(mapData.get("qyxxRefOwid").toString());
                if (3 == qyxx.getState()) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", JyContant.HMD_ERROR_MESSAGE);
                    return resultMap;
                }
                //企业名称，税号
                jybm.setQymc(qyxx.getQymc());
                jybm.setQysh(qyxx.getQyTysh());

            }
            //学生职位
            else if (JyContant.BMLX_XS == bmlx) {
                if (!TextUtils.isEmpty(mapData.get("jobRefOwid"))) {
                    job = jobService.get(mapData.get("jobRefOwid").toString());
                    if (!TextUtils.isEmpty(job.getZphBmjzsj())) {
                        if (System.currentTimeMillis() > job.getZphBmjzsj().getTime()) {
                            resultMap.put("result", "false");
                            resultMap.put("msg", "报名截止时间已过");
                            return resultMap;
                        }
                    }
                    if (!TextUtils.isEmpty(job.getZphKsrq())) {
                        String dateStr = DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd");
                        dateStr += " 23:59:59";
                        if (System.currentTimeMillis() > DateUtil.getDate(dateStr, "yyyy-MM-dd HH:mm:ss").getTime()) {
                            resultMap.put("result", "false");
                            resultMap.put("msg", "报名截止时间已过");
                            return resultMap;
                        }
                    }
                    if (!TextUtils.isEmpty(job.getZwSxsj())) {
                        if (System.currentTimeMillis() > job.getZwSxsj().getTime()) {
                            resultMap.put("result", "false");
                            resultMap.put("msg", "已失效");
                            return resultMap;
                        }
                    }
                }

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
                jybm.setLxdh(yhxxVo.getSjh());
                jybm.setLxr(yhxxVo.getXm());
                jybm.setYhRefOwid(yhxxVo.getOwid());
            }

            //宣讲会
            if (JyContant.BMDX_XJH == bmdx) {
                if (JyContant.BMLX_QY == bmlx) {
                    Map params = new HashMap<String, Object>();
                    params.put("qyxxRefOwid", mapData.get("qyxxRefOwid").toString());
                    params.put("zwlx", JyContant.ZWLB_XJH);
                    params.put("wait", 1);
                    List<BckjBizJob> existJob = jobService.findListByParams(params, "");
                    if (!TextUtils.isEmpty(existJob) && existJob.size() > 0) {
                        resultMap.put("result", "false");
                        resultMap.put("msg", "您已申请过宣讲会，不能重复提交");
                        return resultMap;
                    }
                }
                if (!TextUtils.isEmpty(mapData.get("xjsj"))) {
                    jybm.setXjsj(mapData.get("xjsj").toString());
                } else {
                    job = jobService.get(mapData.get("jobRefOwid").toString());
                    jybm.setXjsj(DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd HH:mm:ss"));
                }

            }
            //招聘会
            else if (JyContant.BMDX_ZPH == bmdx) {
                job = jobService.get(mapData.get("jobRefOwid").toString());
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
                if (!TextUtils.isEmpty(job.getZphBmjzsj())) {
                    if (System.currentTimeMillis() > job.getZphBmjzsj().getTime()) {
                        resultMap.put("result", "false");
                        resultMap.put("msg", "报名截止时间已过");
                        return resultMap;
                    }
                }
                if (!TextUtils.isEmpty(job.getZwSxsj())) {
                    if (System.currentTimeMillis() > job.getZwSxsj().getTime()) {
                        resultMap.put("result", "false");
                        resultMap.put("msg", "招聘会已失效");
                        return resultMap;
                    }
                }
                if (JyContant.BMLX_QY == bmlx) {
                    Map params = new HashMap<String, Object>();
                    params.put("jobRefOwid", mapData.get("jobRefOwid").toString());
//                    params.put("state", 1);
                    if (!TextUtils.isEmpty(mapData.get("qyxxRefOwid"))) {
                        params.put("qyxxRefOwid", mapData.get("qyxxRefOwid").toString());
                    }
                    List<BckjBizJybm> existBm = findListByParams(params, "");
                    if (!TextUtils.isEmpty(existBm) && existBm.size() > 0) {
                        resultMap.put("result", "false");
                        resultMap.put("msg", "已存在报名信息");
                        return resultMap;
                    }
                    if (!TextUtils.isEmpty(job.getZphBmxz())) {
                        //报名限制
                        Integer bmxz = job.getZphBmxz();
                        params.clear();
                        params.put("jobRefOwid", mapData.get("jobRefOwid").toString());
                        params.put("state", 1);
                        existBm = findListByParams(params, "");
                        if (!TextUtils.isEmpty(existBm) && existBm.size() > 0 && existBm.size() >= bmxz) {
                            resultMap.put("result", "false");
                            resultMap.put("msg", "企业报名已满");
                            return resultMap;
                        }
                    }
                }

                if (!TextUtils.isEmpty(job.getZphKsrq())) {
                    jybm.setXjsj(DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd HH:mm:ss"));
                }
            }


            //待审核
            jybm.setState(JyContant.JOB_ZT_DSH);
            if (JyContant.BMDX_ZW == bmdx) {
                jybm.setState(1);
            }
            //宣讲会会自动审核开关//审核开关 0表示关 1表示开
            if (JyContant.BMDX_XJH == bmdx) {
                String flag = com.zghzbckj.base.util.CacheUtil.getVal(JyContant.KG + JyContant.XJHSH);
                if (!com.zghzbckj.util.TextUtils.isEmpty(flag) && "1".equals(flag)) {
                    // 查询字典表 筛选拒绝
                    Map params = Maps.newHashMap();
                    params.put("type", JyContant.ZDYTJ);
                    List<Map> dicList = new ArrayList<>();
                    dicList = qyxxDao.queryDicList(params);
                    boolean isFlag = true;
                    if (!TextUtils.isEmpty(dicList) && dicList.size() > 0) {
                        for (Map map : dicList) {
                            if (!TextUtils.isEmpty(map.get("dic_val2")) && !TextUtils.isEmpty(map.get("dic_val3")) && !TextUtils.isEmpty(map.get("dic_val4"))) {
                                String zdytj = map.get("dic_val2").toString();
                                String zdyjg = map.get("dic_val3").toString();
                                String bzda = map.get("dic_val4").toString();
                                for (int k = 1; k <= 5; k++) {
                                    Object tj = mapData.get("zdytj" + k);
                                    Object jg = mapData.get("tjsd" + k);
                                    if (!TextUtils.isEmpty(tj) && !TextUtils.isEmpty(jg)) {
                                        if (tj.toString().equals(zdytj) && !bzda.equals(jg.toString())) {
                                            isFlag = false;
                                        }
                                    }
                                }
                            }
                        }
                        //拒绝
                        if (!isFlag) {
                            jybm.setState(2);
                        }
                    }

                }
            }
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

        if (!TextUtils.isEmpty(dataMap.get("bmdx"))) {
            dataMap.put("bmdx", dataMap.get("bmdx").toString());
            if ("0".equals(dataMap.get("bmdx").toString())) {
                dataMap.put("orderBy", " b.zph_ksrq desc ");
            }
        }

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
        clearCompany(mapData);
        Map resultMap = new HashMap<>(2);
        BckjBizJybm bm = get(codes.get(0));
        //报名对象  0招聘会 1宣讲会
        Integer bmdx = Integer.parseInt(mapData.get("bmdx").toString());
        if (1 == state) {
            if (bmdx == JyContant.BMDX_ZPH) {
                BckjBizJob job = jobService.get(bm.getJobRefOwid());
                if (TextUtils.isEmpty(mapData.get("zwbh"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "审核通过时请填写分配的展位编号");
                    return resultMap;
                }
                bm.setZwbh(mapData.get("zwbh").toString());
                String content = JyContant.ZPH_PASS_MESS + mapData.get("zwbh") + "，地点：" + job.getZphJbdd() + ",举办日期：" + DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd") + "，具体时间：" + job.getZphJtsj();
                String mobile = bm.getLxdh();
                try {
                    MessageUtil.sendMessage(mobile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (bmdx == JyContant.BMDX_XJH) {
//
                if (TextUtils.isEmpty(mapData.get("xjsj"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "审核通过时请填写宣讲时间");
                    return resultMap;
                }
                if (TextUtils.isEmpty(mapData.get("zphJtsj"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "审核通过时请填写具体时间");
                    return resultMap;
                }
                if (TextUtils.isEmpty(mapData.get("exp3"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "审核通过时请填写学校联系人");
                    return resultMap;
                }
                if (TextUtils.isEmpty(mapData.get("exp4"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "审核通过时请填写学校联系人电话");
                    return resultMap;
                }
                if ("1" == mapData.get("zphSfbm").toString() && TextUtils.isEmpty(mapData.get("zphBmjzsj"))) {
                    resultMap.put("result", "false");
                    resultMap.put("msg", "请分配报名截止时间");
                    return resultMap;
                }
                //如果是宣讲会，在职位表生成数据
                BckjBizJob job = new BckjBizJob();
                job.setQyxxRefOwid(bm.getQyxxRefOwid());
                job.setZwlx(JyContant.ZWLB_XJH);
                job.setZwPro(JyContant.ZW_PRO);
                job.setZwCity(JyContant.ZW_CITY);
                job.setZwArea(JyContant.ZW_AREA);
                job.setExp3(mapData.get("exp3").toString());
                job.setExp4(mapData.get("exp4").toString());
                if (!TextUtils.isEmpty(mapData.get("zphJbdd"))) {
                    job.setZphJbdd(mapData.get("zphJbdd").toString());
                }
                if (!TextUtils.isEmpty(mapData.get("zphSfbm"))) {
                    job.setZphSfbm(Integer.parseInt(mapData.get("zphSfbm").toString()));
                }
                if (!TextUtils.isEmpty(mapData.get("zphSfqd"))) {
                    job.setZphSfqd(Integer.parseInt(mapData.get("zphSfqd").toString()));
                }
                if (!TextUtils.isEmpty(mapData.get("zphBmjzsj"))) {
                    job.setZphBmjzsj(DateUtil.getDate(mapData.get("zphBmjzsj").toString(), "yyyy-MM-dd HH:mm:ss"));
                }
                if (!TextUtils.isEmpty(mapData.get("zphJbdd"))) {
                    job.setZphJbdd(mapData.get("zphJbdd").toString());
                }
                job.setZphJbf(bm.getQymc());
                //待定位
                if (!TextUtils.isEmpty(mapData.get("zphSfqd"))) {
                    if ("1".equals(mapData.get("zphSfqd"))) {
                        job.setExp5("1");
                    }
                }
                job.setZphJtsj(mapData.get("zphJtsj").toString());
                job.setZphCbf(JyContant.ZW_DD);
                bm.setXjsj(mapData.get("xjsj").toString());
                bm.setExp3(mapData.get("exp3").toString());
                bm.setExp4(mapData.get("exp4").toString());
                Date zphKsrq = DateUtil.getDate(bm.getXjsj(), "yyyy-MM-dd");
                job.setZphKsrq(zphKsrq);
                job.setZwbt(DateUtil.getDateString(zphKsrq, "yyyy-MM-dd") + "宣讲会" + bm.getQymc());
                job.setState(JyContant.JOB_ZT_TG);
                //自定义条件和结果
                job.setZdytj1(bm.getZdytj1());
                job.setZdytj2(bm.getZdytj2());
                job.setZdytj3(bm.getZdytj3());
                job.setZdytj4(bm.getZdytj4());
                job.setZdytj5(bm.getZdytj5());
                jobService.saveOrUpdate(job);
                if (!TextUtils.isEmpty(mapData.get("memo"))) {
                    bm.setMemo(mapData.get("memo").toString());
                    job.setMemo(mapData.get("memo").toString());
                }
                bm.setJobRefOwid(job.getOwid());
                jobService.saveOrUpdate(job);

                String content = JyContant.XJH_PASS_MESS + mapData.get("exp3") + "。联系：" + mapData.get("exp4");
                String mobile = bm.getLxdh();
                try {
                    MessageUtil.sendMessage(mobile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        } else if (2 == state) {
            if (bmdx == JyContant.BMDX_ZPH) {
                BckjBizJob job = jobService.get(bm.getJobRefOwid());
                String content = JyContant.ZPH_REJECT_MESS;
                if (!TextUtils.isEmpty(mapData.get("memo"))) {
                    bm.setMemo(mapData.get("memo").toString());
                    content += "原因：" + bm.getMemo();
                }
                String mobile = bm.getLxdh();
                try {
                    MessageUtil.sendMessage(mobile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void clearCompany(Map mapData) {
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
        mapData.remove("qyxx.qyHylb");
        mapData.remove("qyxx.qyGsxz");
        mapData.remove("qyxx.qyGsgm");
        mapData.remove("qyxx.qyZczj");
    }

    public BckjBizJybm getWithJob(String owid) {
        BckjBizJybm jybm = get(owid);
        if (!TextUtils.isEmpty(jybm.getJobRefOwid())) {
            BckjBizJob job = jobService.get(jybm.getJobRefOwid());
            jybm.setZphJbdd(job.getZphJbdd());
            jybm.setZphJtsj(job.getZphJtsj());
            jybm.setZwbt(job.getZwbt());
            jybm.setXxlxr(job.getExp3());
            jybm.setXxlxrdh(job.getExp4());
            jybm.setZphSfbm(job.getZphSfbm());
            jybm.setZphSfqd(job.getZphSfqd());
            jybm.setZphBmjzsj(job.getZphBmjzsj());
        }
        if (!TextUtils.isEmpty(jybm.getQyxxRefOwid())) {
            BckjBizQyxx qyxx = qyxxService.get(jybm.getQyxxRefOwid());
            jybm.setQyxx(qyxx);
        }
        return jybm;
    }
}