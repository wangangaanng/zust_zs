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
import com.zghzbckj.manage.entity.BckjBizDcwjTm;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizDcwjTmService extends CrudService<BckjBizDcwjTmDao, BckjBizDcwjTm> {

	private static final Logger log = Logger.getLogger(BckjBizDcwjTmService.class);

    @Override
	public BckjBizDcwjTm get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizDcwjTm> findList(BckjBizDcwjTm bckjBizDcwjTm) {
		return super.findList(bckjBizDcwjTm);
	}
	@Override
	public PageInfo<BckjBizDcwjTm> findPage(Page<BckjBizDcwjTm> page, BckjBizDcwjTm bckjBizDcwjTm) {
		return super.findPage(page, bckjBizDcwjTm);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizDcwjTm bckjBizDcwjTm) {
		super.saveOrUpdate(bckjBizDcwjTm);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizDcwjTm bckjBizDcwjTm) {
		super.delete(bckjBizDcwjTm);
	}


	/**
     * <p>方法:findPagebckjBizDcwjTm TODO后台BckjBizDcwjTm分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizDcwjTm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizDcwjTm> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizDcwjTm TODO保存BckjBizDcwjTm信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizDcwjTm(Map<String, Object> mapData) {
        BckjBizDcwjTm bckjBizDcwjTm = JsonUtil.map2Bean(mapData, BckjBizDcwjTm.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizDcwjTm bckjBizDcwjTmIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizDcwjTm,bckjBizDcwjTmIndata);
        bckjBizDcwjTm=bckjBizDcwjTmIndata;
        }
        saveOrUpdate(bckjBizDcwjTm);
        return ResponseMessage.sendOK(bckjBizDcwjTm);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizDcwjTm </p>
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
            BckjBizDcwjTm bckjBizDcwjTm = new BckjBizDcwjTm();
        bckjBizDcwjTm.setOwid(owid);
            this.dao.delete(bckjBizDcwjTm);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}