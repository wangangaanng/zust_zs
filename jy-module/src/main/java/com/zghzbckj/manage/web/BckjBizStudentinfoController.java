package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.zghzbckj.common.CommonConstant;

import com.zghzbckj.manage.service.BckjBizStudentinfoService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


/**
 * 学生信息录入类Controller
 * @author wangangaanng
 * @version 2019-10-10
 */
@RestController
@RequestMapping(value = "bckjBizStudentinfo")
public class BckjBizStudentinfoController extends BaseController{

    private Logger log = LoggerFactory.getLogger(BckjBizStudentinfoController.class);
	@Autowired
	private BckjBizStudentinfoService bckjBizStudentinfoService;



}