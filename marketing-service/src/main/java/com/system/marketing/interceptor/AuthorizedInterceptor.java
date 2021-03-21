package com.system.marketing.interceptor;

import com.system.marketing.consts.KeyConsts;
import com.system.marketing.exception.BusinessException;
import com.system.marketing.util.RedisUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.marketing.vo.ResultCode;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 12:29
 * @description:
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtil redisUtil;

    /**
     * 该方法需要preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

    }

    /**
     * 这个方法在preHandle方法返回值为true的时候才会执行。
     * 执行时间是在处理器进行处理之 后，也就是在Controller的方法调用之后执行。
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {

    }

    /**
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 当preHandle的返回值为false的时候整个请求就结束了。
     * 如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        /** 获得请求的ServletPath */
        String servletPath = request.getServletPath();
        String url = request.getRequestURL().toString();
        // 校验登录人信息
        String accessToken = request.getHeader("access-token");
        Object obj = redisUtil.get(KeyConsts.USER_KEY + accessToken);
        if (obj == null) {
            throw new BusinessException(ResultCode.NO_TOKEN);
        }
        return true;
    }
}
