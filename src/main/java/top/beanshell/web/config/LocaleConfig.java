package top.beanshell.web.config;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import top.beanshell.web.config.properties.I18nProperties;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * i18n config
 * @author binchao
 */
@Configuration
public class LocaleConfig {

    @Resource
    private I18nProperties i18nProperties;

    @Value("${spring.messages.basename}")
    private String basenames;

    /**
     * i18n解析器
     * @return localeResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        List<Locale> locales = Arrays.asList(Locale.CHINA, Locale.US);
        if (StringUtils.hasText(i18nProperties.getLocale())) {
            locales.clear();
            String[] localeArr = i18nProperties.getLocale().split(",");
            for (String locale : localeArr) {
                Locale lo = LocaleUtils.toLocale(locale);
                locales.add(lo);
            }
        }
        localeResolver.setSupportedLocales(locales);

        return localeResolver;
    }

    /**
     * 自定义校验工厂
     * @return localValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(resourceBundleMessageSource());
        return localValidatorFactoryBean;
    }

    /**
     * 自定义资源文件包加载配置
     * @return resourceBundleMessageSource
     */
    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        if (StringUtils.hasText(basenames)) {
            String[] bnArr = basenames.split(",");
            resourceBundleMessageSource.setBasenames(bnArr);
        } else {
            resourceBundleMessageSource.setBasename("messages");
        }

        resourceBundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        return resourceBundleMessageSource;
    }
}
