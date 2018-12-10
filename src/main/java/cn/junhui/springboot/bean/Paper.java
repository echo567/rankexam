package cn.junhui.springboot.bean;

import java.util.Date;

/**
 * 军辉
 * 2018-11-05 10:05
 * 试卷
 */
public class Paper {
    private Integer paperId;
    private String paperName;
    private String paperCode;
    private Date createTime;
    private Date updateTime;

    public Paper() {
    }

    public Paper(int paperId, String paperName, String paperCode) {
        this.paperId = paperId;
        this.paperName = paperName;
        this.paperCode = paperCode;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
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
        return "Paper{" +
                "paperId=" + paperId +
                ", paperName='" + paperName + '\'' +
                ", paperCode='" + paperCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
