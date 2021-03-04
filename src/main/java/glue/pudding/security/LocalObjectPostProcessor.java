package glue.pudding.security;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by GUIXu on 2020/4/28.
 */
public class LocalObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    LocalMetadataSource metadataSource;
    UrlAccessDecisionManager accessDecisionManager;

    public LocalObjectPostProcessor(LocalMetadataSource metadataSource, UrlAccessDecisionManager accessDecisionManager) {
        this.metadataSource = metadataSource;
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
        o.setSecurityMetadataSource(metadataSource);
        o.setAccessDecisionManager(accessDecisionManager);
        return o;
    }
}
