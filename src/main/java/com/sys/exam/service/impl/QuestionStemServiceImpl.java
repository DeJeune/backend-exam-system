package com.sys.exam.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.exam.mapper.QuestionStemMapper;
import com.sys.exam.mapper.TestQuestionMapper;
import com.sys.exam.pojo.QuestionStem;
import com.sys.exam.pojo.TestQuestion;
import com.sys.exam.service.QuestionStemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Service
public class QuestionStemServiceImpl implements QuestionStemService {

    @Autowired
    QuestionStemMapper questionStemMapper;

    @Autowired
    TestQuestionMapper testQuestionMapper;

    @Override
    public List<QuestionStem> listPage(Integer page, Integer size, String info) {
        Page<QuestionStem> questionStemPage = questionStemMapper.selectPage(new Page<>(page, size), new QueryWrapper<QuestionStem>().like(!StrUtil.isBlank(info),"info", info));
        return questionStemPage.getRecords();
    }

    @Override
    public int save(QuestionStem questionStem) {
        questionStem.setStemId(IdUtil.simpleUUID());
        questionStem.setCreateTime(DateUtil.now());
        if (StrUtil.isEmpty(questionStem.getUpdateTime())) {
            questionStem.setUpdateTime(DateUtil.now());
        }
        return questionStemMapper.insert(questionStem);
    }

    @Override
    public int remove(String stemId) {
        return questionStemMapper.deleteById(stemId);
    }

    @Override
    public int update(QuestionStem questionStem) {
        questionStem.setUpdateTime(DateUtil.now());
        return questionStemMapper.updateById(questionStem);
    }

    @Override
    public int count(String info) {
        return questionStemMapper.selectCount(new QueryWrapper<QuestionStem>().like(!StrUtil.isBlank(info), "info", info));
    }

    @Override
    public List<QuestionStem> selectByPaperId(String paperId) {

        List<TestQuestion> testQuestionList = testQuestionMapper.selectList(new QueryWrapper<TestQuestion>().eq("paper_id", paperId));
        List<String> stemIdList = new ArrayList<>();
        testQuestionList.forEach(testQuestion -> stemIdList.add(testQuestion.getStemId()));
        return questionStemMapper.selectList(new QueryWrapper<QuestionStem>().in("stem_id", stemIdList));
    }

    @Override
    public QuestionStem selectById(String stemId) {
        return questionStemMapper.selectById(stemId);
    }

}
