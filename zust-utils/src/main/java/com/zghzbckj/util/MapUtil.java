package com.zghzbckj.util;

import com.google.common.collect.Maps;
import com.ourway.base.utils.TextUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>功能描述: </p >
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author </li>
 * <li>@date 2019-02-10 19:46</li>
 * </ul>
 */
public class MapUtil {

    /**
     * <p>功能描述:easySetByMap</p >
     * <ul>
     * <li>@param </li>
     * <li>@return </li>
     * <li>@throws </li>
     * <li>@author xubiyu</li>
     * <li>@date 2019-02-10 19:51</li>
     * </ul>
     */
    public static void easySetByMap(Map map, Object obj) {
        try {
            // 取出bean里的所有方法
            Method[] methods = obj.getClass().getMethods();
            for (int i = 0; i < methods.length; i++) {
                // 取方法名
                String method = methods[i].getName();
                // 取出方法的类型
                Class[] cc = methods[i].getParameterTypes();
                if (cc.length != 1) {
                    continue;
                }
                // 如果方法名没有以set开头的则退出本次for
                if (method.indexOf("set") < 0) {
                    continue;
                }
                //类型
                String type = cc[0].getSimpleName();
                // 转成小写
                Object value = method.substring(3, 4).toLowerCase() + method.substring(4);
                // 如果map里有该key
                if (map.containsKey(value) && map.get(value) != null) {
                    // 调用其底层方法
                    setValue(type, map.get(value), i, methods, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setValue(String type, Object value, int i, Method[] method, Object bean) {
        if (!TextUtils.isEmpty(value)&&!"[]".equals(value.toString())) {
            try {
                if ("String".equals(type)) {
                    // 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
                    method[i].invoke(bean, new Object[]{value});
                } else if ("int".equals(type) || "Integer".equals(type)) {
                    method[i].invoke(bean, new Object[]{new Integer("" + value)});
                } else if ("double".equals(type) || "Double".equals(type)) {
                    method[i].invoke(bean, new Object[]{new Double("" + value)});
                } else if ("byte".equals(type) || "Byte".equals(type)){
                    method[i].invoke(bean, new Object[]{new Byte("" + value)});
                }else if ("float".equals(type) || "Float".equals(type)) {
                    method[i].invoke(bean, new Object[]{new Float("" + value)});
                } else if ("long".equals(type) || "Long".equals(type)) {
                    method[i].invoke(bean, new Object[]{new Long("" + value)});
                } else if ("boolean".equals(type) || "Boolean".equals(type)) {
                    method[i].invoke(bean, new Object[]{Boolean.valueOf("" + value)});
                } else if ("BigDecimal".equals(type)) {
                    method[i].invoke(bean, new Object[]{new BigDecimal("" + value)});
                } else if ("Date".equals(type)) {
                    Date date = null;
                    if ("java.util.Date".equals(value.getClass().getName())) {
                        date = (Date) value;
                    } else {
                        String format = ((String) value).indexOf(":") > 0 ? "yyyy-MM-dd hh:mm:ss" : "yyyy-MM-dd";
                        SimpleDateFormat sf = new SimpleDateFormat();
                        sf.applyPattern(format);
                        date = sf.parse((String) (value));
                    }
                    if (date != null) {
                        method[i].invoke(bean, new Object[]{date});
                    }
                }else if ("byte[]".equals(type)) {
                    method[i].invoke(bean, new Object[]{new String(value + "").getBytes()});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据参数组织一个map 单数 为key 双数为val
     *
     * @param argsArray 参数数组
     * @return map
     */
    public static Map<? extends Object, Object> getMapFromArgs(Object[] argsArray) {
        Map<Object, Object> resultMap = Maps.newHashMap();
        for (int i = 0; (i + 1) < argsArray.length; i = (i + 2)) {
            resultMap.put(argsArray[i], argsArray[i + 1]);
        }
        return resultMap;
    }
}
