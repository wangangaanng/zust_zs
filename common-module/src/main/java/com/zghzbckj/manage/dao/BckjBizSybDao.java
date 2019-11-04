/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.manage.entity.BckjBizYhxx;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-20
 */
@MyBatisDao
public interface BckjBizSybDao extends CrudDao<BckjBizSyb> {


    BckjBizSyb getOne(String owid);



    List<Map<String, Object>> getByTypeSort(Map<String, Object> dataMap);

    void deleteBySfz(String sfz);

    Map<String, Object> getBynfBySfz(Map<String, Object> map);

    BckjBizSyb getOneByXsxh(String xsxh);

    BckjBizSyb getOneBySfz(String sfz);

    void updateBySfz(BckjBizSyb bckjBizSyb);

    BckjBizSyb getOneQt(String owid);

    List<BckjBizSyb> getListByXsxh(String xsxh);

    List<Map> getOldSybs();

    List<Map> getSmallRoutine(Map<String, Object> dataMap);
}