package com.example.demo.controllers;

import com.example.demo.Interface.SqlController;
import com.example.demo.entity.User;
import com.example.demo.services.SqlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;


@RestController
@SuppressWarnings("all")
public class VisitController {

    @Autowired
    SqlServer sqlServer;

    @RequestMapping(value = "/sql",method = RequestMethod.GET)
    public String getSqlResult(){
        //获取指定的用户，这里可以在网页中实现相应的功能
        User user = sqlServer.getUserByName("xixida");
        if (user==null){
            return "fail";
        }
        return user.getAddress()+user.getEmail()+user.getName()+user.getPassword();
    }

    @RequestMapping(value = "/sql/all",method = RequestMethod.GET)
    public String getAllUser(){
        //查询所用用户并返回
        List<User> list = sqlServer.getAllUser();
        StringBuilder builder = new StringBuilder();
        for(User user:list){
            builder.append(user.getAddress());
            builder.append(user.getPassword());
            builder.append(user.getEmail());
            builder.append(user.getName());
        }

        return builder.toString();
    }

    //删除指定的用户
    @RequestMapping(value = "/sql",method = RequestMethod.DELETE)
    public String deleteUser(){
        sqlServer.deleteUser("xixida");
        return "delete over";
    }

    //从网页提交的表格中获取用户信息并加入数据库
    @RequestMapping(value = "sql",method = RequestMethod.POST)
    public String insertUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        User user = new User(username,password,address,email);

        sqlServer.insertUser(user);
        return "insert over";
    }

    //更新指定的用户
    @RequestMapping(value = "/sql",method = RequestMethod.PUT)
    public String updateUser(){
        User user = new User("xixida","","","3306@qq.com");
        sqlServer.updateUser(user);

        return "update over";
    }



}
