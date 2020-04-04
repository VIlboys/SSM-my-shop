package com.bjq.my.shop.web.admin.web.controller;


import com.bjq.my.shop.commons.constant.ConstantUtils;
import com.bjq.my.shop.domain.TbUser;
import com.bjq.my.shop.domain.User;
import com.bjq.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController  {

    @Autowired
    private TbUserService tbUserService;


    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登陆功能
     * @param email
     * @param password
     * @param httpServletResponse
     * @param httpServletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, Model model,HttpServletResponse response) {
        TbUser tbUser = tbUserService.login(email, password);
        //登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login();
        }
        //登录成功
        else {
            httpServletRequest.getSession().setAttribute(ConstantUtils.SUCCEED_USER, tbUser);
              Cookie cookie = new Cookie("username", String.format("%s:%s",email, password));
            cookie.setMaxAge(1000);
            cookie.setPath("/");
            cookie.setSecure(false);
            cookie.setVersion(0);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return "redirect:/main";

        }
    }

    /**
     * 注销功能
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return login();

    }

}
