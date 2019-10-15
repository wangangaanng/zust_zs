/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizSybDao;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.util.PageUtils;
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
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class BckjBizSybService extends CrudService<BckjBizSybDao, BckjBizSyb> {

    private static final Logger log = Logger.getLogger(BckjBizSybService.class);

    @Override
    public BckjBizSyb get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizSyb> findList(BckjBizSyb bckjBizSyb) {
        return super.findList(bckjBizSyb);
    }

    @Override
    public PageInfo<BckjBizSyb> findPage(Page<BckjBizSyb> page, BckjBizSyb bckjBizSyb) {
        return super.findPage(page, bckjBizSyb);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizSyb bckjBizSyb) {
        super.saveOrUpdate(bckjBizSyb);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizSyb bckjBizSyb) {
        super.delete(bckjBizSyb);
    }


    /**
     * <p>方法:findPagebckjBizSyb TODO后台BckjBizSyb分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizSyb(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizSyb> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizSyb TODO保存BckjBizSyb信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizSyb(BckjBizSyb bckjBizSyb) {
        BckjBizSyb xh =this.dao.findByXh(bckjBizSyb.getXh());
        if (!TextUtils.isEmpty(bckjBizSyb.getOwid())) {
            if(null!=xh&&!(xh.getOwid().equals(bckjBizSyb.getOwid()))){
                return ResponseMessage.sendError(ResponseMessage.FAIL,"已存在此学号学生");
            }
            BckjBizSyb bckjBizSybIndata = get(bckjBizSyb.getOwid());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizSyb, bckjBizSybIndata);
            bckjBizSyb = bckjBizSybIndata;
        }else{
            if(null!=xh){
                return ResponseMessage.sendError(ResponseMessage.FAIL,"已存在此学号学生");
            }
        }
        saveOrUpdate(bckjBizSyb);
        return ResponseMessage.sendOK(bckjBizSyb);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizSyb </p>
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
            BckjBizSyb bckjBizSyb = new BckjBizSyb();
            bckjBizSyb.setOwid(owid);
            this.dao.delete(bckjBizSyb);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public BckjBizSyb getSyInfo(Map<String, Object> mapData) {
        Map userInfo=this.dao.getUserXh(mapData);
        BckjBizSyb syb=null;
        if(null!=userInfo.get("XSXH")){
             syb=this.dao.findByXh(userInfo.get("XSXH").toString());
            if(null==syb){
                syb =new BckjBizSyb();
                syb.setXh(userInfo.get("XSXH").toString());
                if(null!=userInfo.get("XM")){
                    syb.setXm(userInfo.get("XM").toString());
                }
                if(null!=userInfo.get("SJH")){
                    syb.setSjhm(userInfo.get("SJH").toString());
                }
                if(null!=userInfo.get("SFZ")){
                    syb.setSfzh(userInfo.get("SFZ").toString());
                }
                if(null!=userInfo.get("XB")){
                    syb.setXb(Integer.valueOf(userInfo.get("XB").toString()));
                }
                if(null!=userInfo.get("MZ")){
                    syb.setMz(userInfo.get("MZ").toString());
                }
                if(null!=userInfo.get("CSRQ")){
                    syb.setCsrq(userInfo.get("CSRQ").toString());
                }
                if(null!=userInfo.get("XSBJ")){
                    syb.setSzbj(userInfo.get("XSBJ").toString());
                }
                if(null!=userInfo.get("XSZY")){
                    syb.setXxzy(userInfo.get("XSZY").toString());
                }
                if(null!=userInfo.get("XSXY")){
                    syb.setSsxy(userInfo.get("XSXY").toString());
                }
            }
        }
        return syb;
    }
    /**
     * 后台生源管理获得gridlist
     * @param filters
     * @param pageNo
     * @param pageSize
     * @return PageInfo<Object>
     */
    public PageInfo<Object> getSybList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Object> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        List<Object> lists = null;
        lists = this.dao.getSybList(dataMap);
        page.setList(lists);
        return PageUtils.assimblePageInfo(page);
    }
}