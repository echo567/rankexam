package cn.junhui.springboot.controller;

import cn.junhui.springboot.bean.User;
import cn.junhui.springboot.service.UserService;
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
 * 2018-10-22 16:38
 */
@Controller
public class AdminController {

    @Autowired
    UserService userService;

    private int pages = 0;

    @GetMapping("/admin")
    public ModelAndView adminIndex() {
        return new ModelAndView("admin/root");
    }

    /*
    考生列表页
     */
    @GetMapping("/stuList")
    public ModelAndView page(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn,
                             Model model) {
        //pageSize:当前页面显示数据条数
        PageHelper.startPage(pn, 10);
        List<User> users = userService.findUserList();
        model.addAttribute("students", users);

        //navigatePages:导航栏页码数
        PageInfo<User> pageInfo = new PageInfo(users, 5);
        /*
        公共分页代码，把链接抽出来
         */
        String link = "/stuList";
        model.addAttribute("link", link);

        return new ModelAndView("admin/studentList", "pageInfo", pageInfo);
    }

    /*
    来  到考生增加页面
     */
    @GetMapping("/toAddStu")
    public ModelAndView toAddStu() {
        return new ModelAndView("admin/addStudent");
    }

    /*
    增加考生详细信息
     */
    @PostMapping("/add_upd_stu_plus")
    public String addStu(User user) {
        userService.dynamicStuAdd(user);
        //重定向到考生列表
        return "redirect:/stuList";
    }

    /*
    修改考生详细信息
     */
    @PutMapping("/add_upd_stu_plus")
    public String updateStu(User user) {
        user.setUpdateTime(new Date());
        userService.dynamicStyUdp(user);
        return "redirect:/stuList";
    }

    /*
    刪除考生
     */
    @DeleteMapping("/delStu/{id}")
    public String delStu(@PathVariable("id") Integer id) {
        System.out.println("delete");
        System.out.println(id);
        userService.deleteUser(id);
        return "redirect:/stuList";
    }


    /*
    跳转到修改考生信息页面
     */
    @GetMapping("/updStu/{id}")
    public String updStu(@PathVariable("id") int id, Model model) {
        System.out.println("编辑的id：" + id);
        User user = userService.findUserbyId(id);
        model.addAttribute("stu", user);
        model.addAttribute("birth", user.getBirth());
        return "admin/addStudent";
    }

    /*
    跳转到查询考生页面
     */
    @GetMapping("/selstu")
    public ModelAndView toSelStu() {

        return new ModelAndView("admin/selectStudent");
    }


    @GetMapping("/selstu/detail")
    public ModelAndView selStu(@RequestParam(value = "param") String param,
                               @RequestParam(value = "data") String data,
                               Model model) {
        User stu = userService.customSel(param, data);
        model.addAttribute("data", data);
        model.addAttribute("stu", stu);
        return new ModelAndView("admin/selectStudent");
    }


}
