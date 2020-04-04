package com.bjq.my.shop.web.ui.controller;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.commons.utils.EmailSendUtils;
import com.bjq.my.shop.web.ui.api.UsersApi;
import com.bjq.my.shop.web.ui.constant.SystemConstants;
import com.bjq.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    private EmailSendUtils emailSendUtils; // 放入Spring帮我们创建了



    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(TbUser tbUser, Model model,HttpServletRequest request) throws Exception {

        BaseResult register = UsersApi.register(tbUser);

        if(register.getStatus() == 200){
                emailSendUtils.send("用户注册", String.format("用户 【%s】 注册 MyShop", tbUser.getUsername()),"ad121357@163.com");

                //重定向回首页
                return "redirect:/login";
         }else if(register.getStatus() == 500){
            model.addAttribute("baseResult", BaseResult.fail(register.getMessage()));
            return null;
         }
            //登陆成功
            else {
                model.addAttribute("baseResult", BaseResult.fail("注册失败可能是名字重复了~!"));
                return register();
            }
    }



    /**
     * 检查验证码是否正确
     * @param tbUser
     * @param request
     * @return
     */
    private boolean checkVerification(TbUser tbUser, HttpServletRequest request){
        String verification =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        //判断是否匹配
        if(StringUtils.equals(verification,tbUser.getVerification())){
            return true;
        }
        return false;

    }


}
