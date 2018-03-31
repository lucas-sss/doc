package com.example.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/7 0007
 */
public class PoiTest {

    public static void main(String[] args) throws Exception {


        /*<insert id="" parameterType="List">
                INSERT INTO `police` (``, ``,``,``)
        VALUES
                <foreach collection="list" item="case" separator=",">
                (#{case.name},#{case.age},#{case.sex},#{case.tt})
        </foreach>
        </insert>*/


        // 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 获得连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, user, password);
        // 获得执行sql的对象
        Statement stmt = conn.createStatement();









        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#");

        InputStream in = new FileInputStream(new File("D:\\协查数据导入.xlsx"));


        XSSFWorkbook book = new XSSFWorkbook(in);


        XSSFSheet sheet = book.getSheetAt(0);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= sheet.getLastRowNum(); i++){

            XSSFRow row = sheet.getRow(i);

            XSSFCell cell = row.getCell(0);

//            String a = row.getCell(0).toString();
            String a = "";



            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        a = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        break;
                    }
                    a = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    a = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    a = cell.getCellFormula();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    a = "";
                    break;
                default:
                    a = "";
            }



            String b = row.getCell(1).toString();
            String c = row.getCell(2).toString();
            String d = row.getCell(3).toString();
            String e = row.getCell(4).toString();
            String f = row.getCell(5).toString();
            String g = row.getCell(6).toString();
            String h = row.getCell(7).toString();


            System.out.println("第" + i + "条记录：" + a + "_" + b + "_" + c + "_" + d + "_" + e + "_" + f + "_" + g + "_" + h);

            // 执行sql 获得结果
            String sql = "insert INTO `police` (`a`,`b`,`c`,`d`,`e`,`f`,`g`,`h`) VALUES (" + "'" + a + "','" + b + "','" + c + "','" + d + "','" + e + "','" + f + "','" + g + "','" + h + "')";
            System.out.println(sql);
            // 返回的int值:表示影响的行数
            int sum = stmt.executeUpdate(sql);
            // 处理结果
            System.out.println(sum);
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

        // 释放资源
        stmt.close();
        conn.close();
    }

}
