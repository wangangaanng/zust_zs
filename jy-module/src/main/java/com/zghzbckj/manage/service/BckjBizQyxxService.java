/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;
import com.zghzbckj.manage.entity.BckjBizQyxx;
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
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizQyxxService extends CrudService<BckjBizQyxxDao, BckjBizQyxx> {

    private static final Logger log = Logger.getLogger(BckjBizQyxxService.class);
    @Autowired
    BckjBizQyxxDao qyxxDao;

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
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizQyxx bckjBizQyxxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizQyxx, bckjBizQyxxIndata);
            bckjBizQyxx = bckjBizQyxxIndata;
        }
        saveOrUpdate(bckjBizQyxx);
        return ResponseMessage.sendOK(bckjBizQyxx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizQyxx </p>
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
            BckjBizQyxx bckjBizQyxx = new BckjBizQyxx();
            bckjBizQyxx.setOwid(owid);
            this.dao.delete(bckjBizQyxx);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }


    @Transactional(readOnly = false)
    public Map companyRegister(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizQyxx company = new BckjBizQyxx();
        try {
            company = MapUtils.map2Bean(mapData, BckjBizQyxx.class);
            company.setState(JyContant.QY_ZT_DSH);
            saveOrUpdate(company);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", company);
        return resultMap;

    }

    public Map login(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        Map params = new HashMap<>();
        params.put("state", JyContant.QY_ZT_TG);
        params.put("qyTysh", resultMap.get("qyTysh"));
        BckjBizQyxx company = qyxxDao.getOne(params);
        if (TextUtils.isEmpty(company)) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.SH_ERROR_MESSAGE);
            return resultMap;
        }
        String sfzStr = company.getQyFrsfz();
        String getSfzStr = mapData.get("qyFrsfz").toString();
        if (!sfzStr.substring(sfzStr.length() - 6, sfzStr.length()).equals(getSfzStr)) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.SFZ_ERROR_MESSAGE);
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", company);
        return resultMap;
    }

    public Map fixCompany(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizQyxx company =new BckjBizQyxx();
        try {
            company = MapUtils.map2Bean(mapData, BckjBizQyxx.class);
            saveOrUpdate(company);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", company);
        return resultMap;
    }
}