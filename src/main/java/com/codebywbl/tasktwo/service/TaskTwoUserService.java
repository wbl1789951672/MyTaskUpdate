package com.codebywbl.tasktwo.service;/*
    @author:bl
    @date:2020/04/02 17:03
    @description:
*/

import com.codebywbl.tasktwo.daoImpl.TaskTwoUserImp;
import com.codebywbl.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTwoUserService {
    @Autowired
    private TaskTwoUserImp task2Imp;

    public List<User> index(){
        return task2Imp.findAll();
    }

    public User saveUser(User user) {
        return task2Imp.saveUser(user);
    }

    public User getUser(String id) {
        return task2Imp.findUserById(id);
    }

    public void update(User user) {
        task2Imp.updateUser(user);
    }

    //专门处理age的条件查询
    public List<User> check(String val) {
        return task2Imp.check(val);
    }

    //专门处理字符串数据
    public List<User> check(String tj, String val) {
        return task2Imp.check(tj,val);
    }

}
