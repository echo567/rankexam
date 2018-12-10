package cn.junhui.springboot.service;

import cn.junhui.springboot.bean.Items;
import cn.junhui.springboot.mapper.ItemsMapper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 军辉
 * 2018-10-29 10:55
 */
@Service
public class ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    /*
    查询所有考题
     */
    public List<Items> getAllItems() {
        List<Items> list = itemsMapper.getAllItem();
        return list;
    }

    /*
    根据试卷id查询试题
     */
    public List<Items> getallByPaperId(Integer paperId) {
        List<Items> list = itemsMapper.getallByPaperId(paperId);
        return list;
    }

    /*
    增加一道试题 试题id自增
     */
    public void addItem(Items it) {
        /*
        此时未设置试卷id
        默认设置为1
         */

        itemsMapper.addItem(it.getPaperId(), it.getTheme(), it.getOp1(), it.getOp2(), it.getOp3(), it.getOp4(), it.getCorrect(), it.getScore(), it.getNote());
    }


    /*
    增加一道试题 试题id非自增
    */
    public void addItem_plus(Items it) {
        itemsMapper.addItem_plus(it.getPaperId(), it.getThemeId(), it.getTheme(), it.getOp1(), it.getOp2(), it.getOp3(), it.getOp4(), it.getCorrect(), it.getScore(), it.getNote());
    }

    /*
    批量增加试题
     */
    public void batPapAdd(List<Items> list) {
        itemsMapper.batPapAdd(list);
    }

    /*
    批量删除试题
     */
    public void batPapDel(List<Items> list) {
        itemsMapper.batPapDel(list);
    }

    /*
    多条件查询试题
     */
    public Items selPap(Integer paperId, Integer themeId) {
        Items items = itemsMapper.selPap(paperId, themeId);
        return items;
    }

    /*
    动态修改试题
     */
    public void dynamicItemUdp(Items items) {
        itemsMapper.dynamicItemUdp(items);
    }

    /*
    删除试题
     */
    public void deleteItem(Items items) {
        itemsMapper.deleteItem(items.getPaperId(), items.getThemeId());
    }

    /*
    解析excel文件添加数据到数据库
     */
    public Items toFile(MultipartFile file, Integer paperId) {
        XSSFWorkbook wb = null;
        Items items = null;
        try {
            /*
            解析指定路径的excel文件
            wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:excel/测试.xlsx")));
            */
            wb = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Excel工作表--得到Excel工作表对象
        excel文件左下方的标识
         */
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();//总行数
        System.out.println("总行数：" + rowNum);
        XSSFRow row = null;

        /*
        封装items
        themeId自增
        score默认5分值
         */
        Integer themeId = 1;
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

            items = new Items(paperId, themeId++, theme, op1, op2, op3, op4, correct, score, note);
            System.out.println("这张试卷解析完毕，开始解析下一张试卷");
        }
        return items;

    }

}
