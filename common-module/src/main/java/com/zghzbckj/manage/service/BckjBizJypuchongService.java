package com.zghzbckj.manage.service;


import com.zghzbckj.base.service.CrudService;
import com.zghzbckj.manage.dao.BckjBizJypuchongDao;
import com.zghzbckj.manage.entity.BckjBizJypuchong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 就业方案补充类Service
 * @author wangangaanng
 * @version 2019-10-10
 */
@Service
@Transactional(readOnly = true)
public class BckjBizJypuchongService extends CrudService<BckjBizJypuchongDao, BckjBizJypuchong> {
	 private static final Logger log = LoggerFactory.getLogger(BckjBizJypuchongService.class);


    public void deleteByXsxh(String xsxh) {
        this.dao.deleteByXsxh(xsxh);
    }


    public void updateJyscheme(BckjBizJypuchong bckjBizJypuchong) {
        this.dao.updateJyscheme(bckjBizJypuchong);
    }

    /**
     * 根据用户id 更新学号
     * @param bckjBizJypuchong
     */
    public void updateXsxhByHyOwid( BckjBizJypuchong bckjBizJypuchong){
        this.dao.updateXsxhByHyOwid(bckjBizJypuchong);
    }
}