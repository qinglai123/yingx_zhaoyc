package cn.baizhi.service;

import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.CategoryDao;
import cn.baizhi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService  {
    //注入dao
    @Autowired
    private CategoryDao categoryDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //根据级别 查询类别 业务
    public List<Category> queryByLevels(int levels) {

        return categoryDao.selectByLevels(levels);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //根据1级类别id查询2级类别 业务
    public List<Category> queryByParentId(String id) {
        return categoryDao.selectByParentId(id);
    }


    // 添加类别
    @DeleteCache
    @Override
    public void save(Category category) {
        category.setId(UUID.randomUUID().toString());
        categoryDao.add(category);
    }


    //根据id删除类别
    @Override
    @DeleteCache
    public void deleteById(String id) {
        categoryDao.dropById(id);
    }

    //删除1级类别
    @DeleteCache
    @Override
    public Map<String, Object> delete(String id) {
        Map<String,Object> map = new HashMap<>();
        List<Category> list = categoryDao.selectByParentId(id);
        if (list.isEmpty()){
            map.put("flag", true);
            categoryDao.dropById(id);

            map.put("msg", "删除成功");
        }else {
            map.put("msg", "有内容,不可删除");
        }



        return map;
    }

    //修改二级类别
    @Override
    @DeleteCache
    public void update(Category category) {
        categoryDao.update(category);
    }
}
