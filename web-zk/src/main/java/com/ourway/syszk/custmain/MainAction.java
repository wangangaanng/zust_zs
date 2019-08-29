package com.ourway.syszk.custmain;

/**
 * Created by ChenChao on 2018/5/15.
 */
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.models.MenuVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.template.MainTemplateAction;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.LoginUtil;
import com.ourway.base.zk.utils.data.MenusDataUtil;
import com.ourway.base.zk.utils.data.MessUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.*;

import java.util.*;

public class MainAction extends BaseWindow implements ComponentWindowSer {
    private static final long serialVersionUID = 3654985667382327935L;
    private Tabs resourceTabs;
    private Tabpanels resourceTabpanels;
    private Tabbox resources;
    private Menupopup tabRightPopup;

    public MainAction() {
    }

    @Override
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        this.initTabPopup();
        this.resourceTabs = (Tabs)this.getFellowIfAny("resourceTabs");
        this.resourceTabpanels = (Tabpanels)this.getFellowIfAny("resourceTabpanels");
        this.resources = (Tabbox)this.getFellowIfAny("resources");
        Hlayout hlt = (Hlayout)this.getFellowIfAny("main");
        this.initMenus();
//        this.listAllWaitTask();
        this.initJsCookie();
//        this.initWebSocket();
        this.initFirstPage();
    }


    

    private void initTabPopup() {
        this.tabRightPopup = new Menupopup();
        this.tabRightPopup.setParent(this);
        Menuitem menuitem = new Menuitem("关闭其他");
        menuitem.addEventListener("onClick", new MainAction.TabRightClick(1));
        menuitem.setParent(this.tabRightPopup);
        menuitem = new Menuitem("关闭所有");
        menuitem.addEventListener("onClick", new MainAction.TabRightClick(2));
        menuitem.setParent(this.tabRightPopup);
        menuitem = new Menuitem("关闭当前");
        menuitem.addEventListener("onClick", new MainAction.TabRightClick(3));
        menuitem.setParent(this.tabRightPopup);
    }

    public void onClientInfo(ClientInfoEvent evt) {
        ZKSessionUtils.setScreenHeight(evt.getDesktopHeight());
        ZKSessionUtils.setScreenWidth(evt.getDesktopWidth());
        this.changeAutoWidthHeight(ZKSessionUtils.getGridDefaultLeft());
    }

    private void changeAutoWidthHeight(int navBarWidth) {
        List _tabpanels = this.resourceTabpanels.getChildren();
        int width = ZKSessionUtils.getScreenWidth();
        int height = ZKSessionUtils.getScreenHeight();
        Iterator i$ = _tabpanels.iterator();

        while(true) {
            while(true) {
                Tabpanel tabpanel;
                do {
                    if(!i$.hasNext()) {
                        return;
                    }

                    tabpanel = (Tabpanel)i$.next();
                    tabpanel.setWidth(width - navBarWidth + "px");
                    tabpanel.setHeight(height - ZKConstants.GRID_DEFAULT_TOP + "px");
                } while(!(tabpanel.getFirstChild() instanceof BaseWindow));

                BaseWindow win = (BaseWindow)tabpanel.getFirstChild();
                if(win instanceof MainTemplateAction) {
                    MainTemplateAction collection1 = (MainTemplateAction)win;
                    ZKSessionUtils.setParentLeftWidth(navBarWidth);
                    collection1.changeAutoWidthHeight();
                } else {
                    win.setWidth(tabpanel.getWidth());
                    Collection collection = win.getFellows();
                    Iterator i$1 = collection.iterator();

                    while(i$1.hasNext()) {
                        Component component = (Component)i$1.next();
                        if(component instanceof BaseGrid) {
                            BaseGrid grid = (BaseGrid)component;
                            if(null != grid.getLayoutVO()) {
                                GridUtils.setDataGridProperty(grid, grid.getLayoutVO(), navBarWidth);
                            }
                        }
                    }
                }
            }
        }
    }

    private void initFirstPage() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if(null != employVO) {
            if(!TextUtils.isEmpty(employVO.getEmpIndex())) {
                this.openFunByPageCa(I18nUtil.getLabelContent(ERCode.INDEX_PAGE), employVO.getEmpIndex());
            }

            this.showUserInfor(employVO);
        }

    }

    private void showUserInfor(EmployVO employVO) {
        if(null != employVO) {
            try {
                Menu e = (Menu)this.getFellowIfAny("userInfo");
                if(!TextUtils.isEmpty(employVO.getEmpName())) {
                    if(employVO.getEmpName().length() > 5) {
                        e.setLabel(employVO.getEmpName().substring(0, 5) + "...");
                    } else {
                        e.setLabel(employVO.getEmpName());
                    }
                }

                e.setTooltiptext(employVO.getEmpName());
                if(!TextUtils.isEmpty(employVO.getEmpPic())) {
                    if(!employVO.getEmpPic().contains("http") && !employVO.getEmpPic().contains("HTTP")) {
                        e.setImage(ZKConstants.SYSTEM_FILE_URL + employVO.getEmpPic());
                    } else {
                        e.setImage(employVO.getEmpPic());
                    }
                }

                Menuitem updatePsw = (Menuitem)this.getFellowIfAny("updatePsw");
                Menuitem logoutOut = (Menuitem)this.getFellowIfAny("logoutOut");
                updatePsw.setLabel(I18nUtil.getLabelContent("sys.button.updatePsw"));
                logoutOut.setLabel(I18nUtil.getLabelContent("sys.button.outSystem"));
            } catch (Exception var5) {
                logger.error("write user Name is Error :" + var5.getMessage());
            }
        }

    }

    private void initJsCookie() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if(null != employVO) {
            Clients.evalJavaScript("zkSetCookie(\'" + ZKConstants.URL_HOST + "\',\'" + employVO.getEmpId() + "\',\'" + employVO.getEmpName() + "\',\'" + employVO.getLanguage() + "\',\'" + ZKConstants.APP_KEY + "\',\'" + ZKSessionUtils.getCookie() + "\')");
        }

    }

    private void initWebSocket() {
        EmployVO employVO = ZKSessionUtils.getZkUser();
        if(null != employVO) {
            Clients.evalJavaScript("connection(\'" + ZKConstants.WEBSOCKET_URL + "\',\'" + ZKConstants.WEBSOCKET_SOCKET_URL + "\',\'" + employVO.getEmpId() + "\')");
        }

    }

    public void invokeByJs() {
        BaseTextbox hiddenMessage = (BaseTextbox)this.getFellowIfAny("hiddenMessage");
        if(!TextUtils.isEmpty(hiddenMessage.getText())) {
            String content = hiddenMessage.getText();
            if(content.startsWith("alert")) {
                AlterDialog.alert(content.replaceAll("alert", ""));
            }

            if(content.startsWith("open")) {
                String pageCa = content.replaceAll("open", "");
                String title = pageCa.split("\\$")[0];
                String _pageCa = pageCa.split("\\$")[1];
                this.openFunByPageCa(title, _pageCa);
            }

            if(content.startsWith("newMess")) {
                this.listAllWaitTask();
            }

        }
    }

    public void clearTab() {
        this.resourceTabs.getChildren().clear();
        this.resourceTabpanels.getChildren().clear();
    }

    public void logout() {
        ZKSessionUtils.clearUser();
        LoginUtil.logOut();
        if(ZKConstants.LOGOUT_URL.indexOf("?") >= 0) {
            Executions.sendRedirect(ZKConstants.LOGOUT_URL);
        } else {
            Executions.sendRedirect(ZKConstants.LOGOUT_URL + "?" + System.currentTimeMillis());
        }

    }

    public void changePsw() {
        this.openFunByPageCa(I18nUtil.getLabelContent("sys.button.updatePsw"), ZKConstants.PAGECA_UPDATEPSW);
    }

    public void openClose() {
        Navbar navbar = (Navbar)this.getFellowIfAny("navbar");
        A toggler = (A)this.getFellowIfAny("toggler");
        Div sidebar = (Div)this.getFellowIfAny("sidebar");
        boolean width = false;
        if(navbar.isCollapsed()) {
            sidebar.setSclass("sidebar");
            sidebar.setWidth("194px");
            navbar.setCollapsed(false);
            toggler.setIconSclass("z-icon-angle-double-left");
            this.changeAutoWidthHeight(ZKSessionUtils.getGridDefaultLeft());
        } else {
            sidebar.setSclass("sidebar");
            sidebar.setWidth("48px");
            navbar.setCollapsed(true);
            toggler.setIconSclass("z-icon-angle-double-right");
            this.changeAutoWidthHeight(BaseConstants.NAV_MIN_LEN);
        }

    }

    private void initMenus() {
        if(null == ZKSessionUtils.getZkUser()) {
            AlterDialog.alert("您无权操作本系统或者您已经登录过去，请登录");
            if(ZKConstants.LOGOUT_URL.indexOf("?") >= 0) {
                Executions.sendRedirect(ZKConstants.LOGOUT_URL);
            } else {
                Executions.sendRedirect(ZKConstants.LOGOUT_URL + "?" + System.currentTimeMillis());
            }
        } else {
            this.addMenuItems();
        }

    }

    public Nav createNewNav(TreeVO menu, Component navbar) {
        Nav nav = new Nav();
        nav.setId("nav" + menu.getOwid());
        MenuVO menuVO = (MenuVO)menu.getBean();
        if("root".equalsIgnoreCase(ZKSessionUtils.getZkUser().getEmpId())) {
            nav.setLabel(menu.getName());
        } else if(!TextUtils.isEmpty(menuVO.getAliais())) {
            nav.setLabel(I18nUtil.getLabelContent(menuVO.getAliais()));
        } else {
            nav.setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
        }

        if(!TextUtils.isEmpty(menuVO.getIconClass())) {
            nav.setIconSclass(menuVO.getIconClass());
        }

        nav.setAttribute("menuBean", menu.getBean());
        nav.setParent(navbar);
        nav.addEventListener("onClick", this);
        return nav;
    }

    public Navitem createNewNavItem(TreeVO vo, Component navbar) {
        Navitem item = new Navitem();
        item.setId("navItem" + vo.getOwid());
        MenuVO menuVO = (MenuVO)vo.getBean();
        if("root".equalsIgnoreCase(ZKSessionUtils.getZkUser().getEmpId())) {
            item.setLabel(vo.getName());
        } else if(!TextUtils.isEmpty(menuVO.getAliais())) {
            item.setLabel(I18nUtil.getLabelContent(menuVO.getAliais()));
        } else {
            item.setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
        }

        if(!TextUtils.isEmpty(menuVO.getIconClass())) {
            item.setIconSclass(menuVO.getIconClass());
        }

        item.setParent(navbar);
        item.setAttribute("menuBean", vo.getBean());
        item.addEventListener("onClick", this);
        return item;
    }

    public Nav createNewNavItemNoClick(TreeVO vo, Component navbar) {
        Nav item = new Nav();
        item.setId("navItem" + vo.getOwid());
        MenuVO menuVO = (MenuVO)vo.getBean();
        if("root".equalsIgnoreCase(ZKSessionUtils.getZkUser().getEmpId())) {
            item.setLabel(vo.getName());
        } else if(!TextUtils.isEmpty(menuVO.getAliais())) {
            item.setLabel(I18nUtil.getLabelContent(menuVO.getAliais()));
        } else {
            item.setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
        }

        if(!TextUtils.isEmpty(menuVO.getIconClass())) {
            item.setIconSclass(menuVO.getIconClass());
        }

        item.setParent(navbar);
        item.setAttribute("menuBean", vo.getBean());
        return item;
    }

    public Menuitem createMenuItem(TreeVO vo, Component navbar) {
        Menuitem item = new Menuitem();
        item.setId("navItem" + vo.getOwid());
        MenuVO menuVO = (MenuVO)vo.getBean();
        if("root".equalsIgnoreCase(ZKSessionUtils.getZkUser().getEmpId())) {
            item.setLabel(vo.getName());
        } else if(!TextUtils.isEmpty(menuVO.getAliais())) {
            item.setLabel(I18nUtil.getLabelContent(menuVO.getAliais()));
        } else {
            item.setLabel(I18nUtil.getLabelContent(menuVO.getIcon()));
        }

        if(!TextUtils.isEmpty(menuVO.getIconClass())) {
            item.setIconSclass(menuVO.getIconClass());
        }

        item.setAttribute("menuBean", vo.getBean());
        item.addEventListener("onClick", this);
        return item;
    }

    public void addMenuItems() {
        int _menuIndex = 0;
        Navbar navbar = (Navbar)this.getFellowIfAny("navbar");
        List menus = MenusDataUtil.getMenus();
        if(null != menus && menus.size() > 0) {
            Iterator i$ = menus.iterator();

            label98:
            while(true) {
                TreeVO menu;
                Nav var17;
                do {
                    label65:
                    do {
                        while(i$.hasNext()) {
                            menu = (TreeVO)i$.next();
                            if(null != menu.getSubTrees() && menu.getSubTrees().size() > 0) {
                                var17 = (Nav)this.getFellowIfAny("nav" + menu.getOwid());
                                if(null == var17) {
                                    var17 = this.createNewNav(menu, navbar);
                                }

                                if(_menuIndex == 0) {
                                    var17.setOpen(true);
                                }

                                ++_menuIndex;
                                continue label65;
                            }

                            Navitem nav = (Navitem)this.getFellowIfAny("navItem" + menu.getOwid());
                            if(null == nav) {
                                this.createNewNavItem(menu, navbar);
                            }
                        }

                        return;
                    } while(null == menu.getSubTrees());
                } while(menu.getSubTrees().size() <= 0);

                Iterator i$1 = menu.getSubTrees().iterator();

                while(true) {
                    label78:
                    while(true) {
                        if(!i$1.hasNext()) {
                            continue label98;
                        }

                        TreeVO treeVO = (TreeVO)i$1.next();
                        if(null != treeVO.getSubTrees() && treeVO.getSubTrees().size() > 0) {
                            Nav var18 = (Nav)this.getFellowIfAny("subNav" + treeVO.getOwid());
                            if(null == var18) {
                                var18 = this.createNewNav(treeVO, var17);
                            }

                            Iterator i$2 = treeVO.getSubTrees().iterator();

                            while(true) {
                                while(true) {
                                    if(!i$2.hasNext()) {
                                        continue label78;
                                    }

                                    TreeVO vo = (TreeVO)i$2.next();
                                    if(null != vo.getSubTrees() && vo.getSubTrees().size() > 0) {
                                        Nav var19 = (Nav)this.getFellowIfAny("navItem" + vo.getOwid());
                                        if(null == var19) {
                                            var19 = this.createNewNavItemNoClick(vo, var18);
                                        }

                                        Menupopup menupopup = new Menupopup();
                                        menupopup.setParent(this);
                                        var19.setPopup(menupopup);
                                        Iterator i$3 = vo.getSubTrees().iterator();

                                        while(i$3.hasNext()) {
                                            TreeVO menu1 = (TreeVO)i$3.next();
                                            Menuitem _item = this.createMenuItem(menu1, var18);
                                            _item.setParent(menupopup);
                                        }
                                    } else {
                                        Navitem item1 = (Navitem)this.getFellowIfAny("navItem" + vo.getOwid());
                                        if(null == item1) {
                                            this.createNewNavItem(vo, var18);
                                        }
                                    }
                                }
                            }
                        } else {
                            Navitem item = (Navitem)this.getFellowIfAny("navItem" + treeVO.getOwid());
                            if(null == item) {
                                this.createNewNavItem(treeVO, var17);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        if(event.getTarget() instanceof Navitem) {
            Navitem item2 = (Navitem)event.getTarget();
            this.openFun(item2.getAttribute("menuBean"));
        } else if(event.getTarget() instanceof Menuitem) {
            Menuitem item1 = (Menuitem)event.getTarget();
            this.openFun(item1.getAttribute("menuBean"));
        } else if(event.getTarget() instanceof Nav) {
            Nav item = (Nav)event.getTarget();
            this.openFun(item.getAttribute("menuBean"));
        }
    }

    private BaseTab preparedTab(String title, int isClosable) {
        BaseTab tab = new BaseTab(title);
        if(0 == isClosable) {
            tab.setClosable(true);
        } else {
            tab.setClosable(false);
        }

        tab.setAttribute("title", title);
        tab.addEventListener("onRightClick", new MainAction.TabRight());
        List tbs = this.resourceTabs.getChildren();
        Iterator i$ = tbs.iterator();

        while(i$.hasNext()) {
            Object object = i$.next();
            if(object instanceof Tab) {
                BaseTab tb2 = (BaseTab)object;
                if(tb2.getAttribute("title").toString().equals(title)) {
                    this.resources.setSelectedTab(tb2);
                    return null;
                }
            }
        }

        tab.setId(TextUtils.getUUID());
        return tab;
    }

    private BaseTab newTab(String title) {
        int index = 0;
        String _tmp = "";
        List tbs = this.resourceTabs.getChildren();
        Iterator tab = tbs.iterator();

        while(tab.hasNext()) {
            Tab tb = (Tab)tab.next();
            if(tb.getLabel().startsWith(title + "-")) {
                _tmp = tb.getLabel().replaceAll(title + "-", "");
                if(Integer.parseInt(_tmp) > index) {
                    index = Integer.parseInt(_tmp);
                }
            }
        }

        BaseTab tab1 = new BaseTab(title + "-" + (index + 1));
        tab1.setClosable(true);
        tab1.setAttribute("title", tab1.getLabel());
        tab1.setId(TextUtils.getUUID());
        tab1.addEventListener("onRightClick", new MainAction.TabRight());
        return tab1;
    }

    private BaseTab newTab(String title, String tabId, boolean closeFlag) {
        int index = 0;
        String _tmp = "";
        List tbs = this.resourceTabs.getChildren();
        Iterator tab = tbs.iterator();

        while(tab.hasNext()) {
            BaseTab tb = (BaseTab)tab.next();
            if(tb.getLabel().startsWith(title + "-")) {
                _tmp = tb.getLabel().replaceAll(title + "-", "");
                if(Integer.parseInt(_tmp) > index) {
                    index = Integer.parseInt(_tmp);
                }
            }
        }

        BaseTab tab1 = new BaseTab(title + "-" + (index + 1));
        tab1.setClosable(closeFlag);
        tab1.setAttribute("title", tab1.getLabel());
        tab1.setId(tabId);
        tab1.addEventListener("onRightClick", new MainAction.TabRight());
        return tab1;
    }

    public void openFun(Object menuBean) {
        ZKSessionUtils.setLastLink(menuBean);
        if(null != menuBean) {
            MenuVO menuVO = (MenuVO)menuBean;
            if(TextUtils.isEmpty(menuVO.getIsshow())) {
                menuVO.setIsshow(Integer.valueOf(0));
            }

            BaseTab tab = this.preparedTab(menuVO.getName(), menuVO.getIsshow().intValue());
            if(null != tab) {
                this.resourceTabs.appendChild(tab);
                Tabpanel tabpanel = this.newTabPanel(tab);
                new HashMap();
                if(!TextUtils.isEmpty(menuVO.getLink())) {
                    Map params = this.prepareMenuItem(menuVO);
                    String link = menuVO.getLink();
                    if(link.indexOf("?") >= 0) {
                        link = link.split("\\?")[0].trim();
                    }

                    this.doCreateWindow(menuVO, link, tab, tabpanel, params);
                    this.resourceTabpanels.appendChild(tabpanel);
                    this.resources.setSelectedTab(tab);
                }

            }
        }
    }

    private void doCreateWindow(MenuVO menuVO, String link, BaseTab tab, Tabpanel tabpanel, Map<String, Object> params) {
        BaseWindow win = null;
        Component nowcomp = null;
        if(null != menuVO.getPageCustomer() && menuVO.getPageCustomer().intValue() == 2) {
            params.put("url", menuVO.getLink());
            nowcomp = Executions.createComponents("/sys/iframe/iframe.do", (Component)null, params);
        } else {
            nowcomp = Executions.createComponents(link, (Component)null, params);
        }

        win = (BaseWindow)nowcomp;
        win.setId("tabWin" + TextUtils.getUUID());
        win.setTabId(tab.getId() + "_window");
        int width = ZKSessionUtils.getScreenWidth() - ZKSessionUtils.getGridDefaultLeft();
        win.setStyle("width:" + width + "px;height:98%;overflow-y:auto !important");
        tabpanel.appendChild(win);
    }

    private void doCreateWindow(String link, BaseTab tab, Tabpanel tabpanel, Map<String, Object> params) {
        BaseWindow win = null;
        Component nowcomp = Executions.createComponents(link, (Component)null, params);
        win = (BaseWindow)nowcomp;
        win.setId("tabWin" + TextUtils.getUUID());
        win.setTabId(tab.getId() + "_window");
        int width = ZKSessionUtils.getScreenWidth() - ZKSessionUtils.getGridDefaultLeft();
        win.setStyle("width:" + width + "px;height:98%;overflow-y:auto !important");
        tabpanel.appendChild(win);
    }

    private Tabpanel newTabPanel(Tab tab) {
        Tabpanel tabpanel = new Tabpanel();
        tabpanel.setId(tab.getId() + "_panel");
        tabpanel.setSclass("z-tabpanel-index");
        return tabpanel;
    }

    public void openFunByPageCa(String tabName, String pageCa) {
        BaseTab tab = this.preparedTab(tabName, 0);
        if(null != tab) {
            this.resourceTabs.appendChild(tab);
            Tabpanel tabpanel = this.newTabPanel(tab);
            Map params = MessUtil.tranfLink(pageCa);
            if(pageCa.indexOf("?") >= 0) {
                String[] _pageCa = pageCa.split("\\?");
                pageCa = _pageCa[0];
                this.doHandleUrlParams(_pageCa[1], params);
            }

            String link = MessUtil.getLinkByPageCa(pageCa);
            params.put("pageCa", pageCa);
            params.put("pageType", Integer.valueOf(2));
            if(TextUtils.isEmpty(link)) {
                link = pageCa;
            }

            this.doCreateWindow(link, tab, tabpanel, params);
            this.resourceTabpanels.appendChild(tabpanel);
            this.resources.setSelectedTab(tab);
        }
    }

    public void openFunByPageCa(String tabName, String pageCa, int pageType) {
        BaseTab tab = this.preparedTab(tabName, 0);
        if(null != tab) {
            this.resourceTabs.appendChild(tab);
            Tabpanel tabpanel = this.newTabPanel(tab);
            Map params = MessUtil.tranfLink(pageCa);
            if(pageCa.indexOf("?") >= 0) {
                String[] _pageCa = pageCa.split("\\?");
                pageCa = _pageCa[0];
                this.doHandleUrlParams(_pageCa[1], params);
            }

            String link = MessUtil.getLinkByPageCa(pageCa);
            params.put("pageCa", pageCa);
            params.put("pageType", Integer.valueOf(pageType));
            if(TextUtils.isEmpty(link)) {
                link = pageCa;
            }

            this.doCreateWindow(link, tab, tabpanel, params);
            this.resourceTabpanels.appendChild(tabpanel);
            this.resources.setSelectedTab(tab);
        }
    }

    public void openFunByPageCaWithType(String tabName, String pageCa, int type) {
        BaseTab tab = this.preparedTab(tabName, 0);
        if(null != tab) {
            this.resourceTabs.appendChild(tab);
            Tabpanel tabpanel = this.newTabPanel(tab);
            Map params = MessUtil.tranfLink(pageCa);
            if(pageCa.indexOf("?") >= 0) {
                String[] _pageCa = pageCa.split("\\?");
                pageCa = _pageCa[0];
                this.doHandleUrlParams(_pageCa[1], params);
            }

            String link = MessUtil.getLinkByPageCa(pageCa);
            params.put("pageCa", pageCa);
            params.put("pageType", Integer.valueOf(type));
            if(TextUtils.isEmpty(link)) {
                link = pageCa;
            }

            this.doCreateWindow(link, tab, tabpanel, params);
            this.resourceTabpanels.appendChild(tabpanel);
            this.resources.setSelectedTab(tab);
        }
    }

    public void openConfigPageCa(String tabName, String pageCa) {
        BaseTab tab = this.preparedTab(tabName, 0);
        if(null != tab) {
            this.resourceTabs.appendChild(tab);
            Tabpanel tabpanel = this.newTabPanel(tab);
            Map params = MessUtil.tranfLink(pageCa);
            if(pageCa.indexOf("?") >= 0) {
                String[] _pageCa = pageCa.split("\\?");
                pageCa = _pageCa[0];
                this.doHandleUrlParams(_pageCa[1], params);
            }

            String link = MessUtil.getLinkByPageCa(pageCa);
            HashMap _params = new HashMap();
            _params.put("pageCa", pageCa);
            _params.put("pageType", Integer.valueOf(2));
            _params.put("ppt", params);
            if(TextUtils.isEmpty(link)) {
                link = pageCa;
            }

            this.doCreateWindow(link, tab, tabpanel, _params);
            this.resourceTabpanels.appendChild(tabpanel);
            this.resources.setSelectedTab(tab);
        }
    }

    private void doHandleUrlParams(String urlParams, Map<String, Object> params) {
        String[] cs = urlParams.split("\\&");
        String[] arr$ = cs;
        int len$ = cs.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String c = arr$[i$];
            if(!TextUtils.isEmpty(c)) {
                String[] _vals = c.split("\\=");
                params.put(_vals[0], _vals[1]);
            }
        }

    }

    @Override
    public String openNewTab(BaseWindow window, String title) {
        BaseTab tab = this.newTab(title);
        if(null == tab) {
            return "";
        } else {
            this.resourceTabs.appendChild(tab);
            Tabpanel tabpanel = this.newTabPanel(tab);
            window.setStyle("width:100%;height:100%;overflow-y:auto !important");
            tabpanel.appendChild(window);
            this.resourceTabpanels.appendChild(tabpanel);
            this.resources.setSelectedTab(tab);
            return tab.getId();
        }
    }

    @Override
    public void openNewTab(Map<String, Object> params, String tabCode, String tabApi) {
    }

    @Override
    public String openNewTab(BaseWindow window, String title, String tabId) {
        BaseTab tab = this.newTab(title, tabId, true);
        if(null == tab) {
            return "";
        } else {
            this.resourceTabs.appendChild(tab);
            Tabpanel tabpanel = this.newTabPanel(tab);
            window.setStyle("width:100%;height:100%;overflow-y:auto !important");
            tabpanel.appendChild(window);
            this.resourceTabpanels.appendChild(tabpanel);
            this.resources.setSelectedTab(tab);
            return tab.getId();
        }
    }

    @Override
    public void closeTab(String tabid) {
        BaseTab tab = (BaseTab)this.getFellowIfAny(tabid);
        tab.onClose();
        if(this.resourceTabs.getChildren().size() > 0) {
            this.resources.setSelectedIndex(this.resourceTabs.getChildren().size() - 1);
        }

    }

    @Override
    public void closeTabWin(BaseWindow window) {
        if(!TextUtils.isEmpty(window.getTabId())) {
            String tabid = window.getTabId().replaceAll("_window", "");
            BaseTab tab = (BaseTab)this.getFellowIfAny(tabid);
            Tabpanel tabpanel = (Tabpanel)this.getFellowIfAny(tabid + "_panel");
            tab.onClose();
            BaseTab paranentTab = this.getParentTabByWindow(window.getTopWindow());
            if(null == paranentTab) {
                if(this.resourceTabs.getChildren().size() > 0) {
                    this.resources.setSelectedIndex(this.resourceTabs.getChildren().size() - 1);
                }
            } else {
                this.resources.setSelectedTab(paranentTab);
            }

        }
    }

    public BaseTab getParentTabByWindow(BaseWindow window) {
        if(null == window) {
            return null;
        } else if(TextUtils.isEmpty(window.getTabId())) {
            return null;
        } else {
            String tabid = window.getTabId().replaceAll("_window", "");
            BaseTab tab = (BaseTab)this.getFellowIfAny(tabid);
            return null == tab?this.getParentTabByWindow(window.getTopWindow()):tab;
        }
    }

    public void closeOneTab(String tabid) {
        BaseTab tab = (BaseTab)this.getFellowIfAny(tabid);
        Tabpanel tabpanel = (Tabpanel)this.getFellowIfAny(tabid + "_panel");
        this.resourceTabpanels.removeChild(tabpanel);
        this.resourceTabs.removeChild(tab);
    }

    public BaseTab getParentTab(List<String> tabids, int index) {
        String tabid = (String)tabids.get(index);
        BaseTab tab = (BaseTab)this.getFellowIfAny(tabid);
        if(null == tab) {
            --index;
            if(index < 0) {
                return null;
            } else {
                --index;
                return this.getParentTab(tabids, index);
            }
        } else {
            return tab;
        }
    }

    private Map<String, Object> prepareMenuItem(MenuVO menuVO) {
        if(TextUtils.isEmpty(menuVO.getLink())) {
            return null;
        } else {
            String link = menuVO.getLink();
            String pageCa = menuVO.getPageCa();
            HashMap menuParams = new HashMap();
            if(pageCa.indexOf("?") >= 0) {
                String[] _pageCa = pageCa.split("\\?");
                pageCa = _pageCa[0];
            }

            menuParams.put("pageCa", pageCa);
            menuParams.put("pageType", menuVO.getPageType());
            if(link.contains("?")) {
                link = link.split("\\?")[1];
                String[] params = link.split("\\&");
                String[] arr$ = params;
                int len$ = params.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String param = arr$[i$];
                    String[] _pt = param.split("\\=");
                    menuParams.put(_pt[0], _pt[1]);
                }
            }

            return menuParams;
        }
    }

    public void listAllWaitTask() {
        String title = "";
        String content = "";
        Map objs = MessUtil.listWaitTask();
        BaseLabel anotiNumLabel = (BaseLabel)this.getFellowIfAny("anotiNumLabel");
        A notippLabel = (A)this.getFellowIfAny("notippLabel");
        Vlayout taskList = (Vlayout)this.getFellowIfAny("taskList");
        taskList.getChildren().clear();
        if(null != objs && null != objs.get("records")) {
            anotiNumLabel.setValue(objs.get("totalCount") + "");
            notippLabel.setLabel(objs.get("totalCount") + I18nUtil.getLabelContent("public.sys.task.waitTaskTip"));
            Iterator i$ = ((List)objs.get("records")).iterator();

            while(i$.hasNext()) {
                Map obj = (Map)i$.next();
                A a = new A();
                a.addEventListener("onClick", new MainAction.AClick(obj));
                a.setIconSclass("btn btn-xs no-hover btn-pink z-icon-comment");
                a.setParent(taskList);
                BaseLabel _label = new BaseLabel();
                if(!TextUtils.isEmpty(obj.get("messTitle"))) {
                    title = obj.get("messTitle").toString();
                } else {
                    title = "public.sys.message.commMessage";
                }

                if(!TextUtils.isEmpty(obj.get("messContent"))) {
                    content = obj.get("messContent").toString();
                } else {
                    content = "";
                }

                _label.setValue("[" + I18nUtil.getLabelContent(title) + "] " + content);
                _label.setParent(a);
                Span span = new Span();
                span.setParent(a);
                span.setClass("z-icon-arrow-right");
            }
        }

    }

    public void changeTabLable(String tabId, String label) {
        BaseTab tab = (BaseTab)this.getFellowIfAny(tabId);
        if(null != tab) {
            tab.setLabel(label);
        }

    }

    void closeAllOthers() {
        List tabs = this.resourceTabs.getChildren();
        ArrayList removeTabs = new ArrayList();
        Iterator i$ = tabs.iterator();

        BaseTab removeTab;
        while(i$.hasNext()) {
            removeTab = (BaseTab)i$.next();
            if(!removeTab.isSelected()) {
                removeTabs.add(removeTab);
            }
        }

        if(removeTabs.size() > 0) {
            i$ = removeTabs.iterator();

            while(i$.hasNext()) {
                removeTab = (BaseTab)i$.next();
                removeTab.onClose();
            }
        }

    }

    void closeAll() {
        List tabs = this.resourceTabs.getChildren();
        ArrayList removeTabs = new ArrayList();
        Iterator i$ = tabs.iterator();

        BaseTab removeTab;
        while(i$.hasNext()) {
            removeTab = (BaseTab)i$.next();
            removeTabs.add(removeTab);
        }

        if(removeTabs.size() > 0) {
            i$ = removeTabs.iterator();

            while(i$.hasNext()) {
                removeTab = (BaseTab)i$.next();
                removeTab.onClose();
            }
        }

    }

    void closeMe() {
        List tabs = this.resourceTabs.getChildren();
        ArrayList removeTabs = new ArrayList();
        Iterator i$ = tabs.iterator();

        BaseTab removeTab;
        while(i$.hasNext()) {
            removeTab = (BaseTab)i$.next();
            if(removeTab.isSelected()) {
                removeTabs.add(removeTab);
            }
        }

        if(removeTabs.size() > 0) {
            i$ = removeTabs.iterator();

            while(i$.hasNext()) {
                removeTab = (BaseTab)i$.next();
                removeTab.onClose();
            }
        }

    }

    class TabRightClick implements EventListener {
        int type;

        public TabRightClick(int type) {
            this.type = type;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            switch(this.type) {
                case 1:
                    MainAction.this.closeAllOthers();
                    break;
                case 2:
                    MainAction.this.closeAll();
                    break;
                case 3:
                    MainAction.this.closeMe();
            }

        }
    }

    class TabRight implements EventListener {
        TabRight() {
        }

        @Override
        public void onEvent(Event event) throws Exception {
            if(event.getTarget() instanceof Tab) {
                BaseTab tab = (BaseTab)event.getTarget();
                MainAction.this.tabRightPopup.open(tab);
            }

        }
    }

    class AClick implements EventListener {
        Map<String, Object> obj;

        public AClick(Map<String, Object> var1) {
            this.obj = obj;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            if(event.getTarget() instanceof A) {
                MessUtil.readMessage(this.obj.get("owid").toString());
                if(!TextUtils.isEmpty(this.obj.get("messBizUrl"))) {
                    MainAction.this.openFunByPageCa(I18nUtil.getLabelContent(this.obj.get("messTitle").toString()), this.obj.get("messBizUrl").toString());
                }

                MainAction.this.listAllWaitTask();
            }

        }
    }


}
