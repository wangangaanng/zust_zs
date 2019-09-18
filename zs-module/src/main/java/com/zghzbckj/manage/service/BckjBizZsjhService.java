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
import com.zghzbckj.manage.entity.BckjBizZsjh;
import com.zghzbckj.manage.dao.BckjBizZsjhDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizZsjhService extends CrudService<BckjBizZsjhDao, BckjBizZsjh> {

	private static final Logger log = Logger.getLogger(BckjBizZsjhService.class);

    @Override
	public BckjBizZsjh get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizZsjh> findList(BckjBizZsjh bckjBizZsjh) {
		return super.findList(bckjBizZsjh);
	}
	@Override
	public PageInfo<BckjBizZsjh> findPage(Page<BckjBizZsjh> page, BckjBizZsjh bckjBizZsjh) {
		return super.findPage(page, bckjBizZsjh);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizZsjh bckjBizZsjh) {
		super.saveOrUpdate(bckjBizZsjh);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizZsjh bckjBizZsjh) {
		super.delete(bckjBizZsjh);
	}


	/**
     * <p>方法:findPagebckjBizZsjh TODO后台BckjBizZsjh分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizZsjh(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizZsjh> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizZsjh TODO保存BckjBizZsjh信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizZsjh(Map<String, Object> mapData) {
        BckjBizZsjh bckjBizZsjh = JsonUtil.map2Bean(mapData, BckjBizZsjh.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizZsjh bckjBizZsjhIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizZsjh,bckjBizZsjhIndata);
        bckjBizZsjh=bckjBizZsjhIndata;
        }
        saveOrUpdate(bckjBizZsjh);
        return ResponseMessage.sendOK(bckjBizZsjh);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizZsjh </p>
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
            BckjBizZsjh bckjBizZsjh = new BckjBizZsjh();
        bckjBizZsjh.setOwid(owid);
            this.dao.delete(bckjBizZsjh);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}