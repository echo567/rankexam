package cn.junhui.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 军辉
 * 2018-10-12 22:07
 * <p>
 * 自定义数据源配置
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    /*
    配置Druid的监控
    1.配置一个管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "junhui");
        initParams.put("loginPassword", "root");
        initParams.put("allow", "");//允许谁登录
        initParams.put("deny", "");//拒绝谁登录

        bean.setInitParameters(initParams);
        return bean;
    }

    /*
    配置一个web监控的filter
     */
    @Bean
    public FilterRegistrationBean webStaFtilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParms = new HashMap<>();
        //不拦截静态资源
        initParms.put("excluisions", "*.js,*.css,/druid/*");
        bean.setUrlPatterns(Arrays.asList("/*"));//拦截所有请求
        return bean;
    }
}
