package com.ourway.syszk.login;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseLabel;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.LoginUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listitem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 LoginAction : <p>
 * <p>说明:用户登录和权限处理</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/23 14:02
 * </pre>
 */
public class LoginAction extends BaseWindow {
    String scence = "";//二维码登录时候定义的全局编号

    /**
     * <p>方法:check 用户登录校验 </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/3 22:44  </li>
     * </ul>
     */
    public void check() {
        bindAll2Ppt(true);
        EmployVO _vo = ZKSessionUtils.getZkUser();
        if (null == _vo) {
            //进行登录判断
            if (TextUtils.isEmpty(ppt.get("language"))) {
                ppt.put("language", "chn");
            }
            _vo = LoginUtil.login(ppt);
            if (null == _vo) {
                AlterDialog.alert("用户名或者密码错误，登录不成功");
                return;
            } else {
                ZKSessionUtils.setZkUser(_vo);
                //调用成功
                Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());
            }
        } else {
            //已经登录过，不需要重复调用接口
            Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());

        }

    }

    void checkLogin(String jsessionId, String cityId) {
        EmployVO _vo = LoginUtil.loginWithSessionId(jsessionId);
        if (null == _vo) {
            AlterDialog.alert("用户名或者密码错误，登录不成功");
            return;
        } else {
            ZKSessionUtils.setZkUser(_vo);
//            doHandleSystemName(cityId);
            _vo.setRmpOffice(cityId);
            String contextPath = ((HttpServletRequest) Executions.getCurrent().getNativeRequest()).getContextPath();
//            logger.info("contextPath:"+contextPath);
            //调用成功

                // Clients.evalJavaScript("openUrl('" + contextPath + "/applications/index.do?" + System.currentTimeMillis() + "&cityId=" + cityId + "')");
                Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis() + "&cityId=" + cityId);
            }
//               Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());

    }

    private void doHandleSystemName(String cityId) {
        String systemName = "";
        PublicData data = PublicData.instantce();
        data.setMethod("/systemApi/systemName");
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("cityId", cityId);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                Map<String, Object> dataMap = (Map<String, Object>) mess.getBean();
                if (null != dataMap) {
                    systemName = dataMap.get("dicVal2").toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ZKSessionUtils.setSystemName(systemName);
    }

    //设置界面的宽度和高度
    public void onClientInfo(ClientInfoEvent evt) {
        ZKSessionUtils.setScreenHeight(evt.getDesktopHeight());
        ZKSessionUtils.setScreenWidth(evt.getScreenWidth());
    }

    private void initLanguage() {
        List<Map<String, Object>> datas = DicUtil.listDic(9, "b.dicVal1");
        BaseListbox language = (BaseListbox) getFellowIfAny("loginWin_language");
        language.getChildren().clear();
        int index = 0;
        for (Map<String, Object> data : datas) {
            Listitem item = new Listitem();
            item.setLabel(data.get("dicVal2").toString());
            item.setValue(data.get("dicVal1").toString());
            if (index == 0) {
                item.setSelected(true);
            }
            index++;
            item.setParent(language);
        }
    }

    @Override
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        String cityId = "";
        HttpSession session = request().getSession();
        if (!TextUtils.isEmpty(request().getParameter("cityId"))) {
            session.setAttribute("cityId", request().getParameter("cityId"));
            doHandleSystemName(request().getParameter("cityId"));
            BaseLabel label = (BaseLabel) getFellowIfAny("systemTitle");
            label.setValue(ZKSessionUtils.getSystemName() + "");
        }
        if (!TextUtils.isEmpty(request().getParameter("jsessionId"))) {
            checkLogin(request().getParameter("jsessionId"), request().getParameter("cityId"));
        } else {
            EmployVO employVO = ZKSessionUtils.getZkUser();
            if (!TextUtils.isEmpty(session.getAttribute("cityId"))) {
                cityId = session.getAttribute("cityId").toString();
            }
            if (TextUtils.isEmpty(employVO)) {
                if (TextUtils.isEmpty(cityId)) {
                    Executions.sendRedirect("/index.htm");
                } else {
                    Executions.sendRedirect("/login.htm?cityId=" + cityId);
                }
            } else {
                employVO.setRmpOffice(cityId);
                ZKSessionUtils.setZkUser(employVO);
                if (employVO.getEmpType().equalsIgnoreCase("001")) {
                    Executions.sendRedirect("/applications/qyIndex.do?" + System.currentTimeMillis());
                } else {
                    Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());
                }
            }
        }
//        System.out.println(request().getParameter("jsessionId"));
//
//        if (!TextUtils.isEmpty(request().getParameter("jsessionId"))) {
//            checkLogin(request().getParameter("jsessionId"));
//        } else {
////            Executions.sendRedirect("/html/login.html?" + System.currentTimeMillis());
////            initQrcode();
//        }
    }

    //初始化，生成二维码
    private void initQrcode() {
        PublicData data = PublicData.instantce();
        data.setMethod("/sysWechatAdminApi/generateQrcode");
        Map<String, Object> ppt = new HashMap<String, Object>(1);
        ppt.put("appId", "wx8fe85b5b1df0805b");
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null != mess && mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                Map<String, Object> dataMap = (Map<String, Object>) mess.getBean();
                if (null != dataMap) {
                    //生成二维码，显示在界面上，二维码3小时过期
                    scence = dataMap.get("scence").toString();
                    String url = dataMap.get("url").toString();
//                    System.out.println(dataMap.get("scence"));
//                    System.out.println(dataMap.get("url"));
                    String path = QRCodeUtil.encode(url, ZKConstants.REPORT_IMG_PATH, ZKConstants.SYSTEM_FILE_PATH, scence + ".jpg", true);
                    if (!TextUtils.isEmpty(path)) {
                        //为了调用前端网页
//                        Image ermImage = (Image) getFellowIfAny("ermImage");
//                        ermImage.setSrc(ZKConstants.SYSTEM_FILE_URL + scence + ".jpg");
                        //发起websocket的链接，并保持心跳
//                        Clients.evalJavaScript("displayImg('" + ZKConstants.SYSTEM_FILE_URL + scence + ".jpg')");
//                        initWebSocket(scence);
                    }
                }
            } else {
                logger.info("生成二維碼錯誤" + mess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initWebSocket(String scenceId) {
        Clients.evalJavaScript("connectionWithLogin('" + ZKConstants.WEBSOCKET_URL + "','" + ZKConstants.WEBSOCKET_SOCKET_URL + "'," + scenceId + ")");
    }

    //扫二维码返回
    public void invokeByJs() {
        EmployVO _vo = null;
        if (null == _vo) {
            //进行登录判断
            _vo = LoginUtil.loginWithScanCode(scence);
            if (null == _vo) {

                return;
            } else {
                ZKSessionUtils.setZkUser(_vo);
                //调用成功
                //调用成功
                if ("001".equalsIgnoreCase(_vo.getEmpType())) {
                    Clients.evalJavaScript("openUrl('../applications/qyIndex.do?" + System.currentTimeMillis() + "')");
                }// Executions.sendRedirect("/applications/qyIndex.do?" + System.currentTimeMillis());
                else {
                    Clients.evalJavaScript("openUrl('../applications/index.do?" + System.currentTimeMillis() + "')");
                }
//                Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());
//                if (_vo.getEmpType().equalsIgnoreCase("001"))
//                    Executions.sendRedirect("/applications/qyIndex.do?" + System.currentTimeMillis());
//                else
//                    Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());
////                Executions.sendRedirect("/applications/index.do?" + System.currentTimeMillis());
            }
        }

    }

}
