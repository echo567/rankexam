package cn.junhui.springboot.mapper;

import cn.junhui.springboot.bean.Items;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 军辉
 * 2018-10-29 10:22
 */
public class BatchItems {

    /*
    批量增加试题(无意义)
     */
    public String batPapAdd(Map map) {
        List<Items> ep = (List<Items>) map.get("list");
        StringBuffer sb = new StringBuffer();
        sb.append("insert into tb_items(paperId,themeId,theme,op1,op2,op3,op4,currect，score) values");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].paperId},#'{'list[{0}].themeId},#'{'list[{0}].theme}," +
                        "#'{'list[{0}].op1},#'{'list[{0}].op2},#'{'list[{0}].op3},#'{'list[{0}].op4}," +
                        "#'{'list[{0}].correct}, #'{'list[{0}].score})"
        );
        for (int i = 0; i < ep.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < ep.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /*
    批量删除试题(根据题号)
     */
    public String batPapDel(Map map) {
        List<Items> items = (List<Items>) map.get("list");
        StringBuffer sb = new StringBuffer();
        sb.append("delete from tb_items where themeId in (");
        for (int i = 0; i < items.size(); i++) {
            sb.append("'").append(items.get(i).getThemeId()).append("'");
            if (i < items.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }


    /*
    动态查询试题
     */
    public String dynamicPapSel(Items ep) {
        return new SQL() {{
            SELECT("*");
            FROM("tb_items");
            if (ep.getThemeId() != null) {
                WHERE("themeId = #{themeId}");
            }
            if (ep.getPaperId() != null) {
                WHERE("paperId = #{paperId}");
            }
        }}.toString();
    }

    /*
    动态修改试题信息
     */
    public String dynamicItemUdp(Items items) {
        return new SQL() {{
            UPDATE("tb_items");
            if (items.getTheme() != null) {
                SET("theme = #{theme}");
            }
            if (items.getOp1() != null) {
                SET("op1 = #{op1}");
            }
            if (items.getOp2() != null) {
                SET("op2 = #{op2}");
            }
            if (items.getOp3() != null) {
                SET("op3 = #{op3}");
            }
            if (items.getOp4() != null) {
                SET("op4 = #{op4}");
            }
            if (items.getCorrect() != null) {
                SET("correct = #{correct}");
            }
            if (items.getScore() != 0) {
                SET("score = #{score}");
            }
            if (items.getNote() != null) {
                SET("note = #{note}");
            }
            if (items.getUpdateTime() != null) {
                SET("updateTime = #{updateTime}");
            }
            WHERE("paperId=#{paperId} and themeId=#{themeId}");
        }}.toString();
    }

    /*
    删除试题
     */
    public String deleteItem(Integer papaerId, Integer themeId) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from tb_items where paperId = ")
                .append(papaerId + " and themeId = ")
                .append(themeId);
        //System.out.println("sql语句：" + sb);
        return sb.toString();
    }

}
