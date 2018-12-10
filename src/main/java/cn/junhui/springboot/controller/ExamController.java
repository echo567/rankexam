package cn.junhui.springboot.controller;


import cn.junhui.springboot.bean.Items;
import cn.junhui.springboot.bean.Language;
import cn.junhui.springboot.bean.Paper;
import cn.junhui.springboot.service.ItemsService;
import cn.junhui.springboot.service.LanguageService;
import cn.junhui.springboot.service.PaperService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.io.File;

import java.io.IOException;

import java.util.List;

/**
 * 军辉
 * 2018-11-19 17:23
 */
@Controller
@RequestMapping("/exam/")
public class ExamController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private ItemsService itemsService;

    /*
    跳转查询页面
     */
    @GetMapping("/selectpage")
    public ModelAndView toSelect() {
        List<Language> list = languageService.getall();
        return new ModelAndView("exam/select", "language", list);
    }

    /*
    查询paperCode的试卷
    表单：
    http://localhost/exam/select?paperCode=101
     */
    @GetMapping("/select")
    public ModelAndView select(@RequestParam(value = "paperCode") String paperCode, Model model) {
        System.out.println("查询paperCode试卷:" + paperCode);
        List<Paper> list = paperService.getallByPaperCode(paperCode);
        List<Language> lan = languageService.getall();
        model.addAttribute("language", lan);
        model.addAttribute("code", paperCode);

        return new ModelAndView("exam/select", "paper", list);
    }

    /*
    遍历paperId的试题
    http://localhost/exam/itemspage?1
     */
    @GetMapping("/itemspage/{paperId}")
    public ModelAndView itemspage(@PathVariable(value = "paperId") Integer paperId) {
        System.out.println("paperId:" + paperId);
        List<Items> list = itemsService.getallByPaperId(paperId);
        return new ModelAndView("exam/examlist", "items", list);
    }

    /*
    删除paperId的试题
     */

    @GetMapping("/delete/{paperId}")
    public ModelAndView deleteItems(@PathVariable(value = "paperId") Integer paperId, Model model) {
        List<Items> items = itemsService.getallByPaperId(paperId);
        for (int i = 0; i < items.size(); i++) {
            //System.out.println(list.get(i).getThemeId() + " " + list.get(i).getTheme());
            itemsService.deleteItem(items.get(i));
        }
        List<Language> lan = languageService.getall();
        model.addAttribute("msg", "试卷清空完成，请转至试卷管理处查看");
        return new ModelAndView("exam/select", "language", lan);
    }

    /*
    跳转到上传excel页面
    */
    @GetMapping("/uploadpage")
    public ModelAndView excelUploadPage() {
        return new ModelAndView("exam/uploadPage");
    }


    /*
   解析excel文件，并向数据库增加数据
    */
    @PostMapping("/upload")
    public ModelAndView testRead(@RequestParam("file") MultipartFile file,
                                 @RequestParam("paperId") Integer paperId) {
        System.out.println("批量试卷id" + paperId);
        //Excel文件--得到Excel工作簿对象
        XSSFWorkbook wb = null;
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

        String[] themearr = null;

        for (int i = 1; i <= rowNum - 6; i += 7) {

            row = sheet.getRow(i + 0);//得到Excel工作表的行
            theme = row.getCell(0).toString();//得到Excel工作表指定行的单元格 坐标：[i,0]第i行第0列
            // System.out.println("theme1:" + theme);

            themearr = theme.split("）");
            //themeId = themearr[themearr.length - 2].charAt(1) - 48;
            theme = themearr[themearr.length - 1];
            System.out.println("theme2:" + theme);

            row = sheet.getRow(i + 1);
            op1 = row.getCell(0).toString();
            op1 = op1.substring(2, op1.length());
            System.out.println("op1:" + op1);

            row = sheet.getRow(i + 2);
            op2 = row.getCell(0).toString();
            op2 = op2.substring(2, op2.length());
            System.out.println("op2:" + op2);

            row = sheet.getRow(i + 3);
            op3 = row.getCell(0).toString();
            op3 = op3.substring(2, op3.length());
            System.out.println("op3:" + op3);

            row = sheet.getRow(i + 4);
            op4 = row.getCell(0).toString();
            op4 = op4.substring(2, op4.length());
            System.out.println("op4:" + op4);

            row = sheet.getRow(i + 5);
            correct = row.getCell(0).toString();
            correct = correct.substring(3, correct.length());
            System.out.println("correct" + correct);

            row = sheet.getRow(i + 6);
            if (row != null) {
                note = row.getCell(0).toString();
                note = note.substring(3, note.length());
                System.out.println("note:" + note);

            } else {
                note = "无 ";
                System.out.println("note为空");
            }

            Items items = new Items(paperId, themeId++, theme, op1, op2, op3, op4, correct, score, note);
            itemsService.addItem_plus(items);

            System.out.println("题号" + (themeId - 1) + "解析完毕");
        }

        return new ModelAndView("exam/uploadPage", "msg", "解析成功");
    }


    /*
    上传文件到临时目录（重启服务器之后消失）
     */
    /* @PostMapping("/upload")*/
    public String upload(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request) {

        String uploadDir = request.getSession().getServletContext().getRealPath("/") + "excel/";
        //String uploadDir = request.getSession().getServletContext().getRealPath("/");
        System.out.println(uploadDir);
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //
        String fileName = file.getOriginalFilename();
        //
        File serverFile = new File(uploadDir + fileName);
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }
        System.out.println("上传成功");
        return "exam/uploadPage";
    }

    @PostMapping("/testupload")
    public String testexcel(@RequestParam("file") MultipartFile file) {
        System.out.println("testupload");
        //Excel文件--得到Excel工作簿对象
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Excel工作表--得到Excel工作表对象
          excel文件左下方的标识
         */
        XSSFSheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();//总行数
        System.out.println("总行数(因为是从0开始的，所以输出会比实际上少1):" + rowNum);

        //获得某一行的对象
        XSSFRow row = null;
        for (int i = 0; i <= rowNum; i++) {
            row = sheet.getRow(i);//获取第i行
            String st1 = row.getCell(0).toString();//获取第i行的第0列
            String st2 = row.getCell(1).toString();//获取第i行的第1列
            System.out.println(st1 + " " + st2);
        }
        System.out.println("解析成功");
        return "exam/uploadPage";
    }

}
