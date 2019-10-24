package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;

import com.zghzbckj.manage.entity.BckjBizStudentExpand;
@MyBatisDao
public interface BckjBizStudentExpandDao extends CrudDao<BckjBizStudentExpand> {
    void deleteBySfz(String sfz);

    void deleteByName(String xsxh);
}
