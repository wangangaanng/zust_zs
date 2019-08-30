package com.zghzbckj.common;

/**
 * <dl>
 * <dt>BusinessException</dt>
 * <dd>Description: 业务公用异常类</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/5/22</dd>
 * </dl>
 *
 * @author xby
 */
public class BusinessException extends RuntimeException {

    public BusinessException(Object Obj) {
        super(Obj.toString());
    }

}