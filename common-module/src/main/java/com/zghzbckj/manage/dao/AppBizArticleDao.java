/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.AppBizArticle;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-04-13
 */
@MyBatisDao
public interface AppBizArticleDao extends CrudDao<AppBizArticle> {

    List<AppBizArticle> getOneByEjLmbh(Map<String, Object> mapData);

    List<AppBizArticle> findMapByShort(Map<String, Object> mapData);
}