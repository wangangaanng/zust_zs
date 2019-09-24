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
//import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizJtcyxx;
import com.zghzbckj.manage.dao.BckjBizJtcyxxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJtcyxxService extends CrudService<BckjBizJtcyxxDao, BckjBizJtcyxx> {

	private static final Logger log = Logger.getLogger(BckjBizJtcyxxService.class);

    @Override
	public BckjBizJtcyxx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizJtcyxx> findList(BckjBizJtcyxx bckjBizJtcyxx) {
		return super.findList(bckjBizJtcyxx);
	}
	@Override
	public PageInfo<BckjBizJtcyxx> findPage(Page<BckjBizJtcyxx> page, BckjBizJtcyxx bckjBizJtcyxx) {
		return super.findPage(page, bckjBizJtcyxx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizJtcyxx bckjBizJtcyxx) {
		super.saveOrUpdate(bckjBizJtcyxx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizJtcyxx bckjBizJtcyxx) {
		super.delete(bckjBizJtcyxx);
	}


	/**
     * <p>方法:findPagebckjBizJtcyxx TODO后台BckjBizJtcyxx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizJtcyxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizJtcyxx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizJtcyxx TODO保存BckjBizJtcyxx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizJtcyxx(Map<String, Object> mapData) {
        BckjBizJtcyxx bckjBizJtcyxx = JsonUtil.map2Bean(mapData, BckjBizJtcyxx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizJtcyxx bckjBizJtcyxxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizJtcyxx,bckjBizJtcyxxIndata);
        bckjBizJtcyxx=bckjBizJtcyxxIndata;
        }
        saveOrUpdate(bckjBizJtcyxx);
        return ResponseMessage.sendOK(bckjBizJtcyxx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizJtcyxx </p>
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
            BckjBizJtcyxx bckjBizJtcyxx = new BckjBizJtcyxx();
        bckjBizJtcyxx.setOwid(owid);
            this.dao.delete(bckjBizJtcyxx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}