/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.ourway.base.utils.BeanUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.feign.BckjBizZxzxSer;
import com.zghzbckj.util.PageUtils;
import com.zghzbckj.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizZjzx;
import com.zghzbckj.manage.dao.BckjBizZjzxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizZjzxService extends CrudService<BckjBizZjzxDao, BckjBizZjzx> {

	private static final Logger log = Logger.getLogger(BckjBizZjzxService.class);

    @Override
	public BckjBizZjzx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizZjzx> findList(BckjBizZjzx bckjBizZjzx) {
		return super.findList(bckjBizZjzx);
	}
	@Override
	public PageInfo<BckjBizZjzx> findPage(Page<BckjBizZjzx> page, BckjBizZjzx bckjBizZjzx) {
		return super.findPage(page, bckjBizZjzx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizZjzx bckjBizZjzx) {
		super.saveOrUpdate(bckjBizZjzx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizZjzx bckjBizZjzx) {
		super.delete(bckjBizZjzx);
	}
    @Autowired
    BckjBizZxzxSer bckjbizzxzxSer;

	/**
     * <p>方法:findPagebckjBizZjzx TODO后台BckjBizZjzx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizZjzx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizZjzx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizZjzx TODO保存BckjBizZjzx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizZjzx(Map<String, Object> mapData) {
        BckjBizZjzx bckjBizZjzx = JsonUtil.map2Bean(mapData, BckjBizZjzx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizZjzx bckjBizZjzxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizZjzx,bckjBizZjzxIndata);
        bckjBizZjzx=bckjBizZjzxIndata;
        }
        saveOrUpdate(bckjBizZjzx);
        return ResponseMessage.sendOK(bckjBizZjzx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizZjzx </p>
        *<ul>
            *<li> @param codes TODO</li>
            *<li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            *<li>@author D.chen.g </li>
            *<li>@date 2018/9/6 17:14  </li>
            *</ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage removeOrder(List<String> codes) {
            List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
            for (String owid : codes) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            BckjBizZjzx bckjBizZjzx = new BckjBizZjzx();
        bckjBizZjzx.setOwid(owid);
            this.dao.delete(bckjBizZjzx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }

    public ResponseMessage supervisorList(Map<String, Object> dataMap) {
        Page<BckjBizZjzx> page = new Page(Integer.parseInt(dataMap.get("pageNo").toString()), Integer.parseInt(dataMap.get("pageSize").toString()));
        dataMap.put("page", page);
        page.setList(this.dao.zjList(dataMap));
        return ResponseMessage.sendOK(PageUtils.assimblePageInfo(page));
    }


    public ResponseMessage showStudentNoReplyList(Map<String, Object> dataMap) {
        //根据专家yhid得到owid
        BckjBizZjzx bckjBizZjzx = this.dao.getOneByCondition(dataMap);
        dataMap.put("state","1");
        dataMap.put("zxzyid",bckjBizZjzx.getOwid());
        return bckjbizzxzxSer.getListByZxzyid(dataMap);
    }


    public ResponseMessage showStudentReplyList(Map<String, Object> dataMap) {
        //根据专家yhid得到owid
        BckjBizZjzx bckjBizZjzx = this.dao.getOneByCondition(dataMap);
        dataMap.put("state","2");
        dataMap.put("zxzyid",bckjBizZjzx.getOwid());
        return bckjbizzxzxSer.getListByZxzyid(dataMap);
    }

    public ResponseMessage replyConsult(Map<String, Object> dataMap) {
        //根据专家yhid得到owid
        BckjBizZjzx bckjBizZjzx = this.dao.getOneByCondition(dataMap);
        dataMap.put("hfOwid",bckjBizZjzx.getYhid());
        dataMap.put("hfName",bckjBizZjzx.getZjxm());
        dataMap.put("zxzyid",bckjBizZjzx.getOwid());
        return bckjbizzxzxSer.replyConsult(dataMap);
    }
}