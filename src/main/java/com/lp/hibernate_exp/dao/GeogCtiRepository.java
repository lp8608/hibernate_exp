package com.lp.hibernate_exp.dao;

import com.lp.hibernate_exp.bo.GeogCtiBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:24
 */
@Repository
public interface GeogCtiRepository extends JpaRepository<GeogCtiBO,Long>{


    /**
     * 根据ctiId更新当前分类及其所有子项的sla数据
     * @param ctiId
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE tbl_itsm_sla_cti sc INNER JOIN (SELECT c1.id FROM tbl_itsm_system_ctiinfo c1  \n" +
                    "INNER JOIN tbl_itsm_system_ctiinfo c2 ON c1.classcode LIKE concat(c2.classcode,'%') WHERE c2.id =?1 \n" +
                    ")  AS cti ON sc.cti_id = cti.id SET sc.sla_id=?2")
    void updateSlaByCtiId(Long ctiId , Long slaId);

}
