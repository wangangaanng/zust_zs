package com.zghzbckj.common;

/**
 * <dl>
 * <dt>JyContant</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
public class JyContant {
    //企业待审核 通过 禁用 拒绝
    public static final Integer QY_ZT_DSH = 1;
    public static final Integer QY_ZT_TG = 2;
    public static final Integer QY_ZT_JY = 3;
    public static final Integer QY_ZT_JJ = 4;
    public static final String SH_ERROR_MESSAGE = "企业统一税号输入有误";
    public static final String HMD_ERROR_MESSAGE = "企业已进入黑名单";
    public static final String SFZ_ERROR_MESSAGE = "法人身份证输入有误";
    public static final String SHCF_ERROR_MESSAGE = "已存在该企业统一税号";
    //职位 待审核 通过 拒绝 下架
    public static final Integer JOB_ZT_DSH = 0;
    public static final Integer JOB_ZT_TG = 2;
    public static final Integer JOB_ZT_JJ = 3;
    public static final Integer JOB_ZT_XJ = 6;
    //字典表
    //公司性质
    public static final Integer GSXZ = 20000;
    //行业类别
    public static final Integer HYLB = 20001;
    //公司规模
    public static final Integer GSGM = 20002;
    //工作职能
    public static final Integer GZZN = 20003;
    //工作性质
    public static final Integer GZXZ = 20004;
    //年龄要求
    public static final Integer NLYQ = 20005;
    //学历要求
    public static final Integer XLYQ = 20006;
    //语言要求
    public static final Integer YYYQ = 20007;
    //工作年限
    public static final Integer GZNX = 20008;
    //宣讲会自定义条件
    public static final Integer ZDYTJ = 20009;
    //开关
    public static final Integer KG = 10017;
    //职位审核
    public static final String ZWSH = "zwsh";
    //企业审核
    public static final String QYSH = "qykg";

    public static final String ZPHSH = "zphsh";
    public static final String XJHSH = "xjhsh";
    //报名类型 企业 学生01
    public static final int BMLX_QY = 0;
    public static final int BMLX_XS = 1;
    //报名对象 0招聘会 1宣讲会2职位
    public static final int BMDX_ZPH = 0;
    public static final int BMDX_XJH = 1;
    public static final int BMDX_ZW = 2;
    //职位类别 0职位 3招聘会 4宣讲会
    public static final int ZWLB_ZW = 0;
    public static final int ZWLB_ZPH = 3;
    public static final int ZWLB_XJH = 4;
    public static final String ZW_PRO = "浙江省";
    public static final String ZW_CITY = "杭州市";
    public static final String ZW_AREA = "西湖区";
    public static final String ZW_DD = "浙江科技学院";
    //中国地图省份
    public static final String[] provinceList = {"北京", "天津", "上海", "重庆", "河北", "河南", "云南", "辽宁", "黑龙江", "湖南", "安徽", "山东",
            "新疆", "江苏", "浙江", "江西", "湖北", "广西", "甘肃", "山西", "内蒙古", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏",
            "四川", "宁夏", "海南", "台湾", "香港", "澳门"};

    public static final String QY_PASS_MESS = "恭喜您，您注册的公司已通过审核";
    public static final String QY_REJECT_MESS = "很抱歉，您注册的公司未通过审核";
    public static final String ZPH_PASS_MESS = "恭喜您，您申请的招聘会已通过审核，展位编号：";
    public static final String ZPH_REJECT_MESS = "很抱歉，您申请的招聘会未通过审核。";
    public static final String XJH_PASS_MESS = "恭喜您，您申请的宣讲会已通过审核，学校联系人：";
}