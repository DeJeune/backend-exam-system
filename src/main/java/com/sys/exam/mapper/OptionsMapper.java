package com.sys.exam.mapper;

import com.sys.exam.pojo.Options;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @author sy132
* @description 针对表【options(选项表)】的数据库操作Mapper
* @createDate 2022-01-06 20:07:21
* @Entity com.sys.exam.pojo.Options
*/
@Repository
public interface OptionsMapper extends BaseMapper<Options> {
}




