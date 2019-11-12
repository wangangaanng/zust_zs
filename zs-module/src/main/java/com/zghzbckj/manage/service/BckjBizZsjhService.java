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
import com.zghzbckj.manage.dao.BckjBizZsjhDao;
import com.zghzbckj.manage.entity.BckjBizZsjh;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizZsjhService extends CrudService<BckjBizZsjhDao, BckjBizZsjh> {

    private static final Logger log = Logger.getLogger(BckjBizZsjhService.class);

    @Override
    public BckjBizZsjh get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizZsjh> findList(BckjBizZsjh bckjBizZsjh) {
        return super.findList(bckjBizZsjh);
    }

    @Override
    public PageInfo<BckjBizZsjh> findPage(Page<BckjBizZsjh> page, BckjBizZsjh bckjBizZsjh) {
        return super.findPage(page, bckjBizZsjh);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizZsjh bckjBizZsjh) {
        super.saveOrUpdate(bckjBizZsjh);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizZsjh bckjBizZsjh) {
        super.delete(bckjBizZsjh);
    }


    /**
     * <p>方法:findPagebckjBizZsjh TODO后台BckjBizZsjh分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizZsjh(List<FilterModel> filters, Integer pageNo, Integer pageSize) throws IllegalAccessException, InstantiationException {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizZsjh> page = findPage(dataMap, pageNo, pageSize, null);
        List<BckjBizZsjh> records = page.getRecords();
        BckjBizZsjh bckjBizZsjh = BckjBizZsjh.class.newInstance();
        bckjBizZsjh.setNf("共有:"+page.getTotalCount()+"条");
        bckjBizZsjh.setReadOnly(true);
        bckjBizZsjh.setState(null);
        records.add(0,bckjBizZsjh);
        page.setRecords(records);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizZsjh TODO保存BckjBizZsjh信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizZsjh(Map<String, Object> mapData) {
        BckjBizZsjh bckjBizZsjh = JsonUtil.map2Bean(mapData, BckjBizZsjh.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizZsjh bckjBizZsjhIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizZsjh, bckjBizZsjhIndata);
            bckjBizZsjh = bckjBizZsjhIndata;
        }
        saveOrUpdate(bckjBizZsjh);
        return ResponseMessage.sendOK(bckjBizZsjh);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizZsjh </p>
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
            BckjBizZsjh bckjBizZsjh = new BckjBizZsjh();
            bckjBizZsjh.setOwid(owid);
            this.dao.delete(bckjBizZsjh);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
    *<p>方法:getChanges TODO招生计划查询条件数据获取</p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return java.util.List<com.zghzbckj.manage.entity.BckjBizZsjh>  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/14 15:33  </li>
    *</ul>
    */
    public Map<String, Object> getChanges(Map<String, Object> mapData) {
        Map<String, Object> result = new HashMap<>();
        List<BckjBizZsjh> nfList = this.dao.findListByNf(mapData);
        List<BckjBizZsjh> sfList = this.dao.findListBySf(mapData);
        List<BckjBizZsjh> klList = this.dao.findListByKl(mapData);
        List<BckjBizZsjh> pcList = this.dao.findListByPc(mapData);
        List<BckjBizZsjh> zyList = this.dao.findListByZy(mapData);
        result.put("nfList", nfList);
        result.put("sfList", sfList);
        result.put("klList", klList);
        result.put("pcList", pcList);
        result.put("zyList", zyList);
        return result;
    }

    /**
     *<p>方法:getResult TODO招生计划分页获取查询结果 </p>
     *<ul>
     *<li> @param mapData TODO</li>
     *<li>@return com.zghzbckj.base.entity.PageInfo<com.zghzbckj.manage.entity.BckjBizZsjh>  </li>
     *<li>@author D.chen.g </li>
     *<li>@date 2019/10/14 16:00  </li>
     *</ul>
     */
    public PageInfo<BckjBizZsjh> getResult(Map<String, Object> mapData) {
        Integer pageNo= MapUtils.getInt(mapData,"pageNo");
        Integer pageSize= MapUtils.getInt(mapData,"pageSize");
        PageInfo<BckjBizZsjh> page = findPage(mapData, pageNo, pageSize, " a.createtime DESC");
        return page;
    }

    /**
     *<p>功能描述:导出招生计划excel表格 generateExcel</p >
     *<ul>
     *<li>@param [dataMap]</li>
     *<li>@return java.lang.String</li>
     *<li>@throws </li>
     *<li>@author xuyux</li>
     *<li>@date 2019/11/4 19:42</li>
     *</ul>
     */
    public String generateExcel(Map<String, Object> dataMap) {
        List<BckjBizZsjh> dataList = this.dao.findListByMap(dataMap);
        List<List<String>> excelList = new ArrayList<>(dataList.size());
        //本地
//        String filePath = "F:\\img\\";
        //正式
        String filePath = "/mnt/files/zjcFiles/excel/";
        String fileOutPath = System.currentTimeMillis() + ".xls";
        String[] title = {"年份", "省份", "科类", "批次", "专业", "学制", "招生数", "学费/年", "授予学位"};
        for (BckjBizZsjh data : dataList) {
            List<String> colList = new ArrayList<>();
            colList.add(data.getNf());
            colList.add(data.getSf());
            colList.add(data.getKl());
            colList.add(data.getPc());
            colList.add(data.getZy());
            colList.add(data.getXz());
            colList.add(data.getZss().toString());
            colList.add(data.getXf().toPlainString());
            colList.add(data.getSyxw());
            excelList.add(colList);
        }
        ExcelUtils.writeContentToExcel(title, excelList, filePath + fileOutPath);
        return fileOutPath;
    }

    /**
     * 后台录入学生计划
     *
     * @param path
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordInfo(String path) throws IllegalAccessException, InstantiationException, ParseException {
        String filename = path;
        List<List<String>> list = getExcelLists(path);
        List<BckjBizZsjh> bckjBizZsjhs= Lists.newArrayList();
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
                String zss = cellList.get(6); //招生数
                if(!TextUtils.isEmpty(zss)){
                    resMap.put("zss", Integer.parseInt(zss.substring(0,zss.indexOf("."))));
                }
                String xf = cellList.get(7); //学费
                resMap.put("xf", xf);
                String syxw = cellList.get(8); //授予学位
                resMap.put("syxw", syxw);
                BckjBizZsjh bckjBizZsjh = BckjBizZsjh.class.newInstance();
                MapUtil.easySetByMap(resMap,bckjBizZsjh);
                bckjBizZsjhs.add(bckjBizZsjh);
            }
            for (BckjBizZsjh bckjBizZsjh:bckjBizZsjhs){
                saveOrUpdate(bckjBizZsjh);
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public static List<List<String>> getExcelLists(String filename) {
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        return list;
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage saveOne(Map<String, Object> mapData) {
        BckjBizZsjh bckjBizZsjh = JsonUtil.map2Bean(mapData, BckjBizZsjh.class);
        MapUtil.easySetByMap(mapData,bckjBizZsjh);
        saveOrUpdate(bckjBizZsjh);
        return ResponseMessage.sendOK(bckjBizZsjh);
    }
}