/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;

import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.manage.entity.BckjBizZjzx;
import com.zghzbckj.util.*;
import com.zghzbckj.vo.BckjBizYhxxVo;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;

import com.zghzbckj.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizDacx;
import com.zghzbckj.manage.dao.BckjBizDacxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizDacxService extends CrudService<BckjBizDacxDao, BckjBizDacx> {

    private static final Logger log = Logger.getLogger(BckjBizDacxService.class);

    @Override
    public BckjBizDacx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizDacx> findList(BckjBizDacx bckjBizDacx) {
        return super.findList(bckjBizDacx);
    }

    @Override
    public PageInfo<BckjBizDacx> findPage(Page<BckjBizDacx> page, BckjBizDacx bckjBizDacx) {
        return super.findPage(page, bckjBizDacx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizDacx bckjBizDacx) {
        super.saveOrUpdate(bckjBizDacx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizDacx bckjBizDacx) {
        super.delete(bckjBizDacx);
    }


    /**
     * <p>方法:findPagebckjBizDacx TODO后台BckjBizDacx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizDacx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizDacx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizDacx TODO保存BckjBizDacx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizDacx(Map<String, Object> mapData) {
        BckjBizDacx bckjBizDacx = JsonUtil.map2Bean(mapData, BckjBizDacx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizDacx bckjBizDacxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizDacx, bckjBizDacxIndata);
            bckjBizDacx = bckjBizDacxIndata;
        }
        saveOrUpdate(bckjBizDacx);
        return ResponseMessage.sendOK(bckjBizDacx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizDacx </p>
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
            BckjBizDacx bckjBizDacx = new BckjBizDacx();
            bckjBizDacx.setOwid(owid);
            this.dao.delete(bckjBizDacx);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public ResponseMessage inquiryArchives(Map<String, Object> datamap) {
        BckjBizDacx bckjBizDacx = this.dao.inquiryArchives(datamap);
        if (bckjBizDacx == null) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.Fail_InquiryArchives);
        }
        return ResponseMessage.sendOK(bckjBizDacx);
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage recDanInfo(String path) throws ParseException {
        //文件路径
        String filename = path;
        List<BckjBizDacx> resultDacx = new ArrayList<>();
        List<String> excelSfzh = new ArrayList<>();
        List<String> excelXh = new ArrayList<>();
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        if (list != null) {
            for (int i = 2; i < list.size(); i++) {   //行循环
                HashMap<Object, Object> dataMap = Maps.newHashMap();
                List<String> cellList = list.get(i);
                String xsxh = cellList.get(0);//学生学号/工号/税号
                xsxh = ExcelUtils.stmodifyExcelData(xsxh);
                excelXh.add(xsxh);
                dataMap.put("xsxh", xsxh);
                String xsxm = cellList.get(1); //姓名
                xsxm = ExcelUtils.stmodifyExcelData(xsxm);
                dataMap.put("xsxm", xsxm);
                String sfzh = cellList.get(3);//身份证号
                sfzh=ExcelUtils.stmodifyExcelData(sfzh);
                String regex = "\\d{15}(\\d{2}[0-9xX])?";
                if (!sfzh.matches(regex)) {
                    return ResponseMessage.sendOK("存在错误的身份证格式录入");
                }
                excelSfzh.add(sfzh);
                sfzh = ExcelUtils.stmodifyExcelData(sfzh);
                dataMap.put("sfzh", sfzh);
                String xb = cellList.get(2);//性别
                xb = ExcelUtils.stmodifyExcelData(xb);
                if (xb.indexOf("男") != -1) {
                    dataMap.put("xb", 1);
                } else if (xb.indexOf("女") != -1) {
                    dataMap.put("xb", 0);
                }

                String bysj = cellList.get(4);//毕业时间
                bysj=ExcelUtils.stmodifyExcelData(bysj);
                dataMap.put("bysj", ExcelUtils.stringtoDate(bysj));

                String szxy = cellList.get(5);//所在院系
                szxy = ExcelUtils.stmodifyExcelData(szxy);
                dataMap.put("szxy", szxy);

                String szbj = cellList.get(6);//所在班级
                szbj= ExcelUtils.stmodifyExcelData(szbj);
                dataMap.put("szbj", szbj);

                String dwmc = cellList.get(7);//单位名称
                dwmc = ExcelUtils.stmodifyExcelData(dwmc);
                dataMap.put("dwmc", dwmc);

                String bdzDwmc = cellList.get(8);//报到证签往单位名称
                bdzDwmc = ExcelUtils.stmodifyExcelData(bdzDwmc);
                dataMap.put("bdzDwmc", bdzDwmc);

                String dazjDwdz = cellList.get(9);//档案转寄单位地址
                dazjDwdz = ExcelUtils.stmodifyExcelData(dazjDwdz);
                dataMap.put("dazjDwdz", dazjDwdz);

                String dazjDwmc = cellList.get(10);//档案转寄单位名称
                dazjDwmc = ExcelUtils.stmodifyExcelData(dazjDwmc);
                dataMap.put("dazjDwmc", dazjDwmc);

                String bdzBh = cellList.get(11);//报到证编号
                bdzBh=ExcelUtils.stmodifyExcelData(bdzBh);
                dataMap.put("bdzBh", bdzBh);

                String memo = cellList.get(12);//备注
                memo=ExcelUtils.stmodifyExcelData(memo);
                dataMap.put("memo", memo);

                BckjBizDacx bckjBizDacx = new BckjBizDacx();
                MapUtil.easySetByMap(dataMap, bckjBizDacx);
                resultDacx.add(bckjBizDacx);

            }
            //判断是否存在学号一样的数据
            List<String> xsxhs = this.dao.getXsxhList();
            List<String> sfzs = this.dao.getSfzList();
            ArrayList<String> operates = new ArrayList();
            operates.addAll(xsxhs);
            operates.addAll(excelXh);
            HashSet xhSet = new HashSet();
            HashSet sfzSet = new HashSet();
            for (String i : operates)
                xhSet.add(i);
            if (xhSet.size() != operates.size()) {
                return ResponseMessage.sendOK("存在学号重复录入");
            }
            operates.clear();
            operates.addAll(sfzs);
            operates.addAll(excelSfzh);
            for (String i : operates)
                sfzSet.add(i);
            if (sfzSet.size() != operates.size()) {
                return ResponseMessage.sendOK("存在身份证号重复录入");
            }
            saveOrUpdateAll(resultDacx);
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public ResponseMessage showInfoList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Map<String ,Object>> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        page.setList(this.dao.showDanInfoList(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage saveStudentInfo(List<Map<String, Object>> components) {
        try {
            for (Map<String, Object> component : components) {
                //如果為刪除的記錄
                if (Integer.parseInt(component.get("updateFlag").toString()) == 2) {
                    delete(JsonUtil.map2Bean(component, BckjBizDacx.class));
                }
            }
                /*if (Integer.parseInt(component.get("updateFlag").toString()) == 1) {
                    BckjBizDacx dacxXsxh = null;
                    BckjBizDacx dacxSfzh = null;
                    //判断是否存在一样的学号或工号
                    if (!TextUtils.isEmpty(component.get("xsxh").toString())) {
                        dacxXsxh = this.dao.getXsxh(component.get("xsxh").toString());
                    }
                    if (!TextUtils.isEmpty(component.get("sfzh").toString())) {
                        dacxSfzh = this.dao.getSfzh(component.get("sfzh").toString());
                    }
                    //如果為更新
                   *//* if (component.get("owid") != null) {
                        if (!com.zghzbckj.util.TextUtils.isEmpty(dacxXsxh)) {
                            if(!component.get("owid").equals(dacxXsxh.getOwid())){
                                return ResponseMessage.sendError(ResponseMessage.FAIL, "存在学号重复录入");
                            }
                        }
                        if(!TextUtils.isEmpty(dacxSfzh)){
                            if(!component.get("owid").equals(dacxSfzh.getOwid())){
                                return ResponseMessage.sendError(ResponseMessage.FAIL, "存在身份证重复录入");
                            }
                        }
                        component.put("bysj",ExcelUtils.stringtoDatec(component.get("bysj").toString()));
                        BckjBizDacx bckjBizDacx=new BckjBizDacx();
                        MapUtil.easySetByMap(component,bckjBizDacx);
                        saveOrUpdate(bckjBizDacx);
                        //否则为新建
                    } *//**//*else if (component.get("owid") == null) {
                        if (!com.zghzbckj.util.TextUtils.isEmpty(dacxXsxh)) {
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在学号重复录入");
                        }
                        if(!TextUtils.isEmpty(dacxSfzh)){
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在身份证重复录入");
                        }
                        saveOrUpdate(JsonUtil.map2Bean(component,BckjBizDacx.class));
                    }*//**//*
                }*//*
                }*/

                return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);

        } catch(Exception e){
                log.error(CommonConstant.ERROR_MESSAGE, e);
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
            }

    }

