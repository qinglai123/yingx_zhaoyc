package cn.baizhi.controller;

import cn.baizhi.entity.Category;
import cn.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {
    //注入业务
    @Autowired
    private CategoryService categoryService;

    //根据级别 查询类别
    @RequestMapping("queryByLevels")
    public List<Category> queryByLevels(int levels){
            //调业务并返回 list
        return categoryService.queryByLevels(levels);
    }
    //根据1级类别id查询2级类别

    @RequestMapping("queryByParentId")
    public List<Category> queryByParentId(String id){
        //调业务 并返回list
       return categoryService.queryByParentId(id);
    }
    //添加类别
    @RequestMapping("save")
    public void save( @RequestBody Category category){
        //调业务 完成添加
        categoryService.save(category);
    }
    //根据id删除二级类别
    @RequestMapping("deleteById")
    public void deleteById(String id){
        //调用业务 完成删除
        categoryService.deleteById(id);
    }
    //删除一级类别
    @RequestMapping("delete")
    public Map<String, Object> delete(String id){
       return categoryService.delete(id);
    }

    //修改二级类别
    @RequestMapping("update")
    public void update(@RequestBody Category category){
        //调业务完成修改
        categoryService.update(category);
    }




}
