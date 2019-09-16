/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.wechat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zghzbckj.base.entity.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


/**
 * 单表生成Entity
 * @author D.chen.g
 * @version 2018-07-13
 */
public class SysWxtip extends DataEntity<SysWxtip> {
	
	private static final long serialVersionUID = 1L;
	private String msgid;		// msgid
	private String openid;		// openid
	private String appid;		// appid
	private Date msgDt;		// msg_dt
	private Integer msgCat;		// msg_cat
	private Integer msgType;		// msg_type
	private String msgContent;		// msg_content
	private String videoId;		// video_id
	private String videoTpId;		// video_tp_id
	private String linkTitle;		// link_title
	private String linkDesc;		// link_desc
	private String link;		// link
	private String gpsWd;		// gps_wd
	private String gpsJd;		// gps_jd
	private String gpsSf;		// gps_sf
	private String gpsMsg;		// gps_msg
	private String voiceId;		// voice_id
	private String voiceGs;		// voice_gs
	private String tpLink;		// tp_link
	private String tpId;		// tp_id
	private String tpFile;		// tp_file
	private String voiceFile;		// voice_file
	private String videoFile;		// video_file
	private String videoTpFile;		// video_tp_file
	private String hfr;		// hfr
	private Date hfsj;		// hfsj
	private String hfnr;		// hfnr
	private String language;		// language

	
	public SysWxtip() {
		super();
	}

	public Integer getMsgCat() {
		return msgCat;
	}

	public void setMsgCat(Integer msgCat) {
		this.msgCat = msgCat;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Length(min=0, max=100, message="msgid长度必须介于 0 和 100 之间")
	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
	@Length(min=0, max=100, message="openid长度必须介于 0 和 100 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=100, message="appid长度必须介于 0 和 100 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsgDt() {
		return msgDt;
	}

	public void setMsgDt(Date msgDt) {
		this.msgDt = msgDt;
	}
	

	
	@Length(min=0, max=640, message="msg_content长度必须介于 0 和 640 之间")
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@Length(min=0, max=128, message="video_id长度必须介于 0 和 128 之间")
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	@Length(min=0, max=128, message="video_tp_id长度必须介于 0 和 128 之间")
	public String getVideoTpId() {
		return videoTpId;
	}

	public void setVideoTpId(String videoTpId) {
		this.videoTpId = videoTpId;
	}
	
	@Length(min=0, max=640, message="link_title长度必须介于 0 和 640 之间")
	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
	
	@Length(min=0, max=640, message="link_desc长度必须介于 0 和 640 之间")
	public String getLinkDesc() {
		return linkDesc;
	}

	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}
	
	@Length(min=0, max=640, message="link长度必须介于 0 和 640 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=64, message="gps_wd长度必须介于 0 和 64 之间")
	public String getGpsWd() {
		return gpsWd;
	}

	public void setGpsWd(String gpsWd) {
		this.gpsWd = gpsWd;
	}
	
	@Length(min=0, max=64, message="gps_jd长度必须介于 0 和 64 之间")
	public String getGpsJd() {
		return gpsJd;
	}

	public void setGpsJd(String gpsJd) {
		this.gpsJd = gpsJd;
	}
	
	@Length(min=0, max=64, message="gps_sf长度必须介于 0 和 64 之间")
	public String getGpsSf() {
		return gpsSf;
	}

	public void setGpsSf(String gpsSf) {
		this.gpsSf = gpsSf;
	}
	
	@Length(min=0, max=640, message="gps_msg长度必须介于 0 和 640 之间")
	public String getGpsMsg() {
		return gpsMsg;
	}

	public void setGpsMsg(String gpsMsg) {
		this.gpsMsg = gpsMsg;
	}
	
	@Length(min=0, max=128, message="voice_id长度必须介于 0 和 128 之间")
	public String getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}
	
	@Length(min=0, max=64, message="voice_gs长度必须介于 0 和 64 之间")
	public String getVoiceGs() {
		return voiceGs;
	}

	public void setVoiceGs(String voiceGs) {
		this.voiceGs = voiceGs;
	}
	
	@Length(min=0, max=640, message="tp_link长度必须介于 0 和 640 之间")
	public String getTpLink() {
		return tpLink;
	}

	public void setTpLink(String tpLink) {
		this.tpLink = tpLink;
	}
	
	@Length(min=0, max=64, message="tp_id长度必须介于 0 和 64 之间")
	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
	
	@Length(min=0, max=128, message="tp_file长度必须介于 0 和 128 之间")
	public String getTpFile() {
		return tpFile;
	}

	public void setTpFile(String tpFile) {
		this.tpFile = tpFile;
	}
	
	@Length(min=0, max=128, message="voice_file长度必须介于 0 和 128 之间")
	public String getVoiceFile() {
		return voiceFile;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}
	
	@Length(min=0, max=128, message="video_file长度必须介于 0 和 128 之间")
	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}
	
	@Length(min=0, max=128, message="video_tp_file长度必须介于 0 和 128 之间")
	public String getVideoTpFile() {
		return videoTpFile;
	}

	public void setVideoTpFile(String videoTpFile) {
		this.videoTpFile = videoTpFile;
	}
	
	@Length(min=0, max=128, message="hfr长度必须介于 0 和 128 之间")
	public String getHfr() {
		return hfr;
	}

	public void setHfr(String hfr) {
		this.hfr = hfr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHfsj() {
		return hfsj;
	}

	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}
	
	@Length(min=0, max=640, message="hfnr长度必须介于 0 和 640 之间")
	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}
	
	@Length(min=0, max=16, message="language长度必须介于 0 和 16 之间")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	

	
}