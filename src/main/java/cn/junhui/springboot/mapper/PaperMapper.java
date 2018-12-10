package cn.junhui.springboot.mapper;

import cn.junhui.springboot.bean.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 军辉
 * 2018-11-13 9:01
 */
@Mapper
public interface PaperMapper {
    /*
    查询所有试卷
     */
    @Select("select * from tb_paper")
    List<Paper> getallPaper();

    /*
    根据paperCode查询试卷 不是主键，所以返回list
     */
    @Select("select * from tb_paper where paperCode = #{paperCode}")
    List<Paper> getallByPaperCode(@Param("paperCode") String paperCode);

    /*
    根据paperId查询试卷
     */
    @Select("select * from tb_paper where paperId = #{paperId}")
    Paper getPaperByPaperId(@Param("paperId") int paperId);

    /*
    增加试卷
     */
    @Insert("insert into tb_paper(paperId,paperName,paperCode) values(#{paperId},#{paperName},#{paperCode})")
    Integer addPaper(@Param("paperId") Integer paperId, @Param("paperName") String paperName,
                     @Param("paperCode") String paperCode);

    /*
    查询单张试卷信息paperId主键
     */
    @Select("select * from tb_paper where paperId = #{paperId}")
    Paper getPaper(Integer paperId);

    /*
    删除单张试卷
     */
    @Delete("delete from tb_paper where paperId = #{paperId}")
    Integer deletePaper(@Param("paperId") Integer paperId);

    /*
    修改单张试卷信息
     */
    @Update("update tb_paper set paperName = #{paperName},paperCode = #{paperCode} where paperId = #{paperId}")
    Integer updatePaper(@Param("paperId") int paperId, @Param("paperName") String paperName,
                        @Param("paperCode") String paperCode);
}
