package com.lp.hibernate_exp.action;

import com.lp.hibernate_exp.base.util.ServerResult;
import com.lp.hibernate_exp.bo.CtiBO;
import com.lp.hibernate_exp.dao.CtiRepository;
import com.lp.hibernate_exp.service.CtiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-24 22:02
 */
@Api(description = "三级分类接口")
@RestController
@RequestMapping("/ctiApi")
public class CtiController {

    @Autowired
    private CtiRepository ctiRepository;

    @Autowired
    private CtiService ctiService;


    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    @GetMapping("/getAll")
    public ServerResult queryAllCti(){
        return ServerResult.success("查询成功",ctiRepository.findAll());
    }


    @ApiOperation(value = "按名称模糊匹配",notes = "按名称模糊匹配")
    @GetMapping("/getByName/{name}")
    public ServerResult queryByName(@PathVariable String name){
        return ServerResult.success("查询成功",ctiRepository.findByTroubleNameLike("%"+name+"%"));
    }

    @ApiOperation(value = "根据ID查询单个cti" , notes = "根据id获取单个cti详情")
    @GetMapping("/getById/{id}")
    public ServerResult queryByName(@PathVariable Long id){
        return ServerResult.success("查询成功",ctiRepository.findById(id));
    }

    @ApiOperation(value = "根据id查询cti及其所有下属cti" ,notes = "主要测试@Query中的内连接查询")
    @ApiImplicitParam(name = "id" , value = "分类ID",dataType = "int",paramType = "path")
    @GetMapping("/getChildById/{id}")
    public ServerResult findChildById(@PathVariable Long id){
        return ServerResult.success("查询成功",ctiRepository.findAllChilds(id));
    }

    @ApiOperation(value = "根据地理信息查询cti列表" ,notes = "主要测试关联查询")
    @ApiImplicitParam(name = "geogId" , value = "地理信息ID",dataType = "int",paramType = "path")
    @GetMapping("/getByGeogId/{geogId}")
    public ServerResult getCtisByGeogId(@PathVariable Long geogId){
        return ServerResult.success("查询成功",ctiRepository.findByGeogId(geogId));
    }

    @ApiOperation(value = "根据地理信息IDS和flowcode查询cti列表" ,notes = "主要测试cb里面构建连接查询、in查询、动态条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flowcode" , value = "流程编码",dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "geogIds" , value = "多个地理信息ID，以逗号拼接",dataType = "String",paramType = "body")
    })
    @GetMapping("/getByGeogIdsAndFlowcode")
    public ServerResult getCtiByGeogIdsAndFlowcode(String flowcode, String geogIds){
        return ServerResult.success("查询成功", ctiService.findByGeogIdAndFlowcode(geogIds,flowcode));
    }


    @ApiOperation(value = "根据IDS查询cti列表" ,notes = "主要测试CriteriaBuilder构建in查询")
    @ApiImplicitParam(name = "ids" , value = "多个CTI的ID，以逗号拼接",dataType = "String",paramType = "query")
    @GetMapping("/getByIds")
    public ServerResult getByIds(String ids){
        return ServerResult.success("查询成功", ctiService.findByIds(ids));
    }


    @ApiOperation(value = "根据ID删除cti" ,notes = "主要测试级联删除，删除一级分类后，级联删除所有子分类和地理信息关联数据")
    @ApiImplicitParam(name = "geogId" , value = "多个CTI的ID，以逗号拼接",dataType = "String",paramType = "path")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public ServerResult deleteById(@PathVariable Long id){
        ctiRepository.deleteById(id);
        return ServerResult.success("删除成功","");
    }

    @ApiOperation(value = "根据ID更新cti及所有子类的flowcode" ,notes = "主要测试@Query部分字段的更新操作，另外还测试快速修改所有子类的部分属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" , value = "CTI的ID",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "incflag" , value = "事件流程",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "pmflag" , value = "问题流程",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "taskflag" , value = "",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "chgflag" , value = "",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "rlsflag" , value = "",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "kmflag" , value = "",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "cmflag" , value = "",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "amflag" , value = "",dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "reqflag" , value = "",dataType = "String",paramType = "form"),
    })
    @PostMapping("/update")
    public ServerResult update(CtiBO ctiBO){
        List<CtiBO> list = ctiRepository.findAllChilds(ctiBO.getId());
        List<Long> ids = list.stream().map(CtiBO::getId).collect(Collectors.toList());
        ctiRepository.updateFlowCode(ctiBO,ids);
        return ServerResult.success("更新成功", "");
    }


    @ApiOperation(value = "根据flowCodes查询cti列表" ,notes = "主要测试EntiryManager.createQuery创建动态查询")
    @ApiImplicitParam(name = "flowCodes" , value = "多个flowCode，以逗号拼接",dataType = "String",paramType = "query")
    @RequestMapping(value = "/findByFlowCodes" , method = RequestMethod.GET)
    public ServerResult findByFlowCodes(String flowCodes){
        return ServerResult.success("查询成功",ctiService.findByflowCodes(flowCodes));
    }

    @ApiOperation(value = "根据troubleName查询分页的cti列表" ,notes = "测试分页查询:利用jpa自己的分页接口，可以简单分页")
    @ApiImplicitParam(name = "troubleName" , value = "名称",dataType = "String",paramType = "query")
    @RequestMapping(value = "/findPageByTroubleName" , method = RequestMethod.GET)
    public ServerResult findPageByTroubleName(String troubleName){
        return ServerResult.success("查询成功",ctiRepository.findByTroubleNameLike("%"+troubleName+"%",new PageRequest(0,10)));
    }


    @ApiOperation(value = "根据troubleName查询分页的cti列表" ,notes = "测试分页查询:利用原生sql + 分页参数 ")
    @ApiImplicitParam(name = "troubleName" , value = "名称",dataType = "String",paramType = "query")
    @RequestMapping(value = "/findPageByNameBySql" , method = RequestMethod.GET)
    public ServerResult findPageByName(String troubleName){
        return ServerResult.success("查询成功",ctiRepository.findByName("%"+troubleName+"%",new PageRequest(0,10)));
    }

    @ApiOperation(value = "根据troubleName查询分页的cti列表" ,notes = "测试分页查询:根据hql + 分页参数 自定义分页查询 适合固定条件的分页查询 ")
    @ApiImplicitParam(name = "troubleName" , value = "名称",dataType = "String",paramType = "query")
    @RequestMapping(value = "/findPageByNameByHql" , method = RequestMethod.GET)
    public ServerResult findPageByNameByHql(String troubleName){
        return ServerResult.success("查询成功",ctiRepository.findPageByTroubleName("%"+troubleName+"%",new PageRequest(0,10)));
    }


    @ApiOperation(value = "根据troubleName查询分页的cti列表" ,notes = "测试分页查询:根据Specfication分页查询,适合动态条件的分页查询 ")
    @ApiImplicitParam(name = "ctiBO" , value = "cto",dataType = "CtiBO",paramType = "query")
    @RequestMapping(value = "/findPageByNameBySpec" , method = RequestMethod.GET)
    public ServerResult findPageByNameBySpec(CtiBO ctiBO){
        return ServerResult.success("查询成功",ctiService.findPage(ctiBO,new PageRequest(0,10)));
    }
}
