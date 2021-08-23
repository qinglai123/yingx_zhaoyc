package cn.baizhi.test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @Excel(name="学生的id")
    private String id;
    @Excel(name="学生的姓名")
    private String name;
    @Excel(name="学生的年龄")
    private Integer age;
    @Excel(name="学生的生日",exportFormat = "yyyy-MM-dd",importFormat = "yyyy-MM-dd")
    private Date bir;
}
