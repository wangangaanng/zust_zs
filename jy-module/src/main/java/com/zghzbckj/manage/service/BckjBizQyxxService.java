/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.base.util.CacheUtil;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.JyContant;
import com.zghzbckj.manage.dao.BckjBizJobDao;
import com.zghzbckj.manage.dao.BckjBizQyxxDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJybm;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.web.MessageUtil;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import com.zghzbckj.util.PageUtils;
import com.zghzbckj.util.TextUtils;
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
public class BckjBizQyxxService extends CrudService<BckjBizQyxxDao, BckjBizQyxx> {

    private static final Logger log = Logger.getLogger(BckjBizQyxxService.class);
    @Autowired
    BckjBizQyxxDao qyxxDao;
    @Autowired
    BckjBizJobService jobService;
    @Autowired
    BckjBizJobDao jobDao;
    @Autowired
    BckjBizJybmService jybmService;


    @Override
    public BckjBizQyxx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizQyxx> findList(BckjBizQyxx bckjBizQyxx) {
        return super.findList(bckjBizQyxx);
    }

    @Override
    public PageInfo<BckjBizQyxx> findPage(Page<BckjBizQyxx> page, BckjBizQyxx bckjBizQyxx) {
        return super.findPage(page, bckjBizQyxx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizQyxx bckjBizQyxx) {
        super.saveOrUpdate(bckjBizQyxx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizQyxx bckjBizQyxx) {
        super.delete(bckjBizQyxx);
    }


    /**
     * <p>方法:findPagebckjBizQyxx TODO后台BckjBizQyxx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizQyxx(List<FilterModel> filters, Integer state, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        if (state.equals(JyContant.QY_ZT_TG)) {
            dataMap.put("pass", 1);
        } else {
            dataMap.put("state", state);
        }

        PageInfo<BckjBizQyxx> page = findPage(dataMap, pageNo, pageSize, " a.createtime desc ");

        List<BckjBizQyxx> records = page.getRecords();
        BckjBizQyxx qy = new BckjBizQyxx();
        qy.setQymc("共有：" + page.getTotalCount() + "家企业");
        qy.setReadOnly(true);
        records.add(0, qy);

        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizQyxx TODO保存BckjBizQyxx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizQyxx(Map<String, Object> mapData) {
        BckjBizQyxx bckjBizQyxx = JsonUtil.map2Bean(mapData, BckjBizQyxx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizQyxx bckjBizQyxxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizQyxx, bckjBizQyxxIndata);
            bckjBizQyxx = bckjBizQyxxIndata;
        }
        saveOrUpdate(bckjBizQyxx);
        return ResponseMessage.sendOK(bckjBizQyxx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizQyxx </p>
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
            BckjBizQyxx bckjBizQyxx = new BckjBizQyxx();
            bckjBizQyxx.setOwid(owid);
            this.dao.delete(bckjBizQyxx);
            params.put("owid", owid);
            objs.add(params);

            Map temp = new HashMap<>();
            temp.put("qyxxRefOwid", owid);
            List<BckjBizJob> jobList = jobService.findListByParams(temp, "");
            if (jobList != null && jobList.size() > 0) {
                for (BckjBizJob job : jobList) {
                    jobService.delete(job);
                }
            }
            List<BckjBizJybm> bmList = jybmService.findListByParams(temp, "");
            if (bmList != null && bmList.size() > 0) {
                for (BckjBizJybm bm : bmList) {
                    jybmService.delete(bm);
                }
            }
        }
        return ResponseMessage.sendOK(objs);
    }


    public static void main(String[] args) {
        String str = "91330108586547926H- (1/1)";
        String a = "9133010877T3580641H";

        System.out.println(a.length());
        if (str.length() > 18) {
            System.out.println(str.substring(0, 18));
        }
    }


    @Transactional(readOnly = false)
    public Map companyRegister(Map<String, Object> mapData) {

        Map resultMap = new HashMap<>(2);
        //统一税号
        Map params = new HashMap<>(1);
        params.put("qyTysh", mapData.get("qyTysh").toString());
        params.put("qyFrsfz", mapData.get("qyFrsfz").toString());
        BckjBizQyxx existCompany = qyxxDao.getExistOne(params);
        if (!TextUtils.isEmpty(existCompany)) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.SHCF_ERROR_MESSAGE);
            return resultMap;
        }

        BckjBizQyxx company = new BckjBizQyxx();
        try {
            company = MapUtils.map2Bean(mapData, BckjBizQyxx.class);
            company.setState(JyContant.QY_ZT_DSH);
            saveOrUpdate(company);
            //审核开关 0表示关 1表示开
            String flag = CacheUtil.getVal(JyContant.KG + JyContant.QYSH);
            if (!TextUtils.isEmpty(flag) && "1".equals(flag)) {
                company.setState(JyContant.QY_ZT_TG);
                String sfzStr = company.getQyFrsfz();
                String content = JyContant.QY_PASS_MESS + company.getQyTysh() + "，您的登录密码：" + sfzStr.substring(sfzStr.length() - 6, sfzStr.length());
                String mobile = company.getQyLxrdh();
                try {
                    MessageUtil.sendMessage(mobile, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            saveOrUpdate(company);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", company);
        return resultMap;

    }

    public Map login(Map<String, Object> mapData) {
        String getSfzStr = mapData.get("qyFrsfz").toString();
        Map resultMap = Maps.newHashMap();
        Map params = Maps.newHashMap();
        params.put("qyFrsfz", getSfzStr);
        params.put("qyTysh", mapData.get("qyTysh"));
        BckjBizQyxx company = qyxxDao.getOneInfo(params);
        if (TextUtils.isEmpty(company)) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.SH_ERROR_MESSAGE);
            return resultMap;
        }
        if (JyContant.QY_ZT_JY.equals(company.getState())) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.HMD_ERROR_MESSAGE);
            return resultMap;
        }
        company = new BckjBizQyxx();
        params.put("state", JyContant.QY_ZT_TG);
        company = qyxxDao.getOne(params);
        if (TextUtils.isEmpty(company)) {
            resultMap.put("result", "false");
            resultMap.put("msg", "不存在该账户或企业已被拒绝");
            return resultMap;
        }
        String sfzStr = company.getQyFrsfz();

        if (!sfzStr.substring(sfzStr.length() - 6, sfzStr.length()).equals(getSfzStr)) {
            resultMap.put("result", "false");
            resultMap.put("msg", JyContant.SFZ_ERROR_MESSAGE);
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", company);
        return resultMap;
    }

    @Transactional(readOnly = false)
    public Map fixCompany(Map<String, Object> mapData) {
        Map resultMap = new HashMap<>(2);
        BckjBizQyxx company = new BckjBizQyxx();
        BckjBizQyxx oldCompany = get(mapData.get("owid").toString());
        try {
            company = MapUtils.map2Bean(mapData, BckjBizQyxx.class);
            BeanUtil.copyPropertiesIgnoreNull(company, oldCompany);
            oldCompany.setState(JyContant.QY_ZT_TG);
            saveOrUpdate(oldCompany);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "false");
            resultMap.put("msg", e.getMessage());
            return resultMap;
        }
        resultMap.put("result", "true");
        resultMap.put("bean", company);
        return resultMap;
    }

    public BckjBizQyxx getOneCompany(String owid) {
        BckjBizQyxx qyxx = get(owid);
        Map params = new HashMap<>(2);
        if (!TextUtils.isEmpty(qyxx.getQyGsgm())) {
            params.put("type", JyContant.GSGM);
            params.put("dicVal1", qyxx.getQyGsgm());
            String gsgmStr = qyxxDao.queryDic(params);
            qyxx.setQyGsgmStr(gsgmStr);
        }
        if (!TextUtils.isEmpty(qyxx.getQyHylb())) {
            params.put("type", JyContant.HYLB);
            params.put("dicVal1", qyxx.getQyHylb());
            String hylbStr = qyxxDao.queryDic(params);
            qyxx.setQyHylbStr(hylbStr);
        }
        if (!TextUtils.isEmpty(qyxx.getQyGsxz())) {
            params.put("type", JyContant.GSXZ);
            params.put("dicVal1", qyxx.getQyGsxz());
            String gsxzStr = qyxxDao.queryDic(params);
            qyxx.setQyGsxzStr(gsxzStr);
        }
        return qyxx;
    }


    @Transactional(readOnly = false)
    public Map submitPurchaseBack(List<String> codes, Integer state) {
        Map resultMap = new HashMap<>(2);
        BckjBizQyxx qyxx = get(codes.get(0));
        String sfzStr = qyxx.getQyFrsfz();
        if (JyContant.QY_ZT_TG.equals(state)) {
            String content = JyContant.QY_PASS_MESS + qyxx.getQyTysh() + "，您的登录密码：" + sfzStr.substring(sfzStr.length() - 6, sfzStr.length());
            String mobile = qyxx.getQyLxrdh();
            try {
                MessageUtil.sendMessage(mobile, content);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (JyContant.QY_ZT_JJ.equals(state)) {
            String content = JyContant.QY_REJECT_MESS;
            String mobile = qyxx.getQyLxrdh();
            try {
                MessageUtil.sendMessage(mobile, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        qyxx.setState(state);
        saveOrUpdate(qyxx);
        resultMap = new HashMap<>(2);
        resultMap.put("result", "true");
        List<Object> _list = new ArrayList();
        _list.add(qyxx);
        resultMap.put("bean", _list);
        return resultMap;
    }

    /**
     * 企业查看关注的或报名的学生信息
     *
     * @param pageNo
     * @param pageSize
     * @param dataMap
     * @return
     */
    public PageInfo<Object> showStudentInfo(Integer pageNo, Integer pageSize, Map<String, Object> dataMap) {
        Page<Object> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        List<Object> lists = null;
        String type = dataMap.get("type").toString();
        //如果为报名
        if (type.equals("1")) {
            lists = this.dao.getBaoMingList(dataMap);
        }
        //如果为关注
        else if (type.equals("2")) {
            lists = this.dao.getGuanZhuList(dataMap);
        }
        page.setList(lists);
        return PageUtils.assimblePageInfo(page);
    }

    /**
     * <p>方法:addIntoHmd TODO加入黑名单 </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li> @param state TODO 2表示移除黑名单，3表示加入黑名单</li>
     * <li>@return java.util.List<java.util.Map>  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/16 15:11  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public List<Map> addIntoHmd(List<Object> codes) {
        BckjBizQyxx qyxx;
        Map result = Maps.newHashMap();
        List<Map> resluts = Lists.newArrayList();
        for (Object one : codes) {
            qyxx = this.get(MapUtils.getString((Map) one, "owid"));
            if (null != qyxx) {
                qyxx.setHmdqzt(qyxx.getState());
                qyxx.setState(3);
                saveOrUpdate(qyxx);
                result.put("owid", qyxx.getOwid());
                resluts.add(result);
                result = Maps.newHashMap();
                //下架职位
                Map params = Maps.newHashMap();
                params.put("qyxxRefOwid", qyxx.getOwid());
                jobDao.lowerJob(params);
            }
        }
        return resluts;
    }

    /**
     * <p>方法:reIntoHmd TODO移除黑名单 </p>
     * <ul>
     * <li> @param list TODO</li>
     * <li>@return java.util.List<java.util.Map>  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/24 20:45  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public List<Map> reIntoHmd(List<Object> list) {
        BckjBizQyxx qyxx;
        Map result = Maps.newHashMap();
        List<Map> resluts = Lists.newArrayList();
        for (Object one : list) {
            qyxx = this.get(MapUtils.getString((Map) one, "owid"));
            if (null != qyxx) {
                qyxx.setState(qyxx.getHmdqzt());
                saveOrUpdate(qyxx);
                result.put("owid", qyxx.getOwid());
                resluts.add(result);
                result = Maps.newHashMap();
            }
        }
        return resluts;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordQyxxInfo(String path) {
        //文件路径
        String filename = path;
        List<List<String>> list = getExcelLists(filename);
        HashMap<Object, Object> resMap = Maps.newHashMap();
        List<BckjBizQyxx> qyxxes = Lists.newArrayList();
        List<String> qyshs = Lists.newArrayList();
        List<Map> gsxzs = getDicListMapByType(20000);//公司性质20000
        List<Map> gshys = getDicListMapByType(20001);//公司行业20001
        List<Map> gsgms = getDicListMapByType(20002);//公司规模20002
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String qyTysh = cellList.get(0); //企业统一税号
                //如果企业统一税号为空则退出
                if (com.ourway.base.utils.TextUtils.isEmpty(qyTysh)) {
                    break;
                }
                qyshs.add(qyTysh);
                resMap.put("qyTysh", qyTysh);
                String qyFrsfz = cellList.get(1); //法人身份证号
                resMap.put("qyFrsfz", qyFrsfz);
                String qyFrdbxm = cellList.get(2); //法人姓名
                resMap.put("qyFrdbxm", qyFrdbxm);
                String qymc = cellList.get(3); //公司名称
                resMap.put("qymc", qymc);
                String qyProv = cellList.get(4); //所在省份
                resMap.put("qyProv", qyProv);
                String qyCity = cellList.get(5); //所在市
                resMap.put("qyCity", qyCity);
                String qyArea = cellList.get(6); //所在区
                resMap.put("qyArea", qyArea);
                String qyZczj = cellList.get(7); //公司注册资金
                resMap.put("qyZczj", qyZczj);
                String qydz = cellList.get(8); //公司地址
                resMap.put("qydz", qydz);
                String qyLxr = cellList.get(9); //联系人
                resMap.put("qyLxr", qyLxr);
                String qyLxrdh = cellList.get(10); //联系方式
                resMap.put("qyLxrdh", qyLxrdh);
                String qylxfs = cellList.get(11); //企业固话
                resMap.put("qylxfs", qylxfs);
                String qyYx = cellList.get(12); //企业邮箱
                resMap.put("qyYx", qyYx);
                String qyGsxz = cellList.get(13); //公司性质
                for (Map map : gsxzs) {
                    if (map.get("val2").equals(qyGsxz)) {
                        resMap.put("qyGsxz", map.get("val1"));
                    }
                }
                String qyHylb = cellList.get(14); //行业类别
                for (Map map : gshys) {
                    if (map.get("val2").equals(qyHylb)) {
                        resMap.put("qyHylb", map.get("val1"));
                    }
                }
                String qyGsgm = cellList.get(15); //公司规模
                for (Map map : gsgms) {
                    if (map.get("val2").equals(qyGsgm)) {
                        resMap.put("qyGsgm", map.get("val1"));
                    }
                }
                String qyGsjs = cellList.get(16); //公司介绍
                resMap.put("qyGsjs", qyGsjs);
                BckjBizQyxx bckjBizQyxx = new BckjBizQyxx();
                MapUtil.easySetByMap(resMap, bckjBizQyxx);
                BckjBizQyxx oneCompanyByTysh = getOneCompanyByTysh(bckjBizQyxx);
                if (!TextUtils.isEmpty(oneCompanyByTysh)) {
                    bckjBizQyxx.setOwid(oneCompanyByTysh.getOwid());
                    bckjBizQyxx.setCreatetime(new Date());
                }
                bckjBizQyxx.setState(2);
                qyxxes.add(bckjBizQyxx);
            }
        }
        //判断excel表中是否存在税号
        Set<String> qyshSet = new HashSet<>();
        int count = 1;
        for (String qysh : qyshs) {
            qyshSet.add(qysh);
            if (qyshSet.size() != count++) {
                return ResponseMessage.sendOK("导入失败,企业税号存在重复:" + qysh);
            }
        }
        //开始批量更新
        for (BckjBizQyxx bckjBizQyxx : qyxxes) {
            saveOrUpdate(bckjBizQyxx);
        }

        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public BckjBizQyxx getOneCompanyByTysh(BckjBizQyxx bckjBizQyxx) {
        return this.dao.getOneCompanyByTysh(bckjBizQyxx);
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


    public List<Map> getDicListMapByType(Integer i) {
        return this.dao.getDicListMapByType(i);
    }
}