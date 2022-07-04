package com.sys.exam.mapper;

import com.sys.exam.pojo.TestPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author sy132
 * @description 针对表【test_paper(试卷表)】的数据库操作Mapper
 * @createDate 2022-01-06 20:08:40
 * @Entity com.sys.exam.pojo.TestPaper
 */
@Repository
public interface TestPaperMapper extends BaseMapper<TestPaper> {
    @Select("select sum(points) from exam.question_stem, exam.test_question where test_question.stem_id = question_stem.stem_id and paper_id = #{paperId} ")
    int sum(String paperId);
}
