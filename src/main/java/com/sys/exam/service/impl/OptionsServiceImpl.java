package com.sys.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sys.exam.mapper.OptionsMapper;
import com.sys.exam.mapper.QuestionStemMapper;
import com.sys.exam.pojo.Options;
import com.sys.exam.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Repository
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    OptionsMapper optionsMapper;

    @Autowired
    QuestionStemMapper questionStemMapper;

    @Override
    public List<Options> selectByStemId(String stemId) {
        return optionsMapper.selectList(new QueryWrapper<Options>().eq("stem_id", stemId));
    }

    @Override
    public List<Map<String, Object>> getStemOptionsMap(List<String> stemIds) {
        List<Map<String, Object>> res = new ArrayList<>();
        for (String stemId : stemIds) {
            Map<String, Object> map = new HashMap<>();
            List<Options> options = selectByStemId(stemId);
            map.put("options", options);
            String info = questionStemMapper.selectById(stemId).getInfo();
            System.out.println(info);
            map.put("info", info);;
            res.add(map);
        }
        return res;
    }


    @Override
    public int save(Options options) {
        return optionsMapper.insert(options);
    }


    @Override
    public Options selectById(String id) {
        return optionsMapper.selectById(id);
    }
}
