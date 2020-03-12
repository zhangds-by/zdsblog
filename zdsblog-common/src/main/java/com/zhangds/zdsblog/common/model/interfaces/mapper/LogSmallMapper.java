package com.zhangds.zdsblog.common.model.interfaces.mapper;

import com.zhangds.zdsblog.common.model.dto.LogSmallDTO;
import com.zhangds.zdsblog.common.model.entity.Log;
import com.zhangds.zdsblog.common.model.interfaces.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Author zhangds
 * @Date 2020/3/12 11:41
 * @Return
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends BaseMapper<LogSmallDTO, Log> {

}