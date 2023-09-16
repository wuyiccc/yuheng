package com.wuyiccc.yuheng.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.wuyiccc.yuheng.pojo.dto.*;
import com.wuyiccc.yuheng.pojo.vo.CommonResult;
import com.wuyiccc.yuheng.pojo.vo.UserVO;
import com.wuyiccc.yuheng.pojo.vo.base.PageResult;
import com.wuyiccc.yuheng.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:21
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;


    @ApiOperation(value = "用户登录")
    @SaIgnore
    @PostMapping("/login")
    public CommonResult<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        String token = userService.login(userLoginDTO);
        return CommonResult.ok(token);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public CommonResult<String> logout() {

        StpUtil.logout();
        return CommonResult.ok();
    }


    @ApiOperation(value = "查询当前登录的用户信息")
    @GetMapping("/getLoginUserInfo")
    public CommonResult<UserVO> getLoginUserInfo() {

        UserVO userVO = userService.getLoginUserInfo();
        return CommonResult.ok(userVO);
    }


    @ApiOperation(value = "新建用户")
    @PostMapping("/addUser")
    public CommonResult<String> addUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {

        userService.addUser(userCreateDTO);
        return CommonResult.ok();
    }

    @ApiOperation(value = "删除指定id的用户")
    @PostMapping("/deleteUser")
    public CommonResult<String> deleteUser(@RequestParam("id") String id) {

        userService.deleteUser(id);
        return CommonResult.ok();
    }


    @ApiOperation(value = "查询指定id的用户信息")
    @GetMapping("/findUserById")
    public CommonResult<UserVO> findUserById(@RequestParam("id") String id) {

        UserVO userVO = userService.findUserById(id);
        return CommonResult.ok(userVO);
    }


    @ApiOperation(value = "更新用户信息")
    @PostMapping("/updateUser")
    public CommonResult<String> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {

        userService.updateUser(userUpdateDTO);
        return CommonResult.ok();
    }

    @ApiOperation(value = "用户信息分页查询")
    @PostMapping("/pageQueryUser")
    public CommonResult<PageResult<UserVO>> pageQueryUser(@Valid @RequestBody UserPageQueryDTO userPageQueryDTO) {

        PageResult<UserVO> pageResult = userService.pageQueryUser(userPageQueryDTO);
        return CommonResult.ok(pageResult);
    }

    @ApiOperation(value = "更新自己的密码")
    @PostMapping("/updateMyPassword")
    public CommonResult<String> updateMyPassword(@Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO) {
        userPasswordUpdateDTO.setId(StpUtil.getLoginIdAsString());
        userService.updateUserPassword(userPasswordUpdateDTO);
        return CommonResult.ok();
    }

    @ApiOperation(value = "更新用户密码")
    @PostMapping("/updateUserPassword")
    public CommonResult<String> updateUserPassword(@Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO) {

        userService.updateUserPassword(userPasswordUpdateDTO);
        return CommonResult.ok();
    }


}
