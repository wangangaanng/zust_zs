package com.zghzbckj.wechat.model;

import java.io.Serializable;

/**
 * <p>方法:微信中的菜单 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2018/2/13 23:08  </li>
 * </ul>
 */
public class WeChatMenu implements Serializable {
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}