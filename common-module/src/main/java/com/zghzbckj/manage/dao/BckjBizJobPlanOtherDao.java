package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJobPlanOther;

@MyBatisDao
public interface BckjBizJobPlanOtherDao extends CrudDao<BckjBizJobPlanOther> {
    void deleteByName(String xsxh);
}
