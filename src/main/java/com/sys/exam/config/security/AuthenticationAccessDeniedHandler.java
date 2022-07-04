package com.sys.exam.config.security;

import com.sys.exam.utils.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String json = "{\"status\":\"403\",\"msg\":\"拒绝访问\"}";
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(json);

    }
}
