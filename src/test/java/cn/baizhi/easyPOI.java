package cn.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.baizhi.service.UserService;
import cn.baizhi.test.Product;
import cn.baizhi.test.Student;
import cn.baizhi.test.Teacher;
import cn.baizhi.test.User;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class easyPOI {
    @Autowired
    private UserService userService;
    //导出
    @Test
    public void test() throws IOException {
        //创建集合
        List<Student> list  = new ArrayList<>();
        list.add(new Student("1", "赵振豪", 22, new Date()));
        list.add(new Student("2", "赵振豪2", 23, new Date()));
        list.add(new Student("3", "赵振豪", 24, new Date()));

                                                                      //头部         表名
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("学生信息","学生"),
                Student .class, list);
        workbook.write(new FileOutputStream("f:\\easypoi.xls"));
    }
    //导出
    @Test
    public  void  test2() throws IOException {
        //创建学生集合
        List<Student> students  = new ArrayList<>();
        students.add(new Student("1", "赵振豪", 22, new Date()));
        students.add(new Student("2", "赵振豪2", 23, new Date()));
        students.add(new Student("3", "赵振豪", 24, new Date()));


        //创建teacher集合
        List<Teacher> teachers  = new ArrayList<>();
        teachers.add(new Teacher("5", "gaoj", 10000.0, students));
        teachers.add(new Teacher("6", "xzhao", 30000.0, students));
        teachers.add(new Teacher("7", "ws", 20000.0, students));

        //导入工具对象调用
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("老师管理表", "老师"), Teacher.class, teachers);
        //指定位置
        workbook.write(new FileOutputStream("f:\\easypoi.xls"));
    }
    //导入
    @Test
    public void test3(){
        //导入参数对象
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //表格标题
        params.setHeadRows(2);//表头
        //创建导入工具类 返回集合
        List<Teacher> teachers = ExcelImportUtil.importExcel(new File("f:\\easypoi.xls"), Teacher.class, params);
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
    //导出用户头像
    @Test
    public void test4() throws IOException {
        //创建集合
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("5", "C:\\Users\\admin\\Pictures\\Camera Roll\\8a5f8645253d26b.jpg", "张东健",null));
        users.add(new User("6", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg", "张东健2",null));
        users.add(new User("7", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg", "张东健3",null));
        //导入工具类调用
        //导入工具对象调用
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户管理表", "用户"), User.class, users);
        //指定位置
        workbook.write(new FileOutputStream("f:\\easypoi-user.xls"));

    }
    //导入用户头像
    @Test
    public void test5(){
        //设置导入参数
        ImportParams params = new ImportParams();

        params.setTitleRows(1);//表的标题
        params.setHeadRows(1);//表头
        //导入工具类 返回list
        List<User> users = ExcelImportUtil.importExcel(new File("f:\\easypoi-user.xls "), User.class, params);
        for (User user : users) {
            System.out.println(user);
        }
    }
    //导出用户头像 商品信息
    @Test
    public void test6() throws IOException {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "iphone", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg"));
        products.add(new Product("2", "iphone5", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg"));
        products.add(new Product("3", "iphone6", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg"));



        //创建集合
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("5", "C:\\Users\\admin\\Pictures\\Camera Roll\\8a5f8645253d26b.jpg", "张东健",products));
        users.add(new User("6", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg", "张东健2",products));
        users.add(new User("7", "C:\\Users\\admin\\Pictures\\Camera Roll\\45a4ed976cc37381.jpg", "张东健3",products));
        //导入工具类调用
        //导入工具对象调用
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户管理表", "用户"), User.class, users);
        //指定位置
        workbook.write(new FileOutputStream("f:\\easypoi-user-product.xls"));

    }
}
