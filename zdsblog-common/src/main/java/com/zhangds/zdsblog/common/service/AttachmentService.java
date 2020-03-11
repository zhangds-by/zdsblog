package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Attachment;
import com.zhangds.zdsblog.common.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Create by zhangds
 * 2020-03-11 11:42
 **/
public interface AttachmentService extends BaseService<Attachment, Long> {

    Page<Attachment> getPage(BaseQueryCriteria queryCriteria, Pageable pageable);

    Attachment getById(Long id);

    Attachment upload(MultipartFile file, String username) throws IOException;
}
