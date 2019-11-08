/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Lists;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.BaseTree;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizXyzyDao;
import com.zghzbckj.manage.entity.BckjBizXyzy;
import com.zghzbckj.manage.entity.BckjDicMenu;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ccService
 *
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizXyzyService extends CrudService<BckjBizXyzyDao, BckjBizXyzy> {

    private static final Logger log = Logger.getLogger(BckjBizXyzyService.class);

    @Override
    public BckjBizXyzy get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizXyzy> findList(BckjBizXyzy bckjBizXyzy) {
        return super.findList(bckjBizXyzy);
    }

    @Override
    public PageInfo<BckjBizXyzy> findPage(Page<BckjBizXyzy> page, BckjBizXyzy bckjBizXyzy) {
        return super.findPage(page, bckjBizXyzy);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizXyzy bckjBizXyzy) {
        super.saveOrUpdate(bckjBizXyzy);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizXyzy bckjBizXyzy) {
        super.delete(bckjBizXyzy);
    }


    /**
     * <p>方法:findPagebckjBizXyzy TODO后台BckjBizXyzy分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizXyzy(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizXyzy> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjBizXyzy TODO保存BckjBizXyzy信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizXyzy(Map<String, Object> mapData) {
        BckjBizXyzy bckjBizXyzy = JsonUtil.map2Bean(mapData, BckjBizXyzy.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizXyzy bckjBizXyzyIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizXyzy, bckjBizXyzyIndata);
            bckjBizXyzy = bckjBizXyzyIndata;
        }
        saveOrUpdate(bckjBizXyzy);
        return ResponseMessage.sendOK(bckjBizXyzy);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizXyzy </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage removeOrder(List<String> codes) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : codes) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            BckjBizXyzy bckjBizXyzy = new BckjBizXyzy();
            bckjBizXyzy.setOwid(Long.valueOf(owid));
            this.dao.delete(bckjBizXyzy);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    public List<BckjBizXyzy> getZyList(Map<String, Object> mapData) {
        List<BckjBizXyzy> lists = findListByParams(mapData, " a.createtime desc ");
        return lists;
    }

    public Object listTree(List<FilterModel> filters) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        List<BckjBizXyzy> xyzys = this.dao.findListByMap(dataMap);
        List<BaseTree> baseTreeList = Lists.newArrayList();
        if (xyzys.size() > 0) {
            for (BckjBizXyzy xyzy : xyzys) {
                BaseTree bt = new BaseTree();
                bt.setOwid(xyzy.getOwid().intValue());
                bt.setFid(xyzy.getParentId().intValue());
                bt.setName(xyzy.getMz());
                bt.setPath(xyzy.getPath());
                baseTreeList.add(bt);
            }
        }
        return baseTreeList;
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Object removeTree(Map<String, Object> mapData) {
        this.deleteByMap(mapData);
        this.dao.deleteApply(mapData);
        return mapData;
    }

    @Transactional(readOnly = false)
    public BaseTree saveTree(Map<String, Object> mapData) {
        BckjBizXyzy bckjBizXyzy = JsonUtil.map2Bean(mapData, BckjBizXyzy.class);
        bckjBizXyzy.setParentId(Long.valueOf(mapData.get("fid").toString()));
        if (TextUtils.isEmpty(bckjBizXyzy.getOwid())) {
            this.save(bckjBizXyzy);
            if (!TextUtils.isEmpty(bckjBizXyzy.getPath()) && bckjBizXyzy.getPath().endsWith("//")) {
                String path = bckjBizXyzy.getPath().substring(0, bckjBizXyzy.getPath().length() - 1);
                bckjBizXyzy.setPath(path + bckjBizXyzy.getOwid() + "/");
            } else {
                bckjBizXyzy.setPath(bckjBizXyzy.getPath() + bckjBizXyzy.getOwid() + "/");
            }
        }
        this.saveOrUpdate(bckjBizXyzy);
        BaseTree tree = new BaseTree();
        tree.setOwid(bckjBizXyzy.getOwid().intValue());
        tree.setId(bckjBizXyzy.getOwid().intValue());
        tree.setFid(bckjBizXyzy.getParentId().intValue());
        tree.setName(bckjBizXyzy.getMz());
        tree.setPath(bckjBizXyzy.getPath());
        return tree;
    }
}