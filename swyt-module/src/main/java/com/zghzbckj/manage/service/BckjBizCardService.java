/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.dao.BckjBizBmDao;
import com.zghzbckj.manage.dao.BckjBizCardDao;
import com.zghzbckj.manage.entity.BckjBizBm;
import com.zghzbckj.manage.entity.BckjBizCard;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-11-05
 */
@Service
@Transactional(readOnly = true)
public class BckjBizCardService extends CrudService<BckjBizCardDao, BckjBizCard> {

    private static final Logger log = Logger.getLogger(BckjBizCardService.class);
    @Autowired
    BckjBizBmDao bckjBizBmDao;
    @Autowired
    BckjBizBmService bckjBizBmService;

    @Override
    public BckjBizCard get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizCard> findList(BckjBizCard bckjBizCard) {
        return super.findList(bckjBizCard);
    }

    @Override
    public PageInfo<BckjBizCard> findPage(Page<BckjBizCard> page, BckjBizCard bckjBizCard) {
        return super.findPage(page, bckjBizCard);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizCard bckjBizCard) {
        super.saveOrUpdate(bckjBizCard);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizCard bckjBizCard) {
        super.delete(bckjBizCard);
    }


    /**
     * <p>方法:findPagebckjBizCard TODO后台BckjBizCard分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.PageInfo  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public PageInfo<BckjBizCard> findPageBckjBizCard(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("createtime2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("createtime2").toString(), 1);
            dataMap.put("createtime2", date);
        }
        PageInfo<BckjBizCard> page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");

        List<BckjBizCard> records = page.getRecords();
        BckjBizCard jbxx = new BckjBizCard();
        jbxx.setExp1("共有：" + page.getTotalCount() + "条信息");
        jbxx.setReadOnly(true);
        records.add(0, jbxx);

        return page;
    }

    /**
     * <p>方法:savebckjBizCard TODO保存BckjBizCard信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizCard  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public BckjBizCard saveBckjBizCard(Map<String, Object> mapData) {
        BckjBizCard bckjBizCard = JsonUtil.map2Bean(mapData, BckjBizCard.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizCard bckjBizCardIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizCard, bckjBizCardIndata);
            bckjBizCard = bckjBizCardIndata;
        }
        saveOrUpdate(bckjBizCard);
        return bckjBizCard;
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizCard </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return java.util.List  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = Lists.newArrayList();
        for (String owid : codes) {
            Map<String, Object> params = Maps.newHashMap();
            BckjBizCard bckjBizCard = new BckjBizCard();
            bckjBizCard.setOwid(owid);
            this.dao.delete(bckjBizCard);
            params.put("owid", owid);
            objs.add(params);
        }
        return objs;
    }

    /**
     * <p>方法:dealPostData TODO处理上传的数据 </p>
     * <ul>
     * <li> @param postData TODO</li>
     * <li>@return void  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/11/5 16:06  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public void dealPostData(String postData) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(postData);
        BckjBizCard appBizCard = new BckjBizCard();
        assemble(jsonObject, appBizCard);
        if (1 == appBizCard.getIsPass()) {
            throw CustomerException.newInstances("已过滤");
        }
        String bmnd = DateUtil.getCurrentDate(CommonConstant.DATE_FROMART).substring(0, 4);
        //表示年度信息
        appBizCard.setExp1(bmnd);
        Map map = Maps.newHashMap();
        map.put("bmnd", bmnd);
        map.put("stateCheck", 7);
        map.put("sfzh", appBizCard.getNumber());
        BckjBizBm bm = bckjBizBmDao.getOneByMap(map);
        if (null == bm) {
            MyWebSocket.sendInfo("-1:" + appBizCard.getNumber() + ":" + appBizCard.getName() + ":-:-:-");
            appBizCard.setIsBm(0);
            return;
        } else {
            appBizCard.setIsBm(1);
        }
        saveOrUpdate(appBizCard);
        MyWebSocket.sendInfo("0:" + appBizCard.getNumber() + ":" + appBizCard.getName() + ":" + bm.getZkzh() + ":" + bm.getXzzymc() + ":" + bm.getMssj());
        if (appBizCard.getIsPass() == 0) {
            bm.setRzbd(1);
        } else {
            //未通过
            bm.setRzbd(2);
        }
        bckjBizBmService.saveOrUpdate(bm);
    }

    private void assemble(JSONObject jsonObject, BckjBizCard appBizCard) {

        if (jsonObject.has("headImage")) {
            String ss = jsonObject.get("headImage").toString();
            appBizCard.setHeadImage(ss.replaceAll(" ", "+"));
        }
        if (jsonObject.has("name"))
            appBizCard.setName(jsonObject.get("name").toString());

        if (jsonObject.has("sex")) {
            if ("男".equals(jsonObject.get("sex").toString()))
                appBizCard.setXb(1);
            else
                appBizCard.setXb(2);
        }
        if (jsonObject.has("folk"))
            appBizCard.setFolk(jsonObject.get("folk").toString());

        if (jsonObject.has("address"))
            appBizCard.setAddress(jsonObject.get("address").toString());

        if (jsonObject.has("number"))
            appBizCard.setNumber(jsonObject.get("number").toString());

        if (jsonObject.has("faceImage")) {
            String temp = jsonObject.get("faceImage").toString();
            appBizCard.setFaceImage(temp.replaceAll(" ", "+"));
        }

        if (jsonObject.has("faceValue"))
            appBizCard.setFaceValue(Double.parseDouble(jsonObject.get("faceValue").toString()));

        if (jsonObject.has("source"))
            appBizCard.setSource(jsonObject.get("source").toString());

        if (jsonObject.has("deviceId"))
            appBizCard.setDeviceid(jsonObject.get("deviceId").toString());

        if (jsonObject.has("birthday") && !TextUtils.isEmpty(jsonObject.get("birthday")))
            appBizCard.setBirthday(DateUtil.getDate(jsonObject.get("birthday").toString(), "yyyyMMdd"));

        if (jsonObject.has("validterm") && !TextUtils.isEmpty(jsonObject.get("validterm")))
            appBizCard.setValidterm(DateUtil.getDate(jsonObject.get("validterm").toString(), "yyyyMMdd"));

        if (jsonObject.has("isPass")) {
            if ("true".equals(jsonObject.get("isPass").toString())) {
                appBizCard.setIsPass(0);     //通过
            } else {
                appBizCard.setIsPass(1);
            }
        } else {
            /*没有这值，返回不通过*/
            appBizCard.setIsPass(1);
        }
    }
}