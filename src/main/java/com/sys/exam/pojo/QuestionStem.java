package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 题干表
 * @TableName question_stem
 */
@TableName(value ="question_stem")
@Data
public class QuestionStem implements Serializable {
    /**
     *
     */
    @TableId
    private String stemId;

    /**
     *
     */
    private String info;

    /**
     *
     */
    private Integer points;

    /**
     *
     */
    private String bankId;

    /**
     *
     */

    private String createTime;

    /**
     *
     */
    private String updateTime;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}