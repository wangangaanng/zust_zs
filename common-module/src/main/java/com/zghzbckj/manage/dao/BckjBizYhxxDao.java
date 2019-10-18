/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizYhxx;

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

    Map<String, Object>  logIn(Map<String, Object> datamap);

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

    List<Map> getYhxxBmInfo(Map<String, Object> dataMap);
}