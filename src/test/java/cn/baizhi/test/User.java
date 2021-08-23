package cn.baizhi.test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name = "用户的id",needMerge = true)    //needMerge = true的属性,就可以完成单元格的合并
    private String id;
    @Excel(name = "用户的头像",type = 2)   //1是文本 2是图片
    private String headPath;
    @Excel(name = "用户名",needMerge = true)
    private String username;
    @Excel(name = "用户的商品信息")
    private List<Product> products;
}
