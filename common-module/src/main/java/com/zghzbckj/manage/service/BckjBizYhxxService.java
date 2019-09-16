/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectRestriction;
import com.google.common.collect.Maps;
import com.ourway.base.utils.BeanUtil;

import com.sun.org.apache.regexp.internal.RE;
import com.zghzbckj.common.CommonConstant;
import org.springframework.stereotype.Service;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.model.FilterModel;

import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizYhxxDao;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;
import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.dao.BckjBizYhxxDao;

/**
 * ccService
 * @author cc
 * @version 2019-09-09
 */
@Service
@Transactional(readOnly = true)
public class BckjBizYhxxService extends CrudService<BckjBizYhxxDao, BckjBizYhxx> {

	private static final Logger log = Logger.getLogger(BckjBizYhxxService.class);

    @Override
	public BckjBizYhxx get(String owid) {
		return super.get(owid);
	}
	@Override
	public List<BckjBizYhxx> findList(BckjBizYhxx bckjBizYhxx) {
		return super.findList(bckjBizYhxx);
	}
	@Override
	public PageInfo<BckjBizYhxx> findPage(Page<BckjBizYhxx> page, BckjBizYhxx bckjBizYhxx) {
		return super.findPage(page, bckjBizYhxx);
	}
	
	@Transactional(readOnly = false)
	public void save(BckjBizYhxx bckjBizYhxx) {
		super.saveOrUpdate(bckjBizYhxx);
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BckjBizYhxx bckjBizYhxx) {
		super.delete(bckjBizYhxx);
	}


	/**
     * <p>方法:findPagebckjBizYhxx TODO后台BckjBizYhxx分页列表</p>
     * <ul>
    * <li> @param filters TODO</li>
    * <li> @param pageNo TODO</li>
    * <li> @param pageSize TODO</li>
    * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
    * <li>@author D.cehn.g </li>
    * <li>@date 2018/9/5 9:47  </li>
    * </ul>
     */
    public ResponseMessage findPageBckjBizYhxx(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
    Map<String, Object> dataMap = FilterModel.doHandleMap(filters);
    PageInfo<BckjBizYhxx> page = findPage(dataMap, pageNo, pageSize, null);
        return ResponseMessage.sendOK(page);
        }

        /**
        * <p>方法:savebckjBizYhxx TODO保存BckjBizYhxx信息 </p>
        * <ul>
            * <li> @param mapData TODO</li>
            * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
            * <li>@author D.chen.g </li>
            * <li>@date 2018/9/6 17:05  </li>
            * </ul>
        */
        @Transactional(readOnly = false)
        public ResponseMessage saveBckjBizYhxx(Map<String, Object> mapData) {
        BckjBizYhxx bckjBizYhxx = JsonUtil.map2Bean(mapData, BckjBizYhxx.class);
        if(!TextUtils.isEmpty(mapData.get("owid"))){
        BckjBizYhxx bckjBizYhxxIndata=get(mapData.get("owid").toString());
        BeanUtil.copyPropertiesIgnoreNull(bckjBizYhxx,bckjBizYhxxIndata);
        bckjBizYhxx=bckjBizYhxxIndata;
        }
        saveOrUpdate(bckjBizYhxx);
        return ResponseMessage.sendOK(bckjBizYhxx);
        }

        /**
        *<p>方法:removeOrder TODO多条删除BckjBizYhxx </p>
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
            BckjBizYhxx bckjBizYhxx = new BckjBizYhxx();
        bckjBizYhxx.setOwid(owid);
            this.dao.delete(bckjBizYhxx);
            params.put("owid", owid);
            objs.add(params);
            }
            return ResponseMessage.sendOK(objs);
            }


    /**
     *<p>功能描述: 学生登录 </p >
     *<ul>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/11 </li>
     *</ul>
     */
    @Transactional(readOnly = false ,rollbackFor = Exception.class)
    public ResponseMessage logIn(Map<String, Object> datamap) {
        Map<String,Object> resMap = Maps.newHashMap();
        String psw = TextUtils.MD5(datamap.get("yhDlmm").toString()).toUpperCase();
        datamap.remove("psw");
        Map<String, Object> map = this.dao.logIn(datamap);
        if(TextUtils.isEmpty(map)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
        if(!psw.equalsIgnoreCase(map.get("yhDlmm").toString())){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.PasswordError);
        }
        if(Integer.parseInt(map.get("olx").toString())!=0){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
        //设置最近登录时间
        this.dao.updateDlsj(map.get("owid").toString());
        resMap.put("owid",map.get("owid"));
        resMap.put("yhtx",map.get("yhtx"));
        resMap.put("sjh",map.get("sjh"));
        return ResponseMessage.sendOK(resMap);
    }



    /**
     *<p>功能描述: 修改密码 </p >
     *<ul>
     *<li>@return com.zghzbckj.base.model.ResponseMessage</li>
     *<li>@throws </li>
     *<li>@author wangangaanng</li>
     *<li>@date 2019/9/11 </li>
     *</ul>
     */
    @Transactional(readOnly = false ,rollbackFor = Exception.class)
    public ResponseMessage modfiyPassword(Map<String, Object> datamap) {
        HashMap<String, Object> map = Maps.newHashMap();
        String newPassword=datamap.get("newPassword").toString();
        String newPasswordAgain=datamap.get("newPasswordAgain").toString();
        if(!newPassword.equals(newPasswordAgain)){
            return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NewPasswordNotMatch);
        }
        String oldPsw = TextUtils.MD5(datamap.get("oldPassword").toString()).toUpperCase();
        BckjBizYhxx bckjbizyhxx = this.dao.getOneByCondition(datamap);
       if(TextUtils.isEmpty(bckjbizyhxx)) {
            return  ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.NoAccounctExists);
        }
       if(!bckjbizyhxx.getYhdlmm().equalsIgnoreCase(oldPsw)){
           return ResponseMessage.sendError(ResponseMessage.FAIL,CommonConstant.PasswordError);
       }
        String newPsw = TextUtils.MD5(datamap.get("newPassword").toString()).toUpperCase();
        map.put("owid",datamap.get("owid"));
        map.put("yhDlmm",newPsw);
        this.dao.modfiyPassword(map);
        return  ResponseMessage.sendOK(CommonConstant.SUCCESS_MESSAGE);
    }


    public ResponseMessage getOneByOwid(String owid) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("owid",owid);
        return ResponseMessage.sendOK(this.dao.getOneByCondition(map));
    }
}