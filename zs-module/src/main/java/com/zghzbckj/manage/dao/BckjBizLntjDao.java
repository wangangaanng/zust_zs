/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizLntj;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizLntjDao extends CrudDao<BckjBizLntj> {

    List<BckjBizLntj> findListByNf(Map<String, Object> dataMap);

    List<BckjBizLntj> findListBySf(Map<String, Object> dataMap);

    List<BckjBizLntj> findListByKl(Map<String, Object> dataMap);

    List<BckjBizLntj> findListByPc(Map<String, Object> dataMap);

    List<BckjBizLntj> findListByZy(Map<String, Object> dataMap);

}