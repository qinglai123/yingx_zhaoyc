package cn.baizhi.service;


import cn.baizhi.entity.User;


import java.util.List;
import java.util.Map;

public interface UserService {
    //分页业务
    Map<String,Object> queryByPage(int page,int size);
    //修改状态
    void updateStatus(User user);
    //添加用户
    void add(User user);
    //根据id删除用户
    void deleteById(String id);
    //查询所有用户并导出
    void exportUser();
    ////查询女生和男生人数和月份 用户分析
    Map<String,Object> queryCount();


}
