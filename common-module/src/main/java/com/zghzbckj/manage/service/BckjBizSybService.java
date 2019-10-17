/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.IdGen;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizSybDao;
import com.zghzbckj.manage.entity.*;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.PageUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    BckjBizYhkzService bckjBizYhkzService;
    @Autowired
    BckjBizJypuchongService bckjBizJypuchongService;
    @Autowired
    BckjBizStudentinfoService bckjBizStudentinfoService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    BckjBizJyschemeService bckjBizJyschemeService;


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

    /**
     * 前端获得学生生源地信息
     * @param mapData
     * @return Map
     */
    public Map<String, Object> getSyInfo(Map<String, Object> mapData) {
        Map<String, Object> resMap = Maps.newHashMap();
        return resMap = bckjBizYhxxService.getStudentOne(mapData);
    }





    /**
     * <p>方法:后台录入就业方案   syd 部分</p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    public void updataInfo(BckjBizSyb bckjBizSyb){
        this.dao.updataInfo(bckjBizSyb);
    }

    public void updateByXsxh(BckjBizSyb bckjBizSyb) {
        this.dao.updateByXsxh(bckjBizSyb);
    }

    public void deleteByXsxh(String xh) {
        this.dao.deleteByXsxh(xh);
    }

    public void updateJyscheme(BckjBizSyb bckjBizSyb) {
        this.dao.updateJyscheme(bckjBizSyb);
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

    public Object getOne(String owid) {
        return this.dao.getOne(owid);
    }

    /**
     * 后台保存或更新生源管理信息
     * @param dataMap
     * @return ResponseMessage
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage insertssInfo(Map<String, Object> dataMap) {

        BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
        BckjBizStudentinfo bckjBizStudentinfo=new BckjBizStudentinfo();
        BckjBizYhkz bckjBizYhkz=new BckjBizYhkz();
        BckjBizYhxx bckjBizYhxx=new BckjBizYhxx();
        BckjBizSyb bckjBizSyb=new BckjBizSyb();
        BckjBizJypuchong bckjBizJypuchong = new BckjBizJypuchong();

        dataMap.put("xh", dataMap.get("xsxh"));
        dataMap.put("xsxm", dataMap.get("xm"));
        dataMap.put("yhRefOwid",dataMap.get("owid"));
        BckjBizYhkz bckjBizYhkz1 = bckjBizYhkzService.getByXsxh(dataMap.get("xsxh").toString());

        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        MapUtil.easySetByMap(dataMap, bckjBizStudentinfo);
        MapUtil.easySetByMap(dataMap, bckjBizYhkz);
        MapUtil.easySetByMap(dataMap,bckjBizJyscheme);
        MapUtil.easySetByMap(dataMap,bckjBizJypuchong);
        dataMap.remove("owid");
        MapUtil.easySetByMap(dataMap, bckjBizSyb);

        if (!TextUtils.isEmpty(bckjBizYhxx.getSfz())) {
            String regex = "\\d{15}(\\d{2}[0-9xX])?";
            if (!bckjBizYhxx.getSfz().matches(regex)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
            }
        }
        if (!TextUtils.isEmpty(bckjBizYhxx.getSjh())) {
            if (bckjBizYhxx.getSjh().length() != 11) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.SjHError);
            }
        }
        //如果為更新
        if (bckjBizYhxx.getOwid() != null) {
            if (!com.zghzbckj.util.TextUtils.isEmpty(bckjBizYhkz1)) {
                if (!(bckjBizYhkz1.getYhRefOwid().equals(bckjBizYhxx.getOwid()))) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "此学号已在库内");
                }
            }
            bckjBizJypuchongService.updateXsxhByHyOwid(bckjBizJypuchong);
            bckjBizJyschemeService.updateXsxhByHyOwid(bckjBizJyscheme);
            bckjBizYhxxService.updateSyscheme(bckjBizYhxx);
            bckjBizYhkzService.updateSyscheme(bckjBizYhkz);
            bckjBizStudentinfoService.updateSyscheme(bckjBizStudentinfo);
            this.dao.update(bckjBizSyb);
        }
        //否则为新建
        if (bckjBizYhxx.getOwid() == null) {
            if (!TextUtils.isEmpty(bckjBizYhkz1)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "学号已在库内");
            }
            //更新yhxx
            String uuid = IdGen.uuid();
            bckjBizYhxx.setOwid(uuid);
            bckjBizYhxx.setYhlx(1);
            bckjBizYhxxService.insert(bckjBizYhxx);
            //更新yhkz
            bckjBizYhkz.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizYhkz.setOlx(0);
            bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
            //更新syb
            bckjBizSyb.setYhRefOwid(bckjBizYhxx.getOwid());
            saveOrUpdate(bckjBizSyb);
            //更新studentInfo
            bckjBizStudentinfo.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizStudentinfoService.saveOrUpdate(bckjBizStudentinfo);
            //新保存jypuchong
            bckjBizJypuchong.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizJypuchongService.saveOrUpdate(bckjBizJypuchong);
            //新保存jyscheme
            bckjBizJyscheme.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizJyschemeService.saveOrUpdate(bckjBizJyscheme);
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
    public Map<String,Object> getBynfByXsxh(Map<String,Object> dataMap){
        return this.dao.getBynfByXsxh(dataMap);
    }


}