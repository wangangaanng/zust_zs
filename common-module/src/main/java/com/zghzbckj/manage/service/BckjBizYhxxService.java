/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;


import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.IdGen;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.manage.dao.BckjBizYhxxDao;
import com.zghzbckj.manage.entity.*;
import com.zghzbckj.util.*;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




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
    @Autowired
    BckjBizStudentExpandService bckjBizStudentExpandService;


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
    public ResponseMessage findPageBckjBizYhxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizYhxx> page = findPage(dataMap, pageNo, pageSize, null);
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
        Map<String, Object> resMap = Maps.newHashMap();
        Map<String, Object> map = this.dao.logIn(datamap);
        if (TextUtils.isEmpty(map)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
        }
        if (!(datamap.get("yhDlmm").toString().equalsIgnoreCase(map.get("yhDlmm").toString()))) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.PasswordError);
        }
        //如果不是为老师或者学生
        if (Integer.parseInt(map.get("olx").toString()) != 0 && Integer.parseInt(map.get("olx").toString()) != 1) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
        }
        //根据学号去查询毕业年份如果已毕业则不能登入
        Map<String, Object> bynfMap = bckjBizSybService.getBynfBySfz(map);
        if (!TextUtils.isEmpty(bynfMap)) {
            if (!TextUtils.isEmpty(bynfMap.get("bynf"))) {
                String year = bynfMap.get("bynf").toString();
                String month = "07";
                String day = "01";
                String dateStr = (year + "-" + month + "-" + day);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date byDate = sdf.parse(dateStr);
                Date currentDate = new Date();
                if (byDate.before(currentDate)) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
                }
            }
        }
        //设置最近登录时间
        this.dao.updateDlsj(map.get("owid").toString());
        resMap.put("olx", map.get("olx"));
        resMap.put("owid", map.get("owid"));
        resMap.put("yhtx", map.get("yhtx"));
        resMap.put("sjh", map.get("sjh"));
        resMap.put("xsxh", map.get("xsxh"));
        resMap.put("xm", map.get("xm"));
        addLog(map);
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
        String psw = TextUtils.MD5(dataMap.get("yhDlmm").toString()).toUpperCase();
        dataMap.remove("psw");
        Map<String, Object> map = this.dao.logIn(dataMap);
        if (TextUtils.isEmpty(map)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
        }
        if (!psw.equalsIgnoreCase(map.get("yhDlmm").toString())) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.PasswordError);
        }
        if (Integer.parseInt(map.get("olx").toString()) != 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.NoAccounctExists);
        }
        BckjBizYhxx bckjBizYhxx = this.dao.getOneByCondition(map);
        BckjBizYhgl bckjBizYhgl = new BckjBizYhgl();
        if (TextUtils.isEmpty(bckjBizYhxx.getUnionid())) {
            bckjBizYhxx.setUnionid(dataMap.get("unionid").toString());
            bckjBizYhgl.setOpenid(dataMap.get("openid").toString());
            bckjBizYhgl.setWxbh(dataMap.get("wxid").toString());
            bckjBizYhgl.setGzsj(new Date());
            if (TextUtils.isEmpty(dataMap.get("nickname"))) {
                bckjBizYhxx.setXm(dataMap.get("nickname").toString());
            }
            if (TextUtils.isEmpty(dataMap.get("gender"))) {
                bckjBizYhxx.setXb(Integer.parseInt(dataMap.get("gender").toString()));
            }
            if (TextUtils.isEmpty(dataMap.get("city"))) {
                bckjBizYhxx.setCity(dataMap.get("city").toString());
            }
            if (TextUtils.isEmpty(dataMap.get("province"))) {
                bckjBizYhxx.setProv(dataMap.get("province").toString());
            }
            if (TextUtils.isEmpty(dataMap.get("country"))) {
                bckjBizYhxx.setArea(dataMap.get("country").toString());
            }
            if (TextUtils.isEmpty(dataMap.get("avatarUrl"))) {
                bckjBizYhxx.setYhtx(dataMap.get("avatarUrl").toString());
            }
        }
        bckjBizYhxx.setDlzhsj(new Date());
        saveOrUpdate(bckjBizYhxx);
        bckjBizYhglService.saveOrUpdate(bckjBizYhgl);
        resMap.put("owid", bckjBizYhxx.getOwid());
        resMap.put("yhtx", bckjBizYhxx.getYhtx());
        resMap.put("sjh", bckjBizYhxx.getSjh());
        return ResponseMessage.sendOK(resMap);
    }

    public BckjBizYhxx getOneByUnionId(String unionid) {
        return this.dao.getOneByUnionId(unionid);
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
     *
     * @param type
     * @param filterModels
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageInfo<Map> getYhxxInfoList(Integer type, List<FilterModel> filterModels, Integer pageSize, Integer pageNo) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
        Page<Map> page = new Page<>(pageNo,pageSize);
        dataMap.put("page", page);
        List<Map> resLists = null;
        //签到
        if (type == 1) {
            String Sum = this.dao.getYhxxQdSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xsxh", "签到总人数：" + Sum);
            resMap.put("readonly", true);
            resLists= this.dao.getYhxxQdInfo(dataMap);
            resLists.add(0, resMap);
        }
        //关注
        if (type == 0) {
            String sum = this.dao.getYhxxGzSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xsxh","关注总人数："+sum);
            resMap.put("readonly",true);
            resLists = this.dao.getYhxxGzInfo(dataMap);
            resLists.add(0,resMap);
        }
        //如果为报名
        if(type==2){
            String sum = this.dao.getYhxxBmSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("xsxh","报名总人数："+sum);
            resMap.put("readonly",true);
            resLists = this.dao.getYhxxBmInfo(dataMap);
            resLists.add(0,resMap);
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
}