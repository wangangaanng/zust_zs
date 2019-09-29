/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizZxzx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizZxzxDao extends CrudDao<BckjBizZxzx> {

    List<Map<String,Object>> findListByZxlx(Map<String, Object> dataMap);

    List<Map<String,Object>>  findlyList(Map<String, Object> dataMap);

    BckjBizZxzx getOneByCondition(Map<String, Object> dataMap);

    void verify(HashMap<Object, Object> sendMap);
}