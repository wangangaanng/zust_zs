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
import com.zghzbckj.manage.entity.BckjDicMenu;
import com.zghzbckj.manage.dao.BckjDicMenuDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjDicMenuService extends CrudService<BckjDicMenuDao, BckjDicMenu> {

	private static final Logger log = Logger.getLogger(BckjDicMenuService.class);

    @Override
	public BckjDicMenu get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjDicMenu> findList(BckjDicMenu bckjDicMenu) {
		return super.findList(bckjDicMenu);
	}
	@Override
	public PageInfo<BckjDicMenu> findPage(Page<BckjDicMenu> page, BckjDicMenu bckjDicMenu) {
		return super.findPage(page, bckjDicMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjDicMenu bckjDicMenu) {
		super.saveOrUpdate(bckjDicMenu);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjDicMenu bckjDicMenu) {
		super.delete(bckjDicMenu);
	}


	/**
     * <p>方法:findPagebckjDicMenu TODO后台BckjDicMenu分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjDicMenu(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjDicMenu> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjDicMenu TODO保存BckjDicMenu信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjDicMenu(Map<String, Object> mapData) {
        BckjDicMenu bckjDicMenu = JsonUtil.map2Bean(mapData, BckjDicMenu.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjDicMenu bckjDicMenuIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjDicMenu,bckjDicMenuIndata);
        bckjDicMenu=bckjDicMenuIndata;
        }
        saveOrUpdate(bckjDicMenu);
        return ResponseMessage.sendOK(bckjDicMenu);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjDicMenu </p>
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
            BckjDicMenu bckjDicMenu = new BckjDicMenu();
        bckjDicMenu.setOwid(owid);
            this.dao.delete(bckjDicMenu);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }
	
}