package glue.pudding.config;

import glue.pudding.security.WebSecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by GUIXu on 2020/4/18.
 */
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置ContextLoaderListener所创建的应用上下文中的bean
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                RootConfig.class,
                DataConfig.class,
                CachingConfig.class,
                WebSecurityConfig.class,
                MailConfig.class
        };
    }

    /**
     * 加载文件配置和java配置中所声明的bean
     * 配置DispatcherServlet所创建的应用上下文中的bean
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class};
    }

    /**
     * 配置DispatcherServlet的请求路径
     * @return
     */
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }


}
