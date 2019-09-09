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
import com.zghzbckj.manage.entity.BckjBizXxpz;
import com.zghzbckj.manage.dao.BckjBizXxpzDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizXxpzService extends CrudService<BckjBizXxpzDao, BckjBizXxpz> {

	private static final Logger log = Logger.getLogger(BckjBizXxpzService.class);

    @Override
	public BckjBizXxpz get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizXxpz> findList(BckjBizXxpz bckjBizXxpz) {
		return super.findList(bckjBizXxpz);
	}
	@Override
	public PageInfo<BckjBizXxpz> findPage(Page<BckjBizXxpz> page, BckjBizXxpz bckjBizXxpz) {
		return super.findPage(page, bckjBizXxpz);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizXxpz bckjBizXxpz) {
		super.saveOrUpdate(bckjBizXxpz);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizXxpz bckjBizXxpz) {
		super.delete(bckjBizXxpz);
	}


	/**
     * <p>方法:findPagebckjBizXxpz TODO后台BckjBizXxpz分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizXxpz(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizXxpz> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizXxpz TODO保存BckjBizXxpz信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizXxpz(Map<String, Object> mapData) {
        BckjBizXxpz bckjBizXxpz = JsonUtil.map2Bean(mapData, BckjBizXxpz.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizXxpz bckjBizXxpzIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizXxpz,bckjBizXxpzIndata);
        bckjBizXxpz=bckjBizXxpzIndata;
        }
        saveOrUpdate(bckjBizXxpz);
        return ResponseMessage.sendOK(bckjBizXxpz);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizXxpz </p>
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
            BckjBizXxpz bckjBizXxpz = new BckjBizXxpz();
        bckjBizXxpz.setOwid(owid);
            this.dao.delete(bckjBizXxpz);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}