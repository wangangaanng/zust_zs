/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.entity.BckjBizYhkz;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.vo.BckjBizStudentXxVo;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizYhxxDao extends CrudDao<BckjBizYhxx> {


    BckjBizYhxx getOneByCondition(Map<String, Object> datamap);

    Map<String, Object>  logIn(Map<String, Object> datamap);

    Integer modfiyPassword(Map<String, Object> datamap);

    void updateDlsj(String owid);

    BckjBizYhxx getOneByUnionId(String unionid);

    List<Object> showStudentInfoList(Map<String,Object> dataMap);

    List<Object> showTeatchInfoList(Map<String,Object> dataMap);


    List<String> getSfzList();

    List<String> getSjhList();

    BckjBizYhxx findBySjh(String sjh);

    BckjBizYhxx findBySfz(String sfz);


}