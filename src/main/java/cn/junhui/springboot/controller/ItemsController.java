package cn.junhui.springboot.controller;

import cn.junhui.springboot.bean.Items;
import cn.junhui.springboot.bean.Paper;
import cn.junhui.springboot.service.ItemsService;
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
 * 2018-10-29 20:51
 */
@Controller
@RequestMapping("/items/")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private PaperService paperService;

    /*
    来到试题列表页
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(required = false, value = "pn", defaultValue = "1") Integer pn,
                             Model model) {
        PageHelper.startPage(pn, 10);
        List<Items> list = itemsService.getAllItems();
        PageInfo<Items> pageInfo = new PageInfo<>(list, 5);
        String link = "/items/list";
        model.addAttribute("items", list);
        model.addAttribute("pageInfo", pageInfo);
        /*
        公共分页代码，把链接抽出来
         */
        model.addAttribute("link", link);
        return new ModelAndView("items/itemsList");
    }

    /*
    跳转到 手动 增加试题页面
     */
    @GetMapping("/add/{paperId}")
    public ModelAndView addPage(@PathVariable(required = false, value = "paperId") Integer paperId, Model model) {
       // System.out.println("手动向试卷id：" + paperId + "增加试题");
        Paper paper = paperService.selectPaper(paperId);
        return new ModelAndView("items/addItem", "paper", paper);

    }

    /*
    跳转到 批量导入 增加试题页面
    */
    @GetMapping("/add_batch")
    public ModelAndView addPageBatch(@RequestParam(value = "paperId") Integer paperId, Model model) {
        System.out.println("批量向试卷id：" + paperId + "增加试题");
        Paper paper = paperService.selectPaper(paperId);
        return new ModelAndView("exam/uploadPage", "paper", paper);
    }


    /*
    增加试题  id自增

     */
/*    @PostMapping("/add")
    public String addItem(Items items) {
        *//* items.setPaperId(1);*//*
        itemsService.addItem(items);
        System.out.println("增加的试题信息" + items);
        return "redirect:/items/list";
        //return null;
    }*/

    /*
增加试题  id非自增

 */
    @PostMapping("/add")
    public String addItem_plus(Items items) {
        /* items.setPaperId(1);*/
        System.out.println("增加的试题信息" + items);
        itemsService.addItem_plus(items);
        return "redirect:/items/list";
        //return null;
    }

    /*
    跳转到编辑页面
     */
    @GetMapping("/edit")
    public ModelAndView editPage(@RequestParam(value = "paperId") Integer paperId,
                                 @RequestParam(value = "themeId") Integer themeId,
                                 Model model) {
      //  System.out.println("试卷id" + paperId + "题目id" + themeId);
        Items it = itemsService.selPap(paperId, themeId);
       // System.out.println(it);
        model.addAttribute("items", it);
        return new ModelAndView("items/addItem");
    }

    /*
    修改试题信息

     */
    @PutMapping("/add")
    public String updateItem(Items items) {
        items.setUpdateTime(new Date());
       // System.out.println("修改试题的信息:" + items);
        itemsService.dynamicItemUdp(items);
        return "redirect:/items/list";
    }

    /*
    删除试题
     */
    @DeleteMapping("/delete")
    public String deleteItems(@RequestParam(value = "paperId") Integer paperId,
                              @RequestParam(value = "themeId") Integer themeId) {
        Items it = itemsService.selPap(paperId, themeId);
        itemsService.deleteItem(it);
        return "redirect:/items/list";

    }

}
