package com.zghzbckj.manage.web;

import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.manage.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@RestController
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    CommonService commonService;

    @PostMapping(value = "test")
    public ResponseMessage test(){
        return ResponseMessage.sendOK("OK");
    }

    @PostMapping(value = "lntjPieChart")
    @ResponseBody
    public ResponseMessage getLntj(PublicDataVO dataVO) {
        try {
            Map<String, Object> result = commonService.getLntjPie();
            return ResponseMessage.sendOK(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    @PostMapping(value = "zsjhPieChart")
    @ResponseBody
    public ResponseMessage getZsjh(PublicDataVO dataVO) {
        try {
            return ResponseMessage.sendOK("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

}