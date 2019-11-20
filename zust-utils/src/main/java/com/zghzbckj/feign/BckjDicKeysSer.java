package com.zghzbckj.feign;

import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "zustCommonModule")
@Component(value = "bckjDicKeysSer")
public interface BckjDicKeysSer {
    @RequestMapping(value = "/bckjDicKeys/jyKeyFilter", method = RequestMethod.POST)
    ResponseMessage jyKeyFilter(@RequestBody Map<String,Object> keyMap);
}
