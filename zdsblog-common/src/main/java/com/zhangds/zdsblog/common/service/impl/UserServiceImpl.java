package com.zhangds.zdsblog.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.zhangds.zdsblog.common.model.dto.UserQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Role;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.enums.LoginStatus;
import com.zhangds.zdsblog.common.repository.RoleRepository;
import com.zhangds.zdsblog.common.repository.UserRepository;
import com.zhangds.zdsblog.common.service.UserService;
import com.zhangds.zdsblog.common.utils.QueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
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
        user.setCreateTime(new Date());
        user.setStatus(LoginStatus.ENABLE.getCode());
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
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
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

        Page<User> userPage = userRepository.findAll((root, criteriaQuery, criteriaBuilder)
                        -> getPredicate(username, root, criteriaBuilder), pageable);
        return userPage;
    }

    @Override
    public Page<User> getPage(UserQueryCriteria queryCriteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, queryCriteria, criteriaBuilder), pageable);
        return page;
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
    public List<User> getAll() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Override
    public void updatePassword(String encode, String username) {
        User user = getUserByName(username);
        user.setPassword(encode);
        update(user);
    }

}
