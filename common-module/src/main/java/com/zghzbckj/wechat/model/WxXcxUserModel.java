package com.zghzbckj.wechat.model;

import java.io.Serializable;

/**
 * <dl>
 * <dt>WxXcxUserModel</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2017</dd>
 * <dd>Company:步长科技有限公司</dd>
 * <dd>CreateDate: 2017/10/16</dd>
 * </dl>
 *
 * @author xby
 */
public class WxXcxUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openId;
    private String nickname;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionid;
    private String  userOwid;
    private String compOwid;
    private Integer isBind;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserOwid() {
        return userOwid;
    }

    public void setUserOwid(String userOwid) {
        this.userOwid = userOwid;
    }

    public String getCompOwid() {
        return compOwid;
    }

    public void setCompOwid(String compOwid) {
        this.compOwid = compOwid;
    }

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }
}