package cn.junhui.springboot.bean;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
/*
考生信息
*/


public class User {
    private Integer registerId;

    private String admissionId;

    @Length(min = 2, max = 10, message = "用户名长度在2到10个字符之间")
    private String name;

    @Length(min = 6, max = 16, message = "密码长度在6到16个字符之间")
    private String password;

    private String sex;

    private Date birth;

    @Length(min = 11, max = 11, message = "手机号填写错误")
    private String phone;

    private String national;

    @Length(min = 18, max = 18, message = "身份证号必须是18位")
    private String idCard;

    private String address;

    private String examCode;

    private String photo;

    private String note;

    private Date createTime;

    private Date updateTime;

    private Integer grade;

    public User() {
    }

    /*
    初始信息
     */
    public User(String phone, String name, String password) {
        this.phone = phone;
        this.name = name;
        this.password = password;
    }
    /*
    详细信息
     */

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "User{" +
                "registerId=" + registerId +
                ", admissionId='" + admissionId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                ", national='" + national + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", examCode='" + examCode + '\'' +
                ", photo='" + photo + '\'' +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", grade=" + grade +
                '}';
    }
}