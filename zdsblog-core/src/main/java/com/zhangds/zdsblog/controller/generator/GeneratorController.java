package com.zhangds.zdsblog.controller.generator;

import com.zhangds.generator.domain.ColumnInfo;
import com.zhangds.generator.service.GenConfigService;
import com.zhangds.generator.service.GeneratorService;
import com.zhangds.zdsblog.common.exception.GlobalRequestException;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @date 2019-01-02
 */
@RestController
@RequestMapping("generator")
@Api(tags = "代码生成管理")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private GenConfigService genConfigService;

    @Value("${blog.generator.enabled}")
    private Boolean generatorEnabled;

    @ApiOperation("查询数据库数据")
    @GetMapping(value = "/tables")
    public BaseResponse getTables(){
        return BaseResponse.ok(generatorService.getTables());
    }

    @ApiOperation("分页查询数据库数据")
    @GetMapping(value = "/getTablePage")
    public BaseResponse getTablePage(@RequestParam(defaultValue = "") String name,
                                            @RequestParam(defaultValue = "0")Integer page,
                                            @RequestParam(defaultValue = "10")Integer size){
        int[] startEnd = PageUtil.transToStartEnd(page+1, size);
        return BaseResponse.ok(generatorService.getTables(name, startEnd));
    }

    @ApiOperation("查询字段数据")
    @GetMapping(value = "/columns")
    public BaseResponse getTables(@RequestParam String tableName){
        List<ColumnInfo> columnInfos = generatorService.getColumns(tableName);
        return BaseResponse.ok(PageUtil.toPage(columnInfos,columnInfos.size()));
    }

    @ApiOperation("保存字段数据")
    @PostMapping("/save")
    public BaseResponse save(@RequestBody List<ColumnInfo> columnInfos){
        generatorService.save(columnInfos);
        return BaseResponse.ok();
    }

    @ApiOperation("同步字段数据")
    @PostMapping(value = "sync")
    public BaseResponse sync(@RequestBody List<String> tables){
        for (String table : tables) {
            generatorService.sync(generatorService.getColumns(table), generatorService.query(table));
        }
        return BaseResponse.ok();
    }

    @ApiOperation("生成代码")
    @PostMapping(value = "/{tableName}/{type}")
    public BaseResponse generator(@PathVariable String tableName, @PathVariable Integer type, HttpServletRequest request, HttpServletResponse response){
        if(!generatorEnabled && type == 0){
            throw new GlobalRequestException("此环境不允许生成代码，请选择预览或者下载查看！");
        }
        switch (type){
            // 生成代码
            case 0: generatorService.generator(genConfigService.find(tableName), generatorService.getColumns(tableName));
                    break;
            // 预览
            case 1: return generatorService.preview(genConfigService.find(tableName), generatorService.getColumns(tableName));
            // 打包
            case 2: generatorService.download(genConfigService.find(tableName), generatorService.getColumns(tableName), request, response);
                    break;
            default: throw new GlobalRequestException("没有这个选项");
        }
        return BaseResponse.ok();
    }
}
