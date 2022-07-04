package com.sys.exam.service;

import com.sys.exam.pojo.UserDto;
import com.sys.exam.vo.User;

import java.util.List;

/**
 * @author suyao
 * @date 1/7/2022
 */
public interface UserDtoService {
    public List<UserDto> listPage(Integer page, Integer size, String username);

    public int count(String username);

    public int save(UserDto user);

    public int remove(String userId);
    public int update(UserDto user);

    UserDto selectById(String userId);

    UserDto selectByUsername(String username);

    List<String> selectByIdList(List<String> userIdList);

    void saveCode(String password, String bcryptCode, String bcryptId);
}
