package com.sys.exam.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.sys.exam.pojo.QuestionStem;
import com.sys.exam.pojo.TestPaper;

import com.sys.exam.service.*;
import com.sys.exam.utils.CommonResult;
import com.sys.exam.utils.UserUtils;
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

/**
 * @author Tri
 * @Date 2022/1/6
 * @Description //TODO
 */
@RestController
@RequestMapping("/admin/testPaper")
@Api(tags="试卷控制器")
@CrossOrigin
public class TestPaperController {
    @Autowired
    private UserDtoService userDtoService;

    @Resource
    private TestPaperService testPaperService;

    @Autowired
    private QuestionStemService questionStemService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private UserPaperService userPaperService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页起始数据",example = "1",required = true,dataType = "integer"),
            @ApiImplicitParam(name = "size",value = "每页显示最大记录数",example = "10",required = true,dataType = "integer"),
    })
    @GetMapping("/listPage")
    @ApiOperation(value = "分页查询试卷信息")
    public CommonResult<?> listPage(int page, int size, String paperName){
        Map<String,Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        List<Integer> status = new ArrayList<>();
        List<TestPaper> testPaperList = testPaperService.listPage(page,size,paperName);
        testPaperList.forEach(testPaper -> {
            list.add(testPaperService.sum(testPaper.getPaperId()));
            status.add(userPaperService.checkStatus(testPaper.getPaperId()));
        });
        map.put("sum", list);
        map.put("status",status);
        map.put("count",testPaperService.count(paperName));
        map.put("data",testPaperService.listPage(page,size,paperName));
        return CommonResult.success(JSONUtil.parseObj(map));
    }

    @PostMapping("/save")
    @ApiOperation(value = "组卷试卷")
    public CommonResult<?> save(@RequestBody TestPaper testPaper){
        testPaper.setPaperId(IdUtil.simpleUUID());

        testPaper.setUserId(userDtoService.selectByUsername(UserUtils.getCurrentUser().getUsername()).getUserId());
        return CommonResult.success(testPaperService.save(testPaper));
    }

    @GetMapping("/select-paper")
    @ApiOperation(value = "查看组好的试卷")
    public CommonResult<?> selectATestPaper(String paperId) {
        List<QuestionStem> questionStemList = questionStemService.selectByPaperId(paperId);
        List<String> stemIds = new ArrayList<>();
        questionStemList.forEach(questionStem -> stemIds.add(questionStem.getStemId()));
        List<Map<String, Object>> stemIdOptionsList = optionsService.getStemOptionsMap(stemIds);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("baseInfo", testPaperService.selectById(paperId));
        map1.put("concreteInfo", stemIdOptionsList);
        Map<String, Object> result = new HashMap<>();
        result.put("data", map1);
        return CommonResult.success(JSONUtil.parseObj(result));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改试卷信息")
    public CommonResult<?> update(@RequestBody TestPaper testPaper){
        return CommonResult.success(testPaperService.update(testPaper));
    }


    @GetMapping("/remove")
    @ApiOperation(value = "根据ID删除试卷")
    public CommonResult<?> remove(String paperId){
        return CommonResult.success(testPaperService.remove(paperId));
    }
}

