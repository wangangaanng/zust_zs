package com.ourway.ommonevent;

public enum BackMsg {
    LACKAPIURL("配置有误，请完善apiUrl！"),
    LACKMES("配置有误，请完善提示语！"),
    LACKGRIDID("配置有误，缺少grid！"),
    LACKGRID("配置有误，grid为空！"),
    LACKDATA("请选择一条记录进行提交！"),
    ONLYONE("只能选择一条记录！"),
    MULTISUBMIT("该记录已提交，不要重复提交！"),
    LACKBASEGRID("父页面未设定baseGrid"),
    SHOWTIP("提示：流程框是红色表示记录表示记录可修改，如果变成绿色则说明该流程已经提交给管理科室审核，不可修改，请谨慎提交。项目进度是不会变成全绿，这个是正常情况，因为项目进度需要后期不断的维护。"),
    INVOKEFAIL("接口调用失败！");


    private final String value;

    private BackMsg(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return value;
    }

}
