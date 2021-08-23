package cn.baizhi.test;


import cn.baizhi.dao.AdminDao;
import cn.baizhi.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testDao {
    @Autowired
    private AdminDao adminDao;
    @Test
    public void test(){
        Admin admin = adminDao.selectByName("zyc");
        System.out.println(admin);
    }
}
