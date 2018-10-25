package com.lp.hibernate_exp.bo;

import com.alibaba.fastjson.annotation.JSONField;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 21:45
 */
@Entity
@Table(name = "TBL_ITSM_SYSTEM_CTIINFO")
@Data
public class CtiBO implements Serializable{


    private static final long serialVersionUID = 1L;
    public static final String TABLE_ALIAS = "系统分类信息，包括类别、子类和条目等";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 22)
    private Long id;

    @Column(name = "TROUBLE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
    private java.lang.String troubleCode;

    @Column(name = "TROUBLE_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
    private java.lang.String troubleName;

    @Column(name = "PARENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long parentId;

    @Column(name = "CLASSCODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
    private java.lang.String classcode;

    @Column(name = "INCFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long incflag = 0L;

    @Column(name = "PMFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long pmflag  = 0L;
    @Column(name = "CHGFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long chgflag  = 0L;
    @Column(name = "RLSFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long rlsflag  = 0L;
    @Column(name = "TASKFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long taskflag  = 0L;
    @Column(name = "KMFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long kmflag  = 0L;
    @Column(name = "CMFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long cmflag  = 0L;
    @Column(name = "AMFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long amflag  = 0L;
    @Column(name = "REQFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long reqflag  = 0L;
    @Column(name = "JOBFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long jobflag  = 0L;
    @Column(name = "RMFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long rmflag = 0L;
    @Column(name = "AFFFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long affflag = 0L;
    @Column(name = "MATERIALFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long materialflag = 0L;
    @Column(name = "DEVICEFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long deviceflag = 0L;
    @Column(name = "LICFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long licflag = 0L;
    @Column(name = "F1GLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long f1flag = 0L;
    @Column(name = "F2FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long f2flag = 0L;
    @Column(name = "F3FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long f3flag = 0L;
    @Column(name = "F4FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long f4flag = 0L;

    @Column(name = "DEFAULT_RANGE",unique = false,nullable = true,insertable = true,updatable = true,length = 7)
    private Long defaultrange;//默认影响度

    @Column(name = "DEFAULT_DEGREE",unique = false,nullable = true,insertable = true,updatable = true,length = 7)
    private Long defaultdegree;//默认紧急度

    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
    private String remark;
    @Column(name = "CREATDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creatdate;
    @Column(name = "CREATEUSER", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
    private String createuser;

    @Column(name = "LASTMODIFYDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastmodifydate;

    @Column(name = "LASTMODEFYUSER", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
    private String lastmodefyuser;
    @Column(name = "DELFLAG", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Long delflag = 0L;
    //科技服务标记
    @Column(name = "SCISERFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long  sciSerflag;
    @Column(name = "CMADMINID", unique = false, nullable = true, insertable = true, updatable = true)
    private Long cmadminid;
    @Column(name = "AMADMINID", unique = false, nullable = true, insertable = true, updatable = true)
    private Long amadminid;



    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID",nullable = false, insertable = false, updatable = false)
    })
    @JSONField(serialize = false)
    private CtiBO parentCti;


    @OneToMany(mappedBy = "parentCti", fetch = FetchType.LAZY)
    @JSONField(serialize = false)
    private List<CtiBO> childCtis = new ArrayList<>();


    @OneToMany(cascade={},fetch=FetchType.LAZY,mappedBy = "ctiBO")
    @JSONField(serialize = false)
    private List<GeogCtiBO> geogCtiBOS;



    public CtiBO(){
    }

    public CtiBO(CtiBO parentCti){
        this.parentCti = parentCti;
    }





}
