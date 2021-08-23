package cn.baizhi;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.dao.UserDao;
import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Admin;
import cn.baizhi.entity.User;
import cn.baizhi.entity.Video;
import cn.baizhi.util.Feil;
import cn.baizhi.vo.MonthAndCount;
import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class YingxZhaoycApplicationTests {
 @Autowired
  private  AdminDao adminDao;
 @Autowired
 private UserDao userDao;
 @Autowired
 private VideoDao videoDao;

    @Test
    void contextLoads() {
        Admin admin = adminDao.selectByName("zyc");
        System.out.println(admin);


    }
    @Test
    public void test1(){
        List<User> list = userDao.queyRange(0, 3);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void test5(){
        String s = "http://qinglai123.oss-cn-beijing.aliyuncs.com/";
        int length = s.length();
        System.out.println(length);
    }
    @Test
    public void test6(){
        List<Video> list = videoDao.selectByPage(0, 1);
        for (Video video : list) {
            System.out.println(video);
        }
    }
    @Test
    public void list1(){
        ArrayList<Object> list = new ArrayList<>();
        list.add("小明，小红，小强");
        //list转数组
        Object[] objects = list.toArray();
        System.out.println(objects);
        for (Object object : objects) {
            System.out.println(object);
        }
    }
    @Test
    public void array(){
        int[] c = {1,2,3};
        for (int i : c) {
            System.out.println(i);
        }

    }
    @Test
    public void uu(){
        String s = UUID.randomUUID().toString();
        String s1 = s.replace("-", "");
        System.out.println("id是   "+s1);
    }
//    @Test
//    public void test13(){
//        List<MonthAndCount> monthAndCounts = userDao.selectMan();
//        for (MonthAndCount monthAndCount : monthAndCounts) {
//            System.out.println(monthAndCount);
//        }
//    }



}
