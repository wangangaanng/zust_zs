/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.*;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.IdGen;
import com.zghzbckj.base.util.PageUtil;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.dao.BckjBizYhxxDao;
import com.zghzbckj.manage.entity.*;
import com.zghzbckj.manage.utils.MessageUtil;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.PageUtils;
import com.zghzbckj.wechat.model.WxXcxUserModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.alibaba.druid.support.monitor.annotation.AggregateType.Sum;


/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizYhxxService extends CrudService<BckjBizYhxxDao, BckjBizYhxx> {


    private static final Logger log = Logger.getLogger(BckjBizYhxxService.class);
    @Autowired
    BckjBizYhglService bckjBizYhglService;
    @Autowired
    BckjBizYhkzService bckjBizYhkzService;
    @Autowired
    BckjBizSybService bckjBizSybService;
    @Autowired
    BckjBizJyschemeService bckjBizJyschemeService;
    @Autowired
    private BckjBizUserlogService bckjBizUserlogService;
//    @Autowired
//    BckjBizXsgzSer bckjbizXsgzSer;


    @Override
    public BckjBizYhxx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizYhxx> findList(BckjBizYhxx bckjBizYhxx) {
        return super.findList(bckjBizYhxx);
    }

    @Override
    public PageInfo<BckjBizYhxx> findPage(Page<BckjBizYhxx> page, BckjBizYhxx bckjBizYhxx) {
        return super.findPage(page, bckjBizYhxx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizYhxx bckjBizYhxx) {
        super.saveOrUpdate(bckjBizYhxx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizYhxx bckjBizYhxx) {
        super.delete(bckjBizYhxx);
    }

    /**
     * <p>方法:findPagebckjBizYhxx TODO后台BckjBizYhxx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizYhxx(List<FilterModel> filters, Integer state, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (!TextUtils.isEmpty(state)) {
            dataMap.put("yhlx", state);
        }
        dataMap.put("state", 1);

        if (!com.ourway.base.utils.TextUtils.isEmpty(dataMap.get("createtime2"))) {
            String date = DateUtil.getAfterDate(dataMap.get("createtime2").toString(), 1);
            dataMap.put("createtime2", date);
        }
        PageInfo<BckjBizYhxx> page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");


        List<BckjBizYhxx> records = page.getRecords();
        BckjBizYhxx jbxx = new BckjBizYhxx();
        jbxx.setXm("共有：" + page.getTotalCount() + "个用户");
        jbxx.setReadOnly(true);
        records.add(0, jbxx);

        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizYhxx TODO保存BckjBizYhxx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizYhxx(Map<String, Object> mapData) {
        BckjBizYhxx bckjBizYhxx = JsonUtil.map2Bean(mapData, BckjBizYhxx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizYhxx bckjBizYhxxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizYhxx, bckjBizYhxxIndata);
            bckjBizYhxx = bckjBizYhxxIndata;
        }
        saveOrUpdate(bckjBizYhxx);
        return ResponseMessage.sendOK(bckjBizYhxx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizYhxx </p>
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
            BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
            bckjBizYhxx.setOwid(owid);
            this.dao.delete(bckjBizYhxx);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }


    /**
     * <p>功能描述: 用户登录 </p >
     * <ul>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11 </li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage logIn(Map<String, Object> datamap) throws ParseException {
        BckjBizSyb oneBySfz = null;
        Map<String, Object> resMap = Maps.newHashMap();
        //根据账号登入
        Map<String, Object> map = this.dao.logIn(datamap);
        if (TextUtils.isEmpty(map)) {
            map = this.dao.logInBySfz(datamap);
            if (TextUtils.isEmpty(map)) {
                datamap.put("sfz", datamap.get("yhDlzh").toString());
                oneBySfz = bckjBizSybService.getOneBySfz(datamap);
                datamap.remove("sfz");
                if (TextUtils.isEmpty(oneBySfz)) {
                    oneBySfz = bckjBizSybService.getOneByXsxh(datamap.get("yhDlzh").toString());
                }
                if (TextUtils.isEmpty(oneBySfz)) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
                }
            }
        }
        if (!TextUtils.isEmpty(map)) {
            if (!(datamap.get("yhDlmm").toString().equalsIgnoreCase(map.get("yhDlmm").toString()))) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.PasswordError);
            }
            //如果不是为学生
            if (Integer.parseInt(map.get("olx").toString()) != 0) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
            }
        }
        if (!TextUtils.isEmpty(oneBySfz)) {
            if (!(datamap.get("yhDlmm").toString().equalsIgnoreCase(TextUtils.MD5(oneBySfz.getSfz().substring(oneBySfz.getSfz().length() - 6))))) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.PasswordError);
            }
        }
        BckjBizYhxx bckjBizYhxx = null;
        BckjBizYhkz bckjBizYhkz = null;
        if (!com.zghzbckj.util.TextUtils.isEmpty(oneBySfz) && com.zghzbckj.util.TextUtils.isEmpty(map)) {

            bckjBizYhxx = new BckjBizYhxx();
            bckjBizYhkz = new BckjBizYhkz();
            bckjBizYhxx.setXm(oneBySfz.getXm());
            bckjBizYhxx.setSfz(oneBySfz.getSfz());
            bckjBizYhxx.setYhDlmm(TextUtils.MD5(oneBySfz.getSfz().substring(oneBySfz.getSfz().length() - 6)));
            if (!TextUtils.isEmpty(oneBySfz.getSjh())) {
                bckjBizYhxx.setSjh(oneBySfz.getSjh());
            }
            if (!com.zghzbckj.util.TextUtils.isEmpty(oneBySfz.getXsxh())) {
                bckjBizYhkz.setXsxh(oneBySfz.getXsxh());
                bckjBizYhxx.setYhDlzh(oneBySfz.getXsxh());
            }
            bckjBizYhkz.setOlx(0);
            bckjBizYhxx.setYhlx(1);
            if (!TextUtils.isEmpty(oneBySfz.getXb())) {
                bckjBizYhxx.setXb(oneBySfz.getXb());
            }
            if (!TextUtils.isEmpty(oneBySfz.getCsrq())) {
                bckjBizYhxx.setCsrq(oneBySfz.getCsrq());
            }
            if (!TextUtils.isEmpty(oneBySfz.getMz())) {
                bckjBizYhxx.setMz(oneBySfz.getMz());
            }
            if (!TextUtils.isEmpty(oneBySfz.getYhRefOwid())) {
                bckjBizYhxx.setOwid(oneBySfz.getYhRefOwid());
                bckjBizYhkz.setYhRefOwid(oneBySfz.getYhRefOwid());
            }
            bckjBizYhkz.setXsxy(oneBySfz.getXsxy());
            bckjBizYhkz.setXsbj(oneBySfz.getXsbj());
            bckjBizYhkz.setXszy(oneBySfz.getXszy());
            BckjBizYhkz oneByYhRefOwid = bckjBizYhkzService.getOneByYhRefOwid(oneBySfz.getYhRefOwid());
            if (com.zghzbckj.util.TextUtils.isEmpty(oneByYhRefOwid)) {
                bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
            }
            ResponseMessage oneByOwid = getOneByOwid(oneBySfz.getYhRefOwid());
            if (TextUtils.isEmpty(oneByOwid)) {
                insert(bckjBizYhxx);
            }
        }
        //如果为学生的小程序登入
        if (!TextUtils.isEmpty(datamap.get("type")) && datamap.get("type").toString().indexOf("xcx") != -1) {
            ValidateMsg msg = ValidateUtils.isEmpty(datamap, "unionid", "openid", "wxid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            bckjBizYhxx = this.dao.getOneByCondition(map);
            BckjBizYhgl bckjBizYhgl = new BckjBizYhgl();
            bckjBizYhxx.setUnionid(datamap.get("unionid").toString());
            bckjBizYhgl.setOpenid(datamap.get("openid").toString());
            bckjBizYhgl.setWxbh(datamap.get("wxid").toString());
            bckjBizYhgl.setGzsj(new Date());
               /* if (!TextUtils.isEmpty(datamap.get("nickname"))) {
                    bckjBizYhxx.setXm(datamap.get("nickname").toString());
                }*/
            /*if (!TextUtils.isEmpty(datamap.get("gender"))) {
                bckjBizYhxx.setXb(Integer.parseInt(datamap.get("gender").toString()));
            }*/
            if (!TextUtils.isEmpty(datamap.get("city"))) {
                bckjBizYhxx.setCity(datamap.get("city").toString());
            }
            if (!TextUtils.isEmpty(datamap.get("province"))) {
                bckjBizYhxx.setProv(datamap.get("province").toString());
            }
            if (!TextUtils.isEmpty(datamap.get("country"))) {
                bckjBizYhxx.setArea(datamap.get("country").toString());
            }
            if (!TextUtils.isEmpty(datamap.get("avatarUrl"))) {
                bckjBizYhxx.setYhtx(datamap.get("avatarUrl").toString());
            }
            bckjBizYhxx.setDlzhsj(new Date());
            saveOrUpdate(bckjBizYhxx);
            bckjBizYhgl.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizYhglService.saveOrUpdate(bckjBizYhgl);
        }
        //设置最近登录时间
        if (!com.zghzbckj.util.TextUtils.isEmpty(map)) {
            this.dao.updateDlsj(map.get("owid").toString());
            resMap.put("olx", map.get("olx"));
            resMap.put("owid", map.get("owid"));
            resMap.put("yhtx", map.get("yhtx"));
            resMap.put("sjh", map.get("sjh"));
            resMap.put("xsxh", map.get("xsxh"));
            resMap.put("xm", map.get("xm"));
            addLog(map);
        } else {
            this.dao.updateDlsj(bckjBizYhxx.getOwid());
            /*resMap.put("olx",bckjBizYhkz.getOlx());*/
            resMap.put("owid", bckjBizYhxx.getOwid());
            resMap.put("yhtx", bckjBizYhxx.getYhtx());
            /*resMap.put("sjh", bckjBizYhxx.getSjh());*/
            resMap.put("xsxh", bckjBizYhkz.getXsxh());
            resMap.put("xm", bckjBizYhxx.getXm());
            addLog(resMap);
        }
        return ResponseMessage.sendOK(resMap);
    }

    private void addLog(Map<String, Object> map) {
        BckjBizUserlog loginLog = new BckjBizUserlog();
        loginLog.setDoContent(CommonModuleContant.USER_LOGIN_LOG);
        loginLog.setDoTime(new Date());
        loginLog.setYhRefOwid(map.get("owid").toString());
        loginLog.setYhlx(Integer.valueOf(map.get("olx").toString()));
        if (null != map.get("xsxh")) {
            loginLog.setXsxh(map.get("xsxh").toString());
        }
        if (null != map.get("xm")) {
            loginLog.setName(map.get("xm").toString());
        }
        bckjBizUserlogService.saveOrUpdate(loginLog);
    }


    /**
     * <p>功能描述: 修改密码 </p >
     * <ul>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/11 </li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage modfiyPassword(Map<String, Object> datamap) {
        HashMap<String, Object> map = Maps.newHashMap();
        String newPassword = datamap.get("newPassword").toString();
        String newPasswordAgain = datamap.get("newPasswordAgain").toString();
        if (!newPassword.equals(newPasswordAgain)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NewPasswordNotMatch);
        }
        String oldPsw = TextUtils.MD5(datamap.get("oldPassword").toString()).toUpperCase();
        BckjBizYhxx bckjbizyhxx = this.dao.getOneByCondition(datamap);
        if (TextUtils.isEmpty(bckjbizyhxx)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
        }
        if (!bckjbizyhxx.getYhDlmm().equals(oldPsw)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.PasswordError);
        }
        String newPsw = TextUtils.MD5(datamap.get("newPassword").toString()).toUpperCase();
        map.put("owid", datamap.get("owid"));
        map.put("yhDlmm", newPsw);
        this.dao.modfiyPassword(map);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    public ResponseMessage getOneByOwid(String owid) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("owid", owid);
        return ResponseMessage.sendOK(this.dao.getOneByCondition(map));
    }

    public BckjBizYhxx getByOwid(String owid) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("owid", owid);
        return this.dao.getOneByCondition(map);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage appletLogin(Map<String, Object> dataMap) {
        Map<String, Object> resMap = Maps.newHashMap();
        /*String psw = TextUtils.MD5(dataMap.get("yhDlmm").toString()).toUpperCase();*/
        /*dataMap.remove("psw");*/
        Map<String, Object> map = this.dao.logInByteach(dataMap);
        if (TextUtils.isEmpty(map)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
        }
        if (!dataMap.get("yhDlmm").toString().equalsIgnoreCase(map.get("yhDlmm").toString())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.PasswordError);
        }
        BckjBizYhxx bckjBizYhxx = this.dao.getOneByCondition(map);
        BckjBizYhgl bckjBizYhgl = new BckjBizYhgl();
        bckjBizYhxx.setUnionid(dataMap.get("unionid").toString());
        bckjBizYhgl.setOpenid(dataMap.get("openid").toString());
        bckjBizYhgl.setWxbh(dataMap.get("wxid").toString());
        bckjBizYhgl.setGzsj(new Date());
        /* if (!TextUtils.isEmpty(dataMap.get("nickname"))) {
            bckjBizYhxx.setXm(dataMap.get("nickname").toString());
        }*/
       /* if (!TextUtils.isEmpty(dataMap.get("gender"))) {
            bckjBizYhxx.setXb(Integer.parseInt(dataMap.get("gender").toString()));
        }*/
        if (!TextUtils.isEmpty(dataMap.get("city"))) {
            bckjBizYhxx.setCity(dataMap.get("city").toString());
        }
        if (!TextUtils.isEmpty(dataMap.get("province"))) {
            bckjBizYhxx.setProv(dataMap.get("province").toString());
        }
        if (!TextUtils.isEmpty(dataMap.get("country"))) {
            bckjBizYhxx.setArea(dataMap.get("country").toString());
        }
        if (!TextUtils.isEmpty(dataMap.get("avatarUrl"))) {
            bckjBizYhxx.setYhtx(dataMap.get("avatarUrl").toString());
        }
        bckjBizYhxx.setDlzhsj(new Date());
        saveOrUpdate(bckjBizYhxx);
        bckjBizYhgl.setYhRefOwid(bckjBizYhxx.getOwid());
        bckjBizYhglService.saveOrUpdate(bckjBizYhgl);
        map.remove("sfz");
        map.remove("sjh");
        return ResponseMessage.sendOK(map);
    }


    /**
     * insert 所有的entity
     *
     * @param entitys corder wangangaanng
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveAll(List<BckjBizYhxx> entitys) {
        Iterator var2 = entitys.iterator();
        while (var2.hasNext()) {
            BckjBizYhxx entity = (BckjBizYhxx) var2.next();
            this.dao.insert(entity);
        }
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertInfo(BckjBizYhxx bckjBizYhxx) {
        this.dao.insert(bckjBizYhxx);
    }


    public void insert(BckjBizYhxx bckjBizYhxx) {
        this.dao.insert(bckjBizYhxx);
    }

    public Map<String, Object> queryDocument(Map<String, Object> dataMap) {
        return this.dao.queryDocument(dataMap);
    }

    /**
     * 后台根据job 的 owid 获得关注学生信息
     *
     * @param type
     * @param filterModels
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageInfo<Map> getYhxxInfoList(Integer type, List<FilterModel> filterModels, Integer pageSize, Integer pageNo) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
        Page<Map> page = new Page<>(pageNo, pageSize);
        dataMap.put("page", page);
        List<Map> resLists = null;
        //签到
        if (type == 1) {
            //获得总人数
            String Sum = this.dao.getYhxxQdSum(dataMap);
            //签到未成功人数
            page = new Page<>(pageNo, pageSize);
            dataMap.put("page", page);
            String NoSuccessSum = this.dao.getYhxxQdNoSuccessSum(dataMap);
            //签到成功人数
            page = new Page<>(pageNo, pageSize);
            dataMap.put("page", page);
            String SuccessSum = this.dao.getYhxxQdSuccessSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xsxh", "签到总人数：" + Sum + "其中 成功人数:" + SuccessSum + "未成功人数:" + NoSuccessSum);
            resMap.put("readonly", true);
            page = new Page<>(pageNo, pageSize);
            dataMap.put("page", page);
            resLists = this.dao.getYhxxQdInfo(dataMap);
            resLists.add(0, resMap);
        }
        //关注
        if (type == 0) {
            String sum = this.dao.getYhxxGzSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xsxh", "关注总人数：" + sum);
            resMap.put("readonly", true);
            page = new Page<>(pageNo, pageSize);
            dataMap.put("page", page);
            resLists = this.dao.getYhxxGzInfo(dataMap);
            resLists.add(0, resMap);
        }
        //如果为报名
        if (type == 2) {
            String sum = this.dao.getYhxxBmSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xsxh", "报名总人数：" + sum);
            resMap.put("readonly", true);
            page = new Page<>(pageNo, pageSize);
            dataMap.put("page", page);
            resLists = this.dao.getYhxxBmInfo(dataMap);
            resLists.add(0, resMap);
        }
        if (type == 9) {
            String sum = this.dao.getXchBmSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xm", "参加总人数：" + sum);
            resMap.put("readonly", true);
            page = new Page<>(pageNo, pageSize);
            dataMap.put("page", page);
            resLists = this.dao.getXchBm(dataMap);
            resLists.add(0, resMap);
        }
        page.setList(resLists);
        return PageUtils.assimblePageInfo(page);
    }


    public BckjBizYhxx getOneBySfz(String sfz) {
        return this.dao.getOneBySfz(sfz);
    }

    public void deleteBySfz(String yhRefOwid) {
        this.dao.deleteBySfz(yhRefOwid);
    }

    /***
     *<p>方法:swYtzc TODO三位一体注册 </p>
     *<ul>
     *<li> @param yhxx TODO</li>
     *<li>@return java.lang.String  </li>
     *<li>@author D.chen.g </li>
     *<li>@date 2019/10/23 17:53  </li>
     *</ul>
     */
    @Transactional(readOnly = false)
    public BckjBizYhxx swYtzc(Map yhxx) throws CustomerException {
        BckjBizYhxx indata = getBySwZh(yhxx, "swZh");
        if (null == indata) {
            throw new CustomerException("手机号信息不存在，请重新发送验证码");
        }
        if (indata.getState() == 1) {
            throw new CustomerException("此手机号已经注册！");
        }
        BckjBizYhxx userNew = JackSonJsonUtils.map2Bean(yhxx, BckjBizYhxx.class);
        if (!indata.getYzm().equals(userNew.getYzm())) {
            throw new CustomerException("验证码错误！");
        }
        BeanUtil.copyPropertiesIgnoreNull(userNew, indata);
        indata.setState(1);
        indata.setSwMm(TextUtils.MD5(indata.getSwMm()).toUpperCase());
        this.saveOrUpdate(indata);
        return indata;
    }

    /**
     * 根据账号和用户类型获取三位一体用户
     *
     * @param mapData
     * @return
     */
    public BckjBizYhxx getBySwZh(Map<String, Object> mapData, String paramName) {
        Map param = Maps.newHashMap();
        param.put(paramName, MapUtils.getString(mapData, paramName));
        param.put("yhlx", 3);
        BckjBizYhxx indata = this.dao.getOneByCondition(param);
        return indata;
    }

    /**
     * <p>方法:loginSwty TODO登录 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.lang.String  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/23 17:52  </li>
     * </ul>
     */
    public BckjBizYhxx loginSwty(Map<String, Object> mapData) throws CustomerException {
        BckjBizYhxx indata = getBySwZh(mapData, "swZh");
        if (null == indata || indata.getState() != 1) {
            throw new CustomerException("用户尚未注册");
        }
        String passWord = TextUtils.MD5(MapUtils.getString(mapData, "swMm")).toUpperCase();
        if (!(passWord.equals(indata.getSwMm()))) {
            throw new CustomerException("密码错误");
        } else {
            return indata;
        }
    }


    @Transactional(readOnly = false)
    public void swWxinfo(WxXcxUserModel wxUser) {
        if (null != wxUser) {
            if (null == wxUser.getUnionid()) {
                return;
            }
            Map param = Maps.newHashMap();
            param.put("unionid", wxUser.getUnionid());
            param.put("yhlx", 3);
            BckjBizYhxx indata = this.dao.getOneByCondition(param);
            if (null == indata) {
                BckjBizYhxx yhxx = new BckjBizYhxx();
                yhxx.setState(0);
                yhxx.setYhlx(3);
                if (!TextUtils.isEmpty(wxUser.getGender())) {
                    yhxx.setXb(Integer.valueOf(wxUser.getGender()));
                }
                yhxx.setUnionid(wxUser.getUnionid());
                yhxx.setYhtx(wxUser.getAvatarUrl());
                this.save(yhxx);
                BckjBizYhgl yhgl = new BckjBizYhgl();
                yhgl.setYhRefOwid(yhxx.getOwid());
                yhgl.setWxbh(wxUser.getWxid());
                yhgl.setOpenid(wxUser.getOpenId());
                yhgl.setGzsj(new Date());
                bckjBizYhglService.save(yhgl);
            }
        }
    }

    /**
     * <p>方法:forgetPwd TODO忘记密码 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizYhxx  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/23 19:15  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public BckjBizYhxx forgetPwd(Map<String, Object> mapData) throws CustomerException {
        BckjBizYhxx indata = getBySwZh(mapData, "swZh");
        String yzm = MapUtils.getString(mapData, "yzm");
        if (null == indata) {
            throw new CustomerException("不存在此用户");
        }
        if (indata.getState() == 0) {
            throw new CustomerException("此手机号未注册");
        }
        if (!yzm.equals(indata.getYzm())) {
            throw new CustomerException("验证码错误");
        }
        indata.setSwMm(TextUtils.MD5(MapUtils.getString(mapData, "swMm")).toUpperCase());
        saveOrUpdate(indata);
        return indata;
    }

    public void updateBySfz(BckjBizYhxx bckjBizYhxx) {
        this.dao.updateBySfz(bckjBizYhxx);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteByOwid(String owid) {
        this.dao.deleteByOwid(owid);
    }

    /**
     * 招生考生报名
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage candidatesRegistration(Map<String, Object> dataMap) throws IllegalAccessException, InstantiationException {
        if (dataMap.get("yzm").toString().indexOf(com.zghzbckj.base.util.CacheUtil.getVal(dataMap.get("sjh").toString())) == -1) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "验证码输入错误!");
        }
        BckjBizYhxx bckjBizYhxx1 = this.dao.getZsBySjh(dataMap.get("sjh").toString());
        if (!TextUtils.isEmpty(bckjBizYhxx1)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "此手机号已报名!");
        }
        BckjBizYhgl bckjBizYhgl = BckjBizYhgl.class.newInstance();
        BckjBizYhxx bckjBizYhxx = BckjBizYhxx.class.newInstance();
        //如果是pc
        if (dataMap.get("type").toString().equals("1")) {
            MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        }
        //如果是小程序
        if (dataMap.get("type").toString().equals("2")) {
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "unionid", "openid", "wxid");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            MapUtil.easySetByMap(dataMap, bckjBizYhxx);
            MapUtil.easySetByMap(dataMap, bckjBizYhgl);
            bckjBizYhxx.setDlzhsj(new Date());
            bckjBizYhxx.setOwid(IdGen.uuid());
            bckjBizYhgl.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizYhglService.saveOrUpdate(bckjBizYhgl);
        }
        //设置报名年份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String format = sdf.format(new Date());
        bckjBizYhxx.setExp8(format);
        bckjBizYhxx.setYhlx(4);
        bckjBizYhxx.setExp10("未预约开放日");
        this.insert(bckjBizYhxx);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    /**
     * 招生考生报名发送验证码
     *
     * @param dataMap
     * @return
     */
    public ResponseMessage sendBmYzm(Map<String, Object> dataMap) {
        //生成验证码
        String yzm = CommonService.getRandom();
        BckjBizYhxx bckjBizYhxx = this.dao.getZsBySjh(dataMap.get("sjh").toString());
        if (!TextUtils.isEmpty(bckjBizYhxx)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "此手机号已报名!");
        }
        try {
            MessageUtil.sendMessageCode(dataMap.get("sjh").toString(), yzm);
            com.zghzbckj.base.util.CacheUtil.setVal(dataMap.get("sjh").toString(), Integer.parseInt(yzm));
            return ResponseMessage.sendOK("获取成功！");
        } catch (Exception e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "获取失败!");
        }
    }

    /**
     * 考生报名list
     *
     * @return
     */
    public PageInfo<BckjBizYhxx> getZsList(List<FilterModel> filters, Integer pageNo, Integer pageSize) throws IllegalAccessException, InstantiationException {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<BckjBizYhxx> page = new Page<>(pageNo, pageSize);
        dataMap.put("page", page);
        List<BckjBizYhxx> zsList = this.dao.getZsList(dataMap);
        BckjBizYhxx bckjBizYhxx = BckjBizYhxx.class.newInstance();
        page.setList(zsList);
        bckjBizYhxx.setXm("共有" + page.getCount() + "条");
        bckjBizYhxx.setState(null);
        bckjBizYhxx.setReadOnly(true);
        zsList.add(0, bckjBizYhxx);
        page.setList(zsList);
        return PageUtils.assimblePageInfo(page);
    }

    /**
     * 预约校园开放日
     *
     * @param dataMap
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage apOfCaOpDay(Map<String, Object> dataMap) {
        if (dataMap.get("yzm").toString().indexOf(com.zghzbckj.base.util.CacheUtil.getVal(dataMap.get("sjh").toString())) == -1) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "验证码输入错误!");
        }
        BckjBizYhxx bckjBizYhxx = this.dao.getZsBySjh(dataMap.get("sjh").toString());
        if (bckjBizYhxx.getExp10().indexOf("未预约开放日") == -1) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "您已预约过开放日，不能再次预约!");
        }
        bckjBizYhxx.setExp10(dataMap.get("val2").toString());
        saveOrUpdate(bckjBizYhxx);
        return ResponseMessage.sendOK("预约成功！");
    }


    /**
     * 开放日预约发送验证码
     *
     * @param dataMap
     * @return
     */
    public ResponseMessage sendYyYzm(Map<String, Object> dataMap) {
        //生成验证码
        String yzm = CommonService.getRandom();
        BckjBizYhxx bckjBizYhxx = this.dao.getZsBySjh(dataMap.get("sjh").toString());
        if (TextUtils.isEmpty(bckjBizYhxx)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "未考生报名,不能预约!");
        }
        try {
            MessageUtil.sendMessageCode(dataMap.get("sjh").toString(), yzm);
            com.zghzbckj.base.util.CacheUtil.setVal(dataMap.get("sjh").toString(), Integer.parseInt(yzm));
            return ResponseMessage.sendOK("获取成功！");
        } catch (Exception e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "获取失败!");
        }
    }

    /**
     * 展示校园开发日页面
     *
     * @return
     */
    public List<Map> getShowCaOpDayDate() {
        List<Map> dicListMapByType = bckjBizJyschemeService.getDicListMapByType(70000);
        List<Map> resMaps = Lists.newArrayList();
        for (Map map : dicListMapByType) {
            HashMap<String, Object> resMap = Maps.newHashMap();
            resMap.put(map.get("val1").toString(), map.get("val2").toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.ORA_DATE_FORMAT);
            if ((DateUtil.getDate(simpleDateFormat.format(new Date()), DateUtil.ORA_DATE_FORMAT).equals(DateUtil.getDate(map.get("val2").toString(), "yyyy-MM-dd")))) {
                resMaps.add(resMap);
            }
            if (DateUtil.getDate(simpleDateFormat.format(new Date()), DateUtil.ORA_DATE_FORMAT).before(DateUtil.getDate(map.get("val2").toString(), "yyyy-MM-dd"))) {
                resMaps.add(resMap);
            }
        }
        return resMaps;
    }

    public PageInfo<Map> getCaOpDayDateList(Map<String, Object> dataMap) {
        Page<Map> page = new Page<>(MapUtils.getInt(dataMap, "pageNo"), MapUtils.getInt(dataMap, "pageSize"));
        dataMap.put("page", page);
        List<Map> lists = this.dao.getCaOpDayDateList(dataMap);
        for (Map map : lists) {
            map.put("state", "1");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.ORA_DATE_FORMAT);
            if ((DateUtil.getDate(simpleDateFormat.format(new Date()), DateUtil.ORA_DATE_FORMAT).equals(DateUtil.getDate(map.get("dicVal2").toString(), "yyyy-MM-dd")))) {
                map.put("state", "2");
            }
            if (DateUtil.getDate(simpleDateFormat.format(new Date()), DateUtil.ORA_DATE_FORMAT).before(DateUtil.getDate(map.get("dicVal2").toString(), "yyyy-MM-dd"))) {
                map.put("state", "2");
            }
        }
        page.setList(lists);
        return PageUtils.assimblePageInfo(page);
    }

    @Transactional(readOnly = false)
    public Map proxyLogin(Map<String, Object> datamap) {
        Map<String, Object> map = this.dao.logIn(datamap);
        if (!TextUtils.isEmpty(map) && !TextUtils.isEmpty(map.get("owid"))) {
            this.dao.updateDlsj(map.get("owid").toString());
            Map mapRes = Maps.newHashMap();
            mapRes.put("stuOwid", map.get("owid"));
            mapRes.put("stuSjh", map.get("sjh"));
            mapRes.put("stuXm", map.get("xm"));
            mapRes.put("userType", 1);
            mapRes.put("yhOwid", map.get("owid"));
            return mapRes;
        }
        return map;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateDlzh(Map<String, Object> xxMap) {
        this.dao.updateDlzh(xxMap);
    }


//    @Transactional(readOnly = false, rollbackFor = Exception.class)
//    public ResponseMessage zsXchBm(Map<String, Object> dataMap) throws Exception {
//        Map<String, Object> dicMap = this.dao.getOneDicByOwid(dataMap.get("owid").toString());
//        Integer dicVal5 = MapUtils.getInt(dicMap, "dicVal5");
//        Integer dicVal4 = MapUtils.getInt(dicMap, "dicVal4");
//        if (dicVal4 <= dicVal5) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, "已达报名最多人数,报名失败");
//        }
//        dicVal5++;
//        dicMap.put("dicVal5", dicVal5.toString());
//        BckjBizYhxx sfz = getOneBySfz(dataMap.get("sfz").toString());
//        if (TextUtils.isEmpty(sfz)) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, "无法找到此生源,请检查输入信息是否有误");
//        }
//        ResponseMessage oneXsgz = bckjbizXsgzSer.getOneXchByYhRefOwid(sfz.getOwid(), dataMap.get("owid").toString());
//        if (!TextUtils.isEmpty(oneXsgz) && oneXsgz.getBackCode() == 0 && !TextUtils.isEmpty(oneXsgz.getBean())) {
//            return ResponseMessage.sendError(ResponseMessage.FAIL, "您已报名,无法再次报名!");
//        }
//        String dicOwid = dataMap.get("owid").toString();
//        dataMap.remove("owid");
//        MapUtil.easySetByMap(dataMap, sfz);
//        BckjBizYhkz oneByYhRefOwid = bckjBizYhkzService.getOneByYhRefOwid(sfz.getOwid());
//        MapUtil.easySetByMap(dataMap, oneByYhRefOwid);
//        BckjBizSyb oneBySfz = bckjBizSybService.getOneBySfz(dataMap);
//        MapUtil.easySetByMap(dataMap, oneBySfz);
//        bckjBizSybService.saveOrUpdate(oneBySfz);
//        bckjBizYhkzService.saveOrUpdate(oneByYhRefOwid);
//        saveOrUpdate(sfz);
//        HashMap<String, Object> xsgzMap = Maps.newHashMap();
//        xsgzMap.put("jobRefOwid", dicOwid);
//        xsgzMap.put("yhRefOwid", sfz.getOwid());
//        xsgzMap.put("xxlb", 9);
//        xsgzMap.put("gzsj", new Date());
//        ResponseMessage responseMessage = bckjbizXsgzSer.mapXsgzInfo(xsgzMap);
//        if (!TextUtils.isEmpty(responseMessage) && responseMessage.getBackCode() == 0 && !TextUtils.isEmpty(responseMessage.getBean())) {
//            if (CommonConstant.SUCCESS_MESSAGE.equals(responseMessage.getBean().toString())) {
//                MessageUtil.sendMessage(dataMap.get("sjh").toString(), "感谢您报名参加" + dicMap.get("dicVal1").toString() + ",开始时间为" + dicMap.get("dicVal2").toString() + ",举办地点为" + dicMap.get("dicVal3") + ",请您实时关注");
//            }
//            dicMap.put("owid", dicOwid);
//            this.dao.updateDicByMap(dicMap);
//            return ResponseMessage.sendOK("报名成功,请查收短信");
//        }
//        return ResponseMessage.sendError(ResponseMessage.FAIL, "报名失败");
//
//    }


    public PageInfo<Map> getQdList(Integer zwlx, List<FilterModel> filterModels, Integer pageSize, Integer pageNo) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
        Page<Map> page = new Page<>(pageNo, pageSize);
        dataMap.put("page", page);
        dataMap.put("zwlx", zwlx);
        //获得总人数
        List<Map> qdLists=this.dao.getQdList(dataMap);
        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("xsxh", "签到统计总人数:" + page.getCount());
        resMap.put("readonly", true);
        qdLists.add(0,resMap);
        page.setList(qdLists);
        return PageUtils.assimblePageInfo(page);

    }

    public PageInfo<Map> getQd(Map<String, Object> dataMap, Integer type) {
        dataMap.put("zwlx",type);
        Page<Map> page=new Page<>(MapUtils.getInt(dataMap,"pageNo"),MapUtils.getInt(dataMap,"pageSize"));
        dataMap.put("page",page);
        List<Map> qd = this.dao.getQd(dataMap);
        page.setList(qd);
       return PageUtils.assimblePageInfo(page);
    }

    public List<String> getCustomList(Map<String, Object> dataMap) {
        List<String> lists = Lists.newArrayList();
        if (dataMap.get("key").toString().indexOf("xszy")!=-1){
            lists=this.dao.getCustomListXszy(dataMap);
        }
        if (dataMap.get("key").toString().indexOf("xsxy")!=-1){
            lists=this.dao.getCustomListXsxy(dataMap);
        }if (dataMap.get("key").toString().indexOf("xsbj")!=-1){
            lists=this.dao.getCustomListXsbj(dataMap);
        }
        return lists;
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void deleteBaoMing(List<Map> deleteMaps) {
        this.dao.deleteBaoMing(deleteMaps);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveBaoMing(List<Map> saveMaps) throws IllegalAccessException, InstantiationException {
        List<BckjBizYhxx> yhxxes= Lists.newArrayList();
        for (Map<String,Object> map:saveMaps){
            BckjBizYhxx bckjBizYhxx = BckjBizYhxx.class.newInstance();
            bckjBizYhxx.setYhlx(5);
            MapUtil.easySetByMap(map,bckjBizYhxx);
            yhxxes.add(bckjBizYhxx);
        }
        saveOrUpdateAll(yhxxes);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void updateBaoMing(List<Map> updateMaps) {
        List<BckjBizYhxx> bckjBizYhxxes= Lists.newArrayList();
        for (Map map:updateMaps){
            BckjBizYhxx bckjBizYhxx = get(map.get("owid").toString());
            MapUtil.easySetByMap(map,bckjBizYhxx);
            bckjBizYhxxes.add(bckjBizYhxx);
        }
        saveOrUpdateAll(bckjBizYhxxes);
    }

    public PageInfo<Map> getXchBaoMingList(Map<String, Object> dataMap) {
        Page<Map> page=new Page<>(MapUtils.getInt(dataMap,"pageNo"),MapUtils.getInt(dataMap,"pageSize"));
        dataMap.put("yhlx",5);
        dataMap.put("exp2",dataMap.get("owid"));
        dataMap.remove("owid");
        page.setList(this.dao.getXchBaoMingList(dataMap));
        return PageUtils.assimblePageInfo(page);
    }
}