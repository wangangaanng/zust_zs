package com.zghzbckj.util;



import com.zghzbckj.base.config.UserInfoContext;
import com.zghzbckj.base.entity.DataWithExpEntity;
import com.zghzbckj.base.model.OurwaySysEmploys;
import com.zghzbckj.base.util.IdGen;


import java.util.Date;

public class CustomSaveALL {

    public static String preInsert(DataWithExpEntity object){
        object.setOwid(IdGen.uuid());
        OurwaySysEmploys userInfo = UserInfoContext.getUser();
        if (null != userInfo) {
            object.setCreator(userInfo.getOwid());
            if (!com.ourway.base.utils.TextUtils.isEmpty(userInfo.getEmpName()) && userInfo.getEmpName().length() <= 20) {
                object.setCreatorName(userInfo.getEmpName());
            }
        }

        if (null == object.getDelflg()) {
            object.setDelflg(0);
        }

        if (null == object.getState()) {
            object.setState(0);
        }

        object.setVer(1);
        object.setLastupdate(new Date());
        object.setCreatetime(object.getLastupdate());
        object.setVertime(object.getLastupdate());
        return object.getOwid();
    }





}
