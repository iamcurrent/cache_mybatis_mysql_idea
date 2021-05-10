package com.example.demo.services;

import com.example.demo.Interface.SqlController;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlServer {
    @Autowired
    SqlController sqlController;
    //创建缓存，当再次调用方法的时候，去缓存中获取
    @Cacheable(cacheNames = "query_user")
    public User getUserByName(String name){
        System.out.println("queryOk");
        return sqlController.queryByName(name);
    }

    //查询所有的用户
    @Cacheable(cacheNames = "all_user")
    public List<User> getAllUser(){
        System.out.println("queryAll");
        return sqlController.queryAll();
    }

    //更新缓存
    @CachePut(value = "query_user")
    public void updateUser(User user){
        System.out.println("update");
        sqlController.update(user);
    }

    //删除缓存
    @CacheEvict(value = "query_user")
    public void deleteUser(String name){
        System.out.println("delete");
        sqlController.deleteByName(name);
    }
    //插入相应的用户
    public void insertUser(User user){
        System.out.println("insert");
        sqlController.insertUser(user);
    }

}
