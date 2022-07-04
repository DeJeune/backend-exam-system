package com.sys.exam.service;

import com.sys.exam.pojo.AnswerPaper;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
public interface AnswerPaperService {
    List<AnswerPaper> listPage(Integer page, Integer size, String paperName);
    int remove(String answerId);
    int count(String paperName);

    String rate(String paperId, String answerId);

    boolean save(AnswerPaper answerPaper);

    List<String> selectTestPaperName(String paperId);
    List<AnswerPaper> selectByUserId(String userID);
}
