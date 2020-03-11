package com.zhangds.zdsblog.common.repository;

import com.zhangds.zdsblog.common.model.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Create by zhangds
 * 2020-03-11 11:44
 **/
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long>, JpaSpecificationExecutor {
}
