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
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.BckjBizJobDao;
import com.zghzbckj.manage.dao.BckjBizJybmDao;
import com.zghzbckj.manage.dao.BckjBizJypmDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizJypm;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.web.MessageUtil;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJypmService extends CrudService<BckjBizJypmDao, BckjBizJypm> {

    private static final Logger log = Logger.getLogger(BckjBizJypmService.class);

    @Autowired
    BckjBizJypmDao bckjBizJypmDao;
    @Autowired
    BckjBizJobDao jobDao;
    @Autowired
    BckjBizJybmDao bmDao;
    @Autowired
    BckjBizQyxxService qyxxServie;
    @Autowired
    BckjBizJybmService jybmService;

    @Override
    public BckjBizJypm get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizJypm> findList(BckjBizJypm bckjBizJypm) {
        return super.findList(bckjBizJypm);
    }

    @Override
    public PageInfo<BckjBizJypm> findPage(Page<BckjBizJypm> page, BckjBizJypm bckjBizJypm) {
        return super.findPage(page, bckjBizJypm);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizJypm bckjBizJypm) {
        super.saveOrUpdate(bckjBizJypm);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizJypm bckjBizJypm) {
        super.delete(bckjBizJypm);
    }

    /**
     * <p>方法:就业排行榜列表 listRank TODO </p>
     * <ul>
     * <li> @param dataMap TODO</li>
     * <li>@return java.util.List </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/10/12 11:30  </li>
     * </ul>
     */
    public List<Map<String, Object>> listRank(Map<String, Object> dataMap) {
        //统计学院就业情况
        List<Map<String, Object>> collegeList = this.dao.collegeStats();
        for (Map<String, Object> college : collegeList) {
            List<Map<String, Object>> majorList = this.dao.majorList(MapUtils.getString(college, "szxy"));
            Map<String, Object> statsMap = new HashMap<>();
            BigDecimal pmbyzrs = new BigDecimal(MapUtils.getInt(college, "pmbyrs"));
            BigDecimal pmqyzrs = new BigDecimal(MapUtils.getInt(college, "pmqyrs") * 100);
            statsMap.put("pmzy", "合计");
            statsMap.put("pmbyrs", college.get("pmbyrs"));
            statsMap.put("pmqyrs", college.get("pmqyrs"));
            statsMap.put("pmjyl", pmqyzrs.divide(pmbyzrs, 2, RoundingMode.HALF_UP));
            college.put("avgjyl", pmqyzrs.divide(pmbyzrs, 2, RoundingMode.HALF_UP));
            majorList.add(statsMap);
            college.put("pmzyList", majorList);
        }
        //根据就业率排名
        collegeList.sort((o1, o2) -> {
            BigDecimal a = (BigDecimal) o1.get("avgjyl");
            BigDecimal b = (BigDecimal) o2.get("avgjyl");
            if (a.compareTo(b) > -1) {
                return -1;
            } else if (a.compareTo(b) == 0) {
                return 0;
            } else {
                return 1;
            }
        });
        return collegeList;
    }

    /**
     * <p>方法:根据学院专业名称查询 getByCollegeMajor TODO </p>
     * <ul>
     * <li> @param majorName TODO</li>
     * <li>@return com.zghzbckj.manage.entity.BckjBizJypm  </li>
     * <li>@author xuyux </li>
     * <li>@date 2019/9/22 18:14  </li>
     * </ul>
     */
    public BckjBizJypm getByCollegeMajor(String collegeName, String majorName) {
        return this.dao.getByMajor(collegeName, majorName);
    }

    /**
     * <p>功能描述:清空所有数据 deleteAll</p >
     * <ul>
     * <li>@param []</li>
     * <li>@return void</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/11/7 15:00</li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public void deleteAll() {
        bckjBizJypmDao.deleteAll();
    }

    @Transactional(readOnly = false)
    public void deleteThisYear(String nf) {
        bckjBizJypmDao.deleteThisYear(nf);
    }

    /**
     * <p>功能描述:导出合并单元格的excel exportRankExcel</p >
     * <ul>
     * <li>@param []</li>
     * <li>@return java.lang.String</li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/11/8 10:22</li>
     * </ul>
     */
    public String exportRankExcel() {
        Map<String, Object> params = new HashMap<>();
        String[] title = {"所在学院", "所在专业", "毕业生人数（人）", "就业人数（人）", "就业率（%）", "排名"};
        //本地测试
//        String filePath = "F:\\img\\export\\";
        //线上正式
        String filePath = "/mnt/files/zjcFiles/export/";
        String fileName = System.currentTimeMillis() + ".xls";
        List<Map<String, Object>> dataList = listRank(params);
        ExcelUtils.exportRankExcel(title, dataList, filePath + fileName);
        return fileName;
    }

    /**
     * <p>方法:findPagebckjBizJypm TODO后台BckjBizJypm分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizJypm(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        dataMap.put("isNull", "yes");
        PageInfo<BckjBizJypm> page = findPage(dataMap, pageNo, pageSize, "pmjyl");
        List<BckjBizJypm> records = page.getRecords();
        Map<String, Object> data = this.dao.statistic(dataMap);
        int byrs = MapUtils.getInt(data, "pmbyrs");
        int qyrs = MapUtils.getInt(data, "pmqyrs");
        if (byrs < 0 || qyrs < 0) {
            byrs += 1;
            qyrs += 1;
        }
        BigDecimal pmbyrs = new BigDecimal(byrs);
        BigDecimal pmqyrs = new BigDecimal(qyrs * 100);
        BigDecimal pmjyl = pmqyrs.divide(pmbyrs,1, RoundingMode.DOWN);
        //统计行
        BckjBizJypm jypm = new BckjBizJypm();
        jypm.setSzxy("共有：" + MapUtils.getString(data, "szxy") + "个学院");
        jypm.setPmzy("共有：" + page.getTotalCount() + "个专业");
        jypm.setPmbyrs(byrs);
        jypm.setPmqyrs(qyrs);
        jypm.setPmjyl(pmjyl);
        jypm.setReadOnly(true);
        records.add(0, jypm);
        return ResponseMessage.sendOK(page);
    }

    public ResponseMessage findPageBckjBizJypmNf(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        dataMap.put("isNotNull", "yes");
        PageInfo<BckjBizJypm> page = findPage(dataMap, pageNo, pageSize, "pmnf,pmjyl");
        List<BckjBizJypm> records = page.getRecords();
        Map<String, Object> data = this.dao.statistic(dataMap);
        int byrs = MapUtils.getInt(data, "pmbyrs");
        int qyrs = MapUtils.getInt(data, "pmqyrs");
        if (byrs < 0 || qyrs < 0) {
            byrs += 1;
            qyrs += 1;
        }
        BigDecimal pmbyrs = new BigDecimal(byrs);
        BigDecimal pmqyrs = new BigDecimal(qyrs * 100);
        BigDecimal pmjyl = pmqyrs.divide(pmbyrs,1, RoundingMode.DOWN);
        //统计行
        BckjBizJypm jypm = new BckjBizJypm();
        jypm.setSzxy("共有：" + MapUtils.getString(data, "szxy") + "个学院");
        jypm.setPmzy("共有：" + page.getTotalCount() + "个专业");
        jypm.setPmbyrs(byrs);
        jypm.setPmqyrs(qyrs);
        jypm.setPmjyl(pmjyl);
        jypm.setReadOnly(true);
        records.add(0, jypm);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizJypm TODO保存BckjBizJypm信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizJypm(Map<String, Object> mapData) {
        BckjBizJypm bckjBizJypm = JsonUtil.map2Bean(mapData, BckjBizJypm.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizJypm bckjBizJypmIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizJypm, bckjBizJypmIndata);
            bckjBizJypm = bckjBizJypmIndata;
        }
        saveOrUpdate(bckjBizJypm);
        return ResponseMessage.sendOK(bckjBizJypm);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizJypm </p>
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
            BckjBizJypm bckjBizJypm = new BckjBizJypm();
            bckjBizJypm.setOwid(owid);
            this.dao.delete(bckjBizJypm);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }


    /**
     * 读取excel
     *
     * @param filename
     * @return
     */
    public static List<List<String>> getExcelLists(String filename) {
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        return list;
    }


//    public static void main(String[] args) {
//        String a= "25.0";
//        String b ="25";
//        System.out.println(a.replaceAll(".0",""));
//        System.out.println(b.replaceAll(".0",""));
//    }

    @Transactional(readOnly = false)
    public ResponseMessage recordJobInfo(String path) {
        //文件路径
        String filename = path;
        List<List<String>> list = getExcelLists(filename);
        HashMap<Object, Object> resMap = Maps.newHashMap();
//        HashMap<Object, Object> bmMap = Maps.newHashMap();
//        List<BckjBizQyxx> qyxxes = Lists.newArrayList();
        List<String> qyshs = Lists.newArrayList();

        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //企业信息录入
                List<String> cellList = list.get(i);//行循环
                String qyTysh = cellList.get(1); //企业统一税号
                //如果企业统一税号为空则退出
                if (com.ourway.base.utils.TextUtils.isEmpty(qyTysh)) {
                    continue;
                }
                qyshs.add(qyTysh);
                resMap.put("qyTysh", qyTysh);
                String qyFrsfz = cellList.get(2); //法人身份证号
                resMap.put("qyFrsfz", qyFrsfz);
                String qyFrdbxm = cellList.get(3); //法人姓名
                resMap.put("qyFrdbxm", qyFrdbxm);
                String qymc = cellList.get(4); //公司名称
                resMap.put("qymc", qymc);
                String qyLxr = cellList.get(5); //联系人
                resMap.put("qyLxr", qyLxr);
                String qyLxrdh = cellList.get(6); //联系方式
                resMap.put("qyLxrdh", qyLxrdh);
                String qylxfs = cellList.get(7); //企业固话
                resMap.put("qylxfs", qylxfs);

                String zwbh = cellList.get(0); //展位编号
                if (TextUtils.isEmpty(zwbh)) {
                    zwbh = JyContant.ZWMESS;
                }
                String zw1 = cellList.get(8); //岗位1
                String zw2 = cellList.get(10); //岗位2
                String zw3 = cellList.get(12); //岗位3
                String zw4 = cellList.get(14); //岗位4
                String zw5 = cellList.get(16); //岗位5

                String rs1 = cellList.get(9).replaceAll(".0", "");
                String rs2 = cellList.get(11).replaceAll(".0", "");
                String rs3 = cellList.get(13).replaceAll(".0", "");
                String rs4 = cellList.get(15).replaceAll(".0", "");
                String rs5 = cellList.get(17).replaceAll(".0", "");

                boolean isNumRs1 = rs1.matches("[0-9]+");
                boolean isNumRs2 = rs2.matches("[0-9]+");
                boolean isNumRs3 = rs3.matches("[0-9]+");
                boolean isNumRs4 = rs4.matches("[0-9]+");
                boolean isNumRs5 = rs5.matches("[0-9]+");
                //团报来源
                String exp1 = cellList.get(18);

                BckjBizQyxx bckjBizQyxx = new BckjBizQyxx();
                MapUtil.easySetByMap(resMap, bckjBizQyxx);
                BckjBizQyxx oneCompanyByTysh = qyxxServie.getOneCompanyByTysh(bckjBizQyxx);
                if (!com.zghzbckj.util.TextUtils.isEmpty(oneCompanyByTysh)) {
                    bckjBizQyxx.setOwid(oneCompanyByTysh.getOwid());
                    bckjBizQyxx.setCreatetime(new Date());
                }
                bckjBizQyxx.setState(2);
                qyxxServie.saveOrUpdate(bckjBizQyxx);
                ///保存职来职往企业报名

                BckjBizJob job = jobDao.getByName(JyContant.ZWBT);

                Map params = Maps.newHashMap();
                params.put("qyxxRefOwid", bckjBizQyxx.getOwid());
                params.put("jobRefOwid", job.getOwid());
                List<BckjBizJybm> oldJybms = jybmService.findListByParams(params, "");
                if (!TextUtils.isEmpty(oldJybms) && oldJybms.size() > 0) {
                    BckjBizJybm oldJybm = oldJybms.get(0);
                    oldJybm.setState(1);
                    oldJybm.setExp1(exp1);
                    oldJybm.setZwbh(zwbh);
                    jybmService.saveOrUpdate(oldJybm);
                    //发送通知短信
                    String content = JyContant.ZPH_PASS_MESS + zwbh + "，地点：" + job.getZphJbdd() + ",举办日期：" + DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd") + "，具体时间：" + job.getZphJtsj() + "，展位现场分配";
                    String mobile = qyLxrdh;
                    try {
                        MessageUtil.sendMessage(mobile, content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                BckjBizJybm jybm = new BckjBizJybm();


                jybm.setQyxxRefOwid(bckjBizQyxx.getOwid());
                jybm.setJobRefOwid(job.getOwid());
                jybm.setBmlx(JyContant.BMLX_QY);
                jybm.setBmdx(JyContant.BMDX_ZPH);
                jybm.setBmsj(new Date());
                jybm.setZwbh(zwbh);
                jybm.setLxr(qyLxr);
                jybm.setLxdh(qyLxrdh);
                jybm.setBmqygs(1);
                jybm.setQymc(qymc);
                jybm.setQysh(qyTysh);
                jybm.setExp1(exp1);
                jybm.setXjsj(DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd"));
                if (!TextUtils.isEmpty(zw1)) {
                    jybm.setZw1(zw1);
                }
                if (!TextUtils.isEmpty(zw2)) {
                    jybm.setZw2(zw2);
                }
                if (!TextUtils.isEmpty(zw3)) {
                    jybm.setZw3(zw3);
                }
                if (!TextUtils.isEmpty(zw4)) {
                    jybm.setZw4(zw4);
                }
                if (!TextUtils.isEmpty(zw5)) {
                    jybm.setZw5(zw5);
                }
                if (!TextUtils.isEmpty(rs1) && isNumRs1) {
                    jybm.setRs1(rs1);
                }
                if (!TextUtils.isEmpty(rs2) && isNumRs2) {
                    jybm.setRs2(rs2);
                }
                if (!TextUtils.isEmpty(rs3) && isNumRs3) {
                    jybm.setRs3(rs3);
                }
                if (!TextUtils.isEmpty(rs4) && isNumRs4) {
                    jybm.setRs4(rs4);
                }
                if (!TextUtils.isEmpty(rs5) && isNumRs5) {
                    jybm.setRs5(rs5);
                }
                jybm.setState(1);
                jybmService.saveOrUpdate(jybm);
                //发送通知短信
                String content = JyContant.ZPH_PASS_MESS + zwbh + "，地点：" + job.getZphJbdd() + ",举办日期：" + DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd") + "，具体时间：" + job.getZphJtsj() + "，展位现场分配";
                String mobile = qyLxrdh;
                try {
                    MessageUtil.sendMessage(mobile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //判断excel表中是否存在税号
//        Set<String> qyshSet = new HashSet<>();
//        int count = 1;
//        for (String qysh : qyshs) {
//            qyshSet.add(qysh);
//            if (qyshSet.size() != count++) {
//                return ResponseMessage.sendOK("导入失败,企业税号存在重复:" + qysh);
//            }
//        }
//        //开始批量更新
//        for (BckjBizQyxx bckjBizQyxx : qyxxes) {
//            qyxxServie.saveOrUpdate(bckjBizQyxx);
//
//        }

        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    @Transactional(readOnly = false)
    public ResponseMessage recordBmInfo(Map<String, Object> dataMap) {
        //文件路径

        String path = dataMap.get("path").toString();
        List<List<String>> list = getExcelLists(path);
        String yqRefOwid = dataMap.get("yqRefOwid").toString();
        String jobRefOwid = dataMap.get("jobRefOwid").toString();
        BckjBizQyxx qyxx = qyxxServie.get(yqRefOwid);
        if (TextUtils.isEmpty(qyxx)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "园区不存在");
        }
        Map params = Maps.newHashMap();
        params.put("qyxxRefOwid", yqRefOwid);
        params.put("jobRefOwid", jobRefOwid);
        BckjBizJybm jybm = jybmService.findOneByParams(params, "");
        if (TextUtils.isEmpty(jybm)) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "不存在报名信息");
        }
//        params.clear();
//        params.put("yqRefOwid", yqRefOwid);
//        params.put("jobRefOwid", jobRefOwid);
//        params.put("state", 1);
//        List<BckjBizJybm> bmList = bmDao.findListByMap(params);
//        if (!TextUtils.isEmpty(bmList) && bmList.size() > 0 && list != null && list.size() > 0) {
//            Integer jybmAllSize = jybm.getBmqygs();
//            Integer existSize = bmList.size();
//            Integer bmSize = list.size() + 1;
//            if (existSize + bmSize > jybmAllSize) {
//                return ResponseMessage.sendError(ResponseMessage.FAIL, "导入失败，分配企业已满，请核实");
//            }
//        }

        HashMap<Object, Object> resMap = Maps.newHashMap();
        List<String> qyshs = Lists.newArrayList();

        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //企业信息录入
                List<String> cellList = list.get(i);//行循环
                String qyTysh = cellList.get(1); //企业统一税号
                //如果企业统一税号为空则退出
                if (com.ourway.base.utils.TextUtils.isEmpty(qyTysh)) {
                    break;
                }
                qyshs.add(qyTysh);
                resMap.put("qyTysh", qyTysh);
                String qyFrsfz = cellList.get(2); //法人身份证号
                resMap.put("qyFrsfz", qyFrsfz);
                String qyFrdbxm = cellList.get(3); //法人姓名
                resMap.put("qyFrdbxm", qyFrdbxm);
                String qymc = cellList.get(4); //公司名称
                resMap.put("qymc", qymc);
                String qyLxr = cellList.get(5); //联系人
                resMap.put("qyLxr", qyLxr);
                String qyLxrdh = cellList.get(6); //联系方式
                resMap.put("qyLxrdh", qyLxrdh);
                String qylxfs = cellList.get(7); //企业固话
                resMap.put("qylxfs", qylxfs);
                resMap.put("yqRefOwid", yqRefOwid);
                resMap.put("yqmc", qyxx.getQymc());

                String zwbh = cellList.get(0); //展位编号
                String zw1 = cellList.get(8); //岗位1
                String zw2 = cellList.get(10); //岗位2
                String zw3 = cellList.get(12); //岗位3
                String zw4 = cellList.get(14); //岗位4
                String zw5 = cellList.get(16); //岗位5

                String rs1 = cellList.get(9);
                String rs2 = cellList.get(11);
                String rs3 = cellList.get(13);
                String rs4 = cellList.get(15);
                String rs5 = cellList.get(17);

                BckjBizQyxx bckjBizQyxx = new BckjBizQyxx();
                MapUtil.easySetByMap(resMap, bckjBizQyxx);
                BckjBizQyxx oneCompanyByTysh = qyxxServie.getOneCompanyByTysh(bckjBizQyxx);
                if (!com.zghzbckj.util.TextUtils.isEmpty(oneCompanyByTysh)) {
                    bckjBizQyxx.setOwid(oneCompanyByTysh.getOwid());
                    bckjBizQyxx.setCreatetime(new Date());
                }
                bckjBizQyxx.setState(2);
                qyxxServie.saveOrUpdate(bckjBizQyxx);
                ///保存职来职往企业报名
                jybm = new BckjBizJybm();
                jybm.setQyxxRefOwid(bckjBizQyxx.getOwid());
                BckjBizJob job = jobDao.getByName(JyContant.ZWBT);
                jybm.setJobRefOwid(job.getOwid());
                jybm.setBmlx(JyContant.BMLX_QY);
                jybm.setBmdx(JyContant.BMDX_ZPH);
                jybm.setBmsj(new Date());
                jybm.setZwbh(zwbh);
                jybm.setLxr(qyLxr);
                jybm.setLxdh(qyLxrdh);
                jybm.setBmqygs(1);
                jybm.setQymc(qymc);
                jybm.setQysh(qyTysh);
                jybm.setXjsj(DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd"));
                jybm.setYqRefOwid(yqRefOwid);
                if (!TextUtils.isEmpty(zw1)) {
                    jybm.setZw1(zw1);
                }
                if (!TextUtils.isEmpty(zw2)) {
                    jybm.setZw2(zw2);
                }
                if (!TextUtils.isEmpty(zw3)) {
                    jybm.setZw3(zw3);
                }
                if (!TextUtils.isEmpty(zw4)) {
                    jybm.setZw4(zw4);
                }
                if (!TextUtils.isEmpty(zw5)) {
                    jybm.setZw5(zw5);
                }
                if (!TextUtils.isEmpty(rs1)) {
                    jybm.setRs1(rs1);
                }
                if (!TextUtils.isEmpty(rs2)) {
                    jybm.setRs2(rs2);
                }
                if (!TextUtils.isEmpty(rs3)) {
                    jybm.setRs3(rs3);
                }
                if (!TextUtils.isEmpty(rs4)) {
                    jybm.setRs4(rs4);
                }
                if (!TextUtils.isEmpty(rs5)) {
                    jybm.setRs5(rs5);
                }
                jybm.setState(1);
                jybmService.saveOrUpdate(jybm);
                //发送通知短信
                String content = JyContant.ZPH_PASS_MESS + zwbh + "，地点：" + job.getZphJbdd() + ",举办日期：" + DateUtil.getDateString(job.getZphKsrq(), "yyyy-MM-dd") + "，具体时间：" + job.getZphJtsj();
                String mobile = qyLxrdh;
                try {
                    MessageUtil.sendMessage(mobile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }
}