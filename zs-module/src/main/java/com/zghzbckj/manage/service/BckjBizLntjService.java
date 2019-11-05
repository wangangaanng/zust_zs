/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizLntjDao;
import com.zghzbckj.manage.entity.BckjBizLntj;
import com.zghzbckj.manage.entity.BckjBizZsjh;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
public class BckjBizLntjService extends CrudService<BckjBizLntjDao, BckjBizLntj> {

    private static final Logger log = Logger.getLogger(BckjBizLntjService.class);

    @Override
    public BckjBizLntj get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizLntj> findList(BckjBizLntj bckjBizLntj) {
        return super.findList(bckjBizLntj);
    }

    @Override
    public PageInfo<BckjBizLntj> findPage(Page<BckjBizLntj> page, BckjBizLntj bckjBizLntj) {
        return super.findPage(page, bckjBizLntj);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizLntj bckjBizLntj) {
        super.saveOrUpdate(bckjBizLntj);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizLntj bckjBizLntj) {
        super.delete(bckjBizLntj);
    }

    @Autowired
    BckjBizZsjhService bckjBizZsjhService;
    /**
     * <p>方法:findPagebckjBizLntj TODO后台BckjBizLntj分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizLntj(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizLntj> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizLntj TODO保存BckjBizLntj信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizLntj(Map<String, Object> mapData) {
        BckjBizLntj bckjBizLntj = JsonUtil.map2Bean(mapData, BckjBizLntj.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizLntj bckjBizLntjIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizLntj, bckjBizLntjIndata);
            bckjBizLntj = bckjBizLntjIndata;
        }
        saveOrUpdate(bckjBizLntj);
        return ResponseMessage.sendOK(bckjBizLntj);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizLntj </p>
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
            BckjBizLntj bckjBizLntj = new BckjBizLntj();
            bckjBizLntj.setOwid(owid);
            this.dao.delete(bckjBizLntj);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
     * <p>方法:getChanges TODO查询条件数据获取</p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.util.List<com.zghzbckj.manage.entity.BckjBizLntj>  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/14 15:33  </li>
     * </ul>
     */
    public Map<String, Object> getChanges(Map<String, Object> mapData) {
        Map<String, Object> result = new HashMap<>();
        List<BckjBizLntj> nfList = this.dao.findListByNf(mapData);
        List<BckjBizLntj> sfList = this.dao.findListBySf(mapData);
        List<BckjBizLntj> klList = this.dao.findListByKl(mapData);
        List<BckjBizLntj> pcList = this.dao.findListByPc(mapData);
        List<BckjBizLntj> zyList = this.dao.findListByZy(mapData);
        result.put("nfList", nfList);
        result.put("sfList", sfList);
        result.put("klList", klList);
        result.put("pcList", pcList);
        result.put("zyList", zyList);
        return result;
    }

    /**
     * <p>方法:getResult TODO分页获取查询结果 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.entity.PageInfo<com.zghzbckj.manage.entity.BckjBizLntj>  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/14 16:00  </li>
     * </ul>
     */
    public PageInfo<BckjBizLntj> getResult(Map<String, Object> mapData) {
        Integer pageNo = MapUtils.getInt(mapData, "pageNo");
        Integer pageSize = MapUtils.getInt(mapData, "pageSize");
        PageInfo<BckjBizLntj> page = findPage(mapData, pageNo, pageSize, " a.createtime DESC");
        return page;
    }

    /**
     * <p>功能描述:导出历年分数名次excel表 generateExcel</p >
     * <ul>
     * <li>@param [dataMap]</li>
     * <li>@return void</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/11/4 17:53</li>
     * </ul>
     */
    public String generateExcel(Map<String, Object> dataMap) {
        List<BckjBizLntj> dataList = this.dao.findListByMap(dataMap);
        List<List<String>> excelList = new ArrayList<>(dataList.size());
        //本地
        String filePath = "F:\\img\\";
        //正式
//        String filePath = "/mnt/files/zjcFiles/excel";
        String fileOutPath = System.currentTimeMillis() + ".xls";
        String[] title = {"年份", "省份", "科类", "批次", "专业", "学制", "录取数", "最高分", "最低分", "平均分"};
        for (BckjBizLntj data : dataList) {
            List<String> colList = new ArrayList<>();
            colList.add(data.getNf());
            colList.add(data.getSf());
            colList.add(data.getKl());
            colList.add(data.getPc());
            colList.add(data.getZy());
            colList.add(data.getXz());
            colList.add(data.getLqs().toString());
            colList.add(data.getZdf().toPlainString());
            colList.add(data.getZdf().toPlainString());
            colList.add(data.getPjf().toPlainString());
            excelList.add(colList);
        }
        ExcelUtils.writeContentToExcel(title, excelList, filePath + fileOutPath);
        return fileOutPath;
    }

    /**
     * 后台录入历年统计
     *
     * @param path
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordInfo(String path) throws IllegalAccessException, InstantiationException, ParseException {
        String filename = path;
        List<List<String>> list = bckjBizZsjhService.getExcelLists(path);
        List<BckjBizLntj> bckjBizLntjs= Lists.newArrayList();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                HashMap<Object, Object> resMap = Maps.newHashMap();
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String nf = cellList.get(0); //年份
                //如果年份为空则退出
                if (TextUtils.isEmpty(nf)) {
                    break;
                }
                resMap.put("nf", nf);
                String sf = cellList.get(1); //省份
                resMap.put("sf", sf);
                String kl = cellList.get(2); //科类
                resMap.put("kl", kl);
                String pc = cellList.get(3); //批次
                resMap.put("pc", pc);
                String zy = cellList.get(4); //专业
                resMap.put("zy", zy);
                String xz = cellList.get(5); //学制
                resMap.put("xz", xz);
                String lqs = cellList.get(6); //录取数
                if(!TextUtils.isEmpty(lqs)){
                    resMap.put("lqs", Double.parseDouble(lqs));
                }
                String szxy = cellList.get(7); //所属学院
                resMap.put("szxy", szxy);
                String pif = cellList.get(8); //平均分
                if(!TextUtils.isEmpty(pif)){
                    resMap.put("pif", Double.parseDouble(pif));
                }
                String zgf = cellList.get(9); //最高分
                if(!TextUtils.isEmpty(zgf)){
                    resMap.put("zgf", Double.parseDouble(zgf));
                }
                String zdf = cellList.get(10); //最低分
                if(!TextUtils.isEmpty(zdf)){
                    resMap.put("zdf", Double.parseDouble(zdf));
                }
                BckjBizLntj bckjBizLntj = BckjBizLntj.class.newInstance();
                MapUtil.easySetByMap(resMap,bckjBizLntj);
                bckjBizLntjs.add(bckjBizLntj);
            }
            for (BckjBizLntj bckjBizLntj:bckjBizLntjs){
                saveOrUpdate(bckjBizLntj);
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
}