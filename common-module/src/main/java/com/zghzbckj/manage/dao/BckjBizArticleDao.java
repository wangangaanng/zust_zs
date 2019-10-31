/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizArticle;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizArticleDao extends CrudDao<BckjBizArticle> {


    List<BckjBizArticle> findMapByShort(Map<String, Object> mapData);

    List<BckjBizArticle> findMapByKey(Map<String, Object> mapData);
    //通过一级栏目获取二级栏目所有文章
    List<BckjBizArticle> findYjBylmbh(Map<String, Object> mapData);

}