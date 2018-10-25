package com.lp.hibernate_exp.dao;

import com.lp.hibernate_exp.bo.CtiBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:00
 */
@Repository
@Transactional
public interface CtiRepository extends JpaRepository<CtiBO,Long> {


    public List<CtiBO> findByTroubleNameLike(String troubleName);

}
