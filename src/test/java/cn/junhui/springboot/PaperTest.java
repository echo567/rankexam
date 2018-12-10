package cn.junhui.springboot;

import cn.junhui.springboot.bean.Paper;
import cn.junhui.springboot.service.PaperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 军辉
 * 2018-11-13 9:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaperTest {

    @Autowired
    private PaperService paperService;

    /*
    查询所有试卷
     */
    @Test
    public void getall() {
        System.out.println(paperService.getall());

    }

    /*
    增加一张试卷
     */
    @Test
    public void addPaper() {
        Paper p = new Paper(4, "二级office", "201");
        paperService.addPaper(p);
    }

    /*
    删除一张试卷
     */
    @Test
    public void deletePaper() {
        paperService.deletePaper(4);
    }

    /*
    根据paperId修改单张试卷信息
     */
    @Test
    public void updatePaper() {
        Paper paper = paperService.selectPaper(231);
        paper.setPaperName("你好");
        paper.setUpdateTime(new Date());

        paperService.updatePaper(paper);
    }

    /*
    根据paperId查询试卷
    paperId 是主键
     */
    @Test
    public void selectByPaperId() {
        System.out.println(paperService.selectPaper(1));
    }

    /*
    根据paperCode查询
     */
    @Test
    public void getallbyPaperCode() {
        System.out.println(paperService.getallByPaperCode("101"));
    }

}
