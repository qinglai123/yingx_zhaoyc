package cn.baizhi.service;

import cn.baizhi.entity.Admin;



public interface AdminService {

    //登录业务
    Admin queryByName(String name);

}
