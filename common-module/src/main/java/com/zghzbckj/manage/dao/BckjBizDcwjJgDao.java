/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizDcwjJg;
import org.apache.ibatis.annotations.Param;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizDcwjJgDao extends CrudDao<BckjBizDcwjJg> {

    int countPeople(@Param("dcwjRefOwid") String dcwjRefOwid);

    void deleteByDcwj(@Param("dcwjRefOwid") String dcwjRefOwid);
	
}