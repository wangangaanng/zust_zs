/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizQyxx;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 *
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizJobDao extends CrudDao<BckjBizJob> {
    List<BckjBizJob> findListByMapFirst(Map<String, Object> dataMap);

    List findListByMapWithCompany(Map<String, Object> paramsMap);


    List<BckjBizJob> findQdList(Map<String, Object> dataMap);

    List<BckjBizJob> getCdList(Map<String, Object> dataMap);

    void setCdPoint(Map<String, Object> dataMap);

    void lowerJob(Map params);

    BckjBizJob getByName(String zwbt);

    List<BckjBizJob> getAllBmInfo();

    BckjBizJob queryByOwid(String owid);

    List<BckjBizQyxx> findMess();


//    List<BckjBizJob> myJobList(Map<String, Object> dataMap);
}