package com.ourway.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.manage.WebConstants;
import com.ourway.sys.dao.SysDicDao;
import com.ourway.sys.dao.SysMenusDao;
import com.ourway.sys.dao.SysPrivsallocateDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.model.OurwaySysPrivsallocate;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("customDicService")
public class CustomDicService {
    @Autowired
    SysDicDao sysDicDao;
    @Autowired
    FilesService filesService;
    @Autowired
    DicService dicService;
    @Autowired
    MenusService menusService;
    @Autowired
    SysMenusDao sysMenusDao;
    @Autowired
    SysPrivsallocateDao sysPrivsallocateDao;

    public List<Map<String, Object>> getByType(Map<String, Object> mapData) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=:dicType and ( b.dicVal3=:webType or b.dicVal3=2) and b.dicVal4=1 order by a.dicVal5 " ;
        List<Object[]> objs = sysDicDao.listObjsAllByHql(hql, mapData);
        List<Map<String, Object>> datas = new ArrayList(objs.size());
        Iterator var7 = objs.iterator();
        while (var7.hasNext()) {
            Object[] objects = (Object[]) var7.next();
//            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap();
            BeanUtil.obj2Map(val, dataMap);
//            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return datas;
    }


    private Integer isSingle(Integer type, String dicVal1) {
        Map mapParam = Maps.newHashMap();
        mapParam.put("dicVal1", dicVal1);
        Object[] dicvals = sysDicDao.getSingleDicByType(type, mapParam);
        String val3 = ((OurwaySysDicValue) dicvals[1]).getDicVal3();
        return Integer.valueOf(val3);
    }

    public ResponseMessage listModel(Map<String, Object> mapData) {
        return null;
    }

    private String getLbmh(Integer lmbh) {
        Map mapParam = Maps.newHashMap();
        mapParam.put("dicVal1", lmbh.toString());
        //100000 特指一级菜单
        Object[] dicvals = sysDicDao.getSingleDicByType(100000, mapParam);
        return ((OurwaySysDicValue) dicvals[1]).getDicVal2();
    }



    //
    public ResponseMessage removeFiles(Map<String, Object> mapData) {
        return null;
    }

