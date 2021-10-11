package top.beanshell.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import top.beanshell.web.config.properties.CorsProperties;

import javax.annotation.Resource;

/**
 * CORS 跨域配置
 * @author binchao
 */
@Configuration
@ConditionalOnProperty(name = "ram.application.cors.enable", havingValue = "true")
public class CorsConfig {

    @Resource
    private CorsProperties corsProperties;

    /**
     * cors filter
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        if (StringUtils.hasText(corsProperties.getDomain())) {
            String[] domains = corsProperties.getDomain().split(";");
            for (String domain : domains) {
                config.addAllowedOrigin(domain);
            }
        }
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration(corsProperties.getPath(), config);
        return new CorsFilter(source);
    }

}
