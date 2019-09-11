package com.ourway.manage.service;

import com.ourway.sys.dao.SysPageDao;
import com.ourway.sys.dao.SysTemplateDao;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("customMenuService")

public class CustomMenuService {
    @Autowired
    SysPageDao sysPageDao;
    @Autowired
    SysTemplateDao sysTemplateDao;

    public Map<String, Object> moduleMenu(String owid, String pageCode) {
        Map<String, Object> map = doCreateMap(-100, -100, "-1/-100", "/project/moduleTemplate.do", "流程模板配置", "流程模板配置", "");
        return map;
    }

    private Map<String, Object> doCreateMap(int id, int fid, String path, String pageCa, String name, String color, String pageId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owid", id);
        map.put("fid", fid);
        map.put("path", path);
        map.put("icon", name);
        map.put("name", name);
        map.put("pageCa", pageCa);
        map.put("pageId", pageId);
        Map<String, Object> params = new HashMap<String, Object>(1);
        String afterAsk = "";
        if (pageCa.indexOf("?") >= 0) {
            afterAsk = "?" + pageCa.split("\\?")[1];
            pageCa = pageCa.split("\\?")[0];
        } else {
            pageCa = pageCa;
        }
        params.put("pageCa", pageCa);
        //获取page信息
        OurwaySysPage page = sysPageDao.getOneByParams(params, "");
        if (null == page) {
            map.put("link", pageCa + afterAsk);
        } else {
            OurwaySysTemplate template = sysTemplateDao.getOneById(page.getPageTemplate());
            map.put("link", template.getTemplatePath() + afterAsk);
        }
        return map;

    }
}
