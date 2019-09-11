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
import com.zghzbckj.manage.entity.BckjBizXsyy;
import com.zghzbckj.manage.dao.BckjBizXsyyDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizXsyyService extends CrudService<BckjBizXsyyDao, BckjBizXsyy> {

	private static final Logger log = Logger.getLogger(BckjBizXsyyService.class);

    @Override
	public BckjBizXsyy get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizXsyy> findList(BckjBizXsyy bckjBizXsyy) {
		return super.findList(bckjBizXsyy);
	}
	@Override
	public PageInfo<BckjBizXsyy> findPage(Page<BckjBizXsyy> page, BckjBizXsyy bckjBizXsyy) {
		return super.findPage(page, bckjBizXsyy);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizXsyy bckjBizXsyy) {
		super.saveOrUpdate(bckjBizXsyy);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizXsyy bckjBizXsyy) {
		super.delete(bckjBizXsyy);
	}


	/**
     * <p>方法:findPagebckjBizXsyy TODO后台BckjBizXsyy分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizXsyy(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizXsyy> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizXsyy TODO保存BckjBizXsyy信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizXsyy(Map<String, Object> mapData) {
        BckjBizXsyy bckjBizXsyy = JsonUtil.map2Bean(mapData, BckjBizXsyy.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizXsyy bckjBizXsyyIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizXsyy,bckjBizXsyyIndata);
        bckjBizXsyy=bckjBizXsyyIndata;
        }
        saveOrUpdate(bckjBizXsyy);
        return ResponseMessage.sendOK(bckjBizXsyy);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizXsyy </p>
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
            BckjBizXsyy bckjBizXsyy = new BckjBizXsyy();
        bckjBizXsyy.setOwid(owid);
            this.dao.delete(bckjBizXsyy);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}