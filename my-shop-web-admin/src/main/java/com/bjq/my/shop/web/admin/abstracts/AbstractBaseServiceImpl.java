package com.bjq.my.shop.web.admin.abstracts;

import com.bjq.my.shop.commons.dto.PageInfo;
import com.bjq.my.shop.commons.persistence.BaseDao;
import com.bjq.my.shop.commons.persistence.BaseEntity;
import com.bjq.my.shop.commons.persistence.BaseService;
import com.bjq.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部信息
     *
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }


    /**
     * 删除用户
     * @param id
     */
    @Override
    public void delete(Long id){
        dao.delete(id);
    }


    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id){
        return dao.getById(id);
    }


    /**
     * 更新用户信息
     * @param entity
     */
    @Override
    public void update(T entity){
        dao.update(entity);
    }



    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }


    /**
     * 查询总比数
     * @param entity
     * @return
     */
    @Override
    public int count(T entity){
        return dao.count(entity);
    }



    /**
     * 分页查询
     * @param
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);

        params.put("pageParams",entity);

        PageInfo<T> pageInfo =new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));
        return pageInfo;

    }
}
