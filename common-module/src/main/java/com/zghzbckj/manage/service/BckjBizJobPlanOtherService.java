package com.zghzbckj.manage.service;

import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizStudentExpandDao;
import com.zghzbckj.manage.entity.BckjBizStudentExpand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BckjBizJobPlanOtherService extends CrudService<BckjBizStudentExpandDao, BckjBizStudentExpand> {
}
