/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizYhkzDao;
import com.zghzbckj.manage.entity.BckjBizYhkz;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BckjBizYhkzService extends CrudService<BckjBizYhkzDao, BckjBizYhkz> {

	private static final Logger log = Logger.getLogger(BckjBizYhkzService.class);

    @Override
	public BckjBizYhkz get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizYhkz> findList(BckjBizYhkz bckjBizYhkz) {
		return super.findList(bckjBizYhkz);
	}
	@Override
	public PageInfo<BckjBizYhkz> findPage(Page<BckjBizYhkz> page, BckjBizYhkz bckjBizYhkz) {
		return super.findPage(page, bckjBizYhkz);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizYhkz bckjBizYhkz) {
		super.saveOrUpdate(bckjBizYhkz);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizYhkz bckjBizYhkz) {
		super.delete(bckjBizYhkz);
	}


	/**
     * <p>方法:findPagebckjBizYhkz TODO后台BckjBizYhkz分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizYhkz(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizYhkz> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizYhkz TODO保存BckjBizYhkz信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizYhkz(Map<String, Object> mapData) {
        BckjBizYhkz bckjBizYhkz = JsonUtil.map2Bean(mapData, BckjBizYhkz.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizYhkz bckjBizYhkzIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizYhkz,bckjBizYhkzIndata);
        bckjBizYhkz=bckjBizYhkzIndata;
        }
        saveOrUpdate(bckjBizYhkz);
        return ResponseMessage.sendOK(bckjBizYhkz);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizYhkz </p>
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
            BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
        bckjBizYhkz.setOwid(owid);
            this.dao.delete(bckjBizYhkz);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}