    public ResponseMessage saveDic(Map<String, Object> dataMap, int type) {
        List<OurwaySysDicValue> dicValues = new ArrayList();
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List) dataMap.get("dataList");
            dicValues = JsonUtil.map2List(components, OurwaySysDicValue.class);
        }
        for (OurwaySysDicValue oneDic : dicValues) {
            if (oneDic.getUpdateFlag()==1&&isExistDicVal(100010,oneDic)){
                return ResponseMessage.sendError(ResponseMessage.FAIL,oneDic.getDicVal1()+"此栏目编号已入库，请更换");
            }
        }
        this.dicService.saveOrUpdateAll(dicValues, type);
        dealEjMenu(dicValues);
        return ResponseMessage.sendOK(this.dicService.listDicByType(type, ""));
    }


    private void dealYjMenu(List<OurwaySysDicValue> dicValues) {
        List<OurwaySysMenus> menusList = Lists.newArrayList();
        Map desubMenuParam = Maps.newHashMap();
        int count = 13;
        for (OurwaySysDicValue oneDic : dicValues) {
            CacheUtil.setVal(WebConstants.LMBH+oneDic.getDicVal1(),oneDic.getDicVal2());
            switch (oneDic.getUpdateFlag()) {
                case 1:
                    count++;
                    menusList.add(orgernizeYjMenu(oneDic, count));
                    break;
                case 2:
                    desubMenuParam.put("language", oneDic.getDicVal1());
                    menusService.removeMenu(desubMenuParam);
                    break;
            }
        }
        menusService.saveOrUpdateAll(menusList);
        for(OurwaySysMenus menus:menusList){
            signMenusToRoles(menus);
        }
    }

    /**
     * 二级菜单
     *
     * @param dicValues
     */
    private void dealEjMenu(List<OurwaySysDicValue> dicValues) {
        List<OurwaySysMenus> menusList = Lists.newArrayList();
        Map desubMenuParam = Maps.newHashMap();
        int count = 0;
        for (OurwaySysDicValue oneDic : dicValues) {
            CacheUtil.setVal(WebConstants.LMBH2+oneDic.getDicVal1(),oneDic.getDicVal2());
            switch (oneDic.getUpdateFlag()) {
                case 1:
                    count++;
                    menusList.add(orgernizeMenu(oneDic, count));
                    break;
                case 2:
                    desubMenuParam.put("language", oneDic.getDicRefOwid().toString());
                    menusService.removeMenu(desubMenuParam);
                    break;
            }
        }
        menusService.saveOrUpdateAll(menusList);
        for(OurwaySysMenus menus:menusList){
            signMenusToRoles(menus);
        }
    }

    /**
     * 处理一级栏目菜单
     *
     * @param oneDic
     * @param px
     * @return
     */
    private OurwaySysMenus orgernizeYjMenu(OurwaySysDicValue oneDic, int px) {
        Map queryPram = Maps.newHashMap();
        queryPram.put("language", oneDic.getDicVal1());
        OurwaySysMenus menus = sysMenusDao.getOneByParams(queryPram, "");
        if (null == menus) {
            menus = new OurwaySysMenus();
            menus.setIsshow((byte) 0);
            menus.setLanguage(oneDic.getDicVal1());
            menus.setCc(1);
            menus.setType(0);
            menus.setFid(990);
            menus.setPageCa("");
            menusService.saveOrUpdate(menus);
            menus.setPath("-1/990/" + menus.getOwid() + "/");
        }
        menus.setPx(Double.valueOf(px));
        menus.setName(oneDic.getDicVal2());
        menus.setIcon(oneDic.getDicVal2());
        menus.setAliais(oneDic.getDicVal2());
        return menus;
    }

    /**
     * 判断是否存在次dic
     *
     * @param type
     * @param paramDic
     * @return
     */
    private boolean isExistDicVal(int type, OurwaySysDicValue paramDic) {
        Map mapParam = Maps.newHashMap();
        mapParam.put("dicVal1", paramDic.getDicVal1());
        Object[] dicvals = sysDicDao.getSingleDicByType(type, mapParam);
        if (TextUtils.isEmpty(paramDic.getOwid()) && null != dicvals) {
            return true;
        }
        return false;
    }

    /**
     * 处理二级栏目菜单
     *
     * @param oneDic
     * @param px
     * @return
     */
    private OurwaySysMenus orgernizeMenu(OurwaySysDicValue oneDic, int px) {
        Map queryPram = Maps.newHashMap();
        queryPram.put("language", oneDic.getDicVal4());
        OurwaySysMenus menusParent = sysMenusDao.getOneByParams(queryPram, "");
        queryPram.put("language", oneDic.getDicRefOwid());
        OurwaySysMenus menus = sysMenusDao.getOneByParams(queryPram, "");
        if (null == menus) {
            menus = new OurwaySysMenus();
            menus.setIsshow((byte) 0);
            menus.setLanguage(oneDic.getDicRefOwid().toString());
            menus.setPrivsAllocate((byte) 0);
            menus.setPrivsDefault((byte) 0);
            menus.setCc(2);
            menus.setTarget((byte) 0);
            menus.setType(0);
            menusService.saveOrUpdate(menus);
            menus.setPath(menusParent.getPath() + menus.getOwid() + "/");
        }
        menus.setPx(Double.valueOf(px));
        String link = null;
        //文章列表
        if (oneDic.getDicVal3().equals("0")) {
            menus.setPageId(WebConstants.PAGE_LIST_ARTICLE);
            link = WebConstants.ARTCILE_LIST_LINK.replace("LMBH", oneDic.getDicVal4()).replace("LB2", oneDic.getDicVal1());
            menus.setPageCa(WebConstants.ARTCILE_LIST_PAGECA);
            menus.setPageType((byte) 4);
        }//文章详情
        else if (oneDic.getDicVal3().equals("1")){
            menus.setPageId(WebConstants.PAGE_DETAIL_ARTICLE);
            link = WebConstants.ARTCILE_DET_LINK.replace("LMBH", oneDic.getDicVal4()).replace("LB2", oneDic.getDicVal1());
            menus.setPageCa(WebConstants.ARTCILE_DET_PAGECA);
            menus.setPageType((byte) 2);
        }else if (oneDic.getDicVal3().equals("2")){   //图片列表
            menus.setPageId(WebConstants.PAGE_LIST_PIC);
            link = WebConstants.ARTCILE_LIST_LINK.replace("LMBH", oneDic.getDicVal4()).replace("LB2", oneDic.getDicVal1());
            menus.setPageCa(WebConstants.PIC_LIST_PAGECA);
            menus.setPageType((byte) 4);
        }
        menus.setLink(link);
        menus.setName(oneDic.getDicVal2());
        menus.setIcon(oneDic.getDicVal2());
        menus.setAliais(oneDic.getDicVal2());
        menus.setFid(menusParent.getOwid());
        return menus;
    }


    /**
     * 保存一级菜单
     *
     * @param dataMap
     * @return
     */
    public ResponseMessage saveYjDic(Map<String, Object> dataMap) {
        List<OurwaySysDicValue> dicValues = new ArrayList();
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List) dataMap.get("dataList");
            dicValues = JsonUtil.map2List(components, OurwaySysDicValue.class);
        }
        for (OurwaySysDicValue oneDic : dicValues) {
            if (oneDic.getUpdateFlag()==1&&isExistDicVal(100000,oneDic)){
                return ResponseMessage.sendError(ResponseMessage.FAIL,oneDic.getDicVal1()+"此栏目编号已入库，请更换");
            }
        }
        this.dicService.saveOrUpdateAll(dicValues, 100000);
        dealYjMenu(dicValues);
        return ResponseMessage.sendOK(this.dicService.listDicByType(100000, ""));
    }

    public ResponseMessage listEjlm(List<FilterModel> filters, int type) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=" + type;
        HqlStatement hqlStatement = new HqlStatement(hql, filters, " b.dicVal1");
        List<Object[]> objs = sysDicDao.listObjsAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
        List<Map<String, Object>> datas = new ArrayList(objs.size());
        Iterator var7 = objs.iterator();
        while (var7.hasNext()) {
            Object[] objects = (Object[]) var7.next();
//            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap();
            BeanUtil.obj2Map(val, dataMap);
//            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return ResponseMessage.sendOK(datas);
    }

    /**
     * 获取二级栏目
     * @param mapData
     * @return
     */
    public ResponseMessage getSeclm(Map<String, Object> mapData) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=100010 and b.dicVal4=:dicVal4 and b.dicVal5=1 order by a.dicVal6 " ;
        List<Object[]> objs = sysDicDao.listObjsAllByHql(hql, mapData);
        List<Map<String, Object>> datas = new ArrayList(objs.size());
        Iterator var7 = objs.iterator();
        while (var7.hasNext()) {
            Object[] objects = (Object[]) var7.next();
            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap();
            BeanUtil.obj2Map(val, dataMap);
            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return ResponseMessage.sendOK(datas);
    }



    private void signMenusToRoles(OurwaySysMenus menus){
        Map param=Maps.newHashMap();
        param.put("roleRefOwid",WebConstants.ADMIN_ROLE_OWID);
        param.put("menuRefOwid",menus.getOwid());
        List<OurwaySysPrivsallocate> privsallocates=sysPrivsallocateDao.listAllByParam(param,"");
        if(null==privsallocates||privsallocates.size()==0){
            OurwaySysPrivsallocate privsallocate=new OurwaySysPrivsallocate();
            privsallocate.setMenuRefOwid(menus.getOwid());
            privsallocate.setRoleRefOwid(WebConstants.ADMIN_ROLE_OWID);
            sysPrivsallocateDao.saveOrUpdate(privsallocate);
        }
    }

}
