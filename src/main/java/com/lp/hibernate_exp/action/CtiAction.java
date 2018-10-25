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


    @GetMapping("/findAll")
    public ServerResult queryAllCti(){
        return ServerResult.success("查询成功",ctiRepository.findAll());
    }


    @GetMapping("/findByName/{name}")
    public ServerResult queryByName(@PathVariable String name){
        return ServerResult.success("查询成功",ctiRepository.findByTroubleNameLike("%"+name+"%"));
    }

    @GetMapping("/findById/{id}")
    public ServerResult queryByName(@PathVariable Long id){
        return ServerResult.success("查询成功",ctiRepository.findById(id));
    }

    @GetMapping("/getChildById/{id}")
    public ServerResult findChildById(@PathVariable Long id){
        Optional<CtiBO> ctibo = ctiRepository.findById(id);
        if(ctibo.isPresent()){
            return ServerResult.success("查询成功",ctibo.get().getChildCtis());
        }else{
            return ServerResult.error(1001,"没有找到结果！");
        }
    }


}
