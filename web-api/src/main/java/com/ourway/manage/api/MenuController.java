package com.ourway.manage.api;

import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.manage.service.CustomMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Controller
@RequestMapping("menuApi")
public class MenuController {


    @Autowired
    CustomMenuService customMenuService;

    @RequestMapping(value = "moduleMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage moduleMenu(HttpServletRequest request, HttpServletResponse response,
                                      PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "owid", "pageCode");
        if (!validateMsg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
        }

        Map<String, Object> menu = customMenuService.moduleMenu(mapData.get("owid").toString(), mapData.get("pageCode").toString());
        return ResponseMessage.sendOK(menu);
    }
}
