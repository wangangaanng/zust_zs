/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.*;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizDcwjDao;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;
import com.zghzbckj.manage.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizDcwjService extends CrudService<BckjBizDcwjDao, BckjBizDcwj> {

    private static final Logger log = Logger.getLogger(BckjBizDcwjService.class);

    @Autowired
    BckjBizDcwjTmDao bckjBizDcwjTmDao;
    @Autowired
    BckjBizDcwjDtmxService bckjBizDcwjDtmxService;
    @Autowired
    BckjBizDcwjJgService bckjBizDcwjJgService;
    @Autowired
    BckjBizDcwjTmService bckjBizDcwjTmService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;

    @Override
    public BckjBizDcwj get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizDcwj> findList(BckjBizDcwj bckjBizDcwj) {
        return super.findList(bckjBizDcwj);
    }

    @Override
    public PageInfo<BckjBizDcwj> findPage(Page<BckjBizDcwj> page, BckjBizDcwj bckjBizDcwj) {
        return super.findPage(page, bckjBizDcwj);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizDcwj bckjBizDcwj) {
        super.saveOrUpdate(bckjBizDcwj);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizDcwj bckjBizDcwj) {
        super.delete(bckjBizDcwj);
    }

    /**
     * <p>方法:返回调查问卷列表 listAllFilter TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.entity.PageInfo</li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/12 11:27  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public PageInfo<Map<String, Object>> listAllQuestionnaire(Map<String, Object> dataMap, Integer pageNo, Integer pageSize) throws Exception {
        PageInfo<BckjBizDcwj> result = findPage(dataMap, pageNo, pageSize, " kssj ");
        if (null == result) {
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<BckjBizDcwj> questionnaireList = result.getRecords();
        Map<String, Object> objectMap = null;
        for (BckjBizDcwj questionnaire : questionnaireList) {
            objectMap = new HashMap<>();
            BeanUtil.obj2Map(questionnaire, objectMap);
            Date endTime = questionnaire.getJssj();
            //系统时间大于问卷结束时间，无效
            if (System.currentTimeMillis() > endTime.getTime()) {
                objectMap.put("sfyx", 0);
                questionnaire.setSfyx(0);
                save(questionnaire);
            } else {
                objectMap.put("sfyx", questionnaire.getSfyx());
            }
            dataList.add(objectMap);
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
        pageInfo.setRecords(dataList);
        pageInfo.setCurrentIndex(result.getCurrentIndex());
        pageInfo.setPageSize(result.getPageSize());
        pageInfo.setTotalCount(result.getTotalCount());
        pageInfo.setCurrentPage(result.getCurrentPage());
        pageInfo.setTotalPage(result.getTotalPage());
        return pageInfo;
    }

    /**
     * <p>方法:后台返回问题列表 listAllQuestions TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/12 16:52  </li>
     * </ul>
     */
    public List<Map<String, Object>> listAllQuestions(Map<String, Object> dataMap) {
        dataMap.put("orderBy", "tmsx");
        List<BckjBizDcwjTm> questionList = bckjBizDcwjTmDao.findListByMap(dataMap);
        if (null == questionList) {
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (BckjBizDcwjTm question : questionList) {
            Map<String, Object> objectMap = BeanUtil.obj2Map(question, false);
            dataList.add(objectMap);
        }
        return dataList;
    }

    /**
     * <p>方法:给前端返回问题列表 listQuestions TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/23 19:36  </li>
     * </ul>
     */
    public List<Map<String, Object>> listQuestions(Map<String, Object> dataMap) {
        dataMap.put("orderBy", "tmsx");
        List<BckjBizDcwjTm> questionList = bckjBizDcwjTmDao.findListByMap(dataMap);
        if (null == questionList) {
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> objectMap = null;
        for (BckjBizDcwjTm question : questionList) {
            objectMap = new HashMap<>();
            BeanUtil.obj2Map(question, objectMap);
            List<Map<String, Object>> optionList = new ArrayList<>();
            //查出选项，放入选项列表
            Map<String, Object> tmMap = bckjBizDcwjTmDao.getOption(question.getOwid());
            for (Map.Entry<String, Object> entry : tmMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Map<String, Object> map1 = new HashMap<>();
                if (null == value || "".equals(value)) continue;
                map1.put("bh", key);
                map1.put("ms", value);
                optionList.add(map1);
            }
            objectMap.put("xxList", optionList);
            dataList.add(objectMap);
        }
        return dataList;
    }

    /**
     * <p>方法:保存答案 saveAnswer TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return void  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/12 16:53  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveAnswer(Map<String, Object> dataMap) {
        List<Map<String, Object>> answerList = (List<Map<String, Object>>) dataMap.get("answerList");
        if (TextUtils.isEmpty(answerList) || answerList.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "答案列表为空");
        }
        BckjBizDcwjJg result = new BckjBizDcwjJg();
        if (!TextUtils.isEmpty(dataMap.get("ksdt")) && !TextUtils.isEmpty(dataMap.get("jsdt"))) {
            result.setKsdt(DateUtil.getDate(MapUtils.getString(dataMap, "ksdt")));
            result.setJsdt(DateUtil.getDate(MapUtils.getString(dataMap, "jsdt")));
            result.setDtsc(DateUtil.getBetweenMinutes(result.getJsdt(), result.getKsdt()));
        }
        if (!TextUtils.isEmpty(dataMap.get("dtrId"))) {
            result.setDtrid(dataMap.get("dtrId").toString());
            result.setDtrxm(dataMap.get("dtrXm").toString());
        } else {
            result.setDtrxm("匿名用户");
        }
        //扩展字段保存调查问卷的owid，方便统计和判断
        result.setExp1(MapUtils.getString(dataMap, "dcwjRefOwid"));
        result.setExp2(MapUtils.getString(dataMap, "wjmc"));
        bckjBizDcwjJgService.save(result);
        for (Map<String, Object> map : answerList) {
            BckjBizDcwjDtmx answer = JsonUtil.map2Bean(map, BckjBizDcwjDtmx.class);
            answer.setDcwjjgRefOwid(result.getOwid());
            bckjBizDcwjDtmxService.save(answer);
        }
        return ResponseMessage.sendOK("问卷答案已保存");
    }

    /**
     * <p>方法:保存问卷和问卷题目 saveAll TODO </p>
     * <ul>
     * <li> @param dcwj TODO</li>
     * <li> @param tmList TODO</li>
     * <li>@return void  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/11 14:41  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public void saveAll(BckjBizDcwj dcwj, List<BckjBizDcwjTm> tmList) {
        //保存调查问卷表
        if (TextUtils.isEmpty(dcwj.getOwid())) {
            dcwj.setSfyx(1);
        }
        //调查问卷题目为空，设置状态为1 0为前端可见
        if (TextUtils.isEmpty(tmList) || tmList.size() <= 0) {
            dcwj.setState(1);
        } else {
            dcwj.setState(0);
        }
        save(dcwj);
        String wjOwid = dcwj.getOwid();
        //保存调查问卷题目表
        for (BckjBizDcwjTm tm : tmList) {
            tm.setDcwjRefOwid(wjOwid);
            tm.setExp1("");
            bckjBizDcwjTmService.saveOrUpdate(tm);
        }
    }

    /**
     * <p>方法:findPagebckjBizDcwj TODO后台BckjBizDcwj分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizDcwj(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizDcwj> page = findPage(dataMap, pageNo, pageSize, "kssj");
        List<BckjBizDcwj> records = page.getRecords();
        //统计行
        BckjBizDcwj dcwj = new BckjBizDcwj();
        dcwj.setWjmc("共有："+ page.getTotalCount() +"张调查问卷");
        dcwj.setState(null);
        dcwj.setReadOnly(true);
        records.add(0, dcwj);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizDcwj TODO保存BckjBizDcwj信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizDcwj(Map<String, Object> mapData) {
        BckjBizDcwj bckjBizDcwj = JsonUtil.map2Bean(mapData, BckjBizDcwj.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizDcwj bckjBizDcwjIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizDcwj, bckjBizDcwjIndata);
            bckjBizDcwj = bckjBizDcwjIndata;
        }
        saveOrUpdate(bckjBizDcwj);
        return ResponseMessage.sendOK(bckjBizDcwj);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizDcwj </p>
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
            BckjBizDcwj bckjBizDcwj = new BckjBizDcwj();
            bckjBizDcwj.setOwid(owid);
            this.dao.delete(bckjBizDcwj);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

}