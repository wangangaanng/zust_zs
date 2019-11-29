/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJypm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizJypmDao extends CrudDao<BckjBizJypm> {

    Map<String, Object> statistic(Map<String, Object> dataMap);

    List<Map<String, Object>> collegeStats();

    List<Map<String, Object>> majorList(@Param("szxy") String szxy);

    BckjBizJypm getByMajor(@Param("collegeName") String collegeName, @Param("majorName") String majorName);

    void deleteAll();

    void deleteThisYear(@Param("nf") String nf);

    List<Map<String, Object>> sumPmrs();
}