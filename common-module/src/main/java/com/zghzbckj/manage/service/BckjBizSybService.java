/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.IdGen;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizSybDao;
import com.zghzbckj.manage.entity.BckjBizStudentExpand;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.entity.BckjBizYhkz;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
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
 * @version 2019-09-20
 */
@Service
@Transactional(readOnly = true)
public class BckjBizSybService extends CrudService<BckjBizSybDao, BckjBizSyb> {

    private static final Logger log = Logger.getLogger(BckjBizSybService.class);

    @Override
    public BckjBizSyb get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizSyb> findList(BckjBizSyb bckjBizSyb) {
        return super.findList(bckjBizSyb);
    }

    @Override
    public PageInfo<BckjBizSyb> findPage(Page<BckjBizSyb> page, BckjBizSyb bckjBizSyb) {
        return super.findPage(page, bckjBizSyb);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizSyb bckjBizSyb) {
        super.saveOrUpdate(bckjBizSyb);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizSyb bckjBizSyb) {
        super.delete(bckjBizSyb);
    }

    @Autowired
    BckjBizYhkzService bckjBizYhkzService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    BckjBizJyschemeService bckjBizJyschemeService;
    @Autowired
    BckjBizStudentExpandService bckjBizStudentExpandService;

    /**
     * 后台展示生源list
     *
     * @param filters
     * @param pageNo
     * @param pageSize
     * @return List<bckjbizsyb>
     */
    public ResponseMessage findPageBckjBizSyb(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizSyb> page = findPage(dataMap, pageNo, pageSize, " xsxh asc");
        return ResponseMessage.sendOK(page);
    }


    /**
     * <p>方法:removeOrder TODO多条删除BckjBizSyb </p>
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
            BckjBizSyb bckjBizSyb = new BckjBizSyb();
            bckjBizSyb.setOwid(owid);
            this.dao.delete(bckjBizSyb);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
     * 前端获得学生生源地信息
     *
     * @param mapData
     * @return Map
     */
    public Map<String, Object> getSyInfo(Map<String, Object> mapData) {
        Map<String, Object> resMap = Maps.newHashMap();
        return null;
    }




    /**
     * 后台展示一条学生生源信息
     *
     * @param owid
     * @return BckjBizSyb
     */
    public BckjBizSyb getOne(String owid) {
        return this.dao.getOne(owid);
    }



    public List<Map<String, Object>> getByTypeSort(Map<String, Object> dataMap) {
        return this.dao.getByTypeSort(dataMap);
    }

    public void deleteBySfz(String sfz) {
        this.dao.deleteBySfz(sfz);
    }

    public Map<String, Object> getBynfBySfz(Map<String, Object> map) {
        return this.dao.getBynfBySfz(map);
    }


    /**
     * <p>功能描述:后台录入学生投档信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/23</li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordStudentInfo(String path) throws ParseException {
        //文件路径
        String filename = path;
        List<List<String>> list = getExcelLists(filename);
        HashMap<Object, Object> resMap = Maps.newHashMap();
        List<BckjBizYhxx> yhxxes = new ArrayList();
        List<BckjBizYhkz> yhkzes = new ArrayList();
        List<BckjBizSyb> sybs = new ArrayList();
        List<BckjBizStudentExpand> stus = new ArrayList();
        //用来存sfz
        List<String> sfzs = Lists.newArrayList();
        //获得已经在数据库的生源信息:key---yhrefowid&&value---sfz
        List<Map> resOldSybs = getOldSybs();
        //读取自定义扩展字段
        List<String> fieldLists = new ArrayList<>();
        List<String> codeList = list.get(0);   //拿到code行
        List<Map> syds= bckjBizJyschemeService.getDicListMapByType(50005);
        List<Map> mzs= bckjBizJyschemeService.getDicListMapByType(50009);
        List<Map> zzmms= bckjBizJyschemeService.getDicListMapByType(50008);
        for (int j = 34; j < codeList.size(); j++) {
            if (TextUtils.isEmpty(codeList.get(j))) {
                break;
            }
            fieldLists.add(codeList.get(j));
        }
        if (list != null) {
            for (int i = 2; i < list.size(); i++) {
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String sfz = cellList.get(4); //身份证号
                //如果身份证为空则退出
                if (TextUtils.isEmpty(sfz)) {
                    break;
                }
              /*  String regex = "\\d{15}(\\d{2}[0-9xX])?";
                if (!sfz.matches(regex)) {
                    return ResponseMessage.sendOK("存在错误的身份证格式录入");
                }*/
                resMap.put("sfz", sfz);
                sfzs.add(sfz);
                String xsxh = cellList.get(0); //学生学号
                resMap.put("xsxh", xsxh);
                String ksh = cellList.get(1); //考生号
                resMap.put("ksh", ksh);
                String xm = cellList.get(2); //姓名
                resMap.put("xm", xm);
                String xb = cellList.get(3); //性别
                resMap.put("xb", xb);
                if (xb.equals("男")) {
                    resMap.put("xb", 1);
                } else {
                    resMap.put("xb", 2);
                }
                String csrq = cellList.get(5); //出生日期
                try {
                    resMap.put("csrq", stringtoDate(csrq));
                }
                catch (Exception e){
                    System.out.println(e);
                }

