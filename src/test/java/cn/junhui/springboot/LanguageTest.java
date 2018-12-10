package cn.junhui.springboot;

import cn.junhui.springboot.bean.Language;
import cn.junhui.springboot.service.LanguageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 军辉
 * 2018-11-15 10:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageTest {

    @Autowired
    private LanguageService languageService;

    /*
    查询所有
     */
    @Test
    public void getall() {
        System.out.println(languageService.getall());
    }

    /*
    查询单个
     */
    @Test
    public void select() {
        System.out.println(languageService.getLanguage("103"));
    }

    /*
    增加单个
     */
    @Test
    public void add() {
        Language language = new Language("501", 5, "java五级", new Date());
        languageService.addLanguage(language);
    }

    /*
    删除单个
     */
    @Test
    public void delete() {
        languageService.deleteLanguage("501");
    }

    /*
    根据paperId修改单个信息
     */
    @Test
    public void update() {
        Language language = languageService.getLanguage("501");
        language.setUpdateTime(new Date());
        language.setLanLevel(555);
        languageService.updateLanguage(language);

    }
}
