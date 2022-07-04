package com.sys.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.exam.pojo.QuestionStem;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
public interface QuestionStemService {
    List<QuestionStem> listPage(Integer page, Integer size, String info);
    int save(QuestionStem questionStem);
    int remove(String stemId);
    int update(QuestionStem questionStem);
    int count(String info);
    List<QuestionStem> selectByPaperId(String paperId);

    QuestionStem selectById(String stemId);

}
