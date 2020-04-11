package com.codebywbl.tasktwo.controller;/*
    @author:bl
    @date:2020/04/02 16:59
    @description:
*/

import com.codebywbl.bean.Description;
import com.codebywbl.bean.Msg;
import com.codebywbl.bean.User;
import com.codebywbl.tasktwo.service.TaskTwoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TaskTwoUserController {
    @Autowired
    private TaskTwoUserService taskTwoUserService;

    /*Task2*/

    //在index.ftl页面加载完成之后，通过ajax发送请求，获取信息
    @RequestMapping("/index1")
    @ResponseBody
    public List<User> index1(){
        List<User> users = taskTwoUserService.index();
        return users;
    }

    //先访问index2请求，转发到index页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //处理模态框(员工插入)
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveUser(HttpServletRequest request,User user,Description description){
        user.setHobby(request.getParameter("hobby").split(";"));  //设置爱好
        user.setDescription(description);
        if ((user = taskTwoUserService.saveUser(user)) != null){
            return Msg.success().add(user);
        }
        return Msg.fail();
    }

    //先查询到user对象
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User user(@PathVariable("id") String id){
        return taskTwoUserService.getUser(id);
    }

    //用户更新
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(@PathVariable("id")String id, User user, HttpServletRequest request, Description description){
        user.setId(id); //设置id
        user.setHobby(request.getParameter("hobby").split(";"));  //设置爱好
        user.setDescription(description);
        taskTwoUserService.update(user);
    }

    //根据输入条件查询数据
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    @ResponseBody
    public List<User> check(HttpServletRequest request){
        //获得条件
        String tj = request.getParameter("tj");
        //获得条件对应的值
        String val = request.getParameter("val");
        //如果条件是age，那么进行针对age查询
        if ("age".equals(tj)){
            return taskTwoUserService.check(val);
        }else if (val.equals("")){
            //如果条件值为空，那么就查询全部
            return taskTwoUserService.index();
        } else{
            //否则，按条件和值进行查询
            return taskTwoUserService.check(tj,val);
        }
    }
}
