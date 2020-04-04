package com.bjq.my.shop.web.api.service.impl;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.commons.validator.BeanValidator;
import com.bjq.my.shop.domain.TbUser;
import com.bjq.my.shop.web.api.dao.TbUserDao;
import com.bjq.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {

        TbUser user = tbUserDao.login(tbUser);

        if(user != null){
            //将明文密码加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            //如果密码是一样的话就是登陆成功了
            if(password.equals(user.getPassword())){
                return user;
            }

        }

        return null;
    }

    @Override
    public int register(String username) {
        return tbUserDao.register(username);
    }


    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
            if(validator != null){
                return BaseResult.fail(validator);
            }else {
            tbUser.setUpdated(new Date());
            if (tbUser.getId() == null) {
                //密码加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes())); //密码加密
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
            }
    }


}
