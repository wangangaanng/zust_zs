package com.zghzbckj.util;

import com.ourway.base.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.crypto.interfaces.PBEKey;
import javax.xml.crypto.Data;
import java.io.*;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jackson on 17-8-8.
 */

/**
 * @描述：测试excel读取 导入的jar包
 * <p/>
 * poi-3.8-beta3-20110606.jar
 * <p/>
 * poi-ooxml-3.8-beta3-20110606.jar
 * <p/>
 * poi-examples-3.8-beta3-20110606.jar
 * <p/>
 * poi-excelant-3.8-beta3-20110606.jar
 * <p/>
 * poi-ooxml-schemas-3.8-beta3-20110606.jar
 * <p/>
 * poi-scratchpad-3.8-beta3-20110606.jar
 * <p/>
 * xmlbeans-2.3.0.jar
 * <p/>
 * dom4j-1.6.1.jar
 * <p/>
 * jar包官网下载地址：http://poi.apache.org/download.html
 * <p/>
 * 下载poi-bin-3.8-beta3-20110606.zipp
 */

public class ExcelUtils {
    /**
     * 总行数
     */
    private int totalRows = 0;
    /**
     * 总列数
     */
    private int totalCells = 0;
    /**
     * 错误信息
     */
    private String errorInfo;

