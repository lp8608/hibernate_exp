package com.lp.hibernate_exp.service;

import com.lp.hibernate_exp.bo.CtiBO;

import java.util.List;

/**
 * @author LIPENGAK
 * @Description: Cti 业务接口
 * @date 2018-10-25 23:14
 */

public interface CtiService {


    List<CtiBO> findByGeogIdAndFlowcode(String geogIds, String flowcode);


    List<CtiBO> findByIds(String ids);
}
