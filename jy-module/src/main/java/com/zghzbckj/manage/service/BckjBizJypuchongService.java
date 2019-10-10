package com.zghzbckj.manage.service;


import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizJypuchongDao;
import com.zghzbckj.manage.entity.BckjBizJypuchong;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;

import com.zghzbckj.base.model.ResponseMessage;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 就业方案补充类Service
 * @author wangangaanng
 * @version 2019-10-10
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJypuchongService extends CrudService<BckjBizJypuchongDao, BckjBizJypuchong> {
	 private static final Logger log = LoggerFactory.getLogger(BckjBizJypuchongService.class);




}