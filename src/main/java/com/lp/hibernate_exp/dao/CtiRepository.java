package com.lp.hibernate_exp.dao;

import com.lp.hibernate_exp.bo.CtiBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:00
 */
@Repository
public interface CtiRepository extends JpaRepository<CtiBO,Long> ,JpaSpecificationExecutor<CtiBO> {


    List<CtiBO> findByTroubleNameLike(String troubleName);

    @Query(value = "select c1 from CtiBO c1 inner join CtiBO c2 on c1.classcode like concat(c2.classcode,'%') and c2.id= ?1")
    List<CtiBO> findAllChilds(long id);

    /**
     * 根据geogId查询地理信息所属cti list
     * @param geogId
     * @return
     */
    @Query("select c from CtiBO c join c.geogCtiBOS gc on gc.geogId=?1")
    List<CtiBO> findByGeogId(Long geogId);

    /**
     * 如果是修改cti的flowcode，连带修改所有子类及父类的flowcode
     */
    @Modifying
    @Transactional
    //@Query( nativeQuery = true,
    //        value = "update tbl_itsm_system_ctiinfo c1 inner join (select id,classcode from tbl_itsm_system_ctiinfo where id=#{#ctiBO.id} ) as c2 on c1.classcode like concat(c2.classcode,'%')  \n" +
    //                " set c1.incflag=#{#ctiBO.incflag},c1.pmflag=#{#ctiBO.pmflag},c1.chgflag=#{#ctiBO.chgflag},c1.rlsflag=#{#ctiBO.rlsflag}, \n" +
    //                " c1.taskflag=#{#ctiBO.taskflag},c1.kmflag=#{#ctiBO.kmflag},c1.cmflag=#{#ctiBO.cmflag}, \n" +
    //                " c1.amflag=#{#ctiBO.amflag},c1.reqflag=#{#ctiBO.reqflag} "
    //)
    @Query(nativeQuery = true,
    value = "update tbl_itsm_system_ctiinfo c1  set c1.incflag=:#{#ctiBO.incflag},c1.pmflag=:#{#ctiBO.pmflag},c1.chgflag=:#{#ctiBO.chgflag},c1.rlsflag=:#{#ctiBO.rlsflag}, \n" +
            " c1.taskflag=:#{#ctiBO.taskflag},c1.kmflag=:#{#ctiBO.kmflag},c1.cmflag=:#{#ctiBO.cmflag}, \n" +
            " c1.amflag=:#{#ctiBO.amflag},c1.reqflag=:#{#ctiBO.reqflag} where id in(:ids) ")
    void updateFlowCode(@Param("ctiBO")CtiBO ctiBO,@Param("ids") List<Long> ids);


    /**
     * 利用原生sql + 分页参数
     * @param troubleName
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,
    value = "select * from Tbl_itsm_system_ctiinfo where trouble_name like ?1",
    countQuery = "select count(*) from Tbl_itsm_system_ctiinfo where trouble_name like ?1")
    Page<CtiBO> findByName(String troubleName,Pageable pageable);

    /**
     * 利用jpa自己的分页接口，可以简单分页
     * @param troubleName
     * @param pageable
     * @return
     */
    List<CtiBO> findByTroubleNameLike(String troubleName, Pageable pageable);

    /**
     * 根据hql + 分页参数 自定义分页额查询 适合固定条件的分页查询
     * 返回结果可以自定义整个实体(Page<User>)，也可以查询某几个字段（Page<Object[]>），和原生sql几乎一样灵活。
     * @param troubleName
     * @param pageable
     * @return
     */
    @Query(value = "select c from CtiBO c where c.troubleName like ?1 order by c.id desc")
    Page<CtiBO> findPageByTroubleName(String troubleName, Pageable pageable);

    /**
     * 动态条件的分页查询，在serviceImpl中实现
     * @param spec
     * @param pageable
     * @return
     */
    @Override
    Page<CtiBO> findAll(Specification<CtiBO> spec, Pageable pageable);
}
