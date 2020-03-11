package com.zhangds.zdsblog.common.model.interfaces;

import java.io.Serializable;

/**
 * Create by zhangds
 * 2020-03-03 16:25
 **/
public interface GetId<K extends Serializable, T>{
    K getId(T t);
}
