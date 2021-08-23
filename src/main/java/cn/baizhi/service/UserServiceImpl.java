package cn.baizhi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.baizhi.annotation.DeleteCache;
import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;

import cn.baizhi.util.Video;
import cn.baizhi.vo.MonthAndCount;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    //注入dao
    @Autowired
    private UserDao userDao;

    //分页查业务
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override                               //页数     //条数
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String,Object> map = new HashMap<>();
        //调用dao 范围查询
        List<User> list = userDao.queyRange((page - 1) * size, size);
        //调用dao 查总条数
        int count = userDao.selectCount();

        int a = 0;
        if(count % size ==0){
            a= count/size;
        }else{
            a= count/size+1;
        }


        map.put("data", list);
        map.put("page", page);
        map.put("counts", a);

        return map;
    }

    //修改状态
    @Override
    @DeleteCache
    public void updateStatus(User user) {
        if(user.getStatus()==1){
            user.setStatus(0);
        }else {
            user.setStatus(1);
        }
        userDao.updateStatus(user);
    }


    //添加用户
    @Override
    @DeleteCache
    public void add(User user) {
        //调用dao
        user.setId(UUID.randomUUID().toString());
        userDao.insert(user);
    }

    //根据id删除用户
    @Override
    @DeleteCache
    public void deleteById(String id) {
        //调用dao
        userDao.deleteById(id);
    }

    //查询所有用户 并导出
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void exportUser() {
        //调Dao
        List<User> users = userDao.findAll();
        for (User user : users) {
            String headimg = user.getHeadimg();
            String headimg1 = headimg.substring(headimg.lastIndexOf("/")+1);
            System.out.println(headimg1);
            String upload = Video.upload(headimg1);
            user.setHeadimg(upload);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "用户"), User.class, users);
        //指定位置
        try {
            workbook.write(new FileOutputStream("F:\\exportUsers.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //查询女生和男生人数和月份 用户分析
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryCount() {
        Map<String,Object> map = new HashMap<>();
        //调dao方法 查询男生人数和月份
        List<MonthAndCount> manCount1 = userDao.selectMan("男");
        List<MonthAndCount> womanCount1 = userDao.selectMan("女");


        int[] aa = new int[12];
        for (int i = 1;i<13;i++){
            for (MonthAndCount man : manCount1) {
                if(i==man.getMonth()){
                    aa[i-1]=man.getCount();
                    break;
                }else {
                    aa[i-1]=0;
                }

            }
        }

        int[] bb = new int[12];
        for (int k=1;k<13;k++){
            for (MonthAndCount woman : womanCount1) {
                if (k==woman.getMonth()){
                    bb[k-1]=woman.getCount();
                    break;
                }else {
                    bb[k-1]=0;
                }
            }
        }

        List<String> data = Arrays.asList("1月", "2月", "3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
        List<int[]> manCount = Arrays.asList(aa);//男生
        List<int[]> womanCount = Arrays.asList(bb);//女生
        map.put("data", data);
        map.put("manCount", manCount);
        map.put("womanCount", womanCount);


        return map;
    }




}
