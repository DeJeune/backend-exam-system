package com.sys.exam.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sys.exam.pojo.Role;
import com.sys.exam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    RoleService roleService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println(requestUrl);
        System.out.println(antPathMatcher.match("/**", requestUrl));
        if ("/login".equals(requestUrl)) {
            return null;
        }
        List<Role> roleList = roleService.getAllRoles();
        for (Role role: roleList) {
            if (antPathMatcher.match(role.getUrl(), requestUrl)) {
                return SecurityConfig.createList(role.getRoleName());
            }
        }
        // 没有匹配上的资源
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
