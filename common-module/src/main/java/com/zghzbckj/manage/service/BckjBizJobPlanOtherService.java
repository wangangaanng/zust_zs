package com.zghzbckj.manage.service;

import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizJobPlanOtherDao;
import com.zghzbckj.manage.dao.BckjBizStudentExpandDao;
import com.zghzbckj.manage.entity.BckjBizJobPlanOther;
import com.zghzbckj.manage.entity.BckjBizStudentExpand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
public class BckjBizJobPlanOtherService extends CrudService<BckjBizJobPlanOtherDao, BckjBizJobPlanOther> {
    public void deleteByName(String xsxh) {
        this.dao.deleteByName(xsxh);
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void deleteBdzhByName(Map map) {
        this.dao.deleteBdzhByName(map);
    }

    public String getOneByXsxhCode(String name, String code) {
        return this.dao.getOneByXsxhCode(name,code);
    }
@Transactional(readOnly = false,rollbackFor = Exception.class)
    public void deleteOneByOwid(Object owid) {
        this.dao.deleteOneByOwid(owid);
    }
}
