package com.zghzbckj.manage.web;

import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "test")
    public ResponseMessage test(){
        return ResponseMessage.sendOK("OK");
    }
}