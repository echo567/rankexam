package cn.junhui.springboot.controller;

import cn.junhui.springboot.bean.Language;
import cn.junhui.springboot.service.LanguageService;
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
 * 2018-11-16 15:19
 */
@Controller
@RequestMapping("/language/")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    /*
    跳转到语言等级列表
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                             Model model) {
        PageHelper.startPage(pn, 10);
        List<Language> list = languageService.getall();
        PageInfo<Language> pageInfo = new PageInfo<>(list, 5);
        String link = "/language/list";

        model.addAttribute("link", link);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("language", list);
        return new ModelAndView("language/languageList");

    }

    /*
    跳转到语言增加界面
     */
    @GetMapping("/addpage")
    public ModelAndView addpage() {
        return new ModelAndView("language/addLanguage");
    }

    /*
    增加语言
     */
    @PostMapping("/add")
    public String add(Language language) {
        languageService.addLanguage(language);
        return "redirect:/language/list";
    }

    /*
    跳转到修改语言界面
     */
    @GetMapping("/editpage/{lanCode}")
    public ModelAndView editPage(@PathVariable("lanCode") String lanCode, Model model) {
        Language language = languageService.getLanguage(lanCode);
        System.out.println(language);
        model.addAttribute("lan", language);
        return new ModelAndView("/language/addLanguage");
    }


    /*
    根据languageCode修改语言信息
     */
    @PutMapping("/add")
    public String update(Language language) {
        language.setUpdateTime(new Date());
        languageService.updateLanguage(language);
        return "redirect:/language/list";
    }

    /*
    根据languageCode删除单个语言
     */
    @DeleteMapping("/delete/{lanCode}")
    public String delete(@PathVariable("lanCode") String lanCode) {
        languageService.deleteLanguage(lanCode);
        return "redirect:/language/list";
    }
}
