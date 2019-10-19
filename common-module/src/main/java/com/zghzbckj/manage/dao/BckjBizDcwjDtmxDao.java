/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizDcwjDtmx;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizDcwjDtmxDao extends CrudDao<BckjBizDcwjDtmx> {

    List<BckjBizDcwjDtmx> listDtmx(@Param("dcwjjgRefOwid") String dcwjjgRefOwid);

    Integer countDtmx(String dcwjtmRefOwid, char wjda);

    List<BckjBizDcwjDtmx> listDtmxByDcwj(@Param("dcwjRefOwid") String dcwjRefOwid);

}