package com.zhangds.zdsblog.common.repository;

import com.zhangds.zdsblog.common.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Create by zhangds
 * 2020-03-04 12:51
 **/
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
}
