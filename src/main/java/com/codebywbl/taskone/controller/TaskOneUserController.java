package com.codebywbl.taskone.controller;/*
    @author:bl
    @date:2020/04/01 21:35
    @description:
*/

import com.codebywbl.bean.Description;
import com.codebywbl.bean.Msg;
import com.codebywbl.bean.User;
import com.codebywbl.taskone.service.TaskOneUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
@Api(tags = "员工管理")
@RestController
public class TaskOneUserController {
    @Autowired
    private TaskOneUserService taskOneUserService;

    @ApiOperation("添加用户")
    @ApiImplicitParam(name = "user",value = "要提交到数据库的用户信息")
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public Msg insertUser(User user, Description description) {
        user.setDescription(description);
        if (taskOneUserService.insertUser(user) != null){
            return Msg.success().add(user);
        }
        return Msg.fail();
    }

    //根据id查找数据
    @ApiOperation("根据用户id查询用户")
    @ApiImplicitParam(name = "id",value = "要查找的用户的id")
    @RequestMapping(value = "/findUserById",method = RequestMethod.GET)
    public Msg findUserById(@RequestParam(value = "id") String id) {
        User user = taskOneUserService.findUserById(id);
        if(user != null){
            return Msg.success().add(user);
        }
        return Msg.fail();
    }

    //根据用户id，去更改数据库中id为对应值数据
    @ApiOperation("根据用户id更新用户")
    @ApiImplicitParam(name = "user",value = "获取前台提交的数据，自动映射user中",dataType = "String")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Msg updateUser(User user, Description description) {
        user.setDescription(description);
        if (taskOneUserService.updateUser(user) > 0){
            return Msg.success();
        }
        return Msg.fail();
    }

    //根据name查找数据
    @ApiOperation("根据名字查找用户")
    @ApiImplicitParam(name = "name",value = "获取前台提交的数据，根据数据中的name找到用户",dataType = "String")
    @RequestMapping(value = "/findUserByName",method = RequestMethod.GET)
    public Msg findUserByName(@RequestParam("name") String name){
        List<User> users = taskOneUserService.findUserByName(name);
        if (users.size() > 0){
            return Msg.success().setExtend(users);
        }
        return Msg.fail();
    }

    //根据年龄去查询，url中接受两个参数age1，age2，会根据两个数，查询出大于小的数并且小于大的数的用户集合
    @ApiOperation("根据年龄范围查找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "age1",value = "age范围的其中一个参数",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "age2",value = "age范围的令一个参数",required = true,dataType = "Integer")})
    @RequestMapping(value = "/findUserByAge",method = RequestMethod.GET)
    public Msg findUserByAge(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2){
        List<User> users = taskOneUserService.findUserByAge(age1 > age2 ? age2 : age1, age2 > age1 ? age2 : age1);
        if (users.size() > 0)
            return Msg.success().setExtend(users);
        return Msg.fail();
    }

    //根据hobby中的值，去数据库查询对应的数据
    @ApiOperation("根据爱好查找用户")
    @ApiImplicitParam(name = "hobby",value = "根据数据中的hobby找到用户",dataType = "String")
    @RequestMapping(value = "/findUserByHobby",method = RequestMethod.GET)
    public Msg findUserByHobby(@RequestParam("hobby") String hobby){
        List<User> users = taskOneUserService.findUserByHobby(hobby);
        if (users.size() > 0){
            return Msg.success().setExtend(users);
        }
        return Msg.fail();
    }

    //根据用户输入的数据，去数据库中查询description的子数据中是否有对应的值
    @ApiOperation("根据description查找用户")
    @ApiImplicitParam(name = "request",value = "获取前台提交的数据，根据数据中的description中的数据查找用户",dataType = "HttpServletRequest")
    @RequestMapping(value = "/description",method = RequestMethod.GET)
    public List<User> findUserByDescription(HttpServletRequest request){
        String name = null;
        Enumeration<String> names = request.getParameterNames();
        if (names.hasMoreElements()){
            name = names.nextElement();
        }
        String val = request.getParameter(name);
        return taskOneUserService.findUserByDescription(name,val);
    }

    //处理未经映射的请求
    @RequestMapping("/*")
    public Msg other(){
        return Msg.fail().setCodeStatus("未经映射的请求！");
    }
}
