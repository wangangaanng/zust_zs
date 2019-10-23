/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjDicTreeDao;
import com.zghzbckj.manage.entity.BckjDicTree;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图文Service
 *
 * @author cg
 * @version 2019-03-15
 */
@Service
@Transactional(readOnly = true)
public class BckjDicTreeService extends CrudService<BckjDicTreeDao, BckjDicTree> {

    private static final Logger log = Logger.getLogger(BckjDicTreeService.class);

    @Override
    public BckjDicTree get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjDicTree> findList(BckjDicTree bckjDicTree) {
        return super.findList(bckjDicTree);
    }

    @Override
    public PageInfo<BckjDicTree> findPage(Page<BckjDicTree> page, BckjDicTree bckjDicTree) {
        return super.findPage(page, bckjDicTree);
    }

    @Transactional(readOnly = false)
    public void save(BckjDicTree bckjDicTree) {
        super.saveOrUpdate(bckjDicTree);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjDicTree bckjDicTree) {
        super.delete(bckjDicTree);
    }


    /**
     * <p>方法:findPagebckjDicTree TODO后台BckjDicTree分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjDicTree(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjDicTree> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    /**
     * <p>方法:savebckjDicTree TODO保存BckjDicTree信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjDicTree(Map<String, Object> mapData) {
        BckjDicTree bckjDicTree = JsonUtil.map2Bean(mapData, BckjDicTree.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjDicTree bckjDicTreeIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjDicTree, bckjDicTreeIndata);
            bckjDicTree = bckjDicTreeIndata;
        }
        saveOrUpdate(bckjDicTree);
        return ResponseMessage.sendOK(bckjDicTree);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjDicTree </p>
     * <ul>
     * <li> @param codes TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:14  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage removeOrder(List<Long> codes) {
        List<Map<String, Object>> objs =Lists.newArrayList();
        for (Long owid : codes) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            BckjDicTree bckjDicTree = new BckjDicTree();
            bckjDicTree.setOwid(owid);
            this.dao.delete(bckjDicTree);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }


    public ResponseMessage listAllDicByType(Integer type) {
        Map map= Maps.newHashMap();
        map.put("type",type.toString());
        List<BckjDicTree> list = this.dao.findListByMap(map);
        return ResponseMessage.sendOK(getAreaTrees(list,-1));
    }

    public List<BckjDicTree> getAreaTrees(List<BckjDicTree> datas, int fatherId) {
        List<BckjDicTree> list = new ArrayList<>();
        for (BckjDicTree treeNode : datas) {
            if (treeNode.getFid().intValue() == fatherId) {
                convertMenuToTree(datas, treeNode);
                list.add(treeNode);
            } else {
                continue;
            }
        }
        return list;
    }

    /**
     * <p>
     * 递归，判断节点
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: TreeUtil.java,v 0.1 2011-11-15 下午07:35:31 Jack Exp $
     */
    public void convertMenuToTree(List<BckjDicTree> datas, BckjDicTree node) {
        for (BckjDicTree treeNode : datas) {
            if (node.getOwid().intValue()==treeNode.getFid()) {
                if (null == node.getSubChidren()) {
                    List<BckjDicTree>subList=Lists.newArrayList();
                    node.setSubChidren(subList);
                }
                // node.setLeaf(true);
                node.getSubChidren().add(treeNode);
                convertMenuToTree(datas, treeNode);
            } else {
                continue;
            }
        }
    }
}