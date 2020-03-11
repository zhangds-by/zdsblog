package com.zhangds.zdsblog.controller.admin;

import com.google.common.collect.Lists;
import com.zhangds.zdsblog.common.config.SystemConfig;
import com.zhangds.zdsblog.common.exception.file.FileLimitException;
import com.zhangds.zdsblog.common.model.dto.BaseQueryCriteria;
import com.zhangds.zdsblog.common.model.entity.Attachment;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.service.AttachmentService;
import com.zhangds.zdsblog.common.utils.file.FileUtils;
import com.zhangds.zdsblog.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Create by zhangds
 * 2020-03-11 11:24
 **/
@Slf4j
@RestController
@Api(tags = "附件管理")
@RequestMapping("attachment")
public class AttachmentController extends BaseController {

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation("分页获取附件信息")
    @GetMapping("/getPage")
    public BaseResponse getPage(BaseQueryCriteria queryCriteria, Pageable pageable){
        Page<Attachment> page = attachmentService.getPage(queryCriteria, pageable);
        return BaseResponse.ok(page);
    }

    @ApiOperation("根据id查询附件")
    @GetMapping("/getById/{id}")
    public BaseResponse getById(@PathVariable Long id){
        Attachment attachment = attachmentService.getById(id);
        return BaseResponse.ok(attachment);
    }

    @ApiOperation("上传附件")
    @PostMapping("/uploads")
    public BaseResponse uploads(@RequestBody MultipartFile[] files) {
        User user = super.getCurrentUser();
        List<Attachment> list = Lists.newArrayList();
        for (MultipartFile file : files) {
            Attachment attachment = null;
            try {
                attachment = attachmentService.upload(file, user.getUsername());
            } catch (IOException e) {
                log.error("附件上传失败" + e.getMessage());
                throw new FileLimitException("附件上传失败" + e.getMessage());
            }
            list.add(attachment);
        }
        return BaseResponse.ok(list);
    }

    @ApiOperation("下载附件")
    @PostMapping("/download/{id:\\d+}")
    public BaseResponse uploads(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request){
        Attachment attachment = attachmentService.getById(id);
        String fileName = attachment.getName();
        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
        String filePath = SystemConfig.getDownloadPath() + fileName;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (IOException e) {
            log.error("文件下载失败" + e.getMessage());
            throw new FileLimitException("文件下载失败" + e.getMessage());
        }
        return BaseResponse.ok();
    }

    @ApiOperation("删除附件")
    @GetMapping("/deleteById/{id:\\d+}")
    public BaseResponse deleteById(@PathVariable Long id){
        Attachment attachment = attachmentService.getById(id);
        attachmentService.deleteById(id);
        FileUtils.deleteFile(attachment.getThumbPath());
        return BaseResponse.ok();
    }

    @ApiOperation("批量删除附件")
    @PostMapping("/deleteInBatch")
    public BaseResponse deleteInBatch(Set<Long> id){
        attachmentService.deleteInBatch(id);
        return BaseResponse.ok();
    }

    @ApiOperation("获取附件的所有类型")
    @PostMapping("/getAttachmentType")
    public BaseResponse getAttachmentType(){
        return BaseResponse.ok();
    }

}
