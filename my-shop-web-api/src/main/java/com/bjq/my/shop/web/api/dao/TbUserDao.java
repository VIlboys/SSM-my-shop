package com.bjq.my.shop.web.api.dao;

import com.bjq.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 */
@Repository
public interface TbUserDao {


    TbUser login (TbUser tbUser);

    int register(String username);

    void insert(TbUser tbUser);

}
