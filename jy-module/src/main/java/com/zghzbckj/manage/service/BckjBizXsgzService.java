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
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.feign.BckjBizYhkzSer;
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.manage.dao.BckjBizXsgzDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizXsgz;
import com.zghzbckj.util.JudgeInTimeIntervalUtils;
import com.zghzbckj.util.LocationUtils;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.PageUtils;
import com.zghzbckj.vo.BckjBizYhkzVo;
import com.zghzbckj.vo.BckjBizYhxxVo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.util.ElementScanner6;
import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizXsgzService extends CrudService<BckjBizXsgzDao, BckjBizXsgz> {

    private static final Logger log = Logger.getLogger(BckjBizXsgzService.class);

    @Override
    public BckjBizXsgz get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizXsgz> findList(BckjBizXsgz bckjBizXsgz) {
        return super.findList(bckjBizXsgz);
    }

    @Override
    public PageInfo<BckjBizXsgz> findPage(Page<BckjBizXsgz> page, BckjBizXsgz bckjBizXsgz) {
        return super.findPage(page, bckjBizXsgz);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizXsgz bckjBizXsgz) {
        super.saveOrUpdate(bckjBizXsgz);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizXsgz bckjBizXsgz) {
        super.delete(bckjBizXsgz);
    }

    @Autowired
    BckjBizJobService bckjBizJobService;
    @Autowired
    BckjBizYhxxSer bckjbizyhxxSer;
    @Autowired
    BckjBizYhkzSer bckjbizyhkzSer;
    @Autowired
    BckjBizJybmService bckjBizJybmService;
    @Autowired
    BckjBizQyxxService bckjBizQyxxService;


    /**
     * <p>方法:findPagebckjBizXsgz TODO后台BckjBizXsgz分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizXsgz(List<FilterModel> filters, Integer pageNo, Integer pageSize, Map map) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!TextUtils.isEmpty(map.get("jobRefOwid"))) {
            dataMap.put("jobRefOwid", map.get("jobRefOwid").toString());
        }
        if (!TextUtils.isEmpty(map.get("gzlx"))) {
            dataMap.put("gzlx", map.get("gzlx").toString());
        }
        if (!TextUtils.isEmpty(map.get("xxlb"))) {
            dataMap.put("xxlb", map.get("xxlb").toString());
        }
        PageInfo<BckjBizXsgz> page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");


        List<BckjBizXsgz> records = page.getRecords();
        BckjBizXsgz job = new BckjBizXsgz();
        job.setZwbt("共有：" + page.getTotalCount() + "条信息");
        job.setReadOnly(true);
        records.add(0, job);

        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizXsgz TODO保存BckjBizXsgz信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizXsgz(Map<String, Object> mapData) {
        BckjBizXsgz bckjBizXsgz = JsonUtil.map2Bean(mapData, BckjBizXsgz.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizXsgz bckjBizXsgzIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizXsgz, bckjBizXsgzIndata);
            bckjBizXsgz = bckjBizXsgzIndata;
        }
        saveOrUpdate(bckjBizXsgz);
        return ResponseMessage.sendOK(bckjBizXsgz);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizXsgz </p>
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
            BckjBizXsgz bckjBizXsgz = new BckjBizXsgz();
            bckjBizXsgz.setOwid(owid);
            this.dao.delete(bckjBizXsgz);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
     * <p>功能描述:学生签到或关注企业 职位</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11 </li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage signInOrScribe(Map<String, Object> datamap) {
        String distance = null;
        BckjBizXsgz bckjBizXsgz = new BckjBizXsgz();
        BckjBizJob bckjBizJob = bckjBizJobService.get(datamap.get("jobRefOwid").toString());
        ResponseMessage responseYhxx = bckjbizyhxxSer.getOneByOwid(datamap.get("yhRefOwid").toString());
        if (TextUtils.isEmpty(responseYhxx) || responseYhxx.getBackCode() != 0 || TextUtils.isEmpty(responseYhxx.getBean())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        BckjBizYhxxVo yhxxVo = JsonUtil.map2Bean((Map) responseYhxx.getBean(), BckjBizYhxxVo.class);
        if (TextUtils.isEmpty(yhxxVo)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        if (TextUtils.isEmpty(bckjBizJob)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        ResponseMessage responseYhkz = bckjbizyhkzSer.getOneByYhRefOwid(yhxxVo.getOwid());
        if (TextUtils.isEmpty(responseYhkz) || responseYhkz.getBackCode() != 0 || TextUtils.isEmpty(responseYhkz.getBean())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        BckjBizYhkzVo bckjBizYhkzVo = JsonUtil.map2Bean((Map) responseYhkz.getBean(), BckjBizYhkzVo.class);
        if (TextUtils.isEmpty(bckjBizYhkzVo) || bckjBizYhkzVo.getOlx() != 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.FAIL_MESSAGE);
        }
        //如果已经存在
        BckjBizXsgz xsgz = this.dao.getOneByJobYh(datamap);
        if (!TextUtils.isEmpty(xsgz)) {
            //如果为关注
            if (xsgz.getXxlb() == 0 && Integer.parseInt(datamap.get("xxlb").toString()) == xsgz.getXxlb()&&xsgz.getState()==1) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "已关注");
            }
            //如果为签到
            if (xsgz.getXxlb() == 1) {
                //不成功的签到
                if(xsgz.getState()==0){
                    bckjBizXsgz.setOwid(xsgz.getOwid());
                }else {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "您已签到过！");
                }
            }
        }
        //如果为新的签到
        if (Integer.parseInt(datamap.get("xxlb").toString()) == 1) {
            //如果职来职往 学生不用报名
           /* if(bckjBizJob.getZwlx()!=3){
                //如果需要报名的
                if (bckjBizJob.getZphSfbm() != null && bckjBizJob.getZphSfbm() == 1) {
                    //根据用户和job找到报名信息
                    BckjBizJybm bckjbizjybm = bckjBizJybmService.getOneByJobHy(datamap);
                    //报名类型不为学生或。。。。。。
                    if (bckjbizjybm.getBmlx() != 1 || bckjbizjybm.getState() != 1) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.FAIL_MESSAGE);
                    }
                }
            }*/
            //如果不需要签到的
            if (bckjBizJob.getZphSfqd() != null && bckjBizJob.getZphSfqd() == 0) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "不需要签到");
            }
            //判断是否已经定点
            if(!TextUtils.isEmpty(bckjBizJob.getExp5())){
                if (bckjBizJob.getExp5().equals("1")) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "管理员还未设置签到点位，无法签到");
                }
            }
            //判断job信息是否失效
            if (!com.zghzbckj.util.TextUtils.isEmpty(bckjBizJob.getZwSxsj())) {
                if (!(bckjBizJob.getZwSxsj().compareTo(new Date()) > 0)) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.BeyondTime);
                }
            }
            //判断签到日期是否在举办日期内
            if (!com.zghzbckj.util.TextUtils.isEmpty(bckjBizJob.getZphKsrq())) {
                if (!JudgeInTimeIntervalUtils.judgeSameDay(bckjBizJob.getZphKsrq(), new Date())) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.OutOfCheckTime);
                }
            }
            //判断签到时间是否在签到时间区间内
          if (!TextUtils.isEmpty(bckjBizJob.getQdsj1())&&!TextUtils.isEmpty(bckjBizJob.getQdsj2())){
              if(bckjBizJob.getQdsj1().after(new Date())){
                  return ResponseMessage.sendError(ResponseMessage.FAIL,"签到时间还未开始");
              }
              if(bckjBizJob.getQdsj2().before(new Date())){
                  return ResponseMessage.sendError(ResponseMessage.FAIL,"签到时间已结束");
              }
            }
            //根据经纬度判断距离
            ValidateMsg msg = ValidateUtils.isEmpty(datamap, "gpsJd", "gpsWd");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }

         /*   //设置距离
            Integer bj = bckjBizJob.getZphGpsbj();
            if (distance > bj) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.OutOfCheckInRange);
            }*/
            //查看此微信号是否已经注册过   unionid存exp5
            HashMap<Object, Object> sendMap = Maps.newHashMap();
            sendMap.put("jobRefOwid",bckjBizJob.getOwid());
            sendMap.put("unionid",yhxxVo.getUnionid());
            BckjBizXsgz oneByUnionId = this.dao.getOneByUnionId(sendMap);
            if (!TextUtils.isEmpty(oneByUnionId)&& oneByUnionId.getState()==1) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "该微信已签到过,不能再签到");
            }
        }
        //如果为新的关注
        if (Integer.parseInt(datamap.get("xxlb").toString()) == 0) {
            if (bckjBizJob.getZwGzs() == null) {
                bckjBizJob.setZwGzs(1);
            }
            int count = bckjBizJob.getZwGzs() + 1;
            bckjBizJob.setZwGzs(count);
            bckjBizJobService.saveOrUpdate(bckjBizJob);
        }
        if (bckjBizJob.getZwlx() == 0) {
            bckjBizXsgz.setGzlx(0);
        } else {
            bckjBizXsgz.setGzlx(1);
        }
        bckjBizXsgz.setState(1);
        bckjBizXsgz.setXxlb(Integer.parseInt(datamap.get("xxlb").toString()));
        //设置unionid
        if (bckjBizXsgz.getXxlb() == 1) {
            bckjBizXsgz.setExp5(yhxxVo.getUnionid());
            //前端传过来距离
            Integer bj = null;
            if(TextUtils.isEmpty(bckjBizJob.getZphGpsbj())){
                //默认为250m半径
                bj = 250;
            }
            else{
                bj = bckjBizJob.getZphGpsbj();
            }
            if(!TextUtils.isEmpty(datamap.get("distance"))){
                distance=datamap.get("distance").toString();
            }else {
                distance = LocationUtils.getDistance(bckjBizJob.getZphGpsjd().doubleValue(), bckjBizJob.getZphGpswd().doubleValue(), Double.valueOf(datamap.get("gpsJd").toString()), Double.valueOf(datamap.get("gpsWd").toString()))+"m";
            }
            //设置exp10距离if(da)
            bckjBizXsgz.setExp10(distance);
            if(distance.indexOf("km")!=-1){
                 distance = distance.substring(0, distance.lastIndexOf("k"));
                if (Integer.parseInt(distance)*1000 > bj) {
                    //未通过
                    bckjBizXsgz.setState(0);
                }
            }
            if(distance.indexOf("m")!=-1){
                distance = distance.substring(0, distance.lastIndexOf("m"));
                if (Double.parseDouble(distance)> bj) {
                    //未通过
                    bckjBizXsgz.setState(0);
                }
            }
        }
        bckjBizXsgz.setGzsj(new Date());
        bckjBizXsgz.setJobRefOwid(bckjBizJob.getOwid());
        bckjBizXsgz.setYhRefOwid(yhxxVo.getOwid());
        bckjBizXsgz.setLxdh(yhxxVo.getSjh());
        bckjBizXsgz.setLxr(yhxxVo.getXm());
        bckjBizXsgz.setGpsJd(bckjBizJob.getZphGpsjd());
        bckjBizXsgz.setGpsWd(bckjBizJob.getZphGpswd());
        bckjBizXsgz.setCreatetime(new Date());
        bckjBizXsgz.setExp1(bckjBizJob.getZwlx().toString());
        saveOrUpdate(bckjBizXsgz);
        if(bckjBizXsgz.getState()==0){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"距离太远签到失败!");
        }
        HashMap<String, Object> sendMap = Maps.newHashMap();
        sendMap.put("jobRefOwid", bckjBizXsgz.getJobRefOwid());
        sendMap.put("yhRefOwid", bckjBizXsgz.getYhRefOwid());
        List<BckjBizXsgz> bckjbiz = this.dao.findListByMap(sendMap);
        //去掉铭感信息
        for (BckjBizXsgz bckjBizXsgz1:bckjbiz){
            bckjBizXsgz1.setLxdh("");
            bckjBizXsgz1.setLxr("");
        }
        if (!TextUtils.isEmpty(bckjbiz) && bckjbiz.size() > 0) {
            return ResponseMessage.sendOK(bckjbiz);
        }
        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.FAIL_MESSAGE);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage cancelSubcribe(Map<String, Object> dataMap) {
        BckjBizXsgz bckjBizXsgz = this.dao.get(dataMap.get("owid").toString());
        BckjBizJob bckjBizJob = bckjBizJobService.get(bckjBizXsgz.getJobRefOwid());
        if (com.zghzbckj.util.TextUtils.isEmpty(bckjBizJob)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.FAIL_MESSAGE);
        }
        Integer zwGzs = bckjBizJob.getZwGzs() - 1;
        bckjBizJob.setZwGzs(zwGzs);
        bckjBizJobService.saveOrUpdate(bckjBizJob);
        this.dao.delete(dataMap.get("owid").toString());
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public PageInfo myCareList(Map<String, Object> dataMap) {
        Integer pageNo = Integer.parseInt(dataMap.get("pageNo").toString());
        Integer pageSize = Integer.parseInt(dataMap.get("pageSize").toString());
        dataMap.put("orderBy", " a.createtime desc ");
        Page<BckjBizXsgz> page = new Page<>(pageNo, pageSize);
        List<BckjBizXsgz> myCareList = this.dao.findListByMap(dataMap);
        page.setList(myCareList);
        PageInfo<BckjBizXsgz> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;

    }

    public BckjBizXsgz getOneCare(String owid) {
        BckjBizXsgz xsgz = get(owid);
        if (!TextUtils.isEmpty(xsgz)) {
            BckjBizJob job = bckjBizJobService.get(xsgz.getJobRefOwid());
            Map params = new HashMap<>(2);
            if (!TextUtils.isEmpty(job.getZwGzzn())) {
                params.put("type", JyContant.GZZN);
                params.put("dicVal1", job.getZwGzzn());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwGzznStr(str);
            }
            if (!TextUtils.isEmpty(job.getZwGzzn())) {
                params.put("type", JyContant.GZZN);
                params.put("dicVal1", job.getZwGzzn());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwGzznStr(str);
            }
            if (!TextUtils.isEmpty(job.getZwGzxz())) {
                params.put("type", JyContant.GZXZ);
                params.put("dicVal1", job.getZwGzxz());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwGzxzStr(str);
            }
            if (!TextUtils.isEmpty(job.getZwNlyq())) {
                params.put("type", JyContant.NLYQ);
                params.put("dicVal1", job.getZwNlyq());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwNlyqStr(str);
            }
            if (!TextUtils.isEmpty(job.getZwXlyq())) {
                params.put("type", JyContant.XLYQ);
                params.put("dicVal1", job.getZwXlyq());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwXlyqStr(str);
            }
            if (!TextUtils.isEmpty(job.getZwYyyq())) {
                params.put("type", JyContant.YYYQ);
                params.put("dicVal1", job.getZwYyyq());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwYyyqStr(str);
            }
            if (!TextUtils.isEmpty(job.getZwGznx())) {
                params.put("type", JyContant.GZNX);
                params.put("dicVal1", job.getZwGznx());
                String str = bckjBizQyxxService.qyxxDao.queryDic(params);
                job.setZwGznxStr(str);
            }
            xsgz.setJob(job);
        }
        return xsgz;
    }

    public List<BckjBizXsgz> findListByMap(Map<String, Object> sendMap) {
        return this.dao.findListByMap(sendMap);
    }

    public ResponseMessage studentSubcribeList(Map<String, Object> dataMap) {
        Page<Map<String, Object>> page = new Page(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        if (!TextUtils.isEmpty(dataMap.get("zwlx"))) {
            if (Integer.parseInt(dataMap.get("zwlx").toString()) == 2) {
                dataMap.remove("zwlx");
                dataMap.put("zwlx", 3);
            }
        }
        if (!TextUtils.isEmpty(dataMap.get("zwlx"))) {
            if (Integer.parseInt(dataMap.get("zwlx").toString()) == 3) {
                dataMap.remove("zwlx");
                dataMap.put("zwlx", 2);
            }
        }
        List<Map<String, Object>> mapList = this.dao.studentSubcribeList(dataMap);
        for(Map map:mapList){
            map.put("lxdh","");
        }
        page.setList(mapList);
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    public ResponseMessage zwSubcribeList(Map<String, Object> dataMap) {
        Page<Map<String, Object>> page = new Page(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        dataMap.put("gzlx", "0");
        dataMap.put("xxlb", "0");
        dataMap.put("orderBy", " a.createtime desc ");
        page.setList(this.dao.zwSubcribeList(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    public BckjBizXsgz getOneXchByYhRefOwid(String yhRefOwid,String owid) {
        return this.dao.getOneXchByYhRefOwid(yhRefOwid,owid,9);
    }
}