/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.utils.excel.ExcelUtil;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.IdGen;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.manage.dao.BckjBizSybDao;
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
     * <p>方法:后台录入就业方案   syd 部分</p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    public void updataInfo(BckjBizSyb bckjBizSyb) {
        this.dao.updataInfo(bckjBizSyb);
    }

    public void updateByXsxh(BckjBizSyb bckjBizSyb) {
        this.dao.updateByXsxh(bckjBizSyb);
    }

    public void deleteByXsxh(String xh) {
        this.dao.deleteByXsxh(xh);
    }

    public void updateJyscheme(BckjBizSyb bckjBizSyb) {
        this.dao.updateJyscheme(bckjBizSyb);
    }

    /**
     * 后台生源管理获得gridlist
     *
     * @param filters
     * @param pageNo
     * @param pageSize
     * @return PageInfo<Object>
     */
    public PageInfo<Object> getSybList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Object> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        List<Object> lists = null;
        lists = this.dao.getSybList(dataMap);
        page.setList(lists);
        return PageUtils.assimblePageInfo(page);
    }


    /**
     * 后台展示一条学生生源信息
     * @param owid
     * @return BckjBizSyb
     */
    public BckjBizSyb getOne(String owid) {
        return this.dao.getOne(owid);
    }


    public Map<String, Object> getBynfByXsxh(Map<String, Object> dataMap) {
        return this.dao.getBynfByXsxh(dataMap);
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
    public ResponseMessage recordStudentInfo(String path) throws ParseException, RepeatException {
        //文件路径
        String filename = path;
        List<List<String>> list = getExcelLists(filename);
        HashMap<Object, Object> resMap = Maps.newHashMap();
        List<BckjBizYhxx> yhxxes = new ArrayList();
        List<BckjBizYhkz> yhkzes = new ArrayList();
        List<BckjBizSyb> sybs = new ArrayList();
        List<BckjBizStudentExpand> stus = new ArrayList();
        //读取自定义扩展字段
        List<String> fieldLists = new ArrayList<>();
        List<String> codeList = list.get(0);   //拿到code行
        for (int j = 34; j <codeList.size(); j++) {
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
                String xsxh = cellList.get(0); //学生学号
                resMap.put("xsxh", xsxh);
                String ksh = cellList.get(1); //考生号
                resMap.put("ksh", ksh);
                String xm = cellList.get(2); //姓名
                resMap.put("xm", xm);
                String xb = cellList.get(3); //性别
                if (xb.equals("男")) {
                    resMap.put("xb", 1);
                } else {
                    resMap.put("xb", 2);
                }
                String csrq = cellList.get(5); //出生日期
                resMap.put("csrq", ExcelUtils.stringtoDate(csrq));
                String syd = cellList.get(6); //生源地
                resMap.put("syd", bckjBizJyschemeService.getDicVal(50005, syd));
                String mz = cellList.get(7); //民族
                resMap.put("mz", bckjBizJyschemeService.getDicVal(50009, mz));
                String zzmm = cellList.get(8); //政治面貌
                resMap.put("zzmm", bckjBizJyschemeService.getDicVal(50008, zzmm));
                String rxnf = cellList.get(9); //入学日期
                resMap.put("rxnf", ExcelUtils.stringtoDate(rxnf));
                String bynf = cellList.get(10); //毕业年份
                resMap.put("bynf", bynf);
                String byrq = cellList.get(11); //毕业日期
                resMap.put("byrq", ExcelUtils.stringtoDate(byrq));
                String cxsy = cellList.get(12); //城乡生源
                resMap.put("cxsy", cxsy);
                String xqda = cellList.get(13); //入学前档案所在单位
                resMap.put("xqda", xqda);
                String sfrx = cellList.get(14); //档案是否转入学校
                if(sfrx.equals("是")){
                    resMap.put("sfrx", 1);
                }else if(sfrx.equals("否")){
                    resMap.put("sfrx", 0);
                }
                String hkpcs = cellList.get(15); //入学前户口所在地派出所
                resMap.put("hkpcs", hkpcs);
                String hkrx = cellList.get(16); //户口是否转入学校
                if(hkrx.equals("是")){
                    resMap.put("hkrx", 1);
                }else if(hkrx.equals("否")){
                    resMap.put("hkrx", 0);
                }
                String xlcc = cellList.get(17); //学历层次
                resMap.put("xlcc", xlcc);
                String xz = cellList.get(18); //学制
                resMap.put("xz", xz);
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
                    bckjBizStudentExpand.setVal(cellList.get(34+n));
                    stus.add(bckjBizStudentExpand);
                }
                BckjBizSyb bckjBizSyb = new BckjBizSyb();
                BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
                BckjBizYhkz bckjBizYhkz = new BckjBizYhkz();
                MapUtil.easySetByMap(resMap, bckjBizSyb);
                MapUtil.easySetByMap(resMap, bckjBizYhxx);
                MapUtil.easySetByMap(resMap, bckjBizYhkz);
                //此sfz判断是存在
                BckjBizSyb oneBySfz = getOneBySfz(bckjBizSyb.getSfz());
                //判断sfz是否存在学生 不为空则删除此身份证学生信息
                if (!TextUtils.isEmpty(oneBySfz)) {
                    bckjBizYhxxService.deleteBySfz(oneBySfz.getSfz());
                    bckjBizYhkzService.deleteByYhRefOwid(oneBySfz.getYhRefOwid());
                    delete(oneBySfz);
                    bckjBizStudentExpandService.deleteBySfz(oneBySfz.getSfz());
                }
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
            //开始批量更新
            for (BckjBizYhkz bckjBizYhkz : yhkzes) {
                bckjBizYhkzService.saveOrUpdate(bckjBizYhkz);
            }
            for (BckjBizYhxx bckjBizYhxx : yhxxes) {
                    bckjBizYhxxService.insert(bckjBizYhxx);
            }
            for (BckjBizSyb bckjBizSyb : sybs) {
                try {
                saveOrUpdate(bckjBizSyb);
                }
                catch (Exception e){
                    System.out.println(bckjBizSyb);
                }
            }
            for (BckjBizStudentExpand bckjBizStudentExpand : stus) {
                try {
                    bckjBizStudentExpandService.saveOrUpdate(bckjBizStudentExpand);
                }
                catch (Exception e){
                    System.out.println(bckjBizStudentExpand);
                }
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public static List<List<String>> getExcelLists(String filename) {
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        return list;
    }

    private BckjBizSyb getOneBySfz(String sfz) {
        return this.dao.getOneBySfz(sfz);
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
    public ResponseMessage insertssInfo(Map<String,Object> dataMap){
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
            //判断是否存在相同的学号
            if(!TextUtils.isEmpty(bckjBizSyb.getXsxh())){
                BckjBizSyb oneByXsxh = getOneByXsxh(bckjBizSyb.getXsxh());
                if (!TextUtils.isEmpty(oneByXsxh)){
                    //判断两个owid是否相等 如不相等则删除另一个
                    if (!oneByXsxh.getOwid().equals(bckjBizSyb.getOwid())){
                        return ResponseMessage.sendError(ResponseMessage.FAIL,"保存失败,此学号已存在");
                    }
                }
            }
            //设置状态为已编辑
            bckjBizSyb.setExp2("2");
            bckjBizYhxx.setOwid(bckjBizSyb.getYhRefOwid());
            bckjBizYhkzService.updateSudentInfo(bckjBizYhkz);
            //重新设置登入账号
            if (!TextUtils.isEmpty(bckjBizSyb.getXsxh())){
                bckjBizYhxx.setYhDlzh(bckjBizSyb.getXsxh());
            }
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
        bckjBizSyb.setExp1(bckjBizJyschemeService.recordLx(bckjBizSyb.getSyd()));
        saveOrUpdate(bckjBizSyb);
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
    public BckjBizSyb getOneByXsxh(String xsxh) {
        return this.dao.getOneByXsxh(xsxh);
    }

    public void updateBySfz(BckjBizSyb bckjBizSyb){
        this.dao.updateBySfz(bckjBizSyb);
    }

    public BckjBizSyb getOneQt(String owid) {
        return this.dao.getOneQt(owid);

    }
}