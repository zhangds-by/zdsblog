package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.model.entity.Task;
import com.zhangds.zdsblog.common.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Create by zhangds
 * 2020-03-04 12:48
 **/
@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Page<Task> pageBy(Pageable pageable) {
        return null;
    }
}
