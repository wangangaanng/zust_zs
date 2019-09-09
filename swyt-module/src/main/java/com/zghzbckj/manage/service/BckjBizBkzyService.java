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
import com.zghzbckj.manage.entity.BckjBizBkzy;
import com.zghzbckj.manage.dao.BckjBizBkzyDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizBkzyService extends CrudService<BckjBizBkzyDao, BckjBizBkzy> {

	private static final Logger log = Logger.getLogger(BckjBizBkzyService.class);

    @Override
	public BckjBizBkzy get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizBkzy> findList(BckjBizBkzy bckjBizBkzy) {
		return super.findList(bckjBizBkzy);
	}
	@Override
	public PageInfo<BckjBizBkzy> findPage(Page<BckjBizBkzy> page, BckjBizBkzy bckjBizBkzy) {
		return super.findPage(page, bckjBizBkzy);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizBkzy bckjBizBkzy) {
		super.saveOrUpdate(bckjBizBkzy);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizBkzy bckjBizBkzy) {
		super.delete(bckjBizBkzy);
	}


	/**
     * <p>方法:findPagebckjBizBkzy TODO后台BckjBizBkzy分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizBkzy(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizBkzy> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizBkzy TODO保存BckjBizBkzy信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizBkzy(Map<String, Object> mapData) {
        BckjBizBkzy bckjBizBkzy = JsonUtil.map2Bean(mapData, BckjBizBkzy.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizBkzy bckjBizBkzyIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizBkzy,bckjBizBkzyIndata);
        bckjBizBkzy=bckjBizBkzyIndata;
        }
        saveOrUpdate(bckjBizBkzy);
        return ResponseMessage.sendOK(bckjBizBkzy);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizBkzy </p>
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
            BckjBizBkzy bckjBizBkzy = new BckjBizBkzy();
        bckjBizBkzy.setOwid(owid);
            this.dao.delete(bckjBizBkzy);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}