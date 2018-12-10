package cn.junhui.springboot;

import cn.junhui.springboot.bean.Items;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 军辉
 * 2018-11-21 17:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {
    //读取单个单元格
    @Test
    public void testRead() throws Exception {
        //Excel文件--得到Excel工作簿对象
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:excel/测试.xlsx")));
        //Excel工作表--得到Excel工作表对象
        XSSFSheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();//总行数
        System.out.println("总行数：" + rowNum);
        XSSFRow row = null;

        /*
        封装items
         */
        String themeId;
        String theme;
        String op1;
        String op2;
        String op3;
        String op4;
        String correct;
        Integer score = 5;
        String note;


        for (int i = 1; i <= rowNum - 6; i += 7) {

            row = sheet.getRow(i + 0);//得到Excel工作表的行
            theme = row.getCell(0).toString();//得到Excel工作表指定行的单元格 坐标：[i,0]第i行第0列
            System.out.println("theme:" + theme);

            row = sheet.getRow(i + 1);
            op1 = row.getCell(0).toString();
            System.out.println("op1:" + op1);

            row = sheet.getRow(i + 2);
            op2 = row.getCell(0).toString();
            System.out.println("op2:" + op2);

            row = sheet.getRow(i + 3);
            op3 = row.getCell(0).toString();
            System.out.println("op3:" + op3);

            row = sheet.getRow(i + 4);
            op4 = row.getCell(0).toString();
            System.out.println("op4:" + op4);

            row = sheet.getRow(i + 5);
            correct = row.getCell(0).toString();
            System.out.println("correct" + correct);

            row = sheet.getRow(i + 6);
            note = row.getCell(0).toString();
            System.out.println("note:" + note);

            /*String op1 = row.getCell(i + 1).toString();
            System.out.println(op1);
            String op2 = row.getCell(i + 2).toString();
            System.out.println(op2);
            String op3 = row.getCell(i + 3).toString();
            System.out.println(op3);
            String op4 = row.getCell(i + 4).toString();
            System.out.println(op4);
            String current = row.getCell(i + 5).toString();
            System.out.println(current);
            String note = row.getCell(i + 6).toString();
            System.out.println(note);*/
        }
        System.out.println();

    }
}

