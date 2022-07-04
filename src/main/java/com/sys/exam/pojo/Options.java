package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 选项表
 * @TableName options
 */
@TableName(value ="options")
@Data
public class Options implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String info;

    /**
     * 
     */
    private Integer isCorrect;

    /**
     * 
     */
    private String stemId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}