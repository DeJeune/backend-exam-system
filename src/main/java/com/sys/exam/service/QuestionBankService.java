package com.sys.exam.service;

import com.sys.exam.pojo.QuestionBank;

/**
 * @author suyao
 * @date 1/6/2022
 */
public interface QuestionBankService {
    QuestionBank selectById(String bankId);
}
