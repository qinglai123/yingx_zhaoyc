package cn.baizhi.controller;


import cn.baizhi.entity.Admin;
import cn.baizhi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("admin") //访问路径
@RestController   //创建对象 响应json
@CrossOrigin    //解决跨域
public class AdminController {
    //定义日志对象
    private Logger log = LoggerFactory.getLogger(AdminController.class);

    //注入业务
    @Autowired
    private AdminService adminService;
    //创建map集合
    Map<String,Object> map = new HashMap<>();
    //登录
    @RequestMapping("login")
    public Map<String,Object> login(@RequestBody Admin admin) {
        log.debug(admin.getUsername());
        log.debug(admin.getPassword());

        //调业务
        Admin selectAdmin = adminService.queryByName(admin.getUsername());
        map.put("flag", false);

        //如果查出来的用户不为空 进行判断密码
        if (selectAdmin!=null) {
            //判断密码
            if (selectAdmin.getPassword().equals(admin.getPassword())) {
                    //密码正确 覆盖false
                map.put("flag", true);
                //将对象存到集合中
                //map.put("selectAdmin", selectAdmin);
            }else {
                //密码错误 响应信息
                map.put("msg", "密码错误");
            }
        }else {
            //说明用户为空 响应信息用户名不存在
            map.put("msg", "用户名不存在");
        }
        return map;
    }
}
