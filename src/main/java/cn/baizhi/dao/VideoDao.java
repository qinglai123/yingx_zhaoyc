package cn.baizhi.dao;

import cn.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    //分页查所有 视频
    List<Video> selectByPage(@Param("start") int start, @Param("end") int end);
    //查总条数
    int selectCount();
    //添加视频
    void  insert(Video video);
    //删除视频
    void delete(String id);
    //查一个视频
    Video selectById(String id);

}
