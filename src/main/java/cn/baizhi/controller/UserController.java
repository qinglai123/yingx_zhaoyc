package cn.baizhi.controller;


import cn.baizhi.entity.User;
import cn.baizhi.service.UserService;
import cn.baizhi.util.Feil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    //注入业务
    @Autowired
    private UserService userService;

    //分页查
    @RequestMapping("queryByPage")
    public  Map<String, Object> queryByPage(int page){
        //表示一页显示3条
        int size = 3;
        //调用业务
         return userService.queryByPage(page, size);

    }

    //修改状态
    @RequestMapping("updateStatus")
    public void updateStatus(@RequestBody User user){
        System.out.println(user);
        userService.updateStatus(user);

    }
    //添加用户
    @RequestMapping("add")
    public void add(MultipartFile photo,String username,String phone,String brief){
     log.debug(photo.getName());
     log.debug(photo.getOriginalFilename());

        User user = new User(null, username, phone, "http://qinglai123.oss-cn-beijing.aliyuncs.com/"+photo.getOriginalFilename(), brief, null, new Date(), 0);
        userService.add(user);
        Feil.uploadFeil(photo);
    }
    //根据id删除用户
    @RequestMapping("deleteById")
    public void deleteById(String id,String headimg){

        System.out.println(id);
        System.out.println(headimg);
        //字符串从46开始截取
        String s = headimg.substring(46);
        //删除图片
        Feil.deleteFile(s);
        //调业务完成删除
        userService.deleteById(id);

    }
    //导出
    @RequestMapping("export")
    public void export(){
        //查出所有用户信息
        userService.exportUser();

    }
    //Echars 用户注册 分析
    @RequestMapping("registCount")
    public Map<String, Object> registCount(){
        //System.out.println("执行了");
        //Arrays.asList  //该方法相当于创建list并添加数据
//        List<Integer> manCount = Arrays.asList(350, 300, 480, 200, 250, 400);
//        List<Integer> womanCount = Arrays.asList(350, 300, 480, 200, 250, 400);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("data", data);
//        map.put("manCount", manCount);
//        map.put("womanCount", womanCount);

        //调业务
        return userService.queryCount();


    }



}
