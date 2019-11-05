/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.TreeUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.BaseTree;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizBkzyDao;
import com.zghzbckj.manage.dao.BckjBizBmDao;
import com.zghzbckj.manage.entity.BckjBizBkzy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class BckjBizBkzyService extends CrudService<BckjBizBkzyDao, BckjBizBkzy> {

    private static final Logger log = Logger.getLogger(BckjBizBkzyService.class);
    @Autowired
    BckjBizBmService bmService;
    @Autowired
    BckjBizBmDao bmDao;

    @Override
    public BckjBizBkzy get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizBkzy> findList(BckjBizBkzy bckjBizBkzy) {
        return super.findList(bckjBizBkzy);
    }

    @Override
    public PageInfo<BckjBizBkzy> findPage(Page<BckjBizBkzy> page, BckjBizBkzy bckjBizBkzy) {
        return super.findPage(page, bckjBizBkzy);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizBkzy bckjBizBkzy) {
        super.saveOrUpdate(bckjBizBkzy);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizBkzy bckjBizBkzy) {
        super.delete(bckjBizBkzy);
    }


    /**
     * <p>方法:findPagebckjBizBkzy TODO后台BckjBizBkzy分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.PageInfo  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public PageInfo<BckjBizBkzy> findPageBckjBizBkzy(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizBkzy> page = findPage(dataMap, pageNo, pageSize, null);
        return page;
    }

    /**
     * <p>方法:savebckjBizBkzy TODO保存BckjBizBkzy信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public BaseTree saveBckjBizBkzy(Map<String, Object> mapData) {
        BckjBizBkzy depat = JsonUtil.map2Bean(mapData, BckjBizBkzy.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizBkzy bckjBizBkzyIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(depat, bckjBizBkzyIndata);
            depat = bckjBizBkzyIndata;
        }
        if (TextUtils.isEmpty(depat.getOwid())) {
            saveOrUpdate(depat);
            depat.setPath(TreeUtils.checkPath(depat.getPath(), depat.getOwid().toString()));
            saveOrUpdate(depat);
        } else {
            saveOrUpdate(depat);
        }

        BaseTree tree = new BaseTree();
        tree.setOwid(depat.getOwid().intValue());
        tree.setId(depat.getOwid().intValue());
        tree.setFid(depat.getFid().intValue());
        tree.setName(depat.getName());
        tree.setPath(depat.getPath());
        tree.setPx(Double.valueOf(depat.getPx()));
        tree.setCc(depat.getCc());
        return tree;
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizBkzy </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = Lists.newArrayList();
        for (String owid : codes) {
            Map<String, Object> params = Maps.newHashMap();
            BckjBizBkzy bckjBizBkzy = new BckjBizBkzy();
            bckjBizBkzy.setOwid(Long.valueOf(owid));
            this.dao.delete(bckjBizBkzy);
            params.put("owid", owid);
            objs.add(params);
        }
        return objs;
    }

    /**
     * <p>方法:getListByCj TODO获取专业列表 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.util.List<com.zghzbckj.manage.entity.BckjBizBkzy>  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/25 16:40  </li>
     * </ul>
     */
    public List<BckjBizBkzy> getListByCj(Map<String, Object> mapData) {
        return findListByParams(mapData, "a.px");
    }

    public List<BaseTree> listTree(List models) {
        String name = "";
        Map params = Maps.newHashMap();
        params.put("orderBy", "cc,px");
        List deptList = this.dao.findListByMap(params);
        ArrayList baseTreeList = new ArrayList();
        if (null != deptList && deptList.size() > 0) {
            Iterator var5 = deptList.iterator();

            while (var5.hasNext()) {
                BckjBizBkzy depat = (BckjBizBkzy) var5.next();
                name = depat.getName();
                BaseTree bt = new BaseTree();
                bt.setOwid(depat.getOwid().intValue());
                bt.setFid(depat.getFid().intValue());
//                if (!TextUtils.isEmpty(depat.getDepNo())) {
//                    name = name + depat.getDepNo() + "-";
//                }
//
//                if (!TextUtils.isEmpty(depat.getDepName())) {
//                    name = name + depat.getDepName();
//                }

                bt.setName(name);
                bt.setPath(depat.getPath());
                bt.setCc(depat.getCc());
                bt.setPx(Double.valueOf(depat.getPx()));
                baseTreeList.add(bt);
            }
        }
        return baseTreeList;
    }

    public Integer getNumber(String dl) {
        Map params = Maps.newHashMap();
        params.put("bklbOwid", Integer.parseInt(dl));
        params.put("state", 7);
        params.put("wait", 1);
        Integer count = bmDao.queryWaitNumber(params);
        return count;

    }
}