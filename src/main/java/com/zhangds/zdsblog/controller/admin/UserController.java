package com.zhangds.zdsblog.controller.admin;

import com.zhangds.zdsblog.model.entity.User;
import com.zhangds.zdsblog.model.param.UserParam;
import com.zhangds.zdsblog.model.support.BaseResponse;
import com.zhangds.zdsblog.service.UserService;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-25 14:50
 **/
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @Author zhangds
     * @Date 2020/2/25 14:52
     * @Return
     */
    @PostMapping("/save")
    public BaseResponse<User> save(@RequestBody User user){
        User res = userService.save(user);
        return BaseResponse.ok(res);
    }

    @PostMapping("getPage")
    public BaseResponse getPage(@RequestBody UserParam param){
        Page<User> userPage = userService.getPage(param.getUsername(), param.getPageIndex(), param.getPageSize());
        List<User> list = userPage.getContent();
        return BaseResponse.ok(list);
    }
}
