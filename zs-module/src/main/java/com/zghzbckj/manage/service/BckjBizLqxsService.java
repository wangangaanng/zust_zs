/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.manage.dao.BckjBizLqxsDao;
import com.zghzbckj.manage.entity.BckjBizLntj;
import com.zghzbckj.manage.entity.BckjBizLqxs;
import com.zghzbckj.manage.entity.BckjBizZsjh;
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
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizLqxsService extends CrudService<BckjBizLqxsDao, BckjBizLqxs> {

    private static final Logger log = Logger.getLogger(BckjBizLqxsService.class);
    @Autowired
    BckjBizZsjhService bckjBizZsjhService;

    @Override
    public BckjBizLqxs get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizLqxs> findList(BckjBizLqxs bckjBizLqxs) {
        return super.findList(bckjBizLqxs);
    }

    @Override
    public PageInfo<BckjBizLqxs> findPage(Page<BckjBizLqxs> page, BckjBizLqxs bckjBizLqxs) {
        return super.findPage(page, bckjBizLqxs);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizLqxs bckjBizLqxs) {
        super.saveOrUpdate(bckjBizLqxs);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizLqxs bckjBizLqxs) {
        super.delete(bckjBizLqxs);
    }



    /**
     * <p>方法:findPagebckjBizLqxs TODO后台BckjBizLqxs分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizLqxs(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizLqxs> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizLqxs TODO保存BckjBizLqxs信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizLqxs(Map<String, Object> mapData) {
        BckjBizLqxs bckjBizLqxs = JsonUtil.map2Bean(mapData, BckjBizLqxs.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizLqxs bckjBizLqxsIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizLqxs, bckjBizLqxsIndata);
            bckjBizLqxs = bckjBizLqxsIndata;
        }
        saveOrUpdate(bckjBizLqxs);
        return ResponseMessage.sendOK(bckjBizLqxs);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizLqxs </p>
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
            BckjBizLqxs bckjBizLqxs = new BckjBizLqxs();
            bckjBizLqxs.setOwid(owid);
            this.dao.delete(bckjBizLqxs);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    /**
    *<p>方法:getLqcx TODO录取查询 </p>
    *<ul>
     *<li> @param mapData TODO</li>
    *<li>@return com.zghzbckj.manage.entity.BckjBizLqxs  </li>
    *<li>@author D.chen.g </li>
    *<li>@date 2019/10/15 15:06  </li>
    *</ul>
    */
    public BckjBizLqxs getLqcx(Map<String, Object> mapData) {
        List<BckjBizLqxs> list = this.dao.findListByMap(mapData);
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 后台录入录取招生
     *
     * @param path
     * @return ResponseMessage
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordInfo(String path) throws IllegalAccessException, InstantiationException, ParseException {
        String filename = path;
        List<List<String>> list = bckjBizZsjhService.getExcelLists(path);
        List<BckjBizLqxs> bckjBizLqxss= Lists.newArrayList();
        List<Map> oldMap =getOldLqxs();
        List<String> sfzs= Lists.newArrayList();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                HashMap<Object, Object> resMap = Maps.newHashMap();
                //学生信息录入
                List<String> cellList = list.get(i);//行循环
                String sfzh = cellList.get(0); //身份证号
                //如果身份证号为空则退出
                if (TextUtils.isEmpty(sfzh)) {
                    break;
                }
                sfzs.add(sfzh);
                resMap.put("sfzh", sfzh);
                String ksh = cellList.get(1); //考生号
                resMap.put("ksh", ksh);
                String xm = cellList.get(2); //姓名
                resMap.put("xm", xm);
                String xbdm = cellList.get(3); //性别
                resMap.put("xbdm", xbdm);
                String lqzy = cellList.get(4); //预录专业
                resMap.put("lqzy", lqzy);
                String lqzyok = cellList.get(5); //录取专业
                resMap.put("lqzyok", lqzyok);
                String ntzzy = cellList.get(6); //拟调整专业
                resMap.put("ntzzy", ntzzy);
                String lxdh = cellList.get(7); //联系电话
                resMap.put("lxdh", lxdh);
                String csny = cellList.get(8); //出生年月
                resMap.put("csny", csny);
                String zzmmdm = cellList.get(9); //政治面貌
                resMap.put("zzmmdm", getDicVal1(50008,zzmmdm));
                String mzdm = cellList.get(10); //民族
                if(mzdm.indexOf("汉")!=-1){
                    resMap.put("mzdm", getDicVal1(50009,"汉族"));
                }else {
                    resMap.put("mzdm",  getDicVal1(50009,mzdm));
                }
                String kslbdm = cellList.get(11); //考生类别
                resMap.put("kslbdm", kslbdm);
                String bylbdm = cellList.get(12); //毕业类别
                resMap.put("bylbdm", bylbdm);
                String zxdm = cellList.get(13); //中学代码
                resMap.put("zxdm", zxdm);
                String zxmc = cellList.get(14); //中学名称
                resMap.put("zxmc", zxmc);
                String dqdm = cellList.get(15); //户籍代码
                resMap.put("dqdm", dqdm);
                String jtdz = cellList.get(16); //考生家庭地址
                resMap.put("jtdz", jtdz);
                String yzbm = cellList.get(17); //邮编
                resMap.put("yzbm", yzbm);
                String kstc = cellList.get(18); //考生特长
                resMap.put("kstc", kstc);
                String ksjlhcf = cellList.get(19); //获奖情况
                resMap.put("ksjlhcf", ksjlhcf);
                String sjr = cellList.get(20); //收件人
                resMap.put("sjr", sjr);
                String tdcj = cellList.get(21); //投档成绩
                if(!TextUtils.isEmpty(tdcj)){
                    resMap.put("tdcj", Double.parseDouble(tdcj));
                }
                String tdzy = cellList.get(22); //投档志愿
                resMap.put("tdzy", tdzy);
                String yw = cellList.get(23); //语文
                if(!TextUtils.isEmpty(yw)){
                    resMap.put("yw", Double.parseDouble(yw));
                }
                String sx = cellList.get(24); //数学
                if(!TextUtils.isEmpty(sx)){
                    resMap.put("sx", Double.parseDouble(sx));
                }
                String wy = cellList.get(25); //外语
                if(!TextUtils.isEmpty(wy)){
                    resMap.put("wy", Double.parseDouble(wy));
                }
                String zhw = cellList.get(26); //综合文
                if(!TextUtils.isEmpty(zhw)){
                    resMap.put("zhw", Double.parseDouble(zhw));
                }
                String zhl = cellList.get(27); //综合理
                if(!TextUtils.isEmpty(zhl)){
                    resMap.put("zhl", Double.parseDouble(zhl));
                }
                String mk = cellList.get(28); //模块(技能加分)
                if(!TextUtils.isEmpty(mk)){
                    resMap.put("mk", Double.parseDouble(mk));
                }
                String mscj = cellList.get(29); //美术省统考成绩
                if(!TextUtils.isEmpty(mscj)){
                    resMap.put("mscj", Double.parseDouble(mscj));
                }
                String mszhcj = cellList.get(30); //美术综合成绩
                if(!TextUtils.isEmpty(mszhcj)){
                    resMap.put("mszhcj", Double.parseDouble(mszhcj));
                }
                String xy = cellList.get(31); //录取学院
                resMap.put("xy", xy);
                String xz = cellList.get(32); //学制
                resMap.put("xz", xz);
                String lp = cellList.get(33); //科类
                resMap.put("lp", lp);
                String pc = cellList.get(34); //批次
                resMap.put("pc", pc);
                String sf = cellList.get(35); //省份
                resMap.put("sf", sf);
                String memo = cellList.get(36); //备注
                resMap.put("memo", memo);
                String jcsj = cellList.get(37); //录取通知单寄出时间
                Date date = stringtoDate(jcsj);
                resMap.put("jcsj", date);
                String ems = cellList.get(38); //EMS单号
                resMap.put("ems", ems);
                BckjBizLqxs bckjBizLqxs = BckjBizLqxs.class.newInstance();
                MapUtil.easySetByMap(resMap,bckjBizLqxs);
                bckjBizLqxss.add(bckjBizLqxs);
            }
            for (BckjBizLqxs bckjBizLqxs:bckjBizLqxss){
                saveOrUpdate(bckjBizLqxs);
            }
            HashSet hashSet = new HashSet(sfzs);
            Integer count =hashSet.size();
            for (Map map:oldMap){
                hashSet.add(map.get("sfzh"));
                if(hashSet.size()!=++count){
                    BckjBizLqxs bckjBizLqxs = BckjBizLqxs.class.newInstance();
                    bckjBizLqxs.setOwid(map.get("owid").toString());
                    delete(bckjBizLqxs);
                }
            }
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

   public String getDicVal1(Integer type,String val2){
        return this.dao.getDicVal1( type, val2);

    }

    private List<Map> getOldLqxs() {
       return this.dao.getOldLqxs();
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

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseMessage saveOne(Map<String, Object> mapData) {
        BckjBizLqxs bckjBizLqxs = JsonUtil.map2Bean(mapData, BckjBizLqxs.class);
        MapUtil.easySetByMap(mapData,bckjBizLqxs);
        saveOrUpdate(bckjBizLqxs);
        return ResponseMessage.sendOK(bckjBizLqxs);
    }
}