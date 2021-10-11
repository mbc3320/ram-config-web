package top.beanshell.web.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * primary key request parameter
 * @author: binchao
 */
@Data
public class PrimaryKeyRequest implements Serializable {

    /**
     * id
     */
    @NotNull(message = "id必填")
    private Long id;
}
