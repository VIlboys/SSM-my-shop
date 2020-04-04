package com.bjq.my.shop.web.admin.service.Impl;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.domain.TbUser;
import com.bjq.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.bjq.my.shop.web.admin.dao.TbUserDao;
import com.bjq.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;


    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser entity) {
        return null;
    }

    /**
     * 登陆逻辑处理
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser != null){
            //加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            //判断加密后的密码和数据库中存放的密码是否匹配，匹配则表示允许登录
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }

        }
        return null;
    }



}
