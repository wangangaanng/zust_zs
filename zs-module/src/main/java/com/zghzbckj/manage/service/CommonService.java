package com.zghzbckj.manage.service;

import com.ourway.base.utils.MapUtils;
import com.zghzbckj.manage.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@Service("commonService")
public class CommonService {

    @Autowired
    CommonDao commonDao;

    /**
     * <p>功能描述:历年饼图数据 getLntjPie</p >
     * <ul>
     * <li>@param []</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object></li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/11/12 13:48</li>
     * </ul>
     */
    public Map<String, Object> getLntjPie() {
        Map<String, Object> result = new HashMap<>();
        List<String> nfList = new ArrayList<>();
        List<Map<String, Object>> dataList = commonDao.getListLntj();
        for (Map<String, Object> data : dataList) {
            nfList.add(MapUtils.getString(data, "name"));
        }
        result.put("pieData", dataList);
        result.put("nfList", nfList);
        return result;
    }

    /**
     * <p>功能描述:招生计划饼图 getZsjhPie</p >
     * <ul>
     * <li>@param []</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object></li>
     * <li>@throws </li>
     * <li>@author xuyux</li>
     * <li>@date 2019/11/12 13:54</li>
     * </ul>
     */
    public Map<String, Object> getZsjhPie() {
        Map<String, Object> result = new HashMap<>();
        List<String> nfList = new ArrayList<>();
        List<Map<String, Object>> dataList = commonDao.getListZsjh();
        for (Map<String, Object> data : dataList) {
            nfList.add(MapUtils.getString(data, "name"));
        }
        result.put("pieData", dataList);
        result.put("nfList", nfList);
        return result;
    }

}