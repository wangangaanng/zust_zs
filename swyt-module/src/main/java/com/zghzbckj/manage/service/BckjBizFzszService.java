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
import com.zghzbckj.manage.entity.BckjBizFzsz;
import com.zghzbckj.manage.dao.BckjBizFzszDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizFzszService extends CrudService<BckjBizFzszDao, BckjBizFzsz> {

	private static final Logger log = Logger.getLogger(BckjBizFzszService.class);

    @Override
	public BckjBizFzsz get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizFzsz> findList(BckjBizFzsz bckjBizFzsz) {
		return super.findList(bckjBizFzsz);
	}
	@Override
	public PageInfo<BckjBizFzsz> findPage(Page<BckjBizFzsz> page, BckjBizFzsz bckjBizFzsz) {
		return super.findPage(page, bckjBizFzsz);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizFzsz bckjBizFzsz) {
		super.saveOrUpdate(bckjBizFzsz);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizFzsz bckjBizFzsz) {
		super.delete(bckjBizFzsz);
	}


	/**
     * <p>方法:findPagebckjBizFzsz TODO后台BckjBizFzsz分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizFzsz(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizFzsz> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizFzsz TODO保存BckjBizFzsz信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizFzsz(Map<String, Object> mapData) {
        BckjBizFzsz bckjBizFzsz = JsonUtil.map2Bean(mapData, BckjBizFzsz.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizFzsz bckjBizFzszIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizFzsz,bckjBizFzszIndata);
        bckjBizFzsz=bckjBizFzszIndata;
        }
        saveOrUpdate(bckjBizFzsz);
        return ResponseMessage.sendOK(bckjBizFzsz);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizFzsz </p>
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
            BckjBizFzsz bckjBizFzsz = new BckjBizFzsz();
        bckjBizFzsz.setOwid(owid);
            this.dao.delete(bckjBizFzsz);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}