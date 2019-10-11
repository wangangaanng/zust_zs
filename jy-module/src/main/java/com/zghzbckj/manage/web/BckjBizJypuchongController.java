package com.zghzbckj.manage.web;

import com.ourway.base.utils.JsonUtil;
import com.zghzbckj.common.CommonConstant;

import com.zghzbckj.manage.service.BckjBizJypuchongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 就业方案补充类Controller
 * @author wangangaanng
 * @version 2019-10-10
 */
@RestController
@RequestMapping(value = "bckjBizJypuchong")
public class BckjBizJypuchongController extends BaseController{

    private Logger log = LoggerFactory.getLogger(BckjBizJypuchongController.class);
	@Autowired
	private BckjBizJypuchongService bckjBizJypuchongService;



}