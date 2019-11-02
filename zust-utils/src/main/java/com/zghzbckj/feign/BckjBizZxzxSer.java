package com.zghzbckj.feign;

import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Map;

@FeignClient(value = "zustCommonModule")
@Component(value = "bckjbizzxzxSer")
public interface BckjBizZxzxSer {
        /**
         *<p>功能描述:根据专家owid查询此学生咨询此专家的所有信息</p >
         *<ul>
         *<li>@param [request, response]</li>
         *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
         *<li>@throws </li>
         *<li>@author wangangaanng</li>
         *<li>@date 2019/9/11</li>
         *</ul>
         */
        @RequestMapping(value = "/bckjBizZxzx/getListByZxzyid",method = RequestMethod.POST)
        ResponseMessage getListByZxzyid(@RequestBody Map<String ,Object > dataMap);
    /**
     *<p>功能描述:专家回复学生的咨询</p >
     *<ul>
     *<li>@param [request, response]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/11</li>bckjBizZxzx/replyConsult
     *</ul>
     */
    @RequestMapping(value = "/bckjBizZxzx/replyConsult",method = RequestMethod.POST)
    ResponseMessage replyConsult(@RequestBody Map<String ,Object> dataMap);


}
