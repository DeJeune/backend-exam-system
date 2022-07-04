package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户试卷表
 * @TableName user_paper
 */
@TableName(value ="user_paper")
@Data
public class UserPaper implements Serializable {
    /**
     * 
     */
    @TableId
    private String userId;

    /**
     * 
     */
    private String paperId;

    /**
     * 
     */
    private String startTime;

    /**
     * 
     */
    private String finishTime;

    /**
     * 
     */
    private Integer status;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}