package cn.junhui.springboot.mapper;

import cn.junhui.springboot.bean.Language;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 军辉
 * 2018-11-15 10:28
 */
@Mapper
public interface LanguageMapper {

    /*
    查询所有语言考试项目
     */
    @Select("select * from tb_language")
    List<Language> getallLanguage();

    /*
    根据语言代码查询某个语言
     */
    @Select("select * from tb_language where lanCode = #{lanCode}")
    Language selectLan(@Param("lanCode") String lanCode);

    /*
    增加语言考试类型
     */
    @Insert("insert into tb_language (lanCode,lanLevel,lanType,lanTime)values(#{lanCode},#{lanLevel},#{lanType},#{lanTime})")
    Integer addLanguage(@Param("lanCode") String lanCode, @Param("lanLevel") Integer lanLevel, @Param("lanType") String lanType,
                        @Param("lanTime") Date lanTime);

    /*
    删除语言考试类型
     */
    @Delete("delete from tb_language where lanCode = #{lanCode}")
    Integer deleteLanguage(@Param("lanCode") String lanCode);

    /*
    根据语言代码修改单个语言信息
     */
    @Update("update tb_language set lanLevel = #{lanLevel},lanType = #{lanType},lanTime = #{lanTime} where " +
            "lanCode = #{lanCode}")
    Integer updateLanguage(@Param("lanCode") String lanCode, @Param("lanLevel") Integer lanLevel,
                           @Param("lanType") String lanType, @Param("lanTime") Date lanTime);


}
