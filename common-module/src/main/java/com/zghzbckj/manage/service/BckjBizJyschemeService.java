/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.IdGen;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.manage.dao.BckjBizJyschemeDao;
import com.zghzbckj.manage.entity.*;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.PageUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * 就业方案Service
 *
 * @author wangangaanng
 * @version 2019-09-30
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJyschemeService extends CrudService<BckjBizJyschemeDao, BckjBizJyscheme> {

    private static final Logger log = Logger.getLogger(BckjBizJyschemeService.class);

    @Override
    public BckjBizJyscheme get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizJyscheme> findList(BckjBizJyscheme bckjBizJyscheme) {
        return super.findList(bckjBizJyscheme);
    }

    @Override
    public PageInfo<BckjBizJyscheme> findPage(Page<BckjBizJyscheme> page, BckjBizJyscheme bckjBizJyscheme) {
        return super.findPage(page, bckjBizJyscheme);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizJyscheme bckjBizJyscheme) {
        super.saveOrUpdate(bckjBizJyscheme);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizJyscheme bckjBizJyscheme) {
        super.delete(bckjBizJyscheme);
    }

    @Autowired
    BckjBizJypuchongService bckjBizJypuchongService;
    @Autowired
    BckjBizSybService bckjBizSybService;
    @Autowired
    BckjBizYhkzService bckjBizYhkzService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    BckjBizStudentinfoService bckjBizStudentinfoService;


    /**
     * <p>方法:findPagebckjBizJyscheme TODO后台BckjBizJyscheme分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizJyscheme(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizJyscheme> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizJyscheme TODO保存BckjBizJyscheme信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizJyscheme(Map<String, Object> mapData) throws ParseException {
        //先从数据库查
        BckjBizJyscheme bckjBizJyscheme = this.dao.getJyschemeByMap(mapData);
        if (!TextUtils.isEmpty(mapData.get("bdkssj"))) {
            mapData.put("bdkssj", ExcelUtils.stringtoDatec(mapData.get("bdkssj").toString()));
        }
        if (!TextUtils.isEmpty(mapData.get("bdjssj"))) {
            mapData.put("bdjssj", ExcelUtils.stringtoDatec(mapData.get("bdjssj").toString()));
        }
        //map转变为类
        BckjBizJyscheme bckjBizJyscheme1 = new BckjBizJyscheme();
        MapUtil.easySetByMap(mapData, bckjBizJyscheme1);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            if (null != bckjBizJyscheme && !(bckjBizJyscheme1.getOwid().equals(bckjBizJyscheme.getOwid()))) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "已存在此学号学生");
            }
            BckjBizJyscheme bckjBizJyschemeIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJyscheme, bckjBizJyschemeIndata);
            bckjBizJyscheme = bckjBizJyschemeIndata;
        } else {
            if (null != bckjBizJyscheme) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "已存在此学号学生");
            }
        }
        saveOrUpdate(bckjBizJyscheme1);
        return ResponseMessage.sendOK(bckjBizJyscheme);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizJyscheme </p>
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
            BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
            bckjBizJyscheme.setOwid(owid);
            this.dao.delete(bckjBizJyscheme);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public ResponseMessage saveJyFaInfo(Map<String, Object> dataMap) {
        if (!TextUtils.isEmpty(dataMap.get("sfz"))) {
            String regex = "\\d{15}(\\d{2}[0-9xX])?";
            if (!dataMap.get("sfz").toString().matches(regex)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
            }
        }
        BckjBizJyscheme bckjBizJyscheme1 = new BckjBizJyscheme();
        MapUtil.easySetByMap(dataMap, bckjBizJyscheme1);
        if (TextUtils.isEmpty(bckjBizJyscheme1)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        saveOrUpdate(bckjBizJyscheme1);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public ResponseMessage getJyBaseInfo(Map<String, Object> dataMap) {
        String xsxh = this.dao.getXsxhByOwid(dataMap.get("owid").toString());
        HashMap<String, Object> sendMap = Maps.newHashMap();
        sendMap.put("xsxh", xsxh);
        BckjBizJyscheme bckjBizJyscheme = this.dao.getJyschemeByMap(sendMap);
        if (TextUtils.isEmpty(bckjBizJyscheme)) {
            Map<String, Object> map = this.dao.getJyBaseInfo(dataMap);
            return ResponseMessage.sendOK(map);
        }
        return ResponseMessage.sendOK(bckjBizJyscheme);
    }

    /**
     * 字典表导入
     *
     * @param path
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Object recordInfo(String path) {
        //文件路径
        String filename = path;
        /*List<String> excelSjh = new ArrayList<>();*/
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                String val1 = cellList.get(0);
                String val2 = cellList.get(1);
                Integer type = 50005;
                int owid1 = new Random().nextInt(999999999);
                int dicRefOwid = owid1;
                String owid2 = IdGen.uuid();
                HashMap<Object, Object> sendMap1 = Maps.newHashMap();
                sendMap1.put("owid", owid1);
                sendMap1.put("type1", type);
                HashMap<Object, Object> sendMap2 = Maps.newHashMap();
                sendMap2.put("val1", val1);
                sendMap2.put("val2", val2);
                sendMap2.put("dicRefOwid", dicRefOwid);
                sendMap2.put("owid", owid2);
                this.dao.insertDicVal1(sendMap1);
                this.dao.insertDicVal2(sendMap2);
            }
        }
        return ResponseMessage.sendOK("");
    }

    /**
     * 后台录入学生就业方案表
     *
     * @param path
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordJyInfo(String path) {
        String error = "";
        //文件路径
        String filename = path;
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        HashMap<Object, Object> sybMap = Maps.newHashMap();
        HashMap<Object, Object> yhxxMap = Maps.newHashMap();
        HashMap<Object, Object> yhkzMap = Maps.newHashMap();
        HashMap<Object, Object> jySchemeMap = Maps.newHashMap();
        HashMap<Object, Object> jyPcMap = Maps.newHashMap();
        HashMap<Object, Object> studentInfoMap = Maps.newHashMap();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //就业方案录入
                List<String> cellList = list.get(i);//行循环
                String xsxh = cellList.get(0);//学生学号/工号/税号
                yhkzMap.put("xsxh", xsxh);
                sybMap.put("xh", xsxh);
                jyPcMap.put("xsxh", xsxh);
                jySchemeMap.put("xsxh", xsxh);
                studentInfoMap.put("xsxh",xsxh);
                String xm = cellList.get(1); //姓名
                yhxxMap.put("xm", xm);
                yhkzMap.put("xsxm", xm);
                //如果学生学号为空则跳出
                if(TextUtils.isEmpty(xsxh)){
                    break;
                }
                String sfz = cellList.get(2); //身份证号
                sfz = ExcelUtils.stmodifyExcelData(sfz);
                String regex = "\\d{15}(\\d{2}[0-9xX])?";
                if (!sfz.matches(regex)) {
                    return ResponseMessage.sendOK("存在错误的身份证格式录入");
                }
                yhxxMap.put("sfz", sfz);
                String xb = cellList.get(3); //性别
                if (xb.equals("男")) {
                    yhxxMap.put("xb", 1);
                } else {
                    yhxxMap.put("xb", 2);
                }
                String mz = cellList.get(4); //民族
                yhxxMap.put("mz", getDicVal(50009,mz));
                String xxmc = cellList.get(5); //学校名称
                jySchemeMap.put("xxmc", xxmc);
                String xsxy = cellList.get(6); //院系
                yhkzMap.put("xsxy", xsxy);
                String xszy = cellList.get(7); //专业
                yhkzMap.put("xszy", xszy);
                String xsbj = cellList.get(8); //班级
                yhkzMap.put("xsbj", xsbj);
                String bynf = cellList.get(9); //毕业年份
                sybMap.put("bynf", bynf);
                String byxl = cellList.get(10); //毕业学历
                jySchemeMap.put("byxl", byxl);
                String xz = cellList.get(11); //学制
                sybMap.put("xz", xz);
                String sfsf = cellList.get(12); //是否师范
                if (sfsf.equals("是")) {
                    jyPcMap.put("sfsf", 1);
                } else {
                    jyPcMap.put("sfsf", 2);
                }
                String sfzz = cellList.get(13); //是否在职
                if (sfzz.equals("是")) {
                    jyPcMap.put("sfzz", 1);
                } else {
                    jyPcMap.put("sfzz", 2);
                }
                String sfdlxy = cellList.get(14); //是否独立学院
                if (sfdlxy.equals("是")) {
                    jyPcMap.put("sfdlxy", 1);
                } else {
                    jyPcMap.put("sfdlxy", 2);
                }
                String syddm = cellList.get(15); //生源地代码
                studentInfoMap.put("syddm",syddm);
                String syd = cellList.get(16); //生源地
                sybMap.put("syd", getDicVal(50005,syd));
                String sjh = cellList.get(17); //联系手机
                yhxxMap.put("sjh", sjh);
                String jtdh = cellList.get(18); //联系方式(固定电话)
                sybMap.put("jtdh", jtdh);
                String qqhm = cellList.get(19); //QQ号码
                sybMap.put("qqhm", qqhm);
                String yx = cellList.get(20); //Email
                yhxxMap.put("yx", yx);
                String byqx = cellList.get(21); //毕业去向
                jySchemeMap.put("byqx",dao.getDicVal(50001,byqx));
                String jyqdbz = cellList.get(22); //就业标志
                jyPcMap.put("jyqdbz", jyqdbz);
                String yrdwxz = cellList.get(23); //用人单位性质
                jySchemeMap.put("yrdwxz", dao.getDicVal(50002,yrdwxz));
                String gzzwlb = cellList.get(24); //工作职位类别
                jySchemeMap.put("gzzwlbmc", dao.getDicVal(50004,gzzwlb));
                String yrdwdm = cellList.get(25); //用人单位代码
                jySchemeMap.put("yrdwdm", yrdwdm);
                String yrdwmc = cellList.get(26); //用人单位名称
                jySchemeMap.put("yrdwmc", yrdwmc);
                String zgdwdm = cellList.get(27); //主管单位代码
                jyPcMap.put("zgdwdm", zgdwdm);
                String zgdwmc = cellList.get(28); //主管单位名称
                jyPcMap.put("zgdwmc", zgdwmc);
                String yrdwlsmc = cellList.get(29); //用人单位隶属部门
                jyPcMap.put("yrdwlsmc", yrdwlsmc);
                String dwszdmc = cellList.get(30); //用人单位所在地址
                jySchemeMap.put("dwszdmc", dao.getDicVal(50005,dwszdmc));
                String dwszddm = cellList.get(31); //报到地区代码
                jyPcMap.put("dwszddm", dwszddm);
                String bdzszdmc = cellList.get(32); //报到地区
                String bdzszdmc1=bdzszdmc;
                jySchemeMap.put("bdzszdmc", dao.getDicVal(50005,bdzszdmc));
                String bdzqflbmc = cellList.get(33); //报到证签发类别
                jySchemeMap.put("bdzqflbmc",dao.getDicVal(50007,bdzqflbmc) );
                String bdkssj = cellList.get(34); //报到开始时间
                jySchemeMap.put("bdkssj", bdkssj);
                String bdjssj = cellList.get(35); //报到结束时间
                jySchemeMap.put("bdjssj", bdjssj);
                String bdzbh = cellList.get(36); //报到证号
                jySchemeMap.put("bdzbh", bdzbh);
                String xjcqk = cellList.get(37); //下基层情况
                jyPcMap.put("xjcqk", xjcqk);
                String datdxxdz = cellList.get(38); //档案投递地址
                jySchemeMap.put("datdxxdz", datdxxdz);
                String datddw = cellList.get(39); //档案投递单位
                jySchemeMap.put("datddw", datddw);
                String hkqydz = cellList.get(40); //户口迁移地址
                jySchemeMap.put("hkqydz", hkqydz);
                String dwlxr = cellList.get(41); //单位联系人
                jySchemeMap.put("dwlxr", dwlxr);
                String dwdh = cellList.get(42); //单位联系电话
                jySchemeMap.put("dwdh", dwdh);
                String sfzydk = cellList.get(43); //专业是否对口
                if (sfzydk.equals("是")) {
                    jySchemeMap.put("sfzydk", 1);
                } else {
                    jySchemeMap.put("sfzydk", 2);
                }
                String dwlbmc = cellList.get(44);//就业行业
                jySchemeMap.put("dwlbmc", dao.getDicVal(50003,dwlbmc));
                String bdzlsh = cellList.get(45);//报到证流水号
                jySchemeMap.put("bdzlsh", bdzlsh);
                String bzone = cellList.get(46);
                jySchemeMap.put("bzone", bzone);
                String bztwo = cellList.get(47);
                jySchemeMap.put("bztwo", bztwo);
                String bzthree = cellList.get(48);
                jySchemeMap.put("bzthree", bzthree);
                String sfdydwbdz=cellList.get(49);
                if(sfdydwbdz.equals("是")){
                    jySchemeMap.put("sfdydwbdz",1);
                }else {
                    jySchemeMap.put("sfdydwbdz",2);
                }
                String bdzbz=cellList.get(50);
                jySchemeMap.put("bdzbz",bdzbz);
                BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
                BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
                BckjBizSyb bckjBizSyb = new BckjBizSyb();
                BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
                BckjBizJypuchong bckjBizJypuchong = new BckjBizJypuchong();
                BckjBizStudentinfo bckjBizStudentinfo = new BckjBizStudentinfo();
                MapUtil.easySetByMap(yhxxMap, bckjBizYhxx);
                MapUtil.easySetByMap(yhkzMap, bckjBizYhkz);
                MapUtil.easySetByMap(sybMap, bckjBizSyb);
                MapUtil.easySetByMap(jyPcMap, bckjBizJypuchong);
                MapUtil.easySetByMap(jySchemeMap, bckjBizJyscheme);
                MapUtil.easySetByMap(studentInfoMap,bckjBizStudentinfo);
                //如果学生不存在
                BckjBizYhkz byXsxh = bckjBizYhkzService.getByXsxh(xsxh);
                if (TextUtils.isEmpty(byXsxh)) {
                    error = error + "," + xsxh;
                    continue;
                }

                bckjBizYhxx.setOwid(byXsxh.getYhRefOwid());
                //更新yhxx
                bckjBizYhxxService.updateJyscheme(bckjBizYhxx);
                //更新yhkz
                bckjBizYhkz.setYhRefOwid(bckjBizYhxx.getOwid());
                bckjBizYhkzService.updateJyscheme(bckjBizYhkz);
                //更新syb
                bckjBizSyb.setYhRefOwid(bckjBizYhxx.getOwid());
                bckjBizSybService.updateJyscheme(bckjBizSyb);
                //更新studentinfo
                bckjBizStudentinfo.setYhRefOwid(bckjBizYhxx.getOwid());
                bckjBizStudentinfoService.updataInfo(bckjBizStudentinfo);

                bckjBizJypuchong.setYhRefOwid(bckjBizYhxx.getOwid());
                bckjBizJypuchongService.updateJyscheme(bckjBizJypuchong);
                bckjBizJyscheme.setExp1(recordLx(bdzszdmc1));
                bckjBizJyscheme.setYhRefOwid(bckjBizYhxx.getOwid());
                this.dao.updateJyscheme(bckjBizJyscheme);
            }
            if (!TextUtils.isEmpty(error)) {
                error = "生源信息中不存在以下的学号,请重新录入:" + error;
                return ResponseMessage.sendOK(error);
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    /**
     * 后台显示方案就业方案列表
     *
     * @param filters
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Object> showInfoList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Object> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        List<Object> lists = null;
        lists = this.dao.showJyInfoList(dataMap);
        page.setList(lists);
        return PageUtils.assimblePageInfo(page);
    }

    /**
     * 后台获取单个就业方案详情
     *
     * @param dataMap
     * @return
     */
    public ResponseMessage getJySchemeOne(Map<String, Object> dataMap) {
        return ResponseMessage.sendOK(this.dao.showJyInfo(dataMap));
    }

    public void deleteByXsxh(String xsxh) {
        this.dao.deleteByXsxh(xsxh);
    }

    /**
     * 根据单位所在地来记录学生去往的省份，直辖市，自治区，特别行政区
      * @param szd
     * @return
     */
     public String recordLx(String szd){
         if(TextUtils.isEmpty(szd)){
             return "不详";
         }
        if(szd.indexOf("北京")!=-1){
            return "北京市";
        }
         if(szd.indexOf("上海")!=-1){
             return "北京市";
         }
         if(szd.indexOf("天津")!=-1){
             return "天津市";
         }
         if(szd.indexOf("重庆")!=-1){
             return "重庆市";
         }
         if(szd.indexOf("香港")!=-1){
             return "香港特别行政区";
         }
         if(szd.indexOf("澳门")!=-1){
             return "澳门特别行政区";
         }
         if(szd.indexOf("自治区")!=-1){
             return szd.substring(0,szd.indexOf("区")+1);
         }
         if(szd.indexOf("省")!=-1){
             return szd.substring(0,szd.indexOf("省")+1);
         }
         return "不详";
     }

    /**
     * 后台删除jyscheme gridlist
     *
     * @param xsxhcodes
     * @param owidcodes
     * @return List
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Object deleteInfoList(List<String> xsxhcodes, List<String> owidcodes) {
        List<Map<String, Object>> objs = new ArrayList<>();
        int count = 0;
        for(String xsxh : xsxhcodes){
            HashMap<String, Object> params = Maps.newHashMap();
            bckjBizJypuchongService.deleteByXsxh(xsxh);
            deleteByXsxh(xsxh);
            params.put("owid",owidcodes.get(count));
            count++;
            objs.add(params);
        }
        return objs;
    }
    /**
     * <p>功能描述:新建或修改就业方案信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/14</li>
     * </ul>
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage insertssInfo(Map<String, Object> dataMap) {

        BckjBizStudentinfo bckjBizStudentinfo = new BckjBizStudentinfo();
        BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
        BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
        BckjBizSyb bckjBizSyb = new BckjBizSyb();
        BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
        BckjBizJypuchong bckjBizJypuchong = new BckjBizJypuchong();

        BckjBizYhkz bckjBizYhkz1 = bckjBizYhkzService.getByXsxh(dataMap.get("xsxh").toString());


        dataMap.put("xh", dataMap.get("xsxh"));
        dataMap.put("xsxm", dataMap.get("xm"));
        dataMap.put("yhRefOwid",dataMap.get("owid"));

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
            if(!com.zghzbckj.util.TextUtils.isEmpty(bckjBizYhkz1)){
                if (!(bckjBizYhkz1.getYhRefOwid().equals(bckjBizYhxx.getOwid()))) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "此学号已存在库内");
                }
            }
            //更改后的登入账号
            bckjBizYhxx.setYhDlzh(bckjBizYhkz.getXsxh());
            bckjBizYhxxService.updateJyscheme(bckjBizYhxx);
            bckjBizSybService.updateJyscheme(bckjBizSyb);
            bckjBizYhkzService.updateJyscheme(bckjBizYhkz);
            bckjBizJypuchongService.updateJyscheme(bckjBizJypuchong);
            bckjBizStudentinfoService.updataInfo(bckjBizStudentinfo);
            bckjBizJyscheme.setExp2("2");
            bckjBizJyscheme.setExp1(recordLx(getDicVall(50005,bckjBizJyscheme.getBdzszdmc())));
            this.dao.updateJyscheme(bckjBizJyscheme);
        }

        //否则为新建
        if(bckjBizYhxx.getOwid() == null){
            if(!com.zghzbckj.util.TextUtils.isEmpty(bckjBizYhkz1)){
                if (!(bckjBizYhkz1.getYhRefOwid().equals(bckjBizYhxx.getOwid()))) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "此学号已存在库内");
                }
            }
            //更新yhxx
            String uuid = IdGen.uuid();
            bckjBizYhxx.setOwid(uuid);
            bckjBizYhxx.setYhlx(1);
            bckjBizYhkz.setOlx(0);
            bckjBizYhxxService.insert(bckjBizYhxx);
            //更新yhkz
            bckjBizYhkz.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
            //更新syb
            bckjBizSyb.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizSybService.saveOrUpdate(bckjBizSyb);
            //更新studentInfo
            bckjBizStudentinfo.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizStudentinfoService.saveOrUpdate(bckjBizStudentinfo);
            //新保存jypuchong
            bckjBizJypuchong.setYhRefOwid(bckjBizYhxx.getOwid());
            bckjBizJypuchongService.saveOrUpdate(bckjBizJypuchong);
            //新保存jyscheme
            bckjBizJyscheme.setExp1(recordLx(getDicVall(50005,bckjBizJyscheme.getBdzszdmc())));
            bckjBizJyscheme.setYhRefOwid(bckjBizYhxx.getOwid());
            saveOrUpdate(bckjBizJyscheme);
            }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
    /**
     * 根据yhrefowid得到就业方案信息
     * @param yhRefOwid
     * @return
     */
    public BckjBizJyscheme getByYhRefOwid(String yhRefOwid){
        return this.dao.getByYhRefOwid(yhRefOwid);
    }

    /**
     * 根据用户id 更新学号
     * @param bckjBizJyscheme
     */
     public void updateXsxhByHyOwid( BckjBizJyscheme bckjBizJyscheme){
         this.dao.updateXsxhByHyOwid(bckjBizJyscheme);
     }

    /**
     * 根据字典表type 和 val2 获得val1
     * @param type
     * @param val2
     * @return
     */
    public String getDicVal(int type,String val2){
         return this.dao.getDicVal(type,val2);
    }

    /**
     * 根据字典表type 和 val1 获得val2
     * @param type
     * @param val1
     * @return
     */
    public String getDicVall(int type,String val1){
        return this.dao.getDicVall(type,val1);
    }
    /**
     * 根据字典表type 获得val2list
     * @param type
     * @return
     */
    public  List getDicListByType(int type){
        return this.dao.getDicListByType(type);
    }

    /**
     * 查询档案信息
     * @param  dataMap
     * @return  Map<String ,Object>
     */
    public  Map<String ,Object> queryDocument(Map<String, Object> dataMap) {
        return this.dao.showJyInfo(dataMap);
    }
}