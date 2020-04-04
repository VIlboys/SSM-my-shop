package com.bjq.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 会员登陆dto
 */
@Data
public class TbUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verification;


}
