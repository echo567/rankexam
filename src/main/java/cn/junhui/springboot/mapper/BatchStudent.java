package cn.junhui.springboot.mapper;

import cn.junhui.springboot.bean.User;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 军辉
 * 2018-10-23 9:57
 */
public class BatchStudent {
    /*
    批量增加学生基本信息
     */
    public String batStuAdd(Map map) {
        List<User> students = (List<User>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into tb_user(phone,name,password) values");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].phone},#'{'list[{0}].name},#'{'list[{0}].password})"
        );
        for (int i = 0; i < students.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < students.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /*
    批量删除
     */
    public String batStuDel(Map map) {
        List<User> students = (List<User>) map.get("list");
        StringBuffer sb = new StringBuffer();
        sb.append("delete from tb_user where registerId in (");
        for (int i = 0; i < students.size(); i++) {
            sb.append("'").append(students.get(i).getRegisterId()).append("'");
            if (i < students.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /*
    动态修改学生全部信息
     */
    public String dynamicStuUpd(User user) {
        return new SQL() {{
            UPDATE("tb_user");
            if (user.getName() != null) {
                SET("name = #{name}");
            }
            if (user.getPassword() != null) {
                SET("password = #{password}");
            }
            if (user.getSex() != null) {
                SET("sex=#{sex}");
            }
            if (user.getBirth() != null) {
                SET("birth=#{birth}");
            }
            if (user.getPhone() != null) {
                SET("phone=#{phone}");
            }
            if (user.getNational() != null) {
                SET("national = #{national}");
            }
            if (user.getIdCard() != null) {
                SET("idCard = #{idCard}");
            }
            if (user.getAddress() != null) {
                SET("address = #{address}");
            }
            if (user.getPhoto() != null) {
                SET("photo = #{photo}");
            }
            if (user.getExamCode() != null) {
                SET("examCode = #{examCode}");
            }
            if (user.getNote() != null) {
                SET("note = #{note}");
            }
            if (user.getPassword() != null) {
                SET("password = #{password}");
            }
            if (user.getUpdateTime() != null) {
                SET("updateTime = #{updateTime}");
            }
            WHERE("registerId =#{registerId}");
        }}.toString();

    }

    /*
    动态增加
     */
    public String dynamicStuAdd(User user) {
        return new SQL() {{
            INSERT_INTO("tb_user");
            if (user.getName() != null) {
                VALUES("name", "#{name}");
            }
            if (user.getPassword() != null) {
                VALUES("password", "#{password}");
            }
            if (user.getSex() != null) {
                VALUES("sex", "#{sex}");
            }
            if (user.getBirth() != null) {
                VALUES("birth", "#{birth}");
            }
            if (user.getPhone() != null) {
                VALUES("phone", "#{phone}");
            }
            if (user.getNational() != null) {
                VALUES("national", "#{national}");
            }
            if (user.getIdCard() != null) {
                VALUES("idCard", "#{idCard}");
            }
            if (user.getAddress() != null) {
                VALUES("address", "#{address}");
            }
            if (user.getPhoto() != null) {
                VALUES("photo", "#{photo}");
            }
            if (user.getNote() != null) {
                VALUES("note", "#{note}");
            }
        }}.toString();
    }

    /*
    动态查询
     */
    public String dynamicStuSel(User user) {
        return new SQL() {{
            SELECT("*");
            FROM("tb_user");
            if (user.getRegisterId() != null) {
                WHERE("registerId = #{registerId}");
            }
            if (user.getAdmissionId() != null) {
                WHERE("admissionId = #{admissionId}");
            }
            if (user.getPhone() != null) {
                WHERE("phone = #{phone}");
            }
            if (user.getIdCard() != null) {
                WHERE("idCard=#{idCard}");
            }

        }}.toString();
    }


    /* 自定义属性查询*/

    public String customSel(String custom, String data) {
/*        return new SQL() {{
            SELECT("*");
            FROM("tb_user");
            WHERE("'#{custom}' = '#{data}'"
            );
        }}.toString();*/
        StringBuffer sql = new StringBuffer();
        sql.append("select * from tb_user where ")
                .append(custom)
                .append("= '")
                .append(data)
                .append("'");
        return sql.toString();
    }

    /**
     * 在某个用户下按id查
     *
     * @param attr     查询的字段
     * @param content  查询内容
     * @param username 用户名
     * @return Stirng sql
     */
    public String selectByAttr(String attr, String content, String username) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from blog where ")
                .append("blog_user_id = (select blog_id from user where blog_username = '")
                .append(username)
                .append("' ) and ")
                .append(attr)
                .append(" = '")
                .append(content)
                .append("'");
        return sql.toString();
    }

}
