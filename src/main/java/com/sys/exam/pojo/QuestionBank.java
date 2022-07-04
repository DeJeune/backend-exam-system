package com.sys.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题库表
 * @TableName question_bank
 */
@TableName(value ="question_bank")
@Data
public class QuestionBank implements Serializable {
    /**
     * 
     */
    @TableId
    private String bankId;

    /**
     * 
     */
    private String category;

    /**
     * 
     */
    private String userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}