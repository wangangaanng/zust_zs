package com.zghzbckj.manage.web;

import com.zghzbckj.manage.service.BckjBizJyDyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bckjBizJyDyInfo")
public class BckjBizJyDyInfoController {
    @Autowired
    BckjBizJyDyInfoService bckjBizJyDyInfoService;

}
