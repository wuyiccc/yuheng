package com.wuyiccc.yuheng.config;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.util.StrUtil;
import com.wuyiccc.yuheng.common.constants.ErrorMsgConstants;
import com.wuyiccc.yuheng.common.enums.ResponseStatusEnum;
import com.wuyiccc.yuheng.common.exception.CustomException;
import com.wuyiccc.yuheng.pojo.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuyiccc
 * @date 2023/6/26 21:59
 * 全局异常拦截器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {




    @ExceptionHandler(CustomException.class)
    public CommonResult<String> returnMyCustomException(CustomException e) {
        String msg = e.getMsg();
        if (StrUtil.isBlank(msg)) {
            msg = e.getResponseStatusEnum().msg();
        }
        return CommonResult.exception(e.getResponseStatusEnum(), msg);
    }

    @ExceptionHandler(SaTokenException.class)
    public CommonResult<String> returnSaTokenException(SaTokenException e) {
        return CommonResult.exception(ResponseStatusEnum.TOKEN_FAILED);
    }



    @ExceptionHandler(DuplicateKeyException.class)
    public CommonResult<String> returnMyDuplicateKeyException(DuplicateKeyException e) {
        return CommonResult.errorMsg(ErrorMsgConstants.DATA_REPEAT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Map<String, String>> returnNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> errors = getErrors(result);
        return CommonResult.errorData(errors);
    }

    @ExceptionHandler(Exception.class)
    public CommonResult<String> returnException(Exception e) {
        log.error("非自定义异常", e);
        return CommonResult.errorMsg(e.getMessage());
    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList) {
            // 错误字段名称
            String fieldName = error.getField();
            // 错误信息
            String errMsg = error.getDefaultMessage();
            errorMap.put(fieldName, errMsg);
        }
        return errorMap;
    }





}
