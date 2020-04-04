package com.bjq.my.shop.web.admin.web.interceptor;

import com.bjq.my.shop.commons.constant.ConstantUtils;
import com.bjq.my.shop.domain.TbUser;
import com.bjq.my.shop.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {

    /**
     * 拦截器之前
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //以login结尾的请求
        if(modelAndView != null && modelAndView.getViewName() !=null && modelAndView.getViewName().endsWith("login")){
            TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SUCCEED_USER);
            if(user != null){
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
