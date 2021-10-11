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
     * @param result
     * @return
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
     * @param data
     * @return
     */
    protected BaseResponse successResponse(Object data) {
        BaseResponse response = new BaseResponse();
        response.setData(data);
        return response;
    }

    /**
     * base response for failed status with data;
     * @param data
     * @return
     */
    protected BaseResponse failedResponse(Object data) {
        BaseResponse response = new BaseResponse();
        response.setFailed();
        response.setData(data);
        return response;
    }

    /**
     * base response for custom status code
     * @param status
     * @return
     */
    protected BaseResponse statusResponse(EnumCode status) {
        return new BaseResponse(status);
    }
}
