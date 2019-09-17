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
import com.zghzbckj.manage.entity.BckjBizLqxs;
import com.zghzbckj.manage.dao.BckjBizLqxsDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizLqxsService extends CrudService<BckjBizLqxsDao, BckjBizLqxs> {

	private static final Logger log = Logger.getLogger(BckjBizLqxsService.class);

    @Override
	public BckjBizLqxs get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizLqxs> findList(BckjBizLqxs bckjBizLqxs) {
		return super.findList(bckjBizLqxs);
	}
	@Override
	public PageInfo<BckjBizLqxs> findPage(Page<BckjBizLqxs> page, BckjBizLqxs bckjBizLqxs) {
		return super.findPage(page, bckjBizLqxs);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizLqxs bckjBizLqxs) {
		super.saveOrUpdate(bckjBizLqxs);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizLqxs bckjBizLqxs) {
		super.delete(bckjBizLqxs);
	}


	/**
     * <p>方法:findPagebckjBizLqxs TODO后台BckjBizLqxs分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizLqxs(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizLqxs> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizLqxs TODO保存BckjBizLqxs信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizLqxs(Map<String, Object> mapData) {
        BckjBizLqxs bckjBizLqxs = JsonUtil.map2Bean(mapData, BckjBizLqxs.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizLqxs bckjBizLqxsIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizLqxs,bckjBizLqxsIndata);
        bckjBizLqxs=bckjBizLqxsIndata;
        }
        saveOrUpdate(bckjBizLqxs);
        return ResponseMessage.sendOK(bckjBizLqxs);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizLqxs </p>
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
            BckjBizLqxs bckjBizLqxs = new BckjBizLqxs();
        bckjBizLqxs.setOwid(owid);
            this.dao.delete(bckjBizLqxs);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}