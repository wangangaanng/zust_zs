/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.*;


import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.feign.BckjBizYhkzSer;
import com.zghzbckj.manage.dao.BckjBizYhxxDao;
import com.zghzbckj.manage.entity.BckjBizYhgl;
import com.zghzbckj.manage.entity.BckjBizYhkz;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.util.CustomSaveALL;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.PageUtils;

import com.zghzbckj.wechat.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ourway.base.utils.BeanUtil;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;

import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizYhxxDao;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import org.apache.log4j.Logger;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;



/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizYhxxService extends CrudService<BckjBizYhxxDao, BckjBizYhxx> {

	private static final Logger log = Logger.getLogger(BckjBizYhxxService.class);

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
	@Autowired
    BckjBizYhglService bckjBizYhglService;
    @Autowired
    BckjBizYhkzService bckjBizYhkzService;


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
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizYhxx bckjBizYhxxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizYhxx,bckjBizYhxxIndata);
        bckjBizYhxx=bckjBizYhxxIndata;
        }
        saveOrUpdate(bckjBizYhxx);
        return ResponseMessage.sendOK(bckjBizYhxx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizYhxx </p>
        *<ul>
            *<li> @param codes TODO</li>
            *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            *<li>@author D.chen.g </li>
            *<li>@date 2018/9/6 17:14  </li>
            *</ul>
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
     *<p>功能描述: 用户登录 </p >
     *<ul>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/11 </li>
     *</ul>
     */
    @Transactional(readOnly = false ,rollbackFor = Exception.class)
    public ResponseMessage logIn(Map<String, Object> datamap) {
        Map<String,Object> resMap = Maps.newHashMap();
        Map<String, Object> map = this.dao.logIn(datamap);
        if(TextUtils.isEmpty(map)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
        if(!(datamap.get("yhDlmm").toString().equalsIgnoreCase(map.get("yhDlmm").toString()))){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.PasswordError);
        }
        //如果不是为老师或者学生
        if(Integer.parseInt(map.get("olx").toString())!=0&&Integer.parseInt(map.get("olx").toString())!=1){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
        //设置最近登录时间
        this.dao.updateDlsj(map.get("owid").toString());
        resMap.put("olx",map.get("olx"));
        resMap.put("owid",map.get("owid"));
        resMap.put("yhtx",map.get("yhtx"));
        resMap.put("sjh",map.get("sjh"));
        resMap.put("xsxh",map.get("xsxh"));
        return ResponseMessage.sendOK(resMap);
    }



    /**
     *<p>功能描述: 修改密码 </p >
     *<ul>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/11 </li>
     *</ul>
     */
    @Transactional(readOnly = false ,rollbackFor = Exception.class)
    public ResponseMessage modfiyPassword(Map<String, Object> datamap) {
        HashMap<String, Object> map = Maps.newHashMap();
        String newPassword=datamap.get("newPassword").toString();
        String newPasswordAgain=datamap.get("newPasswordAgain").toString();
        if(!newPassword.equals(newPasswordAgain)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NewPasswordNotMatch);
        }
        String oldPsw = TextUtils.MD5(datamap.get("oldPassword").toString()).toUpperCase();
        BckjBizYhxx bckjbizyhxx = this.dao.getOneByCondition(datamap);
       if(TextUtils.isEmpty(bckjbizyhxx)) {
            return  ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
       if(!bckjbizyhxx.getYhDlmm().equals(oldPsw)){
           return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.PasswordError);
       }
        String newPsw = TextUtils.MD5(datamap.get("newPassword").toString()).toUpperCase();
        map.put("owid",datamap.get("owid"));
        map.put("yhDlmm",newPsw);
        this.dao.modfiyPassword(map);
        return  ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    public ResponseMessage getOneByOwid(String owid) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("owid",owid);
        return ResponseMessage.sendOK(this.dao.getOneByCondition(map));
    }

    @Transactional(readOnly = false ,rollbackFor = Exception.class)
    public ResponseMessage appletLogin(Map<String, Object> dataMap) {
        Map<String,Object> resMap = Maps.newHashMap();
        String psw = TextUtils.MD5(dataMap.get("yhDlmm").toString()).toUpperCase();
        dataMap.remove("psw");
        Map<String, Object> map = this.dao.logIn(dataMap);
        if(TextUtils.isEmpty(map)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
        if(!psw.equalsIgnoreCase(map.get("yhDlmm").toString())){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.PasswordError);
        }
        if(Integer.parseInt(map.get("olx").toString())!=0){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
        BckjBizYhxx bckjBizYhxx = this.dao.getOneByCondition(map);
        BckjBizYhgl bckjBizYhgl=new BckjBizYhgl();
       if(TextUtils.isEmpty(bckjBizYhxx.getUnionid())){
           bckjBizYhxx.setUnionid(dataMap.get("unionid").toString());
           bckjBizYhgl.setOpenid(dataMap.get("openid").toString());
           bckjBizYhgl.setWxbh(dataMap.get("wxid").toString());
           bckjBizYhgl.setGzsj(new Date());
           if(TextUtils.isEmpty(dataMap.get("nickname"))){
               bckjBizYhxx.setXm(dataMap.get("nickname").toString());
           }
           if(TextUtils.isEmpty(dataMap.get("gender"))){
               bckjBizYhxx.setXb(Integer.parseInt(dataMap.get("gender").toString()));
           }
           if(TextUtils.isEmpty(dataMap.get("city"))){
               bckjBizYhxx.setCity(dataMap.get("city").toString());
           }
           if(TextUtils.isEmpty(dataMap.get("province"))){
               bckjBizYhxx.setProv(dataMap.get("province").toString());
           }
           if(TextUtils.isEmpty(dataMap.get("country"))){
               bckjBizYhxx.setArea(dataMap.get("country").toString());
           }
           if(TextUtils.isEmpty(dataMap.get("avatarUrl"))){
               bckjBizYhxx.setYhtx(dataMap.get("avatarUrl").toString());
           }
       }
        bckjBizYhxx.setDlzhsj(new Date());
        saveOrUpdate(bckjBizYhxx);
        bckjBizYhglService.saveOrUpdate(bckjBizYhgl);
        resMap.put("owid",bckjBizYhxx.getOwid());
        resMap.put("yhtx",bckjBizYhxx.getYhtx());
        resMap.put("sjh",bckjBizYhxx.getSjh());
        return ResponseMessage.sendOK(resMap);
    }

    public BckjBizYhxx getOneByUnionId(String unionid) {
        return this.dao.getOneByUnionId(unionid);
    }

    /**
     * <p>功能描述:后台录入师生信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/20</li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordInfo(String path) throws ParseException, RepeatException {
        //文件路径
        String filename = path;
        List<BckjBizYhxx> resultYhxx = new ArrayList<>();
        List<BckjBizYhkz> resultYhkz = new ArrayList<>();
        List<String> excelXh = new ArrayList<>();
        List<String> excelSfz = new ArrayList<>();
        List<String> excelSjh = new ArrayList<>();
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        if (list != null) {
            for (int i = 2; i < list.size(); i++) {     //行循环
                HashMap<Object, Object> dataMap = Maps.newHashMap();
                List<String> cellList = list.get(i);
                String xsxh = cellList.get(0);//学生学号/工号/税号
                xsxh = ExcelUtils.stmodifyExcelData(xsxh);
                excelXh.add(xsxh);
                dataMap.put("xsxh", xsxh);
                String xm = cellList.get(1); //姓名
                dataMap.put("xm", xm);
                String sfz = cellList.get(2);//身份证号
                String regex = "\\d{15}(\\d{2}[0-9xX])?";
                if (!sfz.matches(regex)) {
                    return ResponseMessage.sendOK("存在错误的身份证格式录入");
                }
                excelSfz.add(sfz);
                dataMap.put("sfz", sfz);
                String mz = cellList.get(3);   //民族
                dataMap.put("mz", mz);
                String sjh = cellList.get(4);
                sjh = ExcelUtils.stmodifyExcelData(sjh);//手机号
                dataMap.put("sjh", sjh);
                if (dataMap.get("sjh").toString().length() != 11) {
                    return ResponseMessage.sendOK("存在错误的手机格式录入");
                }
                excelSjh.add(sjh);
                String xb = cellList.get(5);//性别
                if (xb.indexOf("男") != -1) {
                    dataMap.put("xb", 1);
                } else if (xb.indexOf("女") != -1) {
                    dataMap.put("xb", 0);
                }
                String csrq = cellList.get(6);//出生日期
                csrq = ExcelUtils.stmodifyExcelData(csrq);//进行变换
                dataMap.put("csrq", ExcelUtils.stringtoDate(csrq));
                String yx = cellList.get(7);//邮箱
                dataMap.put("yx", yx);
                String prov = cellList.get(8);//家庭住址(省)
                dataMap.put("prov", prov);
                String city = cellList.get(9);//家庭住址(市)
                dataMap.put("city", city);
                String area = cellList.get(10);//家庭住址(区,乡镇)
                dataMap.put("area", area);
                String xsxy = cellList.get(11);//所在学院
                dataMap.put("xsxy", xsxy);
                String xszy = cellList.get(12);//所在专业
                dataMap.put("xszy", xszy);
                String xsnj = cellList.get(13);
                xsnj = ExcelUtils.modifyExcelData(xsnj);//所在年级
                dataMap.put("xsnj", xsnj);
                String xsbj = cellList.get(14);
                xsbj = ExcelUtils.modifyExcelData(xsbj);//所在班级
                dataMap.put("xsbj", xsbj);
                String yhDlzh = cellList.get(15);
                yhDlzh = ExcelUtils.stmodifyExcelData(yhDlzh);//登入账号
                dataMap.put("yhDlzh", yhDlzh);
                String yhDlmm = cellList.get(16);
                yhDlmm = ExcelUtils.stmodifyExcelData(yhDlmm);//登入账号
                dataMap.put("lxr", yhDlmm);
                yhDlmm = TextUtils.MD5(yhDlmm);//md5加密
                dataMap.put("yhDlmm", yhDlmm);
                BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
                BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();

                bckjBizYhkz.setOlx(0);  //类型 0 学生 1教师 2 工作人员 3 企业
                bckjBizYhxx.setYhlx(1); //类型 1学生或老师 2企业

                MapUtil.easySetByMap(dataMap, bckjBizYhxx);
                MapUtil.easySetByMap(dataMap, bckjBizYhkz);
                resultYhxx.add(bckjBizYhxx);
                String owid = CustomSaveALL.preInsert(bckjBizYhxx);
                bckjBizYhkz.setYhRefOwid(owid);
                resultYhkz.add(bckjBizYhkz);
            }
        }
        //判断是否存在学号一样的数据
        List<String> xsxhs = bckjBizYhkzService.getXsxhList();
        List<String> sfzs = this.dao.getSfzList();
        List<String> sjhs = this.dao.getSjhList();
        ArrayList<String> operates = new ArrayList();
        operates.addAll(xsxhs);
        operates.addAll(excelXh);
        HashSet xhSet = new HashSet();
        HashSet sfzSet = new HashSet();
        HashSet sjhSet = new HashSet();
        for (String i : operates)
            xhSet.add(i);
        if (xhSet.size() != operates.size()) {
            throw new RepeatException("存在学号重复录入");
        }
        operates.clear();
        operates.addAll(sfzs);
        operates.addAll(excelSfz);
        for (String i : operates)
            sfzSet.add(i);
        if (sfzSet.size() != operates.size()) {
            throw new RepeatException("存在身份证号重复录入");
        }
        operates.clear();
        operates.addAll(sjhs);
        operates.addAll(excelSjh);
        for (String i : operates)
            sjhSet.add(i);
        if (sjhSet.size() != operates.size()) {
            throw new RepeatException("存在手机号重复录入");
        }
        //全部的学生的bcjkbizYhxx信息放入数据库
        saveAll(resultYhxx);
        //全部的学生的bcjkbizYhkz信息放入数据库
        bckjBizYhkzService.saveOrUpdateAll(resultYhkz);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    /**
     * insert 所有的entity
     *
     * @param entitys corder wangangaanng
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveAll(List<BckjBizYhxx> entitys) {
        Iterator var2 = entitys.iterator();
        while (var2.hasNext()) {
            BckjBizYhxx entity = (BckjBizYhxx) var2.next();
            this.dao.insert(entity);
        }
    }

    public ResponseMessage showInfoList(Integer state ,List<FilterModel> filters,Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if(!TextUtils.isEmpty(dataMap.get("sfz"))){
            String regex = "\\d{15}(\\d{2}[0-9xX])?";
            if (!dataMap.get("sfz").toString().matches(regex)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
            }
        }
        Page<Object> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        //state :0是学生 1 是老师
        dataMap.put("olx",state);
        List<Object> lists = this.dao.showStudentInfoList(dataMap);
        page.setList(lists);
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage saveStudentInfo(List<Map<String, Object>> components) {
        try {
            for (Map<String, Object> component : components) {
                //判断手机号和身份证号
                if (!TextUtils.isEmpty(component.get("sfz"))) {
                    String regex = "\\d{15}(\\d{2}[0-9xX])?";
                    if (!component.get("sfz").toString().matches(regex)) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
                    }
                }
                if (!TextUtils.isEmpty(component.get("sjh"))) {
                    if (component.get("sjh").toString().length() != 11) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.SjHError);
                    }
                }
                //如果為刪除的記錄
                if (Integer.parseInt(component.get("updateFlag").toString()) == 2) {
                    delete(JsonUtil.map2Bean(component, BckjBizYhxx.class));
                    if(!TextUtils.isEmpty(component.get("owid"))) {
                        component.put("yhRefOwid", component.get("owid"));
                        bckjBizYhkzService.deletConditionByMap(component);
                    }
                }
                if (Integer.parseInt(component.get("updateFlag").toString()) == 1) {
                    BckjBizYhkz bckjBizYhkz=null;
                    BckjBizYhxx yhxxSjh=null;
                    BckjBizYhxx yhxSfz=null;
                    //判断是否存在一样的学号或工号
                    if(!TextUtils.isEmpty(component.get("xsxh").toString())){
                        bckjBizYhkz=bckjBizYhkzService.getByXsxh(component.get("xsxh").toString());
                    }
                    if(!TextUtils.isEmpty(component.get("sjh").toString())){
                        yhxxSjh=this.dao.findBySjh(component.get("sjh").toString());
                    }
                    if(!TextUtils.isEmpty(component.get("sfz").toString())){
                        yhxSfz=this.dao.findBySfz(component.get("sfz").toString());
                    }
                //如果為更新
                if (component.get("owid") != null) {
                    if (!com.zghzbckj.util.TextUtils.isEmpty(bckjBizYhkz)) {
                        if(!component.get("owid").equals(bckjBizYhkz.getYhRefOwid())){
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在学号重复录入");
                        }
                    }
                    if(!TextUtils.isEmpty(yhxxSjh)){
                        if(!component.get("owid").equals(yhxxSjh.getOwid())){
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在手机号重复录入");
                        }
                    }
                    if(!TextUtils.isEmpty(yhxSfz)){
                        if(!component.get("owid").equals(yhxSfz.getOwid())){
                            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在身份证重复录入");
                        }
                    }
                    component.put("yhDlmm", com.zghzbckj.util.TextUtils.MD5(component.get("lxr").toString()));
                    saveOrUpdate(JsonUtil.map2Bean(component, BckjBizYhxx.class));
                    component.put("yhOwid", component.get("owid"));
                    bckjBizYhkzService.updateBycondition(component);
                    //否则为新建
                } else if (component.get("owid") == null) {
                    if (!com.zghzbckj.util.TextUtils.isEmpty(bckjBizYhkz)) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, "存在学号重复录入");
                    }
                    if(!TextUtils.isEmpty(yhxxSjh)){
                        return ResponseMessage.sendError(ResponseMessage.FAIL, "存在手机号重复录入");
                    }
                    if(!TextUtils.isEmpty(yhxSfz)){
                        return ResponseMessage.sendError(ResponseMessage.FAIL, "存在身份证重复录入");
                    }
                    component.put("yhlx", 1);
                    component.put("olx", 0);
                    component.put("yhDlmm", com.zghzbckj.util.TextUtils.MD5(component.get("lxr").toString()));
                    BckjBizYhxx bckjBizYhxx1 = JsonUtil.map2Bean(component, BckjBizYhxx.class);
                    String owid = CustomSaveALL.preInsert(bckjBizYhxx1);
                    component.put("yhRefOwid", owid);
                    this.dao.insert(bckjBizYhxx1);
                    bckjBizYhkzService.saveOrUpdate(JsonUtil.map2Bean(component, BckjBizYhkz.class));
                }
                }
            }
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        }
        catch (Exception e){
            log.error(CommonConstant.ERROR_MESSAGE,e);
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
        }
    }
}