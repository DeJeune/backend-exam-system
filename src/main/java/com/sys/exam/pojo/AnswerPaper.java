package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 答卷表
 * @TableName answer_paper
 */
@TableName(value ="answer_paper")
@Data
public class AnswerPaper implements Serializable {
    /**
     * 
     */
    @TableId
    private String answerId;

    /**
     * 
     */
    private String paperId;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private Integer points;

    /**
     * 
     */
    private String startTime;

    /**
     * 
     */
    private String submitTime;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}