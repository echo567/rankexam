package cn.junhui.springboot.mapper;

import cn.junhui.springboot.bean.Exam;
import cn.junhui.springboot.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 军辉
 * 2018-10-15 20:45
 */
@Mapper
public interface UserMapper {

    /*
    通过注册id删除考生
     */
    @Delete("delete from tb_user where registerId = #{registerId}")
    public boolean deleteUserbyId(Integer id);

    /*
    通过注册id查询考生所有个人信息
     */
    @Select("select * from tb_user where registerId =#{registerId}")
    public User findUserbyId(Integer id);

    /*
    注册id 逆序累出所有考生
     */
    @Select("select * from tb_user order by registerId desc")
    public List<User> findUserList();

    /*
    注册：输入 手机号 姓名 密码
    注册成功时,返回1
     */
    @Insert("insert into tb_user(phone,name,password) values(#{phone},#{name},#{password})")
    public Integer toRegister(@Param("phone") String phone, @Param("name") String name,
                              @Param("password") String password);

    /*
    登录：通过 phone 查询 用户是否存在
     */
    @Select("select * from tb_user where phone = #{phone}")
    public User selectByPhone(String phone);

    /*
    批量增加学生
     */
    @InsertProvider(type = BatchStudent.class, method = "batStuAdd")
    Integer batStuAdd(List<User> students);

    /*
    批量删除学生
     */
    @DeleteProvider(type = BatchStudent.class, method = "batStuDel")
    Integer batStuDel(List<User> students);

    /*
    动态修改
     */
    @UpdateProvider(type = BatchStudent.class, method = "dynamicStuUpd")
    Integer dynamicStuUpd(User user);

    /*
    动态增加
     */
    @InsertProvider(type = BatchStudent.class, method = "dynamicStuAdd")
    Integer dynamicStuAdd(User user);

    /*
    动态查询
     */
    @SelectProvider(type = BatchStudent.class, method = "dynamicStuSel")
    Integer dynamicStuSel(User user);

    /*
    自定义查询
     */
    @SelectProvider(type = BatchStudent.class, method = "customSel")
    User customSel(String custom, String data);
   /* @Select("select * from tb_user where #{custom} = #{data}")
    User customSel(@Param("custom") String custom,
                   @Param("data") String data);*/

    /*
    接收学生提交的答案
     */
    @Insert("insert into tb_exam (userId,paperId,op1,op2,op3,op4,op5,op6,op7,op8,op9,op10," +
            "op11,op12,op13,op14,op15,op16,op17,op18,op19,op20) " +
            "values(#{exam.userId},#{exam.paperId}," +
            "#{exam.op1},#{exam.op2},#{exam.op3},#{exam.op4},#{exam.op5}," +
            "#{exam.op6},#{exam.op7},#{exam.op8},#{exam.op9},#{exam.op10}," +
            "#{exam.op11},#{exam.op12},#{exam.op13},#{exam.op14},#{exam.op15}," +
            "#{exam.op16},#{exam.op17},#{exam.op18},#{exam.op19},#{exam.op20})")
    public int addAnswer(@Param("exam") Exam exam);

    /*
    查询考生所提交的答案
     */
    @Select("select * from tb_exam where userId = #{userId} and paperId = #{paperId} ")
    public Exam tograde(@Param("userId") int userId, @Param("paperId") int paperId);
}
