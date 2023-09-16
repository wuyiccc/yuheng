package com.wuyiccc.yuheng.controller;

import com.wuyiccc.yuheng.pojo.vo.CommonResult;
import com.wuyiccc.yuheng.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author wuyiccc
 * @date 2023/9/16 06:28
 */
@Api(tags = "文件管理")
@Slf4j
@RestController
@RequestMapping("/file")
@Validated
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation(value = "图片上传")
    @PostMapping("/uploadImg")
    public CommonResult<String> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {

        String url = fileService.uploadImg(file);
        return CommonResult.ok(url);
    }
}
