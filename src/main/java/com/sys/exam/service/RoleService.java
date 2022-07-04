package com.sys.exam.service;

import com.sys.exam.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sy132
* @description 针对表【role(角色表)】的数据库操作Service
* @createDate 2022-01-06 15:50:55
*/

public interface RoleService {
    List<Role> getAllRoles();
}
