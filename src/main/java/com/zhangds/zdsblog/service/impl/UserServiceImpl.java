package com.zhangds.zdsblog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.zhangds.zdsblog.model.entity.Moudle;
import com.zhangds.zdsblog.model.entity.Role;
import com.zhangds.zdsblog.model.entity.User;
import com.zhangds.zdsblog.repository.RoleRepository;
import com.zhangds.zdsblog.repository.UserRepository;
import com.zhangds.zdsblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Create by zhangds
 * 2020-02-25 14:56
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 保存用户
     * @Author zhangds
     * @Date 2020/2/26 10:33
     * @Return
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    /**
     * 用户管理分页查询
     * @Author zhangds
     * @Date 2020/2/26 10:33
     * @Return
     */
    @Override
    public Page<User> getPage(String username, int pageIndex, int pageSize) {
        pageIndex = pageIndex < 0 ? 0 : pageIndex;
        pageSize = pageSize < 0 ? 10 : pageSize;
        /*Specification<User> query = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();

                if (StringUtils.isEmpty(username)) {
                    predicates.add(criteriaBuilder.equal(root.get("userName"), "%"+username+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        PageRequest page = PageRequest.of(pageIndex, pageSize);
        return userRepository.findAll(query, page);*/
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> userPage = userRepository.findAll((root, criteriaQuery, criteriaBuilder)
                        -> getPredicate(username, root, criteriaBuilder), pageable);
        return userPage;
    }

    private Predicate getPredicate(String name, Root<User> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> list = Lists.newArrayList();
        if (StrUtil.isNotEmpty(name)) {
            list.add(criteriaBuilder.like(root.get("userName").as(String.class), "%" + name + "%"));
        }
        /*if (StringUtils.isNotEmpty(appleName)) {
            Join<Apple, Person> join = root.join("apple", JoinType.LEFT);
            list.add(criteriaBuilder.like(join.get("name").as(String.class), "%" + appleName + "%"));
        }*/
        Predicate[] p = new Predicate[list.size()];
        return criteriaBuilder.and(list.toArray(p));
    }

    /**
     * 通过名称获取用户
     * @Author zhangds
     * @Date 2020/2/26 10:33
     * @Return
     */
    @Override
    public User getUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        List<Role> list = roleRepository.getRoleByUserId(userId);
        return list;

        /*
        Specification<Role> specification = new Specification<Role>(){
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                Join<User, UserRole> join1 = root.join("roles", JoinType.LEFT);
                if (StringUtils.isEmpty(userId)){
                    predicates.add(cb.equal(join1.get("userId"), userId));
                }
                Predicate[] p = new Predicate[predicates.size()];
                return cb.and(predicates.toArray(p));
            }
        };
        return roleRepository.findAll(specification);*/
    }

    @Override
    public List<Moudle> getMoudlesByUserId(Long userId) {
        return null;
    }


}
