/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.JsonUtil;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.manage.dao.BckjDicKeysDao;
import com.zghzbckj.manage.entity.BckjDicKeys;
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
public class BckjDicKeysService extends CrudService<BckjDicKeysDao, BckjDicKeys> {

    private static final Logger log = Logger.getLogger(BckjDicKeysService.class);

    @Override
    public BckjDicKeys get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjDicKeys> findList(BckjDicKeys bckjDicKeys) {
        return super.findList(bckjDicKeys);
    }

    @Override
    public PageInfo<BckjDicKeys> findPage(Page<BckjDicKeys> page, BckjDicKeys bckjDicKeys) {
        return super.findPage(page, bckjDicKeys);
    }

    @Transactional(readOnly = false)
    public void save(BckjDicKeys bckjDicKeys) {
        super.saveOrUpdate(bckjDicKeys);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjDicKeys bckjDicKeys) {
        super.delete(bckjDicKeys);
    }


    /**
     * <p>方法:findPagebckjDicKeys TODO后台BckjDicKeys分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjDicKeys(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjDicKeys> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjDicKeys TODO保存BckjDicKeys信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjDicKeys(Map<String, Object> dataMap) {
        List<BckjDicKeys> keys=null;
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List) dataMap.get("dataList");
            keys = JsonUtil.map2List(components, BckjDicKeys.class);
        }else{
            return ResponseMessage.sendError(ResponseMessage.FAIL,"no data");
        }
        for(BckjDicKeys one:keys){
           if(one.getUpdateFlag()==2){
               this.delete(one);
           }else{
               this.saveOrUpdate(one);
           }
        }
        return ResponseMessage.sendOK(this.findList(new BckjDicKeys()));
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjDicKeys </p>
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
            BckjDicKeys bckjDicKeys = new BckjDicKeys();
            bckjDicKeys.setOwid(owid);
            this.dao.delete(bckjDicKeys);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public String filterContent(Map<String, Object> mapData) {
        String content=mapData.get("content").toString();
        List<BckjDicKeys> allKeys=this.dao.findListByMap(mapData);
        StringBuffer sf=new StringBuffer();
        for(BckjDicKeys oneKey:allKeys){
            if(content.contains(oneKey.getKeyWord())){
                sf.append(oneKey.getKeyWord()+ CommonModuleContant.SPILE_DOUHAO);
            }
        }
        return sf.substring(0,sf.lastIndexOf(","));
    }

}