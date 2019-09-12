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
import com.zghzbckj.manage.dao.BckjBizJypmDao;
import com.zghzbckj.manage.entity.BckjBizJypm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BckjBizJypmService extends CrudService<BckjBizJypmDao, BckjBizJypm> {

	private static final Logger log = Logger.getLogger(BckjBizJypmService.class);

	@Autowired
    BckjBizJypmDao bckjBizJypmDao;

    @Override
	public BckjBizJypm get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizJypm> findList(BckjBizJypm bckjBizJypm) {
		return super.findList(bckjBizJypm);
	}
	@Override
	public PageInfo<BckjBizJypm> findPage(Page<BckjBizJypm> page, BckjBizJypm bckjBizJypm) {
		return super.findPage(page, bckjBizJypm);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizJypm bckjBizJypm) {
		super.saveOrUpdate(bckjBizJypm);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizJypm bckjBizJypm) {
		super.delete(bckjBizJypm);
	}

	/**
     *<p>功能描述:返回根据学院合并过的map listAll</p >
     *<ul>
     *<li>@param []</li>
     *<li>@return java.util.Map</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/9/11 16:09</li>
     *</ul>
     */
    public Map<String, List<Map<String, Object>>> listAll() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("orderBy", "szxy");
        //根据所在学院字段分组
        List<BckjBizJypm> dataList = this.dao.findListByMap(dataMap);
        if (null == dataList) {
            return null;
        }
        List<Map<String, Object>> jypmList = new ArrayList<>();
        for (BckjBizJypm pm : dataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("szxy", pm.getSzxy());
            map.put("pmzy", pm.getPmzy());
            map.put("pmbj", pm.getPmbj());
            map.put("pmbyrs", pm.getPmbyrs());
            map.put("pmyprs", pm.getPmyprs());
            map.put("pmqyrs", pm.getPmqyrs());
            map.put("pmqyl", pm.getPmqyl());
            map.put("pmjyl", pm.getPmjyl());
            map.put("pmmc", pm.getPmmc());
            jypmList.add(map);
        }
        //根据所在学院合并
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        for (Map<String, Object> map : jypmList) {
            if (result.containsKey(map.get("szxy"))) {
                result.get(map.get("szxy")).add(map);
            } else {
                List<Map<String, Object>> list = new ArrayList<>();
                list.add(map);
                result.put(map.get("szxy").toString(), list);
            }
        }
        return result;
    }

	/**
     * <p>方法:findPagebckjBizJypm TODO后台BckjBizJypm分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizJypm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizJypm> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizJypm TODO保存BckjBizJypm信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizJypm(Map<String, Object> mapData) {
        BckjBizJypm bckjBizJypm = JsonUtil.map2Bean(mapData, BckjBizJypm.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizJypm bckjBizJypmIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizJypm,bckjBizJypmIndata);
        bckjBizJypm=bckjBizJypmIndata;
        }
        saveOrUpdate(bckjBizJypm);
        return ResponseMessage.sendOK(bckjBizJypm);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizJypm </p>
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
            BckjBizJypm bckjBizJypm = new BckjBizJypm();
        bckjBizJypm.setOwid(owid);
            this.dao.delete(bckjBizJypm);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}