package top.beanshell.web.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * base request body
 * @param <T>   request data class
 * @author binchao
 */
@Data
public class BaseRequest<T> implements Serializable {

    /**
     * timestamp
     */
    private Long timestamp;

    /**
     *  param sign string
     */
    private String sign;

    /**
     * param
     */
    @Valid
    @NotNull(message = "{i18n.request.valid.ram-config-web.base-request.data}")
    private T data;

}
