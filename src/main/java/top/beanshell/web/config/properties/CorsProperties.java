package top.beanshell.web.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * CORS config property
 * @author: binchao
 */
@Component
@ConfigurationProperties(prefix = "ram.application.cors")
@Data
public class CorsProperties {

    /**
     * domains
     */
    private String domain;

    /**
     * context
     */
    private String path;

    /**
     * allow header
     */
    private String allowedHeader;

    /**
     * allow method
     */
    private String allowedMethod;
}
