package com.bjq.my.shop.web.admin.dao;

import com.bjq.my.shop.commons.persistence.BaseDao;
import com.bjq.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TbUserDao  extends BaseDao<TbUser>   {

     /**
      * 根据邮箱查询用户信息
      * @param email
      * @return
      */
     TbUser getByEmail(String email);
}
