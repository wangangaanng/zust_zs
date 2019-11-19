/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizZsjh;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizZsjhDao extends CrudDao<BckjBizZsjh> {

    List<BckjBizZsjh> findListByNf(Map<String, Object> dataMap);

    List<BckjBizZsjh> findListBySf(Map<String, Object> dataMap);

    List<BckjBizZsjh> findListByKl(Map<String, Object> dataMap);

    List<BckjBizZsjh> findListByPc(Map<String, Object> dataMap);

    List<BckjBizZsjh> findListByZy(Map<String, Object> dataMap);

    List<Map<String,Object>> getDicListMap(Map<String, Object> dataMap);


    List<String> getCustomDicListXsxy(Map<String, Object> dataMap);

    List<String> getCustomDicListXsbj(Map<String, Object> dataMap);

    List<String> getCustomDicListXszy(Map<String, Object> dataMap);
}