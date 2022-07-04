package com.sys.exam.controller;

import com.sys.exam.service.impl.UserService;
import com.sys.exam.utils.CommonResult;
import com.sys.exam.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author suyao
 * @date 1/6/2022
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public CommonResult<?> login() {
        return CommonResult.failed("尚未登录，请登录");
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }


}
