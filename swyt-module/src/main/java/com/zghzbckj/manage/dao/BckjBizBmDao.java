/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizBm;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-10-21
 */
@MyBatisDao
public interface BckjBizBmDao extends CrudDao<BckjBizBm> {

    BckjBizBm getOneByMap(Map<String, Object> mapData);

    Map getBmxx(Map<String, Object> mapData);

    List<Map> listDicByType(Map params);

    Integer queryWaitNumber(Map params);

    Integer todoMan(Map<String, Object> dataMap);
}