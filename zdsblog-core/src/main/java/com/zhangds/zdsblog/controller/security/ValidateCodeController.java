package com.zhangds.zdsblog.controller.security;

import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.security.code.ValidateCodeGenerator;
import com.zhangds.zdsblog.security.code.img.ImageCode;
import com.zhangds.zdsblog.security.code.img.ImageCodeGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

/**
 * Create by zhangds
 * 2020-03-06 14:38
 **/
@RestController
@Api(tags = "验证码接口")
@RequestMapping("validateCode")
public class ValidateCodeController {

    @Autowired
    private ValidateCodeGenerator codeGenerator;

    @ApiOperation("获取校验码")
    @GetMapping("/image/code")
    public BaseResponse getImageCode(){
        ImageCode imageCode = (ImageCode) codeGenerator.createCode();
        BufferedImage image = imageCode.getImage();
        return BaseResponse.ok(image);
    }
}
