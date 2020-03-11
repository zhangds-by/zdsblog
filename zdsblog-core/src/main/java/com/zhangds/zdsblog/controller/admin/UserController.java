package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.common.exception.file.FileLimitException;
import com.zhangds.zdsblog.common.model.dto.UserQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Attachment;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.AttachmentService;
import com.zhangds.zdsblog.common.service.UserService;
import com.zhangds.zdsblog.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Create by zhangds
 * 2020-02-25 14:50
 **/
@Slf4j
@RestController
@RequestMapping("user")
@Api(tags = "UserController", value = "用户信息管理")
public class UserController extends BaseController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @ApiOperation("新增单个用户")
    @PostMapping("/save")
    public BaseResponse<User> save(@RequestBody User user){
        User res = userService.save(user);
        return BaseResponse.ok(res);
    }

    @ApiOperation("修改用户")
    @PostMapping("/update")
    public BaseResponse<User> update(@RequestBody User user){
        User res = userService.update(user);
        return BaseResponse.ok(res);
    }


    /*@PostMapping("/getPage")
    public BaseResponse getPage(@RequestBody UserParam param){
        Page<User> userPage = userService.getPage(param.getUsername(), param.getPageIndex(), param.getPageSize());
        List<User> list = userPage.getContent();
        return BaseResponse.ok(list);
    }*/

    @ApiOperation("用户分页查询")
    @GetMapping("/getPage")
    public BaseResponse getPage(UserQueryCriteria queryCriteria, Pageable pageable){
        return BaseResponse.ok(userService.getPage(queryCriteria, pageable));
    }

    @ApiOperation("获取所有用户")
    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping("/getList")
    public BaseResponse<List> getList(){
        List<User> list = userService.getAll();
        return BaseResponse.ok(list);
    }

    @ApiOperation("修改用户密码")
    @PostMapping("/updatePassword")
    public BaseResponse updatePassword(String newPassword){
        User user = super.getCurrentUser();
        userService.updatePassword(passwordEncoder.encode(newPassword), user.getUsername());
        return BaseResponse.ok();
    }

    @ApiOperation("修改用户头像")
    @PostMapping("/changeIcon/{username}")
    public BaseResponse changeIcon(@RequestParam MultipartFile file, String username){
        //上传头像文件
        Attachment attachment = null;
        try {
            attachment = attachmentService.upload(file, username);
        } catch (IOException e) {
            log.error("头像上传失败" + e.getMessage());
            throw new FileLimitException("头像上传失败" + e.getMessage());
        }
        //修改用户信息
        User user = userService.getUserByName(username);
        user.setIcon(attachment.getName());
        User res = userService.update(user);
        return BaseResponse.ok(res);
    }

}
