package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.model.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * Create by zhangds
 * 2020-03-11 17:48
 **/
@Data
public class MenuDTO extends Menu {

    List<MenuDTO> children;
}
