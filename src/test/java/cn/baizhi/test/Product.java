package cn.baizhi.test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Excel(name = "商品的id")
    private String id;
    @Excel(name = "商品名")
    private String name;
    @Excel(name = "商品图",type = 2)
    private String path;
}
