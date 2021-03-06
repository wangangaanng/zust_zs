/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.beust.jcommander.internal.Maps;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.SwytConstant;
import com.zghzbckj.manage.dao.BckjBizBmDao;
import com.zghzbckj.manage.dao.BckjBizFzszDao;
import com.zghzbckj.manage.entity.BckjBizBm;
import com.zghzbckj.manage.entity.BckjBizFzsz;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BckjBizFzszService extends CrudService<BckjBizFzszDao, BckjBizFzsz> {
    @Autowired
    BckjBizBkzyService bkzyService;
    @Autowired
    BckjBizBmDao bmDao;
    @Autowired
    BckjBizBmService bmService;


    private static final Logger log = Logger.getLogger(BckjBizFzszService.class);

    @Override
    public BckjBizFzsz get(String owid) {
        return super.get(owid);
    }

    @Override
    public List<BckjBizFzsz> findList(BckjBizFzsz bckjBizFzsz) {
        return super.findList(bckjBizFzsz);
    }

    @Override
    public PageInfo<BckjBizFzsz> findPage(Page<BckjBizFzsz> page, BckjBizFzsz bckjBizFzsz) {
        return super.findPage(page, bckjBizFzsz);
    }

    @Transactional(readOnly = false)
    public void save(BckjBizFzsz bckjBizFzsz) {
        super.saveOrUpdate(bckjBizFzsz);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(BckjBizFzsz bckjBizFzsz) {
        super.delete(bckjBizFzsz);
    }


    /**
     * <p>方法:findPagebckjBizFzsz TODO后台BckjBizFzsz分页列表</p>
     * <ul>
     * <li> @param filters TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.cehn.g </li>
     * <li>@date 2018/9/5 9:47  </li>
     * </ul>
     */
    public ResponseMessage findPageBckjBizFzsz(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
        PageInfo<BckjBizFzsz> page = findPageWithZy(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
    }

    private PageInfo<BckjBizFzsz> findPageWithZy(Map<String, Object> paramsMap, Integer pageNo, Integer pageSize, String orderBy) {
        Page page = new Page(pageNo, pageSize);
        paramsMap.put("page", page);
        if (!TextUtils.isEmpty(orderBy)) {
            paramsMap.put("orderBy", orderBy);
        }
        List<BckjBizFzsz> fzszList = this.dao.findListByMap(paramsMap);
        if (!TextUtils.isEmpty(fzszList) && fzszList.size() > 0) {
            for (BckjBizFzsz fzsz : fzszList) {
                String dl = fzsz.getDl();
                String dlStr = bkzyService.get(dl.toString()).getName();
                fzsz.setDlid(dlStr);

                //已选择
                Map params = new HashMap<>();
                params.put("fzszRefOwid", fzsz.getOwid());
                Integer yxz = bmDao.queryWaitNumber(params);
                fzsz.setState(yxz);
            }
        }
        page.setList(fzszList);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;

    }

    /**
     * <p>方法:savebckjBizFzsz TODO保存BckjBizFzsz信息 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2018/9/6 17:05  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public ResponseMessage saveBckjBizFzsz(Map<String, Object> mapData) {
        BckjBizFzsz bckjBizFzsz = JsonUtil.map2Bean(mapData, BckjBizFzsz.class);
        if (!TextUtils.isEmpty(mapData.get("owid"))) {
            BckjBizFzsz bckjBizFzszIndata = get(mapData.get("owid").toString());
            BeanUtil.copyPropertiesIgnoreNull(bckjBizFzsz, bckjBizFzszIndata);
            bckjBizFzsz = bckjBizFzszIndata;
        }
        saveOrUpdate(bckjBizFzsz);
        return ResponseMessage.sendOK(bckjBizFzsz);
    }

    /**
     * <p>方法:removeOrder TODO多条删除BckjBizFzsz </p>
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
            BckjBizFzsz bckjBizFzsz = new BckjBizFzsz();
            bckjBizFzsz.setOwid(owid);
            this.dao.delete(bckjBizFzsz);
            params.put("owid", owid);
            objs.add(params);
        }
        return ResponseMessage.sendOK(objs);
    }

    @Transactional(readOnly = false)
    public void deleteAll() {
        this.dao.deleteAll();
        return;
    }

    @Transactional(readOnly = false)
    public void zdfpAction() {
        Map prams = Maps.newHashMap();
        List<BckjBizFzsz> fzszList = findListByParams(prams, "");
        if (TextUtils.isEmpty(fzszList) || fzszList.size() == 0) {
            return;
        }
        for (BckjBizFzsz fzsz : fzszList) {
            //已选择
            Map params = new HashMap<>();
            params.put("fzszRefOwid", fzsz.getOwid());
            Integer yxz = bmDao.queryWaitNumber(params);
            if (yxz >= fzsz.getZhrs()) {
                continue;
            }
            Integer leftNumber = fzsz.getZhrs() - yxz;
            params.clear();
            params.put("bklbOwid", Integer.parseInt(fzsz.getDl()));
            params.put("state", 7);
            params.put("limit", leftNumber);
            params.put("rand", 1);
            List<BckjBizBm> bmList = bmDao.findListByMap(params);
            if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
                for (BckjBizBm bm : bmList) {
                    bm.setState(8);
                    bm.setMssj(fzsz.getMemo());
                    bm.setFzszRefOwid(fzsz.getOwid());
                    bm.setXybnr(SwytConstant.XZMSD);
                    bmService.saveOrUpdate(bm);
                }
            }


        }
    }

    @Transactional(readOnly = false)
    public void cxfpAction() {
        Map prams = Maps.newHashMap();
        List<BckjBizFzsz> fzszList = findListByParams(prams, "");
        if (TextUtils.isEmpty(fzszList) || fzszList.size() == 0) {
            return;
        }
        for (BckjBizFzsz fzsz : fzszList) {
            Map params = new HashMap<>();
            params.put("fzszRefOwid", fzsz.getOwid());
            params.put("state", 8);
            List<BckjBizBm> bmList = bmDao.findListByMap(params);
            if (!TextUtils.isEmpty(bmList) && bmList.size() > 0) {
                for (BckjBizBm bm : bmList) {
                    bm.setState(7);
                    bm.setXybnr(SwytConstant.BMDMSFP);
                    bm.setFzszRefOwid("");
                    bm.setMemo("");
                    bm.setZkzh("");
                    bm.setMssj("");
                    bmService.saveOrUpdate(bm);
                }
            }
        }
    }
}