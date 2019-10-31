/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJbxx;

import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-10-21
 */
@MyBatisDao
public interface BckjBizJbxxDao extends CrudDao<BckjBizJbxx> {

    BckjBizJbxx findOneByMap(Map<String, Object> mapData);

    BckjBizJbxx findStateByMap(Map<String, Object> mapData);

    BckjBizJbxx findByUser(Map<String, Object> mapData);

    int updatePsw(Map params);
}