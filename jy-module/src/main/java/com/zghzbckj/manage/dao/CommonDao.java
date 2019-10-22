package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJob;
import com.zghzbckj.manage.entity.BckjBizJyscheme;
import com.zghzbckj.manage.entity.BckjBizQyxx;
import com.zghzbckj.manage.entity.BckjBizSyb;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonDao</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@MyBatisDao
public interface CommonDao {

    List<Map<String, Object>> getListJob(Map<String, Object> dataMap);

    List<Map<String, Object>> getListCompany(Map<String, Object> dataMap);

    List<Map<String, Object>> getListCompanyNumber(Map<String, Object> dataMap);

    List<Integer> getCompanyNum(@Param("groupBy") String  groupBy);

    List<Map<String, Object>> getListJobNumber(Map<String, Object> dataMap);

    Map<String, Object> getOneDic(Map<String, Object> dataMap);

    List<Map<String, Object>> listDicVal(Map<String, Object> dataMap);

    Map<String, Object> getEmploys(Map<String, Object> param);

    String queryZjRole(Map<String, Object> param);

    Integer getEmploysNum(Map<String, Object> cxmap);

    void saveOrUpdateFile(Map<String, Object> param);

    Map<String, Object> getDeptInfo(Map<String, Object> deptMap);

    int saveDicTree(Map<String, Object> dataMap);

    Map<String, Object> updateDicTree(Map<String, Object> dataMap);

    List<Map<String, Object>> getConfig();

}
