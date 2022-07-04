package com.sys.exam.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sys.exam.pojo.UserDto;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.vo.User;
import com.sys.exam.service.impl.UserService;
import com.sys.exam.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@Api(tags = "用户控制器")
@CrossOrigin //解决跨域访问问题，
public class UserController {
    @Autowired  //注入
    private UserDtoService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始数据", example = "1",
                    required = true, dataType = "integer"),
            @ApiImplicitParam(name = "size", value = "每页显示最大记录数", example = "10",
                    required = true, dataType = "integer"),
            @ApiImplicitParam(name = "username", value = "根据用户姓名查询", example = "admin",
                    dataType = "string"),
    })
    @GetMapping("/listPage")
    @ApiOperation("分页查询用户信息")
    public CommonResult<?> listPage(Integer page,Integer size, String username){
        Map<String,Object> map=new HashMap<>();
        map.put("count",userService.count(username));
        map.put("data",userService.listPage(page,size,username));
        return CommonResult.success(JSONUtil.parseObj(map));
    }
    
    

    @GetMapping("/me")
    @ApiOperation("根据当前用户获取基本信息")
    public CommonResult<?> selectByUsername(String username) {
        UserDto userDto = userService.selectByUsername(username);
        return CommonResult.success(JSONUtil.parseObj(userDto));
    }


    @PostMapping("/save")
    @ApiOperation("添加用户信息")
    public CommonResult<?> save(@RequestBody UserDto user) {
        user.setUserId(IdUtil.simpleUUID());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return CommonResult.success(userService.save(user));
    }

    @PostMapping("/update")
    @ApiOperation("修改用户信息")
    public CommonResult<?> update(@RequestBody UserDto user) {
        return CommonResult.success(userService.update(user));
    }

    @DeleteMapping("/remove")
    @ApiOperation("根据ID删除用户信息")
    public CommonResult<?> remove(String userId) {
        System.out.println("-------" + userId);
        return CommonResult.success(userService.remove(userId));
    }


}
