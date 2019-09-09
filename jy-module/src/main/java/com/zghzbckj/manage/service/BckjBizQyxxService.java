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
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizQyxxService extends CrudService<BckjBizQyxxDao, BckjBizQyxx> {

	private static final Logger log = Logger.getLogger(BckjBizQyxxService.class);

    @Override
	public BckjBizQyxx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizQyxx> findList(BckjBizQyxx bckjBizQyxx) {
		return super.findList(bckjBizQyxx);
	}
	@Override
	public PageInfo<BckjBizQyxx> findPage(Page<BckjBizQyxx> page, BckjBizQyxx bckjBizQyxx) {
		return super.findPage(page, bckjBizQyxx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizQyxx bckjBizQyxx) {
		super.saveOrUpdate(bckjBizQyxx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizQyxx bckjBizQyxx) {
		super.delete(bckjBizQyxx);
	}


	/**
     * <p>方法:findPagebckjBizQyxx TODO后台BckjBizQyxx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizQyxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizQyxx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizQyxx TODO保存BckjBizQyxx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizQyxx(Map<String, Object> mapData) {
        BckjBizQyxx bckjBizQyxx = JsonUtil.map2Bean(mapData, BckjBizQyxx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizQyxx bckjBizQyxxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizQyxx,bckjBizQyxxIndata);
        bckjBizQyxx=bckjBizQyxxIndata;
        }
        saveOrUpdate(bckjBizQyxx);
        return ResponseMessage.sendOK(bckjBizQyxx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizQyxx </p>
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
            BckjBizQyxx bckjBizQyxx = new BckjBizQyxx();
        bckjBizQyxx.setOwid(owid);
            this.dao.delete(bckjBizQyxx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}