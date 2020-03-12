package com.zhangds.zdsblog.controller.generator;

import com.zhangds.generator.domain.GenConfig;
import com.zhangds.generator.service.GenConfigService;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @date 2019-01-14
 */
@RestController
@RequestMapping("genConfig")
@Api(tags = "代码生成器配置管理")
public class GenConfigController {

    @Autowired
    private GenConfigService genConfigService;

    @ApiOperation("查询")
    @GetMapping(value = "/{tableName}")
    public BaseResponse get(@PathVariable String tableName){
        return BaseResponse.ok(genConfigService.find(tableName));
    }

    @ApiOperation("修改")
    @PostMapping("/emailConfig")
    public BaseResponse emailConfig(@Validated @RequestBody GenConfig genConfig){
        return  BaseResponse.ok(genConfigService.update(genConfig.getTableName(), genConfig));
    }
}
