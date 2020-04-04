package com.bjq.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 所有数据访问层的基类
 */
public interface BaseDao<T extends  BaseEntity> {

    /**
     * 查询全部信息
     *
     * @return
     */
    List<T> selectAll();


    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新
     * @param entity
     */
    void update(T entity);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     *
     * @param params 需要两个参数
     * @return
     */
    List<T> page(Map<String, Object> params);

    /**
     * 查询总比数
     */
    int count(T entity);


}
