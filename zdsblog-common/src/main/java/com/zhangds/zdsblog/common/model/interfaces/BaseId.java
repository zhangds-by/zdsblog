package com.zhangds.zdsblog.common.model.interfaces;

import java.io.Serializable;

/**
 * Create by zhangds
 * 2020-03-03 16:23
 **/
public interface BaseId<T extends Serializable> extends Serializable{
    T getId();
}
