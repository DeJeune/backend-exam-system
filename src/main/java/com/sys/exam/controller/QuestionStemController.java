package com.sys.exam.controller;


import cn.hutool.json.JSONUtil;
import com.sys.exam.pojo.Options;
import com.sys.exam.pojo.QuestionStem;
import com.sys.exam.service.OptionsService;
import com.sys.exam.service.QuestionBankService;
import com.sys.exam.service.QuestionStemService;
import com.sys.exam.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/question")
@Api(tags = "题目控制器")
@CrossOrigin //解决跨域访问问题，
public class QuestionStemController {
    @Autowired
    private QuestionStemService questionStemService;
    @Autowired
    private QuestionBankService questionBankService;


        @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "分页起始数据",example = "1",
                    required = true, dataType = "integer"),
            @ApiImplicitParam(name = "size",value = "每页显示最大记录数",example = "10",
                    required = true,dataType = "integer"),
            @ApiImplicitParam(name = "info",value = "请输入题干信息查询",example = "cs",
                    dataType = "string"),
    })
    @GetMapping("/listPage")
    @ApiOperation("分页查询题目信息")
    public CommonResult<?> listPage(int page, int size, String info) {
        Map<String, Object> map = new HashMap<>();
        List<QuestionStem> questionStemList = questionStemService.listPage(page, size, info);
        List<String> subjectList = new ArrayList<>();
        questionStemList.forEach(questionStem -> subjectList.add(questionBankService.selectById(questionStem.getBankId()).getCategory()));
        Map<String, Object> map2 = new HashMap<>();
        map2.put("subject", subjectList);
        map2.put("data", questionStemList);
        map.put("count", questionStemService.count(info));
        map.put("data", map2);
        return CommonResult.success(JSONUtil.parseObj(map));
    }

    @PostMapping("/save")
    @ApiOperation("添加题目信息")
    public CommonResult<?> save(@RequestBody QuestionStem questionStem) {
        return CommonResult.success(questionStemService.save(questionStem));
    }


    @PostMapping("/update")
    @ApiOperation("修改题目信息")
    public CommonResult update(@RequestBody QuestionStem questionStem) {
        return CommonResult.success(questionStemService.update(questionStem));
    }

    @PostMapping("/remove")
    @ApiOperation("根据ID删除题目信息")
    public CommonResult remove(@RequestBody String stemId) {
        return CommonResult.success(questionStemService.remove(stemId));

    }

}
