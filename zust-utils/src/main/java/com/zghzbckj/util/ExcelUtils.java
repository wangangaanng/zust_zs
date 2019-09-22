package com.zghzbckj.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackson on 2018/5/16.
 */
public class ExcelUtils {

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


    public ArrayList<ArrayList<String>> xlsx_reader(String excel_url) throws IOException {
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

    public static void main(String[] args) {
//        String[] title = {"公司名称", "名字", "号码"};
//        List<List<String>> result = new ArrayList<>();
//        List<String> list = new ArrayList();
//        list.add("111");
//        list.add("111");
//        list.add("111");
//        result.add(list);
//        String fileOutPath = "E:\\excel\\result.xls";
//        writeContentToExcel(title, result, fileOutPath);
//        ExcelUtils test = new ExcelUtils();
//        ArrayList<ArrayList<String>> arr = null;  //后面的参数代表需要输出哪些列，参数个数可以任意
//        try {
//            arr = test.xlsx_reader("E:\\excel\\excel2018-01.xlsx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < arr.size(); i++) {
//            ArrayList<String> row = arr.get(i);
//            for (int j = 0; j < row.size(); j++) {
//                System.out.print(row.get(j) + " ");
//            }
//            System.out.println("");
//        }

        rename("C:\\Users\\MJG\\Desktop\\qww\\1527333246939");
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
}
