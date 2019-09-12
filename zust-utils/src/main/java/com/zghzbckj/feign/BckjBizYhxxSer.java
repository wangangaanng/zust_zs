package com.zghzbckj.feign;




import com.zghzbckj.vo.BckjBizYhxxVo;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.SunHints;


/**
 * <dl>
 * <dt>BckjBizYhxxSer</dt>
 * <dd>CreateDate: 2019/9/11</dd>
 * </dl>
 * @author wangangaanng
 */
@Component
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
    BckjBizYhxxVo getOneByOwid(@RequestParam("owid") String owid);
}
