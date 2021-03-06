/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizLqxs;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizLqxsDao extends CrudDao<BckjBizLqxs> {

    List<Map>  getOldLqxs();

    String getDicVal1(Integer type, String val2);

    List<String> findListByLqzy(Map<String, Object> dataMap);

    List<Map> getListBoxLinkage(Map<String, Object> dataMap);
}