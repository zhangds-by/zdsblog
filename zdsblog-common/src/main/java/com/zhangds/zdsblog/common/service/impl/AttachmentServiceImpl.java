package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.config.ServerConfig;
import com.zhangds.zdsblog.common.config.SystemConfig;
import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Attachment;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.repository.AttachmentRepository;
import com.zhangds.zdsblog.common.service.AttachmentService;
import com.zhangds.zdsblog.common.service.base.AbstractBaseService;
import com.zhangds.zdsblog.common.utils.FileUtil;
import com.zhangds.zdsblog.common.utils.QueryHelp;
import com.zhangds.zdsblog.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Create by zhangds
 * 2020-03-11 11:43
 **/
@Service
public class AttachmentServiceImpl extends AbstractBaseService<Attachment, Long> implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private ServerConfig serverConfig;

    @Override
    public Page<Attachment> getPage(BaseQueryCriteria queryCriteria, Pageable pageable) {
        Page<Attachment> page = attachmentRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> QueryHelp.getPredicate(root, queryCriteria, criteriaBuilder), pageable);
        return page;
    }

    @Override
    public Attachment getById(Long id) {
        return attachmentRepository.getOne(id);
    }

    @Override
    public Attachment upload(MultipartFile file, String username) throws IOException {
        // 上传文件路径
        String filePath = SystemConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = serverConfig.getUrl() + fileName;
        Attachment attachment = new Attachment();
        attachment.setName(fileName);
        attachment.setSize(file.getSize());
        attachment.setUrl(url);
        attachment.setUserName(username);
        attachment.setFileType(FileUtil.getExtensionName(file.getName()));
        return attachmentRepository.save(attachment);
    }
}
