package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJobPlanOther;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@MyBatisDao
public interface BckjBizJobPlanOtherDao extends CrudDao<BckjBizJobPlanOther> {
    void deleteByName(String xsxh);

    void deleteBdzhByName(Map map);

    String getOneByXsxhCode(@Param("name") String name, @Param("code") String code);

    void deleteOneByOwid(Object owid);
}
