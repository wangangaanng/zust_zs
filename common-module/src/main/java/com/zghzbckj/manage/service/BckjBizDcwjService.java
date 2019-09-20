/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizDcwjDao;
import com.zghzbckj.manage.dao.BckjBizDcwjTmDao;
import com.zghzbckj.manage.entity.BckjBizDcwj;
import com.zghzbckj.manage.entity.BckjBizDcwjDtmx;
import com.zghzbckj.manage.entity.BckjBizDcwjJg;
import com.zghzbckj.manage.entity.BckjBizDcwjTm;
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
    public PageInfo<Map<String, Object>> listAllQuestionnaire(Map<String, Object> dataMap, Integer pageNo, Integer pageSize) {
        PageInfo<BckjBizDcwj> result = findPage(dataMap, pageNo, pageSize, null);
        if (null == result) {
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<BckjBizDcwj> questionnaireList = result.getRecords();
        for (BckjBizDcwj questionnaire : questionnaireList) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("owid", questionnaire.getOwid());
            objectMap.put("wjmc", questionnaire.getWjmc());
            objectMap.put("wjjj", questionnaire.getWjjj());
            objectMap.put("wjjjtp", questionnaire.getWjjjtp());
            objectMap.put("wjsm", questionnaire.getWjsm());
            objectMap.put("kssj", questionnaire.getKssj());
            objectMap.put("jssj", questionnaire.getJssj());
            objectMap.put("sfyx", questionnaire.getSfyx());
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
     * <p>方法:返回问题列表 listAllQuestions TODO </p>
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
        result.setKsdt(DateUtil.getDate(dataMap.get("ksdt").toString()));
        result.setJsdt(DateUtil.getDate(dataMap.get("jsdt").toString()));
        result.setDtsc(DateUtil.getBetweenMinutes(result.getJsdt(), result.getKsdt()));
        bckjBizDcwjJgService.save(result);
        for (Map<String, Object> map : answerList) {
            BckjBizDcwjDtmx answer = JsonUtil.map2Bean(map, BckjBizDcwjDtmx.class);
            answer.setDcwjjgRefOwid(result.getOwid());
            bckjBizDcwjDtmxService.save(answer);
        }
        return ResponseMessage.sendOK("问卷答案已保存");
    }

    /**
     * <p>方法:后台保存调查问卷和问卷题目saveAll TODO </p>
     * <ul>
     * <li> @param dcwj TODO</li>
     * <li> @param tmList TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/20 9:29  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveAll(BckjBizDcwj dcwj, List<BckjBizDcwjTm> tmList) {
        if (null == tmList) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
        //保存调查问卷表
        save(dcwj);
        //保存调查问卷题目表
        for (BckjBizDcwjTm tm : tmList) {
            if (null == tm.getDcwjRefOwid()) {
                bckjBizDcwjTmService.delete(tm);
            } else {
                bckjBizDcwjTmService.saveOrUpdate(tm);
            }
        }
//        bckjBizDcwjTmService.saveOrUpdateAll(tmList);
        return ResponseMessage.sendOK("");
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
        PageInfo<BckjBizDcwj> page = findPage(dataMap, pageNo, pageSize, null);
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