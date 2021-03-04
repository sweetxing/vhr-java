package glue.pudding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by GUIXu on 2020/4/18.
 */
@Configuration
@ComponentScan(basePackages = "glue.pudding",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)})
public class RootConfig {
}
