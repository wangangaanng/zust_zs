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
import com.zghzbckj.manage.entity.BckjDicKeys;
import com.zghzbckj.manage.dao.BckjDicKeysDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjDicKeysService extends CrudService<BckjDicKeysDao, BckjDicKeys> {

	private static final Logger log = Logger.getLogger(BckjDicKeysService.class);

    @Override
	public BckjDicKeys get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjDicKeys> findList(BckjDicKeys bckjDicKeys) {
		return super.findList(bckjDicKeys);
	}
	@Override
	public PageInfo<BckjDicKeys> findPage(Page<BckjDicKeys> page, BckjDicKeys bckjDicKeys) {
		return super.findPage(page, bckjDicKeys);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjDicKeys bckjDicKeys) {
		super.saveOrUpdate(bckjDicKeys);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjDicKeys bckjDicKeys) {
		super.delete(bckjDicKeys);
	}


	/**
     * <p>方法:findPagebckjDicKeys TODO后台BckjDicKeys分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjDicKeys(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjDicKeys> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjDicKeys TODO保存BckjDicKeys信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjDicKeys(Map<String, Object> mapData) {
        BckjDicKeys bckjDicKeys = JsonUtil.map2Bean(mapData, BckjDicKeys.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjDicKeys bckjDicKeysIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjDicKeys,bckjDicKeysIndata);
        bckjDicKeys=bckjDicKeysIndata;
        }
        saveOrUpdate(bckjDicKeys);
        return ResponseMessage.sendOK(bckjDicKeys);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjDicKeys </p>
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
            BckjDicKeys bckjDicKeys = new BckjDicKeys();
        bckjDicKeys.setOwid(owid);
            this.dao.delete(bckjDicKeys);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}