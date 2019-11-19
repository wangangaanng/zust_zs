package com.zghzbckj.feign;

import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@FeignClient(value = "zustJYModule")
@Component(value = "bckjbizXsgzSer")
public interface BckjBizXsgzSer {
    @RequestMapping(value = "/bckjBizXsgz/save", method = RequestMethod.POST)
    ResponseMessage mapXsgzInfo(@RequestBody Map<String,Object> dataMap);
    @RequestMapping(value = "/bckjBizXsgz/getOneXchByYhRefOwid", method = RequestMethod.POST)
    ResponseMessage getOneXchByYhRefOwid(@RequestParam("yhRefOwid") String yhRefOwid,@RequestParam("owid") String owid);
}
