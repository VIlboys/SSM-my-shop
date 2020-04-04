package com.bjq.my.shop.web.admin.web.controller;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.commons.dto.PageInfo;
import com.bjq.my.shop.domain.TbUser;
import com.bjq.my.shop.web.admin.abstracts.AbstractBaseController;
import com.bjq.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    @Autowired
    private TbUserService tbUserService;

    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    @Override
    public String form() {
        return null;
    }

    @Override
    public String save(TbUser entity, Model model, RedirectAttributes redirectAttributes) {
        return null;
    }

    @Override
    public BaseResult delete(String ids) {
        return null;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser) {

        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);

        PageInfo<TbUser> pageInfo = service.page(start,length,draw,tbUser);

        return pageInfo;

    }

    @Override
    public String detail() {
        return null;
    }


}
