package com.lp.hibernate_exp.action;

import com.lp.hibernate_exp.base.util.ServerResult;
import com.lp.hibernate_exp.bo.CtiBO;
import com.lp.hibernate_exp.dao.CtiRepository;
import com.lp.hibernate_exp.service.CtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:02
 */
@RestController
@RequestMapping("/ctiApi")
public class CtiAction {

    @Autowired
    private CtiRepository ctiRepository;

    @Autowired
    private CtiService ctiService;



    @GetMapping("/getAll")
    public ServerResult queryAllCti(){
        return ServerResult.success("查询成功",ctiRepository.findAll());
    }


    @GetMapping("/getByName/{name}")
    public ServerResult queryByName(@PathVariable String name){
        return ServerResult.success("查询成功",ctiRepository.findByTroubleNameLike("%"+name+"%"));
    }

    @GetMapping("/getById/{id}")
    public ServerResult queryByName(@PathVariable Long id){
        return ServerResult.success("查询成功",ctiRepository.findById(id));
    }

    @GetMapping("/getChildById/{id}")
    public ServerResult findChildById(@PathVariable Long id){
        return ServerResult.success("查询成功",ctiRepository.findAllChilds(id));
    }

    @GetMapping("/getByGeogId/{geogId}")
    public ServerResult getCtisByGeogId(@PathVariable Long geogId){
        return ServerResult.success("查询成功",ctiRepository.findByGeogId(geogId));
    }


    @GetMapping("/getByGeogIdsAndFlowcode")
    public ServerResult getCtiByGeogIdsAndFlowcode(String flowcode, String geogids){
        return ServerResult.success("查询成功", ctiService.findByGeogIdAndFlowcode(geogids,flowcode));
    }
    @GetMapping("/getByIds")
    public ServerResult getByIds(String ids){
        return ServerResult.success("查询成功", ctiService.findByIds(ids));
    }

}
