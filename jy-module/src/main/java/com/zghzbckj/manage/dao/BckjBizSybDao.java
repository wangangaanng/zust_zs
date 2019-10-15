/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizSyb;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-20
 */
@MyBatisDao
public interface BckjBizSybDao extends CrudDao<BckjBizSyb> {
    BckjBizSyb findByXh(String xh);

    Map getUserXh(Map yhOwid);

    List<Object> getSybList(Map<String, Object> dataMap);
}