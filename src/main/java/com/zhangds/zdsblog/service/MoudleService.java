package com.zhangds.zdsblog.service;

import com.zhangds.zdsblog.model.entity.Moudle;

/**
 * Create by zhangds
 * 2020-02-26 11:41
 **/
public interface MoudleService {

    Moudle save(Moudle moudle);

    Moudle update(Moudle moudle);

    Moudle getMoudleById(Long moudleId);
}
