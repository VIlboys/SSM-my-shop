package com.bjq.my.shop.web.ui.interceptor;

import com.bjq.my.shop.web.ui.constant.SystemConstants;
import com.bjq.my.shop.web.ui.dto.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(SystemConstants.SESSION_USER_KEY);

        //未登录状态
        if(tbUser == null){
            return true;
        }
        //已经登陆的状态
        else{
            //重定向到index
            httpServletResponse.sendRedirect("/index");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
