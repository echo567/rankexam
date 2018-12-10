package cn.junhui.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 军辉
 * 2018-10-20 16:09
 * 国际化组件--区域信息解析器
 * 可以在链接上携带区域信息
 */
public class MyLocaleResolver implements LocaleResolver {

    /*
    重写本地区域化信息
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lan = request.getParameter("lan");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(lan)) {
            String[] split = lan.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }


}
