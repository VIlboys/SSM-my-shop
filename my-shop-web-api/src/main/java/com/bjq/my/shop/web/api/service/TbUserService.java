package com.bjq.my.shop.web.api.service;


import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.domain.TbUser;

public interface TbUserService {

    TbUser login (TbUser tbUser);


    int register(String username);


    BaseResult save(TbUser tbUser);

}
