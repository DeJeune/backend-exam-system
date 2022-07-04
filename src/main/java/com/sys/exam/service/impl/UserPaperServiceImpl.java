package com.sys.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sys.exam.mapper.UserPaperMapper;
import com.sys.exam.pojo.UserPaper;
import com.sys.exam.service.UserPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tri
 * @Date 2022/1/9
 * @Description //TODO
 */

@Service
public class UserPaperServiceImpl implements UserPaperService {

    @Autowired
    private UserPaperMapper userPaperMapper;

    @Override
    public UserPaper selectByPaperId(String paperId) {
        return userPaperMapper.selectOne(new QueryWrapper<UserPaper>().eq("paper_id", paperId));
    }

    @Override
    public boolean save(UserPaper userPaper) {
        return userPaperMapper.insert(userPaper)==1;
    }

    @Override
    public int checkStatus(String paperId){
        List<UserPaper> userPaperLists = userPaperMapper.selectList(new QueryWrapper<UserPaper>().eq("paper_id", paperId));
        for (UserPaper userPaper: userPaperLists) {
            if(userPaper.getStatus()==0){
                return 1;
            }
        }
        return 0;
    }

}
