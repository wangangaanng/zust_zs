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
import com.zghzbckj.manage.entity.BckjBizJbxx;
import com.zghzbckj.manage.dao.BckjBizJbxxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJbxxService extends CrudService<BckjBizJbxxDao, BckjBizJbxx> {

	private static final Logger log = Logger.getLogger(BckjBizJbxxService.class);

    @Override
	public BckjBizJbxx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizJbxx> findList(BckjBizJbxx bckjBizJbxx) {
		return super.findList(bckjBizJbxx);
	}
	@Override
	public PageInfo<BckjBizJbxx> findPage(Page<BckjBizJbxx> page, BckjBizJbxx bckjBizJbxx) {
		return super.findPage(page, bckjBizJbxx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizJbxx bckjBizJbxx) {
		super.saveOrUpdate(bckjBizJbxx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizJbxx bckjBizJbxx) {
		super.delete(bckjBizJbxx);
	}


	/**
     * <p>方法:findPagebckjBizJbxx TODO后台BckjBizJbxx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizJbxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizJbxx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizJbxx TODO保存BckjBizJbxx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizJbxx(Map<String, Object> mapData) {
        BckjBizJbxx bckjBizJbxx = JsonUtil.map2Bean(mapData, BckjBizJbxx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizJbxx bckjBizJbxxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizJbxx,bckjBizJbxxIndata);
        bckjBizJbxx=bckjBizJbxxIndata;
        }
        saveOrUpdate(bckjBizJbxx);
        return ResponseMessage.sendOK(bckjBizJbxx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizJbxx </p>
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
            BckjBizJbxx bckjBizJbxx = new BckjBizJbxx();
        bckjBizJbxx.setOwid(owid);
            this.dao.delete(bckjBizJbxx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}