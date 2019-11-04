package com.ourway.syszk.login;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.ZKSessionUtils;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Iframe;

/**
 * <p>方法 LoginAction : <p>
 * <p>说明:用户登录和权限处理</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/23 14:02
 * </pre>
 */
public class OpenPdfAction extends BaseWindow {


    @Override
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        String url = ZKSessionUtils.getLaskLink().toString();
        Iframe iframePage = (Iframe) this.getFellowIfAny("iframePage");
//        iframePage.setStyle("width:100%;border:0px;height:98%;");
//        iframePage.setSrc("https://testapi.fadada.com:8443/api//viewdocs.action?app_id=400392&send_app_id=null&v=2.0&timestamp=20171017154909&transaction_id=2A57052DCD484C2384D15C4C057EFD5A&msg_digest=MDc2RjUyMDlFNkI2NTY5MkM5NEM0QUFBMjhBOEJCRDhBNzdFRUYyNg==");
        iframePage.setSrc(url);
    }


}
