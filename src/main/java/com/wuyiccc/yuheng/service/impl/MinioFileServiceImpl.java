package com.wuyiccc.yuheng.service.impl;

import cn.hutool.core.text.CharPool;
import cn.hutool.core.text.CharSequenceUtil;
import com.wuyiccc.yuheng.common.constants.ErrorMsgConstants;
import com.wuyiccc.yuheng.common.enums.ContentTypeEnum;
import com.wuyiccc.yuheng.common.exception.CustomException;
import com.wuyiccc.yuheng.config.MinioConfig;
import com.wuyiccc.yuheng.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wuyiccc
 * @date 2023/9/3 06:53
 */
@Slf4j
@Service
public class MinioFileServiceImpl implements FileService {


    @Resource
    private MinioConfig minioConfig;


    @Override
    public String uploadImg(MultipartFile file) throws IOException {

        String fileName = UUID.randomUUID().toString().replace(String.valueOf(CharPool.DASHED), CharSequenceUtil.EMPTY) + file.getOriginalFilename();
        MinioClient minioClient = getMinioClient();
        PutObjectArgs build = PutObjectArgs.builder()
                .bucket(minioConfig.getBucket())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(ContentTypeEnum.IMG.getType())
                .build();
        try {

            minioClient.putObject(build);
            return minioConfig.getEndpoint() + CharPool.SLASH + minioConfig.getBucket() + CharPool.SLASH + fileName;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new CustomException(ErrorMsgConstants.FILE_UPLOAD_FAILED);
        }
    }

    private MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
    }
}
