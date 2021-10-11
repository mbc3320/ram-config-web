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

    public void setFailed() {
        this.setStatus(GlobalStatusCode.FAILED);
    }
    public void setSuccess() {
        this.setStatus(GlobalStatusCode.SUCCESS);
    }

    public void setStatus(EnumCode status) {
        this.code = status.getCode();
        this.msg = status.getText();
    }

    public void setStatus(EnumCode status, String message) {
        this.code = status.getCode();
        if (null != status && StringUtils.hasText(message)) {
            this.msg = status.getText() + ">>>" + message;
        } else {
            this.msg = status.getText();
        }
    }
}
