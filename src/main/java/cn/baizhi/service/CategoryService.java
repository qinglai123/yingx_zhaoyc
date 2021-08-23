package cn.baizhi.service;

import cn.baizhi.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    //根据级别 查询类别 业务
    List<Category> queryByLevels(int levels);
    //根据1级类别id查询2级类别 业务
    List<Category> queryByParentId (String id);
    // 添加类别
    void save (Category category);
    //根据id删除二级类别
    void deleteById(String id);
    //删除1级类别
    Map<String,Object> delete(String id);
    //修改二级类别
    void update(Category category);
}
