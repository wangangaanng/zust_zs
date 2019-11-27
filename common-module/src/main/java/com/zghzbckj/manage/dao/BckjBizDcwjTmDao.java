/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizDcwjTm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizDcwjTmDao extends CrudDao<BckjBizDcwjTm> {

    Map<String, Object> getOption(@Param("owid") String owid);

    List<Map<String, Object>> listQuestion(@Param("dcwjRefOwid") String dcwjRefOwid);

    List<BckjBizDcwjTm> listDcwjTm(@Param("dcwjRefOwid") String dcwjRefOwid);

    void deleteByDcwj(@Param("dcwjRefOwid") String dcwjRefOwid);

}