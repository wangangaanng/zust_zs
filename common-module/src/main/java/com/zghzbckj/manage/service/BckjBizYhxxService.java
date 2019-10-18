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

import com.zghzbckj.wechat.utils.MD5Util;
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
    BckjBizStudentinfoService bckjBizStudentinfoService;
    @Autowired
    BckjBizSybService bckjBizSybService;
    @Autowired
    BckjBizJypuchongService bckjBizJypuchongService;
    @Autowired
    BckjBizJyschemeService bckjBizJyschemeService;
    @Autowired
    private BckjBizUserlogService bckjBizUserlogService;


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
        //根据学号去查询毕业年份
        Map<String, Object> xsxhMap = bckjBizSybService.getBynfByXsxh(map);
        if (!TextUtils.isEmpty(xsxhMap)) {
            if (!TextUtils.isEmpty(xsxhMap.get("bynf"))) {
                String year = xsxhMap.get("bynf").toString();
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

    public PageInfo<Object> showInfoList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Object> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        //state :0是学生 1 是老师
        dataMap.put("olx", 0);
        List<Object> lists = null;
        lists = this.dao.showStudentInfoList(dataMap);
        page.setList(lists);
        return PageUtils.assimblePageInfo(page);
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insertInfo(BckjBizYhxx bckjBizYhxx) {
        this.dao.insert(bckjBizYhxx);
    }

    /**
     * 新建学生信息
     *
     * @param dataMap
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage insertssInfo(Map<String, Object> dataMap) throws Exception {
        BckjBizStudentinfo bckjBizStudentinfo = new BckjBizStudentinfo();
        BckjBizJypuchong bckjBizJypuchong = new BckjBizJypuchong();
        BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
        BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
        BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
        BckjBizSyb bckjBizSyb = new BckjBizSyb();
        dataMap.put("xh", dataMap.get("xsxh"));
        dataMap.put("xsxm", dataMap.get("xm"));
        dataMap.put("yhRefOwid", dataMap.get("owid"));
        BckjBizYhkz bckjBizYhkz1 = bckjBizYhkzService.getByXsxh(dataMap.get("xsxh").toString());

        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        MapUtil.easySetByMap(dataMap, bckjBizStudentinfo);
        MapUtil.easySetByMap(dataMap, bckjBizSyb);
        MapUtil.easySetByMap(dataMap, bckjBizYhkz);
        MapUtil.easySetByMap(dataMap, bckjBizJypuchong);
        MapUtil.easySetByMap(dataMap, bckjBizJyscheme);

        if (!TextUtils.isEmpty(bckjBizYhxx.getSfz())) {
            String regex = "\\d{15}(\\d{2}[0-9xX])?";
            if (!bckjBizYhxx.getSfz().matches(regex)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
            }
        }
        if (!TextUtils.isEmpty(bckjBizYhxx.getSjh())) {
            if (bckjBizYhxx.getSjh().length() != 11) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.SjHError);
            }
        }

        //如果為更新
        if (bckjBizYhxx.getOwid() != null) {
            if (!com.zghzbckj.util.TextUtils.isEmpty(bckjBizYhkz1)) {
                if (!(bckjBizYhkz1.getYhRefOwid().equals(bckjBizYhxx.getOwid()))) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "此学号已在库内");
                }
            }
            bckjBizJyschemeService.updateXsxhByHyOwid(bckjBizJyscheme);
            bckjBizJypuchongService.updateXsxhByHyOwid(bckjBizJypuchong);
            //更改后的登入账号
            bckjBizYhxx.setYhDlzh(bckjBizYhkz.getXsxh());
            this.dao.updateByXsxh(bckjBizYhxx);
            bckjBizYhkzService.updateByXsxh(bckjBizYhkz);
            bckjBizStudentinfoService.updateByXsxh(bckjBizStudentinfo);
            bckjBizSybService.updateByXsxh(bckjBizSyb);

        }
        //否则为新建
        if (bckjBizYhxx.getOwid() == null) {
            if (!TextUtils.isEmpty(bckjBizYhkz1)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "学号已在库内");
            }
            String uuid = IdGen.uuid();
            bckjBizYhxx.setOwid(uuid);
            bckjBizYhkz.setYhRefOwid(uuid);
            bckjBizStudentinfo.setYhRefOwid(uuid);
            bckjBizSyb.setYhRefOwid(uuid);
            bckjBizJypuchong.setYhRefOwid(uuid);
            bckjBizJyscheme.setYhRefOwid(uuid);
            //非企业
            bckjBizYhxx.setYhlx(1);
            bckjBizYhxx.setYhDlzh(bckjBizYhkz.getXsxh());
            String dlmm = bckjBizYhxx.getSfz().substring(bckjBizYhxx.getSfz().length() - 6);
            dlmm = MD5Util.MD5Encode(dlmm, "UTF-8");
            bckjBizYhxx.setYhDlmm(dlmm);
            bckjBizYhxx.setCreatetime(new Date());
            this.dao.insert(bckjBizYhxx);
            //学生
            bckjBizYhkz.setOlx(0);
            bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
            bckjBizStudentinfoService.saveOrUpdate(bckjBizStudentinfo);
            bckjBizSyb.setExp1("1");
            bckjBizSybService.saveOrUpdate(bckjBizSyb);
            bckjBizJypuchongService.saveOrUpdate(bckjBizJypuchong);
            bckjBizJyscheme.setExp1("1");
            bckjBizJyschemeService.saveOrUpdate(bckjBizJyscheme);
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    public Map<String, Object> getStudentOne(Map<String, Object> dataMap) {
        //学生的olx为0
        dataMap.put("olx", 0);
        return this.dao.showStudentInfo(dataMap);
    }

    /**
     * <p>功能描述:后台录入招生投档信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/11</li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordStudentInfo(String path) throws ParseException, RepeatException {
        //文件路径
        String filename = path;
        List<String> excelXh = new ArrayList<>();
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        HashMap<Object, Object> sybMap = Maps.newHashMap();
        HashMap<Object, Object> yhxxMap = Maps.newHashMap();
        HashMap<Object, Object> yhkzMap = Maps.newHashMap();
        HashMap<Object, Object> studentInfoMap = Maps.newHashMap();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String xsxh = cellList.get(0);//学生学号/工号/税号
                xsxh = ExcelUtils.stmodifyExcelData(xsxh);
                //如果学生学号为空则跳出
                if (TextUtils.isEmpty(xsxh)) {
                    break;
                }
                excelXh.add(xsxh);
                yhkzMap.put("xsxh", xsxh);
                sybMap.put("xh", xsxh);
                studentInfoMap.put("xsxh", xsxh);
                BckjBizYhkz byXsxh = bckjBizYhkzService.getByXsxh(xsxh);
                //如果数据库有此学号就先删除这条信息
                if (!TextUtils.isEmpty(byXsxh)) {
                    dao.delete(byXsxh.getYhRefOwid());
                    bckjBizYhkzService.deleteByXsxh(xsxh);
                    bckjBizSybService.deleteByXsxh(xsxh);
                    bckjBizStudentinfoService.deleteByXsxh(xsxh);
                    bckjBizJyschemeService.deleteByXsxh(xsxh);
                    bckjBizJypuchongService.deleteByXsxh(xsxh);
                }
                String ksh = cellList.get(1); //考生号
                ksh = ExcelUtils.stmodifyExcelData(ksh);
                sybMap.put("ksh", ksh);
                String xm = cellList.get(2); //姓名
                yhxxMap.put("xm", xm);
                yhkzMap.put("xsxm", xm);
                String xb = cellList.get(3); //姓名
                if (xb.equals("男")) {
                    yhxxMap.put("xb", 1);
                } else {
                    yhxxMap.put("xb", 2);
                }
                String xszy = cellList.get(4); //专业
                yhkzMap.put("xszy", xszy);
                String xsxy = cellList.get(5); //学院
                yhkzMap.put("xsxy", xsxy);
                String zydhOne = cellList.get(6); //专业代号1
                studentInfoMap.put("zydhOne", zydhOne);
                String zydhTwo = cellList.get(7); //专业代号2
                studentInfoMap.put("zydhTwo", zydhTwo);
                String zydhThree = cellList.get(8); //专业代号3
                studentInfoMap.put("zydhThree", zydhThree);
                String zydhFour = cellList.get(9); //专业代号4
                studentInfoMap.put("zydhFour", zydhFour);
                String zydhFive = cellList.get(10); //专业代号5
                studentInfoMap.put("zydhFive", zydhFive);
                String zydhSix = cellList.get(11); //专业代号6
                studentInfoMap.put("zydhSix", zydhSix);
                String csrq = cellList.get(12); //出生年月
                Date date = ExcelUtils.stringtoDate(csrq);
                yhxxMap.put("csrq", date);
                String zzmm = cellList.get(13); //政治面貌
                sybMap.put("zzmm", bckjBizJyschemeService.getDicVal(50008, zzmm));
                String mz = cellList.get(14); //民族
                yhxxMap.put("mz", bckjBizJyschemeService.getDicVal(50009, mz));
                String kslb = cellList.get(15); //考生类别
                studentInfoMap.put("kslb", kslb);
                String bylb = cellList.get(16); //毕业类别
                studentInfoMap.put("bylb", bylb);
                String zxdm = cellList.get(17); //中学代码
                studentInfoMap.put("zxdm", zxdm);
                String zxmc = cellList.get(18); //中学名称
                studentInfoMap.put("zxmc", zxmc);
                String syddm = cellList.get(19); //生源地代码
                studentInfoMap.put("syddm", syddm);
                String syd = cellList.get(20); //生源地
                sybMap.put("syd", bckjBizJyschemeService.getDicVal(50005, syd));
                String sfz = cellList.get(21); //身份证号
                sfz = ExcelUtils.stmodifyExcelData(sfz);
                String regex = "\\d{15}(\\d{2}[0-9xX])?";
                if (!sfz.matches(regex)) {
                    return ResponseMessage.sendOK("存在错误的身份证格式录入");
                }
                yhxxMap.put("sfz", sfz);
                String jtdz = cellList.get(22); //家庭地址
                sybMap.put("jtdz", jtdz);
                String jtyb = cellList.get(23); //邮政编码
                sybMap.put("jtyb", jtyb);
                String sjh = cellList.get(24); //联系手机
                yhxxMap.put("sjh", sjh);
                String jtdh = cellList.get(25); //联系电话
                sybMap.put("jtdh", jtdh);
                String kstc = cellList.get(26); //考生特长
                studentInfoMap.put("kstc", kstc);
                String hjqk = cellList.get(27); //获奖情况
                studentInfoMap.put("hjqk", hjqk);
                String sjr = cellList.get(28); //收件人
                studentInfoMap.put("sjr", sjr);
                String tdcj = cellList.get(29); //投档成绩
                studentInfoMap.put("tdcj", tdcj);
                String tdzy = cellList.get(30); //投档志愿
                studentInfoMap.put("tdzy", tdzy);
                String xkkm = cellList.get(31); //选考科目
                studentInfoMap.put("xkkm", xkkm);
                String yw = cellList.get(32); //语文
                studentInfoMap.put("yw", yw);
                String sx = cellList.get(33); //数学
                studentInfoMap.put("sx", sx);
                String wy = cellList.get(34); //外语
                studentInfoMap.put("wy", wy);
                String wl = cellList.get(35); //物理
                studentInfoMap.put("wl", wl);
                String hx = cellList.get(36); //化学
                studentInfoMap.put("hx", hx);
                String sw = cellList.get(37); //生物
                studentInfoMap.put("sw", sw);
                String zz = cellList.get(38); //政治
                studentInfoMap.put("zz", zz);
                String ls = cellList.get(39); //历史
                studentInfoMap.put("ls", ls);
                String dl = cellList.get(40); //地理
                studentInfoMap.put("dl", dl);
                String js = cellList.get(41); //技术
                studentInfoMap.put("js", js);
                String zhcj = cellList.get(42); //综合成绩
                studentInfoMap.put("zhcj", zhcj);
                String tkmscj = cellList.get(43); //统考美术成绩
                studentInfoMap.put("tkmscj", tkmscj);
                String tkbycj = cellList.get(44); //统考表演成绩
                studentInfoMap.put("tkbycj", tkbycj);
                String tkszcj = cellList.get(45); //统考摄制成绩
                studentInfoMap.put("tkszcj", tkszcj);
                String xz = cellList.get(46); //学制
                sybMap.put("xz", xz);
                String km = cellList.get(47); //科类
                studentInfoMap.put("km", km);
                String pc = cellList.get(48); //批次
                studentInfoMap.put("pc", pc);
                String prov = cellList.get(49); //省份
                yhxxMap.put("prov", prov);
                String memo = cellList.get(50); //备注
                studentInfoMap.put("memo", memo);
                String sg = cellList.get(51); //身高
                studentInfoMap.put("sg", sg);
                String tz = cellList.get(52); //体重
                studentInfoMap.put("tz", tz);
                String jf = cellList.get(53); //加分
                studentInfoMap.put("jf", jf);

                BckjBizStudentinfo bckjBizStudentinfo = new BckjBizStudentinfo();
                BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
                BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
                BckjBizSyb bckjBizSyb = new BckjBizSyb();
                BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
                BckjBizJypuchong bckjBizJypuchong = new BckjBizJypuchong();

                MapUtil.easySetByMap(studentInfoMap, bckjBizStudentinfo);
                MapUtil.easySetByMap(yhxxMap, bckjBizYhxx);
                MapUtil.easySetByMap(yhkzMap, bckjBizYhkz);
                MapUtil.easySetByMap(sybMap, bckjBizSyb);
                MapUtil.easySetByMap(yhkzMap, bckjBizJypuchong);
                MapUtil.easySetByMap(yhkzMap, bckjBizJyscheme);

                String owid = IdGen.uuid();
                bckjBizYhxx.setOwid(owid);
                bckjBizYhkz.setYhRefOwid(owid);
                bckjBizStudentinfo.setYhRefOwid(owid);
                bckjBizSyb.setYhRefOwid(owid);
                bckjBizJypuchong.setYhRefOwid(owid);
                bckjBizJyscheme.setYhRefOwid(owid);
                //非企业
                bckjBizYhxx.setYhlx(1);
                bckjBizYhxx.setYhDlzh(xsxh);
                String dlmm = sfz.substring(sfz.length() - 6);
                bckjBizYhxx.setYhDlmm(dlmm);
                bckjBizYhxx.setCreatetime(new Date());
                this.dao.insert(bckjBizYhxx);
                //学生
                bckjBizYhkz.setOlx(0);
                bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
                bckjBizStudentinfoService.saveOrUpdate(bckjBizStudentinfo);
                //学生为未编辑状态
                bckjBizSyb.setExp2("1");
                bckjBizSybService.saveOrUpdate(bckjBizSyb);
                //学生为未编辑状态
                bckjBizJyscheme.setExp2("1");
                bckjBizJyscheme.setXxmc(CommonConstant.XXMC);
                bckjBizJyscheme.setYhRefOwid(bckjBizYhxx.getOwid());
                bckjBizJyschemeService.saveOrUpdate(bckjBizJyscheme);

                bckjBizJypuchong.setYhRefOwid(bckjBizYhxx.getOwid());
                bckjBizJypuchongService.saveOrUpdate(bckjBizJypuchong);

            }
            //判断是否存在学号一样的数据
            ArrayList<String> operates = new ArrayList();
            operates.addAll(excelXh);
            HashSet xhSet = new HashSet();
            int count = 1;
            for (String i : operates) {
                xhSet.add(i);
                if (xhSet.size() != count) {
                    throw new RepeatException("excel表中存在学号" + i + "重复录入,请确保数据正确,再录入");
                }
                count++;
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteAllList(Map<String, Object> dataMap) {
        this.dao.deleteAllList(dataMap);
    }

    /**
     * 后台录入就业方案
     *
     * @param bckjBizYhxx
     */
    public void updateInfo(BckjBizYhxx bckjBizYhxx) {
        this.dao.updateInfo(bckjBizYhxx);
    }


    /**
     * 后台删除gridlist
     *
     * @param owidcodes
     * @param xsxhcodes
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public List deleteInfoList(List<String> owidcodes, List<String> xsxhcodes) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        int count = 0;
        for (String owid : owidcodes) {
            dao.delete(owid);
            Map<String, Object> params = Maps.newHashMap();
            params.put("owid", owid);
            bckjBizYhkzService.deleteByXsxh(xsxhcodes.get(count));
            bckjBizSybService.deleteByXsxh(xsxhcodes.get(count));
            bckjBizStudentinfoService.deleteByXsxh(xsxhcodes.get(count));
            bckjBizJypuchongService.deleteByXsxh(xsxhcodes.get(count));
            bckjBizJyschemeService.deleteByXsxh(xsxhcodes.get(count));
            count++;
            objs.add(params);
        }
        return objs;
    }


    public void updateJyscheme(BckjBizYhxx bckjBizYhxx) {
        this.dao.updateJyscheme(bckjBizYhxx);
    }

    public void updateSyscheme(BckjBizYhxx bckjBizYhxx) {
        this.dao.updateSyscheme(bckjBizYhxx);
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
     * @param filterModels
     * @param type
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageInfo<Map> getYhxxInfoList(Integer type, List<FilterModel> filterModels, Integer pageSize, Integer pageNo) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filterModels);
        Page<Map> page = new Page<>(pageSize, pageNo);
        dataMap.put("page", page);
        List<Map> resLists = null;
        //签到
        if (type == 1) {
            String Sum = this.dao.getYhxxQdSum(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("sjh", "签到总人数：" + Sum);
            resMap.put("readonly", true);
            resLists= this.dao.getYhxxQdInfo(dataMap);
            resLists.add(0, resMap);
        }
        //关注
        if (type == 0) {
            resLists = this.dao.getYhxxGzInfo(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("sjh","关注总人数："+resLists.get(0).get("num"));
            resMap.put("readonly",true);
            resLists = this.dao.getYhxxGzInfo(dataMap);
            resLists.add(0,resMap);
        }
        //如果为报名
        if(type==2){
            resLists = this.dao.getYhxxBmInfo(dataMap);
            Map<String, Object> resMap = Maps.newHashMap();
            resMap.put("sjh","报名总人数："+resLists.get(0).get("num"));
            resMap.put("readonly",true);
            resLists = this.dao.getYhxxGzInfo(dataMap);
            resLists.add(0,resMap);
        }
        page.setList(resLists);
        return PageUtils.assimblePageInfo(page);
    }

    /**
     *后台获得单个学生关注的信息
     * @param dataMap
     * @param type 0:关注 1：签到 2：报名
     * @return
     */
     public Map<String,Object> getOneYhxxInfo(Map<String,Object> dataMap,Integer type) {
         dataMap.put("type",type);
         Map<String, Object> resMap = Maps.newHashMap();
         if(type==1||type==2){
             resMap=this.dao.getOneYhxxGzOrQdInfo(dataMap);
         }
         else if(type==3){
             resMap=this.dao.getOneYhxxBmInfo(dataMap);
         }
         return resMap;
     }



}