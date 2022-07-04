package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 试卷题目表
 * @TableName test_question
 */
@TableName(value ="test_question")
@Data
public class TestQuestion implements Serializable {
    @TableId
    private String id;
    /**
     * 
     */
    private String stemId;

    /**
     * 
     */
    private String paperId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}