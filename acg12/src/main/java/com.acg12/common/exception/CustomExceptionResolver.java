package com.acg12.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: CustomExceptionResolver</p>
 * <p>Description:全局异常处理器 </p>
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    /**
     * （非 Javadoc）
     * <p>Title: resolveException</p>
     * <p>Description: </p>
     *
     * @param request
     * @param response
     * @param handler
     * @param ex       系统 抛出的异常
     * @return
     * @see HandlerExceptionResolver#resolveException(HttpServletRequest, HttpServletResponse, Object, Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        CustomException customException = null;
        if (ex instanceof CustomException) {
            customException = (CustomException) ex;
        } else {
            customException = new CustomException(ex.toString());
        }
        System.err.println(customException.getMessage());
//        try {
////            response.getWriter().write("{\"res\":-1,\"msg\":\"" + customException.getMessage() + "\"}");
//        } catch (IOException e) {
//
//        }
        return modelAndView;
    }
}
