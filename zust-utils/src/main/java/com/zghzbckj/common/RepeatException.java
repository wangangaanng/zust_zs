package com.zghzbckj.common;


/**
 * <dl>
 * <dt>RepeatException</dt>
 * <dd>Description:xsxh录入出现重复异常</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/9/22</dd>
 * </dl>
 *
 * @author wangangaanng
 */
public class RepeatException extends Exception {
    public RepeatException(String error) {
        super(error);
    }
}
