package com.lp.hibernate_exp.action;

import com.lp.hibernate_exp.base.util.ServerResult;
import com.lp.hibernate_exp.dao.GeogCtiRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-10-27 16:54
 */
@Api(description = "sla数据API")
@RestController
@RequestMapping("/geogCtiApi")
public class GeogCtiController {

    @Autowired
    private GeogCtiRepository geogCtiRepository;




    @ApiOperation(value = "根据cti更新sla数据（包含所有子项）",notes = "主要测试内连接更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ctiId",value = "三级分类ID",dataType = "int",paramType = "form"),
            @ApiImplicitParam(name = "slaId",value = "SLA ID",dataType = "int",paramType = "form")

    })
    @PostMapping("/updateSlaByCtiId")
    public ServerResult updateSlaByCtiId(Long ctiId,Long slaId){
        geogCtiRepository.updateSlaByCtiId(ctiId,slaId);;
        return  ServerResult.success("更新成功","");
    }

}
