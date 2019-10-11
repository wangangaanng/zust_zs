/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizQyxx;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizQyxxDao extends CrudDao<BckjBizQyxx> {

    BckjBizQyxx getOne(Map params);

    BckjBizQyxx getExistOne(Map params);

    String queryDic(Map params);

    List queryDicList(Map params);
}