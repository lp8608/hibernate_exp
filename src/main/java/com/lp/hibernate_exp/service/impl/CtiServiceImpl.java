package com.lp.hibernate_exp.service.impl;

import com.lp.hibernate_exp.bo.CtiBO;
import com.lp.hibernate_exp.dao.CtiRepository;
import com.lp.hibernate_exp.service.CtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-25 09:31
 */
@Service
public class CtiServiceImpl implements CtiService {

    @Autowired
    private CtiRepository ctiRepository;

    /**
     * 按照名称模糊查询三级分类
     *
     * @param name
     * @return
     */
    @Override
    public List<CtiBO> findByTroubleNameLike(String name) {
        String troubleName = "%" + name + "%";
        return ctiRepository.findByTroubleNameLike(troubleName);
    }

    /**
     * 根据id查询子分类
     *
     * @param id
     * @return
     */
    @Override
    public List<CtiBO> findChildByParentId(Long id) {
        Optional<CtiBO> optional = ctiRepository.findById(id);
        if(optional.isPresent()){
            CtiBO ctiBO = optional.get();
            return ctiBO.getChildCtis();
        }
        return null;
    }

}
