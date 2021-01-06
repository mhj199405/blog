package com.irm.interceptor;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            super.addInterceptors(registry);
            // 拦截admin/下面所有的页面
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                    // 放行了/admin  /admin/login
                    .excludePathPatterns("/admin")
                     .excludePathPatterns("/admin/login");
        }

}
