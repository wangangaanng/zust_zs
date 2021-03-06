/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.entity.BckjBizZjzx;
import com.zghzbckj.vo.BckjBizYhxxVo;

import java.util.List;
import java.util.Map;

/**
 * ccDAO接口
 * @author cc
 * @version 2019-09-09
 */
@MyBatisDao
public interface BckjBizZjzxDao extends CrudDao<BckjBizZjzx> {

    List<BckjBizZjzx> zjList(Map<String, Object> dataMap);

    BckjBizZjzx  getOneByCondition(Object owid);

    List<Map<String, Object>> showInfoList(Map<String, Object> dataMap);

    List<Map<String, Object>> showInfoListQt(Map<String, Object> dataMap);


    void updateBycondition(BckjBizZjzx bckjBizZjzx);

    Map<String,Object> getConsultsOne(Map<String, Object> dataMap);

    void deleteByHyid(BckjBizZjzx bckjBizZjzx);

    Map getOneByDlzh(String dlzh);

    void deleteYhxx(String owid);

   List<String>  getConsultsReplyDay();

    String getDicVal2ByVal1(Integer type , String exp4);

    List<String> getFilterKeys();

    List<Map> getZxfx();
}