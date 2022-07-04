package com.sys.exam.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.sys.exam.pojo.UserDto;
import com.sys.exam.pojo.UserPassword;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.service.UserPasswordService;
import com.sys.exam.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sys.exam.utils.CommonResult.success;

@RestController
public class RegisterController {

    @Autowired
    UserDtoService userDtoService;

    @Autowired
    UserPasswordService userPasswordService;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public CommonResult<Boolean> register(@RequestBody UserDto userDto) {
        //生成用户id
        String bcryptId  = IdUtil.simpleUUID();
        //加密密码
        String bcryptCode = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        //存入用户密码表
        UserPassword user = new UserPassword();
        user.setId(IdUtil.simpleUUID());
        user.setUserId(bcryptId);
        user.setUncodedPassword(userDto.getPassword());
        userDto.setUserId(bcryptId);
        userDto.setPassword(bcryptCode);
        userDto.setCreateTime(DateUtil.now());
        userDto.setUpdateTime(DateUtil.now());
        return CommonResult.success(userDtoService.save(userDto) == 1 && userPasswordService.save(user));
    }
}
