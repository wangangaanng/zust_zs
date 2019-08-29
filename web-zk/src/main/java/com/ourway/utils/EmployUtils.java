package com.ourway.utils;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jackson on 2018/4/25.
 */
public class EmployUtils {
    //根据用户empId查询用户信息
    private static final String DETAILEMPLOYURL = "/companyUserApi/detailEmployInfor";
    //根据项目单位账号查询出关联的系统emp账号
    private static final String DETAILEMPLOYBYUSERIDURL = "/companyUserApi/detailEmployInforByUserId";

    /**
     * <p>功能描述：isManage 判断当前用户是否为后台用户，如果是后台用户返回true，如果是申报端用户则返回false</p>
     * <ul>
     * <li>@param []</li>
     * <li>@return boolean</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 2018/4/25 16:22</li>
     * </ul>
     */
    public static boolean isManage() {
        EmployVO employs = ZKSessionUtils.getZkUser();
        if (null == employs || !"001".equals(employs.getEmpType())) {
            return true;
        }
        return false;
    }

    /**
     * <p>根据权限隐藏按钮</p>
     * <ul>
     * <li>@param window</li>
     * <li>@return boolean</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 2018/4/25 16:22</li>
     * </ul>
     */
    public static void hideBtn(BaseWindow window) {
        //详情页按钮\列表页按钮
        //后台项目信息修改按钮点亮
        String[] btns = {"saveBtn", "updateBtn", "commitBtn", "addBtn", "refreshBtn", "submitBtn", "removeBtn", "Supervisor"};
        for (String btn : btns) {
            BaseButton commonBtn = (BaseButton) window.getFellowIfAny("1_" + btn);
            if (null != commonBtn) {
                commonBtn.setDisabled(false);
            }
        }
//        String[] gridIds = {"mainButtonGrid", "buttonGrid", "1"};
//        String[] btns = {"saveBtn", "updateBtn", "commitBtn", "addBtn", "refreshBtn", "submitBtn", "removeBtn", "Supervisor"};
//        for (String gridId : gridIds) {
//            for (String btn : btns) {
//                BaseButton commonBtn = (BaseButton) window.getFellowIfAny(gridId + "_" + btn);
//                if (null != commonBtn) {
//                    commonBtn.setDisabled(true);
//                }
//            }
//        }

    }

    /**
    *<p>功能描述：isGovManage 判断用户是否为科室管理，包括财政主管、行业主管分旗县</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author My</li>
    *<li>@date 2019/5/26 18:04</li>
    *</ul>
    */
    public static boolean isGovManage() {
        EmployVO employs = ZKSessionUtils.getZkUser();
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(JsonUtil.toJson(employs), "/multiUserRelation/bindAccountList");
        if (TextUtils.isEmpty(responseMessage)) {
            return false;
        }
        if (responseMessage.getBackCode() != 0) {
            return false;
        }
        List<Map<String, Object>> result = (List<Map<String, Object>>) responseMessage.getBean();
        if (TextUtils.isEmpty(result) || result.size() <= 0) {
            return false;
        }
        return true;
//        EmployVO employs = ZKSessionUtils.getZkUser();
//        if (TextUtils.isEmpty(employs) || TextUtils.isEmpty(employs.getEmpType())) {
//            return false;
//        }
//        if (employs.getEmpType().equals("008") || employs.getEmpType().equals("009") || employs.getEmpType().equals("010")
//            || employs.getEmpType().equals("011") || employs.getEmpType().equals("012") || employs.getEmpType().equals("013")) {
//            return true;
//        }
//        return false;
    }

    /**
    *<p>功能描述：detailEmployById 根据empId查询用户信息</p>
    *<ul>
    *<li>@param [empId]</li>
    *<li>@return com.ourway.base.zk.models.EmployVO</li>
    *<li>@throws </li>
    *<li>@author My</li>
    *<li>@date 2019/5/26 20:26</li>
    *</ul>
    */
    public static EmployVO detailEmployById(String empId) {
        PublicData data = PublicData.instantce();
        data.setMethod(DETAILEMPLOYURL);
        Map<String, Object> params = new HashMap(1);
        params.put("empId", empId);
        data.setData(JsonUtil.toJson(params));
        String result = "";

        try {
            result = HttpUtils.loginPost(data, "UTF-8", false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0) {
                EmployVO employVO = (EmployVO) BeanUtil.map2Obj((Map)mess.getBean(), EmployVO.class);
                return employVO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
    *<p>功能描述：detailManageEmpByUserId 在项目申报端根据用户userId查询关联的后台账号employ</p>
    *<ul>
    *<li>@param [empId]</li>
    *<li>@return com.ourway.base.zk.models.EmployVO</li>
    *<li>@throws </li>
    *<li>@author My</li>
    *<li>@date 2019/5/26 20:27</li>
    *</ul>
    */
    public static EmployVO detailManageEmpByUserId(String userId) {
        PublicData data = PublicData.instantce();
        data.setMethod(DETAILEMPLOYBYUSERIDURL);
        Map<String, Object> params = new HashMap(1);
        params.put("userId", userId);
        data.setData(JsonUtil.toJson(params));
        String result = "";

        try {
            result = HttpUtils.loginPost(data, "UTF-8", false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0) {
                EmployVO employVO = (EmployVO) BeanUtil.map2Obj((Map)mess.getBean(), EmployVO.class);
                return employVO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
