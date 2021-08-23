package cn.baizhi.dao;

import cn.baizhi.entity.Category;

import java.util.List;


public interface CategoryDao {
    //根据级别 查询类别
    List<Category> selectByLevels(int levels);
   //根据1级类别id查询2级类别
    List<Category> selectByParentId (String id);
    //根据父项添加2级类别   添加类别
    void add (Category category);
    //根据id删除类别
    void dropById(String id);
    //修改二级类别
    void update(Category category);

}
