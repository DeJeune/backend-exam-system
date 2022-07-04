package com.sys.exam.controller;

import cn.hutool.json.JSONUtil;
import com.sys.exam.pojo.AnswerPaper;
import com.sys.exam.service.AnswerPaperService;
import com.sys.exam.service.TestPaperService;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.utils.CommonResult;
import com.sys.exam.utils.UserUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tri
 * @Date 2022/1/7
 * @Description //TODO
 */
@RestController
@RequestMapping("/admin/answerPaper")
@Api(tags="答卷控制器")
@CrossOrigin
public class AnswerPaperController {

    @Resource
    private AnswerPaperService answerPaperService;

    @Autowired
    private UserDtoService userService;

    @Autowired
    private TestPaperService testPaperService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页起始数据",example = "1",required = true,dataType = "integer"),
            @ApiImplicitParam(name = "size",value = "每页显示最大记录数",example = "10",required = true,dataType = "integer"),
            @ApiImplicitParam(name = "paperName",value = "根据试卷名查询",example = "第一章测试",dataType = "String")
    })
    @GetMapping(value = "/listPage", produces = {"application/json;charset=utf-8"})
    @ApiOperation(value = "分页查询学生答卷信息")
    public CommonResult<?> listPage(int page, int size, String paperName){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        List<AnswerPaper> answerPaperList = answerPaperService.listPage(page, size, paperName);
        List<String> paperNameList = new ArrayList<>();
        List<String> username = new ArrayList<>();
        List<String> rate = new ArrayList<>();
        answerPaperList.forEach(answerPaper -> {
            username.add(userService.selectById(answerPaper.getUserId()).getUsername());
            paperNameList.add(testPaperService.selectById(answerPaper.getPaperId()).getName());
            rate.add(answerPaperService.rate(answerPaper.getPaperId(),answerPaper.getAnswerId()));
        });
        map2.put("name", username);
        map2.put("paperName", paperNameList);
        map2.put("data", answerPaperList);
        map2.put("rate",rate);
        map.put("count",answerPaperService.count(paperName));
        map.put("data",map2);
        return CommonResult.success(JSONUtil.parseObj(map));
    }


    @PostMapping("/remove")
    @ApiOperation(value = "删除考试成绩")
    @ApiParam(value="答卷编号")
    public CommonResult<?> remove(String answerId) {
        return CommonResult.success(answerPaperService.remove(answerId));
    }


}
