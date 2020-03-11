package com.zhangds.zdsblog.common.service.base;


import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.repository.base.BaseRepository;
import com.zhangds.zdsblog.common.utils.QueryHelp;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Create by zhangds
 * 2020-03-02 16:15
 **/
@NoArgsConstructor
public abstract class AbstractBaseService<DOMAIN, ID> implements BaseService<DOMAIN, ID> {

    private BaseRepository<DOMAIN, ID> repository;

    AbstractBaseService(BaseRepository<DOMAIN, ID> repository){
        this.repository = repository;
    }

    @Override
    public List<DOMAIN> findAll() {
        return repository.findAll();
    }

    @Override
    public DOMAIN findById(ID id) {
        return repository.getOne(id);
    }

    @Override
    public DOMAIN save(DOMAIN domain) {
        return repository.save(domain);
    }

    @Override
    public List<DOMAIN> saveInBatch(Collection<DOMAIN> list) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : repository.saveAll(list);
    }

    @Override
    public DOMAIN update(DOMAIN domain) {
        return repository.saveAndFlush(domain);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    @Override
    public List<DOMAIN> findAllByIds(Collection<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllByIdIn(ids);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<DOMAIN> updateInBatch(Collection<DOMAIN> list) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : repository.saveAll(list);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteInBatch(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)){
            return;
        }
        repository.deleteByIdIn(ids);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Page<DOMAIN> getPage(BaseQueryCriteria queryCriteria, Pageable pageable) {
        Assert.notNull(pageable, "分页条件不能为空");
        Page<DOMAIN> page = repository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, queryCriteria, criteriaBuilder), pageable);
        return page;
    }

    @Override
    public Page<DOMAIN> getPage(Pageable pageable) {
        Assert.notNull(pageable, "分页条件不能为空");
        return repository.findAll(pageable);
    }
}
