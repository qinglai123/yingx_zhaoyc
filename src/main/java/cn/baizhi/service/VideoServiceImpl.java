package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService  {
    //注入dao
    @Autowired
    private VideoDao videoDao;


    //分页查业务
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override                               //页数  //条数
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String,Object> map = new HashMap<>();
        //调用dao分页查
        List<Video> list = videoDao.selectByPage((page - 1) * size, size);
        //调用dao查询总条数
        int count = videoDao.selectCount();
        int a = 0;
        if(count % size ==0){
            a= count/size;
        }else{
            a= count/size+1;
        }

        map.put("data", list);//查出来的数据
        map.put("page", page);//当前页
        map.put("counts", a);//总页数


        return map;
    }

    //添加视频的业务
    @Override
    @DeleteCache
    public void add(Video video ,MultipartFile videos) {
       //id自动生成
        video.setId(UUID.randomUUID().toString());
        //调用工具类上传云视频
        cn.baizhi.util.Video.downloadVideo(videos);
        videoDao.insert(video);
        //调用工具类剪切
        cn.baizhi.util.Video.shearVideo(videos);
    }
//删除视频业务
    @Override
    @DeleteCache
    public void delete(String id) {
        videoDao.delete(id);
    }


    //查一个业务
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Video queryById(String id) {
        return videoDao.selectById(id);
    }


}
