package com.sys.exam.service;

import com.sys.exam.pojo.Options;

import java.util.List;
import java.util.Map;

/**
 * @author suyao
 * @date 1/6/2022
 */
public interface OptionsService {
    List<Options> selectByStemId(String stemId);
    List<Map<String, Object>> getStemOptionsMap(List<String> stemIds);
    int save(Options options);
    Options selectById(String id);

}
