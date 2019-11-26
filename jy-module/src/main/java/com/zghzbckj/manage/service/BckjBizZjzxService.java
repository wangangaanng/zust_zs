/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectRestriction;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.RepeatException;
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.feign.BckjBizZxzxSer;
import com.zghzbckj.manage.dao.BckjBizZjzxDao;
import com.zghzbckj.manage.entity.BckjBizZjzx;
import com.zghzbckj.util.*;
import com.zghzbckj.vo.BckjBizYhxxVo;
import org.apache.ibatis.annotations.Update;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizZjzxService extends CrudService<BckjBizZjzxDao, BckjBizZjzx> {

    private static final Logger log = Logger.getLogger(BckjBizZjzxService.class);

    @Override
    public BckjBizZjzx get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizZjzx> findList(BckjBizZjzx bckjBizZjzx) {
        return super.findList(bckjBizZjzx);
    }

    @Override
    public PageInfo<BckjBizZjzx> findPage(Page<BckjBizZjzx> page, BckjBizZjzx bckjBizZjzx) {
        return super.findPage(page, bckjBizZjzx);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizZjzx bckjBizZjzx) {
        super.saveOrUpdate(bckjBizZjzx);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizZjzx bckjBizZjzx) {
        super.delete(bckjBizZjzx);
    }

    @Autowired
    BckjBizZxzxSer bckjbizzxzxSer;
    @Autowired
    BckjBizYhxxSer bckjbizyhxxSer;

    /**
     * <p>方法:findPagebckjBizZjzx TODO后台BckjBizZjzx分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizZjzx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizZjzx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizZjzx TODO保存BckjBizZjzx信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizZjzx(Map<String, Object> mapData) {
        BckjBizZjzx bckjBizZjzx = JsonUtil.map2Bean(mapData, BckjBizZjzx.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizZjzx bckjBizZjzxIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizZjzx, bckjBizZjzxIndata);
            bckjBizZjzx = bckjBizZjzxIndata;
        }
        saveOrUpdate(bckjBizZjzx);
        return ResponseMessage.sendOK(bckjBizZjzx);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizZjzx </p>
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
            BckjBizZjzx bckjBizZjzx = new BckjBizZjzx();
            bckjBizZjzx.setOwid(owid);
            this.dao.deleteByHyid(bckjBizZjzx);
            this.dao.deleteYhxx(owid);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public ResponseMessage supervisorList(Map<String, Object> dataMap) {
        Page<BckjBizZjzx> page = new Page(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        page.setList(this.dao.zjList(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    public ResponseMessage showStudentReplyList(Map<String, Object> dataMap) {
        //根据专家yhid得到owid
        BckjBizZjzx bckjBizZjzx = this.dao.getOneByCondition(dataMap);
        dataMap.put("zxzyid", bckjBizZjzx.getOwid());
        return bckjbizzxzxSer.getListByZxzyid(dataMap);
    }


    public ResponseMessage replyConsult(Map<String, Object> dataMap) {
        //根据专家yhid得到owid
        BckjBizZjzx bckjBizZjzx = this.dao.getOneByCondition(dataMap);
        dataMap.put("hfOwid", bckjBizZjzx.getYhid());
        dataMap.put("hfName", bckjBizZjzx.getZjxm());
        dataMap.put("zxzyid", bckjBizZjzx.getOwid());
        ResponseMessage responseMessage = bckjbizzxzxSer.replyConsult(dataMap);
        return responseMessage;
    }


    /**
     * <p>功能描述:后台录入专家信息</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author wangangaanng</li>
     * <li>@date 2019/9/25</li>
     * </ul>
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage recordInfo(String path) throws Exception {
        //文件路径
        String filename = path;
        List<BckjBizYhxxVo> resultYhxx = new ArrayList<>();
        List<BckjBizZjzx> resultZjzx = new ArrayList<>();
        ExcelUtils poi = new ExcelUtils();
        System.out.println("读取excel文件开始" + "===========" + System.currentTimeMillis());
        List<List<String>> list = poi.read(filename);
        System.out.println("读取excel文件完成" + "===========" + System.currentTimeMillis());
        if (list != null) {
            for (int i = 2; i < list.size(); i++) {//行循环

                HashMap<Object, Object> dataMap = Maps.newHashMap();
                List<String> cellList = list.get(i);
                String xm = cellList.get(0);//用户姓名
                xm = ExcelUtils.stmodifyExcelData(xm);
                dataMap.put("xm", xm);
                dataMap.put("zjxm", xm);//专家姓名
                String zjlxfs = cellList.get(1);//专家联系方式
                zjlxfs = ExcelUtils.stmodifyExcelData(zjlxfs);
                if (zjlxfs.length() != 11) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.SjHError);
                }
                dataMap.put("sjh", zjlxfs);
                dataMap.put("zjlxfs", zjlxfs);
                String zjzw = cellList.get(2);   //专家职  位
                zjzw = ExcelUtils.stmodifyExcelData(zjzw);
                dataMap.put("zjzw", zjzw);
                String xb = cellList.get(3);
                xb = ExcelUtils.stmodifyExcelData(xb);
                if (xb.indexOf("男") != -1) {   //专家性别
                    dataMap.put("xb", 1);
                } else if (xb.indexOf("女") != -1) {
                    dataMap.put("xb", 0);
                }
                String yx = cellList.get(4);//邮箱
                yx = ExcelUtils.stmodifyExcelData(yx);
                dataMap.put("yx", yx);
                String zjbgs = cellList.get(5);//办公地点
                zjbgs = ExcelUtils.stmodifyExcelData(zjbgs);
                dataMap.put("zjbgs", zjbgs);
                String szxy = cellList.get(6);//所在学院
                szxy = ExcelUtils.stmodifyExcelData(szxy);
                dataMap.put("szxy", szxy);
                String szzy = cellList.get(7);//所在专业
                szzy = ExcelUtils.stmodifyExcelData(szzy);
                dataMap.put("szzy", szzy);
                String yhDlzh = cellList.get(8);//登入账号
                yhDlzh = ExcelUtils.stmodifyExcelData(yhDlzh);
                dataMap.put("yhDlzh", yhDlzh);
                String yhDlmm = cellList.get(9); //登入密码
                yhDlmm = ExcelUtils.stmodifyExcelData(yhDlmm);
                dataMap.put("exp1", yhDlmm);
                yhDlmm = TextUtils.MD5(yhDlmm);//md5加密
                dataMap.put("yhDlmm", yhDlmm);

                BckjBizYhxxVo bckjBizYhxx = new BckjBizYhxxVo();
                BckjBizZjzx bckjBizZjzx = new BckjBizZjzx();

                dataMap.put("zjsfkyy", 0);// 是否可预约 0 可以 1 不可以

                dataMap.put("yhlx", 1);//类型 1学生或老师 2企业

                dataMap.put("state", 0);

                MapUtil.easySetByMap(dataMap, bckjBizYhxx);
                MapUtil.easySetByMap(dataMap, bckjBizZjzx);
                String owid = CustomSaveALL.preInsert(bckjBizYhxx);
                bckjBizZjzx.setYhid(owid);
                resultYhxx.add(bckjBizYhxx);
                resultZjzx.add(bckjBizZjzx);
            }

            ResponseMessage responseMessage = bckjbizyhxxSer.saveOrALL(resultYhxx);
            if (responseMessage == null || responseMessage.getBackCode() != 0 || responseMessage.getBean() == null) {
                throw new Exception(CommonConstant.ERROR_MESSAGE);
            }
            if (responseMessage.getBean().toString().indexOf("错误") != -1) {
                throw new Exception(CommonConstant.ERROR_MESSAGE);
            }
            saveOrUpdateAll(resultZjzx);
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    public ResponseMessage showInfoList(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Map<String, Object>> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        page.setList(this.dao.showInfoList(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    public ResponseMessage showInfoListQt(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        Page<Map<String, Object>> page = new Page(pageNo, pageSize);
        dataMap.put("page", page);
        List<Map<String, Object>> mapList = this.dao.showInfoListQt(dataMap);
        if(!TextUtils.isEmpty(mapList)&&mapList.size()>0){
            for (Map map:mapList){
                if(!TextUtils.isEmpty(map.get("exp4"))){
                        String zxfx = getDicVal2ByVal1(60001, map.get("exp4").toString());
                        map.put("exp4",zxfx);
                }
            }
        }
        page.setList(mapList);
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage saveConsultInfo(List<Map<String, Object>> components) {
        try {
            for (Map<String, Object> component : components) {
                //如果為刪除的記錄
                if (Integer.parseInt(component.get("updateFlag").toString()) == 2) {
                    ResponseMessage responseMessage = bckjbizyhxxSer.deleteInfo(JsonUtil.map2Bean(component, BckjBizYhxxVo.class));

                    if (responseMessage == null || responseMessage.getBackCode() != 0 || responseMessage.getBean() == null) {
                        throw new Exception(CommonConstant.ERROR_MESSAGE);
                    }
                    if (responseMessage.getBean().toString().indexOf("错误") != -1) {
                        throw new Exception(CommonConstant.ERROR_MESSAGE);
                    }
                    component.put("yhid", component.get("owid"));
                    component.remove("owid");
                    delete(JsonUtil.map2Bean(component, BckjBizZjzx.class));
                }
            }
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public ResponseMessage insertssInfo(Map<String, Object> dataMap) throws Exception {
        BckjBizYhxxVo bckjBizYhxx = new BckjBizYhxxVo();
        BckjBizZjzx bckjBizZjzx = new BckjBizZjzx();
        MapUtil.easySetByMap(dataMap, bckjBizZjzx);
        dataMap.remove("exp2");//办公司电话
        dataMap.remove("exp3");//部门
        dataMap.remove("exp4");//咨询方向
        MapUtil.easySetByMap(dataMap, bckjBizYhxx);
        if (ClassUtils.isAllFieldNull(bckjBizYhxx) && ClassUtils.isAllFieldNull(bckjBizZjzx)) {
            return ResponseMessage.sendOK("无保存内容");
        }
        //根据登入账号去寻找用户
        Map<String, String> oneByDlzh = getOneByDlzh(bckjBizYhxx.getYhDlzh());
        if (!TextUtils.isEmpty(oneByDlzh) && !TextUtils.isEmpty(dataMap.get("owid"))) {
            if (!oneByDlzh.get("owid").equals(dataMap.get("owid"))) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "存在相同的账号，无法保存");
            }
        }
        if (!TextUtils.isEmpty(oneByDlzh) && TextUtils.isEmpty(dataMap.get("owid"))) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "存在相同的账号，无法保存");
        }
        if (TextUtils.isEmpty(bckjBizYhxx.getOwid())) {
            bckjBizYhxx.setXm(bckjBizZjzx.getZjxm());
            bckjBizYhxx.setYhlx(1);
            bckjBizYhxx.setYhDlmm(com.zghzbckj.util.TextUtils.MD5(bckjBizZjzx.getExp1()));
            String owid = CustomSaveALL.preInsert(bckjBizYhxx);
            bckjBizZjzx.setYhid(owid);
            bckjbizyhxxSer.insertInfo(bckjBizYhxx);
            saveOrUpdate(bckjBizZjzx);
        } else {
            bckjBizYhxx.setOwid(oneByDlzh.get("yhid"));
            bckjBizYhxx.setYhDlmm(com.zghzbckj.util.TextUtils.MD5(bckjBizZjzx.getExp1()));
            bckjBizYhxx.setYhlx(1);
            ResponseMessage responseMessage = bckjbizyhxxSer.saveconInfo(bckjBizYhxx);
            if (responseMessage == null || responseMessage.getBackCode() != 0 || responseMessage.getBean() == null) {
                throw new Exception(CommonConstant.ERROR_MESSAGE);
            }
            if (responseMessage.getBean().toString().indexOf("错误") != -1) {
                throw new Exception(CommonConstant.ERROR_MESSAGE);
            }
            bckjBizZjzx.setYhid(bckjBizYhxx.getOwid());
            this.dao.updateBycondition(bckjBizZjzx);
        }
        return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    private Map<String, String> getOneByDlzh(String dlzh) {
        return this.dao.getOneByDlzh(dlzh);
    }

    public ResponseMessage getConsultsOne(Map<String, Object> dataMap) {
        Map<String, Object> consultsOne = this.dao.getConsultsOne(dataMap);
        if (!TextUtils.isEmpty(consultsOne)) {
            if (!TextUtils.isEmpty(consultsOne.get("exp4"))) {
                String zxfx = getDicVal2ByVal1(60001, consultsOne.get("exp4").toString());
                consultsOne.put("exp4", zxfx);
            }
        }
        Map<String, Object> consultsOne1 = this.dao.getConsultsOne(dataMap);
        consultsOne1.put("exp4", consultsOne.get("exp4"));
        return ResponseMessage.sendOK(consultsOne);
    }

    private String getDicVal2ByVal1(Integer type, String exp4) {
        return this.dao.getDicVal2ByVal1(type, exp4);
    }

    public Map getConsultsReplyDay() {
        Map<String, String> resMap = Maps.newHashMap();
        List<String> consultsReplyDay = this.dao.getConsultsReplyDay();
        if (consultsReplyDay.size() > 1) {
            resMap.put("hfts", consultsReplyDay.get(0));
        } else {
            if (TextUtils.isEmpty(resMap) || TextUtils.isEmpty(resMap.get(0)))
                resMap.put("hfts", "无设置");
        }
        return resMap;
    }


    public String filterContent(HashMap<String, Object> filterMap) {
        String content = filterMap.get("content").toString();
        List<String> filters = this.dao.getFilterKeys();
        StringBuffer sf = new StringBuffer();
        for (String str : filters) {
            if (content.indexOf(str) != -1) {
                sf.append(str + ",");
            }
        }
        return sf.toString();
    }
}