package cn.baizhi.controller;

import cn.baizhi.entity.Category;
import cn.baizhi.entity.Video;
import cn.baizhi.service.VideoService;
import cn.baizhi.util.Feil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.Map;

@CrossOrigin
@RequestMapping("video")
@RestController
public class VideoController {
    private Logger log = LoggerFactory.getLogger(VideoController.class);

    //注入service
    @Autowired
    private VideoService videoService;
    //分页查
    @RequestMapping("queryByPage")
    public Map<String, Object> queryByPage(int page){
        //表示一页显示2条
        int size = 2;
        //调用service
        return  videoService.queryByPage(page, size);
    }
    @RequestMapping("add")
    public void add(MultipartFile videos,String title,String id, String brief){

        String coverPath = "http://qinglai123.oss-cn-beijing.aliyuncs.com/video/"+videos.getOriginalFilename().replace(".mp4", ".jpg");
        String videoPath="http://qinglai123.oss-cn-beijing.aliyuncs.com/video/"+videos.getOriginalFilename();
        Category category = new Category();
        category.setId(id);
        Video video = new Video(null, title, brief, coverPath, videoPath, new Date(), category, null, null);
        //调用service 完成添加
        videoService.add(video, videos);

//        log.debug(videos.getOriginalFilename());
//        log.debug(title);
//        log.debug(id);
//        log.debug(brief);

    }
    //删除视频
    @RequestMapping("deleteById")
    public void deleteById(String id,String videos){
        //字符串从46开始截取
        String s = videos.substring(46);
        String s1 = s.replace(".mp4", ".jpg");
        //删除图片
        Feil.deleteFile(s);
        Feil.deleteFile(s1);
        log.debug(s+"      "  +s1);
        videoService.delete(id);
       // log.debug(videos);

    }
    //查一个视频

    @RequestMapping("queryById")
    public Video queryById(String id){
        //调业务完成查询
        return videoService.queryById(id);
    }


}
