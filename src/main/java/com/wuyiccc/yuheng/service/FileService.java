package com.wuyiccc.yuheng.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author wuyiccc
 * @date 2023/9/3 06:53
 */
public interface FileService {


    /**
     * 上传图片
     * @param file 文件数据
     * @return 文件访问url路径
     */
    String uploadImg(MultipartFile file) throws IOException;
}
