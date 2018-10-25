package com.lp.hibernate_exp.service;

import com.lp.hibernate_exp.bo.CtiBO;

import java.util.List;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-25 09:29
 */
public interface CtiService {

    /**
     * 按照名称模糊查询三级分类
     * @param name
     * @return
     */
    public List<CtiBO> findByTroubleNameLike(String name);

    /**
     * 根据id查询子分类
     * @param id
     * @return
     */
    public List<CtiBO> findChildByParentId(Long id);
}