                String syd = cellList.get(6); //生源地
                for (Map map:syds){
                    if(map.get("val2").equals(syd))
                        resMap.put("syd", map.get("val1"));
                }
                String mz = cellList.get(7); //民族
                for (Map map:mzs){
                    if(map.get("val2").equals(mz))
                        resMap.put("mz", map.get("val1"));
                }
                String zzmm = cellList.get(8); //政治面貌
                for (Map map:zzmms){
                    if(map.get("val2").equals(zzmm))
                        resMap.put("zzmm", map.get("val1"));
                }
                String rxnf = cellList.get(9); //入学日期
                try{
                    resMap.put("rxnf", stringtoDate(rxnf));
                }
                catch (Exception e){
                    System.out.println(e);
                }
                String bynf = cellList.get(10); //毕业年份
                resMap.put("bynf", bynf);
                String byrq = cellList.get(11); //毕业日期
                try {
                    resMap.put("byrq", stringtoDate(byrq));
                }
                catch (Exception e){
                    System.out.println(e);
                }
                String cxsy = cellList.get(12); //城乡生源
                resMap.put("cxsy", cxsy);
                String xqda = cellList.get(13); //入学前档案所在单位
                resMap.put("xqda", xqda);
                String sfrx = cellList.get(14); //档案是否转入学校
                if (sfrx.equals("是")) {
                    resMap.put("sfrx", 1);
                } else if (sfrx.equals("否")) {
                    resMap.put("sfrx", 0);
                }
                String hkpcs = cellList.get(15); //入学前户口所在地派出所
                resMap.put("hkpcs", hkpcs);
                String hkrx = cellList.get(16); //户口是否转入学校
                if (hkrx.equals("是")) {
                    resMap.put("hkrx", 1);
                } else if (hkrx.equals("否")) {
                    resMap.put("hkrx", 0);
                }
                String xlcc = cellList.get(17); //学历层次
                resMap.put("xlcc", xlcc);
                String xz = cellList.get(18); //学制
                resMap.put("xz",xz);
                if(xz.indexOf("一")!=-1||xz.indexOf("1")!=-1){
                    resMap.put("xz", "1");
                }else if(xz.indexOf("二")!=-1||xz.indexOf("2")!=-1){
                    resMap.put("xz", "2");
                }else if(xz.indexOf("三")!=-1||xz.indexOf("3")!=-1){
                    resMap.put("xz", "3");
                }else if(xz.indexOf("四")!=-1||xz.indexOf("4")!=-1){
                    resMap.put("xz", "4");
                }else if(xz.indexOf("五")!=-1||xz.indexOf("5")!=-1){
                    resMap.put("xz", "5");
                }else if(xz.indexOf("六")!=-1||xz.indexOf("6")!=-1){
                    resMap.put("xz", "6");
                }else if(xz.indexOf("七")!=-1||xz.indexOf("7")!=-1){
                    resMap.put("xz", "7");
                }else if(xz.indexOf("八")!=-1||xz.indexOf("8")!=-1){
                    resMap.put("xz", "8");
                }
                String xxmc = cellList.get(19); //所属学校
                resMap.put("xxmc", xxmc);
                String xsxy = cellList.get(20); //所属学院
                resMap.put("xsxy", xsxy);
                String xszy = cellList.get(21); //学校专业
                resMap.put("xszy", xszy);
                String zyfx = cellList.get(22); //专业方向
                resMap.put("zyfx", zyfx);
                String xsbj = cellList.get(23); //所在班级
                resMap.put("xsbj", xsbj);
                String pyfs = cellList.get(24); //培养方式
                resMap.put("pyfs", pyfs);
                String wpdw = cellList.get(25); //委培单位
                resMap.put("wpdw", wpdw);
                String knslb = cellList.get(26); //困难生类别
                resMap.put("knslb", knslb);
                String sfslb = cellList.get(27); //师范生类别
                resMap.put("sfslb", sfslb);
                String sjh = cellList.get(28); //手机号码
                resMap.put("sjh", sjh);
                String yx = cellList.get(29); //电子邮箱
                resMap.put("yx", yx);
                String qqhm = cellList.get(30); //QQ号码
                resMap.put("qqhm", qqhm);
                String jtdh = cellList.get(31); //家庭电话
                resMap.put("jtdh", jtdh);
                String jtyb = cellList.get(32); //家庭邮编
                resMap.put("jtyb", jtyb);
                String jtdz = cellList.get(33); //家庭地址
                resMap.put("jtdz", jtdz);
                //收集自定义字段
                for (int n = 1; n < fieldLists.size(); n++) {
                    BckjBizStudentExpand bckjBizStudentExpand = new BckjBizStudentExpand();
                    bckjBizStudentExpand.setName(sfz);
                    bckjBizStudentExpand.setCode(fieldLists.get(n - 1));
                    bckjBizStudentExpand.setVal(cellList.get(34 + n));
                    stus.add(bckjBizStudentExpand);
                }
                BckjBizSyb bckjBizSyb = new BckjBizSyb();
                BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
                BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
                MapUtil.easySetByMap(resMap, bckjBizSyb);
                MapUtil.easySetByMap(resMap, bckjBizYhxx);
                MapUtil.easySetByMap(resMap, bckjBizYhkz);
                String owid = IdGen.uuid();
                bckjBizYhxx.setOwid(owid);
                bckjBizYhkz.setYhRefOwid(owid);
                bckjBizSyb.setYhRefOwid(owid);
                //非企业
                bckjBizYhxx.setYhlx(1);
                bckjBizYhxx.setYhDlzh(bckjBizSyb.getXsxh());
                //登入密码身份后6位
                String dlmm = TextUtils.MD5(sfz.substring(sfz.length() - 6));
                bckjBizYhxx.setYhDlmm(dlmm);
                //学生为未编辑状态
                bckjBizSyb.setExp2("1");
                //学生
                bckjBizYhkz.setOlx(0);
                yhxxes.add(bckjBizYhxx);
                yhkzes.add(bckjBizYhkz);
                //生源地统计放在exp1
                bckjBizSyb.setExp1(bckjBizJyschemeService.recordLx(bckjBizSyb.getSyd()));
                sybs.add(bckjBizSyb);
            }
            //判断excel表中是否存在重复身份证
            Set<String> sfzSet = new HashSet<>();
            int count = 1;
                for (String sfz : sfzs) {
                    sfzSet.add(sfz);
                    if (sfzSet.size() != count++){
                        return ResponseMessage.sendOK("导入失败,身份证存在重复:"+sfz);
                    }
                }
            //开始批量更新
            for (BckjBizYhkz bckjBizYhkz : yhkzes) {
                bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
            }
            for (BckjBizYhxx bckjBizYhxx : yhxxes) {
                bckjBizYhxxService.insert(bckjBizYhxx);
            }
            for (BckjBizSyb bckjBizSyb : sybs) {
                saveOrUpdate(bckjBizSyb);
            }
            for (BckjBizStudentExpand bckjBizStudentExpand : stus) {
                bckjBizStudentExpandService.saveOrUpdate(bckjBizStudentExpand);
            }
        }
        /**
         * 后台录入学生信息时开启新的线程进行检查，相同身份证进行删除
         */
        Set<String> results = new HashSet<>(sfzs);
        Thread t = new Thread(new Runnable() {
            // run方法具体重写
            public void run() {
                for (Map map : resOldSybs) {
                    int count = results.size();
                    results.add(map.get("sfz").toString());
                    if (results.size() != (++count)) {
                        bckjBizYhxxService.deleteByOwid(map.get("yhOwid").toString());
                        bckjBizYhkzService.deleteByYhRefOwid(map.get("yhOwid").toString());
                        deleteByMap(map);
                        count--;
                    }
                }
            }
        });
        t.start();
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public List<Map> getOldSybs() {
        return this.dao.getOldSybs();
    }


    public static List<List<String>> getExcelLists(String filename) {
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        return list;
    }



    /**
     * 后台删除gridlist
     *
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public List deleteInfoList(List<String> owidcodes) {
        List<Map<String, Object>> objs = new ArrayList();
        for (String owid : owidcodes) {
            BckjBizSyb bckjBizSyb = getOne(owid);
            bckjBizYhxxService.deleteBySfz(bckjBizSyb.getSfz());
            bckjBizYhkzService.deleteByYhRefOwid(bckjBizSyb.getYhRefOwid());
            delete(bckjBizSyb);
            bckjBizStudentExpandService.deleteBySfz(bckjBizSyb.getSfz());
            Map<String, Object> params = Maps.newHashMap();
            params.put("owid", owid);
            objs.add(params);
        }
        return objs;
    }


    /**
     * <p>功能描述:新建或修改学生信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/10/23</li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage insertssInfo(Map<String, Object> dataMap) {
        BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
        BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
        BckjBizSyb bckjBizSyb = new BckjBizSyb();
        MapUtil.easySetByMap(dataMap, bckjBizSyb);
        //移除owid
        dataMap.remove("owid");
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        MapUtil.easySetByMap(dataMap, bckjBizYhkz);
        //如果為更新
        if (bckjBizSyb.getOwid() != null) {
            //判断是否存在相同的学号 1yi'x
            if (!TextUtils.isEmpty(bckjBizSyb.getXsxh())) {
                List<BckjBizSyb> bckjBizSybs = getListByXsxh(bckjBizSyb.getXsxh());
                if (bckjBizSybs.size() > 1) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "保存失败,此学号已存在");
                }
                //如果找出一个
                if (bckjBizSybs.size() == 1) {
                    //判断两个owid是否相等
                    if (!bckjBizSybs.get(0).getOwid().equals(bckjBizSyb.getOwid())) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, "保存失败,此学号已存在");
                    }
                }
            }
            //设置状态为已编辑
            bckjBizSyb.setExp2("2");
            bckjBizYhxx.setOwid(bckjBizSyb.getYhRefOwid());
            bckjBizYhkzService.updateSudentInfo(bckjBizYhkz);
            //重新设置登入账号
            if (!TextUtils.isEmpty(bckjBizSyb.getXsxh())) {
                bckjBizYhxx.setYhDlzh(bckjBizSyb.getXsxh());
            }
            String dlmm = TextUtils.MD5(bckjBizSyb.getSfz().substring(bckjBizSyb.getSfz().length() - 6));
            bckjBizYhxx.setYhDlmm(dlmm);
            bckjBizYhxxService.saveOrUpdate(bckjBizYhxx);
        }
        //否则为新建
        if (bckjBizSyb.getOwid() == null) {
            String uuid = IdGen.uuid();
            bckjBizYhxx.setOwid(uuid);
            bckjBizYhkz.setYhRefOwid(uuid);
            bckjBizSyb.setYhRefOwid(uuid);
            //非企业
            bckjBizYhxx.setYhlx(1);
            //判断sfz是否存在学生 不为空则删除此身份证学生信息
            BckjBizYhxx oneBySfz = bckjBizYhxxService.getOneBySfz(bckjBizYhxx.getSfz());
            if (!TextUtils.isEmpty(oneBySfz)) {
                deleteBySfz(bckjBizSyb.getSfz());
                bckjBizYhkzService.deleteByYhRefOwid(oneBySfz.getOwid());
                bckjBizYhxxService.deleteBySfz(oneBySfz.getSfz());
            }
            //设置登入账号
            if (!TextUtils.isEmpty(bckjBizYhkz.getXsxh())) {
                bckjBizYhxx.setYhDlzh(bckjBizSyb.getXsxh());
            }
            //设置登入密码
            String dlmm = TextUtils.MD5(bckjBizSyb.getSfz().substring(bckjBizSyb.getSfz().length() - 6));
            bckjBizYhxx.setYhDlmm(dlmm);
            bckjBizYhxx.setCreatetime(new Date());
            bckjBizYhxxService.insert(bckjBizYhxx);
            //设置未编辑状态
            bckjBizSyb.setExp2("1");
            //学生
            bckjBizYhkz.setOlx(0);
            bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
        }
        //exp1字段统计生源地所在省
        bckjBizSyb.setExp1(bckjBizJyschemeService.recordLx(bckjBizJyschemeService.getDicVall(50005, bckjBizSyb.getSyd())));
        saveOrUpdate(bckjBizSyb);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    private List<BckjBizSyb> getListByXsxh(String xsxh) {
        return this.dao.getListByXsxh(xsxh);
    }

    public BckjBizSyb getOneByXsxh(String xsxh) {
        return this.dao.getOneByXsxh(xsxh);
    }

    public void updateBySfz(BckjBizSyb bckjBizSyb) {
        this.dao.updateBySfz(bckjBizSyb);
    }

    public BckjBizSyb getOneQt(String owid) {
        return this.dao.getOneQt(owid);

    }

    /**
     * 处理excel中的9种日期格式
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date stringtoDate(String str) throws ParseException {
        Date utilDate = null;
        if (str.indexOf("/") != -1) {
            String[] split = str.split("\\/");
            String dateStr = getString(split);
            utilDate = getDate(dateStr, split);
        } else if (str.indexOf("-") != -1) {
            String[] split = str.split("\\-");
            String dateStr = getString(split);
            utilDate = getDate(dateStr, split);
        } else if (!TextUtils.isEmpty(str)) {
            String[] split = null;
            if (str.length() == 4) {
                split = new String[1];
                split[0] = str;
            } else if (str.length() == 6) {
                split = new String[2];
                split[0] = str.substring(0, 4);
                split[1] = str.substring(4);
            } else if (str.length() == 8) {
                split = new String[3];
                split[0] = str.substring(0, 4);
                split[1] = str.substring(4, 6);
                split[2] = str.substring(6);
            }
            String dateStr = getString(split);
            utilDate = getDate(dateStr, split);
        }
        return utilDate;
    }

    private static Date getDate(String dateStr, String[] split) throws ParseException {
        SimpleDateFormat sdf = null;
        if (split.length == 1) {
            sdf = new SimpleDateFormat("yyyy");
        } else if (split.length == 2) {
            sdf = new SimpleDateFormat("yyyy-MM ");
        } else if (split.length == 3) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        DateUtil.getDate(dateStr,"yyyy-MM-dd");
        Date utilDate = sdf.parse(dateStr);
        return utilDate;
    }

    private static String getString(String[] split) {
        String year = "";
        String month = "";
        String day = "";
        if (split.length == 1) {
            year = split[0];
            return year;
        } else if (split.length == 2) {
            year = split[0];
            month = split[1];
            if (month.length() < 2) {
                month = "0" + month;
            }
            return year + "-" + month;
        } else if (split.length == 3) {
            year = split[0];
            month = split[1];
            if (month.length() < 2) {
                month = "0" + month;
            }
            day = split[2];
            if (day.length() < 2) {
                day = "0" + day;
            }
            return year + "-" + month + "-" + day;
        }
        return "";

    }

    /**
     * 前台保存生源
     *
     * @param dataMap
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage insertssInfoQt(Map<String, Object> dataMap) {
        BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
        BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
        BckjBizSyb bckjBizSyb = getOne(dataMap.get("owid").toString());
        dataMap.remove("owid");
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        bckjBizYhxx.setOwid(bckjBizSyb.getYhRefOwid());
        BckjBizSyb bckjBizSyb1 = new BckjBizSyb();
        bckjBizSyb1.setYhRefOwid(bckjBizSyb.getYhRefOwid());
        bckjBizSyb1.setOwid(bckjBizSyb.getOwid());
        MapUtil.easySetByMap(dataMap, bckjBizSyb1);
        MapUtil.easySetByMap(dataMap, bckjBizYhkz);
        //判断是否存在相同的学号
        if (!TextUtils.isEmpty(bckjBizSyb1.getXsxh())) {
            List<BckjBizSyb> bckjBizSybs = getListByXsxh(bckjBizSyb1.getXsxh());
            if (bckjBizSybs.size() > 1) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "保存失败,此学号已存在");
            }
            //如果找出一个
            if (bckjBizSybs.size() == 1) {
                //判断两个owid是否相等
                if (!bckjBizSybs.get(0).getOwid().equals(bckjBizSyb1.getOwid())) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "保存失败,此学号已存在");
                }
            }
        }
        //设置状态为已编辑
        bckjBizSyb1.setExp2("2");
        bckjBizYhkz.setYhRefOwid(bckjBizSyb1.getYhRefOwid());
        bckjBizYhkzService.updateSudentInfo(bckjBizYhkz);
        //重新设置登入账号
        if (!TextUtils.isEmpty(bckjBizSyb1.getXsxh())) {
            bckjBizYhxx.setYhDlzh(bckjBizSyb1.getXsxh());
        }
        String dlmm = TextUtils.MD5(bckjBizSyb1.getSfz().substring(bckjBizSyb1.getSfz().length() - 6));
        bckjBizYhxx.setYhDlmm(dlmm);
        bckjBizYhxxService.saveOrUpdate(bckjBizYhxx);
        bckjBizSyb1.setExp1(bckjBizJyschemeService.recordLx(bckjBizJyschemeService.getDicVall(50005, bckjBizSyb1.getSyd())));
        saveOrUpdate(bckjBizSyb1);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    /**
     * 小程序下拉框显示20字典表内容
     *
     * @param dataMap
     * @return List<Map>
     */
    public List<Map> getSmallRoutine(Map<String, Object> dataMap) {
        return this.dao.getSmallRoutine(dataMap);
    }

    /**
     * 小程序显示生源地
     *
     * @param dataMap
     * @return
     */
    public BckjBizSyb getOneXcx(Map<String, Object> dataMap) {
        BckjBizSyb bckjBizSyb = this.dao.getOneQt(dataMap.get("owid").toString());
        bckjBizSyb.setSyd(bckjBizJyschemeService.getDicVall(50005, bckjBizSyb.getSyd()));
        return bckjBizSyb;
    }


    /**
     * 小程序保存
     *
     * @param dataMap
     * @return ResponseMessage
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage savaOneXcx(Map<String, Object> dataMap) {
        BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
        BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
        BckjBizSyb bckjBizSyb = getOne(dataMap.get("owid").toString());
        dataMap.remove("owid");
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        bckjBizYhxx.setOwid(bckjBizSyb.getYhRefOwid());
        BckjBizSyb bckjBizSyb1 = new BckjBizSyb();
        bckjBizSyb1.setYhRefOwid(bckjBizSyb.getYhRefOwid());
        bckjBizSyb1.setOwid(bckjBizSyb.getOwid());
        MapUtil.easySetByMap(dataMap, bckjBizSyb1);
        MapUtil.easySetByMap(dataMap, bckjBizYhkz);
        bckjBizYhkz.setYhRefOwid(bckjBizSyb1.getYhRefOwid());
        //判断是否存在相同的学号
        if (!TextUtils.isEmpty(bckjBizSyb1.getXsxh())) {
            List<BckjBizSyb> bckjBizSybs = getListByXsxh(bckjBizSyb1.getXsxh());
            if (bckjBizSybs.size() > 1) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "保存失败,此学号已存在");
            }
            //如果找出一个
            if (bckjBizSybs.size() == 1) {
                //判断两个owid是否相等
                if (!bckjBizSybs.get(0).getOwid().equals(bckjBizSyb1.getOwid())) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "保存失败,此学号已存在");
                }
            }
        }
        //设置状态为已编辑
        bckjBizSyb1.setExp2("2");
        bckjBizYhkzService.updateSudentInfo(bckjBizYhkz);
        //重新设置登入账号
        if (!TextUtils.isEmpty(bckjBizSyb1.getXsxh())) {
            bckjBizYhxx.setYhDlzh(bckjBizSyb1.getXsxh());
        }
        String dlmm = TextUtils.MD5(bckjBizSyb1.getSfz().substring(bckjBizSyb1.getSfz().length() - 6));
        bckjBizYhxx.setYhDlmm(dlmm);
        bckjBizYhxxService.saveOrUpdate(bckjBizYhxx);
        if(TextUtils.isEmpty(bckjBizJyschemeService.getDicVal(50005, bckjBizSyb1.getSyd()))){
            return ResponseMessage.sendError(ResponseMessage.FAIL,"生源地填写有误,请重新填写!");
        }
        bckjBizSyb1.setSyd(bckjBizJyschemeService.getDicVal(50005, bckjBizSyb1.getSyd()));
        bckjBizSyb1.setExp1(bckjBizJyschemeService.recordLx(bckjBizJyschemeService.getDicVall(50005, bckjBizSyb1.getSyd())));
        saveOrUpdate(bckjBizSyb1);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public BckjBizSyb getOneBySfz(Map<String,Object> dataMap){
        String sfz=dataMap.get("sfz").toString();
        return this.dao.getOneBySfz(sfz);
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage updateXsxh(String path) {
        String filename = path;
        List<List<String>> list = getExcelLists(path);
        List<Map> listMap= Lists.newArrayList();
        List<Map> kzMaps= Lists.newArrayList();
        List<Map> xxMaps= Lists.newArrayList();
        List<Map> sydMaps= Lists.newArrayList();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                HashMap<String, Object> resMap = Maps.newHashMap();
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String sfz = cellList.get(1); //xsxh
                //如果年份为空则退出
                if (TextUtils.isEmpty(sfz)) {
                    break;
                }
                resMap.put("sfz", sfz);
                String xsxh = cellList.get(0); //省份
                resMap.put("xsxh", xsxh);
                listMap.add(resMap);
            }
            if (!TextUtils.isEmpty(listMap)&&listMap.size()>0){
               for (Map map:listMap){
                   BckjBizSyb oneBySfz = getOneBySfz(map);
                   Map<String,Object> kzMap= Maps.newHashMap();
                   Map<String,Object> sydMap= Maps.newHashMap();
                   Map<String,Object> xxMap= Maps.newHashMap();
                   kzMap.put("yhRefOwid",oneBySfz.getYhRefOwid());
                   sydMap.put("owid",oneBySfz.getOwid());
                   xxMap.put("owid",oneBySfz.getYhRefOwid());
                   if(!TextUtils.isEmpty(map.get("xsxh"))){
                       kzMap.put("xsxh",map.get("xsxh"));
                       sydMap.put("xsxh",map.get("xsxh"));
                       xxMap.put("yhDlzh",map.get("xsxh"));
                       kzMaps.add(kzMap);
                       xxMaps.add(xxMap);
                       sydMaps.add(sydMap);
                   }
               }
               for (Map map:kzMaps){
                   bckjBizYhkzService.updateXsxh(map);
               }
               for (Map map:xxMaps){
                   bckjBizYhxxService.updateDlzh(map);
               }
               for(Map map:sydMaps){
                    this.dao.updateXsxh(map);
                }
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
}