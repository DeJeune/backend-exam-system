package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author suyao
 * @date 1/7/2022
 */

@TableName("user")
@Data
@ApiModel(value="用户实体类", description="用于用户的增删查改")
public class UserDto implements Serializable {
    @TableId
    @ApiModelProperty(value = "用户ID", example="12", required = true,dataType = "string")
    private String userId;
    @ApiModelProperty(value = "用户名", example="admin", required = true,dataType = "string")
    private String username;
    @ApiModelProperty(value = "密码", example="777", required = true,dataType = "string")
    private String password;
    @ApiModelProperty(value = "头像", example="39uyw87f", required = true,dataType = "string")
    private String icon;
    @ApiModelProperty(value = "性别", example="男", required = true,dataType = "string")
    private String gender;
    @ApiModelProperty(value = "电话", example="134564444", required = true,dataType = "string")
    private String phoneNumber;
    @ApiModelProperty(hidden = true)
    private String createTime;
    @ApiModelProperty(hidden = true)
    private String updateTime;

    @TableLogic
    @ApiModelProperty(hidden = true)
    private String deleted;
}
