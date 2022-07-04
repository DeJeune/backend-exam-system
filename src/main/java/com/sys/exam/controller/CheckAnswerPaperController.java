package com.sys.exam.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.sys.exam.pojo.AnswerDetail;
import com.sys.exam.pojo.AnswerPaper;
import com.sys.exam.pojo.UserPaper;
import com.sys.exam.service.*;
import com.sys.exam.utils.CommonResult;
import com.sys.exam.vo.AnswerPaperVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tri
 * @Date 2022/1/9
 * @Description //TODO
 */
@RequestMapping("/user/check-answer-paper")
@Api(tags="提交试卷控制器")
@CrossOrigin
@RestController
public class CheckAnswerPaperController {

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private QuestionStemService questionStemService;

    @Autowired
    private AnswerDetailService answerDetailService;

    @Autowired
    private AnswerPaperService answerPaperService;

    @Autowired
    private UserPaperService userPaperService;

    @PostMapping(value="/check-point", produces="application/json")
    public CommonResult<?> checkPoints(@RequestBody AnswerPaperVo answerPaperVo) {
        System.out.println(answerPaperVo);
        List<AnswerDetail> answerDetailList = answerPaperVo.getAnswerDetailList();
        AnswerPaper answerPaper = answerPaperVo.getAnswerPaper();
        answerPaper.setAnswerId(IdUtil.simpleUUID());
        AtomicInteger point = new AtomicInteger();
        answerDetailList.forEach(answerDetail -> {
            answerDetail.setAnswerId(answerPaper.getAnswerId());
            if(optionsService.selectById(answerDetail.getOptionId()).getIsCorrect()==1){
                point.addAndGet(questionStemService.selectById(answerDetail.getStemId()).getPoints());
                answerDetail.setIsOk(1);
            } else {
                answerDetail.setIsOk(0);
            }
        });
        answerPaper.setPoints(point.get());
        UserPaper userPaper = new UserPaper();
        userPaper.setUserId(answerPaper.getUserId());
        userPaper.setPaperId(answerPaper.getPaperId());
        userPaper.setStartTime(answerPaper.getStartTime());
        userPaper.setFinishTime(answerPaper.getSubmitTime());
        userPaper.setStatus(1);
        return CommonResult.success(userPaperService.save(userPaper) && answerDetailService.saveBatch(answerDetailList) && answerPaperService.save(answerPaper));
    }
}
