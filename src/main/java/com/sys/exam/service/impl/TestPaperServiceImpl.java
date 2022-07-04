package com.sys.exam.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.sys.exam.mapper.AnswerPaperMapper;
import com.sys.exam.mapper.QuestionStemMapper;
import com.sys.exam.mapper.TestPaperMapper;
import com.sys.exam.mapper.TestQuestionMapper;

import com.sys.exam.pojo.QuestionStem;
import com.sys.exam.pojo.TestPaper;
import com.sys.exam.pojo.TestQuestion;
import com.sys.exam.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Autowired
    TestPaperMapper testPaperMapper;

    @Autowired
    QuestionStemMapper questionStemMapper;

    @Autowired
    TestQuestionMapper testQuestionMapper;

    @Autowired
    AnswerPaperMapper answerPaperMapper;

    @Override
    public List<TestPaper> listPage(Integer page, Integer size, String paperName) {
        Page<TestPaper> testPaperPage = testPaperMapper.selectPage(new Page<>(page, size), new QueryWrapper<TestPaper>().like(!StrUtil.isBlank(paperName),"name", paperName));
        return testPaperPage.getRecords();
    }

    @Override
    public int save(TestPaper testPaper) {

        List<QuestionStem> questionStems = questionStemMapper.selectRandomly(testPaper.getCount());

        questionStems.forEach((questionStem) -> {
            TestQuestion testQuestion = new TestQuestion();
            testQuestion.setStemId(questionStem.getStemId());
            testQuestion.setPaperId(testPaper.getPaperId());
            testQuestion.setId(IdUtil.simpleUUID());
            testQuestionMapper.insert(testQuestion);
        });

        testPaper.setCreateTime(DateUtil.now());
        if (StrUtil.isEmpty(testPaper.getUpdateTime())) {
            testPaper.setCreateTime(DateUtil.now());
        }
        return testPaperMapper.insert(testPaper);
    }

    @Override
    public int count(String paperName) {
        return testPaperMapper.selectCount(new QueryWrapper<TestPaper>().like(!StrUtil.isBlank(paperName),"name", paperName));
    }

    @Override
    public int remove(String paperId) {
        return testPaperMapper.deleteById(paperId);
    }

    @Override
    public int update(TestPaper testPaper) {
        testPaper.setUpdateTime(DateUtil.now());
        return testPaperMapper.updateById(testPaper);
    }

    @Override
    public TestPaper selectById(String paperId) {
        return testPaperMapper.selectById(paperId);
    }

    @Override
    public int sum(String paperId) {
        return testPaperMapper.sum(paperId);
    }

    @Override
    public List<TestPaper> selectList(List<String> idList) {
        return testPaperMapper.selectList(new QueryWrapper<TestPaper>().notIn(!idList.isEmpty(),"paper_id", idList));
    }

    @Override
    public List<TestPaper> selectList2(List<String> idList) {
        return testPaperMapper.selectList(new QueryWrapper<TestPaper>().in(!idList.isEmpty(),"paper_id", idList));
    }
}