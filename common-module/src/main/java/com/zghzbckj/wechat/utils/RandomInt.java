package com.zghzbckj.wechat.utils;

import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.util.CacheUtil;

import java.util.Random;

/**
 * <p>方法: 随机产生int </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2018/2/10 15:50  </li>
 * </ul>
 */
public class RandomInt {

    //redis中的前缀
    public static String INT_REDIS_PRE = "apps.sys.random.";

    /**
     * <p>方法:getRandom 随机产生int数 </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return int  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/10 15:51  </li>
     * </ul>
     */
    public static int getRandom() {
        int intUnbounded = new Random().nextInt(10000);
        return intUnbounded;
    }

    /**
    *<p>方法:doGetUniqueID 获取没有使用过的int标记 </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return int  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2018/2/10 15:56  </li>
    *</ul>
    */
    public static int doGetUniqueID() {
        int rint = getRandom();
        boolean flag = true;
        while (flag) {
            if (TextUtils.isEmpty(CacheUtil.getVal(INT_REDIS_PRE + rint))) {
                CacheUtil.setVal(INT_REDIS_PRE + rint, "-1");
                flag = false;
                return rint;
            } else {
                rint = getRandom();
            }

        }
        return -1;
    }

}
