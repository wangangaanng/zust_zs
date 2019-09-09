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
import com.zghzbckj.manage.entity.BckjBizBkyx;
import com.zghzbckj.manage.dao.BckjBizBkyxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizBkyxService extends CrudService<BckjBizBkyxDao, BckjBizBkyx> {

	private static final Logger log = Logger.getLogger(BckjBizBkyxService.class);

    @Override
	public BckjBizBkyx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizBkyx> findList(BckjBizBkyx bckjBizBkyx) {
		return super.findList(bckjBizBkyx);
	}
	@Override
	public PageInfo<BckjBizBkyx> findPage(Page<BckjBizBkyx> page, BckjBizBkyx bckjBizBkyx) {
		return super.findPage(page, bckjBizBkyx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizBkyx bckjBizBkyx) {
		super.saveOrUpdate(bckjBizBkyx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizBkyx bckjBizBkyx) {
		super.delete(bckjBizBkyx);
	}


	/**
     * <p>方法:findPagebckjBizBkyx TODO后台BckjBizBkyx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizBkyx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizBkyx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizBkyx TODO保存BckjBizBkyx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizBkyx(Map<String, Object> mapData) {
        BckjBizBkyx bckjBizBkyx = JsonUtil.map2Bean(mapData, BckjBizBkyx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizBkyx bckjBizBkyxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizBkyx,bckjBizBkyxIndata);
        bckjBizBkyx=bckjBizBkyxIndata;
        }
        saveOrUpdate(bckjBizBkyx);
        return ResponseMessage.sendOK(bckjBizBkyx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizBkyx </p>
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
            BckjBizBkyx bckjBizBkyx = new BckjBizBkyx();
        bckjBizBkyx.setOwid(owid);
            this.dao.delete(bckjBizBkyx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}