package com.sys.exam.utils;

import com.sys.exam.vo.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author suyao
 * @date 1/7/2022
 */
public class UserUtils {
    public static User getCurrentUser() {
        return (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
