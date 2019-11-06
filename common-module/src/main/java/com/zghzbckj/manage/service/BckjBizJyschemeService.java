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
import com.zghzbckj.base.model.PublicDataVO;
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
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    BckjBizSybService bckjBizSybService;
    @Autowired
    BckjBizYhkzService bckjBizYhkzService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    BckjBizJobPlanOtherService bckjBizJobPlanOtherService;


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
   /* @Transactional(readOnly = false)
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
    }*/

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
    public ResponseMessage recordJyInfo(String path) throws IllegalAccessException, InstantiationException, ParseException {
        //用来存放错误信息
        String error = "";
        //文件路径
        String filename = path;
        List<List<String>> list = bckjBizSybService.getExcelLists(path);
        HashMap<Object, Object> resMap = Maps.newHashMap();
        List<BckjBizJyscheme> jys = new ArrayList();
        List<BckjBizJobPlanOther> jos = new ArrayList();
        List<String> xsxhs = Lists.newArrayList();
        //GAI
        List<Map> oldJyschemes = getOldJyscheme();
        //获得毕业去向字典表内容
        List<Map> byqxs = getDicListMapByType(50001);
        List<Map> dwszdmcs = getDicListMapByType(50005);
        List<Map> yrdwxzmcs = getDicListMapByType(50002);
        List<Map> dwhylbmcs = getDicListMapByType(50003);
        List<Map> gzzwlbmcs = getDicListMapByType(50004);
        List<Map> bdzqflbmcs = getDicListMapByType(50007);
        //读取自定义扩展字段
        List<String> fieldLists = new ArrayList<>();
        List<String> codeList = list.get(0);   //拿到code行
        for (int j = 26; j < codeList.size(); j++) {
            if (TextUtils.isEmpty(codeList.get(j))) {
                break;
            }
            fieldLists.add(codeList.get(j));
        }
        if (list != null) {
            for (int i = 2; i < list.size(); i++) {
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String xsxh = cellList.get(0); //学生学号
                //如果学号为空则退出
                if (TextUtils.isEmpty(xsxh)) {
                    break;
                }
                xsxhs.add(xsxh);
                resMap.put("xsxh", xsxh);
                String xm = cellList.get(1); //姓名
                resMap.put("xm", xm);
                String xxmc = cellList.get(2); //学校名称
                resMap.put("xxmc", xxmc);
                String byqx = cellList.get(3); //毕业去向名称 50001
                for (Map map : byqxs) {
                    if (map.get("val2").equals(byqx))
                        resMap.put("byqx", map.get("val1"));
                }
                String sfzydk = cellList.get(4); //专业是否对口
                if (sfzydk.equals("是")) {
                    resMap.put("sfzydk", 1);
                } else if (sfzydk.equals("否")) {
                    resMap.put("sfzydk", 2);
                }
                String yrdwmc = cellList.get(5); //用人单位名称
                resMap.put("yrdwmc", yrdwmc);
                String yrdwdm = cellList.get(6); //用人单位代码
                resMap.put("yrdwdm", yrdwdm);
                String yrdwxzmc = cellList.get(7); //用人单位性质名称 50002
                for (Map map : yrdwxzmcs) {
                    if (map.get("val2").equals(yrdwxzmc))
                        resMap.put("yrdwxzmc", map.get("val1"));
                }
                String dwhylbmc = cellList.get(8); //单位行业类别名称 50003
                for (Map map : dwhylbmcs) {
                    if (map.get("val2").equals(dwhylbmc))
                        resMap.put("dwhylbmc", map.get("val1"));
                }
                String dwszdmc = cellList.get(9); //      50005
                for (Map map : dwszdmcs) {
                    if (map.get("val2").equals(dwszdmc))
                        resMap.put("dwszdmc", map.get("val1"));
                }
                String dwlxr = cellList.get(10); //单位联系人
                resMap.put("dwlxr", dwlxr);
                String dwdh = cellList.get(11); //单位电话
                resMap.put("dwdh", dwdh);
                String gzzwlbmc = cellList.get(12); //工作职位类别名称 50004
                for (Map map : gzzwlbmcs) {
                    if (map.get("val2").equals(gzzwlbmc))
                        resMap.put("gzzwlbmc", map.get("val1"));
                }
                String bdzqflbmc = cellList.get(13); //报到证签发类别名称
                for (Map map : bdzqflbmcs) {
                    if (map.get("val2").equals(bdzqflbmc))
                        resMap.put("bdzqflbmc", map.get("val1"));
                }
                String bdzqwdwmc = cellList.get(14); //报到证签往单位名称
                resMap.put("bdzqwdwmc", bdzqwdwmc);
                String bdzqwszdmc = cellList.get(15); //报到证签往单位所在地名称
                for (Map map : dwszdmcs) {
                    if (map.get("val2").equals(bdzqwszdmc))
                        resMap.put("bdzqwszdmc", map.get("val1"));
                }
                String bdkssj = cellList.get(16); //报到开始时间
                try {
                    resMap.put("bdkssj", bckjBizSybService.stringtoDate(bdkssj));
                } catch (Exception e) {
                    System.out.println(e);
                }
                String bdjssj = cellList.get(17); //报到结束时间
                try {
                    resMap.put("bdjssj", bckjBizSybService.stringtoDate(bdjssj));
                } catch (Exception e) {
                    System.out.println(e);
                }
                String sfdydwbdz = cellList.get(18); //是否打印单位到报到证备注
                if (sfdydwbdz.equals("是")) {
                    resMap.put("sfdydwbdz", 1);
                } else if (sfdydwbdz.equals("否")) {
                    resMap.put("sfdydwbdz", 2);
                }
                String datddw = cellList.get(19); //档案投递单位
                resMap.put("datddw", datddw);
                String bdzbz = cellList.get(20); //报到证备注
                resMap.put("bdzbz", bdzbz);
                String datdxxdz = cellList.get(21); //档案投递详细地址
                resMap.put("datdxxdz", datdxxdz);
                String datddh = cellList.get(22); //档案投递电话
                resMap.put("datddh", datddh);
                String hkqydz = cellList.get(23); //户口迁移地址
                resMap.put("hkqydz", hkqydz);
                String bzone = cellList.get(24); //备注1
                resMap.put("bzone", bzone);
                String bztwo = cellList.get(25); //备注2
                resMap.put("bztwo", bztwo);
                String bzthree = cellList.get(26); //备注3
                resMap.put("bzthree", bzthree);
                //收集自定义字段
                for (int n = 1; n < fieldLists.size(); n++) {
                    BckjBizJobPlanOther bckjBizJobPlanOther = BckjBizJobPlanOther.class.newInstance();
                    bckjBizJobPlanOther.setName(xsxh);
                    bckjBizJobPlanOther.setCode(fieldLists.get(n - 1));
                    bckjBizJobPlanOther.setVal(cellList.get(26 + n));
                    jos.add(bckjBizJobPlanOther);
                }
                BckjBizJyscheme bckjBizJyscheme = BckjBizJyscheme.class.newInstance();
                MapUtil.easySetByMap(resMap, bckjBizJyscheme);
                //设置为未编辑状态
                bckjBizJyscheme.setExp2("1");
                //就业所在地地统计放在exp1
                bckjBizJyscheme.setExp1(recordLx(bckjBizJyscheme.getDwszdmc()));
                jys.add(bckjBizJyscheme);
            }
            //判断excel表中是否存在重复的xsxh
            Set<String> xsxhSet = new HashSet<>();
            int count = 1;
            for (String xsxh : xsxhs) {
                xsxhSet.add(xsxh);
                if (xsxhSet.size() != count++) {
                    return ResponseMessage.sendOK("导入失败,学生学号存在重复:" + xsxh);
                }
            }

            /**
             * 后台录入学生信息时开启新的线程进行检查，相同学号进行删除jyshcheme
             */
            Set<String> results = new HashSet<>(xsxhs);
            Thread t = new Thread(new Runnable() {
                // run方法具体重写
                public void run() {
                    for (Map map : oldJyschemes) {
                        int count = results.size();
                        results.add(map.get("xsxh").toString());
                        if (results.size() != (++count)) {
                            deleteByMap(map);
                            count--;
                            //这里不删除bckjBizStudentExpand
                            //* bckjBizStudentExpandService.deleteBySfz(bckjBizSyb.getSfz());*//*
                        }
                    }
                }
            });
            t.start();
            for (BckjBizJyscheme bckjBizJyscheme : jys) {
                saveOrUpdate(bckjBizJyscheme);
            }
            for (BckjBizJobPlanOther bckjBizJobPlanOther : jos) {
                bckjBizJobPlanOtherService.saveOrUpdate(bckjBizJobPlanOther);
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public List<Map> getDicListMapByType(Integer i) {
        return this.dao.getDicListMapByType(i);
    }

    private List<Map> getOldJyscheme() {
        return this.dao.getOldJyscheme();
    }


    private BckjBizJyscheme getOneByXsxh(String xsxh) {
        return this.dao.getOneByXsxh(xsxh);
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
    public BckjBizJyscheme getOneJyscheme(Map<String, Object> dataMap) {
        return this.dao.getOneJyscheme(dataMap);
    }

    public void deleteByXsxh(String xsxh) {
        this.dao.deleteByXsxh(xsxh);
    }

    /**
     * 根据单位所在地来记录学生去往的省份，直辖市，自治区，特别行政区
     *
     * @param szd
     * @return
     */
    public String recordLx(String szd) {
        if (TextUtils.isEmpty(szd)) {
            return "不详";
        }
        if (szd.indexOf("北京") != -1) {
            return "北京市";
        }
        if (szd.indexOf("上海") != -1) {
            return "北京市";
        }
        if (szd.indexOf("天津") != -1) {
            return "天津市";
        }
        if (szd.indexOf("重庆") != -1) {
            return "重庆市";
        }
        if (szd.indexOf("香港") != -1) {
            return "香港特别行政区";
        }
        if (szd.indexOf("澳门") != -1) {
            return "澳门特别行政区";
        }
        if (szd.indexOf("自治区") != -1) {
            return szd.substring(0, szd.indexOf("区") + 1);
        }
        if (szd.indexOf("省") != -1) {
            return szd.substring(0, szd.indexOf("省") + 1);
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
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Object deleteJyList(List<String> xsxhcodes, List<String> owidcodes) {
        List<Map<String, Object>> objs = new ArrayList<>();
        int count = 0;
        for (String xsxh : xsxhcodes) {
            HashMap<String, Object> params = Maps.newHashMap();
            this.dao.deleteByXsxh(xsxh);
            bckjBizJobPlanOtherService.deleteByName(xsxh);
            deleteByXsxh(xsxh);
            params.put("owid", owidcodes.get(count));
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
     * <li>@date 2019/10/26</li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage insertssInfo(Map<String, Object> dataMap) throws IllegalAccessException, InstantiationException {
        BckjBizJobPlanOther bckjBizJobPlanOther = BckjBizJobPlanOther.class.newInstance();
        BckjBizYhxx bckjBizYhxx = BckjBizYhxx.class.newInstance();
        BckjBizSyb bckjBizSyb = BckjBizSyb.class.newInstance();
        BckjBizJyscheme bckjBizJyscheme = BckjBizJyscheme.class.newInstance();

        MapUtil.easySetByMap(dataMap, bckjBizJyscheme);
        dataMap.remove("owid");
        MapUtil.easySetByMap(dataMap, bckjBizSyb);
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        MapUtil.easySetByMap(dataMap, bckjBizJobPlanOther);
        BckjBizSyb oneByXsxh = bckjBizSybService.getOneByXsxh(bckjBizJyscheme.getXsxh());
        if (TextUtils.isEmpty(oneByXsxh)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "此学号生源不存在,无法保存");
        }
        //如果為更新
        if (bckjBizJyscheme.getOwid() != null) {
            bckjBizJyscheme.setExp2("2");
        }
        //如果為新建
        else if (bckjBizJyscheme.getOwid() == null) {
            //判断jyscheme中此学号是否存在
            BckjBizJyscheme oneByXsxh1 = getOneByXsxh(oneByXsxh.getXsxh());
            if (!TextUtils.isEmpty(oneByXsxh1)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "此学号已存在,无法保存");
            }
            //设置为未编辑状态
            bckjBizJyscheme.setExp2("1");
        }
        //按身份证号码更新 yhxx和syb
        bckjBizYhxx.setSfz(oneByXsxh.getSfz());
        bckjBizSyb.setSfz(oneByXsxh.getSfz());
        //设置yh_ref_owid
        bckjBizJyscheme.setYhRefOwid(oneByXsxh.getYhRefOwid());
        //就业所在地地统计放在exp1
        bckjBizJyscheme.setExp1(recordLx(bckjBizJyscheme.getDwszdmc()));
        //更新yhxx syb jyscheme
        bckjBizYhxxService.updateBySfz(bckjBizYhxx);
        bckjBizSybService.updateBySfz(bckjBizSyb);
        //设置就业所在地的省份
        bckjBizJyscheme.setExp1(recordLx(getDicVall(50005, bckjBizJyscheme.getDwszdmc())));
        saveOrUpdate(bckjBizJyscheme);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    /**
     * 根据字典表type 和 val2 获得val1
     *
     * @param type
     * @param val2
     * @return
     */
    public String getDicVal(int type, String val2) {
        List<String> dicVal = this.dao.getDicVal(type, val2);
        if (dicVal.size() > 0) {
            return dicVal.get(0);
        }
        return "";
    }

    /**
     * 根据字典表type 和 val1 获得val2
     *
     * @param type
     * @param val1
     * @return
     */
    public String getDicVall(int type, String val1) {
        List<String> dicVall = this.dao.getDicVall(type, val1);
        if (dicVall.size() > 0) {
            return dicVall.get(0);
        }
        return "";
    }

    /**
     * 根据字典表type 获得val2list
     *
     * @param type
     * @return
     */
    public List getDicListByType(int type) {
        return this.dao.getDicListByType(type);
    }

    /**
     * 前台进入就业方案读取出一条信息  根据xsxh去取
     *
     * @param
     * @return
     */
    public BckjBizJyscheme getOneJyschemeQt(Map<String, Object> dataMap) {
        BckjBizYhxx bckjBizYhxx = bckjBizYhxxService.get(dataMap.get("owid").toString());
        HashMap<String, Object> sendMap = Maps.newHashMap();
        sendMap.put("sfz",bckjBizYhxx.getSfz());
        BckjBizSyb oneBySfz = bckjBizSybService.getOneBySfz(sendMap);
        BckjBizJyscheme bckjBizJyscheme = null;
        BckjBizSyb xsxh = bckjBizSybService.getOneByXsxh(oneBySfz.getXsxh());
        if (!com.zghzbckj.util.TextUtils.isEmpty(xsxh)) {
            bckjBizJyscheme = getOneByXsxh(xsxh.getXsxh());
        }
        return bckjBizJyscheme;
    }

    /**
     * 前台保存就业方案
     *
     * @param
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage insertssInfoQt(Map<String, Object> dataMap) throws IllegalAccessException, InstantiationException {
        BckjBizYhxx bckjBizYhxx = BckjBizYhxx.class.newInstance();
        BckjBizSyb bckjBizSyb = BckjBizSyb.class.newInstance();
        BckjBizJyscheme bckjBizJyscheme = BckjBizJyscheme.class.newInstance();
        BckjBizJyscheme resbckjBizJyscheme = getOneByXsxh(dataMap.get("xsxh").toString());
        BckjBizSyb oneByXsxh = bckjBizSybService.getOneByXsxh(dataMap.get("xsxh").toString());
        if (TextUtils.isEmpty(oneByXsxh)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "生源库中无该学号");
        }
        //就业方案为null的话
        if (!TextUtils.isEmpty(resbckjBizJyscheme)) {
            bckjBizJyscheme.setOwid(resbckjBizJyscheme.getOwid());
            bckjBizJyscheme.setYhRefOwid(resbckjBizJyscheme.getYhRefOwid());
        }
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        MapUtil.easySetByMap(dataMap, bckjBizJyscheme);
        MapUtil.easySetByMap(dataMap, bckjBizSyb);
        bckjBizJyscheme.setExp2("2");
        //按身份证号码更新 yhxx和syb
        bckjBizYhxx.setSfz(oneByXsxh.getSfz());
        bckjBizSyb.setSfz(oneByXsxh.getSfz());
        //就业所在地地统计放在exp1
        bckjBizJyscheme.setExp1(recordLx(bckjBizJyscheme.getDwszdmc()));
        //更新yhxx syb jyscheme
        bckjBizYhxxService.updateBySfz(bckjBizYhxx);
        bckjBizSybService.updateBySfz(bckjBizSyb);
        //设置就业所在地的省份
        bckjBizJyscheme.setExp1(recordLx(getDicVall(50005, bckjBizJyscheme.getDwszdmc())));
        saveOrUpdate(bckjBizJyscheme);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public BckjBizJyscheme getOneJyschemeXcx(Map<String, Object> dataMap) {
        BckjBizYhxx bckjBizYhxx = bckjBizYhxxService.get(dataMap.get("owid").toString());
        HashMap<String, Object> sendMap = Maps.newHashMap();
        sendMap.put("sfz",bckjBizYhxx.getSfz());
        BckjBizSyb oneBySfz = bckjBizSybService.getOneBySfz(sendMap);
        BckjBizJyscheme bckjBizJyscheme = this.dao.getOneByXsxh(oneBySfz.getXsxh());
        if (!TextUtils.isEmpty(bckjBizJyscheme)) {
            if (!TextUtils.isEmpty(bckjBizJyscheme.getDwszdmc())) {
                bckjBizJyscheme.setDwszdmc(getDicVall(50005, bckjBizJyscheme.getDwszdmc()));
            }
            if (!TextUtils.isEmpty(bckjBizJyscheme.getBdzqwszdmc())) {
                bckjBizJyscheme.setBdzqwszdmc(getDicVall(50005, bckjBizJyscheme.getBdzqwszdmc()));
            }
        }
        return bckjBizJyscheme;
    }


    /**
     * 保存就业方案信息   小程序
     *
     * @param dataMap
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage saveOneJyschemeXcx(Map<String, Object> dataMap) throws IllegalAccessException, InstantiationException {
        BckjBizYhxx bckjBizYhxx = BckjBizYhxx.class.newInstance();
        BckjBizSyb bckjBizSyb = BckjBizSyb.class.newInstance();
        BckjBizJyscheme bckjBizJyscheme = BckjBizJyscheme.class.newInstance();
        BckjBizJyscheme resbckjBizJyscheme = getOneByXsxh(dataMap.get("xsxh").toString());
        BckjBizSyb oneByXsxh = bckjBizSybService.getOneByXsxh(dataMap.get("xsxh").toString());
        if (TextUtils.isEmpty(oneByXsxh)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "生源库中无该学号");
        }
        //就业方案为null的话
        if (!TextUtils.isEmpty(resbckjBizJyscheme)) {
            bckjBizJyscheme.setOwid(resbckjBizJyscheme.getOwid());
            bckjBizJyscheme.setYhRefOwid(resbckjBizJyscheme.getYhRefOwid());
        }
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        MapUtil.easySetByMap(dataMap, bckjBizJyscheme);
        MapUtil.easySetByMap(dataMap, bckjBizSyb);
        bckjBizJyscheme.setExp2("2");
        //按身份证号码更新 yhxx和syb
        bckjBizYhxx.setSfz(oneByXsxh.getSfz());
        bckjBizSyb.setSfz(oneByXsxh.getSfz());
        //就业所在地地统计放在exp1
        bckjBizJyscheme.setExp1(recordLx(bckjBizJyscheme.getDwszdmc()));
        //更新yhxx syb jyscheme
        bckjBizYhxxService.updateBySfz(bckjBizYhxx);
        bckjBizSybService.updateBySfz(bckjBizSyb);
        //设置就业所在地的省份
        if (TextUtils.isEmpty(getDicVal(50005, bckjBizJyscheme.getDwszdmc()))) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "填写有误,请重新填写!");
        }
        if (TextUtils.isEmpty(getDicVal(50005, bckjBizJyscheme.getBdzqwszdmc()))) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "填写有误,请重新填写!");
        }
        bckjBizJyscheme.setDwszdmc(getDicVal(50005, bckjBizJyscheme.getDwszdmc()));
        bckjBizJyscheme.setBdzqwszdmc(getDicVal(50005, bckjBizJyscheme.getBdzqwszdmc()));
        bckjBizJyscheme.setExp1(recordLx(getDicVall(50005, bckjBizJyscheme.getDwszdmc())));
        saveOrUpdate(bckjBizJyscheme);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    /**
     * 查询档案信息
     *
     * @param dataMap
     * @return queryDocument
     */
    public BckjBizJyscheme queryDocument(Map<String, Object> dataMap) {
        BckjBizJyscheme bckjBizJyscheme = null;
        BckjBizSyb oneBySfz = bckjBizSybService.getOneBySfz(dataMap);
        //如果由此生源就根据
        if (!TextUtils.isEmpty(oneBySfz)) {
            if (!TextUtils.isEmpty(oneBySfz.getXsxh())) {
                bckjBizJyscheme = getOneByXsxh(oneBySfz.getXsxh());
            }
        }
        if (!TextUtils.isEmpty(getJyselfInfo(dataMap))){
            bckjBizJyscheme = getJyselfInfo(dataMap);
        }
        if (!TextUtils.isEmpty(bckjBizJyscheme)) {
            bckjBizJyscheme.setBdzqwszdmc(getDicVall(50005, bckjBizJyscheme.getBdzqwszdmc()));
            bckjBizJyscheme.setByqx(getDicVall(50001, bckjBizJyscheme.getByqx()));
            bckjBizJyscheme.setYrdwxzmc(getDicVall(50002, bckjBizJyscheme.getYrdwxzmc()));
            bckjBizJyscheme.setDwhylbmc(getDicVall(50003, bckjBizJyscheme.getDwhylbmc()));
            bckjBizJyscheme.setDwszdmc(getDicVall(50005, bckjBizJyscheme.getDwszdmc()));
            bckjBizJyscheme.setGzzwlbmc(getDicVall(50004, bckjBizJyscheme.getGzzwlbmc()));
            bckjBizJyscheme.setBdzqflbmc(getDicVall(50007, bckjBizJyscheme.getBdzqflbmc()));
        }
        return bckjBizJyscheme;
    }


    private BckjBizJyscheme getJyselfInfo(Map<String, Object> dataMap) {
        return this.dao.getJyselfInfo(dataMap);
    }
}