/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizYhkz;
import com.zghzbckj.manage.entity.BckjBizYhxx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizYhkzDao extends CrudDao<BckjBizYhkz> {

    BckjBizYhkz getOneByYhRefOwid(HashMap<String, Object> map);

    List<String> getXsxhList();

    void updateBycondition(Map<String,Object> dataMap);

    void deletConditionByMap(Map<String, Object> component);

    BckjBizYhkz getByXsxh(String xsxh);

    void updateInfo(BckjBizYhkz bckjBizYhkz);

    void updateByXsxh(BckjBizYhkz bckjBizYhkz);

    void deleteByXsxh(String Xsxh);

    void updateJyscheme(BckjBizYhkz bckjBizYhkz);

    void updateSyscheme(BckjBizYhkz bckjBizYhkz);
}