package com.sys.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.exam.mapper.RoleMapper;
import com.sys.exam.mapper.UserMapper;
import com.sys.exam.mapper.UserRoleMapper;
import com.sys.exam.pojo.Role;
import com.sys.exam.pojo.UserDto;
import com.sys.exam.vo.User;
import com.sys.exam.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto user = userMapper.selectOne(new QueryWrapper<UserDto>().eq("username", s));
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        String userId = user.getUserId();
        UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("user_id", userId));
        Role role = roleMapper.selectById(userRole.getRoleId());
        User userVo = new User();
        userVo.setUsername(user.getUsername());
        userVo.setPassword(user.getPassword());
        userVo.setRole(role);
        return userVo;
    }
}
