package com.zghzbckj.feign;




import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.vo.BckjBizYhxxVo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * <dl>
 * <dt>BckjBizYhxxSer</dt>
 * <dd>CreateDate: 2019/9/11</dd>
 * </dl>
 * @author wangangaanng
 */
@Component("bckjbizyhxxSer")
@FeignClient(value = "zustCommonModule")
public interface BckjBizYhxxSer {
    /**
     *<p>功能描述:根据owid或学生学号</p >
     *<ul>
     *<li>@param [request, response]</li>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/11</li>
     *</ul>
     */
    @RequestMapping(value = "/bckjBizYhxx/getOneByOwid",method = RequestMethod.POST)
    ResponseMessage getOneByOwid(@RequestParam("owid") String owid);

    @RequestMapping(value = "/bckjBizYhxx/saveOrALL",method = RequestMethod.POST)
    ResponseMessage saveOrALL(@RequestBody List<BckjBizYhxxVo> lists);

    @RequestMapping(value = "/bckjBizYhxx/deleteInfo",method = RequestMethod.POST)
    ResponseMessage deleteInfo(@RequestBody  BckjBizYhxxVo bckjBizYhxxVo);

    @RequestMapping(value = "/bckjBizYhxx/saveconInfo",method = RequestMethod.POST)
    ResponseMessage saveconInfo(@RequestBody  BckjBizYhxxVo bckjBizYhxxVo);

    @RequestMapping(value = "/bckjBizYhxx/insertInfo",method = RequestMethod.POST)
    ResponseMessage insertInfo(@RequestBody  BckjBizYhxxVo bckjBizYhxxVo);

}
