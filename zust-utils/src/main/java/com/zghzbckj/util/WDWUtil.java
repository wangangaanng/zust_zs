package com.zghzbckj.util;
/**
 * Created by jackson on 17-8-8.
 */
class WDWUtil {
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
