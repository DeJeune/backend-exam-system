package com.sys.exam.service;

import com.sys.exam.pojo.TestPaper;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
public interface TestPaperService {
    List<TestPaper> listPage(Integer page, Integer size, String paperName);
    int save(TestPaper testPaper);
    int count(String paperName);
    int remove(String paperId);
    int update(TestPaper testPaper);

    TestPaper selectById(String paperId);

    int sum(String paperId);

    List<TestPaper> selectList(List<String> idList);

    List<TestPaper> selectList2(List<String> idList);
}
