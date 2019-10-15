package com.zghzbckj.manage.web;

import com.zghzbckj.base.web.BaseController;
import com.zghzbckj.manage.service.BckjBizJypuchongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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