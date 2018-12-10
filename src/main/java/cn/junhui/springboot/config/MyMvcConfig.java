package cn.junhui.springboot.config;

import cn.junhui.springboot.component.MyLocaleResolver;
import cn.junhui.springboot.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 军辉
 * 2018-10-20 16:17
 * <p>
 * implements WebMvcConfigurer ： 不会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 *
 * @EnableWebMvc + implements WebMvcConfigurer ： 会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 * extends WebMvcConfigurationSupport ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 * extends DelegatingWebMvcConfiguration ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    FilterRegistrationBean filterRegistrationBean;

    /*
    国际化返回方法
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /*
    注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);

        // 拦截路径
        loginRegistry.addPathPatterns("/**");

        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/toRegister");
        loginRegistry.excludePathPatterns("/toLogin");
        loginRegistry.excludePathPatterns("/error");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/register");
        loginRegistry.excludePathPatterns("/root");


        // 排除资源请求 "*.js,*.css,/druid/*
        loginRegistry.excludePathPatterns("/**/*.js");
        loginRegistry.excludePathPatterns("/**/*.css");
        loginRegistry.excludePathPatterns("/**/*.svg");
        loginRegistry.excludePathPatterns("/druid/*");


    }
}
