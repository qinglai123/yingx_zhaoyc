package cn.baizhi.dao;

import cn.baizhi.entity.Admin;

public interface AdminDao {
    //登录 根据名字查一个
    Admin selectByName(String name);
}
