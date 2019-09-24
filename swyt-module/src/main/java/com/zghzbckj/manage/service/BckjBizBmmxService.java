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
//import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizBmmx;
import com.zghzbckj.manage.dao.BckjBizBmmxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizBmmxService extends CrudService<BckjBizBmmxDao, BckjBizBmmx> {

	private static final Logger log = Logger.getLogger(BckjBizBmmxService.class);

    @Override
	public BckjBizBmmx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizBmmx> findList(BckjBizBmmx bckjBizBmmx) {
		return super.findList(bckjBizBmmx);
	}
	@Override
	public PageInfo<BckjBizBmmx> findPage(Page<BckjBizBmmx> page, BckjBizBmmx bckjBizBmmx) {
		return super.findPage(page, bckjBizBmmx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizBmmx bckjBizBmmx) {
		super.saveOrUpdate(bckjBizBmmx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizBmmx bckjBizBmmx) {
		super.delete(bckjBizBmmx);
	}


	/**
     * <p>方法:findPagebckjBizBmmx TODO后台BckjBizBmmx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizBmmx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizBmmx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizBmmx TODO保存BckjBizBmmx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizBmmx(Map<String, Object> mapData) {
        BckjBizBmmx bckjBizBmmx = JsonUtil.map2Bean(mapData, BckjBizBmmx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizBmmx bckjBizBmmxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizBmmx,bckjBizBmmxIndata);
        bckjBizBmmx=bckjBizBmmxIndata;
        }
        saveOrUpdate(bckjBizBmmx);
        return ResponseMessage.sendOK(bckjBizBmmx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizBmmx </p>
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
            BckjBizBmmx bckjBizBmmx = new BckjBizBmmx();
        bckjBizBmmx.setOwid(owid);
            this.dao.delete(bckjBizBmmx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}