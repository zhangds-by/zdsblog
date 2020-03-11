package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.dto.UserQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Role;
import com.zhangds.zdsblog.common.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-25 14:55
 **/
public interface UserService {

    User save(User user);

    User update(User user);

    Page<User> getPage(String username, int pageIndex, int pageSize);

    Page<User> getPage(UserQueryCriteria queryCriteria, Pageable pageable);

    User getUserByName(String username);

    List<Role> getRoleByUserId(Long userId);

    List<User> getAll();

    void updatePassword(String encode, String username);
}
