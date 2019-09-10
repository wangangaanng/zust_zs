/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;

import com.zghzbckj.common.CommonConstant;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;

import com.zghzbckj.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizDacx;
import com.zghzbckj.manage.dao.BckjBizDacxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizDacxService extends CrudService<BckjBizDacxDao, BckjBizDacx> {

	private static final Logger log = Logger.getLogger(BckjBizDacxService.class);

    @Override
	public BckjBizDacx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizDacx> findList(BckjBizDacx bckjBizDacx) {
		return super.findList(bckjBizDacx);
	}
	@Override
	public PageInfo<BckjBizDacx> findPage(Page<BckjBizDacx> page, BckjBizDacx bckjBizDacx) {
		return super.findPage(page, bckjBizDacx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizDacx bckjBizDacx) {
		super.saveOrUpdate(bckjBizDacx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizDacx bckjBizDacx) {
		super.delete(bckjBizDacx);
	}


	/**
     * <p>方法:findPagebckjBizDacx TODO后台BckjBizDacx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizDacx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizDacx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizDacx TODO保存BckjBizDacx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizDacx(Map<String, Object> mapData) {
        BckjBizDacx bckjBizDacx = JsonUtil.map2Bean(mapData, BckjBizDacx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizDacx bckjBizDacxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizDacx,bckjBizDacxIndata);
        bckjBizDacx=bckjBizDacxIndata;
        }
        saveOrUpdate(bckjBizDacx);
        return ResponseMessage.sendOK(bckjBizDacx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizDacx </p>
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
            BckjBizDacx bckjBizDacx = new BckjBizDacx();
        bckjBizDacx.setOwid(owid);
            this.dao.delete(bckjBizDacx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }

    public ResponseMessage inquiryArchives(Map<String, Object> datamap) {
        BckjBizDacx bckjBizDacx = this.dao.inquiryArchives(datamap);
        if(bckjBizDacx==null){
            return  ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.Fail_InquiryArchives);
        }
        return  ResponseMessage.sendOK(this.dao.inquiryArchives(datamap));
    }
}