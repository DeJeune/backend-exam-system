package com.sys.exam.service.impl;

import com.sys.exam.mapper.RoleMapper;
import com.sys.exam.pojo.Role;
import com.sys.exam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectList(null);
    }
}
