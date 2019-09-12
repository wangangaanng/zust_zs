/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.util.LocationUtils;
import com.zghzbckj.vo.BckjBizYhxxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    BckjBizYhxxSer bckjBizYhxxSer;


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
    public ResponseMessage signInOrScribe(Map<String, Object> datamap) {
        BckjBizYhxxVo yhxxVo = bckjBizYhxxSer.getOneByOwid(datamap.get("yhRefOwid").toString());
        BckjBizJob bckjBizJob = bckjBizJobService.get(datamap.get("jobRefOwid").toString());
        //如果为签到
        if (Integer.parseInt(datamap.get("xxlb").toString())==1){
            Double distance=LocationUtils.getDistance(bckjBizJob.getZphGpsjd().doubleValue(),bckjBizJob.getZphGpswd().doubleValue(),Double.valueOf(datamap.get("gpsJd").toString()),Double.valueOf(datamap.get("gpsWd").toString()));
            Integer bj=bckjBizJob.getZphGpsbj();
            if (distance>bj){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.OutOfCheckInRange);
            }
        }
        if(TextUtils.isEmpty(yhxxVo)){
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        if(TextUtils.isEmpty(bckjBizJob)){
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        BckjBizXsgz bckjBizXsgz=new BckjBizXsgz();
        bckjBizXsgz.setGzlx(Integer.parseInt(datamap.get("gzlx").toString()));
        bckjBizXsgz.setXxlb(Integer.parseInt(datamap.get("xxlb").toString()));
        bckjBizXsgz.setGzsj(new Date());
        bckjBizXsgz.setJobRefOwid(bckjBizJob.getOwid());
        bckjBizXsgz.setYhRefOwid(yhxxVo.getOwid());
        bckjBizXsgz.setLxdh(yhxxVo.getSjh());
        bckjBizXsgz.setLxr(yhxxVo.getMz());
        bckjBizXsgz.setGpsJd(bckjBizJob.getZphGpsjd());
        bckjBizXsgz.setGpsWd(bckjBizJob.getZphGpswd());
        bckjBizXsgz.setState(1);
        this.dao.insert(bckjBizXsgz);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


}