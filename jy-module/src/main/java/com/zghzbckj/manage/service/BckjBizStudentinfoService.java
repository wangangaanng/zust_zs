package com.zghzbckj.manage.service;


import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizStudentinfoDao;
import com.zghzbckj.manage.entity.BckjBizStudentinfo;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 学生信息录入类Service
 * @author wangangaanng
 * @version 2019-10-10
 */
@Service
@Transactional(readOnly = true)
public class BckjBizStudentinfoService extends CrudService<BckjBizStudentinfoDao, BckjBizStudentinfo> {
	 private static final Logger log = LoggerFactory.getLogger(BckjBizStudentinfoService.class);





}