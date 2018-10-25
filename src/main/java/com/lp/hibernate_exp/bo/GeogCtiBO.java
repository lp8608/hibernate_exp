package com.lp.hibernate_exp.bo;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 21:46
 */
@Entity
@Table(name = "TBL_ITSM_SLA_CTI")
@Data
public class GeogCtiBO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_ALIAS = "服务级别协议对应地理信息及CTI分类表";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_GEOG_ID = "地理信息ID";
    public static final String ALIAS_CTI_ID = "三级服务分类ID";
    public static final String ALIAS_SLA_ID = "服务级别ID";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Long id;
    @Column(name = "GEOG_ID")
    private Long geogId;
    @Column(name = "CTI_ID")
    private Long ctiId;
    @Column(name = "SLA_ID")
    private Long slaId;

    public GeogCtiBO(){}
    public GeogCtiBO(Long geogId,Long ctiId,Long slaId){
        this.geogId = geogId;
        this.ctiId = ctiId;
        this.slaId = slaId;
    }



    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "CTI_ID",insertable = false,updatable = false)
    private CtiBO ctiBO;







}
