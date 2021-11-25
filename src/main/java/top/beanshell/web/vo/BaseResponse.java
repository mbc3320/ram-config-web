package top.beanshell.web.vo;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.util.StringUtils;
import top.beanshell.common.exception.code.GlobalStatusCode;
import top.beanshell.common.model.enu.EnumCode;

import java.io.Serializable;

/**
 * base response body
 * @author binchao
 */
@JsonPropertyOrder({"code", "msg", "data"})
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * code
     */
    private Integer code;

    /**
     * code desc
     */
    private String msg;

    /**
     * biz data
     */
    private T data;

    public BaseResponse() {
        this.setSuccess();
    }

    public BaseResponse(String msg) {
        this.msg = msg;
    }

    public BaseResponse(EnumCode statusCode) {
        this.setStatus(statusCode);
    }

    public BaseResponse(T data) {
        this.data = data;
    }

    /**
     * set failed status code
     */
    public void setFailed() {
        this.setStatus(GlobalStatusCode.FAILED);
    }

    /**
     * set success status code
     */
    public void setSuccess() {
        this.setStatus(GlobalStatusCode.SUCCESS);
    }

    /**
     * custom status code
     * @param status  custom status code
     */
    public void setStatus(EnumCode status) {
        this.code = status.getCode();
        this.msg = status.getText();
    }

    /**
     *  custom status code with custom msg
     * @param status   custom status code
     * @param message  custom msg
     */
    public void setStatus(EnumCode status, String message) {
        this.code = status.getCode();
        this.msg = status.getText();
        if (StringUtils.hasText(message)) {
            this.data = (T) message;
        }
    }
}
