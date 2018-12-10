package cn.junhui.springboot.bean;

import java.util.Date;

/*
试题
 */
public class Items {
    private Integer paperId;

    private Integer themeId;

    private String theme;

    private String op1;

    private String op2;

    private String op3;

    private String op4;

    private String correct;

    private Integer score;

    private String note;

    private Date createTime;

    private Date updateTime;

    public Items() {
    }

    /*
    试卷id 和 试题id 都是自行设计的
     */
    public Items(Integer paperId, Integer themeId, String theme, String op1, String op2, String op3, String op4, String correct, Integer score, String note) {
        this.paperId = paperId;
        this.themeId = themeId;
        this.theme = theme;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correct = correct;
        this.score = score;
        this.note = note;
    }

    /*
        试题id自动递增
         */
    public Items(Integer paperId, String theme, String op1, String op2, String op3, String op4, String correct, Integer score, String note) {
        this.paperId = paperId;
        this.theme = theme;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correct = correct;
        this.score = score;
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

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Items{" +
                "paperId=" + paperId +
                ", themeId=" + themeId +
                ", theme='" + theme + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", op4='" + op4 + '\'' +
                ", correct='" + correct + '\'' +
                ", score=" + score +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}