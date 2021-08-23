package cn.baizhi.dao;

import cn.baizhi.entity.User;
import cn.baizhi.vo.MonthAndCount;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserDao {
    //范围查询
    List<User> queyRange(@Param("start") int start, @Param("end") int end);
    //查总条数
    int selectCount();
    //根据id修改状态
    void updateStatus(User user);
    //添加用户
    void insert(User user);
    //根据id删除
    void deleteById(String id);
    //查所有用户并导出
    List<User> findAll();
    //查男生人数和月份
    List<MonthAndCount> selectMan(String sex);

}
