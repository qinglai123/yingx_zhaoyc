package cn.baizhi.test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {
    @Excel(name = "老师的id",needMerge = true)
    private String id;
    @Excel(name = "老师的名字",needMerge = true)
    private String name;
    @Excel(name = "老师的工资",needMerge = true)
    private Double salary;
    @ExcelCollection(name = "指定学员")   //集合类型注解
    private List<Student> student;
}
