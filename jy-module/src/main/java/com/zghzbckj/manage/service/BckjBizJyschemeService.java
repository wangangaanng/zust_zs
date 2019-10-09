/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.google.common.collect.Maps;
import com.ourway.base.utils.*;

import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.feign.BckjBizYhxxSer;
import com.zghzbckj.manage.dao.BckjBizJyschemeDao;
import com.zghzbckj.manage.entity.BckjBizJyscheme;
import com.zghzbckj.manage.entity.BckjBizSyb;
import com.zghzbckj.util.ExcelUtils;
import com.zghzbckj.util.MapUtil;
import org.springframework.stereotype.Service;
import com.zghzbckj.base.model.FilterModel;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.config.TxNamespaceHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 就业方案Service
 * @author wangangaanng
 * @version 2019-09-30
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJyschemeService extends CrudService<BckjBizJyschemeDao, BckjBizJyscheme> {

	private static final Logger log = Logger.getLogger(BckjBizJyschemeService.class);

    @Override
	public BckjBizJyscheme get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizJyscheme> findList(BckjBizJyscheme bckjBizJyscheme) {
		return super.findList(bckjBizJyscheme);
	}
	@Override
	public PageInfo<BckjBizJyscheme> findPage(Page<BckjBizJyscheme> page, BckjBizJyscheme bckjBizJyscheme) {
		return super.findPage(page, bckjBizJyscheme);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizJyscheme bckjBizJyscheme) {
		super.saveOrUpdate(bckjBizJyscheme);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizJyscheme bckjBizJyscheme) {
		super.delete(bckjBizJyscheme);
	}


	/**
     * <p>方法:findPagebckjBizJyscheme TODO后台BckjBizJyscheme分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizJyscheme(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizJyscheme> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizJyscheme TODO保存BckjBizJyscheme信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizJyscheme(Map<String, Object> mapData) throws ParseException {
            //先从数据库查
            BckjBizJyscheme bckjBizJyscheme =this.dao.getJyschemeByMap(mapData);
            if(!TextUtils.isEmpty(mapData.get("bdkssj"))){
                mapData.put("bdkssj",ExcelUtils.stringtoDatec(mapData.get("bdkssj").toString()));
            }
            if(!TextUtils.isEmpty(mapData.get("bdjssj"))){
                mapData.put("bdjssj",ExcelUtils.stringtoDatec(mapData.get("bdjssj").toString()));
            }
            //map转变为类
            BckjBizJyscheme bckjBizJyscheme1 =new BckjBizJyscheme();
            MapUtil.easySetByMap(mapData,bckjBizJyscheme1);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
            if(null!=bckjBizJyscheme&&!(bckjBizJyscheme1.getOwid().equals(bckjBizJyscheme.getOwid()))){
                return ResponseMessage.sendError(ResponseMessage.FAIL,"已存在此学号学生");
            }
                BckjBizJyscheme bckjBizJyschemeIndata=get(mapData.get("owid").toString());
                BeanUtil.copyPropertiesIgnoreNull(bckjBizJyscheme,bckjBizJyschemeIndata);
                bckjBizJyscheme=bckjBizJyschemeIndata;
            }else {
                if(null!=bckjBizJyscheme){
                return ResponseMessage.sendError(ResponseMessage.FAIL,"已存在此学号学生");
            }
        }
        saveOrUpdate(bckjBizJyscheme1);
        return ResponseMessage.sendOK(bckjBizJyscheme);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizJyscheme </p>
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
            BckjBizJyscheme bckjBizJyscheme = new BckjBizJyscheme();
        bckjBizJyscheme.setOwid(owid);
            this.dao.delete(bckjBizJyscheme);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }

    public ResponseMessage saveJyFaInfo(Map<String, Object> dataMap) {
            if(!TextUtils.isEmpty(dataMap.get("sfz"))){
                    String regex = "\\d{15}(\\d{2}[0-9xX])?";
                    if (!dataMap.get("sfz").toString().matches(regex)) {
                        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ErrorForIdentityCard);
                }
            }
        BckjBizJyscheme bckjBizJyscheme1 = new BckjBizJyscheme();
        MapUtil.easySetByMap(dataMap,bckjBizJyscheme1);
            if(TextUtils.isEmpty(bckjBizJyscheme1)){
                return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.ERROR_SYS_MESSAG);
            }
            saveOrUpdate(bckjBizJyscheme1);
            return ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }

    public ResponseMessage getJyBaseInfo(Map<String, Object> dataMap) {
        String xsxh = this.dao.getXsxhByOwid(dataMap.get("owid").toString());
        HashMap<String, Object> sendMap = Maps.newHashMap();
        sendMap.put("xsxh",xsxh);
        BckjBizJyscheme bckjBizJyscheme = this.dao.getJyschemeByMap(sendMap);
        if(TextUtils.isEmpty(bckjBizJyscheme)){
            Map<String,Object> map=this.dao.getJyBaseInfo(dataMap);
            return ResponseMessage.sendOK(map);
        }
        return ResponseMessage.sendOK(bckjBizJyscheme);
    }


}