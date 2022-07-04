package com.sys.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.exam.pojo.UserPassword;
import com.sys.exam.service.UserPasswordService;
import com.sys.exam.mapper.UserPasswordMapper;
import org.springframework.stereotype.Service;

/**
* @author sy132
* @description 针对表【user_password(用户密码表)】的数据库操作Service实现
* @createDate 2022-01-09 15:09:50
*/
@Service
public class UserPasswordServiceImpl extends ServiceImpl<UserPasswordMapper, UserPassword>
    implements UserPasswordService{

}




