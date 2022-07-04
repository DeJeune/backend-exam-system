package com.sys.exam.vo;

import com.sys.exam.pojo.AnswerDetail;
import com.sys.exam.pojo.AnswerPaper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tri
 * @Date 2022/1/9
 * @Description //TODO
 */
@Data
public class AnswerPaperVo implements Serializable {

    private List<AnswerDetail> answerDetailList;

    private AnswerPaper answerPaper;
}
