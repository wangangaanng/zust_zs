/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.service;


import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.SysWxconfigDao;
import com.zghzbckj.manage.entity.SysWxconfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单表生成Service
 * @author D.chen.g
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class SysWxconfigService extends CrudService<SysWxconfigDao, SysWxconfig> {


}