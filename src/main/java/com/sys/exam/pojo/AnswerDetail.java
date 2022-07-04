package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 具体答卷表
 * @TableName answer_detail
 */
@TableName(value ="answer_detail")
@Data
public class AnswerDetail implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String answerId;

    private String optionId;

    /**
     * 
     */
    private Integer isOk;

    /**
     * 
     */
    private String stemId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}