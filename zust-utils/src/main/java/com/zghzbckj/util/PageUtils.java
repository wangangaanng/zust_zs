package com.zghzbckj.util;


import com.zghzbckj.base.entity.Page;
import com.zghzbckj.base.entity.PageInfo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * <dl>
 * <dt>PageUtils</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/4/9</dd>
 * </dl>
 *
 * @author xby
 */
public class PageUtils {
    private static Pattern patPunc = Pattern.compile("[~！@#￥……&*（）——|【】‘；：”“'。，、？]");

    /**
     * <p>功能描述:组装pageInfo</p >
     * <ul>
     * <li>@param </li>
     * <li>@return com.zghzbckj.base.entity.PageInfo<org.apache.poi.ss.formula.functions.T></li>
     * <li>@throws </li>
     * <li>@author xubiyu</li>
     * <li>@date 2019-02-13 10:38</li>
     * </ul>
     */
    public static <T> PageInfo<T> assimblePageInfo(Page<T> page) {
        PageInfo<T> pageInfo = new PageInfo();
        pageInfo.setRecords(page.getList());
        pageInfo.setTotalPage((long) page.getTotalPage());
        pageInfo.setCurrentIndex((long) page.getPageNo());
        pageInfo.setPageSize((long) page.getPageSize());
        pageInfo.setTotalCount(page.getCount());
        pageInfo.setCurrentPage((long) page.getPageNo());
        return pageInfo;
    }





    /**
     *<p>功能描述: 组装自定义pageInfo</p >
     *<ul>
     *<li>@param </li>
     *<li>@return com.zghzbckj.base.entity.PageInfo<T></li>
     *<li>@throws </li>
     *<li>@author xubiyu</li>
     *<li>@date 2019-02-17 20:31</li>
     *</ul>
     */
    public static <T> PageInfo<T> assimblePageInfoBySelf(List<T> list , Class<T> type, Integer pageNo, Integer pageSize,
                                                         Integer totalNum ){
        if(pageSize == 0){
            pageSize = 1;
        }
        PageInfo<T> pageInfo = new PageInfo();
        pageInfo.setRecords(list);
        pageInfo.setTotalPage(Integer.valueOf(String.valueOf(totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1)));
        pageInfo.setCurrentIndex((long)pageNo);
        pageInfo.setPageSize((long)pageSize);
        pageInfo.setTotalCount(totalNum);
        pageInfo.setCurrentPage((long)pageNo);
        return pageInfo;
    }

    public static <E> PageInfo<E> assimblePageInfoBySelfKey(List<E> list, Integer pageNo, Integer pageSize,
                                                            Integer totalNum ){
        if(pageSize == 0){
            pageSize = 1;
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRecords(list);
        pageInfo.setTotalPage(Integer.valueOf(String.valueOf(totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1)));
        pageInfo.setCurrentIndex((long)pageNo);
        pageInfo.setPageSize((long)pageSize);
        pageInfo.setTotalCount(totalNum);
        pageInfo.setCurrentPage((long)pageNo);
        return pageInfo;
    }

    /**
     * <p>功能描述:isChinesePunctuation 判断是否包含中文标点符号</p>
     * <ul>
     * <li>@param str </li>
     * <li>@return boolean</li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2019/4/18 10:18</li>
     * </ul>
     */
    public static boolean isChinesePunctuation(String str) {
        Matcher matcher = patPunc.matcher(str);
        return matcher.find();
    }

    /**
     * <p>功能描述:isChinaPhoneLegal 判断是否手机号</p>
     * <ul>
     * <li>@param str </li>
     * <li>@return boolean</li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2019/5/5 15:05</li>
     * </ul>
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}