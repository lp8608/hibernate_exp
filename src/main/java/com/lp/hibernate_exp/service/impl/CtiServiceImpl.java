package com.lp.hibernate_exp.service.impl;

import com.lp.hibernate_exp.base.util.FlowCodeStatics;
import com.lp.hibernate_exp.bo.CtiBO;
import com.lp.hibernate_exp.bo.GeogCtiBO;
import com.lp.hibernate_exp.dao.CtiRepository;
import com.lp.hibernate_exp.service.CtiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-25 23:15
 */
@Service
public class CtiServiceImpl implements CtiService {

    @Autowired
    private CtiRepository ctiRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<CtiBO> findByGeogIdAndFlowcode(String geogIds, String flowcode) {
        return ctiRepository.findAll(new Specification<CtiBO>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<CtiBO> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                Predicate p1 = null;
                if (StringUtils.isNotBlank(geogIds)) {
                    Join<CtiBO, GeogCtiBO> join = root.join("geogCtiBOS", JoinType.INNER);
                    Path<Long> geogId = join.get("geogId");
                    CriteriaBuilder.In<Long> in = cb.in(geogId);
                    for (String id : geogIds.split(",")) {
                        in.value(Long.valueOf(id));
                    }
                    list.add(in);
                }
                Predicate p2 = null;
                if (StringUtils.isNotBlank(flowcode)) {
                    if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_INCIDENT)) {
                        p2 = cb.equal(root.get("incflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_PROBLEM)) {
                        p2 = cb.equal(root.get("pmflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_CHANGE)) {
                        p2 = cb.equal(root.get("chgflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_CONFIGURE)) {
                        p2 = cb.equal(root.get("cmflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_CAPITAL)) {
                        p2 = cb.equal(root.get("amflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_KNOWLEDGE)) {
                        p2 = cb.equal(root.get("kmflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_TASK)) {
                        p2 = cb.equal(root.get("taskflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_RELEASE)) {
                        p2 = cb.equal(root.get("rlsflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_SERVERREQ)) {
                        p2 = cb.equal(root.get("reqflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_PLANTASK)) {
                        p2 = cb.equal(root.get("jobflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_AFFAIRS)) {
                        p2 = cb.equal(root.get("affflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_MATERIAL)) {
                        p2 = cb.equal(root.get("materialflag"), 1);
                    } else if (flowcode.equals(FlowCodeStatics.ITSM_WORK_FLOW_DEVICE)) {
                        p2 = cb.equal(root.get("deviceflag"), 1);
                    }
                    list.add(p2);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        });

    }

    @Override
    public List<CtiBO> findByIds(String ids) {
        if (StringUtils.isNotBlank(ids)) {

            return ctiRepository.findAll(new Specification<CtiBO>() {
                @Nullable
                @Override
                public Predicate toPredicate(Root<CtiBO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("id"));
                    for (String id : ids.split(",")) {
                        in.value(Long.valueOf(id));
                    }
                    return criteriaBuilder.and(in);
                }
            });
        }
        return null;
    }

    @Override
    public List<CtiBO> findByflowCodes(String flowCodes) {
        List<CtiBO> list = new ArrayList<>();
        if(StringUtils.isNotBlank(flowCodes)){
            StringBuilder sql = new StringBuilder("select c from CtiBO c where 1=1 ");
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_INCIDENT) != -1) {
                sql.append(" or incflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_PROBLEM) != -1) {
                sql.append(" or pmflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_CHANGE) != -1) {
                sql.append(" or chgflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_CONFIGURE) != -1) {
                sql.append(" or cmflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_CAPITAL) != -1) {
                sql.append(" or amflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_KNOWLEDGE) != -1) {
                sql.append(" or kmflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_TASK) != -1) {
                sql.append(" or taskflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_RELEASE) != -1) {
                sql.append(" or rlsflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_SERVERREQ) != -1) {
                sql.append(" or reqflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_PLANTASK) != -1) {
                sql.append(" or jobflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_AFFAIRS) != -1) {
                sql.append(" or affflag=1");
            }

            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_MATERIAL) != -1) {
                sql.append(" or materialflag=1");
            }
            if (StringUtils.indexOfIgnoreCase(flowCodes,FlowCodeStatics.ITSM_WORK_FLOW_DEVICE) != -1) {
                sql.append(" or deviceflag=1");
            }
            list = (List<CtiBO>) entityManager.createQuery(sql.toString()).getResultList();
        }
        return list;
    }

    @Override
    public Page<CtiBO> findPage(CtiBO ctiBO, Pageable pageable) {
        return this.ctiRepository.findAll(new Specification<CtiBO>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<CtiBO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(StringUtils.isNotBlank(ctiBO.getTroubleName())){
                    return criteriaBuilder.and(criteriaBuilder.like(root.get("troubleName").as(String.class),ctiBO.getTroubleName()+"%"));
                }
                return null;
            }
        }, pageable);
    }


}
