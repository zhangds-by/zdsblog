package com.zhangds.zdsblog.common.model.interfaces.mapper;

import com.zhangds.zdsblog.common.model.dto.LogErrorDTO;
import com.zhangds.zdsblog.common.model.entity.Log;
import com.zhangds.zdsblog.common.model.interfaces.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Zheng Jie
 * @date 2019-5-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends BaseMapper<LogErrorDTO, Log> {

}