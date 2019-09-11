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
import com.zghzbckj.manage.entity.BckjBizJypm;
import com.zghzbckj.manage.dao.BckjBizJypmDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJypmService extends CrudService<BckjBizJypmDao, BckjBizJypm> {

	private static final Logger log = Logger.getLogger(BckjBizJypmService.class);

    @Override
	public BckjBizJypm get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizJypm> findList(BckjBizJypm bckjBizJypm) {
		return super.findList(bckjBizJypm);
	}
	@Override
	public PageInfo<BckjBizJypm> findPage(Page<BckjBizJypm> page, BckjBizJypm bckjBizJypm) {
		return super.findPage(page, bckjBizJypm);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizJypm bckjBizJypm) {
		super.saveOrUpdate(bckjBizJypm);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizJypm bckjBizJypm) {
		super.delete(bckjBizJypm);
	}


	/**
     * <p>方法:findPagebckjBizJypm TODO后台BckjBizJypm分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizJypm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizJypm> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizJypm TODO保存BckjBizJypm信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizJypm(Map<String, Object> mapData) {
        BckjBizJypm bckjBizJypm = JsonUtil.map2Bean(mapData, BckjBizJypm.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizJypm bckjBizJypmIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizJypm,bckjBizJypmIndata);
        bckjBizJypm=bckjBizJypmIndata;
        }
        saveOrUpdate(bckjBizJypm);
        return ResponseMessage.sendOK(bckjBizJypm);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizJypm </p>
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
            BckjBizJypm bckjBizJypm = new BckjBizJypm();
        bckjBizJypm.setOwid(owid);
            this.dao.delete(bckjBizJypm);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}