/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

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
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.dao.BckjBizSybDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class BckjBizSybService extends CrudService<BckjBizSybDao, BckjBizSyb> {

	private static final Logger log = Logger.getLogger(BckjBizSybService.class);

    @Override
	public BckjBizSyb get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizSyb> findList(BckjBizSyb bckjBizSyb) {
		return super.findList(bckjBizSyb);
	}
	@Override
	public PageInfo<BckjBizSyb> findPage(Page<BckjBizSyb> page, BckjBizSyb bckjBizSyb) {
		return super.findPage(page, bckjBizSyb);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizSyb bckjBizSyb) {
		super.saveOrUpdate(bckjBizSyb);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizSyb bckjBizSyb) {
		super.delete(bckjBizSyb);
	}


	/**
     * <p>方法:findPagebckjBizSyb TODO后台BckjBizSyb分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizSyb(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizSyb> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizSyb TODO保存BckjBizSyb信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizSyb(Map<String, Object> mapData) {
        BckjBizSyb bckjBizSyb = JsonUtil.map2Bean(mapData, BckjBizSyb.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizSyb bckjBizSybIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizSyb,bckjBizSybIndata);
        bckjBizSyb=bckjBizSybIndata;
        }
        saveOrUpdate(bckjBizSyb);
        return ResponseMessage.sendOK(bckjBizSyb);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizSyb </p>
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
            BckjBizSyb bckjBizSyb = new BckjBizSyb();
        bckjBizSyb.setOwid(owid);
            this.dao.delete(bckjBizSyb);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}