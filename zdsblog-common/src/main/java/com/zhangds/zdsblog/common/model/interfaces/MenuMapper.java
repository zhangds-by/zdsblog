package com.zhangds.zdsblog.common.model.interfaces;

import com.zhangds.zdsblog.common.model.dto.MenuDTO;
import com.zhangds.zdsblog.common.model.entity.Menu;
import com.zhangds.zdsblog.common.model.interfaces.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Create by zhangds
 * 2020-03-11 18:32
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDTO, Menu> {
}
