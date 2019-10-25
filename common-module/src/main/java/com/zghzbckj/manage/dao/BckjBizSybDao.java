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

    void updataInfo(BckjBizSyb bckjBizSyb);

    void updateByXsxh(BckjBizSyb bckjBizSyb);

    void deleteByXsxh(String xh);

    void updateJyscheme(BckjBizSyb bckjBizSyb);

    List<Object> getSybList(Map<String, Object> dataMap);

    BckjBizSyb getOne(String owid);

    Map<String,Object> getBynfByXsxh(Map<String,Object> dataMap);


    List<Map<String, Object>> getByTypeSort(Map<String, Object> dataMap);

    void deleteBySfz(String sfz);




    Map<String, Object> getBynfBySfz(Map<String, Object> map);

    BckjBizSyb getOneByXsxh(String xsxh);

    BckjBizSyb getOneBySfz(String sfz);

    void updateBySfz(BckjBizSyb bckjBizSyb);

    BckjBizSyb getOneQt(String owid);
}