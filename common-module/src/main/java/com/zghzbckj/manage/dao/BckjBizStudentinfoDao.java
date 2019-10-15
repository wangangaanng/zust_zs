package com.zghzbckj.manage.dao;


import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizStudentinfo;


/**
 * 学生信息录入类DAO接口
 * @author wangangaanng
 * @version 2019-10-10
 */
@MyBatisDao
public interface BckjBizStudentinfoDao extends CrudDao<BckjBizStudentinfo> {

    void updateByXsxh(BckjBizStudentinfo bckjBizStudentinfo);

    void deleteByXsxh(String xsxh);

    void updataInfo(BckjBizStudentinfo bckjBizStudentinfo);

    void updateSyscheme(BckjBizStudentinfo bckjBizStudentinfo);
}