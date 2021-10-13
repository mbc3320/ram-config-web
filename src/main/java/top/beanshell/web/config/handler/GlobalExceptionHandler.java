package top.beanshell.web.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.beanshell.common.exception.BaseException;
import top.beanshell.common.exception.code.GlobalStatusCode;
import top.beanshell.web.vo.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * global exception handler
 * @author binchao
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * unknown error，ex：500
     * @param req   servlet request
     * @param e     exception
     * @return      baseResponse  with unknown error
     * @throws Exception any runtime exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        BaseResponse r = new BaseResponse();
        if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("Parameter error: {}", e.getMessage(), e);
            r.setStatus(GlobalStatusCode.INVALID_PARAMETER_TYPE);
        } else if (e instanceof IllegalArgumentException) {
            log.error("Parameter error: {}", e.getMessage(), e);
            r.setStatus(GlobalStatusCode.INVALID_PARAMETER);
        } else if (e instanceof HttpMessageNotReadableException) {
            r.setStatus(GlobalStatusCode.MESSAGE_BODY_INVALID);
        } else {
            log.error("unknown error: {}", e.getMessage(), e);
            r.setStatus(GlobalStatusCode.SERVER_ERROR);
        }
        return r;
    }

    /**
     * api not found 404
     * @param req     servlet request
     * @param e       exception
     * @return        baseResponse  with  api not found error
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public BaseResponse notFoundErrorHandler(HttpServletRequest req, NoHandlerFoundException e) {
        BaseResponse response = new BaseResponse();
        response.setStatus(GlobalStatusCode.RESOURCE_IS_NOT_EXIST);
        return response;
    }

    /**
     * parameter binding exception
     * @param req    servlet request
     * @param e      exception
     * @return       baseResponse   with  parameter error
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public BaseResponse bindErrorHandler(HttpServletRequest req, BindException e) {
        log.debug("bind error: {}", e.getMessage(), e);
        return returnArgumentErrorMsg(e.getBindingResult());
    }

    /**
     * parameter invalid exception
     * @param e     exception
     * @return      baseResponse  with parameter error
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse argumentNotValidErrorHandler(MethodArgumentNotValidException e) {
        log.debug("argumentNotValid: {}", e.getMessage(), e);
        return returnArgumentErrorMsg(e.getBindingResult());
    }


    /**
     * custom exception
     * @param req   servlet request
     * @param e     exception
     * @return      baseResponse  with custom status code error
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResponse customErrorHandler(HttpServletRequest req, BaseException e) {
        BaseResponse response = new BaseResponse();
        response.setStatus(e.getStatus(), e.getMessage());
        return response;
    }

    /**
     * request method unsupport exception
     * @param e   exception
     * @return    baseResponse with method not support error
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public BaseResponse requestMethodNotSupportedErrorHandler(HttpRequestMethodNotSupportedException e) {
        BaseResponse response = new BaseResponse(GlobalStatusCode.REQUEST_METHOD_NOT_SUPPORT);
        return response;
    }

    /**
     * parameter mismatch
     * @param bindingResult
     * @return  error msg
     */
    private String handleArgumentErrorMsg(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        if(errors != null){
            for (FieldError error: errors) {
                String msg = error.getDefaultMessage();
                sb.append(msg).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * parameter mismatch
     * @param bindingResult
     * @return   baseResponse
     */
    private BaseResponse returnArgumentErrorMsg(BindingResult bindingResult) {
        String errorMsg = handleArgumentErrorMsg(bindingResult);

        log.error("invalid parameter error: {}", errorMsg);

        BaseResponse response = new BaseResponse();
        response.setStatus(GlobalStatusCode.INVALID_PARAMETER);
        response.setData(errorMsg);
        return response;
    }
}
