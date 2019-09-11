/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.zghzbckj.common.CommonConstant;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
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
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.dao.BckjBizJobDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJobService extends CrudService<BckjBizJobDao, BckjBizJob> {

	private static final Logger log = Logger.getLogger(BckjBizJobService.class);

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
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizJob bckjBizJobIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizJob,bckjBizJobIndata);
        bckjBizJob=bckjBizJobIndata;
        }
        saveOrUpdate(bckjBizJob);
        return ResponseMessage.sendOK(bckjBizJob);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizJob </p>
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
            BckjBizJob bckjBizJob = new BckjBizJob();
        bckjBizJob.setOwid(owid);
            this.dao.delete(bckjBizJob);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}