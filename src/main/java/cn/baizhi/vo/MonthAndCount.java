package cn.baizhi.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthAndCount {
    private Integer month;  //月份
    private Integer count;   //数量
}
