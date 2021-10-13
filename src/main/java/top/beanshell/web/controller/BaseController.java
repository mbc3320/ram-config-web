package top.beanshell.web.controller;

import top.beanshell.common.exception.code.GlobalStatusCode;
import top.beanshell.common.model.enu.EnumCode;
import top.beanshell.web.vo.BaseResponse;

/**
 * Base Controller
 * @author binchao
 */
public class BaseController {

    /**
     *  base response
     *  just return success or failed
     * @param result  handle result
     * @return        baseResponse with success or failed
     */
    protected BaseResponse baseResponse(boolean result) {
        if (result) {
            return new BaseResponse();
        } else {
            return new BaseResponse(GlobalStatusCode.FAILED);
        }
    }

    /**
     * base response for success status with data
     * @param data   biz data
     * @return       baseResponse with success status and biz data
     */
    protected BaseResponse successResponse(Object data) {
        BaseResponse response = new BaseResponse();
        response.setData(data);
        return response;
    }

    /**
     * base response for failed status with data;
     * @param data   biz data or error msg
     * @return       baseResponse with failed status and biz data
     */
    protected BaseResponse failedResponse(Object data) {
        BaseResponse response = new BaseResponse();
        response.setFailed();
        response.setData(data);
        return response;
    }

    /**
     * base response for custom status code
     * @param status    status code
     * @return          baseResponse with custom status code
     */
    protected BaseResponse statusResponse(EnumCode status) {
        return new BaseResponse(status);
    }
}
