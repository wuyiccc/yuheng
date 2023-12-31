package com.wuyiccc.yuheng.service;

import com.wuyiccc.yuheng.pojo.dto.*;
import com.wuyiccc.yuheng.pojo.vo.UserVO;
import com.wuyiccc.yuheng.pojo.vo.base.PageResult;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:22
 */
public interface UserService {


    /**
     * 新建用户
     * @param userCreateDTO 新建用户实体
     */
    void addUser(UserCreateDTO userCreateDTO);

    /**
     * 用户登录
     * @param userLoginDTO 用户登录参数
     * @return 用户登录token
     */
    String login(UserLoginDTO userLoginDTO);


    /**
     * 查询当前登录的用户的信息
     * @return 用户信息
     */
    UserVO getLoginUserInfo();


    /**
     * 删除指定id的用户
     * @param id 用户id
     */
    void deleteUser(String id);

    /**
     * 查询指定用户id的用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserVO findUserById(String id);


    /**
     * 更新指定用户信息
     * @param userUpdateDTO 用户更新信息
     */
    void updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 分页查询用户信息
     * @param userPageQueryDTO 分页查询参数
     * @return 分页结果
     */
    PageResult<UserVO> pageQueryUser(UserPageQueryDTO userPageQueryDTO);

    /**
     * 更新用户的密码
     * @param userPasswordUpdateDTO 更新之后的密码
     */
    void updateUserPassword(UserPasswordUpdateDTO userPasswordUpdateDTO);
}
