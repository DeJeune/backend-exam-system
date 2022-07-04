package com.sys.exam.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sys.exam.mapper.AnswerPaperMapper;
import com.sys.exam.mapper.TestPaperMapper;
import com.sys.exam.pojo.AnswerPaper;
import com.sys.exam.pojo.TestPaper;
import com.sys.exam.service.AnswerPaperService;
import com.sys.exam.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Service
public class AnswerPaperServiceImpl implements AnswerPaperService {
    @Autowired
    private AnswerPaperMapper answerPaperMapper;

    @Autowired
    private TestPaperMapper testPaperMapper;

    @Autowired
    private TestPaperService testPaperService;

    @Override
    public List<AnswerPaper> listPage(Integer page, Integer size, String paperName) {
        List<String> idList = selectTestPaperList(paperName);
        Page<AnswerPaper> paperPage = answerPaperMapper.selectPage(new Page<>(page, size), new QueryWrapper<AnswerPaper>().in("paper_id", idList));
        return paperPage.getRecords();
    }

    @Override
    public int remove(String answerId) {
        return answerPaperMapper.deleteById(answerId);
    }

    @Override
    public int count(String paperName) {
        List<String> idList = selectTestPaperList(paperName);
        return answerPaperMapper.selectCount(new QueryWrapper<AnswerPaper>().in("paper_id", idList));
    }

    @Override
    public String rate(String paperId, String answerId) {
        TestPaper testPaper = testPaperMapper.selectById(paperId);
        AnswerPaper answerPaper = answerPaperMapper.selectById(answerId);

        Double total = (answerPaper.getPoints() * 1.0 / testPaperService.sum(testPaper.getPaperId()));
        total*=100;
        return String.format("%.2f", total);
    }

    @Override
    public boolean save(AnswerPaper answerPaper) {
        return answerPaperMapper.insert(answerPaper) == 1;
    }

    @Override
    public List<String> selectTestPaperName(String paperId) {
        List<TestPaper> testPaperList = testPaperMapper.selectList(new QueryWrapper<TestPaper>().eq("paper_id", paperId));
        List<String> testPaperName = new ArrayList<>();
        for(TestPaper testPaper:testPaperList){
            testPaperName.add(testPaper.getName());
        }
        if(testPaperName.isEmpty()){
            testPaperName.add("");
        }
        return testPaperName;
    }

    @Override
    public List<AnswerPaper> selectByUserId(String userID) {
        return answerPaperMapper.selectList(new QueryWrapper<AnswerPaper>().eq("user_id",userID));

    }
    private List<String> selectTestPaperList(String paperName) {
        List<TestPaper> testPaperList = testPaperMapper.selectList(new QueryWrapper<TestPaper>().like(!StrUtil.isBlank(paperName), "name", paperName));
        List<String> idList = new ArrayList<>();
        for (TestPaper testPaper: testPaperList) {
            idList.add(testPaper.getPaperId());
        }

        if (idList.isEmpty()) {
            idList.add("");
        }

        return idList;
    }
}
