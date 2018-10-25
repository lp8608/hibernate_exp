package com.lp.hibernate_exp.dao;

import com.lp.hibernate_exp.bo.GeogBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:24
 */
@Repository
public interface GeogRepository extends JpaRepository<GeogBO,Long> {
}
