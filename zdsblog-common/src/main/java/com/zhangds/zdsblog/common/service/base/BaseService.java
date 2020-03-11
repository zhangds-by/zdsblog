package com.zhangds.zdsblog.common.service.base;

import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 15:10
 **/
public interface BaseService<DOMAIN, ID> {

    @NonNull
    List<DOMAIN> findAll();

    @NonNull
    List<DOMAIN> findAllByIds(@Nullable Collection<ID> ids);

    @NonNull
    DOMAIN findById(@NonNull ID id);

    long count();

    @NonNull
    @Transactional
    DOMAIN save(@NonNull DOMAIN domain);

    @NonNull
    @Transactional
    List<DOMAIN> saveInBatch(Collection<DOMAIN> list);

    @NonNull
    @Transactional
    DOMAIN update(@NonNull DOMAIN domain);

    @NonNull
    @Transactional
    List<DOMAIN> updateInBatch(Collection<DOMAIN> list);

    @NonNull
    @Transactional
    void deleteById(@NonNull ID id);

    @Transactional
    void deleteInBatch(@NonNull Collection<ID> ids);

    @Transactional
    void deleteAll();

    void flush();

    @NonNull
    Page<DOMAIN> getPage(BaseQueryCriteria queryCriteria, @NonNull Pageable pageable);

    @NonNull
    Page<DOMAIN> getPage(@NonNull Pageable pageable);
}
