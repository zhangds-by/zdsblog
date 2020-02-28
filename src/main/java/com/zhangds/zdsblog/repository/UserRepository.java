package com.zhangds.zdsblog.repository;

import com.zhangds.zdsblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Create by zhangds
 * 2020-02-17 18:37
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor {

    //@Query(value = "SELECT u FROM User u WHERE userName=:username")
    @Query(nativeQuery = true, value = "select u.* from sys_user u where user_name = ?")
    public User findUserByName(@Param("username") String username);

}
