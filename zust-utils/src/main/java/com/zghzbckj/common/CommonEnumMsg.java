package com.zghzbckj.common;

/**
 * <dl>
 * <dt>MerEnumMsg</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/4/24</dd>
 * </dl>
 *
 * @author xby
 */
public enum CommonEnumMsg {

    SUCCESS_MSG(200,"操作成功！"), PARAMETER_FAIL_MSG(10001,"参数不正确！"), INTER_FAIL_MSG(10002,"无接口权限！"),
    SIGN_FAIL_MSG(10003,"验签失败！"),FAIL_MSG(10004,"操作失败！"),ERROE_MSG(10099,"系统错误！");
    
    CommonEnumMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}