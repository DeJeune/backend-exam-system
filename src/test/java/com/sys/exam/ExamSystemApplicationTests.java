package com.sys.exam;


import cn.hutool.json.JSONUtil;
import com.sys.exam.pojo.AnswerDetail;
import com.sys.exam.pojo.AnswerPaper;
import com.sys.exam.service.UserDtoService;
import com.sys.exam.vo.AnswerPaperVo;
import com.sys.exam.vo.User;
import com.sys.exam.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ExamSystemApplicationTests {

    @Autowired
    UserDtoService userService;

    @Test
    void contextLoads() {
        AnswerPaperVo answerPaperVo = new AnswerPaperVo();
        List<AnswerDetail> answerPaperList = new ArrayList<>();
        AnswerPaper answerPaper = new AnswerPaper();
    }

    @Test
    void test() {
        System.out.println(userService.remove("12"));
    }

}
