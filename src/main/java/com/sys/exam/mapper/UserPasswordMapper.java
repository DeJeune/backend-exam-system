package com.sys.exam.mapper;

import com.sys.exam.pojo.UserPassword;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author sy132
* @description 针对表【user_password(用户密码表)】的数据库操作Mapper
* @createDate 2022-01-09 15:09:50
* @Entity com.sys.exam.pojo.UserPassword
*/
@Repository
public interface UserPasswordMapper extends BaseMapper<UserPassword> {

}




