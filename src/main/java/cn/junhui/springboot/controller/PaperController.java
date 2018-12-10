package cn.junhui.springboot.controller;

import cn.junhui.springboot.bean.Items;
import cn.junhui.springboot.bean.Language;
import cn.junhui.springboot.bean.Paper;
import cn.junhui.springboot.service.ItemsService;
import cn.junhui.springboot.service.LanguageService;
import cn.junhui.springboot.service.PaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 军辉
 * 2018-11-13 21:19
 */
@Controller
@RequestMapping("/paper/")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private ItemsService itemsService;

    /*
    来到试卷列表页面
     */
    @GetMapping("/list")
    public ModelAndView paperList(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                  Model model) {
        PageHelper.startPage(pn, 10);
        List<Paper> list = paperService.getall();
        PageInfo<Paper> pageInfo = new PageInfo<>(list, 5);
        String link = "/paper/list";
        model.addAttribute("papers", list);
        model.addAttribute("link", link);
        model.addAttribute("pageInfo", pageInfo);
        return new ModelAndView("paper/paperList");
    }


    /*
    查看本张试卷的所有试题
    调用 ItemsService的方法，
     */
    @GetMapping("/total")
    public ModelAndView total(@RequestParam("paperId") Integer paperId,
                              @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                              Model model) {
        //每页显示5道题
        PageHelper.startPage(pn, 5);
        List<Items> items = itemsService.getallByPaperId(paperId);
        PageInfo<Items> pageInfo = new PageInfo<>(items, 5);
        String link = "/paper/total";
        model.addAttribute("link", link);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("items", items);
        model.addAttribute("paperId", paperId);

        return new ModelAndView("paper/onepaper");
    }

    /*
    来到试卷增加页面
    <input type="text" class="form-control" placeholder="试卷代码：从语言表中选取的" name="paperCode" required
                               th:value="${paper != null} ? ${paper.paperCode}">
     */
    @GetMapping("/addPage")
    public ModelAndView addPaper() {
        List<Language> list = languageService.getall();
        return new ModelAndView("paper/addPaper", "language", list);
    }

    /*
    增加试卷
     */
    @PostMapping("/add")
    public String addPaper(Paper paper) {
        System.out.println("增加试卷的信息：" + paper);
        paperService.addPaper(paper);
        return "redirect:/paper/list";
    }

    /*
    来到编辑页面
     */
    @GetMapping("/editpage/{paperId}")
    public ModelAndView editPage(@PathVariable("paperId") Integer paperId) {
        System.out.println("PathVariable:" + paperId);
        Paper paper = paperService.selectPaper(paperId);
        System.out.println(paper);
        return new ModelAndView("paper/addPaper", "paper", paper);
    }
/*
    @GetMapping("/editpage")
    public ModelAndView editPagetest(@RequestParam("paperId") Integer paperId) {
        System.out.println("RequestParam:" + paperId);
        Paper paper = paperService.selectPaper(paperId);
        System.out.println(paper);
        return new ModelAndView("paper/addPaper", "paper", paper);
    }*/

    /*
    修改试卷
     */
    @PutMapping("/add")
    public String edit(Paper paper) {
        paper.setUpdateTime(new Date());
        paperService.updatePaper(paper);
        return "redirect:/paper/list";
    }

    /*
    删除试卷
     */
    @DeleteMapping("/delete/{paperId}")
    public String delete(@PathVariable("paperId") int paperId) {
        paperService.deletePaper(paperId);
        return "redirect:/paper/list";
    }


}
