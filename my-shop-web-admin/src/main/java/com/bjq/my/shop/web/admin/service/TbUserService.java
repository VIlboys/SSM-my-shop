package com.bjq.my.shop.web.admin.service;

import com.bjq.my.shop.commons.persistence.BaseService;
import com.bjq.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {

    List<TbUser> selectAll();

    /**
     * 根据邮箱和密码查询用户的信息
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);

}
