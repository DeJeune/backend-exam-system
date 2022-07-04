package com.sys.exam.controller;

import cn.hutool.json.JSONUtil;
import com.sys.exam.pojo.AnswerPaper;
import com.sys.exam.pojo.TestPaper;
import com.sys.exam.pojo.UserDto;
import com.sys.exam.service.AnswerPaperService;
import com.sys.exam.service.TestPaperService;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.service.UserPaperService;
import com.sys.exam.utils.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tri
 * @Date 2022/1/9
 * @Description //TODO
 */
@RequestMapping("/user/distinguish-paper")
@Api(tags="切换试卷提交状态列表控制器")
@CrossOrigin
@RestController
public class DistinguishPaperController {

    @Autowired
    private AnswerPaperService answerPaperService;

    @Autowired
    private TestPaperService testPaperService;

    @Autowired
    private UserDtoService userDtoService;

    @GetMapping("/distinguish")
    public CommonResult<?> distinguish(String flag, String username){

        String userId = userDtoService.selectByUsername(username).getUserId();

        List<AnswerPaper> answerPaperList = answerPaperService.selectByUserId(userId);
        List<String> idList = new ArrayList<>();
        List<Integer> pointList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<String> userIdList = new ArrayList<>();
        answerPaperList.forEach(answerPaper -> {
            idList.add(answerPaper.getPaperId());
            pointList.add(answerPaper.getPoints());
        });

        if(flag.equals("1")){
            // 用户已经提交了的试卷，在answer_paper
            List<TestPaper> testPaperList = testPaperService.selectList2(idList);
            testPaperList.forEach(testPaper -> {
                list.add(testPaperService.sum(testPaper.getPaperId()));
                userIdList.add(testPaper.getUserId());
            });
            List<String> nameList = userDtoService.selectByIdList(userIdList);
            Map<String, Object> map = new HashMap<>();
            map.put("name", nameList);
            map.put("point", pointList);
            map.put("sum", list);
            map.put("data", testPaperList);
            return CommonResult.success(JSONUtil.parseObj(map));
        }else {
            // 用户还未提交的试卷
            List<TestPaper> testPaperList = testPaperService.selectList(idList);
            testPaperList.forEach(testPaper -> {
                list.add(testPaperService.sum(testPaper.getPaperId()));
                userIdList.add(testPaper.getUserId());
            });
            List<String> nameList = userDtoService.selectByIdList(userIdList);
            Map<String, Object> map = new HashMap<>();
            map.put("name", nameList);
            map.put("sum", list);
            map.put("data", testPaperList);
            return CommonResult.success(JSONUtil.parseObj(map));
        }
    }
}
