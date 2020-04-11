package com.codebywbl.bean;/*
    @author:bl
    @date:2020/04/10 21:09
    @description:
*/

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Msg {
    //状态码   200-成功    400-失败
    private int code;

    //提示信息
    private String codeStatus;

    //用户要返回给浏览器的数据
    private List<User> extend = new ArrayList<>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        result.setCodeStatus("Success！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(400);
        result.setCodeStatus("failed！");
        return result;
    }

    public Msg add(User user) {
        this.extend.add(user);
        return this;
    }
}
