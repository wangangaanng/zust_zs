package com.zghzbckj.manage.service;

import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizJobPlanOtherDao;
import com.zghzbckj.manage.dao.BckjBizJyDyInfoDao;
import com.zghzbckj.manage.entity.BckjBizJobPlanOther;
import com.zghzbckj.manage.entity.BckjBizJyDyInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class BckjBizJyDyInfoService extends CrudService<BckjBizJyDyInfoDao, BckjBizJyDyInfo> {

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void deleteOneByXsxyNfXlcc(Map<String, Object> dataMap) {
        this.dao.getOngByXsxyNfXlcc(dataMap);
    }

    public BckjBizJyDyInfo getOngByXsxyNfXlcc(Map<String,Object> dataMap){
        return this.dao.getOngByXsxyNfXlcc(dataMap);
    }


    public List<BckjBizJyDyInfo> getNfListByNfXlcc(Map dataMap) {
        return this.dao.getNfListByNfXlcc(dataMap);
    }

    public BckjBizJyDyInfo getOneByOwid(String owid) {
        return this.dao.getOneByOwid(owid);
    }
}
