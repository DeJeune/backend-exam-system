package com.sys.exam.controller;

import cn.hutool.json.JSONUtil;
import com.sys.exam.pojo.UserDto;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.service.impl.UserService;
import com.sys.exam.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyao
 * @date 1/9/2022
 */
@RequestMapping("/user")
@Api(tags="个人中心控制器")
@RestController
public class PersonController {

    @Autowired
    UserDtoService userService;

    @GetMapping("/me")
    @ApiOperation("根据当前用户获取基本信息")
    public CommonResult<?> selectByUsername(String username) {
        UserDto userDto = userService.selectByUsername(username);
        return CommonResult.success(JSONUtil.parseObj(userDto));
    }
}
