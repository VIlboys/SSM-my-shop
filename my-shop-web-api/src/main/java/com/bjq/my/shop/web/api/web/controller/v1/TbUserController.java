package com.bjq.my.shop.web.api.web.controller.v1;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.domain.TbUser;
import com.bjq.my.shop.web.api.dao.TbUserDao;
import com.bjq.my.shop.web.api.service.TbUserService;
import com.bjq.my.shop.web.api.web.dto.Register;
import com.bjq.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 会员管理
 */
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){

        TbUser user = tbUserService.login(tbUser);
        if(user == null){
            return BaseResult.fail("账号或密码错误");
        } else {
            TbUserDTO tbUserDTO = new TbUserDTO();
            BeanUtils.copyProperties(user, tbUserDTO);
            return BaseResult.success("成功",tbUserDTO);
        }


    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public BaseResult save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {

        String username = tbUser.getUsername();
        if(tbUserService.register(username) > 0){

            //注册失败a
            Register dto = new Register();
            BeanUtils.copyProperties(tbUser,dto);
            return BaseResult.fail("注册失败用户名重复",dto);
        }
        else{
            //如果数据库中没有这个username的话就可以进行注册
            Register dto = new Register();
            BaseResult baseResult = tbUserService.save(tbUser);
            BeanUtils.copyProperties(baseResult,dto);
            return BaseResult.success("成功",dto);

        }
    }


}
