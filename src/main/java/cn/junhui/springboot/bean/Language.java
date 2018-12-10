package cn.junhui.springboot.bean;

import java.util.Date;

/*
考试类型（语言）
 */
public class Language {
    private String lanCode;

    private Integer lanLevel;

    private String lanType;

    private Date lanTime;

    private Date createTime;

    private Date updateTime;

    public Language() {
    }

    public Language(String lanCode, Integer lanLevel, String lanType, Date lanTime) {
        this.lanCode = lanCode;
        this.lanLevel = lanLevel;
        this.lanType = lanType;
        this.lanTime = lanTime;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public int getLanLevel() {
        return lanLevel;
    }

    public void setLanLevel(int lanLevel) {
        this.lanLevel = lanLevel;
    }

    public String getLanType() {
        return lanType;
    }

    public void setLanType(String lanType) {
        this.lanType = lanType;
    }

    public Date getLanTime() {
        return lanTime;
    }

    public void setLanTime(Date lanTime) {
        this.lanTime = lanTime;
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

    @Override
    public String toString() {
        return "Language{" +
                "lanCode='" + lanCode + '\'' +
                ", lanLevel=" + lanLevel +
                ", lanType='" + lanType + '\'' +
                ", lanTime=" + lanTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}