package com.bjq.my.shop.web.admin.abstracts;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.commons.dto.PageInfo;
import com.bjq.my.shop.commons.persistence.BaseEntity;
import com.bjq.my.shop.commons.persistence.BaseService;
import com.bjq.my.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>> {

    /*
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    /**
     * 跳转列表页面
     * @return
     */
    public abstract String list();

    /**
     * 跳转到用户表单页面
     * @return
     */
    public abstract String form();


    /**
     * 保存用户信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);


    /**
     * 删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);


    /**
     * 分页查询
     * @param request
     * @param entity
     * @return
     */
    public abstract PageInfo<T> page(HttpServletRequest request, T entity);


    /**
     * 跳转详情页
     * @param
     * @return
     */
    public abstract String detail();

}