@Transactional(readOnly = false,rollbackFor = Exception.class)
        public ResponseMessage insertssInfo(Map<String, Object> dataMap) throws Exception {
                if (!TextUtils.isEmpty(dataMap.get("sfzh"))) {
                    String regex = "\\d{15}(\\d{2}[0-9xX])?";
                    if (!dataMap.get("sfzh").toString().matches(regex)) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
                    }
                }
                BckjBizDacx dacxXsxh=null;
                BckjBizDacx dacxSfzh=null;
                BckjBizDacx bckjBizDacx= new BckjBizDacx();
                //判断是否存在一样的学号
                if(!TextUtils.isEmpty(dataMap.get("xsxh").toString())){
                    dacxXsxh=this.dao.getXsxh(dataMap.get("xsxh").toString());
                }
                if(!TextUtils.isEmpty(dataMap.get("sfzh").toString())){
                    dacxSfzh=this.dao.getSfzh(dataMap.get("sfzh").toString());
                }
                if(dataMap.get("owid")==null){
                   dataMap.put("bysj",ExcelUtils.stringtoDatec(dataMap.get("bysj").toString()));
                    MapUtil.easySetByMap(dataMap,bckjBizDacx);
                if (!com.zghzbckj.util.TextUtils.isEmpty(dacxXsxh)) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, "存在学号重复录入");
                    }
                if(!TextUtils.isEmpty(dacxSfzh)){
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "存在身份证重复录入");
                }
                saveOrUpdate(bckjBizDacx);
                } else if(dataMap.get("owid").toString()!=null){
                    if (!com.zghzbckj.util.TextUtils.isEmpty(dacxXsxh)) {
                        if(!dataMap.get("owid").equals(dacxXsxh.getOwid())){
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在学号重复录入");
                        }
                    }
                    if(!TextUtils.isEmpty(dacxSfzh)){
                        if(!dataMap.get("owid").equals(dacxSfzh.getOwid())){
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在身份证重复录入");
                        }
                    }
                    dataMap.put("bysj",ExcelUtils.stringtoDatec(dataMap.get("bysj").toString()));
                    MapUtil.easySetByMap(dataMap,bckjBizDacx);
                    saveOrUpdate(bckjBizDacx);
                }
                return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
}

    public ResponseMessage getDacxOne(Map<String, Object> dataMap) {
        return ResponseMessage.sendOK(get(dataMap.get("owid").toString()));
    }
}