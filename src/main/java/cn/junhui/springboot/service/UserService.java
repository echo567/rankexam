package cn.junhui.springboot.service;

import cn.junhui.springboot.bean.Exam;
import cn.junhui.springboot.bean.Items;
import cn.junhui.springboot.bean.User;
import cn.junhui.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 军辉
 * 2018-10-15 21:32
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PaperService paperService;

    @Autowired
    private ItemsService itemsService;

    /*
    增加学生详细信息
     */
    public boolean addStuPlus(User user) {
        //System.out.println();
        userMapper.dynamicStuAdd(user);
        return true;
    }

    public boolean deleteUser(Integer id) {
        System.out.println("将被删除的id是：" + id);

        if (id > 0) {
            userMapper.deleteUserbyId(id);
            return true;
        } else {
            return false;
        }
    }

    public User findUserbyId(Integer id) {
        return userMapper.findUserbyId(id);

    }


    public List<User> findUserList() {
        //PageHelper.startPage(1,2);
        return userMapper.findUserList();
    }

    /*
    注册单个学生
     */
    public boolean toRegister(User user) {

        String current;
        System.out.println("注册时的个人信息：" + user);
        userMapper.dynamicStuAdd(user);
        return true;
        // System.out.println("插入时返回的数值:"+userMapper.toRegister(user.getPhone(), user.getName(), user.getPassword()));
     /*   if (1 == userMapper.toRegister(user.getPhone(), user.getName(), user.getPassword())) {
            return true;
        } else {
            return false;
        }*/
    }

    /*
    登录验证
     */
    public User selectByPhone(String phone) {
        User u = userMapper.selectByPhone(phone);
        if (u == null) {
            return null;
        } else {
            return u;
        }
    }

    /*
    批量增加学生
     */
    public void batStuAdd(List<User> list) {

        userMapper.batStuAdd(list);

    }

    /*
    批量删除学生
     */
    public void batStuDel(List<User> list) {

        userMapper.batStuDel(list);

    }

    /*
    动态增加学生
     */
    public void dynamicStuAdd(User user) {
        userMapper.dynamicStuAdd(user);
    }

    /*
    动态编辑学生
     */
    public void dynamicStyUdp(User user) {
        //  System.out.println("当前用户密码：" + user.getPassword());

        userMapper.dynamicStuUpd(user);
    }

    /*
    动态查询单个学生信息
     */
    public void dynamicStuSel(User user) {
        userMapper.dynamicStuSel(user);
    }

    /*
    自定义查询
     */
    public User customSel(String custom, String data) {
        return userMapper.customSel(custom, data);
    }

    /*
    接收学生提交的答案
     */
    public void toAnswer(Exam exam) {
        userMapper.addAnswer(exam);
    }

    /*
    查询考生成绩
     */
    public int tograde(int userId, int paperId) {
        int grades = 0;
        Exam exam = userMapper.tograde(userId, paperId);
        System.out.println("核对考生答案：算分数--exam:" + exam);
        //Map<Integer, Integer> gradeMap = new HashMap<>();
        List<String> answerList = new ArrayList<>();
        answerList.add(exam.getOp1());
        answerList.add(exam.getOp2());
        answerList.add(exam.getOp3());
        answerList.add(exam.getOp4());
        answerList.add(exam.getOp5());
        answerList.add(exam.getOp6());
        answerList.add(exam.getOp7());
        answerList.add(exam.getOp8());
        answerList.add(exam.getOp9());
        answerList.add(exam.getOp10());
        answerList.add(exam.getOp11());
        answerList.add(exam.getOp12());
        answerList.add(exam.getOp13());
        answerList.add(exam.getOp14());
        answerList.add(exam.getOp15());
        answerList.add(exam.getOp16());
        answerList.add(exam.getOp17());
        answerList.add(exam.getOp18());
        answerList.add(exam.getOp19());
        answerList.add(exam.getOp20());


        List<Items> items = itemsService.getallByPaperId(paperId);
        System.out.println("正确选项：" + items);
        for (int i = 0; i < items.size(); i++) {
            System.out.println("answerList:" + answerList.get(i) + " items:" + items.get(i).getCorrect());
            if ((items.get(i).getCorrect().equals(answerList.get(i)))) {
                grades += items.get(i).getScore();
                System.out.println(i);
            }

        }
        System.out.println("------------------->" + grades);
        return grades;
    }
}
