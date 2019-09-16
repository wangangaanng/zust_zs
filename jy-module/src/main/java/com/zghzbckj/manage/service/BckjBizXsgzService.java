/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.feign.BckjBizYhkzSer;
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.util.JudgeInTimeIntervalUtils;
import com.zghzbckj.util.LocationUtils;
import com.zghzbckj.vo.BckjBizYhkzVo;
import com.zghzbckj.vo.BckjBizYhxxVo;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizXsgz;
import com.zghzbckj.manage.dao.BckjBizXsgzDao;

/**
 * ccService
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
    public ResponseMessage findPageBckjBizXsgz(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizXsgz> page = findPage(dataMap, pageNo, pageSize, null);
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
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizXsgz bckjBizXsgzIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizXsgz,bckjBizXsgzIndata);
        bckjBizXsgz=bckjBizXsgzIndata;
        }
        saveOrUpdate(bckjBizXsgz);
        return ResponseMessage.sendOK(bckjBizXsgz);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizXsgz </p>
        *<ul>
            *<li> @param codes TODO</li>
            *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            *<li>@author D.chen.g </li>
            *<li>@date 2018/9/6 17:14  </li>
            *</ul>
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
    @Transactional(readOnly = false , rollbackFor =  Exception.class)
    public ResponseMessage signInOrScribe(Map<String, Object> datamap) {
        BckjBizJob bckjBizJob = bckjBizJobService.get(datamap.get("jobRefOwid").toString());
        ResponseMessage responseYhxx = bckjbizyhxxSer.getOneByOwid(datamap.get("yhRefOwid").toString());
        if(TextUtils.isEmpty(responseYhxx)||responseYhxx.getBackCode()!=0||TextUtils.isEmpty(responseYhxx.getBean())){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.GetMessageFail);
        }
        BckjBizYhxxVo yhxxVo=JsonUtil.map2Bean((Map)responseYhxx.getBean(),BckjBizYhxxVo.class);
        if(TextUtils.isEmpty(yhxxVo)){
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        if(TextUtils.isEmpty(bckjBizJob)){
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        ResponseMessage responseYhkz=bckjbizyhkzSer.getOneByYhRefOwid(yhxxVo.getOwid());
        if(TextUtils.isEmpty(responseYhkz)||responseYhkz.getBackCode()!=0||TextUtils.isEmpty(responseYhkz.getBean())){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.GetMessageFail);
        }
        BckjBizYhkzVo bckjBizYhkzVo = JsonUtil.map2Bean((Map) responseYhkz.getBean(), BckjBizYhkzVo.class);
        if (TextUtils.isEmpty(bckjBizYhkzVo)||bckjBizYhkzVo.getOlx()!=0){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.FAIL_MESSAGE);
        }
        //如果已经存在
        BckjBizXsgz xsgz= this.dao.getOneByJobYh(datamap);
        if(!TextUtils.isEmpty(xsgz)&&xsgz.getState()==1){
            //如果为关注
            if(xsgz.getXxlb()==0&&Integer.parseInt(datamap.get("xxlb").toString())==xsgz.getXxlb()) {
                int x = -(Integer.parseInt(xsgz.getExp1()));
                xsgz.setExp1("" + x);
                saveOrUpdate(xsgz);
                if (x == -1) {
                    int count =bckjBizJob.getZwGzs()-1;
                    bckjBizJob.setZwGzs(count);
                    bckjBizJobService.saveOrUpdate(bckjBizJob);
                    return ResponseMessage.sendOK(CommonConstant.Unfollow);
                }
                int count =bckjBizJob.getZwGzs()+1;
                bckjBizJob.setZwGzs(count);
                bckjBizJobService.saveOrUpdate(bckjBizJob);
                return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
            }
            //如果为签到
            if(xsgz.getXxlb()==1){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.AlreadyCheck);
            }
        }
        //如果为新的签到
        if (Integer.parseInt(datamap.get("xxlb").toString())==1){
            //如果需要报名的
            if(bckjBizJob.getZphSfbm()==1){
                //根据用户和job找到报名信息
                BckjBizJybm bckjbizjybm = bckjBizJybmService.getOneByJobHy(datamap);
                //报名类型不为学生或。。。。。。
                if(bckjbizjybm.getBmlx()!=1||bckjbizjybm.getSfxz()!=1||bckjbizjybm.getState()!=1){
                    return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.FAIL_MESSAGE);
                }
            }
            //如果不需要签到的
            if(bckjBizJob.getZphSfqd()==0){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.FAIL_MESSAGE);
            }
            //判断job信息是否失效
            if(!(bckjBizJob.getZwSxsj().compareTo(new Date())>0)){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.BeyondTime);
            }
            //判断签到日期是否在举办日期内
            if(!JudgeInTimeIntervalUtils.judgeSameDay(bckjBizJob.getZphKsrq(),new Date())){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.OutOfCheckTime);
            }
            //判断签到时间是否在举办时间区间内
            String zphJtsj = bckjBizJob.getZphJtsj();
            String[] splits = zphJtsj.split("-");
           if(!JudgeInTimeIntervalUtils.judgeInTimeIntervalUtils(splits[0],splits[1]) ){
              return   ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.OutOfCheckTime);
           }
            //根据经纬度判断距离
            ValidateMsg msg = ValidateUtils.isEmpty(datamap, "gpsJd", "gpsWd");
            if (!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
            }
            Double distance=LocationUtils.getDistance(bckjBizJob.getZphGpsjd().doubleValue(),bckjBizJob.getZphGpswd().doubleValue(),Double.valueOf(datamap.get("gpsJd").toString()),Double.valueOf(datamap.get("gpsWd").toString()));
            Integer bj=bckjBizJob.getZphGpsbj();
            if (distance>bj){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.OutOfCheckInRange);
            }
        }
        //如果为新的关注
        if(Integer.parseInt(datamap.get("xxlb").toString())==0){
            int count =bckjBizJob.getZwGzs()+1;
            bckjBizJob.setZwGzs(count);
            bckjBizJobService.saveOrUpdate(bckjBizJob);
        }
        BckjBizXsgz bckjBizXsgz=new BckjBizXsgz();
        if(bckjBizJob.getZwlx()==0){
            bckjBizXsgz.setGzlx(0);
        }else{
            bckjBizXsgz.setGzlx(1);
        }
        bckjBizXsgz.setXxlb(Integer.parseInt(datamap.get("xxlb").toString()));
        bckjBizXsgz.setGzsj(new Date());
        bckjBizXsgz.setJobRefOwid(bckjBizJob.getOwid());
        bckjBizXsgz.setYhRefOwid(yhxxVo.getOwid());
        bckjBizXsgz.setLxdh(yhxxVo.getSjh());
        bckjBizXsgz.setLxr(yhxxVo.getMz());
        bckjBizXsgz.setGpsJd(bckjBizJob.getZphGpsjd());
        bckjBizXsgz.setGpsWd(bckjBizJob.getZphGpswd());
        bckjBizXsgz.setCreatetime(new Date());
        bckjBizXsgz.setState(1);
        bckjBizXsgz.setExp1("1");
        saveOrUpdate(bckjBizXsgz);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
}