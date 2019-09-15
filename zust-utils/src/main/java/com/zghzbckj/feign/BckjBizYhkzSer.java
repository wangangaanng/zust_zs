package com.zghzbckj.feign;

import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component("bckjbizyhkzSer")
@FeignClient(value = "zustCommonModule")
public interface BckjBizYhkzSer {
    /**
     *<p>功能描述:根据yhrefowid查找角色</p >
     *<ul>
     *<li>@param [request, response]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/12</li>
     *</ul>
     */
    @RequestMapping(value = "/bckjBizYhkz/getOneByYhRefOwid",method = RequestMethod.POST)
    ResponseMessage getOneByOwid(@RequestParam("yhRefOwid") String yhRefOwid);
}
