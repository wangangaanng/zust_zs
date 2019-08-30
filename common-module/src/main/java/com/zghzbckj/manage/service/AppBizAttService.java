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
import com.zghzbckj.manage.dao.AppBizAttDao;
import com.zghzbckj.manage.entity.AppBizAtt;
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
 * @version 2019-04-13
 */
@Service
@Transactional(readOnly = true)
public class AppBizAttService extends CrudService<AppBizAttDao, AppBizAtt> {

	private static final Logger log = Logger.getLogger(AppBizAttService.class);

    @Override
	public AppBizAtt get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<AppBizAtt> findList(AppBizAtt appBizAtt) {
		return super.findList(appBizAtt);
	}
	@Override
	public PageInfo<AppBizAtt> findPage(Page<AppBizAtt> page, AppBizAtt appBizAtt) {
		return super.findPage(page, appBizAtt);
	}
	
	@Transactional(readOnly = false)
	public void save(AppBizAtt appBizAtt) {
		super.saveOrUpdate(appBizAtt);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(AppBizAtt appBizAtt) {
		super.delete(appBizAtt);
	}


	/**
     * <p>方法:findPageappBizAtt TODO后台AppBizAtt分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageAppBizAtt(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<AppBizAtt> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:saveappBizAtt TODO保存AppBizAtt信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveAppBizAtt(Map<String, Object> mapData) {
        AppBizAtt appBizAtt = JsonUtil.map2Bean(mapData, AppBizAtt.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        AppBizAtt appBizAttIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(appBizAtt,appBizAttIndata);
        appBizAtt=appBizAttIndata;
        }
        saveOrUpdate(appBizAtt);
        return ResponseMessage.sendOK(appBizAtt);
        }

        /**
        *<p>方法:removeOrder TODO多条删除AppBizAtt </p>
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
            AppBizAtt appBizAtt = new AppBizAtt();
        appBizAtt.setOwid(owid);
            this.dao.delete(appBizAtt);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}