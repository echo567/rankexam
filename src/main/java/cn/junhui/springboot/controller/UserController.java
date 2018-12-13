package cn.junhui.springboot.controller;

import cn.junhui.springboot.bean.*;
import cn.junhui.springboot.service.ItemsService;
import cn.junhui.springboot.service.LanguageService;
import cn.junhui.springboot.service.PaperService;
import cn.junhui.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 军辉
 * 2018-10-15 22:19
 */
@Controller("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private ItemsService itemsService;

    private User currentUser = null;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @GetMapping("/toRegister")
    public ModelAndView toRegisterPage() {
        return new ModelAndView("register");
    }

    @GetMapping("/toLogin")
    public ModelAndView toLoginPage() {
        System.out.println("start    --------------");
        return new ModelAndView("login");
    }

    /*
    注册
     */
    @PostMapping(value = "/register")
    public ModelAndView register(Model model, User user) {
        System.out.println("手机号长度：" + user.getPhone().length());
        System.out.println("姓名长度：" + user.getName().length());
        System.out.println("密码长度：" + user.getPassword().length());
        if ((user.getName().length() < 2 || user.getName().length() > 10) ||
                (user.getPhone().length() != 11) ||
                (user.getPassword().length() < 6 || user.getPassword().length() > 16)
                ) {
            System.out.println("有错");
            if ((user.getName().length() < 2 || user.getName().length() > 10)) {
                model.addAttribute("nameError", "用户名长度在2到10个字符之间");
            }
            if (user.getPhone().length() != 11) {
                model.addAttribute("phoneError", "请输入11位手机号");
            }
            if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
                model.addAttribute("passwordError", "密码长度必须是6-16位");
            }
            return new ModelAndView("register");
        } else {
            System.out.println("无错");

            System.out.println("需要注册的信息：" + user);
            String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(md5Password);
            User u = userService.selectByPhone(user.getPhone());
            if (u == null) {
                System.out.println("账号不重复，可注册");
                userService.toRegister(user);
                return new ModelAndView("login");
            } else {
                System.out.println("已存在此账号，不可注册");
                return new ModelAndView("register", "msg", "手机号已存在，请更换手机号，或者联系管理员");

            }


        }


    }

    /*
    登录
     */
    @PostMapping(value = "/login")
    public ModelAndView login(Map<String, Object> map,
                              HttpSession session,
                              Model model,
                              User user) {
        /*
        管理员特定账号
         */
        if (user.getPhone().equals("000") && user.getPassword().equals("root")) {
            session.setAttribute("loginUser", user);
            return new ModelAndView("redirect:/admin");
        }

        User userdatabase = userService.selectByPhone(user.getPhone());


        if (null == userdatabase) {
            return new ModelAndView("login", "msg", "账号或密码错误");

        } else if (userdatabase.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))) {
            currentUser = userdatabase;
            session.setAttribute("loginUser", userdatabase);
            List<Language> languages = languageService.getall();
            if (currentUser.getExamCode() != null) {
                //展示考生所报名考试的信息
                Language language = languageService.getLanguage(currentUser.getExamCode());
                model.addAttribute("language", language);
            }
            model.addAttribute("languages", languages);
            model.addAttribute("user", userdatabase);
            return new ModelAndView("student/student");

        } else {
            return new ModelAndView("login", "msg", "账号或密码错误");
        }

    }

    @PostMapping("/student")
    public ModelAndView User_page() {
        return new ModelAndView("student/student");
    }

    /*
    获取所有用户信息
    http://localhost:8080/getall
     */
    @GetMapping("/getall")
    public String getall() {
        return userService.findUserList().toString();
    }

    /*
    根据registerid查找用户
    http://localhost:8080/user/3
     */
    @GetMapping("user/{registerid}")
    public User findbyid(@PathVariable("registerid") Integer registerid) {
        User u = userService.findUserbyId(registerid);
        return u;
    }


    /*
    修改员工普通信息 PUT(底层方法就存在问题)
     */
    @PutMapping("/user")
    public ModelAndView updateUser(User user, Model model) {
        user.setUpdateTime(new Date());
        userService.dynamicStyUdp(user);

        /*
        学生主页需要用的数据
         */
        currentUser = user;
        List<Language> languages = languageService.getall();
        model.addAttribute("languages", languages);
        //重定向
        //return new ModelAndView("redirect:/login#personal");
        return new ModelAndView("student/student");
    }


    /*
    删除员工
    http://localhost:8080/user/9
     */
    @DeleteMapping("user/{registerid}")
    public boolean deleteUser(@PathVariable("registerid") Integer registerid) {
        boolean b = userService.deleteUser(registerid);
        return b;
    }


    /*
    报名考试
     */
    @GetMapping("/signup")
    public ModelAndView toSignUp(@RequestParam("lanCode") String lanCode, Model model) {
        System.out.println("考试报名处理方法：");
        System.out.println("lanColde:" + lanCode + "当前用户：" + currentUser.getName());
        currentUser.setExamCode(lanCode);
        userService.dynamicStyUdp(currentUser);


        Language language = languageService.getLanguage(lanCode);

        //展示考生所报名考试的信息
        model.addAttribute("language", language);
        model.addAttribute("user", currentUser);
        return new ModelAndView("student/student");
    }

    /*
    跳转到考试系统页面
     */
    @GetMapping("/exam")
    public ModelAndView toexam(Model model) {


        if (currentUser.getNote() == null) {
            //List<Paper> papers = paperService.getall();
            List<Paper> papers = new ArrayList<>();
            papers = paperService.getallByPaperCode(currentUser.getExamCode());
            if (papers.size() == 0) {
                //若所选语言类型的试卷张数为0，则从所有试卷中随便选一张
                papers = paperService.getall();
            }
            int num = (int) (Math.random() * papers.size());
            Paper paper = papers.get(num);
            List<Items> list = itemsService.getallByPaperId(paper.getPaperId());
            System.out.println("所选语言为编码：" + currentUser.getNote() + " 试卷张数:" + list.size());

            currentUser.setNote(paper.getPaperId() + "");//吧考生随机试卷id存入note字段

            userService.dynamicStyUdp(currentUser);

            System.out.println("随机试卷id:" + currentUser.getNote());
            //System.out.println("试卷总数：" + papers.size() + " 随机数:" + num);
            System.out.println(+num + " --> " + papers.get(num));
            model.addAttribute("userId", currentUser.getRegisterId());
            model.addAttribute("paper", paper);
            model.addAttribute("user", currentUser);
            model.addAttribute("items", list);
        } else {
            Paper paper = paperService.getPaperByPaperId(Integer.parseInt(currentUser.getNote()));
            List<Items> list = itemsService.getallByPaperId(Integer.parseInt(currentUser.getNote()));

            model.addAttribute("userId", currentUser.getRegisterId());
            model.addAttribute("paper", paper);
            model.addAttribute("user", currentUser);
            model.addAttribute("items", list);
        }

        // }
        ///itemspage/{paperId}


        return new ModelAndView("exam/examlist");
    }

    /*
    处理考生提交的答案（传递一个对象）
     */
    @PostMapping("/answer")
    public ModelAndView toAnswer(Exam exam) {
        System.out.println("考生所做的答案：" + exam);
        userService.toAnswer(exam);
        return new ModelAndView("redirect:/logout");
    }

    /*
    计算考生分数
     */
    @GetMapping("/grade")
    public ModelAndView tograde(Model model) {
        currentUser = currentUser;
        model.addAttribute("user", currentUser);
        int grade = userService.tograde(currentUser.getRegisterId(), Integer.parseInt(currentUser.getNote()));

        currentUser.setGrade(grade);
        userService.dynamicStyUdp(currentUser);
        return new ModelAndView("student/student");
    }

    /*
    修改账号资料
     */
    @PutMapping("/account")
    public ModelAndView updateAccount(User user) {
        User u = findbyid(user.getRegisterId());

        System.out.println("需要修改的：" + user + " 原本的：" + u);
        if (u != null) {
            user.setUpdateTime(new Date());
            userService.dynamicStyUdp(user);

        }

        return new ModelAndView("redirect:/");
    }

    /*
    退出登录
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            User user = (User) session.getAttribute("loginUser");//从当前session中获取用户信息

            session.invalidate();//关闭session
        }
        return new ModelAndView("login");
    }
}
