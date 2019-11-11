/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizCjcxDao;
import com.zghzbckj.manage.entity.BckjBizCjcx;
import com.zghzbckj.manage.entity.BckjBizLntj;
import com.zghzbckj.manage.entity.BckjBizLqxs;
import com.zghzbckj.util.MapUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BckjBizCjcxService extends CrudService<BckjBizCjcxDao, BckjBizCjcx> {

    private static final Logger log = Logger.getLogger(BckjBizCjcxService.class);

    @Override
    public BckjBizCjcx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizCjcx> findList(BckjBizCjcx bckjBizCjcx) {
        return super.findList(bckjBizCjcx);
    }
    @Autowired
    BckjBizZsjhService bckjBizZsjhService;
    @Autowired
    BckjBizLqxsService bckjBizLqxsService;

    @Override
    public PageInfo<BckjBizCjcx> findPage(Page<BckjBizCjcx> page, BckjBizCjcx bckjBizCjcx) {
        return super.findPage(page, bckjBizCjcx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizCjcx bckjBizCjcx) {
        super.saveOrUpdate(bckjBizCjcx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizCjcx bckjBizCjcx) {
        super.delete(bckjBizCjcx);
    }


    /**
     * <p>方法:findPagebckjBizCjcx TODO后台BckjBizCjcx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizCjcx(List<FilterModel> filters, Integer pageNo, Integer pageSize) throws IllegalAccessException, InstantiationException {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizCjcx> page = findPage(dataMap, pageNo, pageSize, null);
        List<BckjBizCjcx> records = page.getRecords();
        BckjBizCjcx bckjBizCjcx = BckjBizCjcx.class.newInstance();
        bckjBizCjcx.setSfzh("共有"+page.getTotalCount()+"条");
        bckjBizCjcx.setState(null);
        bckjBizCjcx.setReadOnly(true);
        records.add(0,bckjBizCjcx);
        page.setRecords(records);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizCjcx TODO保存BckjBizCjcx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizCjcx(Map<String, Object> mapData) {
        BckjBizCjcx bckjBizCjcx = JsonUtil.map2Bean(mapData, BckjBizCjcx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizCjcx bckjBizCjcxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizCjcx, bckjBizCjcxIndata);
            bckjBizCjcx = bckjBizCjcxIndata;
        }
        saveOrUpdate(bckjBizCjcx);
        return ResponseMessage.sendOK(bckjBizCjcx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizCjcx </p>
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
            BckjBizCjcx bckjBizCjcx = new BckjBizCjcx();
            bckjBizCjcx.setOwid(owid);
            this.dao.delete(bckjBizCjcx);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
    *<p>方法:getCjxx TODO查询成绩 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.manage.entity.BckjBizCjcx  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/15 13:54  </li>
    *</ul>
    */
    public BckjBizCjcx getCjxx(Map<String, Object> mapData) {
        List<BckjBizCjcx> list = this.dao.findListByMap(mapData);
        if(null!=list && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    /**
     * 后台录入成绩查询
     *
     * @param path
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordInfo(String path) throws IllegalAccessException, InstantiationException, ParseException {
        String filename = path;
        List<List<String>> list = bckjBizZsjhService.getExcelLists(path);
        List<BckjBizCjcx> bckjBizCjcxes= Lists.newArrayList();
        List<Map> oldMap =getOldCjcxs();
        List<String> sfzs= Lists.newArrayList();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                HashMap<Object, Object> resMap = Maps.newHashMap();
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String sfzh = cellList.get(0); //身份证号
                //如果身份证号为空则退出
                if (TextUtils.isEmpty(sfzh)) {
                    break;
                }
                sfzs.add(sfzh);
                resMap.put("sfzh", sfzh);
                String ksh = cellList.get(1); //考生号
                resMap.put("ksh", ksh);
                String xm = cellList.get(2); //姓名
                resMap.put("xm", xm);
                String xbdm = cellList.get(3); //性别
                resMap.put("xbdm", xbdm);
                String jtdz = cellList.get(4); //考试类别
                resMap.put("jtdz", jtdz);
                String yw = cellList.get(5); //考试成绩
                resMap.put("yw", yw);
                String jcsj = cellList.get(6); //寄出时间
                Date date = bckjBizLqxsService.stringtoDate(jcsj);
                resMap.put("jcsj", date);
                String memo = cellList.get(7); // 备注
                resMap.put("memo", memo);
                String mzdm = cellList.get(8); //是否合格
                resMap.put("mzdm", mzdm);
                BckjBizCjcx bckjBizCjcx = BckjBizCjcx.class.newInstance();
                MapUtil.easySetByMap(resMap,bckjBizCjcx);
                bckjBizCjcxes.add(bckjBizCjcx);
            }
            for (BckjBizCjcx bckjBizCjcx:bckjBizCjcxes){
                saveOrUpdate(bckjBizCjcx);
            }
            HashSet hashSet = new HashSet(sfzs);
            Integer count =hashSet.size();
            for (Map map:oldMap){
                hashSet.add(map.get("sfzh"));
                if(hashSet.size()!=++count){
                    BckjBizCjcx bckjBizCjcx = BckjBizCjcx.class.newInstance();
                    bckjBizCjcx.setOwid(map.get("owid").toString());
                    delete(bckjBizCjcx);
                }
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    private List<Map> getOldCjcxs() {
        return this.dao.getOldCjcxs();
    }


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage saveOne(Map<String, Object> mapData) {
        BckjBizCjcx bckjBizCjcx = JsonUtil.map2Bean(mapData, BckjBizCjcx.class);
        MapUtil.easySetByMap(mapData,bckjBizCjcx);
        saveOrUpdate(bckjBizCjcx);
        return ResponseMessage.sendOK(bckjBizCjcx);
    }
}