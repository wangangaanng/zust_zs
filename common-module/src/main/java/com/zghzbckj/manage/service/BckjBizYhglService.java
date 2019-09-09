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
import com.zghzbckj.manage.entity.BckjBizYhgl;
import com.zghzbckj.manage.dao.BckjBizYhglDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizYhglService extends CrudService<BckjBizYhglDao, BckjBizYhgl> {

	private static final Logger log = Logger.getLogger(BckjBizYhglService.class);

    @Override
	public BckjBizYhgl get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizYhgl> findList(BckjBizYhgl bckjBizYhgl) {
		return super.findList(bckjBizYhgl);
	}
	@Override
	public PageInfo<BckjBizYhgl> findPage(Page<BckjBizYhgl> page, BckjBizYhgl bckjBizYhgl) {
		return super.findPage(page, bckjBizYhgl);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizYhgl bckjBizYhgl) {
		super.saveOrUpdate(bckjBizYhgl);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizYhgl bckjBizYhgl) {
		super.delete(bckjBizYhgl);
	}


	/**
     * <p>方法:findPagebckjBizYhgl TODO后台BckjBizYhgl分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizYhgl(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizYhgl> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizYhgl TODO保存BckjBizYhgl信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizYhgl(Map<String, Object> mapData) {
        BckjBizYhgl bckjBizYhgl = JsonUtil.map2Bean(mapData, BckjBizYhgl.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizYhgl bckjBizYhglIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizYhgl,bckjBizYhglIndata);
        bckjBizYhgl=bckjBizYhglIndata;
        }
        saveOrUpdate(bckjBizYhgl);
        return ResponseMessage.sendOK(bckjBizYhgl);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizYhgl </p>
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
            BckjBizYhgl bckjBizYhgl = new BckjBizYhgl();
        bckjBizYhgl.setOwid(owid);
            this.dao.delete(bckjBizYhgl);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}