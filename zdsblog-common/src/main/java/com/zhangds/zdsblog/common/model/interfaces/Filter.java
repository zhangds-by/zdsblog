package com.zhangds.zdsblog.common.model.interfaces;

/**
 * Create by zhangds
 * 2020-03-03 16:30
 **/
@FunctionalInterface
public interface Filter<T>{

    public boolean accept(T record);

}
