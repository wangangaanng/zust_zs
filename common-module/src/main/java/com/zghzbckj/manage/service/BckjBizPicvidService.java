/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizPicvidDao;
import com.zghzbckj.manage.entity.BckjBizPicvid;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizPicvidService extends CrudService<BckjBizPicvidDao, BckjBizPicvid> {

	private static final Logger log = Logger.getLogger(BckjBizPicvidService.class);

    @Override
	public BckjBizPicvid get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizPicvid> findList(BckjBizPicvid bckjBizPicvid) {
		return super.findList(bckjBizPicvid);
	}
	@Override
	public PageInfo<BckjBizPicvid> findPage(Page<BckjBizPicvid> page, BckjBizPicvid bckjBizPicvid) {
		return super.findPage(page, bckjBizPicvid);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizPicvid bckjBizPicvid) {
		super.saveOrUpdate(bckjBizPicvid);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizPicvid bckjBizPicvid) {
		super.delete(bckjBizPicvid);
	}


	/**
     * <p>方法:findPagebckjBizPicvid TODO后台BckjBizPicvid分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizPicvid(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizPicvid> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizPicvid TODO保存BckjBizPicvid信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizPicvid(Map<String, Object> mapData) {
        BckjBizPicvid bckjBizPicvid = JsonUtil.map2Bean(mapData, BckjBizPicvid.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizPicvid bckjBizPicvidIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizPicvid,bckjBizPicvidIndata);
        bckjBizPicvid=bckjBizPicvidIndata;
        }
        saveOrUpdate(bckjBizPicvid);
        return ResponseMessage.sendOK(bckjBizPicvid);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizPicvid </p>
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
            BckjBizPicvid bckjBizPicvid = new BckjBizPicvid();
        bckjBizPicvid.setOwid(owid);
            this.dao.delete(bckjBizPicvid);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}