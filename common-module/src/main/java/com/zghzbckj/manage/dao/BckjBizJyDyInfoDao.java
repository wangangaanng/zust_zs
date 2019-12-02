package com.zghzbckj.manage.dao;

import com.zghzbckj.base.dao.CrudDao;
import com.zghzbckj.base.dao.MyBatisDao;
import com.zghzbckj.manage.entity.BckjBizJyDyInfo;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BckjBizJyDyInfoDao extends CrudDao<BckjBizJyDyInfo> {

    void deleteOneByXsxyNfXlcc(Map<String, Object> dataMap);

    BckjBizJyDyInfo getOngByXsxyNfXlcc(Map<String, Object> dataMap);

    List<BckjBizJyDyInfo> getNfListByNfXlcc(Map dataMap);

    BckjBizJyDyInfo getOneByOwid(String owid);

}
