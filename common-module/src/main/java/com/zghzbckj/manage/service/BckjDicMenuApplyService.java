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
import com.zghzbckj.manage.dao.BckjDicMenuApplyDao;
import com.zghzbckj.manage.entity.BckjDicMenuApply;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjDicMenuApplyService extends CrudService<BckjDicMenuApplyDao, BckjDicMenuApply> {

    private static final Logger log = Logger.getLogger(BckjDicMenuApplyService.class);

    @Override
    public BckjDicMenuApply get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjDicMenuApply> findList(BckjDicMenuApply bckjDicMenuApply) {
        return super.findList(bckjDicMenuApply);
    }

    @Override
    public PageInfo<BckjDicMenuApply> findPage(Page<BckjDicMenuApply> page, BckjDicMenuApply bckjDicMenuApply) {
        return super.findPage(page, bckjDicMenuApply);
    }

    @Transactional(readOnly = false)
    public void save(BckjDicMenuApply bckjDicMenuApply) {
        super.saveOrUpdate(bckjDicMenuApply);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjDicMenuApply bckjDicMenuApply) {
        super.delete(bckjDicMenuApply);
    }


    /**
     * <p>方法:findPagebckjDicMenuApply TODO后台BckjDicMenuApply分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjDicMenuApply(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjDicMenuApply> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjDicMenuApply TODO保存BckjDicMenuApply信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjDicMenuApply(Map<String, Object> mapData) {
        BckjDicMenuApply bckjDicMenuApply = JsonUtil.map2Bean(mapData, BckjDicMenuApply.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjDicMenuApply bckjDicMenuApplyIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjDicMenuApply, bckjDicMenuApplyIndata);
            bckjDicMenuApply = bckjDicMenuApplyIndata;
        }
        saveOrUpdate(bckjDicMenuApply);
        return ResponseMessage.sendOK(bckjDicMenuApply);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjDicMenuApply </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : codes) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            BckjDicMenuApply bckjDicMenuApply = new BckjDicMenuApply();
            bckjDicMenuApply.setOwid(Long.valueOf(owid));
            this.dao.delete(bckjDicMenuApply);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public Object saveMeunApply(Map<String, Object> mapData) {
        Integer owid = Integer.valueOf(mapData.get("owid").toString());
        if (!TextUtils.isEmpty(mapData.get("dataList1"))) {
            List<Map<String, Object>> dataList = (List) mapData.get("dataList1");
            for (Map<String, Object> map : dataList) {
                //删除记录
                if (map.get("updateFlag").equals(2)) {
                    this.deleteByMap(map);
                    continue;
                } else {
                    BckjDicMenuApply menuApply = JsonUtil.map2Bean(map, BckjDicMenuApply.class);
                    if(!TextUtils.isEmpty(menuApply.getOwid())){
                        BckjDicMenuApply   menuApplyIndata=this.get(menuApply.getOwid());
                        BeanUtil.copyPropertiesIgnoreNull(menuApply,menuApplyIndata);
                        menuApply=menuApplyIndata;
                    }else {
                        menuApply.setMenuRefOwid(owid);
                    }
                    this.saveOrUpdate(menuApply);
                }
            }
        }
        return Boolean.TRUE;
    }
}