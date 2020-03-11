package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

/**
 * Create by zhangds
 * 2020-03-04 12:17
 **/
public interface TaskService {

    @NonNull
    Page<Task> pageBy(@NonNull Pageable pageable);

}
