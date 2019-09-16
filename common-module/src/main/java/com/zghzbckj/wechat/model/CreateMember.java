package com.zghzbckj.wechat.model;

import java.util.List;

/**
 *创建企业用户
 */
public class CreateMember {
    //对应管理端的帐号，企业内必须唯一
    private String userid;
    //name成员名称。长度为1~64个utf8字符
    private String name;
//mobile手机号码。企业内必须唯一
    private String mobile;
    //成员所属部门id列表,不超过20个
    private List<Integer> department;
    private List<Integer> is_leader_in_dept;
//    private String order;
//    private String position;
//    private String gender;
//    private String email;
//    private String enable;
//    private String avatar_mediaid;
//    private String telephone;
//    private String address;
//
//    private List extattr;
//
//    private String to_invite;
//
//    private String external_position;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }
//
//    public String getOrder() {
//        return order;
//    }
//
//    public void setOrder(String order) {
//        this.order = order;
//    }
//
//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public List<Integer> getIs_leader_in_dept() {
        return is_leader_in_dept;
    }

    public void setIs_leader_in_dept(List<Integer> is_leader_in_dept) {
        this.is_leader_in_dept = is_leader_in_dept;
    }
//
//    public String getEnable() {
//        return enable;
//    }
//
//    public void setEnable(String enable) {
//        this.enable = enable;
//    }
//
//    public String getAvatar_mediaid() {
//        return avatar_mediaid;
//    }
//
//    public void setAvatar_mediaid(String avatar_mediaid) {
//        this.avatar_mediaid = avatar_mediaid;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public List getExtattr() {
//        return extattr;
//    }
//
//    public void setExtattr(List extattr) {
//        this.extattr = extattr;
//    }
//
//    public String getTo_invite() {
//        return to_invite;
//    }
//
//    public void setTo_invite(String to_invite) {
//        this.to_invite = to_invite;
//    }
//
//    public String getExternal_position() {
//        return external_position;
//    }
//
//    public void setExternal_position(String external_position) {
//        this.external_position = external_position;
//    }
}
