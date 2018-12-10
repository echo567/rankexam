package cn.junhui.springboot.mapper;

import cn.junhui.springboot.bean.Items;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 军辉
 * 2018-10-29 21:34
 */
@Mapper
public interface ItemsMapper {
    /*
   查询所有考题
   按照  试卷id降序 试题id升序 排序
    */
    @Select("select * from tb_items order by paperId desc,themeId asc")
    List<Items> getAllItem();

    /*
    查询试题根据试卷id查询 返回list
     */
    @Select("select * from tb_items where paperId = #{paperId} order by themeId")
    List<Items> getallByPaperId(@Param("paperId") Integer paperId);

    /*
    增加试题 试题id自增
     */
    @Insert("insert into tb_items(paperId,theme,op1,op2,op3,op4,correct,score,note)" +
            " values(#{paperId},#{theme},#{op1},#{op2},#{op3},#{op4},#{correct},#{score},#{note})")
    Integer addItem(@Param("paperId") Integer paperId, @Param("theme") String theme,
                    @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4,
                    @Param("correct") String correct, @Param("score") Integer score, @Param("note") String note);

    /*
    增加试题， 试题id非自增
     */
    @Insert("insert into tb_items(paperId,themeId,theme,op1,op2,op3,op4,correct,score,note)" +
            " values(#{paperId},#{themeId},#{theme},#{op1},#{op2},#{op3},#{op4},#{correct},#{score},#{note})")
    Integer addItem_plus(@Param("paperId") Integer paperId, @Param("themeId") Integer themeId, @Param("theme") String theme,
                         @Param("op1") String op1, @Param("op2") String op2, @Param("op3") String op3, @Param("op4") String op4,
                         @Param("correct") String correct, @Param("score") Integer score, @Param("note") String note);

    /*
    批量增加试题
     */
    @InsertProvider(type = BatchItems.class, method = "batPapAdd")
    Integer batPapAdd(List<Items> list);


    /*
    批量删除试题(有问题)
     */
    @DeleteProvider(type = BatchItems.class, method = "batPapDel")
    Integer batPapDel(List<Items> list);

    /*
    多条件查询
    SELECT * FROM ar WHERE title='php数组' AND category='1'
     */
    @Select("select * from tb_items where paperId = #{paperId} and themeId = #{themeId}")
    Items selPap(@Param("paperId") Integer paperId,
                 @Param("themeId") Integer themeId);

    /*
    修改试题
     */
    @UpdateProvider(type = BatchItems.class, method = "dynamicItemUdp")
    Integer dynamicItemUdp(Items items);

    /*
    删除试题
     */
    @DeleteProvider(type = BatchItems.class, method = "deleteItem")
    Integer deleteItem(Integer paperId, Integer themeId);
}
