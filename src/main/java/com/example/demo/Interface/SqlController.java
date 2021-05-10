package com.example.demo.Interface;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SqlController {
    public List<User> queryAll(); //查询所有用户
    public User queryByName(String name); //通过名字进行查询
    public void deleteByName(String name); //通过名字进行删除
    public void insertUser(User user); //增减用户
    public void update(User user);//修改相应的用户邮件
}
