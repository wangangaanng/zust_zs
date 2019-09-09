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
import com.zghzbckj.manage.entity.BckjBizDcwjDtmx;
import com.zghzbckj.manage.dao.BckjBizDcwjDtmxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizDcwjDtmxService extends CrudService<BckjBizDcwjDtmxDao, BckjBizDcwjDtmx> {

	private static final Logger log = Logger.getLogger(BckjBizDcwjDtmxService.class);

    @Override
	public BckjBizDcwjDtmx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizDcwjDtmx> findList(BckjBizDcwjDtmx bckjBizDcwjDtmx) {
		return super.findList(bckjBizDcwjDtmx);
	}
	@Override
	public PageInfo<BckjBizDcwjDtmx> findPage(Page<BckjBizDcwjDtmx> page, BckjBizDcwjDtmx bckjBizDcwjDtmx) {
		return super.findPage(page, bckjBizDcwjDtmx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizDcwjDtmx bckjBizDcwjDtmx) {
		super.saveOrUpdate(bckjBizDcwjDtmx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizDcwjDtmx bckjBizDcwjDtmx) {
		super.delete(bckjBizDcwjDtmx);
	}


	/**
     * <p>方法:findPagebckjBizDcwjDtmx TODO后台BckjBizDcwjDtmx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizDcwjDtmx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizDcwjDtmx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizDcwjDtmx TODO保存BckjBizDcwjDtmx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizDcwjDtmx(Map<String, Object> mapData) {
        BckjBizDcwjDtmx bckjBizDcwjDtmx = JsonUtil.map2Bean(mapData, BckjBizDcwjDtmx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizDcwjDtmx bckjBizDcwjDtmxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizDcwjDtmx,bckjBizDcwjDtmxIndata);
        bckjBizDcwjDtmx=bckjBizDcwjDtmxIndata;
        }
        saveOrUpdate(bckjBizDcwjDtmx);
        return ResponseMessage.sendOK(bckjBizDcwjDtmx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizDcwjDtmx </p>
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
            BckjBizDcwjDtmx bckjBizDcwjDtmx = new BckjBizDcwjDtmx();
        bckjBizDcwjDtmx.setOwid(owid);
            this.dao.delete(bckjBizDcwjDtmx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}