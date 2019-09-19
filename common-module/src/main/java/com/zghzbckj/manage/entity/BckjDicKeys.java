/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zghzbckj.manage.entity;

import com.zghzbckj.base.entity.DataWithExpEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ccEntity
 * @author cc
 * @version 2019-09-09
 */
public class BckjDicKeys extends DataWithExpEntity<BckjDicKeys> {
	
	private static final long serialVersionUID = 1L;

	private String keyWord;		// key_word
	private String memo;		// memo

	private Integer updateFlag;

	
	public BckjDicKeys() {
		super();
	}


	
	@Length(min=0, max=100, message="key_word长度必须介于 0 和 100 之间")
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	@Length(min=0, max=1000, message="memo长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(Integer updateFlag) {
		this.updateFlag = updateFlag;
	}
}