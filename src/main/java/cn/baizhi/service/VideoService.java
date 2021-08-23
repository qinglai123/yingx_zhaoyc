package cn.baizhi.service;

import cn.baizhi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface VideoService {
    //分页业务
    Map<String,Object> queryByPage(int page, int size);
    //添加视频
    void add(Video video, MultipartFile file);
    //删除视频
    void delete(String id);
    //查一个业务
     Video queryById(String id);
}
