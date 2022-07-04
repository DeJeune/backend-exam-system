package com.sys.exam.mapper;

import com.sys.exam.pojo.QuestionStem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author sy132
* @description 针对表【question_stem(题干表)】的数据库操作Mapper
* @createDate 2022-01-06 20:06:53
* @Entity com.sys.exam.pojo.QuestionStem
*/
@Repository
public interface QuestionStemMapper extends BaseMapper<QuestionStem> {
    @Select("select * from exam.question_stem as a where CAST(stem_id as SIGNED) >= floor(RAND() * (SELECT max(CAST(stem_id as SIGNED)) from exam.question_stem)) AND  a.deleted = 0 order by CAST(stem_id as SIGNED) LIMIT #{num}")
    List<QuestionStem> selectRandomly(int num);
}




