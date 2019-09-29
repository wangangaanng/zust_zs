package com.ourway.apply;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseTab;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.LoginUtil;
import com.ourway.base.zk.utils.data.MenusDataUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/24.
 */
public class ManageMainAction extends com.ourway.base.zk.main.MainAction {
    
    private String currCity = "";//退出的通用页面路径
    @Override
    public void initFirstPage() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if (null != employVO) {
            if (!TextUtils.isEmpty(employVO.getEmpIndex())) {
                openFunByPageCa(I18nUtil.getLabelContent(ERCode.INDEX_PAGE), employVO.getEmpIndex());
            }
            showUserInfor(employVO);
        }
        ZKSessionUtils.setGridDefaultLeft(250);
    }


    @Override
    public void onCreate(CreateEvent event) {
        //根据用户类型判断是否出现账号切换按钮
        initTabPopup();
        resourceTabs = (Tabs) this.getFellowIfAny("resourceTabs");
        resourceTabpanels = (Tabpanels) this
                .getFellowIfAny("resourceTabpanels");
        resources = (Tabbox) this.getFellowIfAny("resources");
//        initSystemTitle();
        listAllWaitTask();
        initJsCookie();
//        initWebSocket();
        initMenus();
        initFirstPage();
    }
//    @Override
//    public void initSystemTitle() {
//        BaseLabel systemTitle = (BaseLabel) this.getFellowIfAny("systemTitle");
//        systemTitle.setValue("浙江科技学院招就处网站后台管理系统");
//
//    }

    private void hideOrShowSwitchButton() {
        A a = (A) getFellowIfAny("switchBtn");
        if (isManage()){
            a.setVisible(true);
        }
    }

    private boolean isManage() {
        EmployVO employs = ZKSessionUtils.getZkUser();
        if (TextUtils.isEmpty(employs) || TextUtils.isEmpty(employs.getEmpType())) {
            return false;
        }
        if (employs.getEmpType().equals("008") || employs.getEmpType().equals("009") || employs.getEmpType().equals("010")
            || employs.getEmpType().equals("011") || employs.getEmpType().equals("012") || employs.getEmpType().equals("013")) {
            return true;
        }
        return false;
    }


    @Override
    public void initMenus() {
        if (null == ZKSessionUtils.getZkUser()) {
            AlterDialog.alert("您无权操作本系统或者您已经登录过去，请登录");
            if (ZKConstants.LOGOUT_URL.indexOf("?") >= 0) {
                Executions.sendRedirect(ZKConstants.LOGOUT_URL);
            } else {
                Executions.sendRedirect(ZKConstants.LOGOUT_URL + "?" + System.currentTimeMillis());
            }
        } else {
            addMenuItemsSub();
        }
    }

    public void addMenuItemsSub() {
        int _menuIndex = 0;//若index是0 ，默认打开
//        navbar.setHeight((ZKSessionUtils.getScreenHeight()-240)+"px");
        List<TreeVO> menus = MenusDataUtil.getMenus();
        if (null == menus || menus.size() <= 0) {
            return;
        }
        Tabs menuGroupTabs = (Tabs) getFellowIfAny("menuGroupTabs");
        Tabpanels menuGroupTabPanels = (Tabpanels) getFellowIfAny("menuGroupTabPanels");
        menuGroupTabPanels.setWidth("170px");
        for (TreeVO menu : menus) {
            //tab级别的菜单，系统的一级菜单
            BaseTab tab = new BaseTab();
            tab.setSclass("tabLi");
            tab.setLabel(menu.getName());
            tab.setParent(menuGroupTabs);
            Tabpanel tbpanel = new Tabpanel();
            tbpanel.setParent(menuGroupTabPanels);
            Navbar navbar = new Navbar();
            navbar.setParent(tbpanel);
            navbar.setMold("default");
            navbar.setSclass("nav-list");
            navbar.setOrient("vertical");
            navbar.setStyle("overflow-y:auto");
            //一级菜单设置结束
            _menuIndex = 0;
            //只是单个的navitem
            if (null == menu.getSubTrees() || menu.getSubTrees().size() <= 0) {
                Navitem item = (Navitem) getFellowIfAny("navItem" + menu.getOwid());
                if (null == item) {
                    item = createNewNavItem(menu, navbar);
                }
                continue;
            }
            //处理二级

            for (TreeVO treeVO1 : menu.getSubTrees()) {
                //第一级菜单，如果是第一个，则要默认打开
                if (null == treeVO1.getSubTrees() || treeVO1.getSubTrees().size() <= 0) {
                    Navitem item = (Navitem) getFellowIfAny("navItem" + treeVO1.getOwid());
                    if (null == item) {
                        item = createNewNavItem(treeVO1, navbar);
                    }
                    continue;
                }

                //表示还有下一级
                Nav nav01 = (Nav) this.getFellowIfAny("nav" + treeVO1.getOwid());
                if (null == nav01) {
                    nav01 = createNewNav(treeVO1, navbar);
                }
                if (_menuIndex == 0) {
                    nav01.setOpen(true);
                }
                _menuIndex++;
                for (TreeVO treeVO2 : treeVO1.getSubTrees()) {
                    //第二级菜单
                    if (null == treeVO2.getSubTrees() || treeVO2.getSubTrees().size() <= 0) {
                        Navitem item = (Navitem) getFellowIfAny("navItem" + treeVO2.getOwid());
                        if (null == item) {
                            item = createNewNavItem(treeVO2, nav01);
                        }
                        continue;
                    }

                    Nav nav02 = (Nav) this.getFellowIfAny("subNav" + treeVO2.getOwid());
                    if (null == nav02) {
                        nav02 = createNewNav(treeVO2, nav01);
                    }

                    for (TreeVO treeVO3 : treeVO2.getSubTrees()) {

                        if (null == treeVO3.getSubTrees() || treeVO3.getSubTrees().size() <= 0) {
                            Navitem item = (Navitem) getFellowIfAny("navItem" + treeVO3.getOwid());
                            if (null == item) {
                                item = createNewNavItem(treeVO3, nav02);
                            }
                            continue;
                        }
                        Nav nav03 = (Nav) this.getFellowIfAny("subNav" + treeVO3.getOwid());
                        if (null == nav03) {
                            nav03 = createNewNav(treeVO3, nav02);
                        }

                        for (TreeVO treeVO4 : treeVO3.getSubTrees()) {

                            if (null == treeVO4.getSubTrees() || treeVO4.getSubTrees().size() <= 0) {
                                Navitem item = (Navitem) getFellowIfAny("navItem" + treeVO4.getOwid());
                                if (null == item) {
                                    item = createNewNavItem(treeVO4, nav03);
                                }
                                continue;
                            }
                            Nav item = (Nav) getFellowIfAny("navItem" + treeVO4.getOwid());
                            if (null == item) {
                                item = createNewNavItemNoClick(treeVO4, nav03);
                            }
                            Menupopup menupopup = new Menupopup();
                            menupopup.setParent(this);
                            item.setPopup(menupopup);
                            for (TreeVO treeVO5 : treeVO4.getSubTrees()) {
                                Menuitem _item = createMenuItem(treeVO5, item);
                                _item.setParent(menupopup);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void logout() {
        LoginUtil.logOut();
        ZKSessionUtils.clearUser();
        if (ZKConstants.LOGOUT_URL.indexOf("?") >= 0) {
            Executions.sendRedirect(ZKConstants.LOGOUT_URL);
        } else {
            Executions.sendRedirect(ZKConstants.LOGOUT_URL+"?" + System.currentTimeMillis());
        }
    }
}
