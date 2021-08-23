package cn.baizhi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoEasy {
    @Test
    public void testGoEasy(){
            //向频道发消息
        io.goeasy.GoEasy goEasy = new io.goeasy.GoEasy("https://rest-hangzhou.goeasy.io","BC-a1cad2a3b61c40e7ad42cf9dd151cf15");
        goEasy.publish("my_channel", "hello goeasy  6666");

    }



}
