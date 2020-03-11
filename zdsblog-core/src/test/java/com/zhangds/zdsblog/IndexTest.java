package com.zhangds.zdsblog;

import cn.hutool.core.lang.Assert;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Create by zhangds
 * 2020-03-06 14:00
 **/
@SpringBootTest
public class IndexTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserByName(){
        User user = userService.getUserByName("zhangds");
        System.out.println(user);
        Assert.isNull(user);
    }
}
