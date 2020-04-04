package com.bjq.my.shop.commons.persistence;

import com.bjq.my.shop.commons.dto.BaseResult;
import com.bjq.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * 所有业务逻辑层的基类
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 查询全部
     * @return
     */
    public List<T> selectAll();


    /**
     * 保存
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    T getById(Long id);


    /**
     * 更新用户信息
     * @param entity
     */
    void update(T entity);


    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总比数
     * @param entity
     * @return
     */
    int count(T entity);

}

