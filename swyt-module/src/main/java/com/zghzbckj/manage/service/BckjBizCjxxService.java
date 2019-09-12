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
import com.zghzbckj.manage.entity.BckjBizCjxx;
import com.zghzbckj.manage.dao.BckjBizCjxxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizCjxxService extends CrudService<BckjBizCjxxDao, BckjBizCjxx> {

	private static final Logger log = Logger.getLogger(BckjBizCjxxService.class);

    @Override
	public BckjBizCjxx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizCjxx> findList(BckjBizCjxx bckjBizCjxx) {
		return super.findList(bckjBizCjxx);
	}
	@Override
	public PageInfo<BckjBizCjxx> findPage(Page<BckjBizCjxx> page, BckjBizCjxx bckjBizCjxx) {
		return super.findPage(page, bckjBizCjxx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizCjxx bckjBizCjxx) {
		super.saveOrUpdate(bckjBizCjxx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizCjxx bckjBizCjxx) {
		super.delete(bckjBizCjxx);
	}


	/**
     * <p>方法:findPagebckjBizCjxx TODO后台BckjBizCjxx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizCjxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizCjxx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizCjxx TODO保存BckjBizCjxx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizCjxx(Map<String, Object> mapData) {
        BckjBizCjxx bckjBizCjxx = JsonUtil.map2Bean(mapData, BckjBizCjxx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizCjxx bckjBizCjxxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizCjxx,bckjBizCjxxIndata);
        bckjBizCjxx=bckjBizCjxxIndata;
        }
        saveOrUpdate(bckjBizCjxx);
        return ResponseMessage.sendOK(bckjBizCjxx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizCjxx </p>
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
            BckjBizCjxx bckjBizCjxx = new BckjBizCjxx();
        bckjBizCjxx.setOwid(owid);
            this.dao.delete(bckjBizCjxx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}