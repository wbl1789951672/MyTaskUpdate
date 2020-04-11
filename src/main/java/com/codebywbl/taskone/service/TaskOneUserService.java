package com.codebywbl.taskone.service;/*
    @author:bl
    @date:2020/04/01 21:32
    @description:
*/

import com.codebywbl.taskone.DaoImpl.TaskOneUserImp;
import com.codebywbl.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskOneUserService {
    @Autowired
    private TaskOneUserImp userImp;

    public User insertUser(User user) {
        return userImp.insertUser(user);
    }
    public User findUserById(String id) {
        return userImp.findUserById(id);
    }
    public int updateUser(User user) {
        return userImp.updateUser(user);
    }

    public List<User> findUserByName(String name){
        return userImp.findUserByName(name);
    }

    public List<User> findUserByAge(Integer age1,Integer age2){
        return userImp.findUserByAge(age1,age2);
    }

    public List<User> findUserByHobby(String hobby){
        return userImp.findUserByHobby(hobby);
    }

    public List<User> findUserByDescription(String name, String val){
        return userImp.findUserByDescription(name,val);
    }
}
