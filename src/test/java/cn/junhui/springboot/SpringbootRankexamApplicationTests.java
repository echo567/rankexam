package cn.junhui.springboot;

import cn.junhui.springboot.bean.Exam;
import cn.junhui.springboot.bean.User;
import cn.junhui.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRankexamApplicationTests {


    @Autowired
    private DataSource dataSource;

    //@Autowired
    private User user;

    @Autowired
    private UserService userService;


    @Test
    public void contextLoads() throws SQLException {
        System.out.println("你好" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

/*
    @Test
    public void addUser() {
        User u = new User("123", "456546546", "120");
        userService.addUser(u);
    }
*/

    @Test
    public void findUserbyid() {
        System.out.println(userService.findUserbyId(3));

    }

    @Test
    public void updateUserbyId() {
        User u = new User();
        u.setName("1111");
        u.setRegisterId(5);
        u.setPassword("11111");
        userService.dynamicStyUdp(u);
    }

    @Test
    public void deleteUserbyId() {

        userService.deleteUser(602);
    }

    /*
    查询所有学生
     */
    @Test
    public void findall() {

        System.out.println(userService.findUserList());
    }

    @Test
    public void testRegister() {
        User u = new User();
        u.setPhone("11122");
        u.setName("111");
        u.setPassword("111");
        userService.toRegister(u);
    }


    @Test
    public void testLogin() {
        System.out.println("查询到的用户信息：" + userService.selectByPhone("15500000000"));
    }

    /*
    批量增加学生测试
     */
    @Test
    public void testBatAdd() {
        List<User> students = new ArrayList<>();
        String phone, name, password, uuid;
        for (int i = 0; i < 100; i++) {
            uuid = UUID.randomUUID().toString().substring(0, 5);
            students.add(new User(i + "phone", i + "name", i + "password"));
        }
        userService.batStuAdd(students);
    }

    /*
    批量删除学生
     */
    @Test
    public void testBatDel() {
        List<User> list = new ArrayList();
        User user;
        for (int i = 737; i < 1142; i++) {
            user = userService.findUserbyId(i);
            list.add(user);
        }
        userService.batStuDel(list);
    }


    /*
    动态增加
     */
    @Test
    public void dynamicStuAdd() {
        User u = new User("phone", "name", "password");
        u.setAddress("add");
        u.setBirth(new Date());
        userService.dynamicStuAdd(u);
    }

    /*
    动态修改
     */
    @Test
    public void dynamicStuUdp() {
        User u = userService.findUserbyId(629);
        u.setSex("女");
        u.setPassword("123456789");
        userService.dynamicStyUdp(u);
    }

    /*
    自定义查询
     */
    @Test
    public void customSel() {
        String custom = "phone";
        String data = "123456";
        User u = userService.customSel(custom, data);
        System.out.println(u);

    }

    /*
    测试接收学生答案
     */
    @Test
    public void testanswer() {
        Exam exam = new Exam(1, 1, "A", "A", "A", "A",
                "A", "A", "A", "A", "A", "A", "A",
                "A", "A", "A", "A", "A", "A", "A", "A", "A");

        userService.toAnswer(exam);
    }

    /*
    测试学生成绩
     */
    @Test
    public void testgrade() {
        userService.tograde(640, 879);
    }

    /*
    查询账号是否已存在
     */
    @Test
    public void seelctbuphone(){
        User u = userService.selectByPhone("1213");
        if (u == null) {
            System.out.println("账号不重复，可注册");

        } else{
            System.out.println("已存在此账号，不可注册");
        }
    }

}
