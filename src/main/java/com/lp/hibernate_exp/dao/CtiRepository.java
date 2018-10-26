package com.lp.hibernate_exp.dao;

import com.lp.hibernate_exp.bo.CtiBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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
public interface CtiRepository extends JpaRepository<CtiBO,Long> ,JpaSpecificationExecutor<CtiBO> {


    List<CtiBO> findByTroubleNameLike(String troubleName);

    @Query(value = "select c1 from CtiBO c1 inner join CtiBO c2 on c1.classcode like concat(c2.classcode,'%') and c2.id= ?1")
    List<CtiBO> findAllChilds(long id);

    @Query("select c from CtiBO c join c.geogCtiBOS gc on gc.geogId=?1")
    List<CtiBO> findByGeogId(Long geogId);


}
