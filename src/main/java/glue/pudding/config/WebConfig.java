package glue.pudding.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUIXu on 2020/4/18.
 */
@Configuration
//@EnableWebMvc  // enabling spring mvc
@ComponentScan("glue.pudding.web")
public class WebConfig extends WebMvcConfigurationSupport{

    // configure HTML view resolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/classes/");
        resolver.setSuffix(".html");
        //resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }


    /**
     * 设置模板引擎
     * @param templateResolver
     * @return
     */
    @Bean(name = "fileTemplateEngine")
    public TemplateEngine templateEngine(ITemplateResolver templateResolver){
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    /**
     * 模板解析引擎
     * @return
     */
    @Bean
    public ITemplateResolver templateResolver(){
        ITemplateResolver resolver = new FileTemplateResolver();
        return resolver;

    }

    /*@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }
*/
    // configure static content handling
    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/

//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(stringConverter());
//        converters.add(fastJsonHttpMessageConverter());
//    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(stringConverter());
        converters.add(fastJsonHttpMessageConverter());
    }

    private HttpMessageConverter<String> stringConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    private HttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter() {
            @Override
            protected boolean supports(Class<?> clazz) {
                return clazz != String.class && super.supports(clazz);
            }
        };
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(Charset.forName("UTF-8"));
        config.setSerializerFeatures(
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue,
                // SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty
        );
        config.setSerializeFilters(
                new ValueFilter() {
                    @Override
                    public Object process(Object o, String s, Object value) {
                        if (value instanceof Double) {
                            return value.toString();
                        }
                        return value;
                    }
                }
        );
        converter.setFastJsonConfig(config);

        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);//设定json格式且编码为UTF-8
        //mediaTypes.add(MediaType.APPLICATION_STREAM_JSON);
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

}
