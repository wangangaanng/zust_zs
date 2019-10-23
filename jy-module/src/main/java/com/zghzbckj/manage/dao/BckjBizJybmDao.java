/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJybm;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizJybmDao extends CrudDao<BckjBizJybm> {

    BckjBizJybm getOneByJobHy(Map<String, Object> datamap);

    BckjBizJybm getOneByParam(Map params);

    List findListByMapXjh(Map<String, Object> paramsMap);

    Integer countNumber(Map params);
}