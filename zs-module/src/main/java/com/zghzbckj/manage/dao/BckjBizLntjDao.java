/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizLntj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizLntjDao extends CrudDao<BckjBizLntj> {

    List<BckjBizLntj> findListByNf(@Param("nf") String nf);

    List<BckjBizLntj> findListBySf(@Param("sf")String sf);

    List<BckjBizLntj> findListByKl(@Param("kl") String kl);

    List<BckjBizLntj> findListByPc(@Param("pc")String pc);

    List<BckjBizLntj> findListByZy(@Param("zy")String zy);

}