package com.sys.exam.service.impl;

import com.sys.exam.mapper.QuestionBankMapper;
import com.sys.exam.pojo.QuestionBank;
import com.sys.exam.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suyao
 * @date 1/7/2022
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    @Autowired
    QuestionBankMapper questionStemMapper;

    @Override
    public QuestionBank selectById(String bankId) {
        return questionStemMapper.selectById(bankId);
    }
}
