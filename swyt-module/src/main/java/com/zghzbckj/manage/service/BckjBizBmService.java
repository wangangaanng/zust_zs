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
import com.zghzbckj.manage.entity.BckjBizBm;
import com.zghzbckj.manage.dao.BckjBizBmDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizBmService extends CrudService<BckjBizBmDao, BckjBizBm> {

	private static final Logger log = Logger.getLogger(BckjBizBmService.class);

    @Override
	public BckjBizBm get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizBm> findList(BckjBizBm bckjBizBm) {
		return super.findList(bckjBizBm);
	}
	@Override
	public PageInfo<BckjBizBm> findPage(Page<BckjBizBm> page, BckjBizBm bckjBizBm) {
		return super.findPage(page, bckjBizBm);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizBm bckjBizBm) {
		super.saveOrUpdate(bckjBizBm);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizBm bckjBizBm) {
		super.delete(bckjBizBm);
	}


	/**
     * <p>方法:findPagebckjBizBm TODO后台BckjBizBm分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizBm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizBm> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizBm TODO保存BckjBizBm信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizBm(Map<String, Object> mapData) {
        BckjBizBm bckjBizBm = JsonUtil.map2Bean(mapData, BckjBizBm.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizBm bckjBizBmIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizBm,bckjBizBmIndata);
        bckjBizBm=bckjBizBmIndata;
        }
        saveOrUpdate(bckjBizBm);
        return ResponseMessage.sendOK(bckjBizBm);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizBm </p>
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
            BckjBizBm bckjBizBm = new BckjBizBm();
        bckjBizBm.setOwid(owid);
            this.dao.delete(bckjBizBm);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}