package com.zghzbckj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    static Pattern numberPattern = Pattern.compile("[0-9]*");
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
            nsaex.printStackTrace();
        }
    }

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 判断是否是正确的手机格式
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isEmpty(Object o) {
        if (null == o) {
            return true;
        }
        if ("".equals(o)) {
            return true;
        }
        if ("null".equals(o)) {
            return true;
        }
        return false;
    }

    public static Integer[] toIntegerArray(String s[]) {
        Integer toInt[] = new Integer[s.length];
        for (int i = 0; i < s.length; i++) {
            toInt[i] = Integer.valueOf(s[i]);
        }
        return toInt;
    }

    public static Byte[] toByteArray(byte b[]) {
        Byte byteWrapClass[] = new Byte[b.length];
        for (int i = 0; i < b.length; i++) {
            byteWrapClass[i] = new Byte(b[i]);
        }
        return byteWrapClass;
    }

    public static Long[] toLongArray(String s[]) {
        Long longWrapClass[] = new Long[s.length];
        for (int i = 0; i < s.length; i++) {
            longWrapClass[i] = new Long(s[i]);
        }

        return longWrapClass;
    }

    public static Float[] toFloatArray(String s[]) {
        Float floatWrapClass[] = new Float[s.length];
        for (int i = 0; i < s.length; i++) {
            floatWrapClass[i] = new Float(s[i]);
        }

        return floatWrapClass;
    }

    public static Double[] toDoubleArray(String s[]) {
        Double doubleWrapClass[] = new Double[s.length];
        for (int i = 0; i < s.length; i++) {
            doubleWrapClass[i] = new Double(s[i]);
        }

        return doubleWrapClass;
    }

    public static BigDecimal[] toBigDecimalArray(String s[]) {
        BigDecimal bigDecimalWrapClass[] = new BigDecimal[s.length];
        for (int i = 0; i < s.length; i++) {
            bigDecimalWrapClass[i] = new BigDecimal(s[i]);
        }

        return bigDecimalWrapClass;
    }

    public static Byte[][] toByteArray(String s[]) {
        Byte bs[][] = new Byte[0][];
        for (int i = 0; i < s.length; i++) {
            @SuppressWarnings("unused")
            Byte b[] = toByteArray(s[i].getBytes());
            // ArrayUtils.add(bs, b);
        }

        return bs;
    }

    @SuppressWarnings("unchecked")
    public static Collection toList(Object o[]) {

        return Arrays.asList(o);
    }

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return (new StringBuilder(String.valueOf(hexDigits[d1]))).append(
                hexDigits[d2]).toString();
    }

    public static String MD5(String str) {
        // 确定计算方法
        MessageDigest md5;
        String resultString = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md5.digest(str.getBytes()));/* 213 */
            resultString = resultString.substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        String c0 = hexDigits[(bt & 0xf0) >> 4];
        String c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    public static double calcRealPrice(Double rechargeMoney, Double discount) {
        if (isEmpty(rechargeMoney) || isEmpty(discount)) {
            return 0;
        }
        double count = rechargeMoney * discount;
        BigDecimal twoPcent = new BigDecimal(count);
        double rioMoney = twoPcent.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return rioMoney;
    }


    public static boolean isHourtime(String args) {
        if (isEmpty(args)) {
            return false;
        }
        String rep = "(([0-1][0-9])|2[0-3]):[0-5][0-9]";

        Pattern p = Pattern.compile(rep);
        return p.matcher(args).matches();
    }


    /***
     *<p>方法:toGBK TODO中文转GBK内码</p>
     *<ul>
     *<li> @param source TODO</li>
     *<li>@return java.lang.String  </li>
     *<li>@author D.cehn.g </li>
     *<li>@date 2018/2/6 15:45  </li>
     *</ul>
     */
    public static String toGBK(String source) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = source.getBytes("GBK");
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xff)).toUpperCase());
        }

        return sb.toString();
    }

    public static String GBK2Chinese(String GBKStr) throws Exception {
        byte[] b = HexString2Bytes(GBKStr);
        String chineseStr = new String(b, "gbk");//输入参数为字节数组
        return chineseStr;
    }

    //把16进制字符串转换成字节数组
    public static byte[] HexString2Bytes(String hexStr) {
        byte[] b = new byte[hexStr.length() / 2];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
        }
        return b;
    }


    /**
     * 判断是否是纯数字字符串
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = numberPattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String getUUID() {
        String uuid = "";
        uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        return uuid;
    }
}
