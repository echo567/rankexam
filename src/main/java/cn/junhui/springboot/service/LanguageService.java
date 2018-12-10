package cn.junhui.springboot.service;

import cn.junhui.springboot.bean.Language;
import cn.junhui.springboot.mapper.LanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 军辉
 * 2018-11-15 10:45
 */
@Service
public class LanguageService {

    @Autowired
    LanguageMapper languageMapper;

    /*
    查询所有
     */
    public List<Language> getall() {
        return languageMapper.getallLanguage();
    }

    /*
    根据语言代码查询单个语言信息
     */
    public Language getLanguage(String lanCode) {
        return languageMapper.selectLan(lanCode);
    }

    /*
    增加考试类型
     */
    public void addLanguage(Language language) {
        languageMapper.addLanguage(language.getLanCode(), language.getLanLevel(), language.getLanType(),
                language.getLanTime());
    }

    /*
    删除考试语言类型
     */
    public void deleteLanguage(String lanCode) {
        languageMapper.deleteLanguage(lanCode);
    }

    /*
    根据语言代码修改语言信息
     */
    public void updateLanguage(Language language) {
        language.setUpdateTime(new Date());
        languageMapper.updateLanguage(language.getLanCode(), language.getLanLevel(),
                language.getLanType(), language.getLanTime());
    }

}
