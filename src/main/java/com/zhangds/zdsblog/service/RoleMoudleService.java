package com.zhangds.zdsblog.service;

import com.zhangds.zdsblog.model.entity.Moudle;
import com.zhangds.zdsblog.model.entity.RoleMoudle;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-26 11:41
 **/
public interface RoleMoudleService {

    List<Moudle> getListByRoleId(Long roleId);

    RoleMoudle save(RoleMoudle roleMoudle);

    List<RoleMoudle> saveList(List<RoleMoudle> list);
}
