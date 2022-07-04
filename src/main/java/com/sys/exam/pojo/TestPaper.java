package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 试卷表
 * @TableName test_paper
 */
@TableName(value ="test_paper")
@Data
public class TestPaper implements Serializable {
    /**
     * 
     */
    @TableId
    private String paperId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String startTime;

    /**
     * 
     */
    private Integer totalTime;

    /**
     * 
     */
    private Integer count;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String updateTime;

    /**
     * 
     */
    private String userId;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}