package com.zhangds.zdsblog.common.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 16:20
 **/
@NoRepositoryBean
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID>, JpaSpecificationExecutor {


    List<DOMAIN> findAllByIdIn(Collection<ID> ids);


    void deleteByIdIn(Collection<ID> ids);
}
