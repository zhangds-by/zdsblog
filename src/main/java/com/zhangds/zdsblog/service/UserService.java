package com.zhangds.zdsblog.service;

import com.zhangds.zdsblog.model.entity.Moudle;
import com.zhangds.zdsblog.model.entity.Role;
import com.zhangds.zdsblog.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-25 14:55
 **/
public interface UserService {

    User save(User user);

    User update(User user);

    Page<User> getPage(String username, int pageIndex, int pageSize);

    User getUserByName(String username);

    List<Role> getRoleByUserId(Long userId);

    List<Moudle> getMoudlesByUserId(Long userId);
}
