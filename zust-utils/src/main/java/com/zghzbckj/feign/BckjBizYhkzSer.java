package com.zghzbckj.feign;

import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "zustCommonModule")
@Component(value = "bckjbizyhkzSer")
public interface BckjBizYhkzSer {

        /**
         *<p>功能描述:根据yhRefOwid查询用户信息</p >
         *<ul>
         *<li>@param [request, response]</li>
         *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
         *<li>@throws </li>
         *<li>@author wangangaanng</li>
         *<li>@date 2019/9/11</li>
         *</ul>
         */
        @RequestMapping(value = "/bckjBizYhkz/getOneByYhRefOwid",method = RequestMethod.POST)
        ResponseMessage getOneByYhRefOwid(@RequestParam("yhRefOwid") String yhRefOwid);

}
