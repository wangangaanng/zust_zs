/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;


import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJyscheme;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 就业方案DAO接口
 * @author wangangaanng
 * @version 2019-09-30
 */
@MyBatisDao
public interface BckjBizJyschemeDao extends CrudDao<BckjBizJyscheme> {

    Map<String,Object> getJyBaseInfo(Map<String, Object> dataMap);

    String getXsxhByOwid(String owid);


    BckjBizJyscheme getJyschemeByMap(Map<String, Object> sendMap);

    void insertDicVal1(HashMap<Object, Object> sendMap1);

    void insertDicVal2(HashMap<Object, Object> sendMap2);

    List<Object> showJyInfoList(Map<String, Object> dataMap);


    void deleteByXsxh(String xsxh);

    List<String> getDicVal(@Param("type")int type ,@Param("val")String val);

    List<String> getDicVall(@Param("type") int type, @Param("val1")String val1);

    List<String> getDicListByType(int type);

    BckjBizJyscheme getOneByXsxh(String xsxh);

    BckjBizJyscheme getOneJyscheme(Map<String, Object> dataMap);

    BckjBizJyscheme getOneByYhRefOwid(Map<String, Object> dataMap);

    List<Map> getOldJyscheme();


    List<Map> getDicListMapByType(int type);

    List<BckjBizJyscheme> getJyselfInfo(Map<String, Object> dataMap);

    List<Map<String, Object>> jobOtherGetByCode(String bdzbh);

    List<Map> getJyDynamicInfo(Map<String, Object> dataMap);

    List<Map> getxyDynamicInfo(Map<String, Object> dataMap);

    List<Map> getzyDynamicInfo(Map<String, Object> dataMap);

    Map<String, Object> getJyDyNf();
}