package com.zghzbckj.web.controll;

import com.google.common.collect.Maps;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p>说明:三位一体招生网</p>
 * <pre>
 * @author xiayuwei
 * @date 2019/11/4 20:54
 * </pre>
 */
@Controller
public class SwytController {
    private static final Logger log = Logger.getLogger(SwytController.class);
    @ModelAttribute
    public void setConfig(Model model) {
        model.addAttribute("imagePath", ApiConstants.imagePath);
        model.addAttribute("localUrl", ApiConstants.localUrl);
        model.addAttribute("uploadUrl", ApiConstants.uploadUrl);
    }
   // 三位一体框首页容器
   @RequestMapping(value = "/trinityEnrollment/{pageType}", method = RequestMethod.GET)
   public ModelAndView newsList(HttpServletRequest request,ModelAndView view, @PathVariable String pageType,@CookieValue(value = "swOwid",required = false) String swOwid) throws UnsupportedEncodingException {
        //pageType跳转到的页面
       view.addObject("page",pageType);

       //测试用户owid:swOwid = "1b47f10b042a4f2b877a47d107fda132";
       String applyOwid = "";//表明表id
       //判断是否有个人owid 没有说明没登陆
       if(StringUtils.isEmpty(swOwid)){
           view.setViewName("trinitylogin");
       }else{

           //获取学校信息
           Map param = Maps.newHashMap();
           param.put("yhRefOwid",swOwid);
           param.put("pageNo", "0");
           param.put("pageSize", "20");
           ResponseMessage resultMess  = new ResponseMessage();
           PublicData _data = UnionHttpUtils.manageParam(param, "zustswyt/bckjBizXxpz/getXxxx");
           resultMess = UnionHttpUtils.doPosts(_data);
           if(!StringUtils.isEmpty(resultMess.getBean())) {
               Map<String, Object> records = (Map<String, Object>) resultMess.getBean();
               //单条学院数据
               List<Map<String, Object>> List = (List<Map<String, Object>>) records.get("list");
               //申请表owid 未申请没有改字段
               if(!StringUtils.isEmpty(List.get(0).get("applyOwid"))){
                   applyOwid = List.get(0).get("applyOwid").toString();
                   view.addObject("applyOwid",applyOwid);
                   //报名进行到的状态
                   view.addObject("processState",List.get(0).get("bmState"));
               }else{
                   view.addObject("applyOwid","");
               }
           }

           //获取所有的报名进程所有信息
           Map param2 = Maps.newHashMap();
           param2.put("applyOwid",applyOwid);
           ResponseMessage resultMess2  = new ResponseMessage();
           PublicData _data2 = UnionHttpUtils.manageParam(param2, "zustswyt/bckjBizBm/getResult");
           resultMess2 = UnionHttpUtils.doPosts(_data2);
           if(!StringUtils.isEmpty(resultMess2.getBean())) {
               Map<String, Object> records2 = (Map<String, Object>) resultMess2.getBean();
               view.addObject("bmbZp",records2.get("bmbZp"));//报名表签字zp
               view.addObject("cnszp",records2.get("cnszp"));//承诺书签字zp
               view.addObject("email",records2.get("yx"));//邮箱
               view.addObject("payTime",records2.get("jfsj"));//缴费时间
               view.addObject("payProveImg",records2.get("jfpzZp"));//缴费凭证zp
               view.addObject("nameStu",records2.get("xm"));//姓名
               view.addObject("subjectType",records2.get("xklb"));//学科类别
               view.addObject("languageType",records2.get("yzmc"));//外语语种
               view.addObject("examType",records2.get("bklb"));//报考类别
               view.addObject("major",records2.get("xzzymc"));//招生专业
               view.addObject("examNum",records2.get("zkzh"));//准考证号
               view.addObject("faceTime",records2.get("mssj"));//面试时间
               view.addObject("writeScore",records2.get("bscj"));//笔试时间
               view.addObject("faceScore",records2.get("mscj"));//面试时间
               view.addObject("finalScore",records2.get("zzcj"));//最终成绩
           }
           //获取学生基本信息
           Map param5 = Maps.newHashMap();
           param5.put("yhRefOwid",swOwid);
           ResponseMessage resultMess5  = new ResponseMessage();
           PublicData _data5 = UnionHttpUtils.manageParam(param, "zustswyt/bckjBizJbxx/getInfo");
           resultMess5 = UnionHttpUtils.doPosts(_data5);
           if(!StringUtils.isEmpty(resultMess5.getBean())) {
               Map<String, Object> records5 = (Map<String, Object>) resultMess5.getBean();
               view.addObject("nameStu",records5.get("xm"));//姓名
           }

           //不同页面不同初始化
           switch (pageType){
               case "2"://获取报名表和承诺书签字
                   break;
               case "3"://缴费
                   //获取缴费信息
                   Map param4 = Maps.newHashMap();
                   param4.put("dicType","10025");
                   ResponseMessage resultMess4  = new ResponseMessage();
                   PublicData _data4 = UnionHttpUtils.manageParam(param4, "zustcommon/common/getByType");
                   resultMess4 = UnionHttpUtils.doPosts(_data4);
//                   if(StringUtils.isEmpty(resultMess4)) {
//                      return view;
//                   }
                   List<Map<String, Object>> records4 = (List<Map<String, Object>>) resultMess4.getBean();
                   if(!StringUtils.isEmpty(records4)) {
                       view.addObject("payMess",records4.get(0).get("dicVal2"));
                       view.addObject("payUrl",records4.get(0).get("dicVal3"));
                   }
                   break;
               default://报名表或者面试通知单打印 1或5
                   String curType = pageType;
                   Map param3 = Maps.newHashMap();
                   param3.put("applyOwid",applyOwid);
                   ResponseMessage resultMess3  = new ResponseMessage();
                   if(curType.equals("1")){//报名表
                       PublicData _data3 = UnionHttpUtils.manageParam(param3, "zustswyt/bckjBizBm/getApply");
                       resultMess3 = UnionHttpUtils.doPosts(_data3);
                   }
                   if(curType.equals("5")){//面试通知单
                       PublicData _data3 = UnionHttpUtils.manageParam(param3, "zustswyt/bckjBizBm/notice");
                       resultMess3 = UnionHttpUtils.doPosts(_data3);
                   }
                   if(!StringUtils.isEmpty(resultMess3.getBean())) {
                       view.addObject("filePath",resultMess3.getBean());
                   }

           }

           //去首页：总容器页面
           view.setViewName("trinityEnrollment");
       }
       return view;
   }
    @RequestMapping(value = "trinitylogin", method = RequestMethod.GET)
    public ModelAndView SWlogin(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWlogin");
        return view;
    }

    @RequestMapping(value = "trinityRegister", method = RequestMethod.GET)
    public ModelAndView SWregistered(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWregistered");
        return view;
    }

    @RequestMapping(value = "trinityPsw", method = RequestMethod.GET)
    public ModelAndView SWYTpassword(HttpServletRequest request,ModelAndView view) {
        view.setViewName("SWpassword");
        return view;
    }
}
