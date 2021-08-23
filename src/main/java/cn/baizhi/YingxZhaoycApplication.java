package cn.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//创建所有dao
@MapperScan("cn.baizhi.dao")
@SpringBootApplication
public class YingxZhaoycApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxZhaoycApplication.class, args);
    }

}
