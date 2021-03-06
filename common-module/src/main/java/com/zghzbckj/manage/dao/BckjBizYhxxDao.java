/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.entity.BckjBizYhxx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizYhxxDao extends CrudDao<BckjBizYhxx> {


    BckjBizYhxx getOneByCondition(Map<String, Object> datamap);

    Map<String, Object> logIn(Map<String, Object> datamap);

    Integer modfiyPassword(Map<String, Object> datamap);

    void updateDlsj(String owid);

    BckjBizYhxx getOneByUnionId(String unionid);

    List<Object> showStudentInfoList(Map<String,Object> dataMap);


    Map<String,Object> showStudentInfo(Map<String, Object> dataMap);

    void deleteAllList(Map<String, Object> dataMap);

    void updateInfo(BckjBizYhxx bckjBizYhxx);

    void updateByXsxh(BckjBizYhxx bckjBizYhxx);

    void updateJyscheme(BckjBizYhxx bckjBizYhxx);

    void updateSyscheme(BckjBizYhxx bckjBizYhxx);

    Map<String, Object> queryDocument(Map<String, Object> dataMap);

    List<Map> getYhxxGzInfo(Map<String, Object> dataMap);

    String getYhxxGzSum(Map<String, Object> dataMap);



    String getYhxxQdSum(Map<String, Object> dataMap);

    List<Map> getYhxxQdInfo(Map<String, Object> dataMap);

    List<Map> getYhxxBmInfo(Map<String, Object> dataMap);

    String getYhxxBmSum(Map<String, Object> dataMap);

    BckjBizYhxx getOneBySfz(String sfz);

    void deleteBySfz(String sfz);


    Map<String, Object> logInBySfz(Map<String, Object> datamap);

    void updateBySfz(BckjBizYhxx bckjBizYhxx);

    String getYhxxQdSuccessSum(Map<String, Object> dataMap);

    String getYhxxQdNoSuccessSum(Map<String, Object> dataMap);

    void deleteByOwid(String owid);

    Map<String,Object> logInByteach(Map<String, Object> datamap);

    BckjBizYhxx getZsBySjh(Map<String,Object> dataMap);

    List<BckjBizYhxx> getZsList(Map<String, Object> dataMap);

    List<Map> getCaOpDayDateList(Map<String, Object> dataMap);

    void updateDlzh(Map<String, Object> xxMap);

    List<Map> getXchBm(Map<String, Object> dataMap);
    String getXchBmSum(Map<String, Object> dataMap);

    Map<String,Object> getOneDicByOwid(String owid);

    void updateDicByMap(Map<String, Object> dicMap);


    List<Map> getQdList(Map<String, Object> dataMap);

    List<Map> getQd(Map<String, Object> dataMap);

    List<String> getCustomListXszy(Map<String, Object> dataMap);

    List<String> getCustomListXsxy(Map<String, Object> dataMap);

    List<String> getCustomListXsbj(Map<String, Object> dataMap);

    void deleteBaoMing(List<Map> deleteMaps);

    List<Map> getXchBaoMingList(Map<String, Object> dataMap);

    void deleteDicByOwid(String owid);

    void saveDic(Map<String, Object> map);

    void updateDicVal(Map<String, Object> map);

    void saveDicVal(Map<String, Object> map);

    List<Map> getWeiXinYhList(Map<Object, Object> dataMap);

    int getDicMaxOwid();

    String getOneByDicVal1(String dicVal1,int type);

    void updateDicByMap2(Map<String, Object> dicMap);
}

