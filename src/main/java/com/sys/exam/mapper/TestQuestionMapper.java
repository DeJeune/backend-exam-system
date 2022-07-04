package com.sys.exam.mapper;

import com.sys.exam.pojo.TestQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author sy132
* @description 针对表【test_question(试卷题目表)】的数据库操作Mapper
* @createDate 2022-01-06 20:08:33
* @Entity com.sys.exam.pojo.TestQuestion
*/
@Repository
public interface TestQuestionMapper extends BaseMapper<TestQuestion> {

}




