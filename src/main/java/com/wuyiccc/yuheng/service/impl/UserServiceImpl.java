package com.wuyiccc.yuheng.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyiccc.yuheng.common.constants.DbConstants;
import com.wuyiccc.yuheng.common.constants.ErrorMsgConstants;
import com.wuyiccc.yuheng.common.enums.DelFlagEnum;
import com.wuyiccc.yuheng.common.exception.CustomException;
import com.wuyiccc.yuheng.mapper.UserMapper;
import com.wuyiccc.yuheng.pojo.dto.*;
import com.wuyiccc.yuheng.pojo.entity.UserEntity;
import com.wuyiccc.yuheng.pojo.vo.UserVO;
import com.wuyiccc.yuheng.pojo.vo.base.PageResult;
import com.wuyiccc.yuheng.service.UserService;
import com.wuyiccc.yuheng.common.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Objects;

/**
 * @author wuyiccc
 * @date 2023/9/12 23:23
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(UserCreateDTO userCreateDTO) {

        UserEntity userEntity = BeanUtil.copyProperties(userCreateDTO, UserEntity.class);
        // 1. 生成slat
        String slat = Md5Utils.generateSlat();
        userEntity.setSlat(slat);
        // 2. password加密
        String enPassword = Md5Utils.encrypt(userCreateDTO.getPassword(), slat);
        userEntity.setPassword(enPassword);

        userEntity.setDelFlag(DelFlagEnum.NO_DELETED.getType());
        userEntity.setDelId(DbConstants.DEFAULT_NO_DELETED_ID);

        userMapper.insert(userEntity);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, userLoginDTO.getUsername());
        wrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());

        List<UserEntity> userEntityList = userMapper.selectList(wrapper);

        if (CollUtil.isEmpty(userEntityList)) {
            throw new CustomException(ErrorMsgConstants.USERNAME_OR_PASSWORD_NOT_RIGHT);
        }
        UserEntity userEntity = userEntityList.get(0);
        String slat = userEntity.getSlat();
        String enPassword = Md5Utils.encrypt(userLoginDTO.getPassword(), slat);
        if (!userEntity.getPassword().equals(enPassword)) {
            throw new CustomException(ErrorMsgConstants.USERNAME_OR_PASSWORD_NOT_RIGHT);
        }
        StpUtil.login(userEntity.getId());
        return StpUtil.getTokenValue();
    }

    @Override
    public UserVO getLoginUserInfo() {

        String id = StpUtil.getLoginIdAsString();
        UserEntity userEntity = userMapper.selectById(id);
        if (Objects.isNull(userEntity) || DelFlagEnum.DELETED.getType().equals(userEntity.getDelFlag())) {
            throw new CustomException(ErrorMsgConstants.USER_NOT_FOUND);
        }
        return BeanUtil.copyProperties(userEntity, UserVO.class);
    }

    @Override
    public void deleteUser(String id) {

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getId, id);
        wrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());

        UserEntity userEntity = new UserEntity();
        userEntity.setDelFlag(DelFlagEnum.DELETED.getType());
        userEntity.setDelId(id);

        int res = userMapper.update(userEntity, wrapper);
        if (res != 1) {
            throw new CustomException(ErrorMsgConstants.DATA_NOT_FOUND);
        }
    }

    @Override
    public UserVO findUserById(String id) {

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getId, id);
        wrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());

        List<UserEntity> userEntityList = userMapper.selectList(wrapper);
        if (CollUtil.isEmpty(userEntityList)) {
            throw new CustomException(ErrorMsgConstants.USER_NOT_FOUND);
        }
        return BeanUtil.copyProperties(userEntityList.get(0), UserVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {

        UserEntity userEntity = BeanUtil.copyProperties(userUpdateDTO, UserEntity.class);

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getId, userEntity.getId());
        wrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());

        int res = userMapper.update(userEntity, wrapper);
        if (res != 1) {
            throw new CustomException(ErrorMsgConstants.USER_NOT_FOUND);
        }
    }

    @Override
    public PageResult<UserVO> pageQueryUser(UserPageQueryDTO userPageQueryDTO) {

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        String username = userPageQueryDTO.getUsername();
        wrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());
        if (CharSequenceUtil.isNotBlank(username)) {
            wrapper.like(UserEntity::getUsername, username);
        }

        Page<UserEntity> page = new Page<>(userPageQueryDTO.getCurrent(), userPageQueryDTO.getSize());

        Page<UserEntity> pageResult = userMapper.selectPage(page, wrapper);
        return PageResult.page(BeanUtil.copyToList(pageResult.getRecords(), UserVO.class), pageResult);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserPassword(UserPasswordUpdateDTO userPasswordUpdateDTO) {

        String id = userPasswordUpdateDTO.getId();
        if (Objects.isNull(id)) {
            throw new CustomException(ErrorMsgConstants.DATA_NOT_FOUND);
        }
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());
        wrapper.eq(UserEntity::getId, id);

        List<UserEntity> userEntityList = userMapper.selectList(wrapper);
        if (CollUtil.isEmpty(userEntityList)) {
            throw new CustomException(ErrorMsgConstants.USER_NOT_FOUND);
        }

        UserEntity userEntity = userEntityList.get(0);
        // 2. password加密
        String enPassword = Md5Utils.encrypt(userPasswordUpdateDTO.getPassword(), userEntity.getSlat());
        UserEntity newUser = new UserEntity();
        newUser.setPassword(enPassword);

        LambdaQueryWrapper<UserEntity> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(UserEntity::getId, userEntity.getId());
        updateWrapper.eq(UserEntity::getDelFlag, DelFlagEnum.NO_DELETED.getType());

        int res = userMapper.update(newUser, updateWrapper);
        if (res != 1) {
            throw new CustomException(ErrorMsgConstants.USER_NOT_FOUND);
        }

    }

}
