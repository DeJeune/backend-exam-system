package com.sys.exam.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.exam.mapper.UserMapper;
import com.sys.exam.pojo.UserDto;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyao
 * @date 1/7/2022
 */
@Service
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> listPage(Integer page, Integer size, String username) {
        QueryWrapper<UserDto> wrapper = new QueryWrapper<UserDto>();
        wrapper
                .inSql("user_id","select user_id from user_role where role_id=2")
                .like(!StrUtil.isBlank(username), "username", username);
        Page<UserDto> iPage = userMapper.selectPage(new Page<>(page, size), wrapper);

        return iPage.getRecords();
    }

    public int count(String username) {
        return userMapper.selectCount(new QueryWrapper<UserDto>().like(!StrUtil.isBlank(username), "username", username));
    }

    public int save(UserDto user) {
        user.setCreateTime(DateUtil.now());
        if (StrUtil.isEmpty(user.getUpdateTime())) {
            user.setUpdateTime(DateUtil.now());
        }

        return userMapper.insert(user);
    }

    public int remove(String userId) {
        return userMapper.deleteById(userId);
    }

    public int update(UserDto user) {
        user.setUpdateTime(DateUtil.now());

        return userMapper.updateById(user);
    }

    @Override
    public UserDto selectById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserDto selectByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<UserDto>().eq("username", username));
    }

    @Override
    public List<String> selectByIdList(List<String> userIdList) {
        List<String> usernameList = new ArrayList<>();
        userIdList.forEach(userId -> usernameList.add(userMapper.selectById(userId).getUsername()));
        return usernameList;
    }

    @Override
    public void saveCode(String password, String bcryptCode, String bcryptId) {

    }
}
