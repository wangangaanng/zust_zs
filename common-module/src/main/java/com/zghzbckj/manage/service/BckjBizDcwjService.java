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
import com.zghzbckj.manage.dao.BckjBizDcwjDao;
import com.zghzbckj.manage.entity.BckjBizDcwj;
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
public class BckjBizDcwjService extends CrudService<BckjBizDcwjDao, BckjBizDcwj> {

	private static final Logger log = Logger.getLogger(BckjBizDcwjService.class);

    @Override
	public BckjBizDcwj get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizDcwj> findList(BckjBizDcwj bckjBizDcwj) {
		return super.findList(bckjBizDcwj);
	}
	@Override
	public PageInfo<BckjBizDcwj> findPage(Page<BckjBizDcwj> page, BckjBizDcwj bckjBizDcwj) {
		return super.findPage(page, bckjBizDcwj);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizDcwj bckjBizDcwj) {
		super.saveOrUpdate(bckjBizDcwj);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizDcwj bckjBizDcwj) {
		super.delete(bckjBizDcwj);
	}


	/**
     * <p>方法:findPagebckjBizDcwj TODO后台BckjBizDcwj分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizDcwj(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizDcwj> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizDcwj TODO保存BckjBizDcwj信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizDcwj(Map<String, Object> mapData) {
        BckjBizDcwj bckjBizDcwj = JsonUtil.map2Bean(mapData, BckjBizDcwj.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizDcwj bckjBizDcwjIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizDcwj,bckjBizDcwjIndata);
        bckjBizDcwj=bckjBizDcwjIndata;
        }
        saveOrUpdate(bckjBizDcwj);
        return ResponseMessage.sendOK(bckjBizDcwj);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizDcwj </p>
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
            BckjBizDcwj bckjBizDcwj = new BckjBizDcwj();
        bckjBizDcwj.setOwid(owid);
            this.dao.delete(bckjBizDcwj);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}