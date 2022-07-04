package com.sys.exam.mapper;

import com.sys.exam.pojo.UserDto;
import com.sys.exam.vo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author sy132
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-01-06 15:52:35
* @Entity com.sys.exam.vo.User
*/
@Repository
public interface UserMapper extends BaseMapper<UserDto> {

}




