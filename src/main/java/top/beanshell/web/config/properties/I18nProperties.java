package top.beanshell.web.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author binchao
 */
@Component
@ConfigurationProperties(prefix = "ram.application.i18n")
@Data
public class I18nProperties {

    /**
     * language list
     * zh_CN,zh_TW,zh_HK,en_US...
     */
    private String locale;
}
