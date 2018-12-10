package cn.junhui.springboot.service;

import cn.junhui.springboot.bean.Paper;
import cn.junhui.springboot.mapper.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 军辉
 * 2018-11-13 20:11
 */
@Service
public class PaperService {

    @Autowired
    private PaperMapper paperMapper;

    /*
    查询所有试卷
     */
    public List<Paper> getall() {
        return paperMapper.getallPaper();
    }

    /*
    查询试卷byPaperCode
     */
    public List<Paper> getallByPaperCode(String paperCode) {
        return paperMapper.getallByPaperCode(paperCode);
    }

    /*
    查询试卷byPaperId
     */
    public Paper getPaperByPaperId(int paperId) {
        return paperMapper.getPaperByPaperId(paperId);
    }

    /*
    增加一张试卷
     */
    public void addPaper(Paper paper) {
        paperMapper.addPaper(paper.getPaperId(), paper.getPaperName(), paper.getPaperCode());
    }

    /*
    删除单张试卷
     */
    public void deletePaper(int paperId) {
        paperMapper.deletePaper(paperId);
    }

    /*
    查询一张试卷
     */
    public Paper selectPaper(int paperId) {
        return paperMapper.getPaper(paperId);
    }

    /*
    修改单张试卷信息
     */
    public void updatePaper(Paper paper) {
        paperMapper.updatePaper(paper.getPaperId(), paper.getPaperName(), paper.getPaperCode());
    }

}