    /**
     * 构造方法
     */
    public ExcelUtils() {
    }


    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null
                || !(WDWUtil.isExcel2003(filePath) || WDWUtil
                .isExcel2007(filePath))) {
            errorInfo = "文件名不是excel格式";
            return false;
        }
        /** 检查文件是否存在 */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            errorInfo = "文件不存在";
            return false;
        }
        return true;
    }

    public List<List<String>> read(String filePath) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        InputStream is = null;
        try {
            /** 验证文件是否合法 */
            if (!validateExcel(filePath)) {
                System.out.println(errorInfo);
                return null;
            }
            /** 判断文件的类型，是2003还是2007 */
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(filePath)) {
                isExcel2003 = false;
            }
            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, isExcel2003);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        /** 返回最后读取的结果 */
        return dataLst;
    }


    public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataLst = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = read(wb);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return dataLst;
    }


    private List<List<String>> read(Workbook wb) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(0);
        /** 得到Excel的行数 */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行 */
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
//                            DecimalFormat df = new DecimalFormat("0");
//                            cellValue = df.format(cell.getNumericCellValue());
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
                rowLst.add(cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
        return dataLst;
    }

    public static void main(String[] args) throws Exception {
        ExcelUtils poi = new ExcelUtils();
        List<List<String>> list = poi.read("/home/jackson/Desktop/test.xlsx");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {     //行循环
                List<String> cellList = list.get(i);
//                    for (int j = 0; j < cellList.size(); j++) {     //列循环
//                        System.out.print("    " + cellList.get(j));
//                    }
                String studentNumber = cellList.get(5);     //学号的列数
                String AdminssionCode = cellList.get(2);
                System.out.println(studentNumber + "====" + AdminssionCode);
            }

        }

    }
    public static String stmodifyExcelData(String data){
        data.replaceAll(" ", "");
        int dianCount=0;
        int eCount =0;
        int finalNum=0;
        if(data.indexOf(".")!=-1&&data.indexOf("E")==-1){
            data=data.substring(0, data.indexOf("."));
        }else if(data.indexOf(".")!=-1&&data.indexOf("E")!=-1){
            dianCount=data.indexOf(".");
            data=data.replaceAll("\\.", "");
                eCount=data.indexOf("E");
                finalNum=Integer.parseInt(data.substring(data.indexOf("E")+1));
                if((eCount-dianCount)<finalNum){
                    data = data.substring(0, data.indexOf("E"));
                    for(int count=0;count<(finalNum-(eCount-dianCount));count++){
                        data=data+"0";
                    }
                }else {
                    data=data.substring(0,data.indexOf("E"));
                }
        }
        return data;
    }
    public static String modifyExcelData(String data){
        return data.substring(0,data.indexOf(".")) ;
    }

    /***
     *<p>功能描述：writeContentToExcel 将内容写入excel文件</p>
     *<ul>
     *<li>@param []</li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 2018/5/16 16:45</li>
     *</ul>
     */
    public static void writeContentToExcel(String[] title, List<List<String>> dataList, String fileOutPutUrl) {
        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        //插入第一行数据的表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //写入数据
        for (int i = 0; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            HSSFRow nrow = sheet.createRow(i + 1);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell ncell = nrow.createCell(j);
                ncell.setCellValue(list.get(j));
            }
        }
        //创建excel文件
        //"E:\\excel\\result.xlsx"
        File file = new File(fileOutPutUrl);
        try {
            file.createNewFile();
            //将excel写入
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<ArrayList<String>> xlsx_reader(String excel_url) throws IOException {
        //读取xlsx文件
        XSSFWorkbook xssfWorkbook = null;
        //寻找目录读取文件
        InputStream is = new FileInputStream(new File(excel_url));
        xssfWorkbook = new XSSFWorkbook(is);
        if (xssfWorkbook == null) {
            System.out.println("未读取到内容,请检查路径！");
            return null;
        }
        ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
        //遍历xlsx中的sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                int coloumNum = xssfRow.getPhysicalNumberOfCells();
                if (xssfRow == null) {
                    continue;
                }
                ArrayList<String> curarr = new ArrayList<String>();
                for (int columnNum = 0; columnNum < coloumNum; columnNum++) {
                    XSSFCell cell = xssfRow.getCell(columnNum);
                    curarr.add(Trim_str(getValue(cell)));
                }
                ans.add(curarr);
            }
        }
        return ans;
    }

    //判断后缀为xlsx的excel文件的数据类
    @SuppressWarnings("deprecation")
    private static String getValue(XSSFCell xssfRow) {
        if (xssfRow == null) {
            return "---";
        }
        if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            double cur = xssfRow.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if (Double.parseDouble(longVal + ".0") == cur) {
                inputValue = longVal;
            } else {
                inputValue = cur;
            }
            return String.valueOf(inputValue);
        } else if (xssfRow.getCellType() == Cell.CELL_TYPE_BLANK || xssfRow.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "---";
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    //判断后缀为xls的excel文件的数据类型
    @SuppressWarnings("deprecation")
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell == null) {
            return "---";
        }
        if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            double cur = hssfCell.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if (Double.parseDouble(longVal + ".0") == cur) {
                inputValue = longVal;
            } else {
                inputValue = cur;
            }
            return String.valueOf(inputValue);
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_BLANK || hssfCell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "---";
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    //字符串修剪  去除所有空白符号 ， 问号 ， 中文空格
    static private String Trim_str(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("[\\s\\?]", "").replace("　", "");
    }

    public static String rename(String path){

        //文件夹路径
        File folder = new File(path);
        //判断文件夹是否存在
        if(!folder.exists()){
            return "文件夹不存在";
        }
        //文件夹下所有的文件数组
//        File[] files = folder.listFiles();
        //重命名
//        for (File file :
//                files) {
//
        File newFile = new File(folder.getAbsolutePath() + ".jpg");
        folder.renameTo(newFile);
//        }
        return "重命名成功！";
    }

    public static String rename1(String path,String extion){

        //文件夹路径
        File folder = new File(path);
        //判断文件夹是否存在
        if(!folder.exists()){
            return "文件夹不存在";
        }
        //文件夹下所有的文件数组
//        File[] files = folder.listFiles();
        //重命名
//        for (File file :
//                files) {
//
        File newFile = new File(folder.getAbsolutePath() + extion);
        folder.renameTo(newFile);
//        }
        return "重命名成功！";
    }

    public static Date stringtoDate(String str) throws ParseException {
        String year=str.substring(0,4);
        String month=str.substring(4,6);
        String day=str.substring(6,8);
        String dateStr= (year+"-"+month+"-"+day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate=sdf.parse(dateStr);
        return  utilDate;
    }
    public static Date stringtoDatec(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate=sdf.parse(str);
        return  utilDate;
    }


}


