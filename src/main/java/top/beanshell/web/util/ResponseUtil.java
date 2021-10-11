package top.beanshell.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.beanshell.common.utils.JSON;
import top.beanshell.web.vo.BaseResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http response util
 * @author: binchao
 */
public final class ResponseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * json message response
     * @param response
     * @param result
     * @throws IOException
     */
    public static void responseJson(HttpServletResponse response, BaseResponse result) {
        PrintWriter out = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            String json = JSON.toJSONString(result);
            logger.debug("Response result = {}", json);
            out.append(json);
            out.flush();
        } catch (IOException e) {
            logger.error("Response error: {}", e.getMessage(), e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
}
