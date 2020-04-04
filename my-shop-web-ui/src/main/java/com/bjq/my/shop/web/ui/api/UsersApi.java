package com.bjq.my.shop.web.ui.api;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.commons.utils.HttpClientUtils;
import com.bjq.my.shop.commons.utils.MapperUtils;
import com.bjq.my.shop.web.ui.dto.Register;
import com.bjq.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员管理接口
 */
public class UsersApi {


    /**
     * 登陆
     * @param tbUser
     * @return
     */
    public static TbUser login (TbUser tbUser) throws Exception {

        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));

        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));

        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);

        return user;

    }



    /**
     * 注册
     */
    public static BaseResult register (TbUser tbUser) throws Exception {

        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getUsername()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));
        params.add(new BasicNameValuePair("phone",tbUser.getPhone()));

        String json = HttpClientUtils.doPost(API.API_REGISTER_USER,params.toArray(new BasicNameValuePair[params.size()]));

        Register register = MapperUtils.json2pojoByTree(json, "data", Register.class);

        //System.out.println(register);
        if(register.getStatus() == 200){

            return BaseResult.success("成功");
        }else if(register.getStatus() == 500) {
            return BaseResult.fail(register.getMessage());
        }else {
            return BaseResult.fail("注册失败可能是名字重复了~!");
        }

    }


}
