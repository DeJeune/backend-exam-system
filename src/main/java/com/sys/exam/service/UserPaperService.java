package com.sys.exam.service;

import com.sys.exam.pojo.UserPaper;

import java.util.List;

/**
 * @author suyao
 * @date 1/6/2022
 */
public interface UserPaperService {

    UserPaper selectByPaperId(String paperId);

    boolean save(UserPaper userPaper);

    int checkStatus(String paperId);

}
