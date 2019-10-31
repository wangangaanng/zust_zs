/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.feign.BckjBizYhkzSer;
import com.zghzbckj.feign.BckjBizZxzxSer;
import com.zghzbckj.manage.dao.BckjBizZxzxDao;
import com.zghzbckj.manage.entity.BckjBizYhkz;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.entity.BckjBizZxzx;
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
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizZxzxService extends CrudService<BckjBizZxzxDao, BckjBizZxzx> {

    private static final Logger log = Logger.getLogger(BckjBizZxzxService.class);

    @Override
    public BckjBizZxzx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizZxzx> findList(BckjBizZxzx bckjBizZxzx) {
        return super.findList(bckjBizZxzx);
    }

    @Override
    public PageInfo<BckjBizZxzx> findPage(Page<BckjBizZxzx> page, BckjBizZxzx bckjBizZxzx) {
        return super.findPage(page, bckjBizZxzx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizZxzx bckjBizZxzx) {
        super.saveOrUpdate(bckjBizZxzx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizZxzx bckjBizZxzx) {
        super.delete(bckjBizZxzx);
    }

    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    BckjBizYhkzService bckjBizYhkzService;


    /**
     * <p>方法:findPagebckjBizZxzx TODO后台BckjBizZxzx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizZxzx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizZxzx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizZxzx TODO保存BckjBizZxzx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizZxzx(Map<String, Object> mapData) {
        BckjBizZxzx bckjBizZxzx = JsonUtil.map2Bean(mapData, BckjBizZxzx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizZxzx bckjBizZxzxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizZxzx, bckjBizZxzxIndata);
            bckjBizZxzx = bckjBizZxzxIndata;
        }
        saveOrUpdate(bckjBizZxzx);
        return ResponseMessage.sendOK(bckjBizZxzx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizZxzx </p>
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
            BckjBizZxzx bckjBizZxzx = new BckjBizZxzx();
            bckjBizZxzx.setOwid(owid);
            this.dao.delete(bckjBizZxzx);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage consult(Map<String, Object> dataMap) {
        BckjBizZxzx bckjBizZxzx = new BckjBizZxzx();
        //就业专家咨询或者就业留言
        if (Integer.parseInt(dataMap.get("zxlx").toString()) != 2 && Integer.parseInt(dataMap.get("zxlx").toString()) != 5) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        } //就业专家咨询
        if (Integer.parseInt(dataMap.get("zxlx").toString()) == 2) {
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            ValidateMsg studentOwid = ValidateUtils.isEmpty(dataMap, "studentOwid");
            if (!studentOwid.getSuccess()) {
                return ResponseMessage.sendError(2, "登入过期");
            }
            ResponseMessage studentMessage = bckjBizYhxxService.getOneByOwid(dataMap.get("studentOwid").toString());
            if (studentMessage == null && studentMessage.getBackCode() == 0 && studentMessage.getBean() == null) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
            }
            BckjBizYhxx bckjBizYhxx = (BckjBizYhxx) studentMessage.getBean();
            bckjBizZxzx.setZxzyid(dataMap.get("owid").toString());
            if (!TextUtils.isEmpty(bckjBizYhxx.getYx())) {
                bckjBizZxzx.setYx(bckjBizYhxx.getYx());
            }
            if (!TextUtils.isEmpty(bckjBizYhxx.getSjh())) {
                bckjBizZxzx.setDh(bckjBizYhxx.getSjh());
            }
            if (!TextUtils.isEmpty(bckjBizYhxx.getXm())) {
                bckjBizZxzx.setTwName(bckjBizYhxx.getXm());
            }
            if (!TextUtils.isEmpty(bckjBizYhxx.getOwid())) {
                bckjBizZxzx.setTwOwid(bckjBizYhxx.getOwid());
            }
        }
        //就业留言
        if (Integer.parseInt(dataMap.get("zxlx").toString()) == 5) {
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "sjh", "xm");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            if (dataMap.get("sjh").toString().length() != 11) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.SjHError);
            }
            bckjBizZxzx.setDh(dataMap.get("sjh").toString());
            bckjBizZxzx.setTwName(dataMap.get("xm").toString());
        }
        bckjBizZxzx.setZxlx(Integer.parseInt(dataMap.get("zxlx").toString()));
        bckjBizZxzx.setWtnr((dataMap.get("wtnr").toString()));
        bckjBizZxzx.setTwrq(new Date());
        //是否显示 1显示 0不显示
        bckjBizZxzx.setSfxs(1);
        bckjBizZxzx.setState(1);
        bckjBizZxzx.setExp1("1");
        bckjBizZxzx.setLyip(dataMap.get("ipAdrress").toString());
        saveOrUpdate(bckjBizZxzx);
        /**w
         * 返回老师回复天数
         */
        if(bckjBizZxzx.getZxlx()==2){
            Map consultsReplyDay = getConsultsReplyDay();
            if(consultsReplyDay.get("hfts").toString().indexOf("无设置")!=-1){
                return ResponseMessage.sendOK("咨询已提交，请等待回复");
            }
            else {
                return ResponseMessage.sendOK("你的咨询老师会在"+consultsReplyDay.get("hfts").toString()+"天内回复,请实时关注");
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    /**
     * 获得字典表中专家回复的天数
     * @return
     */
    public Map getConsultsReplyDay() {
        Map<String, String> resMap = Maps.newHashMap();
        String consultsReplyDay = this.dao.getConsultsReplyDay();
        if (!com.zghzbckj.util.TextUtils.isEmpty(consultsReplyDay)) {
            resMap.put("hfts", consultsReplyDay);
        } else {
            if (TextUtils.isEmpty(resMap) || TextUtils.isEmpty(resMap.get(0)))
                resMap.put("hfts", "无设置");
        }
        return resMap;
    }



    public ResponseMessage historyConsult(Map<String, Object> dataMap) {
        if (Integer.parseInt(dataMap.get("zxlx").toString()) != 2) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        Page<Map<String, Object>> page = new Page(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        List<Map<String, Object>> listByZxlx = this.dao.findListByZxlx(dataMap);
        page.setList(listByZxlx);
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage removeHistoryConsult(Map<String, Object> dataMap) {
        this.dao.deleteByMap(dataMap);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public ResponseMessage getListByZxzyid(Map<String, Object> dataMap) {
        Page<BckjBizZxzx> page = new Page(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        dataMap.put("orderBy", "createtime desc");
        List<BckjBizZxzx> listByMap = this.dao.findListByMap(dataMap);
        for(BckjBizZxzx bckjBizZxzx:listByMap){
            BckjBizYhkz oneByYhRefOwid = bckjBizYhkzService.getOneByYhRefOwid(bckjBizZxzx.getTwOwid());
            bckjBizZxzx.setExp1(oneByYhRefOwid.getXsbj());
        }
        page.setList(listByMap);
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage replyConsult(Map<String, Object> dataMap) {
        BckjBizZxzx bckjBizZxzx = this.dao.getOneByCondition(dataMap);
        dataMap.remove("owid");
        if (TextUtils.isEmpty(bckjBizZxzx)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.GetMessageFail);
        }
        if (bckjBizZxzx.getState() == 2) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.FAIL_MESSAGE);
        }
        MapUtil.easySetByMap(dataMap, bckjBizZxzx);
        bckjBizZxzx.setState(2);
        bckjBizZxzx.setHdrq(new Date());
        saveOrUpdate(bckjBizZxzx);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public ResponseMessage showJyMessageList(Integer pageNo, Integer pageSize, String state) {
        Page<Map<String, Object>> page = new Page<>(pageNo, pageSize);
        HashMap<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("zxlx", 5);
        dataMap.put("page", page);
        dataMap.put("state", state);
        page.setList(this.dao.findlyList(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    public ResponseMessage getZxzxDetail(Map<String, Object> dataMap) {
        return ResponseMessage.sendOK(get(dataMap.get("owid").toString()));
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage saveZxzxDetail(Map<String, Object> dataMap) {
        dataMap.put("state", 2);
        dataMap.put("owid", dataMap.get("owid"));
        this.dao.saveZxzxDetail(dataMap);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);

    }

    public ResponseMessage historyMessage(Map<String, Object> dataMap) {
        Page<Object> page = new Page<>(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        dataMap.put("exp1", 2);
        page.setList(this.dao.historyMessage(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage faBu(List<Object> list, String exp1) {
        for (Object component : list) {
            if (TextUtils.isEmpty(((LinkedHashMap) component).get("owid"))) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.FAIL_MESSAGE);
            }
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("owid", ((LinkedHashMap) component).get("owid"));
            dataMap.put("exp1", exp1);
            this.dao.faBu(dataMap);
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
}