package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;

public class BckjBizJobPlanOther  extends DataWithExpEntity<BckjBizJobPlanOther> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String code;
    private String val;
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
