/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.entity.BckjBizXsgz;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizXsgzDao extends CrudDao<BckjBizXsgz> {
    BckjBizXsgz getOneByJobYh(Map<String, Object> datamap);

    List<Map<String,Object>> studentSubcribeList(Map<String, Object> dataMap);
}