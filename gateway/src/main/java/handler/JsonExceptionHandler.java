package handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangYin
 * @create 2019-04-11-14:37
 */

/**
 * 自定义异常处理类
 * springboot提供了一个默认的异常处理类  重写（继承此类）
 * 返回统一的JSON格式
 */
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {
    /*重写构造器*/
    public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 自定义异常信息
     */
    public String buildMessage(ServerRequest request, Throwable ex) {
        StringBuffer sb = new StringBuffer("Faild to handle request[");
        //获取方法名称
        sb.append(request.methodName());
        sb.append(" ");
        //路径
        sb.append(request.uri());
        sb.append("]");
        if (ex != null) {
            sb.append(":");
            //获取异常信息
            sb.append(ex.getMessage());
        }
        return sb.toString();
    }

    /**
     * 构建返回统一的JSON数据格式
     */
    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        //状态码
        map.put("code", status);
        //异常信息
        map.put("message", errorMessage);
        //数据    因为出现异常所以数据为空
        map.put("data", null);
        return map;
    }

    /**
     * 根据code来获取相应的httpstatus
     */
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errAttributes) {
        int status = (int) errAttributes.get("code");
        return HttpStatus.valueOf(status);
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     */
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        int code = 500;
        Throwable error = super.getError(request);
        if (error instanceof NotFoundException) {
            code = 404;
        }
        return response(code, this.buildMessage(request, error));
    }


}