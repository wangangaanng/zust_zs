/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;


import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJyscheme;

import java.util.HashMap;
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

    BckjBizJyscheme getJyFzInfo(Map<String, Object> sendMap);

    BckjBizJyscheme getJyschemeByMap(Map<String, Object> sendMap);

    void insertDicVal1(HashMap<Object, Object> sendMap1);

    void insertDicVal2(HashMap<Object, Object> sendMap2);
}