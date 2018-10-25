package com.lp.hibernate_exp.bo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 21:45
 */
@Entity
@Table(name = "TBL_ITSM_GEOG_INFO")
@Data
public class GeogBO implements Serializable {


    private static final long serialVersionUID = 1L;
    public static final String TABLE_ALIAS = "ITSM地理信息维护";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_GEOG_NAME = "地理名称";
    public static final String ALIAS_GEOG_DESC = "备注";
    public static final String ALIAS_EDIT_STATUS = "editStatus";
    public static final String ALIAS_GEOG_CODE = "地理代码";
    public static final String ALIAS_PARENT_ID = "父类ID";
    public static final String ALIAS_GEOG_LEVEL = "层级.0表示跟节点；1表示第一级节点，2：表示第二级节点一次类推";
    public static final String ALIAS_DELFLAG = "删除标识：1：表示删除；0 ：表示有效";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Long id;
    @Column(name = "GEOG_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
    private java.lang.String geogName;
    @Column(name = "GEOG_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
    private java.lang.String geogDesc;

    @Column(name = "CREATE_TIME")
    private Date createTime;//创建时间
    @Column(name = "UPDATE_TIME")
    private Date updateTime;//更新时间
    @Column(name = "UPDATOR")
    private Long updator;//更新人
    @Column(name = "CREATOR")
    private Long creator;//创建人
    //创建人
    @Column(name = "SHORT_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private java.lang.String shortName;
    @Column(name = "EDIT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long editStatus = 0L;
    @Column(name = "GEOG_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 254)
    private java.lang.String geogCode;
    //移动端新增简称
    @Column(name = "ABBREVIATION", unique = false, nullable = true, insertable = true, updatable = true, length = 254)
    private java.lang.String abbreviation;
    //新增saasflag字段，enterpriseNo字段
    @Column(name = "SAASFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long saasFlag;
    @Column(name = "ENTERPRISE_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
    private String enterpriseNo;



    @Column(name = "PARENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long parentId;

    @Column(name = "GEOG_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long geogLevel;

    @Column(name = "DELFLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Long delflag = 0L;



    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID", nullable = false, insertable = false, updatable = false)
    })
    private GeogBO parentGeog;

    @OneToMany(mappedBy = "parentGeog")
    private Set<GeogBO> childGeogs = new HashSet<>();




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("GeogName", getGeogName())
                .append("GeogDesc", getGeogDesc())
                .append("EditStatus", getEditStatus())
                .append("GeogCode", getGeogCode())
                .append("ParentId", getParentId())
                .append("GeogLevel", getGeogLevel())
                .append("Delflag", getDelflag())
                .append("CREATE_TIME", getCreateTime())
                .append("UPDATE_TIME", getUpdateTime())
                .append("CREATOR", getCreator())
                .append("UPDATOR", getUpdator())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        GeogBO geogBO = (GeogBO) o;

        if (!id.equals(geogBO.id)){
            return false;
        }
        return editStatus != null ? editStatus.equals(geogBO.editStatus) : geogBO.editStatus == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (editStatus != null ? editStatus.hashCode() : 0);
        return result;
    }
}
