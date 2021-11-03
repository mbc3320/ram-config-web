package top.beanshell.web.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.beanshell.common.service.I18nService;
import top.beanshell.web.vo.BaseResponse;

/**
 * 状态码i18n处理器
 * @author binchao
 */
@RestControllerAdvice
@Slf4j
public class GlobalI18nResponseHandler implements ResponseBodyAdvice<BaseResponse> {

    @Autowired
    private I18nService i18nService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public BaseResponse beforeBodyWrite(BaseResponse body, MethodParameter returnType,
                                        MediaType selectedContentType,
                                        Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                        ServerHttpRequest request,
                                        ServerHttpResponse response) {
        // i18n 转换
        String msg = i18nService.getMessage(body.getCode());
        if (null != msg) {
            body.setMsg(msg);
        }
        return body;
    }
}
