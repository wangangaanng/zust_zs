/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.*;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizZxzxDao;
import com.zghzbckj.manage.entity.BckjBizZxzx;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizZxzx;
import com.zghzbckj.manage.dao.BckjBizZxzxDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizZxzxService extends CrudService<BckjBizZxzxDao, BckjBizZxzx> {

	private static final Logger log = Logger.getLogger(BckjBizZxzxService.class);

    @Override
	public BckjBizZxzx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizZxzx> findList(BckjBizZxzx bckjBizZxzx) {
		return super.findList(bckjBizZxzx);
	}
	@Override
	public PageInfo<BckjBizZxzx> findPage(Page<BckjBizZxzx> page, BckjBizZxzx bckjBizZxzx) {
		return super.findPage(page, bckjBizZxzx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizZxzx bckjBizZxzx) {
		super.saveOrUpdate(bckjBizZxzx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizZxzx bckjBizZxzx) {
		super.delete(bckjBizZxzx);
	}
	@Autowired
    BckjBizYhxxService bckjBizYhxxService;

	/**
     * <p>方法:findPagebckjBizZxzx TODO后台BckjBizZxzx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizZxzx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizZxzx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizZxzx TODO保存BckjBizZxzx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizZxzx(Map<String, Object> mapData) {
        BckjBizZxzx bckjBizZxzx = JsonUtil.map2Bean(mapData, BckjBizZxzx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizZxzx bckjBizZxzxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizZxzx,bckjBizZxzxIndata);
        bckjBizZxzx=bckjBizZxzxIndata;
        }
        saveOrUpdate(bckjBizZxzx);
        return ResponseMessage.sendOK(bckjBizZxzx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizZxzx </p>
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
            BckjBizZxzx bckjBizZxzx = new BckjBizZxzx();
        bckjBizZxzx.setOwid(owid);
            this.dao.delete(bckjBizZxzx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }

    public ResponseMessage consult(Map<String, Object> dataMap) {
            BckjBizZxzx bckjBizZxzx=new BckjBizZxzx();
            //就业专家咨询
        if (Integer.parseInt(dataMap.get("zxlx").toString()) == 2) {
                ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid", "studentOwid");
                if (!msg.getSuccess()) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
                }
                ResponseMessage teachMessage = bckjBizYhxxService.getOneByOwid(dataMap.get("owid").toString());
                if (teachMessage == null && teachMessage.getBackCode() == 0 && teachMessage.getBean() == null) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
                }
                ResponseMessage studentMessage = bckjBizYhxxService.getOneByOwid(dataMap.get("studentOwid").toString());
                if (studentMessage == null && studentMessage.getBackCode() == 0 && studentMessage.getBean() == null) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
                }
            BckjBizYhxx bckjBizYhxx = JsonUtil.map2Bean((Map<String, Object>) studentMessage.getBean(), BckjBizYhxx.class);
            if(!TextUtils.isEmpty(bckjBizYhxx.getYx())){
                bckjBizZxzx.setYx(bckjBizYhxx.getYx());
            }
            if (!TextUtils.isEmpty(bckjBizYhxx.getSjh())){
                bckjBizZxzx.setDh(bckjBizYhxx.getSjh());
            }
            if (!TextUtils.isEmpty(bckjBizYhxx.getXm())){
                bckjBizZxzx.setTwName(bckjBizYhxx.getXm());
            }
            if (!TextUtils.isEmpty(bckjBizYhxx.getOwid())){
                bckjBizZxzx.setTwOwid(bckjBizYhxx.getOwid());
            }

        }
        //就业咨询
        if (Integer.parseInt(dataMap.get("zxlx").toString()) == 5){
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "sjh","xm");
            if (!msg.getSuccess()){
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            bckjBizZxzx.setDh(dataMap.get("sjh").toString());
                bckjBizZxzx.setTwName(dataMap.get("xm").toString());
            }
            bckjBizZxzx.setZxlx(Integer.parseInt(dataMap.get("zxlx").toString()));
            bckjBizZxzx.setWtnr((dataMap.get("wtnr").toString()));
            bckjBizZxzx.setTwrq(new Date());
            bckjBizZxzx.setSfxs(1);
            bckjBizZxzx.setLyip(dataMap.get("ipAdrress").toString());
            this.dao.insert(bckjBizZxzx);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
}