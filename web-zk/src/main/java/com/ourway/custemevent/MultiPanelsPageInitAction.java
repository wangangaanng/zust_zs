package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述：项目资金使用页面初始化之后执行方法</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author jackson</li>
 * <li>@date 2018/1/6 14:44</li>
 * </ul>
 */
public class MultiPanelsPageInitAction implements PageInitSer {

    @Override
    public void initPage(BaseWindow baseWindow, Map map, PageVO pageVO) {
        try {
            Map<String, Object> ppt = new HashMap(1);
            if (!TextUtils.isEmpty(map) && !TextUtils.isEmpty(map.get("owid"))) {
                ppt.put("owid", map.get("owid").toString());
//                baseWindow.setPpt(ppt);
            } else {
                //强制刷新管理，使进入后有管理项目库的数据
                map.put("owid", "-1");
            }
            baseWindow.setPpt(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
