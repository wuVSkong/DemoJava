package com.http;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TestDemo extends Thread {

    private String name;

    public TestDemo(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行：" + i);
        }
    }

    public static void main(String[] args) {
		getDaySub("20190505", "20190606");

//        inPut();
    }

    /***
     * 根据开始时间和结束时间计算天数和money。
     * @param starTimeStr
     * @param endTimeStr
     */
    private static void getDaySub(String starTimeStr, String endTimeStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date starTime;
        Date endTime;
        long day;
        try {
            starTime = format.parse(starTimeStr);
            endTime = format.parse(endTimeStr);
            System.out.println(starTime + "---" + endTime + "\n" + starTime.getTime() + "---" + endTime.getTime() + "\n" + (endTime.getTime() - starTime.getTime()));

            day = (endTime.getTime() - starTime.getTime()) / 1000 / 60 / 60 / 24;
            System.out.println("天数：" + day);
            System.out.println("money：" + day * 260);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendClassMsg(String str) {
//		Class clazz = Class.forName(str);

    }

    public static void importExcelAll() throws IOException {
        File file = new File("C:/Users/AdminWK/Desktop/jsontest.txt");
        InputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("C:/Users/AdminWK/Desktop/excelTest.xls");
        byte[] byt = new byte[(int) file.length()];
        fis.read(byt);
        String json = new String(byt, "GBK");
        System.out.println(json);
        fis.close();

        JSONObject obj = JSONObject.fromObject(json);
        JSONArray array = obj.getJSONArray("TDMER23");
        List<Map<String, String>> list = new ArrayList<>();
        Iterator<JSONObject> iobj = array.iterator();
        while (iobj.hasNext()) {
            JSONObject obj1 = iobj.next();
            System.out.println(obj1.toString());
            list.add(obj1);
        }
        System.out.println(list.toString());

        HSSFWorkbook wkbook = new HSSFWorkbook();
        HSSFCellStyle ccs = wkbook.createCellStyle();
        ccs.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        ccs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFSheet sheet = wkbook.createSheet("设备维修");

        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short) 500);

        int g = 0;
        for (String str : list.get(0).keySet()) {
            HSSFCell cell = row1.createCell(g);
            cell.setCellValue(str);
            cell.setCellStyle(ccs);
            g++;
        }


        for (int i = 1; i < list.size() + 1; i++) {
            HSSFRow row = sheet.createRow(i);
            int j = 0;
            for (String str : list.get(i - 1).keySet()) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(list.get(i - 1).get(str));
                j++;
            }
        }

        wkbook.write(fos);
        fos.close();


    }

    public static void exportExcel() throws IOException {
        //创建表头的字段
        String[] str = {"姓名", "班级", "籍贯"};
        //创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet页
        HSSFSheet sheet = workbook.createSheet("sheet");
        //创建文件流
        FileOutputStream out;
        //创建表头
        HSSFRow head = workbook.getSheet("sheet").createRow(0);

        for (int i = 0; i < str.length; i++) {
            HSSFCell cell = head.createCell(i);
            cell.setCellValue(str[i]);
        }

        for (int i = 1; i < 9; i++) {
            HSSFRow row = sheet.createRow(i);

            for (int j = 0; j < 9; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(i);
            }
        }

        out = new FileOutputStream("F:/模板导入2.xls");
        workbook.write(out);
        out.close();
        System.out.println("创建成功！");
    }

    public static void importExcel() throws InvalidFormatException, IOException {
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        File file = new File("F:/模板导入1.xls");
        InputStream inputStream = new FileInputStream(file);
        Workbook work = WorkbookFactory.create(inputStream);
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            row = sheet.getRow(0);
            String[] title = null;

            if (row != null) {
                title = new String[row.getLastCellNum()];
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String key = (String) getCellValue(cell);
                    title[y] = key;
                }
            } else {
                continue;
            }

            Demo demo = new Demo();
            // 遍历当前sheet中的所有行
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                Map<String, Object> map = new HashMap<String, Object>();
                // 遍历所有的列
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String key = title[y];
                    if (!"none".equals(key)) {
                        map.put(key, getCellValue(cell));
                    }
                }
                ls.add(map);
            }

        }


        inputStream.close();
    }

    /**
     * 方法说明
     *
     * @param cell 单元格
     * @return 单元格值
     * @author dada
     */
    public static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        String value = null;
        try {
            DecimalFormat df = new DecimalFormat("0");
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING://字符
                    value = cell.getStringCellValue().trim();
                    value = StringUtils.isEmpty(value) ? "" : value;
                    break;
                case Cell.CELL_TYPE_BOOLEAN://boolean
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA://公式
                    value = String.valueOf(cell.getCellFormula().trim());
                    break;
                case Cell.CELL_TYPE_NUMERIC://数字

                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue().toString();
                    } else {
                        value = df.format(cell.getNumericCellValue());
                        // if(value.contains(".0")){
                        ///     value = value.substring(0, value.length()-2);
                        //  }
                    }
                    break;

                case Cell.CELL_TYPE_BLANK://空值
                case Cell.CELL_TYPE_ERROR://错误
                    value = "";
                    break;
                default:
                    value = cell.toString().trim();
                    break;
            }
        } catch (Exception ex) {
            value = "";
        }
        return value;
    }

    /**
     * 字节流的输出
     *
     * @throws IOException
     */
    private static void outPut() throws IOException {
        String str = "hello word!！！！！can you help me!!";
        byte[] byt = str.getBytes();

        File file = new File("F:/demo.txt");
        OutputStream out = new FileOutputStream(file);

        out.write(byt);
        out.close();
        System.out.println("输出成功！");
    }

    /**
     * 字节流的输入
     *去除文本每行前面的数字
     * @throws IOException
     */
    private static void inPut() {
        try {
            File file = new File("C:/Users/AdminWK/Desktop/demo1.txt");
            InputStream inp = new FileInputStream(file);

            byte[] byt = new byte[(int) file.length()];

            inp.read(byt);

            String result = new String(byt);
            String[] results = result.split("\r");
            StringBuffer strResult = new StringBuffer();
            for (int i = 0; i < results.length; i++) {
                String s = results[i];
                s = s.substring(4) + "\n";
                strResult.append(s);
                System.out.println(s);
            }
            System.out.println(strResult.toString());
//			System.out.println(new String(byt));

            File file1 = new File("C:/Users/AdminWK/Desktop/demo2.txt");

            Writer writer = new FileWriter(file1);

            writer.write(strResult.toString());
            writer.close();

            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outWriter() throws IOException {
        String str = "字符流的输出！！！-->输入";
        File file = new File("F:/demo1.txt");

        Writer writer = new FileWriter(file);

        writer.write(str);
        writer.close();
        System.out.println("字符流输出成功！");

    }

    private static void inReader() throws IOException {
        File file = new File("F:/demo1.txt");
        Reader reader = new FileReader(file);

        char[] c = new char[(int) file.length()];
        reader.read(c);
        System.out.println(new String(c));
        reader.close();
    }

    private static void inPutReader() throws IOException {
        File file = new File("F:/demo.txt");
        InputStream inp = new FileInputStream(file);
        char[] cha = new char[(int) file.length()];

        Reader reader = new InputStreamReader(inp);
        reader.read(cha);
        System.out.println(new String(cha));
        reader.close();

    }

}

abstract class DemoAbs {
    public void init() {
        System.out.println("测试抽象类。");
    }

    public abstract void demo();
}

class exceAbs extends DemoAbs {

    public void init() {
        System.out.println("开玩笑！");
    }

    @Override
    public void demo() {
        // TODO Auto-generated method stub

    }


}

